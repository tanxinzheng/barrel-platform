# ************************************************************
# Sequel Pro SQL dump
# Version 4499
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.19)
# Database: xmo_demo
# Generation Time: 2019-08-03 05:44:49 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table v_account_permission
# ------------------------------------------------------------

DROP VIEW IF EXISTS `v_account_permission`;

CREATE TABLE `v_account_permission` (
   `USERNAME` VARCHAR(30) NULL DEFAULT '',
   `GROUP_NAME` VARCHAR(100) NULL DEFAULT '',
   `GROUP_DESCRIPTION` VARCHAR(200) NULL DEFAULT '',
   `GROUP_CODE` VARCHAR(30) NULL DEFAULT '',
   `PERMISSION_URL` VARCHAR(200) NULL DEFAULT '',
   `PERMISSION_KEY` VARCHAR(100) NULL DEFAULT '',
   `PERMISSION_DESCRIPTION` VARCHAR(200) NULL DEFAULT ''
) ENGINE=MyISAM;



# Dump of table xmo_action_log
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_action_log`;

CREATE TABLE `xmo_action_log` (
  `ID` varchar(32) NOT NULL DEFAULT '' COMMENT '物理主键',
  `USER_ID` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `ACTION_NAME` varchar(50) NOT NULL COMMENT '操作名称',
  `ACTION_DATE` datetime NOT NULL COMMENT '操作时间',
  `ACTION_PARAMS` varchar(255) DEFAULT NULL COMMENT '参数',
  `CLIENT_IP` varchar(100) NOT NULL COMMENT '客户端IP',
  `ACTION_RESULT` varchar(500) DEFAULT NULL COMMENT '操作结果',
  `TARGET_CLASS` varchar(1000) DEFAULT NULL COMMENT '类名',
  `TARGET_METHOD` varchar(1000) DEFAULT NULL COMMENT '方法名',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志';



# Dump of table xmo_app_info
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_app_info`;

