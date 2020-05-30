package com.little.g.springcloud.mall.api;

import com.little.g.springcloud.mall.dto.LitemallStorageDTO;
import org.springframework.core.io.Resource;

import java.io.InputStream;

public interface StorageService {

	Resource loadAsResource(String keyName);

	LitemallStorageDTO store(InputStream inputStream, long contentLength,
			String contentType, String fileName);

}
