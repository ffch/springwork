/*
Navicat MySQL Data Transfer

Source Server         : 阿里云mysql
Source Server Version : 50718
Source Host           : rm-uf664wu2lfctsrtczo.mysql.rds.aliyuncs.com:3306
Source Database       : cff

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-12-26 13:44:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- wa_item 科目表
-- ----------------------------
DROP TABLE IF EXISTS `wa_item`;
CREATE TABLE `wa_item` (
  `acc_no` varchar(20) NOT NULL,
  `user_no` varchar(16) NOT NULL,
  `item_no` varchar(12) ,
  `product_no` varchar(4),
  `bal` int NOT NULL,
  `passwd` varchar(64),
  `frz_bal` int,
  `loan_bal` int,
  `ava_bal` int NOT NULL,
  `frz_flag` varchar(2),
  `lock_flag` varchar(2),
  `bal_dir` varchar(2) NOT NULL,
  `dac` varchar(16) ,
  `open_time` varchar(14) ,
  `close_time` varchar(14),
  `last_time` varchar(14) ,
  `status` varchar(2) NOT NULL,
  PRIMARY KEY (`acc_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
