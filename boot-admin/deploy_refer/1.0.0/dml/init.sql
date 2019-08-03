# ************************************************************
# Sequel Pro SQL dump
# Version 4499
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.19)
# Database: xmo_demo
# Generation Time: 2019-08-03 05:53:22 +0000
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



# Dump of table xmo_action_log
# ------------------------------------------------------------

LOCK TABLES `xmo_action_log` WRITE;
/*!40000 ALTER TABLE `xmo_action_log` DISABLE KEYS */;

INSERT INTO `xmo_action_log` (`ID`, `USER_ID`, `ACTION_NAME`, `ACTION_DATE`, `ACTION_PARAMS`, `CLIENT_IP`, `ACTION_RESULT`, `TARGET_CLASS`, `TARGET_METHOD`)
VALUES
	('02515c9cb27311e997630242ac1e0002',NULL,'查询当前用户权限','2019-07-30 10:37:44',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','getAccountAuthorities'),
	('03b32853a54311e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-21 13:06:27',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('03ba1986a54311e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-21 13:06:27',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('03bd6c95a5e011e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询定时任务','2018-08-22 07:50:18',NULL,'127.0.0.1',NULL,'com.xmomen.module.scheduler.controller.ScheduleTaskController','getScheduleTaskList'),
	('03ff306aad6111e9a7bf0242ac1e0002','f1a70ab2961c11e69aef080027b274d7','查询当前用户权限','2019-07-23 23:46:20',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','getAccountAuthorities'),
	('041eda9fad6111e9a7bf0242ac1e0002','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2019-07-23 23:46:20',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','accountSetting'),
	('1572e6fea5e611e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-22 08:33:45',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('1f6ee25ab51911e99e0a0242ac1e0002','f1a70ab2961c11e69aef080027b274d7','新增用户组','2019-08-02 19:31:51',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.authorization.controller.GroupController','createGroup'),
	('20b1ec95a5e611e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-22 08:34:04',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('22918f81a5e011e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-22 07:51:10',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('22954a0ca5e011e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-22 07:51:10',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('2345e931a5e611e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-22 08:34:08',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('2384b2d5a5e011e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-22 07:51:12',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('248fcfbfa5e011e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-22 07:51:13',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('24942b3da5e011e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-22 07:51:13',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('2498226ca93911e992210242ac1e0002','f1a70ab2961c11e69aef080027b274d7','查询当前用户权限','2019-07-18 16:50:50',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','getAccountAuthorities'),
	('24b28a41a93911e992210242ac1e0002','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2019-07-18 16:50:50',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','accountSetting'),
	('24d08aaea5e011e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-22 07:51:14',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('274ebb5e96df11e89a690242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-03 05:36:21',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('2774e9dc96df11e89a690242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-03 05:36:21',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('28fdd4c696df11e89a690242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-03 05:36:24',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('29128df496df11e89a690242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-03 05:36:24',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('2967a485b1a711e88f720242ac110003','f1a70ab2961c11e69aef080027b274d7','查询定时任务','2018-09-06 07:33:34',NULL,'127.0.0.1',NULL,'com.xmomen.module.scheduler.controller.ScheduleTaskController','getScheduleTaskList'),
	('29702c13a5f211e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','新增用户组','2018-08-22 10:00:13',NULL,'127.0.0.1',NULL,'com.xmomen.module.authorization.controller.GroupController','createGroup'),
	('2a93901ba54111e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-21 12:53:14',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('2bb05100a54111e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-21 12:53:15',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('2bb47b64a54111e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-21 12:53:15',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('2bd071b2a5e611e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-22 08:34:23',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('2bd3065fa54111e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-21 12:53:16',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('2bfabb2ea5e611e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-22 08:34:23',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('2d13e48da54111e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-21 12:53:18',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('2d17bea4a54111e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-21 12:53:18',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('2d7ca762a5e611e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-22 08:34:25',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('2d9609f7a5e611e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-22 08:34:26',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('2e5891cca54111e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-21 12:53:20',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('2f25fc87a5e611e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询定时任务','2018-08-22 08:34:28',NULL,'127.0.0.1',NULL,'com.xmomen.module.scheduler.controller.ScheduleTaskController','getScheduleTaskList'),
	('2facb610b27311e997630242ac1e0002',NULL,'查询当前用户权限','2019-07-30 10:39:00',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','getAccountAuthorities'),
	('378b0856ad6111e9a7bf0242ac1e0002','f1a70ab2961c11e69aef080027b274d7','查询定时任务','2019-07-23 23:47:46',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.scheduler.controller.ScheduleTaskController','getScheduleTaskList'),
	('395843b1ad6111e9a7bf0242ac1e0002','f1a70ab2961c11e69aef080027b274d7','查询定时任务','2019-07-23 23:47:49',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.scheduler.controller.ScheduleTaskController','getScheduleTaskList'),
	('398ac39396df11e89a690242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-03 05:36:52',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('39a6b14396df11e89a690242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-03 05:36:52',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('3a7f03c0ae3711e9a7bf0242ac1e0002',NULL,'查询当前用户权限','2019-07-25 01:19:43',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','getAccountAuthorities'),
	('3cfe1db496df11e89a690242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-03 05:36:57',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('3d19a51396df11e89a690242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-03 05:36:58',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('40f86219b27311e997630242ac1e0002',NULL,'查询当前用户权限','2019-07-30 10:39:29',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','getAccountAuthorities'),
	('4139c882a5f211e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','新增用户组','2018-08-22 10:00:52',NULL,'127.0.0.1',NULL,'com.xmomen.module.authorization.controller.GroupController','createGroup'),
	('41d61e73a5e111e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-22 07:59:12',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('41f18266a5e111e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-22 07:59:12',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('4379b696a5f211e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','删除单个用户组','2018-08-22 10:00:56',NULL,'127.0.0.1',NULL,'com.xmomen.module.authorization.controller.GroupController','deleteGroup'),
	('46566043b27411e997630242ac1e0002',NULL,'查询当前用户权限','2019-07-30 10:46:47',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','getAccountAuthorities'),
	('479e7fb4ae3711e9a7bf0242ac1e0002',NULL,'查询当前用户权限','2019-07-25 01:20:05',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','getAccountAuthorities'),
	('509d12d2a51c11e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-21 08:29:26',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('50b3dffca51c11e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-21 08:29:26',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('51f60ed9ae3611e9a7bf0242ac1e0002',NULL,'查询当前用户权限','2019-07-25 01:13:13',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','getAccountAuthorities'),
	('5223b4bca51c11e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询定时任务','2018-08-21 08:29:29',NULL,'127.0.0.1',NULL,'com.xmomen.module.scheduler.controller.ScheduleTaskController','getScheduleTaskList'),
	('53a011c6a51c11e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询定时任务','2018-08-21 08:29:31',NULL,'127.0.0.1',NULL,'com.xmomen.module.scheduler.controller.ScheduleTaskController','getScheduleTaskList'),
	('54bc0d15a51c11e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询定时任务','2018-08-21 08:29:33',NULL,'127.0.0.1',NULL,'com.xmomen.module.scheduler.controller.ScheduleTaskController','getScheduleTaskList'),
	('556b9ba6a5f211e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','新增用户组','2018-08-22 10:01:26',NULL,'127.0.0.1',NULL,'com.xmomen.module.authorization.controller.GroupController','createGroup'),
	('5adf5c00ae3611e9a7bf0242ac1e0002',NULL,'查询当前用户权限','2019-07-25 01:13:28',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','getAccountAuthorities'),
	('5cad3985a5b511e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-22 02:44:59',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('5e2658e6a5b511e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-22 02:45:02',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('5e2bf268a5b511e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-22 02:45:02',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('5eadfd59a5b511e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-22 02:45:03',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('607d460b96e011e89a690242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-03 05:45:07',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('6109446c96e011e89a690242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-03 05:45:07',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('6126c12c96e011e89a690242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-03 05:45:08',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('626eaa5aa51911e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-21 08:08:27',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('62735d37a51911e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-21 08:08:27',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('662549eea5b511e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询定时任务','2018-08-22 02:45:15',NULL,'127.0.0.1',NULL,'com.xmomen.module.scheduler.controller.ScheduleTaskController','getScheduleTaskList'),
	('66ee2ef996e011e89a690242ac110003','f1a70ab2961c11e69aef080027b274d7','查询定时任务','2018-08-03 05:45:17',NULL,'127.0.0.1',NULL,'com.xmomen.module.scheduler.controller.ScheduleTaskController','getScheduleTaskList'),
	('6be219d1b48511e9ba540242ac1e0002','f1a70ab2961c11e69aef080027b274d7','新增数据字典','2019-08-02 01:54:34',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.dictionary.controller.DictionaryController','createDictionary'),
	('6cb879a3ae3611e9a7bf0242ac1e0002',NULL,'查询当前用户权限','2019-07-25 01:13:58',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','getAccountAuthorities'),
	('6e0b2448b27511e997630242ac1e0002',NULL,'查询当前用户权限','2019-07-30 10:55:03',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','getAccountAuthorities'),
	('765dda0aa5f211e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-22 10:02:22',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('7680236ca5f211e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-22 10:02:22',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('78856bf8b27411e997630242ac1e0002',NULL,'查询当前用户权限','2019-07-30 10:48:11',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','getAccountAuthorities'),
	('79a8b2e1a5df11e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-22 07:46:27',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('7a8a6a3aa5b711e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-22 03:00:08',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('7a8f4b30a5b711e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-22 03:00:08',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('7c06ce2ea5df11e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-22 07:46:31',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('7f791519a5f211e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询定时任务','2018-08-22 10:02:37',NULL,'127.0.0.1',NULL,'com.xmomen.module.scheduler.controller.ScheduleTaskController','getScheduleTaskList'),
	('85768b20b27511e997630242ac1e0002',NULL,'查询当前用户权限','2019-07-30 10:55:43',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','getAccountAuthorities'),
	('859de20bb27511e997630242ac1e0002',NULL,'查询当前用户资料信息','2019-07-30 10:55:43',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','accountSetting'),
	('86f575a0a51911e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-21 08:09:29',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('88884b70a51911e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询定时任务','2018-08-21 08:09:31',NULL,'127.0.0.1',NULL,'com.xmomen.module.scheduler.controller.ScheduleTaskController','getScheduleTaskList'),
	('8c305e90b27511e997630242ac1e0002',NULL,'查询当前用户权限','2019-07-30 10:55:54',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','getAccountAuthorities'),
	('8f8f053c96e011e89a690242ac110003','f1a70ab2961c11e69aef080027b274d7','查询定时任务','2018-08-03 05:46:26',NULL,'127.0.0.1',NULL,'com.xmomen.module.scheduler.controller.ScheduleTaskController','getScheduleTaskList'),
	('9396ff5eb27511e997630242ac1e0002',NULL,'查询当前用户资料信息','2019-07-30 10:56:06',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','accountSetting'),
	('9624805596e011e89a690242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-03 05:46:37',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('9627ecc596e011e89a690242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-03 05:46:37',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('96ef2875a5f111e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-22 09:56:07',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('97109490a5f111e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-22 09:56:07',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('9713878ca5f111e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询定时任务','2018-08-22 09:56:07',NULL,'127.0.0.1',NULL,'com.xmomen.module.scheduler.controller.ScheduleTaskController','getScheduleTaskList'),
	('9904c86d96e011e89a690242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-03 05:46:41',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('9a0fd901a5f111e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询定时任务','2018-08-22 09:56:12',NULL,'127.0.0.1',NULL,'com.xmomen.module.scheduler.controller.ScheduleTaskController','getScheduleTaskList'),
	('ac74a762b1a711e88f720242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-09-06 07:37:14',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('ac74a843b1a711e88f720242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-09-06 07:37:14',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('af7c5d23b1a711e88f720242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-09-06 07:37:19',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('b0db2fa5890611e996870242ac1e0002','f1a70ab2961c11e69aef080027b274d7','查询当前用户权限','2019-06-07 17:29:04',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','getAccountAuthorities'),
	('b107a7a5890611e996870242ac1e0002','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2019-06-07 17:29:04',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','accountSetting'),
	('b17ccdf6b1a511e88f720242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-09-06 07:23:04',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('b1ed27c7ae3511e9a7bf0242ac1e0002',NULL,'查询当前用户权限','2019-07-25 01:08:45',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','getAccountAuthorities'),
	('b22eb3e8b1a711e88f720242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-09-06 07:37:24',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('b22f5380b1a711e88f720242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-09-06 07:37:24',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('b6603aeeae9111e9a7bf0242ac1e0002',NULL,'查询当前用户权限','2019-07-25 12:07:26',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','getAccountAuthorities'),
	('b66f5744b1a511e88f720242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-09-06 07:23:12',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('b66f8e10b1a511e88f720242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-09-06 07:23:12',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('b7bc4088ae3511e9a7bf0242ac1e0002',NULL,'查询当前用户权限','2019-07-25 01:08:55',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','getAccountAuthorities'),
	('b889816bb1a711e88f720242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-09-06 07:37:34',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('b8a5e57ba51f11e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询定时任务','2018-08-21 08:53:49',NULL,'127.0.0.1',NULL,'com.xmomen.module.scheduler.controller.ScheduleTaskController','getScheduleTaskList'),
	('b8cb37a0b1a511e88f720242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-09-06 07:23:16',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('baeb98b7b1a511e88f720242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-09-06 07:23:19',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('baebe802b1a511e88f720242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-09-06 07:23:19',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('bbdc4ddcb1a511e88f720242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-09-06 07:23:21',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('c09f5067a5f111e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-22 09:57:17',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('c09fa4aba5f111e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-22 09:57:17',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('c462946ea5f111e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-22 09:57:23',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('c4d2f7cd96e011e89a690242ac110003','f1a70ab2961c11e69aef080027b274d7','查询定时任务','2018-08-03 05:47:55',NULL,'127.0.0.1',NULL,'com.xmomen.module.scheduler.controller.ScheduleTaskController','getScheduleTaskList'),
	('c737198bb27311e997630242ac1e0002',NULL,'查询当前用户权限','2019-07-30 10:43:14',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','getAccountAuthorities'),
	('c92b7dd4b27311e997630242ac1e0002',NULL,'查询当前用户权限','2019-07-30 10:43:17',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','getAccountAuthorities'),
	('cae53be3b1a511e88f720242ac110003','f1a70ab2961c11e69aef080027b274d7','查询定时任务','2018-09-06 07:23:46',NULL,'127.0.0.1',NULL,'com.xmomen.module.scheduler.controller.ScheduleTaskController','getScheduleTaskList'),
	('cb8c88aa890611e996870242ac1e0002','f1a70ab2961c11e69aef080027b274d7','查询当前用户权限','2019-06-07 17:29:48',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','getAccountAuthorities'),
	('cbb6c5f8890611e996870242ac1e0002','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2019-06-07 17:29:49',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','accountSetting'),
	('cc93223da51611e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-21 07:49:57',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('cc9817e9a51611e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-21 07:49:57',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('cd6f7962a51611e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-21 07:49:58',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('cf1b93c7b4cd11e9ba540242ac1e0002','f1a70ab2961c11e69aef080027b274d7','新增数据字典','2019-08-02 10:32:44',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.dictionary.controller.DictionaryController','createDictionary'),
	('d1e1b4efa61311e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-22 14:01:09',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('d208d3d8a61311e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-22 14:01:09',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('d2497221a51611e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询定时任务','2018-08-21 07:50:07',NULL,'127.0.0.1',NULL,'com.xmomen.module.scheduler.controller.ScheduleTaskController','getScheduleTaskList'),
	('d2fa392eb4cc11e9ba540242ac1e0002','f1a70ab2961c11e69aef080027b274d7','新增数据字典','2019-08-02 10:25:41',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.dictionary.controller.DictionaryController','createDictionary'),
	('d6163735b1a511e88f720242ac110003','f1a70ab2961c11e69aef080027b274d7','查询定时任务','2018-09-06 07:24:05',NULL,'127.0.0.1',NULL,'com.xmomen.module.scheduler.controller.ScheduleTaskController','getScheduleTaskList'),
	('d71d0401890211e996870242ac1e0002','f1a70ab2961c11e69aef080027b274d7','查询当前用户权限','2019-06-07 17:01:30',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','getAccountAuthorities'),
	('d73d0799890211e996870242ac1e0002','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2019-06-07 17:01:30',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','accountSetting'),
	('da802f40b27311e997630242ac1e0002',NULL,'查询当前用户权限','2019-07-30 10:43:46',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','getAccountAuthorities'),
	('df029c9bae3811e9a7bf0242ac1e0002',NULL,'查询当前用户权限','2019-07-25 01:31:29',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','getAccountAuthorities'),
	('e0ef272faedc11e9a7bf0242ac1e0002',NULL,'查询当前用户权限','2019-07-25 21:05:30',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','getAccountAuthorities'),
	('e38d5b4daedc11e9a7bf0242ac1e0002',NULL,'查询当前用户权限','2019-07-25 21:05:34',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','getAccountAuthorities'),
	('e4507e2ba51511e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-21 07:43:27',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('e5a3bfcea51511e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-21 07:43:30',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('e5a90063a51511e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-21 07:43:30',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('e81aeee5b27211e997630242ac1e0002',NULL,'查询当前用户权限','2019-07-30 10:37:00',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','getAccountAuthorities'),
	('ea72f8aea51511e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-21 07:43:38',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('ecd576f0b27211e997630242ac1e0002',NULL,'查询当前用户权限','2019-07-30 10:37:08',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','getAccountAuthorities'),
	('ee211396b27311e997630242ac1e0002',NULL,'查询当前用户权限','2019-07-30 10:44:19',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','getAccountAuthorities'),
	('f2e9787296de11e89a690242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-03 05:34:53',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('f6c07147a51511e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','查询定时任务','2018-08-21 07:43:58',NULL,'127.0.0.1',NULL,'com.xmomen.module.scheduler.controller.ScheduleTaskController','getScheduleTaskList'),
	('f778b37a96de11e89a690242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-03 05:35:01',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('f8eff07196de11e89a690242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-03 05:35:03',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting'),
	('f9c6c754b27311e997630242ac1e0002',NULL,'查询当前用户权限','2019-07-30 10:44:39',NULL,'127.0.0.1',NULL,'com.github.tanxinzheng.module.account.controller.AccountController','getAccountAuthorities'),
	('fcd5896596de11e89a690242ac110003','f1a70ab2961c11e69aef080027b274d7','查询当前用户资料信息','2018-08-03 05:35:10',NULL,'127.0.0.1',NULL,'com.xmomen.module.core.controller.AccountController','accountSetting');

/*!40000 ALTER TABLE `xmo_action_log` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table xmo_app_info
# ------------------------------------------------------------

LOCK TABLES `xmo_app_info` WRITE;
/*!40000 ALTER TABLE `xmo_app_info` DISABLE KEYS */;

INSERT INTO `xmo_app_info` (`id`, `app_name`, `app_code`, `description`, `url`, `state`, `remark`, `dev_group`, `test_group`)
VALUES
	('2','统一认证系统','CAS','统一用户认证中心，用于单点登录','http://localhost:12018',0,NULL,NULL,NULL),
	('3','配置管理中心','PMC','用于管理应用配置信息','http://localhost:12018',0,NULL,NULL,NULL),
	('4','应用管理中心','AMC','用于管理所有应用信息，可查询应用信息，注册应用','http://localhost:12018',0,NULL,NULL,NULL),
	('5','容器管理平台','DMP','Portainer容器管理平台，用于集中管理Docker容器','http://localhost:9000/',0,NULL,NULL,NULL),
	('ER4335423','用户管理系统','UMS','用于管理所有用户信息，可查询用户信息，添加用户，用户授权','http://localhost:12018',0,NULL,NULL,NULL);

/*!40000 ALTER TABLE `xmo_app_info` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table xmo_attachment
# ------------------------------------------------------------

LOCK TABLES `xmo_attachment` WRITE;
/*!40000 ALTER TABLE `xmo_attachment` DISABLE KEYS */;

INSERT INTO `xmo_attachment` (`ID`, `ATTACHMENT_GROUP`, `ATTACHMENT_KEY`, `ATTACHMENT_SIZE`, `ATTACHMENT_PATH`, `ATTACHMENT_SUFFIX`, `ORIGIN_NAME`, `UPLOAD_TIME`, `UPLOAD_USER_ID`, `RELATION_ID`, `IS_PRIVATE`)
VALUES
	('29def01fb1a611e88f720242ac110003','DEFAULT','eb580ffca3d642d498729e0edcd79d67',194,'file:json','application/json','output.json','2018-09-06 15:26:27','f1a70ab2961c11e69aef080027b274d7','','');

/*!40000 ALTER TABLE `xmo_attachment` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table xmo_dictionary
# ------------------------------------------------------------

LOCK TABLES `xmo_dictionary` WRITE;
/*!40000 ALTER TABLE `xmo_dictionary` DISABLE KEYS */;

INSERT INTO `xmo_dictionary` (`ID`, `GROUP_NAME`, `GROUP_CODE`, `DICTIONARY_NAME`, `DICTIONARY_CODE`, `SORT`, `ACTIVE`, `PARENT_ID`, `IS_SHOW`, `CREATED_USER_ID`, `CREATED_TIME`, `UPDATED_USER_ID`, `UPDATED_TIME`, `DATA_VERSION`)
VALUES
	('ceeb0008b4cd11e9ba540242ac1e0002','测试分组','TEST_GROUP','第一测试小组','TEST_0-1968910808',0,'0','','0','f1a70ab2961c11e69aef080027b274d7','2019-08-02 10:32:44','f1a70ab2961c11e69aef080027b274d7','2019-08-02 10:32:44',1),
	('6baacc10b48511e9ba540242ac1e0002','测试分组','TEST_GROUP','第一测试小组','TEST_02',0,'0','','0','f1a70ab2961c11e69aef080027b274d7','2019-08-02 01:54:34','f1a70ab2961c11e69aef080027b274d7','2019-08-02 01:54:34',1),
	('d2bfeb59b4cc11e9ba540242ac1e0002','测试分组','TEST_GROUP','第一测试小组','TEST_0685711545',0,'0','','0','f1a70ab2961c11e69aef080027b274d7','2019-08-02 10:25:41','f1a70ab2961c11e69aef080027b274d7','2019-08-02 10:25:41',1);

/*!40000 ALTER TABLE `xmo_dictionary` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table xmo_group
# ------------------------------------------------------------

LOCK TABLES `xmo_group` WRITE;
/*!40000 ALTER TABLE `xmo_group` DISABLE KEYS */;

INSERT INTO `xmo_group` (`ID`, `GROUP_CODE`, `GROUP_NAME`, `DESCRIPTION`, `ACTIVE`, `GROUP_TYPE`)
VALUES
	('1f5da99cb51911e99e0a0242ac1e0002','ROLE_TEST','测试组','测试组专用角色','','CUSTOM'),
	('41379023a5f211e89ca40242ac110003','ROLE_USER','普通用户','普通用户','1','SYSTEM'),
	('556912fca5f211e89ca40242ac110003','ROLE_ADMIN','管理员','系统管理员','1','SYSTEM');

/*!40000 ALTER TABLE `xmo_group` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table xmo_group_permission
# ------------------------------------------------------------

LOCK TABLES `xmo_group_permission` WRITE;
/*!40000 ALTER TABLE `xmo_group_permission` DISABLE KEYS */;

INSERT INTO `xmo_group_permission` (`ID`, `GROUP_ID`, `PERMISSION_ID`)
VALUES
	('31c0cee9b52411e99e0a0242ac1e0002','556912fca5f211e89ca40242ac110003','98a60980f2864a49972ca7c1379d18ce');

/*!40000 ALTER TABLE `xmo_group_permission` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table xmo_notification
# ------------------------------------------------------------



# Dump of table xmo_organization
# ------------------------------------------------------------



# Dump of table xmo_organization_user
# ------------------------------------------------------------



# Dump of table xmo_permission
# ------------------------------------------------------------

LOCK TABLES `xmo_permission` WRITE;
/*!40000 ALTER TABLE `xmo_permission` DISABLE KEYS */;

INSERT INTO `xmo_permission` (`ID`, `PERMISSION_NAME`, `PERMISSION_URL`, `PERMISSION_KEY`, `PERMISSION_ACTION`, `DESCRIPTION`, `ACTIVE`, `CREATED_USER_ID`, `CREATED_TIME`, `UPDATED_USER_ID`, `UPDATED_TIME`, `DATA_VERSION`)
VALUES
	('004267780dc746ff8fca15498d891b3c','','/file/download/zip','POST:/file/download/zip','POST','downloadZip','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('02cbe8fc5c484024bfe866de6538ad2f','','/group/{groupId}/users','DELETE:/group/{groupId}/users','DELETE','用户组解绑用户','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('04b1ab3415cf4a6b8c11833917d7df5f','','/dictionary/{id}','DELETE:/dictionary/{id}','DELETE','删除单个数据字典','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('04c7167209494bcd9a9739c824327f43','','/group/permission/{id}','PUT:/group/permission/{id}','PUT','更新组权限','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('073555257c9243808fa96652fbbde9c8','','/file/download/temps','HEAD:/file/download/temps','HEAD','downloadTempFile','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('0804be9276fa4469a8d4cd9a41f1d444','','/','GET:/','GET','首页跳转','1','TEST','2019-07-28 23:21:10','TEST','2019-07-29 01:10:20',0),
	('0848a7f337c348369b1e657e96acadd5','','/access/find_password','PUT:/access/find_password','PUT','找回密码','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('0964f1e0cd9a4bed9e34b1b46af1bf8d','','/dictionary/{id}','GET:/dictionary/{id}','GET','查询数据字典','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('0cb204e16b0348caab18ab74f00681cd','','/menu','GET:/menu','GET','查询菜单配置列表','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('0d493999cf2e4a80be6eb5da607ae63c','','/action_log','GET:/action_log','GET','getActionLog','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('0f95590336ee4a718f3b2765c1cad946','','/notification/template','GET:/notification/template','GET','查询通知模板列表','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('10911e5b82a84e5db62212484845d747','','/cache/select','GET:/cache/select','GET','查询数据字典列表','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('1370cb6a4dad48ff86241d1f2e225b6a','','/user/group','POST:/user/group','POST','新增用户组关联','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('1652ff93897c44bbb89f5b247f92a40f','','/file/download/zip','OPTIONS:/file/download/zip','OPTIONS','downloadZip','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('18c518795264431d975156aed4d8419c','','/permission/{id}','PUT:/permission/{id}','PUT','更新权限','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('1c5505edff35469885432536fdcb8fdb','','/group/permission/{id}','GET:/group/permission/{id}','GET','查询组权限','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('1d17eb85621c4e2899561cf16be188b1','','/file/download','DELETE:/file/download','DELETE','download','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('1da586ac421d4fda850ab967f8c01506','','/group/permission','POST:/group/permission','POST','新增组权限','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('1df7659227064aae825b8fd62bf3fbea','','/attachment','GET:/attachment','GET','查询附件列表','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('1f75da9b694747f583f04d8e5a1597e3','','/notification/template/{id}','GET:/notification/template/{id}','GET','查询通知模板','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('2212d752a2bb42da8a61ba8d5ec66a6f','','/dictionary','POST:/dictionary','POST','新增数据字典','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('244d087a6a6945c38ef6f00b90682c03','','/user/group/{id}','GET:/user/group/{id}','GET','查询用户组关联','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('248ebec6855e403aa2eca4789cb9f0e1','','/account/password','PUT:/account/password','PUT','当前用户修改密码','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('249c163c26c74dfbb5860e22f580c441','','/user/{userId}/permission','GET:/user/{userId}/permission','GET','getUserPermission','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('28d8ebcb58f64e29a6ff43597f7f81e5','','/file/download','GET:/file/download','GET','download','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('2978c3555c1644a4a1bcb3277861d8c4','','/user','POST:/user','POST','新增用户','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('2c2dfc448a164fd89858dc4d1b782110','','/account','GET:/account','GET','查询当前用户资料信息','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('2c9ca8866f43444a93492b3e4fcfd532','','/attachment','DELETE:/attachment','DELETE','批量删除附件','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('30c9b760b9504280a030961303a4055d','','/permission','POST:/permission','POST','新增权限','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('31ff7bf0593048aab6dc7ce315cb4cb6','','/permission','DELETE:/permission','DELETE','批量删除权限','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('324953547f554ea7b9c21bef119c827e','','/group/{groupId}/users','POST:/group/{groupId}/users','POST','用户组批量绑定用户','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('32a079fcdaa8465d8dda4953de863ab7','','/account/notification/notification/count','GET:/account/notification/notification/count','GET','查询当前用户资料信息','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('36485b3a7f4b48af8586d18cb4a75bf7','','/user/{id}','GET:/user/{id}','GET','查询用户','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('36b00e30e6cd4d9f9c5a2587554a0bc2','','/group/permission','GET:/group/permission','GET','查询组权限列表','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('37710491d2134d2a9d3243d15e93e8f3','','/file/download/zip','PUT:/file/download/zip','PUT','downloadZip','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('37b4f1b7cff443b7b67a97a1b78da2aa','','/file/download/zip','GET:/file/download/zip','GET','downloadZip','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('38280214a1ca4643af421aa21e38e911','','/group/{groupId}/permissions','DELETE:/group/{groupId}/permissions','DELETE','移除用户组权限','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('3b578954e3544e928c8d42a3ac56d38d','','/permission/url','GET:/permission/url','GET','获取所有未纳入权限控制资源','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('3bb5e5cbcd674a09b1e2be1ac33e2d10','','/group/{groupId}/users/unbind','GET:/group/{groupId}/users/unbind','GET','findPermissionByGroup','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('3c5447c5752f4dd69e9cebeec8923d4d','','/menu/{id}','GET:/menu/{id}','GET','查询菜单配置','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('3e0a7ce3d23840b593fee3419385dc18','','/schedule/task/{jobName}','PUT:/schedule/task/{jobName}','PUT','修改定时任务','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('410b5d4b00dc44e5b27e050f52c2a851','','/dictionary','GET:/dictionary','GET','查询数据字典列表','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('41745647653b4ca0b1e761e134ac6b3f','','/group/{groupId}/permissions','GET:/group/{groupId}/permissions','GET','查询用户组所属权限','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('47975eb1a4914a8c8bf74b3cf95c1d6e','','/file/download','PUT:/file/download','PUT','download','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('489e2fee41f04008b82dddb35b0bf8c5','','/notification/{id}','PUT:/notification/{id}','PUT','更新通知','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('4c29c2b7ae4e45fca935dec041a64bc5','','/account/notification','GET:/account/notification','GET','查询当前用户消息，默认查询三个月内','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('4f50b3fd67f346c1a24964c31986afd3','','/permission/url','PUT:/permission/url','PUT','获取所有未纳入权限控制资源','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('50683e989e2d4c90a57080dd3be7f885','','/group/permission','DELETE:/group/permission','DELETE','批量删除组权限','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('519256a797b346d998267756454fd854','','/notification','DELETE:/notification','DELETE','批量删除通知','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('52cf23529d9f4bf49e4674672038d4b7','','/file/download','PATCH:/file/download','PATCH','download','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('55bf69eea87049258b501ce5d876939a','','/notification','GET:/notification','GET','查询通知列表','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('5741c4e1025244db85b95cfcb108d402','','/group/{groupId}/users','GET:/group/{groupId}/users','GET','查询用户组已绑定用户','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('5c422bc347544a52968286de89ce06f5','','/notification/{id}','DELETE:/notification/{id}','DELETE','删除单个通知','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('5e4a406872f64519b509a9d3d811b0ff','','/permission/url','HEAD:/permission/url','HEAD','获取所有未纳入权限控制资源','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('5f745ec987c74a2a9bd27b20a0b70cce','','/menu','POST:/menu','POST','新增菜单配置','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('5fe951d5fedb4992997c67b3c8be84a9','','/menu/tree','GET:/menu/tree','GET','查询树形菜单','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('606e1b1231204d28b5ca538680b80b00','','/schedule/task/{jobName}','DELETE:/schedule/task/{jobName}','DELETE','删除定时任务','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('60761e539c2f487ea81b44fcc91944a1','','/permission/template','GET:/permission/template','GET','downloadTemplate','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('6648b9c7deac4bf8911004dd4f268bec','','/schedule/task','GET:/schedule/task','GET','查询定时任务','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('67de6df88d0a4b9aba6cf20c8e66675e','','/notification','POST:/notification','POST','新增通知','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('6db0103b0e3c47af9ceccbb3fe938384','','/user/{userId}/group','POST:/user/{userId}/group','POST','createUserGroup','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('6f379a455b614002be311d1a21dbb336','','/notification/{id}','GET:/notification/{id}','GET','查询通知','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('6fb9604d3e134603b955708b265e0e3e','','/group','DELETE:/group','DELETE','批量删除用户组','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('7145bb7676344835aafc9b4314f6430f','','/account/avatar','POST:/account/avatar','POST','更换头像','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('7156f268fd164c088748395568003baf','','/file/download/zip','HEAD:/file/download/zip','HEAD','downloadZip','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('72298a1e2fef4a899a33cc9e9bcc9e53','','/attachment/{id}','GET:/attachment/{id}','GET','查询附件','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('75e4b4b77c304bbbaa58f73e51cc8d92','','/access/login','POST:/access/login','POST','用户登录','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('76f833e931b548c1aa558d0af475c212','','/menu/{id}','PUT:/menu/{id}','PUT','更新菜单配置','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('77eecc46828e464bae600331c6973db1','','/group/{id}','DELETE:/group/{id}','DELETE','删除单个用户组','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('7aab9ffb1b4944b7bac26ec46cc515eb','','/permission/url','OPTIONS:/permission/url','OPTIONS','获取所有未纳入权限控制资源','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('7b214e76abc74303bc9dcb721890fe39','','/notification/template/{id}','DELETE:/notification/template/{id}','DELETE','删除单个通知模板','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('7db23d022e244a039ba261d2a326e32b','','/group','POST:/group','POST','查询用户组列表','1','TEST','2019-07-28 23:21:10','TEST','2019-07-29 12:09:01',0),
	('81c2adc3c8dc4518bb93b3ce9523571a','','/user','DELETE:/user','DELETE','批量删除用户','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('8747ed6c56044b60b74be596301bb8f4','','/file/download','OPTIONS:/file/download','OPTIONS','download','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('8e795ed092c24ff0a4809032b986ac18','','/dictionary/import','POST:/dictionary/import','POST','导入数据字典','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('8f6dcba583ab466bbbff54a58784da2e','','/user','GET:/user','GET','查询用户列表','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('91b4bc54f1624b69a398d30972bce90c','','/account/menu','GET:/account/menu','GET','查询树形菜单','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('92ab19a8d3744b219f79c32869168f18','','/dictionary/export','GET:/dictionary/export','GET','导出数据字典','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('947ee5ac2f924ff9b19acdf50ab390d6','','/file/download/temps','OPTIONS:/file/download/temps','OPTIONS','downloadTempFile','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('96813842e3824b64bc0f22487d0b7c8a','','/user/{id}','PUT:/user/{id}','PUT','更新用户','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('98a60980f2864a49972ca7c1379d18ce','','/permission','GET:/permission','GET','查询权限列表','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('98f49bb7f0f84763916251c2ebe200c6','','/user/group/{id}','PUT:/user/group/{id}','PUT','更新用户组关联','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('992a74b15f504b929cbb6049c3c4cd55','','/file/download/temps','POST:/file/download/temps','POST','downloadTempFile','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('992dde36bbb244e9806dafe0fbc2be08','','/notification/template','DELETE:/notification/template','DELETE','批量删除通知模板','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('99e8ca140b4544d09baf0de8e6bce071','','/user/group/{id}','DELETE:/user/group/{id}','DELETE','删除单个用户组关联','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('9bf3e0076215408c9dedb2f31fe7ef24','','/file/download/temps','DELETE:/file/download/temps','DELETE','downloadTempFile','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('9d98ccd0615e4bd9895c0bc466c2cc0e','','/file/download/zip','PATCH:/file/download/zip','PATCH','downloadZip','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('a312fa76141d4d99b86e2f1de4ce7746','','/schedule/task','POST:/schedule/task','POST','新增定时任务','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('a4b1196416ea4795b0f61a2f6225c7e7','','/menu','DELETE:/menu','DELETE','批量删除菜单配置','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('a73d22890bd54b4d81aaf34b5f7b8e51','','/file/download/zip','DELETE:/file/download/zip','DELETE','downloadZip','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('a7d80d5785934531bc12716331475690','','/access/register','POST:/access/register','POST','用户注册','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('a8440c90bc4745c2b7f15d172e331be0','','/permission/url','POST:/permission/url','POST','获取所有未纳入权限控制资源','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('ab99ba18fd914244b5c7089e13b3e7a0','','/cache','DELETE:/cache','DELETE','清除缓存','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('acc2693c5b764d04856dd013ad34af96','','/group/permission/{id}','DELETE:/group/permission/{id}','DELETE','删除单个组权限','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('af58c262c9fc4597addd30e1d984f947','','/file/upload','POST:/file/upload','POST','upload','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('bf66d016a28343c49ff5deb513e69e48','','/group/{id}','GET:/group/{id}','GET','查询用户组','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('c1090726c27e490a917f7425ea98114b','','/user/group','DELETE:/user/group','DELETE','批量删除用户组关联','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('c16eaaedc2374902a035bb9c34f6e47d','','/dictionary','DELETE:/dictionary','DELETE','批量删除数据字典','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('c1f1fb328eb3477d8e1a1cd6114c7fbd','','/file/download/temps','PUT:/file/download/temps','PUT','downloadTempFile','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('c5aa2143ef3b42be87936a3d2a3e273b','','/group/{groupId}/permissions','POST:/group/{groupId}/permissions','POST','绑定组权限','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('c9a5ed2d41e2466aa716a77975a733af','','/account/bind','PUT:/account/bind','PUT','绑定手机、邮箱','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('ca77b395d1324f688dafda5ed4f6dc57','','/attachment/{id}','DELETE:/attachment/{id}','DELETE','删除单个附件','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('cc16b964375b4b718e7648d441325cbf','','/notification/template/{id}','PUT:/notification/template/{id}','PUT','更新通知模板','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('ccb0a74c74e9496a80096e0905ac61e3','','/permission/{id}','GET:/permission/{id}','GET','查询权限','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('ce8cbf5e0d714db6b1bf9d1407f0765c','','/access/code','POST:/access/code','POST','发送验证码','1','TEST','2019-07-29 01:10:19','TEST','2019-07-29 01:10:19',0),
	('cf77ccf3aa8e49d9b9c589b136b8d1e9','','/permission/{id}','DELETE:/permission/{id}','DELETE','删除单个权限','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('d29a7d93ae244349816abd04eb5b76a4','','/dictionary/{id}','PUT:/dictionary/{id}','PUT','更新数据字典','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('d4c5e0eebdda4640aee594e431a8c106','','/file/download/temps','PATCH:/file/download/temps','PATCH','downloadTempFile','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('d56122b08ef74078923dcb82aaf7faaa','','/notification/template','POST:/notification/template','POST','新增通知模板','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('d57a8551d3fa41fa831ec3fa83c14ccd','','/user/{id}','DELETE:/user/{id}','DELETE','删除单个用户','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('d87247e61e7a4bfa8e8ef30114be639a','','/user/{userId}/group','GET:/user/{userId}/group','GET','getUserGroup','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('df56e25c69de4fe98ce339127ea87ec5','','/group/{id}','PUT:/group/{id}','PUT','更新用户组','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('e19706005281418f80f6ff4801ed0327','','/permission/url','DELETE:/permission/url','DELETE','获取所有未纳入权限控制资源','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('e256883ffd1b476897be94fd27b4b768','','/permission/import','POST:/permission/import','POST','批量导入权限','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('e4abaa6c1a454c9bae551a744544faf7','','/account/authorities','GET:/account/authorities','GET','查询当前用户权限','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('e6d3e6decd334f029658b337ba9fec1c','','/file/download/temps','GET:/file/download/temps','GET','downloadTempFile','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('e6eb76a20f454123af61d65e8a8e1ba6','','/attachment','POST:/attachment','POST','新增附件','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('e920889d757b413da6e75cc414089bb2','','/permission/export','GET:/permission/export','GET','导出权限','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('eda172048cad40b5a60a8d8141b6cf4e','','/file/download','POST:/file/download','POST','download','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('ef3a6de241dd43808fe87b12361c8714','','/menu/{id}','DELETE:/menu/{id}','DELETE','删除单个菜单配置','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('f0b05aa2ac5a4c1a94e34a4161a767b6','','/email','POST:/email','POST','测试邮件发送','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('f5b4e920bf3f42eb9e92bafffba6d91c','','/file/download','HEAD:/file/download','HEAD','download','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('f7e9d950481442aeb32ea83808a94d01','','/permission/url','PATCH:/permission/url','PATCH','获取所有未纳入权限控制资源','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('fb0e46ddafb749a68e71d38e6cf35e26','','/dictionary/template','GET:/dictionary/template','GET','下载数据字典导入模板','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('fbd3b2e077064540838a0ac82f43833f','','/notification/{id}/read','PUT:/notification/{id}/read','PUT','更新通知','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('fc070d0341ab461db2a1be71c6bd76a7','','/attachment/{id}','PUT:/attachment/{id}','PUT','更新附件','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('fc1a437075ae40409252031cac1a5a5f','','/permission/sync','GET:/permission/sync','GET','获取所有未纳入权限控制资源','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('fce7e4e86a2e46f9a78a0ed020c3b1d9','','/account/notification/notification/count/unread','GET:/account/notification/notification/count/unread','GET','查询当前用户资料信息','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0),
	('fe303077839f4ece8dc43e734fa1b6da','','/user/group','GET:/user/group','GET','查询用户组关联列表','1','TEST','2019-07-28 23:21:10','TEST','2019-07-28 23:21:10',0);

/*!40000 ALTER TABLE `xmo_permission` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table xmo_qrtz_blob_triggers
# ------------------------------------------------------------



# Dump of table xmo_qrtz_calendars
# ------------------------------------------------------------



# Dump of table xmo_qrtz_cron_triggers
# ------------------------------------------------------------

LOCK TABLES `xmo_qrtz_cron_triggers` WRITE;
/*!40000 ALTER TABLE `xmo_qrtz_cron_triggers` DISABLE KEYS */;

INSERT INTO `xmo_qrtz_cron_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `CRON_EXPRESSION`, `TIME_ZONE_ID`)
VALUES
	('springJobSchedulerFactoryBean','simpleJobCronTriggerBean','DEFAULT','0/5 * * * * ?','Asia/Shanghai');

/*!40000 ALTER TABLE `xmo_qrtz_cron_triggers` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table xmo_qrtz_fired_triggers
# ------------------------------------------------------------



# Dump of table xmo_qrtz_job_details
# ------------------------------------------------------------

LOCK TABLES `xmo_qrtz_job_details` WRITE;
/*!40000 ALTER TABLE `xmo_qrtz_job_details` DISABLE KEYS */;

INSERT INTO `xmo_qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `JOB_CLASS_NAME`, `IS_DURABLE`, `IS_NONCONCURRENT`, `IS_UPDATE_DATA`, `REQUESTS_RECOVERY`, `JOB_DATA`)
VALUES
	('springJobSchedulerFactoryBean','SIMPLE_JOB','DEFAULT','定时任务测试','com.github.tanxinzheng.module.scheduler.job.SimpleJob','1','0','0','0',X'ACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800');

/*!40000 ALTER TABLE `xmo_qrtz_job_details` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table xmo_qrtz_locks
# ------------------------------------------------------------



# Dump of table xmo_qrtz_paused_trigger_grps
# ------------------------------------------------------------



# Dump of table xmo_qrtz_scheduler_state
# ------------------------------------------------------------



# Dump of table xmo_qrtz_simple_triggers
# ------------------------------------------------------------



# Dump of table xmo_qrtz_simprop_triggers
# ------------------------------------------------------------



# Dump of table xmo_qrtz_triggers
# ------------------------------------------------------------

LOCK TABLES `xmo_qrtz_triggers` WRITE;
/*!40000 ALTER TABLE `xmo_qrtz_triggers` DISABLE KEYS */;

INSERT INTO `xmo_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `NEXT_FIRE_TIME`, `PREV_FIRE_TIME`, `PRIORITY`, `TRIGGER_STATE`, `TRIGGER_TYPE`, `START_TIME`, `END_TIME`, `CALENDAR_NAME`, `MISFIRE_INSTR`, `JOB_DATA`)
VALUES
	('springJobSchedulerFactoryBean','simpleJobCronTriggerBean','DEFAULT','SIMPLE_JOB','DEFAULT',NULL,1564675595000,1564675590000,0,'WAITING','CRON',1564675580000,0,NULL,0,'');

/*!40000 ALTER TABLE `xmo_qrtz_triggers` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table xmo_user
# ------------------------------------------------------------

LOCK TABLES `xmo_user` WRITE;
/*!40000 ALTER TABLE `xmo_user` DISABLE KEYS */;

INSERT INTO `xmo_user` (`ID`, `USERNAME`, `NICKNAME`, `SALT`, `PASSWORD`, `EMAIL`, `PHONE_NUMBER`, `AVATAR`, `LOCKED`, `CREATED_TIME`, `LAST_LOGIN_TIME`)
VALUES
	('f1a70ab2961c11e69aef080027b274d7','admin','系统管理员','9c5aca7819134a3bb1cb4dd395f7cacb','876a44276c2abf4db2873ea64af8df91','admin@xmomen.com','15000084483','07f8eeb1ad7745d399509dead280f8aa','0','2017-07-29 11:21:31','2017-11-08 01:05:23'),
	('f60a85d77b8f11e7bfe902004c4f4f50','test','ces','4ea33c9cc71944bd935e251cf424e45d','f0df6d45459a6ae0ebf54d44773b3b43',NULL,NULL,NULL,NULL,'2017-08-08 00:46:32','2017-08-23 01:24:01');

/*!40000 ALTER TABLE `xmo_user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table xmo_user_group
# ------------------------------------------------------------

LOCK TABLES `xmo_user_group` WRITE;
/*!40000 ALTER TABLE `xmo_user_group` DISABLE KEYS */;

INSERT INTO `xmo_user_group` (`ID`, `USER_ID`, `GROUP_ID`)
VALUES
	('5ae3c022a5f211e89ca40242ac110003','f1a70ab2961c11e69aef080027b274d7','41379023a5f211e89ca40242ac110003'),
	('e5cff405a5f311e89ca40242ac110003','f60a85d77b8f11e7bfe902004c4f4f50','556912fca5f211e89ca40242ac110003');

/*!40000 ALTER TABLE `xmo_user_group` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table xmo_user_permission
# ------------------------------------------------------------



# Dump of table xmo_work_order
# ------------------------------------------------------------





# Replace placeholder table for v_account_permission with correct view syntax
# ------------------------------------------------------------

DROP TABLE `v_account_permission`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `v_account_permission` AS (select `u`.`USERNAME` AS `USERNAME`,`g`.`GROUP_NAME` AS `GROUP_NAME`,`g`.`DESCRIPTION` AS `GROUP_DESCRIPTION`,`g`.`GROUP_CODE` AS `GROUP_CODE`,`p`.`PERMISSION_URL` AS `PERMISSION_URL`,`p`.`PERMISSION_KEY` AS `PERMISSION_KEY`,`p`.`DESCRIPTION` AS `PERMISSION_DESCRIPTION` from ((((`xmo_group_permission` `gp` left join `xmo_group` `g` on((`g`.`ID` = `gp`.`GROUP_ID`))) left join `xmo_permission` `p` on((`p`.`ID` = `gp`.`PERMISSION_ID`))) left join `xmo_user_group` `ug` on((`ug`.`GROUP_ID` = `g`.`ID`))) left join `xmo_user` `u` on((`u`.`ID` = `ug`.`USER_ID`))));

--
-- Dumping routines (FUNCTION) for database 'xmo_demo'
--
DELIMITER ;;

DELIMITER ;

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
