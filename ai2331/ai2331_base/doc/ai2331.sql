/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.21 : Database - ai2331
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ai2331` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `ai2331`;

/*Table structure for table `b_corp_role` */

DROP TABLE IF EXISTS `b_corp_role`;

CREATE TABLE `b_corp_role` (
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色代码',
  `name` varchar(80) NOT NULL COMMENT '角色名',
  `corp_code` varchar(32) NOT NULL COMMENT 'sys_corp.code',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_staff` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'b_corp_staff.username',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否生效：0 否；1 是',
  PRIMARY KEY (`code`,`corp_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='公司角色表';

/*Data for the table `b_corp_role` */

insert  into `b_corp_role`(`code`,`name`,`corp_code`,`create_time`,`create_staff`,`remark`,`enabled`) values ('SO','平台管理员','sys_operation','2020-08-27 17:26:17','1',NULL,1);

/*Table structure for table `b_corp_role_resource` */

DROP TABLE IF EXISTS `b_corp_role_resource`;

CREATE TABLE `b_corp_role_resource` (
  `role_code` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'b_corp_role.code',
  `corp_code` varchar(32) NOT NULL COMMENT 'sys_corp.code',
  `resource_id` mediumint NOT NULL COMMENT 'sys_resource.id',
  PRIMARY KEY (`role_code`,`corp_code`,`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='公司角色与功能的关联表';

/*Data for the table `b_corp_role_resource` */

insert  into `b_corp_role_resource`(`role_code`,`corp_code`,`resource_id`) values ('SO','sys_operation',100),('SO','sys_operation',1100),('SO','sys_operation',1101),('SO','sys_operation',1102),('SO','sys_operation',1103),('SO','sys_operation',1104),('SO','sys_operation',1105),('SO','sys_operation',1200),('SO','sys_operation',1201),('SO','sys_operation',1202),('SO','sys_operation',1203),('SO','sys_operation',1204),('SO','sys_operation',1205),('SO','sys_operation',1300),('SO','sys_operation',1301),('SO','sys_operation',1302),('SO','sys_operation',1303),('SO','sys_operation',1304),('SO','sys_operation',1305);

/*Table structure for table `b_corp_staff` */

DROP TABLE IF EXISTS `b_corp_staff`;

CREATE TABLE `b_corp_staff` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录名',
  `fullname` varchar(50) NOT NULL COMMENT '全名',
  `password` char(32) NOT NULL COMMENT '32位加salt的MD5',
  `title` varchar(20) DEFAULT NULL COMMENT '职位抬头',
  `email` varchar(100) DEFAULT NULL COMMENT '邮件',
  `mobilephone` varchar(20) DEFAULT NULL COMMENT '手机',
  `telephone` varchar(20) DEFAULT NULL COMMENT '固定电话',
  `im` varchar(100) DEFAULT NULL COMMENT 'IM联系方式',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `last_ip` varchar(30) DEFAULT NULL COMMENT '上次登录IP',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否有效：0 否，1 是',
  `is_super` tinyint(1) DEFAULT '0' COMMENT '是否超级管理员',
  `corp_code` varchar(32) NOT NULL COMMENT 'sys_corp.code公司编码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_admin_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='公司员工';

/*Data for the table `b_corp_staff` */

insert  into `b_corp_staff`(`id`,`username`,`fullname`,`password`,`title`,`email`,`mobilephone`,`telephone`,`im`,`create_time`,`last_time`,`last_ip`,`enabled`,`is_super`,`corp_code`) values (1,'superuser','超级用户','a6fc287e4efb8ef53d6ae12c0c0d1953','IT','abc@example.com','13800000000','62000000','qq:00000000','2014-12-07 00:00:43','2018-04-17 16:13:03','0.0.0.0',1,1,'sys_operation'),(2,'yy','jy','5e1dedb5e030088c491f4193950edbbc','闲人','56882658@qq.com','13818349492','123456','1','2019-11-11 17:30:02',NULL,NULL,1,0,'sys_operation');

/*Table structure for table `b_corp_staff_role` */

DROP TABLE IF EXISTS `b_corp_staff_role`;

CREATE TABLE `b_corp_staff_role` (
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'b_corp_staff.username',
  `corp_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'sys_corp.code',
  `role_code` varchar(32) NOT NULL COMMENT 'b_corp_role.code',
  PRIMARY KEY (`username`,`corp_code`,`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='公司角色与功能的关联表';

/*Data for the table `b_corp_staff_role` */

insert  into `b_corp_staff_role`(`username`,`corp_code`,`role_code`) values ('superuser','sys_operation','SO');

/*Table structure for table `sys_choice_config` */

DROP TABLE IF EXISTS `sys_choice_config`;

CREATE TABLE `sys_choice_config` (
  `choice_code` varchar(50) NOT NULL COMMENT '选项代码',
  `choice_name` varchar(50) NOT NULL COMMENT '选项名称',
  `id_label` varchar(50) NOT NULL COMMENT 'ID列的小标题',
  `name_label` varchar(50) DEFAULT NULL COMMENT 'Name列的小标题',
  `extra_fields` varchar(1000) DEFAULT NULL COMMENT '扩展字段，json格式',
  `query_selector` varchar(1000) NOT NULL COMMENT '搜索的sql',
  `get_selector` varchar(1000) NOT NULL COMMENT '取单个的sql',
  PRIMARY KEY (`choice_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='动态选项配置';

/*Data for the table `sys_choice_config` */

insert  into `sys_choice_config`(`choice_code`,`choice_name`,`id_label`,`name_label`,`extra_fields`,`query_selector`,`get_selector`) values ('corp_all','全部公司集合','编码','名称','[{\"fieldName\":\"busiScope\",\"label\":\"义务范围\",\"image\":false}]','SELECT CODE,NAME,bank_card_no,busi_scope FROM sys_corp WHERE NAME LIKE \'%${q}%\' ORDER BY CODE ASC','SELECT CODE,NAME,bank_card_no,busi_scope FROM sys_corp WHERE CODE IN (${in}) ORDER BY CODE ASC');

/*Table structure for table `sys_corp` */

DROP TABLE IF EXISTS `sys_corp`;

CREATE TABLE `sys_corp` (
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公司编码（唯一）',
  `pcode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '总公司编码，顶级为0',
  `name` varchar(255) NOT NULL COMMENT '公司名称',
  `fullname` varchar(512) DEFAULT NULL COMMENT '公司全称',
  `taxno` varchar(32) DEFAULT NULL COMMENT '纳税号',
  `bank` varchar(32) DEFAULT NULL COMMENT '银行（字典）',
  `bank_card_no` varchar(32) DEFAULT NULL COMMENT '银行卡号',
  `busi_scope` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '业务范围',
  `busi_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '经营类型（字典）',
  `phone` varchar(32) DEFAULT NULL COMMENT '公司电话',
  `address` varchar(512) DEFAULT NULL COMMENT '公司地址',
  `responsible` varchar(32) NOT NULL COMMENT '责任人',
  `responsible_phone` varchar(32) NOT NULL COMMENT '责任人电话',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '0失效，1可用',
  `check_status` char(1) NOT NULL COMMENT '审核状态1申请，2通过，9不通过',
  `checker` int NOT NULL COMMENT '审核人',
  `check_reason` varchar(512) DEFAULT NULL COMMENT '审核原因',
  `suit_code` varchar(128) NOT NULL COMMENT '公司套装code，多个以英文逗号分隔',
  `suit_code_extra` varchar(128) DEFAULT NULL COMMENT '增值套装，多个以英文逗号分隔',
  `remark` varchar(1024) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `creator` int NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `updator` int NOT NULL COMMENT '更新人',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sys_corp` */

insert  into `sys_corp`(`code`,`pcode`,`name`,`fullname`,`taxno`,`bank`,`bank_card_no`,`busi_scope`,`busi_type`,`phone`,`address`,`responsible`,`responsible_phone`,`status`,`check_status`,`checker`,`check_reason`,`suit_code`,`suit_code_extra`,`remark`,`create_time`,`creator`,`update_time`,`updator`) values ('sys_operation','0','运营平台','运营平台','00000000','00000000','00000000','管理运营整个平台',NULL,'13800000000','中国','yy','13800000000','1','1',1,'ok','SYS',NULL,NULL,'2020-08-12 14:47:09',1,'2020-08-12 14:47:15',1);

/*Table structure for table `sys_corp_suit` */

DROP TABLE IF EXISTS `sys_corp_suit`;

CREATE TABLE `sys_corp_suit` (
  `code` varchar(32) NOT NULL COMMENT '套装code',
  `name` varchar(512) NOT NULL COMMENT '套装名称',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '0失效，1可用',
  PRIMARY KEY (`code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sys_corp_suit` */

insert  into `sys_corp_suit`(`code`,`name`,`status`) values ('SYS','平台管理员','1');

/*Table structure for table `sys_dict` */

DROP TABLE IF EXISTS `sys_dict`;

CREATE TABLE `sys_dict` (
  `code` varchar(50) NOT NULL COMMENT '代码，如gender',
  `name` varchar(100) NOT NULL COMMENT '名，如“性别”',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '可用：0 否，1 是',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典';

/*Data for the table `sys_dict` */

insert  into `sys_dict`(`code`,`name`,`note`,`status`) values ('abc','abc',NULL,1);

/*Table structure for table `sys_dict_item` */

DROP TABLE IF EXISTS `sys_dict_item`;

CREATE TABLE `sys_dict_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `group_code` varchar(50) NOT NULL COMMENT 'm_dict.code，如gender',
  `value` varchar(50) NOT NULL COMMENT '值，如1',
  `name` varchar(200) NOT NULL COMMENT '名，如男',
  `ext1` varchar(100) DEFAULT NULL COMMENT '扩展1',
  `ext2` varchar(100) DEFAULT NULL COMMENT '扩展2',
  `ext3` varchar(100) DEFAULT NULL COMMENT '扩展3',
  `sort_order` int DEFAULT NULL COMMENT '排序号，小靠前',
  PRIMARY KEY (`id`),
  KEY `fk_reference_21` (`group_code`)
) ENGINE=InnoDB AUTO_INCREMENT=20024 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典条目';

/*Data for the table `sys_dict_item` */

insert  into `sys_dict_item`(`id`,`group_code`,`value`,`name`,`ext1`,`ext2`,`ext3`,`sort_order`) values (20022,'abc','1','A',NULL,NULL,'AA',1),(20023,'abc','2','B',NULL,NULL,NULL,2);

/*Table structure for table `sys_resource` */

DROP TABLE IF EXISTS `sys_resource`;

CREATE TABLE `sys_resource` (
  `id` mediumint NOT NULL AUTO_INCREMENT,
  `pid` mediumint NOT NULL DEFAULT '0' COMMENT '父ID',
  `priv_code` varchar(80) DEFAULT NULL COMMENT '权限代码',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标css类名',
  `name` varchar(80) NOT NULL COMMENT '功能名称',
  `resource_type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '分类：0 菜单；1 功能',
  `sort_order` mediumint DEFAULT '0' COMMENT '排序号',
  `url` varchar(200) DEFAULT NULL COMMENT '访问地址',
  `tab_name` varchar(80) DEFAULT NULL COMMENT '存在url的菜单，显示在Tab上的名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否生效：0 否；1 是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100032 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统功能入口';

/*Data for the table `sys_resource` */

insert  into `sys_resource`(`id`,`pid`,`priv_code`,`icon`,`name`,`resource_type`,`sort_order`,`url`,`tab_name`,`create_time`,`remark`,`enabled`) values (100,0,'admin','','系统管理',0,9999,'',NULL,'2014-12-07 11:13:23','',1),(1100,100,'role_list','fa fa-users','角色管理',0,1,'/basic/roleList',NULL,'2014-12-07 11:11:08','',1),(1101,1100,'role_add','fa fa-wrench','添加角色',1,1,'/basic/roleAdd',NULL,'2014-12-07 11:15:30','',1),(1102,1100,'role_del','fa fa-wrench','删除角色',1,2,'/basic/roleDel',NULL,'2014-12-07 11:15:30',NULL,1),(1103,1100,'role_edit','fa fa-wrench','编辑角色',1,3,'/basic/roleEdit',NULL,'2014-12-07 11:17:31','',1),(1104,1100,'role_grant','fa fa-wrench','角色授权',1,4,'/basic/roleGrant',NULL,'2014-12-07 11:15:30',NULL,1),(1105,1100,'role_list','fa fa-wrench','角色表格',1,5,'/basic/roleGrid',NULL,'2014-12-07 11:15:30',NULL,1),(1200,100,'res_list','fa fa-archive','资源管理',0,2,'/basic/resourceList',NULL,'2014-12-07 11:15:30','',1),(1201,1200,'res_add','fa fa-wrench','添加资源',1,1,'/basic/resourceAdd',NULL,'2014-12-07 11:15:30',NULL,1),(1202,1200,'res_del','fa fa-wrench','删除资源',1,2,'/basic/resourceDel',NULL,'2014-12-07 11:15:30',NULL,1),(1203,1200,'res_edit','fa fa-wrench','编辑资源',1,3,'/basic/resourceEdit',NULL,'2014-12-07 11:15:30',NULL,1),(1204,1200,NULL,'fa fa-wrench','功能菜单',1,4,'/basic/resourceMenu',NULL,'2014-12-07 11:15:30',NULL,1),(1205,1200,'res_list','fa fa-wrench','资源表格',1,5,'/basic/resourceGrid',NULL,'2014-12-07 11:15:30',NULL,1),(1300,100,'user_list','fa fa-user-circle-o','用户管理',0,3,'/basic/userList',NULL,'2014-12-07 11:15:30','',1),(1301,1300,'user_add','fa fa-wrench','添加用户',1,1,'/basic/userAdd',NULL,'2014-12-07 11:15:30',NULL,1),(1302,1300,'user_del','fa fa-wrench','删除用户',1,2,'/basic/userDel',NULL,'2014-12-07 11:15:30',NULL,1),(1303,1300,'user_edit','fa fa-wrench','编辑用户',1,3,'/basic/userEdit',NULL,'2014-12-07 11:15:30',NULL,1),(1304,1300,'user_pwd','fa fa-wrench','修改密码',1,4,'/basic/userPwd',NULL,'2014-12-07 11:15:30',NULL,1),(1305,1300,'user_grant','fa fa-wrench','用户授权',1,5,'/basic/userGrant',NULL,'2014-12-07 11:15:30',NULL,1);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` mediumint NOT NULL AUTO_INCREMENT,
  `pid` mediumint NOT NULL DEFAULT '0' COMMENT '父角色Id',
  `role_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色代码',
  `name` varchar(80) NOT NULL COMMENT '角色名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否生效：0 否；1 是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20003 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`pid`,`role_code`,`name`,`create_time`,`remark`,`enabled`) values (1,0,'system_mgmt','系统管理','2014-12-07 11:15:30',NULL,1);

/*Table structure for table `sys_role_resource` */

DROP TABLE IF EXISTS `sys_role_resource`;

CREATE TABLE `sys_role_resource` (
  `role_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `resource_id` mediumint NOT NULL,
  PRIMARY KEY (`role_code`,`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色与功能的关联表';

/*Data for the table `sys_role_resource` */

insert  into `sys_role_resource`(`role_code`,`resource_id`) values ('system_mgmt',100),('system_mgmt',1100),('system_mgmt',1101),('system_mgmt',1102),('system_mgmt',1103),('system_mgmt',1104),('system_mgmt',1105),('system_mgmt',1200),('system_mgmt',1201),('system_mgmt',1202),('system_mgmt',1203),('system_mgmt',1204),('system_mgmt',1205),('system_mgmt',1300),('system_mgmt',1301),('system_mgmt',1302),('system_mgmt',1303),('system_mgmt',1304),('system_mgmt',1305);

/*Table structure for table `sys_suit_role` */

DROP TABLE IF EXISTS `sys_suit_role`;

CREATE TABLE `sys_suit_role` (
  `suit_code` varchar(32) NOT NULL COMMENT 'sys_corp_suit.code',
  `role_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'sys_role.code',
  PRIMARY KEY (`suit_code`,`role_code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sys_suit_role` */

insert  into `sys_suit_role`(`suit_code`,`role_code`) values ('SYS','system_mgmt');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
