

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for se_app_user
-- ----------------------------
DROP TABLE IF EXISTS `se_user_bind`;
CREATE TABLE `se_user_bind` (
  `user_no` varchar(16) NOT NULL,
  `name` varchar(40) ,
  `sex` varchar(2)  ,
  `cert_no` varchar(4) ,
  `has_account` varchar(1) ,
  `acc_no` varchar(16) ,
  PRIMARY KEY (`user_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
