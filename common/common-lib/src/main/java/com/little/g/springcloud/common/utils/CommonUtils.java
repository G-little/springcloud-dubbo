package com.little.g.springcloud.common.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class CommonUtils {

	private static String ip;
	static {
		try {
			InetAddress addr = InetAddress.getLocalHost();
			ip = addr.getHostAddress();
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public static String getLocalIp() {
		return ip;
	}

}
