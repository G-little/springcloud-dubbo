package com.little.g.springcloud.mall.web.vo;

import com.little.g.springcloud.mall.dto.LitemallKeywordDTO;
import com.little.g.springcloud.mall.dto.LitemallSearchHistoryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("搜索结果")
@Data
public class SearchIndexVo {
    @ApiModelProperty("默认搜索词")
    private LitemallKeywordDTO defaultKeyword;
    @ApiModelProperty("历史搜索词")
    private List<LitemallSearchHistoryDTO> historyKeywordList;
    @ApiModelProperty("热门搜索词")
    private List<LitemallKeywordDTO> hotKeywordList;


}
