package org.openforis.idm.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.openforis.idm.metamodel.Unit;

/**
 * @author G. Miceli
 * @author M. Togna
 */
public final class RealRange extends NumericRange<Double> {

	private static final Pattern PATTERN = Pattern.compile("(-?\\d+(\\.\\d+)?)(-(-?\\d+(\\.\\d+)?))?");
	private static final String INTERNAL_STRING_FORMAT = "%f - %f";

	public RealRange(Double value, Unit unit) {
		super(value, value, unit);
	}

	public RealRange(Double from, Double to) {
		super(from, to, null);
	}
	
	public RealRange(Double from, Double to, Unit unit) {
		super(from, to, unit);
	}

	public static RealRange parseRealRange(String string, Unit unit) {
		if ( StringUtils.isBlank(string) ) {
			return null;
		} else {
			Matcher matcher = PATTERN.matcher(string);
			if ( matcher.matches() ) {
				String fromStr = matcher.group(1);
				String toStr = matcher.group(4);
				if ( toStr == null ) {
					toStr = fromStr;
				}
				double from = Double.parseDouble(fromStr);
				double to = Double.parseDouble(toStr);
				return new RealRange(from, to, unit);
			} else {
				return null;
			}
		}
	}

	@Override
	public String toPrettyFormatString() {
		return toInternalString();
	}
	
	@Override
	public String toInternalString() {
		return String.format(INTERNAL_STRING_FORMAT, getFrom(), getTo());
	}
}