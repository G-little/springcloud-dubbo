package com.little.g.springcloud.admin.web.controller.mall;

import com.little.g.springcloud.admin.web.vo.RegionVo;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.mall.api.LitemallRegionService;
import com.little.g.springcloud.mall.dto.LitemallRegionDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Api("行政区域")
@RestController
@RequestMapping("/admin/region")
@Validated
public class AdminRegionController {

	private final Log logger = LogFactory.getLog(AdminRegionController.class);

	@Reference
	private LitemallRegionService regionService;

	@ApiOperation("子行政区域列表")
	@ApiImplicitParam(name = "id", value = "父行政区ID", dataType = "int", example = "1")
	@GetMapping("/clist")
	public ResultJson<Page<LitemallRegionDTO>> clist(@RequestParam @NotNull Integer id) {
		List<LitemallRegionDTO> regionList = regionService.queryByPid(id);
		return ResponseUtil.okList(regionList);
	}

	@ApiOperation("所有行政区域列表")
	@GetMapping("/list")
	public ResultJson<Page<RegionVo>> list() {
		List<RegionVo> regionVoList = new ArrayList<>();

		List<LitemallRegionDTO> litemallRegions = regionService.getAll();
		Map<Byte, List<LitemallRegionDTO>> collect = litemallRegions.stream()
				.collect(Collectors.groupingBy(LitemallRegionDTO::getType));
		byte provinceType = 1;
		List<LitemallRegionDTO> provinceList = collect.get(provinceType);
		byte cityType = 2;
		List<LitemallRegionDTO> city = collect.get(cityType);
		Map<Integer, List<LitemallRegionDTO>> cityListMap = city.stream()
				.collect(Collectors.groupingBy(LitemallRegionDTO::getPid));
		byte areaType = 3;
		List<LitemallRegionDTO> areas = collect.get(areaType);
		Map<Integer, List<LitemallRegionDTO>> areaListMap = areas.stream()
				.collect(Collectors.groupingBy(LitemallRegionDTO::getPid));

		for (LitemallRegionDTO province : provinceList) {
			RegionVo provinceVO = new RegionVo();
			provinceVO.setId(province.getId());
			provinceVO.setName(province.getName());
			provinceVO.setCode(province.getCode());
			provinceVO.setType(province.getType());

			List<LitemallRegionDTO> cityList = cityListMap.get(province.getId());
			List<RegionVo> cityVOList = new ArrayList<>();
			for (LitemallRegionDTO cityVo : cityList) {
				RegionVo cityVO = new RegionVo();
				cityVO.setId(cityVo.getId());
				cityVO.setName(cityVo.getName());
				cityVO.setCode(cityVo.getCode());
				cityVO.setType(cityVo.getType());

				List<LitemallRegionDTO> areaList = areaListMap.get(cityVo.getId());
				List<RegionVo> areaVOList = new ArrayList<>();
				for (LitemallRegionDTO area : areaList) {
					RegionVo areaVO = new RegionVo();
					areaVO.setId(area.getId());
					areaVO.setName(area.getName());
					areaVO.setCode(area.getCode());
					areaVO.setType(area.getType());
					areaVOList.add(areaVO);
				}

				cityVO.setChildren(areaVOList);
				cityVOList.add(cityVO);
			}
			provinceVO.setChildren(cityVOList);
			regionVoList.add(provinceVO);
		}

		return ResponseUtil.okList(regionVoList);
	}

}
