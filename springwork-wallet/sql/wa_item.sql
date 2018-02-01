

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- wa_item 科目表
-- ----------------------------
DROP TABLE IF EXISTS `wa_item`;
CREATE TABLE `wa_item` (
  `item_no` varchar(8) NOT NULL,
  `item_name` varchar(64) NOT NULL,
  `total_item` varchar(8) ,
  `acct_type` varchar(2),
  `bal_dir` varchar(2),
  `valid_flag` varchar(2),
  PRIMARY KEY (`item_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
