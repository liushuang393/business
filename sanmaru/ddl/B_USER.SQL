-- CREATE  TABLE `ユーザーマスタ`
DROP
TABLE IF EXISTS `B_USER`;
CREATE
TABLE `B_USER` (
      `USER_ID` varchar(10) NOT NULL COMMENT 'ユーザーID(社員番号)'
     ,`USER_NAME` varchar(20) NOT NULL COMMENT 'ユーザー名'
     ,`USERNAME_FURIKANA` varchar(40) COMMENT 'ユーザー名（フリガナ）'
     ,`PASSWORD` varchar(20) NOT NULL COMMENT 'パスワード'
     ,`GENDER` varchar(2) COMMENT '性別'
     ,`BIRTHDAY` date NOT NULL COMMENT '生年月日'
     ,`AGE` int(3) DEFAULT 0 COMMENT '年齢'
     ,`GRADUATION` varchar(10) COMMENT '最終学歴'
     ,`MAJOR` varchar(30) COMMENT '専 攻'
     ,`GRADUATION_YEAR` varchar(4) COMMENT '卒業年次'
     ,`UNIVERSITY` varchar(50) COMMENT '卒業大学'
     ,`JOINED_DATE` date COMMENT '入社期日'
     ,`DEPARTMENT` int(2) COMMENT '所属部門'
     ,`POST` varchar(20) COMMENT '職種'
     ,`TITLE` varchar(20) COMMENT 'タイトル'
     ,`RECENTLY_STATION` varchar(20) COMMENT '最近駅'
     ,`LENGTH_OF_STAY` int(2) COMMENT '滞在期間'
     ,`JAPAN_DATE` date COMMENT '来日日付'
     ,`CONTRACT_TYPE` int(2) COMMENT '契約形式'
     ,`INSURANCE_RELATIONSHIP` int(2) COMMENT '保険関係'
     ,`BIZA_MATURITY` date COMMENT 'ビーザ満期'
     ,`PASSPORT_MATURITY` date COMMENT 'パスポート満期'
     ,`CELLPHONE_NUMBER` varchar(15) COMMENT '携帯番号'
     ,`EMAIL` varchar(50) COMMENT 'メール'
     ,`POSTAL_CODE` varchar(8) COMMENT '郵便番号'
     ,`ADDRESS` varchar(100) COMMENT '住所'
     ,`CONTRACT_MATURITY` date COMMENT '契約満期'
     ,`AUTHORITY_ID` int(2) COMMENT '権限ID'
     ,`CREATEUSERID` varchar(10) COMMENT '登録者'
     ,`CREATETIME` timestamp COMMENT '登録日時'
     ,`UPDATEUSERID` varchar(10) COMMENT '更新者'
     ,`UPDATETIME` timestamp COMMENT '更新日時'
     ,CONSTRAINT PRIMARY KEY (`USER_ID`)
-- ,CONSTRAINT `user_ibfk_departmentId` foreign key (departmentId) references department(departmentId)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;