package com.little.g.springcloud.pay.api;

import com.little.g.springcloud.pay.dto.UserAccountDTO;
import com.little.g.springcloud.pay.enums.AccountStatus;

/**
 * Created by lengligang on 16/1/18. user account service for money change;
 */
public interface UserAccountService {

	/**
	 * create user account
	 * @param uid uid
	 * @return if success
	 */
	boolean createUserAccount(Integer uid);

	/**
	 * update user account status
	 * @param uid uid
	 * @param accountStatus user status enum;
	 * @return
	 */
	boolean updateAccountStatus(Integer uid, AccountStatus accountStatus, String reason);

	/**
	 * query user account
	 * @param uid userId
	 * @return userAccountStatus
	 */
	UserAccountDTO queryUserAccount(Integer uid);

}
