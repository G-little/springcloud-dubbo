package com.little.g.springcloud.admin.web.controller.mall;

import com.little.g.springcloud.admin.web.annotation.RequiresPermissions;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissionsDesc;
import com.little.g.springcloud.admin.web.vo.CategoryVo;
import com.little.g.springcloud.admin.web.vo.L1CategoryVo;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.mall.api.LitemallCategoryService;
import com.little.g.springcloud.mall.dto.LitemallCategoryDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Api("类目管理")
@RestController
@RequestMapping("/admin/category")
@Validated
public class AdminCategoryController {

    private final Log logger = LogFactory.getLog(AdminCategoryController.class);

    @Reference
    private LitemallCategoryService categoryService;

    @ApiOperation("类目分页查询")
    @RequiresPermissions("admin:category:list")
    @RequiresPermissionsDesc(menu = {"商场管理", "类目管理"}, button = "查询")
    @GetMapping("/list")
    public ResultJson<Page<CategoryVo>> list() {
        List<CategoryVo> categoryVoList = new ArrayList<>();

        List<LitemallCategoryDTO> categoryList = categoryService.queryByPid(0);
        for (LitemallCategoryDTO category : categoryList) {
            CategoryVo categoryVO = new CategoryVo();
            categoryVO.setId(category.getId());
            categoryVO.setDesc(category.getDesc());
            categoryVO.setIconUrl(category.getIconUrl());
            categoryVO.setPicUrl(category.getPicUrl());
            categoryVO.setKeywords(category.getKeywords());
            categoryVO.setName(category.getName());
            categoryVO.setLevel(category.getLevel());

            List<CategoryVo> children = new ArrayList<>();
            List<LitemallCategoryDTO> subCategoryList = categoryService
                    .queryByPid(category.getId());
            for (LitemallCategoryDTO subCategory : subCategoryList) {
                CategoryVo subCategoryVo = new CategoryVo();
                subCategoryVo.setId(subCategory.getId());
                subCategoryVo.setDesc(subCategory.getDesc());
                subCategoryVo.setIconUrl(subCategory.getIconUrl());
                subCategoryVo.setPicUrl(subCategory.getPicUrl());
                subCategoryVo.setKeywords(subCategory.getKeywords());
                subCategoryVo.setName(subCategory.getName());
                subCategoryVo.setLevel(subCategory.getLevel());

                children.add(subCategoryVo);
            }

            categoryVO.setChildren(children);
            categoryVoList.add(categoryVO);
        }

        return ResponseUtil.okList(categoryVoList);
    }

    private ResultJson validate(LitemallCategoryDTO category) {
        String name = category.getName();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }

        String level = category.getLevel();
        if (StringUtils.isEmpty(level)) {
            return ResponseUtil.badArgument();
        }
        if (!level.equals("L1") && !level.equals("L2")) {
            return ResponseUtil.badArgumentValue();
        }

        Integer pid = category.getPid();
        if (level.equals("L2") && (pid == null)) {
            return ResponseUtil.badArgument();
        }

        return null;
    }

    @ApiOperation("添加类目")
    @RequiresPermissions("admin:category:create")
    @RequiresPermissionsDesc(menu = {"商场管理", "类目管理"}, button = "添加")
    @PostMapping("/create")
    public ResultJson create(@RequestBody LitemallCategoryDTO category) {
        ResultJson error = validate(category);
        if (error != null) {
            return error;
        }
        categoryService.add(category);
        return ResponseUtil.ok(category);
    }

    @ApiOperation("类目详情")
    @RequiresPermissions("admin:category:read")
    @RequiresPermissionsDesc(menu = {"商场管理", "类目管理"}, button = "详情")
    @GetMapping("/read")
    public ResultJson<LitemallCategoryDTO> read(@NotNull Integer id) {
        LitemallCategoryDTO category = categoryService.findById(id);
        return ResponseUtil.ok(category);
    }

    @ApiOperation("更新类目")
    @RequiresPermissions("admin:category:update")
    @RequiresPermissionsDesc(menu = {"商场管理", "类目管理"}, button = "编辑")
    @PostMapping("/update")
    public ResultJson update(@RequestBody LitemallCategoryDTO category) {
        ResultJson error = validate(category);
        if (error != null) {
            return error;
        }

        if (categoryService.updateById(category) == 0) {
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok();
    }

    @ApiOperation("删除类目")
    @RequiresPermissions("admin:category:delete")
    @RequiresPermissionsDesc(menu = {"商场管理", "类目管理"}, button = "删除")
    @PostMapping("/delete")
    public ResultJson delete(@RequestBody LitemallCategoryDTO category) {
        Integer id = category.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        categoryService.deleteById(id);
        return ResponseUtil.ok();
    }

    @ApiOperation("一级类目")
    @RequiresPermissions("admin:category:list")
    @GetMapping("/l1")
    public ResultJson<Page<L1CategoryVo>> catL1() {
        // 所有一级分类目录
        List<LitemallCategoryDTO> l1CatList = categoryService.queryL1();
        List<L1CategoryVo> data = new ArrayList<>(l1CatList.size());
        for (LitemallCategoryDTO category : l1CatList) {
            L1CategoryVo vo = new L1CategoryVo();
            vo.setValue(category.getId());
            vo.setLabel(category.getName());
            data.add(vo);
        }
        return ResponseUtil.okList(data);
    }

}
