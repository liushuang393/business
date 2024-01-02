-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: business
-- ------------------------------------------------------
-- Server version	5.6.24

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
-- Table structure for table `b_detailed_item_information`
--

DROP TABLE IF EXISTS `b_detailed_item_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `b_detailed_item_information` (
  `COMPANY_ID` varchar(32) NOT NULL COMMENT '案件ID:案件ID',
  `COMPANY_NAME` varchar(60) NOT NULL COMMENT '案件名:案件名',
  `SALES_ID` varchar(10) NOT NULL COMMENT '営業担当ID:営業担当ID',
  `PUBLIC_FLAG` char(1) NOT NULL DEFAULT '0' COMMENT '公開Flag:公開Flag０：非公開;１：公開',
  `WORK_PERIOD_START` date NOT NULL COMMENT '作業期間_開始:資本金',
  `WORK_PERIOD_END` date DEFAULT NULL COMMENT '作業期間_終了:資本金(単位)',
  `WORK_PLACE` varchar(150) DEFAULT NULL COMMENT '作業場所:作業場所',
  `BUSINESS` varchar(20) DEFAULT NULL COMMENT '業務分野:業務',
  `WORK_ENGINEERING` varchar(50) NOT NULL COMMENT '作業工程:作業工程',
  `TECHNOLOGY` varchar(60) NOT NULL COMMENT '技術:技術',
  `JAPANESE_LEVEL_REQUEST` varchar(30) NOT NULL COMMENT '日本語レベル要求:日本語レベル要求',
  `EXPERIENCE_YEARS` char(2) DEFAULT NULL COMMENT '経験年数:経験年数',
  `HOPE_UNIT_PRICE` decimal(10,0) DEFAULT NULL COMMENT '希望単価:希望単価',
  `INTERVIEW_TIMES` char(1) NOT NULL COMMENT '面談回数:面談回数',
  `PAYOFF` varchar(20) DEFAULT NULL COMMENT '精算:精算',
  `REMARKS` varchar(250) DEFAULT NULL COMMENT '備考:備考',
  `CREATEUSERID` varchar(10) NOT NULL DEFAULT 'systemId' COMMENT '登録者:登録者',
  `CREATETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日時:登録日時',
  `UPDATEUSERID` varchar(10) NOT NULL DEFAULT 'systemId' COMMENT '更新者:更新者',
  `UPDATETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日時:更新日時',
  PRIMARY KEY (`COMPANY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='案件詳細情報:';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `b_detailed_item_information`
--

