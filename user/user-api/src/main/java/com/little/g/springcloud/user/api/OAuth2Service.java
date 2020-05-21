package com.little.g.springcloud.user.api;

import com.little.g.springcloud.common.ResultJson;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Created by lengligang on 2019/4/17.
 */
public interface OAuth2Service {

	ResultJson login(@NotEmpty @Size(max = 10) String type,
			@NotEmpty @Size(max = 100) String code, @Size(max = 100) String deviceId,
			Byte deviceType);

}
