
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- wa_lock_reg 锁定信息表
-- ----------------------------
DROP TABLE IF EXISTS `wa_lock_reg`;
CREATE TABLE `wa_lock_reg` (
  `acc_no` varchar(20) NOT NULL,
  `user_no` varchar(16) NOT NULL,
  `item_no` varchar(12) ,
  `product_no` varchar(4),
  `bal` int NOT NULL,
  `passwd` varchar(64),
  `frz_bal` int,
  `loan_bal` int,
  `ava_bal` int NOT NULL,
  `frz_flag` varchar(2),
  `lock_flag` varchar(2),
  `bal_dir` varchar(2) NOT NULL,
  `dac` varchar(16) ,
  `open_time` varchar(14) ,
  `close_time` varchar(14),
  `last_time` varchar(14) ,
  `status` varchar(2) NOT NULL,
  PRIMARY KEY (`acc_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
