

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- wa_liq_account 系统交易账户
-- ----------------------------
DROP TABLE IF EXISTS `wa_liq_account`;
CREATE TABLE `wa_liq_account` (
  item_no     VARCHAR(6) not null,
  liq_level   VARCHAR(1) not null,
  up_itemno   VARCHAR(6) not null,
  uliq_acctno VARCHAR(16) not null,
  lliq_acctno VARCHAR(16) not null,
  PRIMARY KEY (`item_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
