

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- wa_frz_reg 冻结信息表
-- ----------------------------
DROP TABLE IF EXISTS `wa_frz_reg`;
CREATE TABLE `wa_frz_reg` (
  seq_no         VARCHAR(20) not null,
  acc_date       VARCHAR(8) not null,
  acc_no         VARCHAR(16) not null,
  frz_no         VARCHAR(18) not null,
  frz_type       VARCHAR(1) not null,
  frz_amt   	 int not null,
  frz_date       VARCHAR(8) not null,
  frz_operiod    int not null,
  unfrz_date     VARCHAR(8),
  remark         VARCHAR(256),
  frz_status     VARCHAR(1) not null,
  expired_date   VARCHAR(14)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
