

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- wa_frz_reg 冻结信息表
-- ----------------------------
DROP TABLE IF EXISTS `wa_errcode`;
CREATE TABLE `wa_errcode` (
 err_name      VARCHAR(16) not null,
 err_code      VARCHAR(8) not null,
 err_msg       VARCHAR(64) not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
