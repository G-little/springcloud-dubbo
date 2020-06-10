package com.little.g.springcloud.admin.web.controller.mall;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissions;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissionsDesc;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.mall.api.LitemallKeywordService;
import com.little.g.springcloud.mall.dto.LitemallKeywordDTO;
import com.little.g.springcloud.mall.validator.Order;
import com.little.g.springcloud.mall.validator.Sort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@Api("关键词")
@RestController
@RequestMapping("/admin/keyword")
@Validated
public class AdminKeywordController {

    private final Log logger = LogFactory.getLog(AdminKeywordController.class);

    @Reference
    private LitemallKeywordService keywordService;

    @ApiOperation("关键词管理")
    @RequiresPermissions("admin:keyword:list")
    @RequiresPermissionsDesc(menu = {"商场管理", "关键词"}, button = "查询")
    @GetMapping("/list")
    public ResultJson<Page<LitemallKeywordDTO>> list(String keyword, String url,
                                                     @RequestParam(defaultValue = "1") Integer page,
                                                     @RequestParam(defaultValue = "10") Integer limit,
                                                     @Sort @RequestParam(defaultValue = "add_time") String sort,
                                                     @Order @RequestParam(defaultValue = "desc") String order) {
        PageInfo<LitemallKeywordDTO> pageInfo = keywordService.querySelective(keyword,
                url, page, limit, sort, order);
        return ResponseUtil.okPage(pageInfo);
    }

    private ResultJson validate(LitemallKeywordDTO keywords) {
        String keyword = keywords.getKeyword();
        if (StringUtils.isEmpty(keyword)) {
            return ResponseUtil.badArgument();
        }
        return null;
    }

    @RequiresPermissions("admin:keyword:create")
    @RequiresPermissionsDesc(menu = {"商场管理", "关键词"}, button = "添加")
    @PostMapping("/create")
    public ResultJson<LitemallKeywordDTO> create(@RequestBody LitemallKeywordDTO keyword) {
        ResultJson error = validate(keyword);
        if (error != null) {
            return error;
        }
        keywordService.add(keyword);
        return ResponseUtil.ok(keyword);
    }

    @ApiOperation("关键词详情")
    @RequiresPermissions("admin:keyword:read")
    @RequiresPermissionsDesc(menu = {"商场管理", "关键词"}, button = "详情")
    @GetMapping("/read")
    public ResultJson<LitemallKeywordDTO> read(@NotNull Integer id) {
        LitemallKeywordDTO keyword = keywordService.findById(id);
        return ResponseUtil.ok(keyword);
    }

    @ApiOperation("更新关键词")
    @RequiresPermissions("admin:keyword:update")
    @RequiresPermissionsDesc(menu = {"商场管理", "关键词"}, button = "编辑")
    @PostMapping("/update")
    public ResultJson<LitemallKeywordDTO> update(@RequestBody LitemallKeywordDTO keyword) {
        ResultJson error = validate(keyword);
        if (error != null) {
            return error;
        }
        if (keywordService.updateById(keyword) == 0) {
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok(keyword);
    }

    @RequiresPermissions("admin:keyword:delete")
    @RequiresPermissionsDesc(menu = {"商场管理", "关键词"}, button = "删除")
    @PostMapping("/delete")
    public ResultJson delete(@RequestBody LitemallKeywordDTO keyword) {
        Integer id = keyword.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        keywordService.deleteById(id);
        return ResponseUtil.ok();
    }

}
