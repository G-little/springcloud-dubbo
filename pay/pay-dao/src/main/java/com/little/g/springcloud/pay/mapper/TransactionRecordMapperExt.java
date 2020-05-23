package com.little.g.springcloud.pay.mapper;

import com.little.g.springcloud.pay.model.TransactionRecordExample;

public interface TransactionRecordMapperExt {

	Long sumMoneyByExample(TransactionRecordExample example);

}