CREATE TABLE `xmo_app_info` (
  `id` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `app_name` varchar(200) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '应用名称',
  `app_code` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '应用代码',
  `description` varchar(500) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '描述',
  `url` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT 'URL地址',
  `state` int(11) NOT NULL COMMENT '状态：0-准备中，1-已上线，9-已废弃',
  `remark` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  `dev_group` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '开发分组',
  `test_group` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '测试分组',
  PRIMARY KEY (`id`),
  UNIQUE KEY `app_info_code_uni` (`app_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



# Dump of table xmo_attachment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_attachment`;

CREATE TABLE `xmo_attachment` (
  `ID` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `ATTACHMENT_GROUP` varchar(20) NOT NULL COMMENT '附件所属组',
  `ATTACHMENT_KEY` varchar(50) NOT NULL DEFAULT '' COMMENT '附件KEY',
  `ATTACHMENT_SIZE` decimal(10,0) NOT NULL COMMENT '附件大小',
  `ATTACHMENT_PATH` varchar(200) NOT NULL DEFAULT '' COMMENT '附件URL',
  `ATTACHMENT_SUFFIX` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '附件后缀',
  `ORIGIN_NAME` varchar(100) NOT NULL COMMENT '原名称',
  `UPLOAD_TIME` datetime NOT NULL COMMENT '上传时间',
  `UPLOAD_USER_ID` varchar(32) NOT NULL DEFAULT '' COMMENT '上传人ID',
  `RELATION_ID` varchar(32) DEFAULT '' COMMENT '关联ID',
  `IS_PRIVATE` char(1) NOT NULL DEFAULT '' COMMENT '是否私有',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ATTACHMENT_KEY_UNIQUE` (`ATTACHMENT_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table xmo_dictionary
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_dictionary`;

CREATE TABLE `xmo_dictionary` (
  `ID` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `GROUP_NAME` varchar(50) NOT NULL DEFAULT '' COMMENT '字典组名称',
  `GROUP_CODE` varchar(50) NOT NULL DEFAULT '' COMMENT '字典组代码',
  `DICTIONARY_NAME` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `DICTIONARY_CODE` varchar(50) NOT NULL DEFAULT '' COMMENT '代码',
  `SORT` int(3) NOT NULL COMMENT '排序',
  `ACTIVE` char(1) NOT NULL DEFAULT '0' COMMENT '激活',
  `PARENT_ID` varchar(32) DEFAULT '' COMMENT '父节点',
  `IS_SHOW` char(1) NOT NULL DEFAULT '0' COMMENT '显示',
  `CREATED_USER_ID` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `CREATED_TIME` datetime NOT NULL COMMENT '创建时间',
  `UPDATED_USER_ID` varchar(32) NOT NULL DEFAULT '' COMMENT '更新人',
  `UPDATED_TIME` datetime NOT NULL COMMENT '更新时间',
  `DATA_VERSION` int(10) DEFAULT '0' COMMENT '数据版本号',
  PRIMARY KEY (`GROUP_CODE`,`DICTIONARY_CODE`),
  KEY `DICTIONARY_UNIQUE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典参数表';



# Dump of table xmo_group
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_group`;

CREATE TABLE `xmo_group` (
  `ID` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `GROUP_CODE` varchar(30) NOT NULL DEFAULT '' COMMENT '用户组代码',
  `GROUP_NAME` varchar(100) NOT NULL DEFAULT '' COMMENT '用户组名称',
  `DESCRIPTION` varchar(200) NOT NULL DEFAULT '' COMMENT '用户组描述',
  `ACTIVE` char(1) NOT NULL DEFAULT '' COMMENT '激活',
  `GROUP_TYPE` varchar(11) NOT NULL DEFAULT '' COMMENT '用户组类型',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UNIQUE_GROUP_CODE` (`GROUP_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';



# Dump of table xmo_group_permission
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_group_permission`;

CREATE TABLE `xmo_group_permission` (
  `ID` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `GROUP_ID` varchar(32) NOT NULL DEFAULT '' COMMENT '组表ID',
  `PERMISSION_ID` varchar(32) NOT NULL DEFAULT '' COMMENT '权限表ID',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `GROUP_PERMISSION_UNIQUE` (`GROUP_ID`,`PERMISSION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';



# Dump of table xmo_notification
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_notification`;

CREATE TABLE `xmo_notification` (
  `ID` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `NOTIFICATION_TYPE` int(2) NOT NULL COMMENT '通知类型',
  `TITLE` varchar(500) NOT NULL DEFAULT '' COMMENT '标题',
  `BODY` text NOT NULL COMMENT '内容',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table xmo_organization
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_organization`;

CREATE TABLE `xmo_organization` (
  `ID` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `NAME` varchar(30) NOT NULL DEFAULT '' COMMENT '组织名称',
  `CODE` varchar(20) NOT NULL DEFAULT '' COMMENT '组织代码',
  `DESCRIPTION` varchar(200) DEFAULT NULL COMMENT '组织描述',
  `PARENT_ID` varchar(32) DEFAULT NULL COMMENT '上级组织',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';



# Dump of table xmo_organization_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_organization_user`;

CREATE TABLE `xmo_organization_user` (
  `ID` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `ORGANIZATION_ID` varchar(32) NOT NULL DEFAULT '' COMMENT '部门表ID',
  `USER_ID` varchar(32) NOT NULL DEFAULT '' COMMENT '用户表ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门用户关联表';



# Dump of table xmo_permission
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_permission`;

CREATE TABLE `xmo_permission` (
  `ID` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `PERMISSION_NAME` varchar(100) NOT NULL DEFAULT '' COMMENT '权限名称',
  `PERMISSION_URL` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '权限URL',
  `PERMISSION_KEY` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '权限KEY',
  `PERMISSION_ACTION` varchar(50) NOT NULL COMMENT '权限操作级别',
  `DESCRIPTION` varchar(200) NOT NULL DEFAULT '' COMMENT '权限资源',
  `ACTIVE` char(1) NOT NULL DEFAULT '' COMMENT '激活',
  `CREATED_USER_ID` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `CREATED_TIME` datetime NOT NULL COMMENT '创建时间',
  `UPDATED_USER_ID` varchar(32) NOT NULL DEFAULT '' COMMENT '更新人',
  `UPDATED_TIME` datetime NOT NULL COMMENT '更新时间',
  `DATA_VERSION` int(10) NOT NULL DEFAULT '0' COMMENT '数据版本号',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `PERMISSION_KEY_UNI` (`PERMISSION_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';



# Dump of table xmo_qrtz_blob_triggers
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_qrtz_blob_triggers`;

CREATE TABLE `xmo_qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `xmo_qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `xmo_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table xmo_qrtz_calendars
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_qrtz_calendars`;

CREATE TABLE `xmo_qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table xmo_qrtz_cron_triggers
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_qrtz_cron_triggers`;

CREATE TABLE `xmo_qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(200) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `xmo_qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `xmo_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table xmo_qrtz_fired_triggers
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_qrtz_fired_triggers`;

CREATE TABLE `xmo_qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table xmo_qrtz_job_details
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_qrtz_job_details`;

CREATE TABLE `xmo_qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL DEFAULT '' COMMENT '任务名称',
  `JOB_GROUP` varchar(200) NOT NULL DEFAULT '' COMMENT '任务属组',
  `DESCRIPTION` varchar(250) DEFAULT NULL COMMENT '任务描述',
  `JOB_CLASS_NAME` varchar(250) NOT NULL DEFAULT '' COMMENT '任务类名',
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table xmo_qrtz_locks
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_qrtz_locks`;

CREATE TABLE `xmo_qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table xmo_qrtz_paused_trigger_grps
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_qrtz_paused_trigger_grps`;

CREATE TABLE `xmo_qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table xmo_qrtz_scheduler_state
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_qrtz_scheduler_state`;

CREATE TABLE `xmo_qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table xmo_qrtz_simple_triggers
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_qrtz_simple_triggers`;

CREATE TABLE `xmo_qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `xmo_qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `xmo_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table xmo_qrtz_simprop_triggers
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_qrtz_simprop_triggers`;

CREATE TABLE `xmo_qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `xmo_qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `xmo_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table xmo_qrtz_triggers
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_qrtz_triggers`;

CREATE TABLE `xmo_qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  CONSTRAINT `xmo_qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `xmo_qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table xmo_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_user`;

CREATE TABLE `xmo_user` (
  `ID` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `USERNAME` varchar(30) NOT NULL DEFAULT '' COMMENT '用户名',
  `NICKNAME` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `SALT` varchar(50) NOT NULL DEFAULT '' COMMENT '密码盐值',
  `PASSWORD` varchar(50) NOT NULL DEFAULT '' COMMENT '密码',
  `EMAIL` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `PHONE_NUMBER` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `AVATAR` varchar(100) DEFAULT NULL COMMENT '头像',
  `LOCKED` char(1) DEFAULT NULL COMMENT '锁定',
  `CREATED_TIME` datetime DEFAULT NULL COMMENT '注册时间',
  `LAST_LOGIN_TIME` datetime DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USERNAME` (`USERNAME`),
  UNIQUE KEY `PHONE_NUMBER` (`PHONE_NUMBER`),
  UNIQUE KEY `EMAIL` (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';



# Dump of table xmo_user_group
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_user_group`;

CREATE TABLE `xmo_user_group` (
  `ID` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `USER_ID` varchar(32) NOT NULL DEFAULT '' COMMENT '用户表ID',
  `GROUP_ID` varchar(32) NOT NULL DEFAULT '' COMMENT '组表ID',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USER_GROUP_RELATION_UNIQUE` (`USER_ID`,`GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';



# Dump of table xmo_user_permission
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_user_permission`;

CREATE TABLE `xmo_user_permission` (
  `ID` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `USER_ID` varchar(32) NOT NULL DEFAULT '' COMMENT '用户表ID',
  `PERMISSION_ID` varchar(32) NOT NULL DEFAULT '' COMMENT '权限表ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table xmo_work_order
# ------------------------------------------------------------

DROP TABLE IF EXISTS `xmo_work_order`;

CREATE TABLE `xmo_work_order` (
  `id` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `work_order_no` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '工单编号',
  `worker` varchar(50) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '处理人',
  `workorder_type` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '工单类型',
  `title` varchar(200) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '标题',
  `content` text COLLATE utf8_unicode_ci NOT NULL COMMENT '内容',
  `state` varchar(10) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '状态',
  `submitter` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '提出人',
  `created_by` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `updated_by` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '修改人',
  `updated_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `un_worker_order_no` (`work_order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;





# Replace placeholder table for v_account_permission with correct view syntax
# ------------------------------------------------------------

DROP TABLE `v_account_permission`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `v_account_permission` AS (select `u`.`USERNAME` AS `USERNAME`,`g`.`GROUP_NAME` AS `GROUP_NAME`,`g`.`DESCRIPTION` AS `GROUP_DESCRIPTION`,`g`.`GROUP_CODE` AS `GROUP_CODE`,`p`.`PERMISSION_URL` AS `PERMISSION_URL`,`p`.`PERMISSION_KEY` AS `PERMISSION_KEY`,`p`.`DESCRIPTION` AS `PERMISSION_DESCRIPTION` from ((((`xmo_group_permission` `gp` left join `xmo_group` `g` on((`g`.`ID` = `gp`.`GROUP_ID`))) left join `xmo_permission` `p` on((`p`.`ID` = `gp`.`PERMISSION_ID`))) left join `xmo_user_group` `ug` on((`ug`.`GROUP_ID` = `g`.`ID`))) left join `xmo_user` `u` on((`u`.`ID` = `ug`.`USER_ID`))));

--
-- Dumping routines (FUNCTION) for database 'xmo_demo'
--
DELIMITER ;;

# Dump of FUNCTION query_children_organization
# ------------------------------------------------------------

/*!50003 DROP FUNCTION IF EXISTS `query_children_organization` */;;
/*!50003 SET SESSION SQL_MODE="STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION"*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`%`*/ /*!50003 FUNCTION `query_children_organization`(organization_id INt(11)) RETURNS varchar(4000) CHARSET utf8
BEGIN
	DECLARE s_temp VARCHAR(4000);
	DECLARE s_temp_chd VARCHAR(4000);
	SET s_temp = '$';
	SET s_temp_chd = cast(organization_id as char);
	WHILE s_temp_chd is not NULL DO
	SET s_temp = CONCAT(s_temp,',',s_temp_chd);
	SELECT group_concat(id) INTO s_temp_chd FROM sys_organization where FIND_IN_SET(parent_id,s_temp_chd)>0;
	END WHILE;
	return s_temp;
    END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
DELIMITER ;

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
