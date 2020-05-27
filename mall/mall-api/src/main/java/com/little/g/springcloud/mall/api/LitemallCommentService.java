package com.little.g.springcloud.mall.api;

import com.little.g.springcloud.mall.dto.LitemallCommentDTO;

import java.util.List;

public interface LitemallCommentService {
    List<LitemallCommentDTO> queryGoodsByGid(Integer id, int offset, int limit);

    List<LitemallCommentDTO> query(Byte type, Integer valueId, Integer showType, Integer offset, Integer limit);

    int count(Byte type, Integer valueId, Integer showType);

    int save(LitemallCommentDTO comment);

    List<LitemallCommentDTO> querySelective(String userId, String valueId, Integer page, Integer size, String sort, String order);

    void deleteById(Integer id);

    LitemallCommentDTO findById(Integer id);

    int updateById(LitemallCommentDTO comment);
}
