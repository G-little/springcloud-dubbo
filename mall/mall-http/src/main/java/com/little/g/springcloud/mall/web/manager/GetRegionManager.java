package com.little.g.springcloud.mall.web.manager;

import com.little.g.springcloud.mall.api.LitemallRegionService;
import com.little.g.springcloud.mall.dto.LitemallRegionDTO;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhy
 * @date 2019-01-17 23:07
 **/
@Component
public class GetRegionManager {

	@Reference
	private LitemallRegionService regionService;

	private static List<LitemallRegionDTO> litemallRegions;

	protected List<LitemallRegionDTO> getLitemallRegions() {
		if (litemallRegions == null) {
			createRegion();
		}
		return litemallRegions;
	}

	private synchronized void createRegion() {
		if (litemallRegions == null) {
			litemallRegions = regionService.getAll();
		}
	}

}
