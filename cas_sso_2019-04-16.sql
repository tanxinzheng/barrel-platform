# ************************************************************
# Sequel Pro SQL dump
# Version 4499
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.19)
# Database: cas_sso
# Generation Time: 2019-04-16 02:19:33 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table cas_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `cas_user`;

CREATE TABLE `cas_user` (
  `id` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '主键',
  `username` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '密码',
  `salt` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '密码盐值',
  `email` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '电子邮箱',
  `phone` varchar(100) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '手机号码',
  `nickname` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '昵称',
  `created_by` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `updated_by` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '更新人',
  `updated_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNI_USERNAME` (`username`),
  UNIQUE KEY `UNI_NICKNAME` (`nickname`),
  UNIQUE KEY `UNI_EMAIL` (`email`),
  UNIQUE KEY `UNI_PHONE` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户表';

LOCK TABLES `cas_user` WRITE;
/*!40000 ALTER TABLE `cas_user` DISABLE KEYS */;

INSERT INTO `cas_user` (`id`, `username`, `password`, `salt`, `email`, `phone`, `nickname`, `created_by`, `created_time`, `updated_by`, `updated_time`)
VALUES
	('1','admin','5db797c087ba56cebc4326f42719f123','9c5aca7819134a3bb1cb4dd395f7cacb','admin@xmomen.com','','管理员','SYSTEM','2019-04-16 10:15:17','SYSTEM','2019-04-16 10:15:23');

/*!40000 ALTER TABLE `cas_user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
