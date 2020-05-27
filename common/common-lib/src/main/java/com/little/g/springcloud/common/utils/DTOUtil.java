package com.little.g.springcloud.common.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DTOUtil {

    public static <T> T convert2T(Object source, Class<T> t) {
        try {
            if (source == null) {
                return null;
            }
            T target = t.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> convert2List(Collection source, Class<T> t) {
        if (CollectionUtils.isEmpty(source)) {
            return null;
        }
        return (List<T>) source.stream().map(s -> convert2T(s, t)).collect(Collectors.toList());
    }
}
