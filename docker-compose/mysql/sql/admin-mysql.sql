# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 192.168.0.195 (MySQL 5.5.60-MariaDB)
# Database: springcloud-admin
# Generation Time: 2020-05-25 03:21:14 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table admin_role
# ------------------------------------------------------------

create database if not exists `springcloud-admin`;
use `springcloud-admin`;

DROP TABLE IF EXISTS `admin_role`;

CREATE TABLE `admin_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色名',
  `privilege` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '权限',
  `create_time` bigint(15) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# Dump of table admin_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `admin_user`;

CREATE TABLE `admin_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `real_name` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '真实姓名',
  `gender` tinyint(4) DEFAULT NULL COMMENT '-1 女 1 男',
  `card_id` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '身份证号',
  `birthday` bigint(15) DEFAULT NULL COMMENT '生日',
  `wx_num` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '微信号',
  `mobile` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机号',
  `store` int(11) DEFAULT NULL COMMENT '所属门店',
  `contact_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系人姓名',
  `contact_mobile` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系人电话',
  `relationship` tinyint(4) DEFAULT NULL COMMENT '关系',
  `id_card_img1` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '身份证正面',
  `id_card_img2` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '身份证反面',
  `password` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `belong_to` tinyint(5) DEFAULT NULL COMMENT '归属',
  `status` tinyint(4) DEFAULT NULL COMMENT '-1 禁止登陆 1 正常',
  `privilege` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '权限数组',
  `type` tinyint(4) DEFAULT NULL COMMENT '0 普通管理员 1 超级管理员',
  `create_time` bigint(15) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(15) DEFAULT NULL COMMENT '更新时间',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNI_MOBILE` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='管理员';



# Dump of table resources
# ------------------------------------------------------------

DROP TABLE IF EXISTS `resources`;

CREATE TABLE `resources` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标题',
  `path` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '路径',
  `parent_id` int(11) DEFAULT NULL COMMENT '父节点',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型 0 普通 1 通配符',
  `is_menu` tinyint(2) DEFAULT NULL COMMENT '是否是菜单',
  `create_time` bigint(15) DEFAULT NULL COMMENT '创建时间',
  `privilege_pos` int(11) DEFAULT NULL COMMENT '权限位',
  `privilege_val` bigint(64) DEFAULT NULL COMMENT '权限值',
  `need_auth` tinyint(2) DEFAULT NULL COMMENT '是否需要权限',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='资源表';




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
