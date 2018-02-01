

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- wa_product 产品信息表
-- ----------------------------
DROP TABLE IF EXISTS `wa_product`;
CREATE TABLE `wa_product` (
  product_no      VARCHAR(3) not null,
  item_no         VARCHAR(8) not null,
  prduct_name     VARCHAR(256) not null,
  product_curr    VARCHAR(3) not null,
  product_type    CHAR(1) not null,
  is_repeat       CHAR(1) COMMENT '是否允许同一用户有多账户（0. 否1.是）',
  min_draw_amt    int,
  max_draw_amt    int,
  tran_mode       VARCHAR(3) COMMENT '交易方式（账户内互转）',
  lst_mdfdate     CHAR(8) not null,
  valid_flag      CHAR(1) not null,
  PRIMARY KEY (`product_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
