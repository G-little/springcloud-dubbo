package com.little.g.springcloud.admin.web.controller.mall;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissions;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissionsDesc;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.mall.api.LitemallFootprintService;
import com.little.g.springcloud.mall.dto.LitemallFootprintDTO;
import com.little.g.springcloud.mall.validator.Order;
import com.little.g.springcloud.mall.validator.Sort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("用户足迹管理")
@RestController
@RequestMapping("/admin/footprint")
@Validated
public class AdminFootprintController {

    private final Log logger = LogFactory.getLog(AdminFootprintController.class);

    @Reference
    private LitemallFootprintService footprintService;

    @ApiOperation("用户足迹分页查询")
    @RequiresPermissions("admin:footprint:list")
    @RequiresPermissionsDesc(menu = {"用户管理", "用户足迹"}, button = "查询")
    @GetMapping("/list")
    public ResultJson<Page<LitemallFootprintDTO>> list(String userId, String goodsId,
                                                       @RequestParam(defaultValue = "1") Integer page,
                                                       @RequestParam(defaultValue = "10") Integer limit,
                                                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                                                       @Order @RequestParam(defaultValue = "desc") String order) {
        PageInfo<LitemallFootprintDTO> pageInfo = footprintService.querySelective(userId,
                goodsId, page, limit, sort, order);
        return ResponseUtil.okPage(pageInfo);
    }

}
