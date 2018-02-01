
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- wa_open_reg 开户登记表
-- ----------------------------
DROP TABLE IF EXISTS `wa_open_reg`;
CREATE TABLE `wa_open_reg` (
  seq_no     VARCHAR(20) not null,
  acc_date   CHAR(8) not null,
  tran_time  VARCHAR(14) ,
  acc_type   VARCHAR(3) not null,
  user_no    VARCHAR(19) not null,
  item_no    VARCHAR(8) not null,
  acc_no     VARCHAR(16) not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
