# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 192.168.0.195 (MySQL 5.5.60-MariaDB)
# Database: springcloud-pay
# Generation Time: 2020-05-24 09:56:33 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

create database if not exists `springcloud-pay`;
use `springcloud-pay`;

# Dump of table balance_checkpoint
# ------------------------------------------------------------

DROP TABLE IF EXISTS `balance_checkpoint`;

CREATE TABLE `balance_checkpoint` (
  `id` bigint(15) unsigned NOT NULL AUTO_INCREMENT,
  `uid` bigint(15) DEFAULT NULL COMMENT '用户ID',
  `account_id` varchar(35) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '账户',
  `balance` bigint(15) DEFAULT NULL COMMENT '余额',
  `frozen` bigint(15) DEFAULT NULL COMMENT '冻结',
  `max_id` bigint(15) DEFAULT NULL COMMENT '最大ID',
  `update_time` bigint(15) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# Dump of table charge_record
# ------------------------------------------------------------

DROP TABLE IF EXISTS `charge_record`;

CREATE TABLE `charge_record` (
  `tran_num` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '流水',
  `uid` bigint(15) DEFAULT NULL COMMENT '用户ID',
  `money` bigint(15) DEFAULT NULL COMMENT '交易金额',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `description` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `pay_type` tinyint(4) DEFAULT NULL COMMENT '支付类型',
  `preorder_no` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '预支付订单号',
  `mch_id` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商户ID',
  `out_tran_num` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '三方流水',
  `create_time` bigint(15) DEFAULT NULL,
  `update_time` bigint(15) DEFAULT NULL,
  PRIMARY KEY (`tran_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='充值记录';


# Dump of table frozen_record
# ------------------------------------------------------------

DROP TABLE IF EXISTS `frozen_record`;

CREATE TABLE `frozen_record` (
  `trade_num` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '交易流水',
  `account_id` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '账户',
  `amount` bigint(15) DEFAULT NULL COMMENT '交易金额',
  `balance` bigint(15) DEFAULT NULL COMMENT '余额',
  `create_time` bigint(15) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(15) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`trade_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# Dump of table pay_channel
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pay_channel`;

CREATE TABLE `pay_channel` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `code` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '代码',
  `config_version` varchar(35) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '版本',
  `max_pay_fee` bigint(15) DEFAULT NULL COMMENT '最大支付',
  `min_pay_fee` bigint(15) DEFAULT NULL COMMENT '最小支付',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `memo` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'memo',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# Dump of table preorder
# ------------------------------------------------------------

DROP TABLE IF EXISTS `preorder`;

CREATE TABLE `preorder` (
  `mch_id` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '商户ID',
  `pre_order_no` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '预支付订单号',
  `attach` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '附加信息',
  `out_trade_no` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '三方流水',
  `total_fee` bigint(15) DEFAULT NULL COMMENT '总金额',
  `account_id` bigint(15) DEFAULT NULL COMMENT '账户ID',
  `opposit_account` bigint(15) DEFAULT NULL COMMENT '对方账户',
  `trade_type` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '交易类型',
  `btype` tinyint(5) DEFAULT NULL COMMENT '业务类型',
  `status` tinyint(5) DEFAULT NULL COMMENT '状态',
  `notify_url` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '通知地址',
  `subject` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '交易说明',
  `pay_type` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '支付类型',
  `create_time` bigint(15) DEFAULT NULL,
  `update_time` bigint(15) DEFAULT NULL,
  PRIMARY KEY (`mch_id`,`pre_order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


# Dump of table transaction_record
# ------------------------------------------------------------

DROP TABLE IF EXISTS `transaction_record`;

CREATE TABLE `transaction_record` (
  `id` bigint(15) unsigned NOT NULL AUTO_INCREMENT,
  `tran_num` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '交易流水',
  `account_id` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '来源账户',
  `opposite` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '对方账户',
  `trade_num` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '流水',
  `money` bigint(15) DEFAULT NULL COMMENT '金额',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型',
  `btype` tinyint(4) DEFAULT NULL COMMENT '业务类型',
  `comment` varchar(35) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `create_time` bigint(15) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# Dump of table user_account
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_account`;

CREATE TABLE `user_account` (
  `uid` bigint(15) unsigned NOT NULL COMMENT '用户账户',
  `money` bigint(15) unsigned NOT NULL COMMENT '余额',
  `frozon_money` bigint(15) unsigned NOT NULL COMMENT '冻结金额',
  `status` tinyint(4) unsigned NOT NULL COMMENT '状态',
  `update_time` bigint(15) DEFAULT NULL COMMENT '更新时间',
  `create_time` bigint(15) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;

INSERT INTO `user_account` (`uid`, `money`, `frozon_money`, `status`, `update_time`, `create_time`)
VALUES
	(11443,0,0,0,1590246934623,1590246934623);

/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
