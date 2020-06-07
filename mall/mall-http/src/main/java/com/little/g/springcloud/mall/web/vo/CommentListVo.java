package com.little.g.springcloud.mall.web.vo;

import com.little.g.springcloud.user.dto.UserDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("评论列表Vo")
public class CommentListVo {

    @ApiModelProperty("添加时间")
    private LocalDateTime addTime;
    @ApiModelProperty("评论内容")
    private String content;
    @ApiModelProperty("管理员回复内容")
    private String adminContent;
    @ApiModelProperty("图片列表")
    private String[] picList;
    @ApiModelProperty("用户信息")
    private UserDTO userInfo;
}
