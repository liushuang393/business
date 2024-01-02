-- CREATE  TABLE `肩書管理マスタ`
DROP
TABLE IF EXISTS `M_TITLE`;
CREATE
TABLE `M_TITLE` (
     `TITLE_ID` varchar(5) NOT NULL COMMENT '肩書ID'
,     `TITLE_NAME` varchar(20) NOT NULL COMMENT '肩書名'
,     `REMARKS` varchar(30) COMMENT '備考'
,     `CREATEUSERID` varchar(10) COMMENT '登録者'
,     `CREATETIME` timestamp COMMENT '登録日時'
,     `UPDATEUSERID` varchar(10) COMMENT '更新者'
,     `UPDATETIME` timestamp COMMENT '更新日時'
,CONSTRAINT PRIMARY KEY (`TITLE_ID`)

) ENGINE = InnoDB DEFAULT CHARSET=utf8;
