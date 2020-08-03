-- MySQL dump 10.13  Distrib 5.7.28, for Win64 (x86_64)
--
-- Host: localhost    Database: edu
-- ------------------------------------------------------
-- Server version	5.7.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `crm_banner`
--

DROP TABLE IF EXISTS `crm_banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crm_banner` (
  `id` char(19) NOT NULL DEFAULT '' COMMENT 'ID',
  `title` varchar(20) DEFAULT '' COMMENT '标题',
  `image_url` varchar(500) NOT NULL DEFAULT '' COMMENT '图片地址',
  `link_url` varchar(500) DEFAULT '' COMMENT '链接地址',
  `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='首页banner表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crm_banner`
--

LOCK TABLES `crm_banner` WRITE;
/*!40000 ALTER TABLE `crm_banner` DISABLE KEYS */;
INSERT INTO `crm_banner` VALUES ('1194556896025845762','test1','https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-19/57c5694b9387d55afd54a78e6b2020-03-07_17-32-18.jpg','/course',1,0,'2019-11-13 18:05:32','2019-11-18 10:28:22'),('1194607458461216769','test2','https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-19/876dd345d08ca598d7f4d7c7451546342130746.jpg','/teacher',2,0,'2019-11-13 21:26:27','2019-11-14 09:12:15');
/*!40000 ALTER TABLE `crm_banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edu_chapter`
--

DROP TABLE IF EXISTS `edu_chapter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `edu_chapter` (
  `id` char(19) NOT NULL COMMENT '章节ID',
  `course_id` char(19) NOT NULL COMMENT '课程ID',
  `title` varchar(50) NOT NULL COMMENT '章节名称',
  `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '显示排序',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_course_id` (`course_id`),
  CONSTRAINT `fk_2` FOREIGN KEY (`course_id`) REFERENCES `edu_course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='课程';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edu_chapter`
--

LOCK TABLES `edu_chapter` WRITE;
/*!40000 ALTER TABLE `edu_chapter` DISABLE KEYS */;
INSERT INTO `edu_chapter` VALUES ('1281555011880386562','1281554975297667074','1',1,'2020-07-10 19:45:00','2020-07-10 19:45:40'),('1281555036341567489','1281554975297667074','2',2,'2020-07-10 19:45:05','2020-07-10 19:45:05'),('1281555053974421505','1281554975297667074','3',3,'2020-07-10 19:45:10','2020-07-10 19:45:10'),('1284771354389798913','1284771305584877569','第一章',1,'2020-07-19 16:45:35','2020-07-19 16:45:35'),('1284771951528665090','1284771912500666369','一',1,'2020-07-19 16:47:58','2020-07-19 16:47:58'),('1284771972181417986','1284771912500666369','二',2,'2020-07-19 16:48:03','2020-07-19 16:48:03');
/*!40000 ALTER TABLE `edu_chapter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edu_comment`
--

DROP TABLE IF EXISTS `edu_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `edu_comment` (
  `id` char(19) NOT NULL COMMENT '讲师ID',
  `course_id` varchar(19) NOT NULL DEFAULT '' COMMENT '课程id',
  `teacher_id` char(19) NOT NULL DEFAULT '' COMMENT '讲师id',
  `member_id` varchar(19) NOT NULL DEFAULT '' COMMENT '会员id',
  `nickname` varchar(50) DEFAULT NULL COMMENT '会员昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '会员头像',
  `content` varchar(500) NOT NULL COMMENT '评论内容',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_course_id` (`course_id`),
  KEY `idx_teacher_id` (`teacher_id`),
  KEY `idx_member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edu_comment`
--

LOCK TABLES `edu_comment` WRITE;
/*!40000 ALTER TABLE `edu_comment` DISABLE KEYS */;
INSERT INTO `edu_comment` VALUES ('1288822613031813121','1281554975297667074','1189389726308478977','1286227636506427393','lhl','https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-19/93a161419e9b9095bf18e91add1561898715099.jpg','发发斯蒂芬',0,'2020-07-30 21:03:51','2020-07-30 21:03:51'),('1288822669050937345','1281554975297667074','1189389726308478977','1286227636506427393','lhl','https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-19/93a161419e9b9095bf18e91add1561898715099.jpg','按时发大水发三份',0,'2020-07-30 21:04:04','2020-07-30 21:04:04'),('1288823381554135041','1281554975297667074','1189389726308478977','1286227636506427393','lhl','https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-19/93a161419e9b9095bf18e91add1561898715099.jpg','阿斯顿发顺丰',0,'2020-07-30 21:06:54','2020-07-30 21:06:54'),('1288825462126051330','1281554975297667074','1189389726308478977','1286227636506427393','lhl','https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-19/93a161419e9b9095bf18e91add1561898715099.jpg','暗示法',0,'2020-07-30 21:15:10','2020-07-30 21:15:10'),('1288826113115586562','1281554975297667074','1189389726308478977','1286227636506427393','lhl','https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-19/93a161419e9b9095bf18e91add1561898715099.jpg','艹',0,'2020-07-30 21:17:45','2020-07-30 21:17:45'),('1288826175556190210','1281554975297667074','1189389726308478977','1286227636506427393','lhl','https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-19/93a161419e9b9095bf18e91add1561898715099.jpg','啦啦啦',0,'2020-07-30 21:18:00','2020-07-30 21:18:00'),('1288831390368002050','1284771305584877569','1189389726308478977','1286227636506427393','lhl','https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-19/93a161419e9b9095bf18e91add1561898715099.jpg','干',0,'2020-07-30 21:38:43','2020-07-30 21:38:43');
/*!40000 ALTER TABLE `edu_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edu_course`
--

DROP TABLE IF EXISTS `edu_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `edu_course` (
  `id` char(19) NOT NULL COMMENT '课程ID',
  `teacher_id` char(19) NOT NULL COMMENT '课程讲师ID',
  `subject_id` char(19) DEFAULT NULL COMMENT '课程专业ID',
  `subject_parent_id` char(19) DEFAULT NULL COMMENT '课程专业父级ID',
  `title` varchar(50) NOT NULL COMMENT '课程标题',
  `price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '课程销售价格，设置为0则可免费观看',
  `lesson_num` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '总课时',
  `cover` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '课程封面图片路径',
  `buy_count` bigint(10) unsigned NOT NULL DEFAULT '0' COMMENT '销售数量',
  `view_count` bigint(10) unsigned NOT NULL DEFAULT '0' COMMENT '浏览数量',
  `version` bigint(20) unsigned NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `status` varchar(10) NOT NULL DEFAULT 'Draft' COMMENT '课程状态 Draft未发布  Normal已发布',
  `is_deleted` tinyint(3) DEFAULT NULL COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_title` (`title`),
  KEY `idx_subject_id` (`subject_id`),
  KEY `idx_teacher_id` (`teacher_id`),
  KEY `fk_subject_parent_id` (`subject_parent_id`),
  CONSTRAINT `fk_subject_id` FOREIGN KEY (`subject_id`) REFERENCES `edu_subject` (`id`),
  CONSTRAINT `fk_subject_parent_id` FOREIGN KEY (`subject_parent_id`) REFERENCES `edu_subject` (`parent_id`),
  CONSTRAINT `fk_teacher_id` FOREIGN KEY (`teacher_id`) REFERENCES `edu_teacher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='课程';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edu_course`
--

LOCK TABLES `edu_course` WRITE;
/*!40000 ALTER TABLE `edu_course` DISABLE KEYS */;
INSERT INTO `edu_course` VALUES ('1281554975297667074','1189389726308478977','1279681596143738882','1279681595988549634','哈哈哈',5.00,10,'https://edu-avatar1.oss-cn-beijing.aliyuncs.com/timg.jpg',0,1,1,'Normal',NULL,'2020-07-10 19:44:51','2020-07-10 19:44:51'),('1284771305584877569','1189389726308478977','1279681597221675009','1279681597016154114','图像挖掘',1.00,1,'https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-19/57c5694b9387d55afd54a78e6b2020-03-07_17-32-18.jpg',0,13,1,'Normal',NULL,'2020-07-19 16:45:24','2020-07-19 16:45:24'),('1284771789397843969','1189389726308478977','1279681597221675009','1279681597016154114','spark',1.00,1,'https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-19/93a161419e9b9095bf18e91add1561898715099.jpg',4,0,1,'Normal',NULL,'2020-07-19 16:47:19','2020-07-19 16:47:19'),('1284771912500666369','1189390295668469762','1279681597423001602','1279681597330726913','数学分析',1.00,2,'https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-19/a909154c1496f64fe583c2c7b91561898740828.jpg',6,4,1,'Normal',NULL,'2020-07-19 16:47:49','2020-07-19 16:47:49'),('1284772114309603329','1189389726308478977','1279681597423001602','1279681597330726913','数据预处理',1.00,1,'https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-19/d03e6c49d59d26ae00c55129ed1536469807698.jpg',1,0,1,'Normal',NULL,'2020-07-19 16:48:37','2020-07-19 16:48:37'),('1284772334292459522','1189390295668469762','1279681596248596482','1279681595988549634','机器学习',1.00,1,'https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-19/876dd345d08ca598d7f4d7c7451546342130746.jpg',8,5,1,'Normal',NULL,'2020-07-19 16:49:29','2020-07-19 16:49:29'),('1287758575103418370','1189426464967995393','1279681596433145858','1279681595988549634','大数据导论',50.00,5,'https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-27/6dd78d403ca0a3373fb7e35bdb7AEE835D0D277BD447F2191B0D689257.jpg',4,87,1,'Normal',NULL,'2020-07-27 22:35:44','2020-07-27 22:35:44'),('1287758817391583234','1189389726308478977','1279681597423001602','1279681597330726913','数学分析',7.00,10,'https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-27/7b1ce14654bb515d2d0b943b7004F1002EFC68AF282D65F4E2C4525A54.jpg',1,8,1,'Normal',NULL,'2020-07-27 22:36:42','2020-08-03 14:15:54'),('1287759022161698817','1189426437876985857','1279681596248596482','1279681595988549634','高等代数',90.00,64,'https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-27/61fd254e22bae29893e135a52f530813D02D26DDEB9B7ABFD401D0F401.jpg',9,9,1,'Normal',NULL,'2020-07-27 22:37:31','2020-07-27 22:37:31'),('1290021362886270977','1189426464967995393','1279681596248596482','1279681595988549634','test admin',0.00,4,'https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-08-03/63e8ca48c5bc9732879efd893b7F4B82B35278ACFFFC567DFBCD174A15.jpg',0,0,1,'Draft',NULL,'2020-08-03 04:27:15','2020-08-03 04:27:15'),('1290021890248695809','1192249914833055746','1279681596248596482','1279681595988549634','asfsadf',0.00,1,'https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-08-03/8a3efc48fcbd394d43ec7d21457F4B82B35278ACFFFC567DFBCD174A15.jpg',0,0,1,'Draft',NULL,'2020-08-03 04:29:21','2020-08-03 04:29:21'),('1290022627439566850','1','1279681596248596482','1279681595988549634','afasdfas',0.00,1,'https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-08-04/6cd4154b80a8615fc2912c7e3989FF96E22657129907D11159A9DB479A.jpg',0,0,1,'Normal',NULL,'2020-08-03 04:32:17','2020-08-04 01:54:05'),('1290023600224493569','1189426464967995393','1279681596143738882','1279681595988549634','13213asfd',0.00,1,'https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-08-04/285dd5402da6c5bbb88cbf1a519CBD20B3AD5780F2F72EA56CD8640693.jpg',0,0,1,'Normal',NULL,'2020-08-03 04:36:08','2020-08-04 01:51:33');
/*!40000 ALTER TABLE `edu_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edu_course_collect`
--

DROP TABLE IF EXISTS `edu_course_collect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `edu_course_collect` (
  `id` char(19) NOT NULL COMMENT '收藏ID',
  `course_id` char(19) NOT NULL COMMENT '课程讲师ID',
  `member_id` char(19) NOT NULL DEFAULT '' COMMENT '课程专业ID',
  `is_deleted` tinyint(3) NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='课程收藏';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edu_course_collect`
--

LOCK TABLES `edu_course_collect` WRITE;
/*!40000 ALTER TABLE `edu_course_collect` DISABLE KEYS */;
INSERT INTO `edu_course_collect` VALUES ('1196269345666019330','1192252213659774977','1',1,'2019-11-18 11:30:12','2019-11-18 11:30:12');
/*!40000 ALTER TABLE `edu_course_collect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edu_course_description`
--

DROP TABLE IF EXISTS `edu_course_description`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `edu_course_description` (
  `id` char(19) NOT NULL COMMENT '课程ID',
  `description` text COMMENT '课程简介',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_course` FOREIGN KEY (`id`) REFERENCES `edu_course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程简介';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edu_course_description`
--

LOCK TABLES `edu_course_description` WRITE;
/*!40000 ALTER TABLE `edu_course_description` DISABLE KEYS */;
INSERT INTO `edu_course_description` VALUES ('1281554975297667074','<p><strong>沙发撒飞洒法萨芬</strong></p>','2020-07-10 19:44:51','2020-07-10 19:44:51'),('1284771305584877569','<p>1</p>','2020-07-19 16:45:24','2020-07-19 16:45:24'),('1284771789397843969','<p>1</p>','2020-07-19 16:47:19','2020-07-19 16:47:19'),('1284771912500666369',NULL,'2020-07-19 16:47:49','2020-07-19 16:47:49'),('1284772114309603329','<p>1</p>','2020-07-19 16:48:37','2020-07-19 16:48:37'),('1284772334292459522','<p>1</p>','2020-07-19 16:49:29','2020-07-19 16:49:29'),('1287758575103418370','','2020-07-27 22:35:44','2020-07-27 22:35:44'),('1287758817391583234','','2020-07-27 22:36:42','2020-08-03 14:15:54'),('1287759022161698817','','2020-07-27 22:37:31','2020-07-27 22:37:31'),('1290021362886270977','<p>afasdf</p>','2020-08-03 04:27:15','2020-08-03 04:27:15'),('1290021890248695809','<p>asfsaf</p>','2020-08-03 04:29:21','2020-08-03 04:29:21'),('1290022627439566850','<p>1</p>','2020-08-03 04:32:17','2020-08-04 01:54:05'),('1290023600224493569','<p>1</p>','2020-08-03 04:36:08','2020-08-04 01:51:33');
/*!40000 ALTER TABLE `edu_course_description` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edu_subject`
--

DROP TABLE IF EXISTS `edu_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `edu_subject` (
  `id` char(19) NOT NULL COMMENT '课程类别ID',
  `title` varchar(10) NOT NULL COMMENT '类别名称',
  `parent_id` char(19) NOT NULL DEFAULT '0' COMMENT '父ID',
  `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '排序字段',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='课程科目';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edu_subject`
--

LOCK TABLES `edu_subject` WRITE;
/*!40000 ALTER TABLE `edu_subject` DISABLE KEYS */;
INSERT INTO `edu_subject` VALUES ('1279681595988549634','前端开发','0',0,'2020-07-05 15:40:42','2020-07-05 15:40:42'),('1279681596143738882','vue','1279681595988549634',0,'2020-07-05 15:40:43','2020-07-05 15:40:43'),('1279681596248596482','JavaScript','1279681595988549634',0,'2020-07-05 15:40:43','2020-07-05 15:40:43'),('1279681596433145858','jQuery','1279681595988549634',0,'2020-07-05 15:40:43','2020-07-05 15:40:43'),('1279681597016154114','后端开发','0',0,'2020-07-05 15:40:43','2020-07-05 15:40:43'),('1279681597121011713','JavaScript','1279681597016154114',0,'2020-07-05 15:40:43','2020-07-05 15:40:43'),('1279681597221675009','C++','1279681597016154114',0,'2020-07-05 15:40:43','2020-07-05 15:40:43'),('1279681597330726913','数据库开发','0',0,'2020-07-05 15:40:43','2020-07-05 15:40:43'),('1279681597423001602','MySQL','1279681597330726913',0,'2020-07-05 15:40:43','2020-07-05 15:40:43');
/*!40000 ALTER TABLE `edu_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edu_teacher`
--

DROP TABLE IF EXISTS `edu_teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `edu_teacher` (
  `id` char(19) NOT NULL COMMENT '讲师ID',
  `name` varchar(20) NOT NULL COMMENT '讲师姓名',
  `intro` varchar(500) NOT NULL DEFAULT '' COMMENT '讲师简介',
  `career` varchar(500) DEFAULT NULL COMMENT '讲师资历,一句话说明讲师',
  `level` int(10) unsigned NOT NULL COMMENT '头衔 1高级讲师 2首席讲师',
  `avatar` varchar(255) DEFAULT NULL COMMENT '讲师头像',
  `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='讲师';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edu_teacher`
--

LOCK TABLES `edu_teacher` WRITE;
/*!40000 ALTER TABLE `edu_teacher` DISABLE KEYS */;
INSERT INTO `edu_teacher` VALUES ('1','张三','近年主持国家自然科学基金（6项）、江苏省重大科技成果转化项目（5项）、江苏省产学研前瞻性联合研究项目（3项）、省工业科技支撑、省高技术、省自然科学基金等省部级及其企业的主要科研项目40多个，多个项目在企业成功转化，产生了较好的经济、社会和环境效益。积极开展产学研科技合作，并与省内16家企业建立了江苏省研究生工作站，其中6家为江苏省优秀研究生工作站','高级',1,'https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-19/93a161419e9b9095bf18e91add1561898715099.jpg',0,0,'2019-10-30 14:18:46','2019-11-12 13:36:36'),('1189389726308478977','晴天','高级讲师简介','高级讲师资历',2,'https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-19/93a161419e9b9095bf18e91add1561898715099.jpg',1,0,'2019-10-30 11:53:03','2019-10-30 11:53:03'),('1189390295668469762','李刚','高级讲师简介','高级讲师',2,'https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-19/93a161419e9b9095bf18e91add1561898715099.jpg',2,0,'2019-10-30 11:55:19','2019-11-12 13:37:52'),('1189426437876985857','王二','高级讲师简介','高级讲师',1,'https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-19/93a161419e9b9095bf18e91add1561898715099.jpg',0,0,'2019-10-30 14:18:56','2019-11-12 13:37:35'),('1189426464967995393','王五','高级讲师简介','高级讲师',1,'https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-19/93a161419e9b9095bf18e91add1561898715099.jpg',0,0,'2019-10-30 14:19:02','2019-11-12 13:37:18'),('1192249914833055746','李四','高级讲师简介','高级讲师',1,'https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-19/93a161419e9b9095bf18e91add1561898715099.jpg',0,0,'2019-11-07 09:18:25','2019-11-12 13:37:01'),('1192327476087115778','1222-12-12','1111','11',1,'https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-19/93a161419e9b9095bf18e91add1561898715099.jpg',0,0,'2019-11-07 14:26:37','2019-11-11 16:26:26'),('1195337453429129218','test','sdfsdf','sdfdf',1,'https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-19/93a161419e9b9095bf18e91add1561898715099.jpg',0,0,'2019-11-15 21:47:12','2019-11-15 21:47:27'),('1275862803609137153','jkl','string','string',0,'https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-19/93a161419e9b9095bf18e91add1561898715099.jpg',0,0,'2020-06-25 02:46:11','2020-06-25 02:46:11'),('1275863501776101377','string','string','string1',0,'https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-19/93a161419e9b9095bf18e91add1561898715099.jpg',0,0,'2020-06-25 02:48:58','2020-06-25 03:04:37'),('1278726605409177601','lhl','leeeee','hight school',1,'https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-19/93a161419e9b9095bf18e91add1561898715099.jpg',1,0,'2020-07-03 00:25:55','2020-07-09 20:40:08'),('1278728996690939905','小王八2','你是小王吗','小学',1,'https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-19/93a161419e9b9095bf18e91add1561898715099.jpg',1,0,'2020-07-03 00:35:25','2020-07-09 20:47:56'),('1278740941607358466','tom','tomcat','cat',2,'https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-19/93a161419e9b9095bf18e91add1561898715099.jpg',2,0,'2020-07-03 01:22:53','2020-07-03 01:22:53'),('1279316178556227586','测试头像','3222','11',1,'https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-19/93a161419e9b9095bf18e91add1561898715099.jpg',1,0,'2020-07-04 15:28:40','2020-07-09 20:49:09'),('1279481337434120193','大主教','经常集结部队','达拉姆第一人',2,'https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-19/93a161419e9b9095bf18e91add1561898715099.jpg',1,0,'2020-07-05 02:24:57','2020-07-05 02:24:57');
/*!40000 ALTER TABLE `edu_teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edu_video`
--

DROP TABLE IF EXISTS `edu_video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `edu_video` (
  `id` char(19) NOT NULL COMMENT '视频ID',
  `course_id` char(19) NOT NULL COMMENT '课程ID',
  `chapter_id` char(19) NOT NULL COMMENT '章节ID',
  `title` varchar(50) DEFAULT NULL COMMENT '节点名称',
  `video_source_id` varchar(100) DEFAULT NULL COMMENT '云端视频资源',
  `video_original_name` varchar(100) DEFAULT NULL COMMENT '原始文件名称',
  `sort` int(10) unsigned DEFAULT '0' COMMENT '排序字段',
  `play_count` bigint(20) unsigned DEFAULT '0' COMMENT '播放次数',
  `is_free` tinyint(1) unsigned DEFAULT '0' COMMENT '是否可以试听：0收费 1免费',
  `duration` float DEFAULT '0' COMMENT '视频时长（秒）',
  `status` varchar(20) DEFAULT 'Empty' COMMENT 'Empty未上传 Transcoding转码中  Normal正常',
  `size` bigint(20) unsigned DEFAULT '0' COMMENT '视频源文件大小（字节）',
  `version` bigint(20) unsigned DEFAULT '1' COMMENT '乐观锁',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_course_id` (`course_id`),
  KEY `idx_chapter_id` (`chapter_id`),
  CONSTRAINT `fk_chapter_id` FOREIGN KEY (`chapter_id`) REFERENCES `edu_chapter` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='课程视频';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edu_video`
--

LOCK TABLES `edu_video` WRITE;
/*!40000 ALTER TABLE `edu_video` DISABLE KEYS */;
INSERT INTO `edu_video` VALUES ('1281555155405275138','1281554975297667074','1281555036341567489','2.1','70f6801a55bf4983b15be32db5abadc6','测试1',1,0,0,0,'Empty',0,1,'2020-07-10 19:45:34','2020-07-10 19:45:34'),('1281555208295448577','1281554975297667074','1281555036341567489','2.2','70f6801a55bf4983b15be32db5abadc6','测试2',2,0,0,0,'Empty',0,1,'2020-07-10 19:45:46','2020-07-10 19:45:46');
/*!40000 ALTER TABLE `edu_video` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statistics_daily`
--

DROP TABLE IF EXISTS `statistics_daily`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `statistics_daily` (
  `id` char(19) NOT NULL COMMENT '主键',
  `date_calculated` varchar(20) NOT NULL COMMENT '统计日期',
  `register_num` int(11) NOT NULL DEFAULT '0' COMMENT '注册人数',
  `login_num` int(11) NOT NULL DEFAULT '0' COMMENT '登录人数',
  `video_view_num` int(11) NOT NULL DEFAULT '0' COMMENT '每日播放视频数',
  `course_num` int(11) NOT NULL DEFAULT '0' COMMENT '每日新增课程数',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `statistics_day` (`date_calculated`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网站统计日数据';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statistics_daily`
--

LOCK TABLES `statistics_daily` WRITE;
/*!40000 ALTER TABLE `statistics_daily` DISABLE KEYS */;
INSERT INTO `statistics_daily` VALUES ('1289198368379138049','2020-07-23',2,22,231,2333,'2020-07-31 21:56:58','2020-07-31 21:56:58'),('1289229335949025281','2020-07-31',3,123,45,2333,'2020-08-01 00:00:01','2020-08-01 00:00:01'),('1289229339883282434','2020-08-01',0,213,90,2333,'2020-08-01 00:00:02','2020-08-01 00:50:08'),('1290351849773309953','2020-08-04',0,0,0,0,'2020-08-04 02:20:29','2020-08-04 02:20:29');
/*!40000 ALTER TABLE `statistics_daily` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_order`
--

DROP TABLE IF EXISTS `t_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_order` (
  `id` char(19) NOT NULL DEFAULT '',
  `order_no` varchar(20) NOT NULL DEFAULT '' COMMENT '订单号',
  `course_id` varchar(19) NOT NULL DEFAULT '' COMMENT '课程id',
  `course_title` varchar(100) DEFAULT NULL COMMENT '课程名称',
  `course_cover` varchar(255) DEFAULT NULL COMMENT '课程封面',
  `teacher_name` varchar(20) DEFAULT NULL COMMENT '讲师名称',
  `member_id` varchar(19) NOT NULL DEFAULT '' COMMENT '会员id',
  `nickname` varchar(50) DEFAULT NULL COMMENT '会员昵称',
  `mobile` varchar(11) DEFAULT NULL COMMENT '会员手机',
  `total_fee` decimal(10,2) DEFAULT '0.01' COMMENT '订单金额（分）',
  `pay_type` tinyint(3) DEFAULT NULL COMMENT '支付类型（1：微信 2：支付宝）',
  `status` tinyint(3) DEFAULT NULL COMMENT '订单状态（0：未支付 1：已支付）',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_order_no` (`order_no`),
  KEY `idx_course_id` (`course_id`),
  KEY `idx_member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order`
--

LOCK TABLES `t_order` WRITE;
/*!40000 ALTER TABLE `t_order` DISABLE KEYS */;
INSERT INTO `t_order` VALUES ('1289081013800013826','20200731141037928','1287758817391583234','数学分析','https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-27/7b1ce14654bb515d2d0b943b7004F1002EFC68AF282D65F4E2C4525A54.jpg','晴天','1286227636506427393','lhl','13638325291',7.00,1,1,0,'2020-07-31 14:10:38','2020-07-31 14:10:38'),('1289081378742210562','20200731141205100','1287758817391583234','数学分析','https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-27/7b1ce14654bb515d2d0b943b7004F1002EFC68AF282D65F4E2C4525A54.jpg','晴天','1286227636506427393','lhl','13638325291',7.00,1,1,0,'2020-07-31 14:12:05','2020-07-31 14:12:05'),('1289081925230661634','20200731141415431','1287758817391583234','数学分析','https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-27/7b1ce14654bb515d2d0b943b7004F1002EFC68AF282D65F4E2C4525A54.jpg','晴天','1286227636506427393','lhl','13638325291',7.00,1,1,0,'2020-07-31 14:14:16','2020-07-31 14:14:16'),('1289082213329014786','20200731141524166','1287758817391583234','数学分析','https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-27/7b1ce14654bb515d2d0b943b7004F1002EFC68AF282D65F4E2C4525A54.jpg','晴天','1286227636506427393','lhl','13638325291',7.00,1,1,0,'2020-07-31 14:15:24','2020-07-31 14:15:24'),('1289099014431576065','20200731152209810','1287758817391583234','数学分析','https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-27/7b1ce14654bb515d2d0b943b7004F1002EFC68AF282D65F4E2C4525A54.jpg','晴天','1286227636506427393','lhl','13638325291',7.00,1,1,0,'2020-07-31 15:22:10','2020-07-31 15:22:10');
/*!40000 ALTER TABLE `t_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_pay_log`
--

DROP TABLE IF EXISTS `t_pay_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_pay_log` (
  `id` char(19) NOT NULL DEFAULT '',
  `order_no` varchar(20) NOT NULL DEFAULT '' COMMENT '订单号',
  `pay_time` datetime DEFAULT NULL COMMENT '支付完成时间',
  `total_fee` decimal(10,2) DEFAULT '0.01' COMMENT '支付金额（分）',
  `transaction_id` varchar(30) DEFAULT NULL COMMENT '交易流水号',
  `trade_state` char(20) DEFAULT NULL COMMENT '交易状态',
  `pay_type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '支付类型（1：微信 2：支付宝）',
  `attr` text COMMENT '其他属性',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_pay_log`
--

LOCK TABLES `t_pay_log` WRITE;
/*!40000 ALTER TABLE `t_pay_log` DISABLE KEYS */;
INSERT INTO `t_pay_log` VALUES ('1194498446013001730','1194498300579704832','2019-11-13 14:13:17',1.00,'4200000469201911130676624386','SUCCESS',1,'{\"transaction_id\":\"4200000469201911130676624386\",\"nonce_str\":\"2Lc23ILl231It53M\",\"trade_state\":\"SUCCESS\",\"bank_type\":\"CFT\",\"openid\":\"oNpSGwR-QGG5DaZtDkh2UZlsFDQE\",\"sign\":\"5404850AA3ED0E844DE104651477F07A\",\"return_msg\":\"OK\",\"fee_type\":\"CNY\",\"mch_id\":\"1473426802\",\"cash_fee\":\"1\",\"out_trade_no\":\"1194498300579704832\",\"cash_fee_type\":\"CNY\",\"appid\":\"wx8397f8696b538317\",\"total_fee\":\"1\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"result_code\":\"SUCCESS\",\"attach\":\"\",\"time_end\":\"20191113141314\",\"is_subscribe\":\"N\",\"return_code\":\"SUCCESS\"}',0,'2019-11-13 14:13:17','2019-11-13 14:13:17'),('1195253787449430017','1195253049260314624','2019-11-15 16:14:44',1.00,'4200000454201911150981874895','SUCCESS',1,'{\"transaction_id\":\"4200000454201911150981874895\",\"nonce_str\":\"MAM5UM4Xhv1lItvO\",\"trade_state\":\"SUCCESS\",\"bank_type\":\"CFT\",\"openid\":\"oNpSGwR-QGG5DaZtDkh2UZlsFDQE\",\"sign\":\"7DBDCAF4A078B30BB3EF073E6A238C20\",\"return_msg\":\"OK\",\"fee_type\":\"CNY\",\"mch_id\":\"1473426802\",\"cash_fee\":\"1\",\"out_trade_no\":\"1195253049260314624\",\"cash_fee_type\":\"CNY\",\"appid\":\"wx8397f8696b538317\",\"total_fee\":\"1\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"result_code\":\"SUCCESS\",\"attach\":\"\",\"time_end\":\"20191115161440\",\"is_subscribe\":\"N\",\"return_code\":\"SUCCESS\"}',0,'2019-11-15 16:14:44','2019-11-15 16:14:44'),('1196264321397342210','1196264005255872512','2019-11-18 11:10:14',1.00,'4200000453201911184025781554','SUCCESS',1,'{\"transaction_id\":\"4200000453201911184025781554\",\"nonce_str\":\"D1dHexCLIFIxAAg2\",\"trade_state\":\"SUCCESS\",\"bank_type\":\"CFT\",\"openid\":\"oNpSGwR-QGG5DaZtDkh2UZlsFDQE\",\"sign\":\"C9F5CA1EE49EA7891736D73BEB423962\",\"return_msg\":\"OK\",\"fee_type\":\"CNY\",\"mch_id\":\"1473426802\",\"cash_fee\":\"1\",\"out_trade_no\":\"1196264005255872512\",\"cash_fee_type\":\"CNY\",\"appid\":\"wx8397f8696b538317\",\"total_fee\":\"1\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"result_code\":\"SUCCESS\",\"attach\":\"\",\"time_end\":\"20191118111011\",\"is_subscribe\":\"N\",\"return_code\":\"SUCCESS\"}',0,'2019-11-18 11:10:14','2019-11-18 11:10:14');
/*!40000 ALTER TABLE `t_pay_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ucenter_member`
--

DROP TABLE IF EXISTS `ucenter_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ucenter_member` (
  `id` char(19) NOT NULL COMMENT '会员id',
  `openid` varchar(128) DEFAULT NULL COMMENT '微信openid',
  `mobile` varchar(11) DEFAULT '' COMMENT '手机号',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `sex` tinyint(2) unsigned DEFAULT NULL COMMENT '性别 1 女，2 男',
  `age` tinyint(3) unsigned DEFAULT NULL COMMENT '年龄',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `sign` varchar(100) DEFAULT NULL COMMENT '用户签名',
  `is_disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用 1（true）已禁用，  0（false）未禁用',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ucenter_member`
--

LOCK TABLES `ucenter_member` WRITE;
/*!40000 ALTER TABLE `ucenter_member` DISABLE KEYS */;
INSERT INTO `ucenter_member` VALUES ('1',NULL,'13700000001','96e79218965eb72c92a549dd5a330112','小三123',1,5,'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132',NULL,0,0,'2019-01-01 12:11:33','2019-11-08 11:56:01'),('1080736474267144193',NULL,'13700000011','96e79218965eb72c92a549dd5a330112','用户XJtDfaYeKk',1,19,'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132',NULL,0,0,'2019-01-02 12:12:45','2019-01-02 12:12:56'),('1080736474355224577',NULL,'13700000002','96e79218965eb72c92a549dd5a330112','用户wUrNkzAPrc',1,27,'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132',NULL,0,0,'2019-01-02 12:13:56','2019-01-02 12:14:07'),('1086387099449442306',NULL,'13520191388','96e79218965eb72c92a549dd5a330112','用户XTMUeHDAoj',2,20,'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132',NULL,0,0,'2019-01-19 06:17:23','2019-01-19 06:17:23'),('1086387099520745473',NULL,'13520191389','96e79218965eb72c92a549dd5a330112','用户vSdKeDlimn',1,21,'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132',NULL,0,0,'2019-01-19 06:17:23','2019-01-19 06:17:23'),('1086387099608825858',NULL,'13520191381','96e79218965eb72c92a549dd5a330112','用户EoyWUVXQoP',1,18,'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132',NULL,0,0,'2019-01-19 06:17:23','2019-01-19 06:17:23'),('1086387099701100545',NULL,'13520191382','96e79218965eb72c92a549dd5a330112','用户LcAYbxLNdN',2,24,'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132',NULL,0,0,'2019-01-19 06:17:23','2019-01-19 06:17:23'),('1086387099776598018',NULL,'13520191383','96e79218965eb72c92a549dd5a330112','用户dZdjcgltnk',2,25,'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132',NULL,0,0,'2019-01-19 06:17:23','2019-01-19 06:17:23'),('1086387099852095490',NULL,'13520191384','96e79218965eb72c92a549dd5a330112','用户wNHGHlxUwX',2,23,'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132',NULL,0,0,'2019-01-19 06:17:23','2019-01-19 06:17:23'),('1106746895272849410','o1R-t5u2TfEVeVjO9CPGdHPNw-to',NULL,NULL,'檀梵\'',NULL,NULL,'http://thirdwx.qlogo.cn/mmopen/vi_32/zZfLXcetf2Rpsibq6HbPUWKgWSJHtha9y1XBeaqluPUs6BYicW1FJaVqj7U3ozHd3iaodGKJOvY2PvqYTuCKwpyfQ/132',NULL,0,0,'2019-03-16 10:39:57','2019-03-16 10:39:57'),('1106822699956654081',NULL,NULL,NULL,NULL,NULL,NULL,'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132',NULL,0,0,'2019-03-16 15:41:10','2019-03-16 15:41:10'),('1106823035660357634','o1R-t5i4gENwHYRb5lVFy98Z0bdk',NULL,NULL,'GaoSir',NULL,NULL,'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJI53RcCuc1no02os6ZrattWGiazlPnicoZQ59zkS7phNdLEWUPDk8fzoxibAnXV1Sbx0trqXEsGhXPw/132',NULL,0,0,'2019-03-16 15:42:30','2019-03-16 15:42:30'),('1106823041599492098',NULL,NULL,NULL,NULL,NULL,NULL,'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132',NULL,0,0,'2019-03-16 15:42:32','2019-03-16 15:42:32'),('1106823115788341250','o1R-t5l_3rnbZbn4jWwFdy6Gk6cg',NULL,NULL,'换个网名哇、',NULL,NULL,'http://thirdwx.qlogo.cn/mmopen/vi_32/jJHyeM0EN2jhB70LntI3k8fEKe7W6CwykrKMgDJM4VZqCpcxibVibX397p0vmbKURGkLS4jxjGB0GpZfxCicgt07w/132',NULL,0,0,'2019-03-16 15:42:49','2019-03-16 15:42:49'),('1106826046730227714','o1R-t5gyxumyBqt0CWcnh0S6Ya1g',NULL,NULL,'我是Helen',NULL,NULL,'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKDRfib8wy7A2ltERKh4VygxdjVC1x5OaOb1t9hot4JNt5agwaVLdJLcD9vJCNcxkvQnlvLYIPfrZw/132',NULL,0,0,'2019-03-16 15:54:28','2019-03-16 15:54:28'),('1106828185829490690','o1R-t5nNlou5lRwBVgGNJFm4rbc4',NULL,NULL,' 虎头',NULL,NULL,'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKxCqRzuYWQmpwiaqQEjNxbC7WicebicXQusU306jgmfoOzUcFg1qaDq5BStiblwBjw5dUOblQ2gUicQOQ/132',NULL,0,0,'2019-03-16 16:02:58','2019-03-16 16:02:58'),('1106830599651442689','o1R-t5hZHQB1cbX7HZJsiM727_SA',NULL,NULL,'是吴啊',NULL,NULL,'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJ9CsqApybcs7f3Dyib9IxIh0sBqJb7LicbjU4WticJFF0PVwFvHgtbFdBwfmk3H2t3NyqmEmVx17tRA/132',NULL,0,0,'2019-03-16 16:12:34','2019-03-16 16:12:34'),('1106830976199278593','o1R-t5meKOoyEJ3-IhWRCBKFcvzU',NULL,NULL,'我才是Helen',NULL,NULL,'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83epMicP9UT6mVjYWdno0OJZkOXiajG0sllJTbGJ9DYiceej2XvbDSGCK8LCF7jv1PuG2uoYlePWic9XO8A/132',NULL,0,0,'2019-03-16 16:14:03','2019-03-16 16:14:03'),('1106831936900415490','o1R-t5jXYSWakGtnUBnKbfVT5Iok',NULL,NULL,'文若姬',NULL,NULL,'http://thirdwx.qlogo.cn/mmopen/vi_32/3HEmJwpSzguqqAyzmBwqT6aicIanswZibEOicQInQJI3ZY1qmu59icJC6N7SahKqWYv24GvX5KH2fibwt0mPWcTJ3fg/132',NULL,0,0,'2019-03-16 16:17:52','2019-03-16 16:17:52'),('1106832491064442882','o1R-t5sud081Qsa2Vb2xSKgGnf_g',NULL,NULL,'Peanut',NULL,NULL,'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132',NULL,0,0,'2019-03-16 16:20:04','2019-03-16 16:20:04'),('1106833021442510849','o1R-t5lsGc3I8P5bDpHj7m_AIRvQ',NULL,NULL,'食物链终结者',NULL,NULL,'http://thirdwx.qlogo.cn/mmopen/vi_32/MQ7qUmCprK9am16M1Ia1Cs3RK0qiarRrl9y8gsssBjIZeS2GwKSrnq7ZYhmrzuzDwBxSMMAofrXeLic9IBlW4M3Q/132',NULL,0,0,'2019-03-16 16:22:11','2019-03-16 16:22:11'),('1191600824445046786',NULL,'15210078344','96e79218965eb72c92a549dd5a330112','IT妖姬',1,5,'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132',NULL,0,0,'2020-07-31 14:19:10','2019-11-08 18:04:43'),('1191616288114163713',NULL,'17866603606','96e79218965eb72c92a549dd5a330112','xiaowu',NULL,NULL,'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132',NULL,0,0,'2020-07-31 15:20:37','2019-11-05 15:20:37'),('1195187659054329857',NULL,'15010546384','96e79218965eb72c92a549dd5a330112','qy',NULL,NULL,'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132',NULL,0,0,'2020-07-31 11:51:58','2019-11-15 11:51:58'),('1286190582271594498',NULL,'11447907582','96e79218965eb72c92a549dd5a330112','石头人',NULL,NULL,'https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-19/93a161419e9b9095bf18e91add1561898715099.jpg',NULL,0,0,'2020-07-23 14:45:06','2020-07-23 14:45:06'),('1286227636506427393',NULL,'13638325292','96e79218965eb72c92a549dd5a330112','lhl',NULL,NULL,'https://edu-avatar1.oss-cn-beijing.aliyuncs.com/2020-07-19/93a161419e9b9095bf18e91add1561898715099.jpg',NULL,0,0,'2020-07-23 17:12:20','2020-07-23 17:12:20'),('1287355873630445570','o3_SC51QGyZ0bQZGdTr9VfCjcJrY','',NULL,'llllq3',NULL,NULL,'http://thirdwx.qlogo.cn/mmopen/vi_32/RUWKiaVwia99CdL3YNDLNfOzfPgViaxCibcgNIXBy4XLzQtg8tA9DaHl01Uicv7GM1gvVmicWiaribKia9K92aAPcup2ZtA/132',NULL,0,0,'2020-07-26 19:55:33','2020-07-26 19:55:33');
/*!40000 ALTER TABLE `ucenter_member` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-04  2:37:28
