package com.little.g.springcloud.mall.web.controller;

import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.mall.api.LitemallCategoryService;
import com.little.g.springcloud.mall.dto.LitemallCategoryDTO;
import com.little.g.springcloud.mall.web.manager.HomeCacheManager;
import com.little.g.springcloud.mall.web.vo.CategoryAllVo;
import com.little.g.springcloud.mall.web.vo.CategoryCurrentVo;
import com.little.g.springcloud.mall.web.vo.CategoryIndexVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类目服务
 */
@Api
@RestController
@RequestMapping("/catalog")
@Slf4j
@Validated
public class CatalogController {

    @Reference
    private LitemallCategoryService categoryService;

    @ApiOperation("获取一级分类类目")
    @GetMapping("/getfirstcategory")
    public ResultJson<List<LitemallCategoryDTO>> getFirstCategory() {
        // 所有一级分类目录
        List<LitemallCategoryDTO> l1CatList = categoryService.queryL1();
        return ResponseUtil.ok(l1CatList);
    }

    @ApiOperation("所有二级类目")
    @GetMapping("/getsecondcategory")
    public ResultJson<List<LitemallCategoryDTO>> getSecondCategory(@NotNull Integer id) {
        // 所有二级分类目录
        List<LitemallCategoryDTO> currentSubCategory = categoryService.queryByPid(id);
        return ResponseUtil.ok(currentSubCategory);
    }

    /**
     * 分类详情
     *
     * @param id 分类类目ID。 如果分类类目ID是空，则选择第一个分类类目。 需要注意，这里分类类目是一级类目
     * @return 分类详情
     */
    @ApiOperation("分类详情 ")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id", value = "分类类目ID。 如果分类类目ID是空，则选择第一个分类类目。 需要注意，这里分类类目是一级类目", dataType = "Integer", required = false)
    )
    @GetMapping("index")
    public ResultJson<CategoryIndexVo> index(Integer id) {

        // 所有一级分类目录
        List<LitemallCategoryDTO> l1CatList = categoryService.queryL1();

        // 当前一级分类目录
        LitemallCategoryDTO currentCategory = null;
        if (id != null) {
            currentCategory = categoryService.findById(id);
        } else {
            if (l1CatList.size() > 0) {
                currentCategory = l1CatList.get(0);
            }
        }

        // 当前一级分类目录对应的二级分类目录
        List<LitemallCategoryDTO> currentSubCategory = null;
        if (null != currentCategory) {
            currentSubCategory = categoryService.queryByPid(currentCategory.getId());
        }

        CategoryIndexVo vo = new CategoryIndexVo();
        vo.setCategoryList(l1CatList);
        vo.setCurrentCategory(currentCategory);
        vo.setCurrentSubCategory(currentSubCategory);


        return ResponseUtil.ok(vo);
    }

    /**
     * 所有分类数据
     *
     * @return 所有分类数据
     */
    @ApiOperation("查询所有分类数据")
    @GetMapping("all")
    public ResultJson<CategoryAllVo> queryAll() {
        // 优先从缓存中读取
        if (HomeCacheManager.hasData(HomeCacheManager.CATALOG)) {
            return ResponseUtil
                    .ok(HomeCacheManager.getCacheData(HomeCacheManager.CATALOG, CategoryAllVo.class));
        }

        // 所有一级分类目录
        List<LitemallCategoryDTO> l1CatList = categoryService.queryL1();

        // 所有子分类列表
        Map<Integer, List<LitemallCategoryDTO>> allList = new HashMap<>();
        List<LitemallCategoryDTO> sub;
        for (LitemallCategoryDTO category : l1CatList) {
            sub = categoryService.queryByPid(category.getId());
            allList.put(category.getId(), sub);
        }

        // 当前一级分类目录
        LitemallCategoryDTO currentCategory = l1CatList.get(0);

        // 当前一级分类目录对应的二级分类目录
        List<LitemallCategoryDTO> currentSubCategory = null;
        if (null != currentCategory) {
            currentSubCategory = categoryService.queryByPid(currentCategory.getId());
        }

        CategoryAllVo vo = new CategoryAllVo();
        vo.setCategoryList(l1CatList);
        vo.setAllList(allList);
        vo.setCurrentCategory(currentCategory);
        vo.setCurrentSubCategory(currentSubCategory);


        // 缓存数据
        HomeCacheManager.loadData(HomeCacheManager.CATALOG, vo);
        return ResponseUtil.ok(vo);
    }

    /**
     * 当前分类栏目
     *
     * @param id 分类类目ID
     * @return 当前分类栏目
     */
    @ApiOperation("查询当前分类栏目")
    @GetMapping("current")
    public Object current(@NotNull Integer id) {
        // 当前分类
        LitemallCategoryDTO currentCategory = categoryService.findById(id);
        if (currentCategory == null) {
            return ResponseUtil.badArgumentValue();
        }
        List<LitemallCategoryDTO> currentSubCategory = categoryService
                .queryByPid(currentCategory.getId());

        CategoryCurrentVo vo = new CategoryCurrentVo();
        vo.setCurrentCategory(currentCategory);
        vo.setCurrentSubCategory(currentSubCategory);
        return ResponseUtil.ok(vo);
    }

}
