package com.little.g.springcloud.mall.web.controller;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.mall.api.LitemallIssueService;
import com.little.g.springcloud.mall.dto.LitemallIssueDTO;
import com.little.g.springcloud.mall.validator.Order;
import com.little.g.springcloud.mall.validator.Sort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("常见问题")
@RestController
@RequestMapping("/issue")
@Validated
public class IssueController {

    @Reference
    private LitemallIssueService issueService;

    /**
     * 帮助中心
     */
    @RequestMapping("/list")
    @ApiOperation("帮助中心")
    public ResultJson<Page<LitemallIssueDTO>> list(String question,
                                                   @RequestParam(defaultValue = "1") Integer page,
                                                   @RequestParam(defaultValue = "10") Integer size,
                                                   @Sort @RequestParam(defaultValue = "add_time") String sort,
                                                   @Order @RequestParam(defaultValue = "desc") String order) {
        PageInfo<LitemallIssueDTO> pageInfo = issueService.querySelective(question, page,
                size, sort, order);
        return ResponseUtil.okPage(pageInfo);
    }

}
