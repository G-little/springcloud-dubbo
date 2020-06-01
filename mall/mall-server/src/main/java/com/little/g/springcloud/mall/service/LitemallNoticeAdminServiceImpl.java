package com.little.g.springcloud.mall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.common.utils.DTOUtil;
import com.little.g.springcloud.mall.api.LitemallNoticeAdminService;
import com.little.g.springcloud.mall.dto.LitemallNoticeAdminDTO;
import com.little.g.springcloud.mall.mapper.LitemallNoticeAdminMapper;
import com.little.g.springcloud.mall.model.LitemallNoticeAdmin;
import com.little.g.springcloud.mall.model.LitemallNoticeAdminExample;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service(protocol = "dubbo")
public class LitemallNoticeAdminServiceImpl implements LitemallNoticeAdminService {

	@Resource
	private LitemallNoticeAdminMapper noticeAdminMapper;

	@Override
	public PageInfo<LitemallNoticeAdminDTO> querySelective(String title, String type,
			Integer adminId, Integer page, Integer limit, String sort, String order) {
		LitemallNoticeAdminExample example = new LitemallNoticeAdminExample();
		LitemallNoticeAdminExample.Criteria criteria = example.createCriteria();

		if (!StringUtils.isEmpty(title)) {
			criteria.andNoticeTitleLike("%" + title + "%");
		}

		if (type.equals("read")) {
			criteria.andReadTimeIsNotNull();
		}
		else if (type.equals("unread")) {
			criteria.andReadTimeIsNull();
		}
		criteria.andAdminIdEqualTo(adminId);
		criteria.andDeletedEqualTo(false);

		if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
			example.setOrderByClause(sort + " " + order);
		}

		PageHelper.startPage(page, limit);
		return DTOUtil.convert2Page(noticeAdminMapper.selectByExample(example),
				LitemallNoticeAdminDTO.class);
	}

	@Override
	public LitemallNoticeAdminDTO find(Integer noticeId, Integer adminId) {
		LitemallNoticeAdminExample example = new LitemallNoticeAdminExample();
		example.or().andNoticeIdEqualTo(noticeId).andAdminIdEqualTo(adminId)
				.andDeletedEqualTo(false);
		return DTOUtil.convert2T(noticeAdminMapper.selectOneByExample(example),
				LitemallNoticeAdminDTO.class);
	}

	@Override
	public void add(LitemallNoticeAdminDTO noticeAdmin) {
		noticeAdmin.setAddTime(LocalDateTime.now());
		noticeAdmin.setUpdateTime(LocalDateTime.now());
		noticeAdminMapper.insertSelective(
				DTOUtil.convert2T(noticeAdmin, LitemallNoticeAdmin.class));
	}

	@Override
	public void update(LitemallNoticeAdminDTO noticeAdmin) {
		noticeAdmin.setUpdateTime(LocalDateTime.now());
		noticeAdminMapper.updateByPrimaryKeySelective(
				DTOUtil.convert2T(noticeAdmin, LitemallNoticeAdmin.class));
	}

	@Override
	public void markReadByIds(List<Integer> ids, Integer adminId) {
		LitemallNoticeAdminExample example = new LitemallNoticeAdminExample();
		example.or().andIdIn(ids).andAdminIdEqualTo(adminId).andDeletedEqualTo(false);
		LitemallNoticeAdmin noticeAdmin = new LitemallNoticeAdmin();
		LocalDateTime now = LocalDateTime.now();
		noticeAdmin.setReadTime(now);
		noticeAdmin.setUpdateTime(now);
		noticeAdminMapper.updateByExampleSelective(noticeAdmin, example);
	}

	@Override
	public void deleteById(Integer id, Integer adminId) {
		LitemallNoticeAdminExample example = new LitemallNoticeAdminExample();
		example.or().andIdEqualTo(id).andAdminIdEqualTo(adminId).andDeletedEqualTo(false);
		LitemallNoticeAdmin noticeAdmin = new LitemallNoticeAdmin();
		noticeAdmin.setUpdateTime(LocalDateTime.now());
		noticeAdmin.setDeleted(true);
		noticeAdminMapper.updateByExampleSelective(noticeAdmin, example);
	}

	@Override
	public void deleteByIds(List<Integer> ids, Integer adminId) {
		LitemallNoticeAdminExample example = new LitemallNoticeAdminExample();
		example.or().andIdIn(ids).andAdminIdEqualTo(adminId).andDeletedEqualTo(false);
		LitemallNoticeAdmin noticeAdmin = new LitemallNoticeAdmin();
		noticeAdmin.setUpdateTime(LocalDateTime.now());
		noticeAdmin.setDeleted(true);
		noticeAdminMapper.updateByExampleSelective(noticeAdmin, example);
	}

	@Override
	public int countUnread(Integer adminId) {
		LitemallNoticeAdminExample example = new LitemallNoticeAdminExample();
		example.or().andAdminIdEqualTo(adminId).andReadTimeIsNull()
				.andDeletedEqualTo(false);
		return (int) noticeAdminMapper.countByExample(example);
	}

	@Override
	public List<LitemallNoticeAdminDTO> queryByNoticeId(Integer noticeId) {
		LitemallNoticeAdminExample example = new LitemallNoticeAdminExample();
		example.or().andNoticeIdEqualTo(noticeId).andDeletedEqualTo(false);
		return DTOUtil.convert2List(noticeAdminMapper.selectByExample(example),
				LitemallNoticeAdminDTO.class);
	}

	@Override
	public void deleteByNoticeId(Integer id) {
		LitemallNoticeAdminExample example = new LitemallNoticeAdminExample();
		example.or().andNoticeIdEqualTo(id).andDeletedEqualTo(false);
		LitemallNoticeAdmin noticeAdmin = new LitemallNoticeAdmin();
		noticeAdmin.setUpdateTime(LocalDateTime.now());
		noticeAdmin.setDeleted(true);
		noticeAdminMapper.updateByExampleSelective(noticeAdmin, example);
	}

	@Override
	public void deleteByNoticeIds(List<Integer> ids) {
		LitemallNoticeAdminExample example = new LitemallNoticeAdminExample();
		example.or().andNoticeIdIn(ids).andDeletedEqualTo(false);
		LitemallNoticeAdmin noticeAdmin = new LitemallNoticeAdmin();
		noticeAdmin.setUpdateTime(LocalDateTime.now());
		noticeAdmin.setDeleted(true);
		noticeAdminMapper.updateByExampleSelective(noticeAdmin, example);
	}

	@Override
	public int countReadByNoticeId(Integer noticeId) {
		LitemallNoticeAdminExample example = new LitemallNoticeAdminExample();
		example.or().andNoticeIdEqualTo(noticeId).andReadTimeIsNotNull()
				.andDeletedEqualTo(false);
		return (int) noticeAdminMapper.countByExample(example);
	}

	@Override
	public void updateByNoticeId(LitemallNoticeAdminDTO noticeAdmin, Integer noticeId) {
		LitemallNoticeAdminExample example = new LitemallNoticeAdminExample();
		example.or().andNoticeIdEqualTo(noticeId).andDeletedEqualTo(false);
		noticeAdmin.setUpdateTime(LocalDateTime.now());
		noticeAdminMapper.updateByExampleSelective(
				DTOUtil.convert2T(noticeAdmin, LitemallNoticeAdmin.class), example);
	}

}
