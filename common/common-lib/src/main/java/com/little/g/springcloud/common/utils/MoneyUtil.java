package com.little.g.springcloud.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Created by lengligang on 16/1/29.
 */

/**
 * Created by lengligang on 16/1/29.
 */
public class MoneyUtil {

	private static final Logger log = LoggerFactory.getLogger(MoneyUtil.class);

	private static final BigDecimal Hundered = new BigDecimal(100);

	public static Double long2Double(Long fen) {
		if (fen == null) {
			return 0.0;
		}
		return new BigDecimal(fen).divide(Hundered).doubleValue();
	}

	public static Long double2Long(Double yuan) {
		if (yuan == null)
			return 0l;
		return new BigDecimal(yuan).multiply(Hundered)
				.setScale(2, BigDecimal.ROUND_HALF_UP).longValue();
	}

	public static Long double2LongEqual(Double yuan) {
		if (yuan == null)
			return 0l;
		return new BigDecimal(yuan).longValue();
	}

	public static Long String2Long(String yuan) {
		if (yuan == null)
			return 0l;
		return new BigDecimal(yuan).multiply(Hundered).longValue();
	}

	public static Long bigdecimal2Long(BigDecimal yuan) {
		if (yuan == null)
			return 0l;
		return yuan.multiply(Hundered).longValue();
	}

	public static Double mul(double p1, double p2) {

		BigDecimal b1 = new BigDecimal(p1);
		BigDecimal b2 = new BigDecimal(p2);

		return b1.multiply(b2).doubleValue();

	}

}
