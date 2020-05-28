package com.little.g.springcloud.common.utils;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
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
            return Lists.newArrayList();
        }
        return (List<T>) source.stream().map(s -> convert2T(s, t))
                .collect(Collectors.toList());
    }

    public static <T> PageInfo<T> convert2Page(Collection source, Class<T> t) {
        PageInfo<T> pageInfo = new PageInfo<>();
        if (CollectionUtils.isEmpty(source)) {
            return pageInfo;
        }

        pageInfo.setList(DTOUtil.convert2List(source, t));
        return pageInfo;
    }

}
