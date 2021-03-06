package org.openforis.collect.web.listener;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Set;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.Query;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.impl.DataSourceConnectionProvider;
import org.openforis.collect.Collect;
import org.openforis.collect.config.CollectConfiguration;
import org.openforis.collect.config.CollectConfiguration.ServiceConfiguration;
import org.openforis.collect.persistence.DbInitializer;
import org.openforis.collect.persistence.DbUtils;

import io.sentry.DefaultSentryClientFactory;
import io.sentry.Sentry;
import io.sentry.context.ContextManager;
import io.sentry.context.SingletonContextManager;
import io.sentry.dsn.Dsn;

public class ApplicationInitializerServletContextListener implements ServletContextListener {

	private static final Logger LOG = LogManager.getLogger(ApplicationInitializerServletContextListener.class);
	private static final String SENTRY_DSN = "https://d3693c474ffb41f2b5e6265dc3411705@sentry.io/1246866?release=" + Collect.VERSION;
	private static final String DEV_MODE_INIT_PARAMETER_NAME = "devMode";

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		LOG.info("========Open Foris Collect - Starting initialization ==========");
		initSentry();
		initDB();
		CollectConfiguration.setDevelopmentMode(determineIsDevelopmentMode(sce));
		configureOfUsersModule(sce);
		LOG.info("========Open Foris Collect - Initialized ======================");
	}

	private void initSentry() {
		Sentry.init(SENTRY_DSN, new SentryClientFactory()); //use a custom client factory to support extra tags
		
//		Sentry.getContext().addTag( "ReleaseDate", UpdateIniUtils.getVersionInstalled() );
	}

	private boolean determineIsDevelopmentMode(ServletContextEvent sce) {
		return Boolean.parseBoolean(sce.getServletContext().getInitParameter(DEV_MODE_INIT_PARAMETER_NAME));
	}
	
	private void configureOfUsersModule(ServletContextEvent sce) {
		LOG.info("======== Starting OF-Users module configuration ========");
		ServletContext sc = sce.getServletContext();
		String usersServiceProtocol = ObjectUtils.defaultIfNull(sc.getInitParameter("of_users.service.protocol"), "http");
		String usersServiceHost = ObjectUtils.defaultIfNull(sc.getInitParameter("of_users.service.host"), determineHostName());
		int usersServicePort = Integer.parseInt(ObjectUtils.defaultIfNull(sc.getInitParameter("of_users.service.port"), determineLocalPort()));
		
		CollectConfiguration.initUsersServiceConfiguration(new ServiceConfiguration(usersServiceProtocol, usersServiceHost, usersServicePort));
		LOG.info("======== End of OF-Users module configuration ========");
	}

	private void initDB() {
		LOG.info("========Open Foris Collect - Starting DB initialization ========");
		DataSourceConnectionProvider connectionProvider = new DataSourceConnectionProvider(DbUtils.getDataSource());
		new DbInitializer(connectionProvider).start();
		LOG.info("========Open Foris Collect - DB Initialized ====================");
	}

	private String determineHostName() {
		if (CollectConfiguration.isDevelopmentMode()) {
			return "127.0.0.1";
		}
		try {
			String hostName = InetAddress.getLocalHost().getHostAddress();
			return hostName;
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
	}
	
	private String determineLocalPort() {
		try {
			return determineConnectorProperty("port");
		} catch(Exception e) {
			return "8380";
		}
	}
	
	private String determineConnectorProperty(String property) {
		try {
			MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
			Set<ObjectName> objectNames = mBeanServer.queryNames(new ObjectName("*:type=Connector,*"),
					Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
			String result = objectNames.iterator().next().getKeyProperty(property);
			return result;
		} catch (MalformedObjectNameException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

	private static class SentryClientFactory extends DefaultSentryClientFactory {

		@Override
		protected ContextManager getContextManager(Dsn dsn) {
			return new SingletonContextManager();
		}
	}
}