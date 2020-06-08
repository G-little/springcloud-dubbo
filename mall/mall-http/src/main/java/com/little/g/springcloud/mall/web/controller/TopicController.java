package com.little.g.springcloud.mall.web.controller;

import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.mall.api.LitemallGoodsService;
import com.little.g.springcloud.mall.api.LitemallTopicService;
import com.little.g.springcloud.mall.dto.LitemallGoodsDTO;
import com.little.g.springcloud.mall.dto.LitemallTopicDTO;
import com.little.g.springcloud.mall.validator.Order;
import com.little.g.springcloud.mall.validator.Sort;
import com.little.g.springcloud.mall.web.vo.TopicDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 专题服务
 */
@Api("专题服务")
@RestController
@RequestMapping("/topic")
@Validated
@Slf4j
public class TopicController {

    @Reference
    private LitemallTopicService topicService;

    @Reference
    private LitemallGoodsService goodsService;

    /**
     * 专题列表
     *
     * @param page  分页页数
     * @param limit 分页大小
     * @return 专题列表
     */
    @ApiOperation("专题列表")
    @GetMapping("list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页", dataType = "Integer",
                    required = false, defaultValue = "1"),
            @ApiImplicitParam(name = "limit", value = "单页条数", dataType = "Integer",
                    required = false, defaultValue = "10")})
    public ResultJson<Page<LitemallTopicDTO>> list(@RequestParam(defaultValue = "1") Integer page,
                                                   @RequestParam(defaultValue = "10") Integer limit,
                                                   @Sort @RequestParam(defaultValue = "add_time") String sort,
                                                   @Order @RequestParam(defaultValue = "desc") String order) {
        List<LitemallTopicDTO> topicList = topicService.queryList(page, limit, sort,
                order);
        return ResponseUtil.okList(topicList);
    }

    /**
     * 专题详情
     *
     * @param id 专题ID
     * @return 专题详情
     */
    @ApiOperation("专题详情")
    @ApiImplicitParam(name = "id", value = "专题Id")
    @GetMapping("detail")
    public ResultJson<TopicDetailVo> detail(@NotNull Integer id) {
        LitemallTopicDTO topic = topicService.findById(id);
        List<LitemallGoodsDTO> goods = new ArrayList<>();
        for (Integer i : topic.getGoods()) {
            LitemallGoodsDTO good = goodsService.findByIdVO(i);
            if (null != good) {
                goods.add(good);
            }
        }

        TopicDetailVo entity = new TopicDetailVo();
        entity.setTopic(topic);
        entity.setGoods(goods);
        return ResponseUtil.ok(entity);
    }

    /**
     * 相关专题
     *
     * @param id 专题ID
     * @return 相关专题
     */
    @ApiOperation("相关主题")
    @ApiImplicitParam(name = "id", value = "专题Id")
    @GetMapping("related")
    public ResultJson<Page<LitemallTopicDTO>> related(@NotNull Integer id) {
        List<LitemallTopicDTO> topicRelatedList = topicService.queryRelatedList(id, 0, 4);
        return ResponseUtil.okList(topicRelatedList);
    }

}
