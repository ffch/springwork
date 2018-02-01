

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- wa_trans_flow 交易流水表
-- ----------------------------
DROP TABLE IF EXISTS `wa_trans_flow`;
CREATE TABLE `wa_trans_flow` (
  trans_flow     VARCHAR(20) not null,
  trans_date     VARCHAR(8) not null,
  trancode  	 VARCHAR(6) not null,
  trans_time  	 VARCHAR(6) not null,
  trans_acct     VARCHAR(16) not null,
  trans_item     VARCHAR(6) not null,
  opp_acct       VARCHAR(16),
  opp_item       VARCHAR(6),
  trans_amt      int default 0 not null,
  cancel_flag    CHAR(1) default '0' not null,
  refund_flag    CHAR(1) default '0' not null,
  tran_status    CHAR(1) default '1' not null,
  refund_total   int default 0 not null,
  ret_code       CHAR(8) not null,
  ret_remark     VARCHAR(64),
  order_no       CHAR(18),
  remark         VARCHAR(256),
  PRIMARY KEY (`trans_flow`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
