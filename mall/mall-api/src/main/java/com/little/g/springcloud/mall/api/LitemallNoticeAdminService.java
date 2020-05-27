package com.little.g.springcloud.mall.api;

import com.little.g.springcloud.mall.dto.LitemallNoticeAdminDTO;

import java.util.List;

public interface LitemallNoticeAdminService {
    List<LitemallNoticeAdminDTO> querySelective(String title, String type, Integer adminId, Integer page, Integer limit, String sort, String order);

    LitemallNoticeAdminDTO find(Integer noticeId, Integer adminId);

    void add(LitemallNoticeAdminDTO noticeAdmin);

    void update(LitemallNoticeAdminDTO noticeAdmin);

    void markReadByIds(List<Integer> ids, Integer adminId);

    void deleteById(Integer id, Integer adminId);

    void deleteByIds(List<Integer> ids, Integer adminId);

    int countUnread(Integer adminId);

    List<LitemallNoticeAdminDTO> queryByNoticeId(Integer noticeId);

    void deleteByNoticeId(Integer id);

    void deleteByNoticeIds(List<Integer> ids);

    int countReadByNoticeId(Integer noticeId);

    void updateByNoticeId(LitemallNoticeAdminDTO noticeAdmin, Integer noticeId);
}
