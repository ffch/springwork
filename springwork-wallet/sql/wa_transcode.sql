

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- wa_frz_reg 冻结信息表
-- ----------------------------
DROP TABLE IF EXISTS `wa_transcode`;
CREATE TABLE `wa_transcode` (
  trans_code      VARCHAR(6) not null,
  trans_name      VARCHAR(16) not null,
  remark          VARCHAR(64),
  flag   		  VARCHAR(2)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
