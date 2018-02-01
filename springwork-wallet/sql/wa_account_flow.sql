

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- wa_account_flow 资金流水表
-- ----------------------------
DROP TABLE IF EXISTS `wa_account_flow`;
CREATE TABLE `wa_account_flow` (
  `acc_flow` varchar(20) NOT NULL,
  `tran_flow` varchar(20) NOT NULL,
  `trans_date` varchar(8) ,
  `trans_time` varchar(6),
  `trans_item` varchar(8),
  `opp_item` varchar(8),
  `trans_accno` varchar(16),
  `opp_accno` varchar(16) ,
  `trans_amt` int,
  `opp_amt` int,
  `trans_bal` int,
  `bal_dir` varchar(2) NOT NULL,
  `flag` varchar(2)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