LOCK TABLES `b_detailed_item_information` WRITE;
/*!40000 ALTER TABLE `b_detailed_item_information` DISABLE KEYS */;
/*!40000 ALTER TABLE `b_detailed_item_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `b_user`
--

DROP TABLE IF EXISTS `b_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `b_user` (
  `USER_ID` varchar(15) NOT NULL COMMENT '営業ユーザーID:営業ユーザーID',
  `USER_NAME` varchar(20) NOT NULL COMMENT 'ユーザー名:ユーザー名',
  `USERNAME_FURIKANA` varchar(40) DEFAULT NULL COMMENT 'ユーザー名（フリガナ）:ユーザー名（フリガナ）',
  `PASSWORD` varchar(30) NOT NULL COMMENT 'パスワード:パスワード',
  `CELLPHONE_NUMBER` varchar(15) DEFAULT NULL COMMENT '携帯番号:携帯番号',
  `EMAIL` varchar(180) DEFAULT NULL COMMENT 'メール:メール',
  `WE_CHAT` varchar(30) DEFAULT NULL COMMENT 'WeChat:WeChat',
  `CREATEUSERID` varchar(10) NOT NULL DEFAULT 'systemId' COMMENT '登録者:登録者',
  `CREATETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日時:登録日時',
  `UPDATEUSERID` varchar(10) NOT NULL DEFAULT 'systemId' COMMENT '更新者:更新者',
  `UPDATETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日時:更新日時',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ログインユーザー情報:';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `b_user`
--

LOCK TABLES `b_user` WRITE;
/*!40000 ALTER TABLE `b_user` DISABLE KEYS */;
INSERT INTO `b_user` VALUES ('liushuang393','劉双',NULL,'liushuang393',NULL,'liushuang@mitumaru.co.jp',NULL,'systemId','2019-12-28 22:44:27','systemId','2019-12-28 22:44:27'),('liushuang3932','liushuang',NULL,'liushuang393',NULL,'liushuang393',NULL,'systemId','2021-04-09 21:32:42','systemId','2021-04-09 21:32:42');
/*!40000 ALTER TABLE `b_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `b_vip_info`
--

DROP TABLE IF EXISTS `b_vip_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `b_vip_info` (
  `USER_ID` varchar(10) NOT NULL COMMENT '要員ユーザーID:要員ユーザーID',
  `USER_NAME` varchar(256) NOT NULL COMMENT 'ユーザー名:ユーザー名',
  `USERNAME_FURIKANA` varchar(512) DEFAULT NULL COMMENT 'ユーザー名（フリガナ）:ユーザー名（フリガナ）',
  `PUBLIC_FLAG` varchar(1) NOT NULL DEFAULT '0' COMMENT '公開Flag:公開Flag０：非公開;１：公開',
  `GENDER` varchar(2) NOT NULL COMMENT '性別:性別',
  `BIRTHDAY` date NOT NULL COMMENT '生年月日:生年月日',
  `MAJOR` varchar(30) DEFAULT NULL COMMENT '専 攻:専 攻',
  `RECENTLY_STATION` varchar(20) DEFAULT NULL COMMENT '最近駅:最近駅',
  `JAPAN_DATE` date DEFAULT NULL COMMENT '来日日付:来日日付',
  `OPERATION_START_DATE` date NOT NULL COMMENT '稼働開始日:稼働開始日',
  `CONTRACT_MATURITY` date DEFAULT NULL COMMENT '契約満期:契約満期',
  `JAPANESE_LEVEL` varchar(5) NOT NULL COMMENT '日本語レベル:日本語レベル',
  `GOOD_LANGUAGE` varchar(5) NOT NULL COMMENT '得意言語:得意言語',
  `GOOD_BUSINESS_DIVISION` varchar(5) NOT NULL COMMENT '得意業務分野:得意業務分野',
  `REMARKS` varchar(250) NOT NULL COMMENT '備考:備考',
  `STAR_RATING` int(11) DEFAULT NULL COMMENT '星評価:星評価(0~10)',
  `HISTORY_PATH` varchar(256) DEFAULT NULL COMMENT '履歴パス:履歴パス',
  `CREATEUSERID` varchar(10) NOT NULL DEFAULT 'systemId' COMMENT '登録者:登録者',
  `CREATETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日時:登録日時',
  `UPDATEUSERID` varchar(10) NOT NULL DEFAULT 'systemId' COMMENT '更新者:更新者',
  `UPDATETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日時:更新日時',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='要員情報:';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `b_vip_info`
--

LOCK TABLES `b_vip_info` WRITE;
/*!40000 ALTER TABLE `b_vip_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `b_vip_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `batch_job_instance`
--

DROP TABLE IF EXISTS `batch_job_instance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `batch_job_instance` (
  `JOB_INSTANCE_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `JOB_NAME` varchar(100) NOT NULL,
  `JOB_KEY` varchar(2500) DEFAULT NULL,
  PRIMARY KEY (`JOB_INSTANCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `batch_job_instance`
--

LOCK TABLES `batch_job_instance` WRITE;
/*!40000 ALTER TABLE `batch_job_instance` DISABLE KEYS */;
/*!40000 ALTER TABLE `batch_job_instance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_company`
--

DROP TABLE IF EXISTS `m_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_company` (
  `COMPANY_ID` varchar(5) NOT NULL COMMENT '会社ID:部署管理ID',
  `COMPANY_NAME` varchar(20) NOT NULL COMMENT '会社名:部署管理名',
  `ESTABLISHMENT_DAY` date DEFAULT NULL COMMENT '設立日:設立日',
  `CAPITAL` varchar(20) DEFAULT NULL COMMENT '資本金:資本金',
  `CAPITAL_UNIT` varchar(10) DEFAULT NULL COMMENT '資本金(単位):資本金(単位)',
  `SALES_ID` varchar(10) NOT NULL COMMENT '営業担当ID:営業担当ID',
  `SALES_PERSON_NAME` varchar(30) NOT NULL COMMENT '営業担当名:営業担当名',
  `HOME_PAGE` varchar(150) DEFAULT NULL COMMENT 'ホームページ:ホームページ',
  `E_MAIL` varchar(30) DEFAULT NULL COMMENT 'メール:メール',
  `TEL` varchar(15) DEFAULT NULL COMMENT '電話番号:電話番号',
  `FAX` varchar(15) DEFAULT NULL COMMENT 'FAX:FAX',
  `CREATEUSERID` varchar(10) NOT NULL DEFAULT 'systemId' COMMENT '登録者:登録者',
  `CREATETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日時:登録日時',
  `UPDATEUSERID` varchar(10) NOT NULL DEFAULT 'systemId' COMMENT '更新者:更新者',
  `UPDATETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日時:更新日時',
  PRIMARY KEY (`COMPANY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会社マスタ:';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_company`
--

LOCK TABLES `m_company` WRITE;
/*!40000 ALTER TABLE `m_company` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_department`
--

DROP TABLE IF EXISTS `m_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_department` (
  `DEPARTMENT_ID` varchar(5) NOT NULL COMMENT '部署管理ID:部署管理ID',
  `DEPARTMENT_NAME` varchar(20) NOT NULL COMMENT '部署管理名:部署管理名',
  `RESPONSIBLE_PERSON` varchar(15) DEFAULT NULL COMMENT '責任者:責任者',
  `ESTABLISHMENT_DAY` date DEFAULT NULL COMMENT '設立日:設立日',
  `REMARKS` varchar(30) DEFAULT NULL COMMENT '備考:備考',
  `CREATEUSERID` varchar(10) NOT NULL DEFAULT 'systemId' COMMENT '登録者:登録者',
  `CREATETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日時:登録日時',
  `UPDATEUSERID` varchar(10) NOT NULL DEFAULT 'systemId' COMMENT '更新者:更新者',
  `UPDATETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日時:更新日時',
  PRIMARY KEY (`DEPARTMENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部署マスタ:';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_department`
--

LOCK TABLES `m_department` WRITE;
/*!40000 ALTER TABLE `m_department` DISABLE KEYS */;
INSERT INTO `m_department` VALUES ('1','開発一部','開発一部','2019-02-10','','systemId','2019-02-10 02:02:10','systemId','2019-02-10 02:02:10'),('123','部署名','責任者','2021-08-01',NULL,'lius393','2019-02-10 06:22:35','liushuang','2019-04-06 22:06:41'),('2','開発二部','開発二部','2019-02-10','','systemId','2019-02-10 02:02:10','liushuang','2019-04-27 21:32:21'),('3','開発三部','開発三部','2019-02-10','','systemId','2019-02-10 02:02:10','lius393','2019-02-10 05:47:50'),('4','管理本部','管理本部','2019-02-10','','systemId','2019-02-10 02:02:10','lius393','2019-02-10 06:10:41'),('5','事業推進部','事業推進部','2019-02-10','','systemId','2019-02-10 02:02:10','lius393','2019-02-10 06:33:50'),('6','営業部','o','2019-02-19','','systemId','2019-02-10 02:02:10','lius393','2019-02-10 06:03:53'),('7','人事部','人事部','2019-05-13','','systemId','2019-02-10 02:02:10','劉双','2021-04-28 18:39:09');
/*!40000 ALTER TABLE `m_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_position`
--

DROP TABLE IF EXISTS `m_position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_position` (
  `POSITION_ID` varchar(5) NOT NULL COMMENT '役職管理ID:役職管理ID',
  `POSITION_NAME` varchar(20) NOT NULL COMMENT '役職管理名:役職管理名',
  `REMARKS` varchar(30) DEFAULT NULL COMMENT '備考',
  `CREATEUSERID` varchar(10) NOT NULL DEFAULT 'systemId' COMMENT '登録者:登録者',
  `CREATETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日時:登録日時',
  `UPDATEUSERID` varchar(10) NOT NULL DEFAULT 'systemId' COMMENT '更新者:更新者',
  `UPDATETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日時:更新日時',
  PRIMARY KEY (`POSITION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='役職マスタ:';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_position`
--

LOCK TABLES `m_position` WRITE;
/*!40000 ALTER TABLE `m_position` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_roll`
--

DROP TABLE IF EXISTS `m_roll`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_roll` (
  `ROLL_ID` varchar(5) NOT NULL COMMENT 'ロールID',
  `ROLL_NAME` varchar(20) NOT NULL COMMENT 'ロール名',
  `USER_ID` varchar(10) NOT NULL COMMENT '営業ユーザーID',
  `FUNCTION_ID` varchar(10) DEFAULT NULL COMMENT '機能ID',
  `REMARKS` varchar(30) DEFAULT NULL COMMENT '備考',
  `CREATEUSERID` varchar(10) NOT NULL DEFAULT 'systemId' COMMENT '登録者:登録者',
  `CREATETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日時:登録日時',
  `UPDATEUSERID` varchar(10) NOT NULL DEFAULT 'systemId' COMMENT '更新者:更新者',
  `UPDATETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日時:更新日時',
  PRIMARY KEY (`ROLL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='権限:';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_roll`
--

LOCK TABLES `m_roll` WRITE;
/*!40000 ALTER TABLE `m_roll` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_roll` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_title`
--

DROP TABLE IF EXISTS `m_title`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_title` (
  `TITLE_ID` varchar(5) NOT NULL COMMENT '肩書ID:肩書ID',
  `TITLE_NAME` varchar(20) NOT NULL COMMENT '肩書名:肩書名',
  `REMARKS` varchar(30) DEFAULT NULL COMMENT '備考:備考',
  `CREATEUSERID` varchar(10) NOT NULL DEFAULT 'systemId' COMMENT '登録者:登録者',
  `CREATETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日時:登録日時',
  `UPDATEUSERID` varchar(10) NOT NULL DEFAULT 'systemId' COMMENT '更新者:更新者',
  `UPDATETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日時:更新日時',
  PRIMARY KEY (`TITLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='肩書:';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_title`
--

LOCK TABLES `m_title` WRITE;
/*!40000 ALTER TABLE `m_title` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_title` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_info`
--

DROP TABLE IF EXISTS `message_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_info` (
  `MESSAGE_ID` varchar(32) NOT NULL COMMENT '通知メッセージID:通知メッセージID',
  `USER_ID` varchar(15) NOT NULL COMMENT '営業ユーザーID:営業ユーザーID',
  `MESSAGE_TITLE` varchar(30) DEFAULT NULL COMMENT '通知メッセージ題名:通知メッセージ',
  `MESSAGE_BODY` varchar(200) NOT NULL COMMENT '通知メッセージ内容:通知メッセージ内容',
  `CNT` int(11) DEFAULT NULL COMMENT '通知回数:通知回数(<=5)',
  `CREATEUSERID` varchar(10) NOT NULL DEFAULT 'systemId' COMMENT '登録者:登録者',
  `CREATETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日時:登録日時',
  `UPDATEUSERID` varchar(10) NOT NULL DEFAULT 'systemId' COMMENT '更新者:更新者',
  `UPDATETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日時:更新日時',
  PRIMARY KEY (`MESSAGE_ID`,`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通知メッセージ:';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_info`
--

LOCK TABLES `message_info` WRITE;
/*!40000 ALTER TABLE `message_info` DISABLE KEYS */;
INSERT INTO `message_info` VALUES ('000000000000001','liushuang393','通知メッセージ題名','XXX申請を承認お願いいたします。',NULL,'liushuang3','2021-05-01 03:24:19','liushuang3','2021-05-01 03:24:19');
/*!40000 ALTER TABLE `message_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personnel_issues_related`
--

DROP TABLE IF EXISTS `personnel_issues_related`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personnel_issues_related` (
  `ID` varchar(32) NOT NULL COMMENT '関連ID',
  `ROLL_NAME` varchar(32) NOT NULL COMMENT '案件ID',
  `FUNCTION_ID` varchar(32) NOT NULL COMMENT '要員ID',
  `CREATEUSERID` varchar(10) NOT NULL DEFAULT 'systemId' COMMENT '登録者:登録者',
  `CREATETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日時:登録日時',
  `UPDATEUSERID` varchar(10) NOT NULL DEFAULT 'systemId' COMMENT '更新者:更新者',
  `UPDATETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日時:更新日時',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='案件と要員関連:';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personnel_issues_related`
--

LOCK TABLES `personnel_issues_related` WRITE;
/*!40000 ALTER TABLE `personnel_issues_related` DISABLE KEYS */;
/*!40000 ALTER TABLE `personnel_issues_related` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-28 14:52:38
