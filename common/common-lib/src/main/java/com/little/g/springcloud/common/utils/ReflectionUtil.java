package com.little.g.springcloud.common.utils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

public class ReflectionUtil {

	public static void copyMapToObject(Map<String, ? extends Object> map, Object o) {
		Set<String> set = map.keySet();
		Class c = o.getClass();
		Field[] fields = c.getDeclaredFields();
		for (Field f : fields) {
			f.setAccessible(true);
			if (set.contains(f.getName())) {
				try {
					f.set(o, map.get(f.getName()));
				}
				catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

}
