package com.little.g.springcloud.common;

import com.google.common.collect.Maps;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用JSON返回类
 */
@ApiModel
public class ResultJson<T> implements Serializable {

    public static final Integer SUCCESSFUL = 0; // 成功

    public static final Integer SYSTEM_UNKNOWN_EXCEPTION = 10001;

    public static final Integer INVALID_PARAM = 401;

    public static final Map<Integer, String> msg = new HashMap<Integer, String>();

    @ApiModelProperty(value = "错误码", position = 1)
    private Integer c = SUCCESSFUL; // code

    @ApiModelProperty(value = "错误信息", position = 0)
    private String m; // message

    private Map<String, Object> d = null; // data

    private T data; // 优先使用，为null才使用d

    public Integer getC() {
        return c;
    }

    public void setC(Integer c) {
        this.c = c;
    }

    public String getM() {
        return StringUtils.defaultIfEmpty(m, msg.get(c));
    }

    public static String getM(int code) {
        return StringUtils.defaultIfEmpty(msg.get(code), "");
    }

    public ResultJson setM(String m) {
        this.m = m;
        return this;
    }

    @ApiModelProperty(value = "业务数据", position = 2)
    public T getD() {
        return data == null ? (T) d : data;
    }

    public ResultJson setData(T data) {
        this.data = data;
        return this;
    }

    public ResultJson putD(String name, Object value) {
        if (this.d == null) {
            this.d = Maps.newHashMap();
        }

        if (StringUtils.isNotEmpty(name)) {
            this.d.put(name, value);
        }
        return this;
    }

    public ResultJson putAllD(Map<String, Object> maps) {
        if (this.d == null) {
            this.d = Maps.newHashMap();
        }

        if (maps != null && !maps.isEmpty()) {
            this.d.putAll(maps);
        }
        return this;
    }

    public ResultJson putAllArrD(Map<String, String[]> maps) {
        if (this.d == null) {
            this.d = Maps.newHashMap();
        }

        if (maps != null && !maps.isEmpty()) {
            this.d.putAll(maps);
        }
        return this;
    }

    public static ResultJson newInstance() {
        return new ResultJson();
    }

}
