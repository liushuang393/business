-- CREATE  TABLE `ロールマスタ`
DROP
TABLE IF EXISTS `M_ROLL`;
CREATE
TABLE `M_ROLL` (
     `ROLL_ID` varchar(5) NOT NULL COMMENT 'ロールID'
,     `ROLL_NAME` varchar(20) NOT NULL COMMENT 'ロール名'
,     `REMARKS` varchar(30) COMMENT '備考'
,     `CREATEUSERID` varchar(10) COMMENT '登録者'
,     `CREATETIME` timestamp COMMENT '登録日時'
,     `UPDATEUSERID` varchar(10) COMMENT '更新者'
,     `UPDATETIME` timestamp COMMENT '更新日時'
,CONSTRAINT PRIMARY KEY (`ROLL_ID`)

) ENGINE = InnoDB DEFAULT CHARSET=utf8;
