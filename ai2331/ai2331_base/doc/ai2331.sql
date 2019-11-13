-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: ai2331
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_admin_role`
--

DROP TABLE IF EXISTS `t_admin_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_admin_role` (
  `admin_id` mediumint(6) NOT NULL,
  `role_id` mediumint(6) NOT NULL,
  PRIMARY KEY (`admin_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='后台用户与角色的关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_admin_role`
--

LOCK TABLES `t_admin_role` WRITE;
/*!40000 ALTER TABLE `t_admin_role` DISABLE KEYS */;
INSERT INTO `t_admin_role` VALUES (1,1);
/*!40000 ALTER TABLE `t_admin_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_admin_user`
--

DROP TABLE IF EXISTS `t_admin_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_admin_user` (
  `id` mediumint(6) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL COMMENT '登录名',
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_admin_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=20051 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='后台管理用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_admin_user`
--

LOCK TABLES `t_admin_user` WRITE;
/*!40000 ALTER TABLE `t_admin_user` DISABLE KEYS */;
INSERT INTO `t_admin_user` VALUES (1,'superuser','超级用户','a6fc287e4efb8ef53d6ae12c0c0d1953','IT','abc@example.com','13800000000','62000000','qq:00000000','2014-12-07 00:00:43','2018-04-17 16:13:03','0.0.0.0',1,1),(20022,'yy','jy','5e1dedb5e030088c491f4193950edbbc','闲人','56882658@qq.com','13818349492','123456','1','2019-11-11 17:30:02',NULL,NULL,1,0);
/*!40000 ALTER TABLE `t_admin_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_resource`
--

DROP TABLE IF EXISTS `t_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_resource` (
  `id` mediumint(6) NOT NULL AUTO_INCREMENT,
  `pid` mediumint(6) NOT NULL DEFAULT '0' COMMENT '父ID',
  `priv_code` varchar(80) DEFAULT NULL COMMENT '权限代码',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标css类名',
  `name` varchar(80) NOT NULL COMMENT '功能名称',
  `resource_type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '分类：0 菜单；1 功能',
  `sort_order` mediumint(8) DEFAULT '0' COMMENT '排序号',
  `url` varchar(200) DEFAULT NULL COMMENT '访问地址',
  `tab_name` varchar(80) DEFAULT NULL COMMENT '存在url的菜单，显示在Tab上的名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否生效：0 否；1 是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100032 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统功能入口';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_resource`
--

LOCK TABLES `t_resource` WRITE;
/*!40000 ALTER TABLE `t_resource` DISABLE KEYS */;
INSERT INTO `t_resource` VALUES (100,0,'admin','','系统管理',0,9999,'',NULL,'2014-12-07 11:13:23','',1),(1100,100,'role_list','fa fa-users','角色管理',0,1,'/basic/roleList',NULL,'2014-12-07 11:11:08','',1),(1101,1100,'role_add','fa fa-wrench','添加角色',1,1,'/basic/roleAdd',NULL,'2014-12-07 11:15:30','',1),(1102,1100,'role_del','fa fa-wrench','删除角色',1,2,'/basic/roleDel',NULL,'2014-12-07 11:15:30',NULL,1),(1103,1100,'role_edit','fa fa-wrench','编辑角色',1,3,'/basic/roleEdit',NULL,'2014-12-07 11:17:31','',1),(1104,1100,'role_grant','fa fa-wrench','角色授权',1,4,'/basic/roleGrant',NULL,'2014-12-07 11:15:30',NULL,1),(1105,1100,'role_list','fa fa-wrench','角色表格',1,5,'/basic/roleGrid',NULL,'2014-12-07 11:15:30',NULL,1),(1200,100,'res_list','fa fa-archive','资源管理',0,2,'/basic/resourceList',NULL,'2014-12-07 11:15:30','',1),(1201,1200,'res_add','fa fa-wrench','添加资源',1,1,'/basic/resourceAdd',NULL,'2014-12-07 11:15:30',NULL,1),(1202,1200,'res_del','fa fa-wrench','删除资源',1,2,'/basic/resourceDel',NULL,'2014-12-07 11:15:30',NULL,1),(1203,1200,'res_edit','fa fa-wrench','编辑资源',1,3,'/basic/resourceEdit',NULL,'2014-12-07 11:15:30',NULL,1),(1204,1200,NULL,'fa fa-wrench','功能菜单',1,4,'/basic/resourceMenu',NULL,'2014-12-07 11:15:30',NULL,1),(1205,1200,'res_list','fa fa-wrench','资源表格',1,5,'/basic/resourceGrid',NULL,'2014-12-07 11:15:30',NULL,1),(1300,100,'user_list','fa fa-user-circle-o','用户管理',0,3,'/basic/userList',NULL,'2014-12-07 11:15:30','',1),(1301,1300,'user_add','fa fa-wrench','添加用户',1,1,'/basic/userAdd',NULL,'2014-12-07 11:15:30',NULL,1),(1302,1300,'user_del','fa fa-wrench','删除用户',1,2,'/basic/userDel',NULL,'2014-12-07 11:15:30',NULL,1),(1303,1300,'user_edit','fa fa-wrench','编辑用户',1,3,'/basic/userEdit',NULL,'2014-12-07 11:15:30',NULL,1),(1304,1300,'user_pwd','fa fa-wrench','修改密码',1,4,'/basic/userPwd',NULL,'2014-12-07 11:15:30',NULL,1),(1305,1300,'user_grant','fa fa-wrench','用户授权',1,5,'/basic/userGrant',NULL,'2014-12-07 11:15:30',NULL,1);
/*!40000 ALTER TABLE `t_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_role` (
  `id` mediumint(6) NOT NULL AUTO_INCREMENT,
  `pid` mediumint(6) NOT NULL DEFAULT '0' COMMENT '父角色Id',
  `role_code` varchar(80) NOT NULL COMMENT '角色代码',
  `name` varchar(80) NOT NULL COMMENT '角色名',
  `sort_order` mediumint(6) DEFAULT '0' COMMENT '排序号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否生效：0 否；1 是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20003 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` VALUES (1,0,'system_mgmt','系统管理',1,'2014-12-07 11:15:30',NULL,1);
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_resource`
--

DROP TABLE IF EXISTS `t_role_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_role_resource` (
  `role_id` mediumint(6) NOT NULL,
  `resource_id` mediumint(6) NOT NULL,
  PRIMARY KEY (`role_id`,`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色与功能的关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_resource`
--

LOCK TABLES `t_role_resource` WRITE;
/*!40000 ALTER TABLE `t_role_resource` DISABLE KEYS */;
INSERT INTO `t_role_resource` VALUES (1,100),(1,1100),(1,1101),(1,1102),(1,1103),(1,1104),(1,1105),(1,1200),(1,1201),(1,1202),(1,1203),(1,1204),(1,1205),(1,1300),(1,1301),(1,1302),(1,1303),(1,1304),(1,1305);
/*!40000 ALTER TABLE `t_role_resource` ENABLE KEYS */;
UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-13 11:30:54
