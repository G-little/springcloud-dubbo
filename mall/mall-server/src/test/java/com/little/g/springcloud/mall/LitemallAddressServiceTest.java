package com.little.g.springcloud.mall;

import com.alibaba.fastjson.JSONObject;
import com.little.g.springcloud.mall.api.LitemallAddressService;
import com.little.g.springcloud.mall.dto.LitemallAddressDTO;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

public class LitemallAddressServiceTest extends BaseTest {

    @Resource
    private LitemallAddressService litemallAddressService;

    @Test
    public void testSave() {
        LitemallAddressDTO litemallAddressDTO = JSONObject.parseObject("{\n" +
                "  \"addTime\": \"2020-06-09 12:54:39\",\n" +
                "  \"addressDetail\": \"string\",\n" +
                "  \"areaCode\": \"string\",\n" +
                "  \"city\": \"string\",\n" +
                "  \"county\": \"string\",\n" +
                "  \"default\": true,\n" +
                "  \"deleted\": true,\n" +
                "  \"id\": 0,\n" +
                "  \"name\": \"string\",\n" +
                "  \"postalCode\": \"string\",\n" +
                "  \"province\": \"string\",\n" +
                "  \"tel\": \"15201008961\",\n" +
                "  \"updateTime\": \"2020-06-09 12:54:39\",\n" +
                "  \"userId\": 0\n" +
                "}", LitemallAddressDTO.class);
        int add = litemallAddressService.add(litemallAddressDTO);
        Assert.assertTrue(add>0);
    }
}
