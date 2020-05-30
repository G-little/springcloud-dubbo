package com.little.g.springcloud.mall.api;

import com.little.g.springcloud.mall.dto.LitemallGrouponDTO;

public interface QCodeService {

	String createGrouponShareImage(String goodName, String goodPicUrl,
			LitemallGrouponDTO groupon);

	String createGoodShareImage(String goodId, String goodPicUrl, String goodName);

}
