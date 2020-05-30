package com.little.g.springcloud.mall.api;

import com.little.g.springcloud.mall.dto.ExpressInfoDTO;

import java.util.List;
import java.util.Map;

public interface ExpressService {

	String getVendorName(String vendorCode);

	List<Map<String, String>> getVendors();

	ExpressInfoDTO getExpressInfo(String expCode, String expNo);

}
