
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- wa_lock_reg 锁定信息表
-- ----------------------------
DROP TABLE IF EXISTS `wa_lock_reg`;
CREATE TABLE `wa_lock_reg` (
  seq_no         VARCHAR(20) not null,
  acc_date       VARCHAR(8) not null,
  acc_no         VARCHAR(16) not null,
  lock_no         VARCHAR(18) not null,
  lock_flag       VARCHAR(1) not null,
  lock_date       VARCHAR(8) not null,
  lock_operiod    int not null,
  unlock_date     VARCHAR(8),
  remark         VARCHAR(256),
  lock_status     VARCHAR(1) not null,
  expired_date   VARCHAR(14)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
