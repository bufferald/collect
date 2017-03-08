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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jooq.impl.DataSourceConnectionProvider;
import org.openforis.collect.config.CollectConfiguration;
import org.openforis.collect.config.CollectConfiguration.ServiceConfiguration;
import org.openforis.collect.persistence.DbInitializer;
import org.openforis.collect.persistence.DbUtils;

public class ApplicationInitializerServletContextListener implements ServletContextListener {

	private final Log LOG = LogFactory.getLog(ApplicationInitializerServletContextListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		LOG.info("========Open Foris Collect - Starting initialization ==========");
		initDB();
		configureOfUsersModule(sce);
		LOG.info("========Open Foris Collect - Initialized ======================");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

	private void configureOfUsersModule(ServletContextEvent sce) {
		LOG.info("======== Starting OF-Users module configuration ========");
		ServletContext sc = sce.getServletContext();
		String usersServiceProtocol = ObjectUtils.defaultIfNull(sc.getInitParameter("of_users.service.protocol"), "http");
		String usersServiceHost = ObjectUtils.defaultIfNull(sc.getInitParameter("of_users.service.host"), determineHostName());
		int usersServicePort = Integer.parseInt(ObjectUtils.defaultIfNull(sc.getInitParameter("of_users.service.port"), determineConnectorProperty("port")));
		
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
		try {
			String hostName = InetAddress.getLocalHost().getHostAddress();
			return hostName;
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
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
	
}