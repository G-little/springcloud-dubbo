package com.little.g.springcloud.pay.service.impl;

import com.little.g.springcloud.common.exception.ServiceDataException;
import com.little.g.springcloud.pay.api.UserAccountService;
import com.little.g.springcloud.pay.dto.UserAccountDTO;
import com.little.g.springcloud.pay.enums.AccountStatus;
import com.little.g.springcloud.pay.mapper.UserAccountMapper;
import com.little.g.springcloud.pay.model.UserAccount;
import com.little.g.springcloud.pay.model.UserAccountExample;
import com.little.g.springcloud.user.UserErrorCodes;
import com.little.g.springcloud.user.api.UserService;
import com.little.g.springcloud.user.dto.UserDTO;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by llg on 2019/4/14.
 */
@Service(protocol = "dubbo")
public class UserAccountServiceImpl implements UserAccountService {

	private static final Logger logger = LoggerFactory
			.getLogger(UserAccountServiceImpl.class);

	@Resource
	private UserAccountMapper userAccountMapper;

	@Reference
	private UserService userService;

	@Override
    public boolean createUserAccount(Integer uid) {
		UserAccount userAccount = new UserAccount();
		userAccount.setUid(uid);
		userAccount.setUpdateTime(System.currentTimeMillis());
		userAccount.setCreateTime(System.currentTimeMillis());
		userAccount.setStatus(AccountStatus.INIT.getValue());
		return userAccountMapper.insertSelective(userAccount) > 0;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,
			isolation = Isolation.READ_COMMITTED)
    public boolean updateAccountStatus(Integer uid, AccountStatus accountStatus,
                                       String reason) {
		UserAccountExample example = new UserAccountExample();
		example.or().andUidEqualTo(uid).andStatusNotEqualTo(accountStatus.getValue());
		UserAccount userAccount = new UserAccount();
		userAccount.setStatus(accountStatus.getValue());
		userAccount.setUpdateTime(System.currentTimeMillis());
		int ret = userAccountMapper.updateByExampleSelective(userAccount, example);
		logger.info("updateAccountStatus. uid={} status={} reason={}", uid, accountStatus,
				reason);
		return ret > 0;
	}

	@Transactional
	@Override
    public UserAccountDTO queryUserAccount(Integer uid) {

		UserAccount useraccount = userAccountMapper.selectByPrimaryKey(uid);
		if (useraccount == null) {
			useraccount = initAccount(uid);
		}

		UserAccountDTO account = new UserAccountDTO();
		BeanUtils.copyProperties(useraccount, account);
		return account;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW,
			isolation = Isolation.READ_COMMITTED)
    UserAccount initAccount(Integer uid) {
		UserDTO user = userService.getUserById(uid);
		if (user == null) {
			throw new ServiceDataException(UserErrorCodes.USER_NOT_EXIST,
					"msg.user.not.exist");
		}

		UserAccount useraccount = new UserAccount();
		useraccount.setUid(uid);
        useraccount.setMoney(0L);
        useraccount.setFrozonMoney(0L);
		useraccount.setUpdateTime(System.currentTimeMillis());
		useraccount.setCreateTime(System.currentTimeMillis());
		useraccount.setStatus(AccountStatus.INIT.getValue());
		userAccountMapper.insertSelective(useraccount);
		return useraccount;
	}

}
