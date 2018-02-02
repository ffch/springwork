/*
Navicat MySQL Data Transfer

Source Server         : 阿里云mysql
Source Server Version : 50718
Source Host           : rm-uf664wu2lfctsrtczo.mysql.rds.aliyuncs.com:3306
Source Database       : cff

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-02-01 18:14:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for wa_account
-- ----------------------------
DROP TABLE IF EXISTS `wa_account`;
CREATE TABLE `wa_account` (
  `acc_no` varchar(16) NOT NULL,
  `user_no` varchar(16) NOT NULL,
  `acc_type` varchar(3) NOT NULL,
  `item_no` varchar(8) DEFAULT NULL,
  `product_no` varchar(4) DEFAULT NULL,
  `bal` int(11) NOT NULL DEFAULT '0',
  `passwd` varchar(64) DEFAULT NULL,
  `frz_bal` int(11) DEFAULT '0',
  `loan_bal` int(11) DEFAULT '0',
  `ava_bal` int(11) NOT NULL DEFAULT '0',
  `frz_flag` varchar(2) DEFAULT '0' COMMENT '0：正常 1：冻结',
  `lock_flag` varchar(2) DEFAULT '0' COMMENT '0：正常 1：锁定',
  `bal_dir` varchar(2) NOT NULL COMMENT '余额方向（0.双方 1：借 2：贷',
  `dac` varchar(16) DEFAULT NULL,
  `open_time` varchar(14) DEFAULT NULL,
  `close_time` varchar(14) DEFAULT NULL,
  `last_time` varchar(14) DEFAULT NULL,
  `status` varchar(2) NOT NULL COMMENT '0：正常 1：未激活 2：销户',
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`acc_no`,`acc_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wa_account_flow
-- ----------------------------
DROP TABLE IF EXISTS `wa_account_flow`;
CREATE TABLE `wa_account_flow` (
  `acc_flow` varchar(20) NOT NULL,
  `tran_flow` varchar(20) NOT NULL,
  `trans_date` varchar(8) DEFAULT NULL,
  `trans_time` varchar(6) DEFAULT NULL,
  `trans_item` varchar(8) DEFAULT NULL,
  `opp_item` varchar(8) DEFAULT NULL,
  `trans_accno` varchar(16) DEFAULT NULL,
  `opp_accno` varchar(16) DEFAULT NULL,
  `trans_amt` int(11) DEFAULT NULL,
  `opp_amt` int(11) DEFAULT NULL,
  `trans_bal` int(11) DEFAULT NULL,
  `bal_dir` varchar(2) NOT NULL,
  `flag` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wa_errcode
-- ----------------------------
DROP TABLE IF EXISTS `wa_errcode`;
CREATE TABLE `wa_errcode` (
  `err_name` varchar(16) NOT NULL,
  `err_code` varchar(8) NOT NULL,
  `err_msg` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wa_frz_reg
-- ----------------------------
DROP TABLE IF EXISTS `wa_frz_reg`;
CREATE TABLE `wa_frz_reg` (
  `seq_no` varchar(20) NOT NULL,
  `acc_date` varchar(8) NOT NULL,
  `acc_no` varchar(16) NOT NULL,
  `frz_no` varchar(18) NOT NULL,
  `frz_type` varchar(1) NOT NULL,
  `frz_amt` int(11) NOT NULL,
  `frz_date` varchar(8) NOT NULL,
  `frz_operiod` int(11) NOT NULL,
  `unfrz_date` varchar(8) DEFAULT NULL,
  `remark` varchar(256) DEFAULT NULL,
  `frz_status` varchar(1) NOT NULL,
  `expired_date` varchar(14) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wa_item
-- ----------------------------
DROP TABLE IF EXISTS `wa_item`;
CREATE TABLE `wa_item` (
  `item_no` varchar(8) NOT NULL,
  `item_name` varchar(64) NOT NULL,
  `total_item` varchar(8) DEFAULT NULL,
  `acct_type` varchar(3) DEFAULT NULL,
  `bal_dir` varchar(2) DEFAULT NULL,
  `valid_flag` varchar(2) DEFAULT NULL COMMENT '0：有效 1：无效',
  PRIMARY KEY (`item_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wa_liq_account
-- ----------------------------
DROP TABLE IF EXISTS `wa_liq_account`;
CREATE TABLE `wa_liq_account` (
  `item_no` varchar(6) NOT NULL,
  `liq_level` varchar(1) NOT NULL,
  `up_itemno` varchar(6) NOT NULL,
  `uliq_acctno` varchar(16) NOT NULL,
  `lliq_acctno` varchar(16) NOT NULL,
  PRIMARY KEY (`item_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wa_lock_reg
-- ----------------------------
DROP TABLE IF EXISTS `wa_lock_reg`;
CREATE TABLE `wa_lock_reg` (
  `seq_no` varchar(20) NOT NULL,
  `acc_date` varchar(8) NOT NULL,
  `acc_no` varchar(16) NOT NULL,
  `lock_no` varchar(18) NOT NULL,
  `lock_flag` varchar(1) NOT NULL,
  `lock_date` varchar(8) NOT NULL,
  `lock_operiod` int(11) NOT NULL,
  `unlock_date` varchar(8) DEFAULT NULL,
  `remark` varchar(256) DEFAULT NULL,
  `lock_status` varchar(1) NOT NULL,
  `expired_date` varchar(14) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wa_open_reg
-- ----------------------------
DROP TABLE IF EXISTS `wa_open_reg`;
CREATE TABLE `wa_open_reg` (
  `seq_no` varchar(20) NOT NULL,
  `acc_date` char(8) NOT NULL,
  `tran_time` varchar(14) DEFAULT NULL,
  `acc_type` varchar(3) NOT NULL,
  `user_no` varchar(19) NOT NULL,
  `item_no` varchar(8) NOT NULL,
  `acc_no` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wa_product
-- ----------------------------
DROP TABLE IF EXISTS `wa_product`;
CREATE TABLE `wa_product` (
  `product_no` varchar(3) NOT NULL,
  `item_no` varchar(8) NOT NULL,
  `prduct_name` varchar(256) NOT NULL,
  `product_curr` varchar(3) NOT NULL COMMENT '币种',
  `product_type` char(1) NOT NULL COMMENT '产品类型 1：个人主主账户',
  `is_repeat` char(1) DEFAULT NULL COMMENT '是否允许同一用户有多账户（0. 否1.是）',
  `min_draw_amt` int(11) DEFAULT NULL,
  `max_draw_amt` int(11) DEFAULT NULL,
  `tran_mode` varchar(3) DEFAULT NULL COMMENT '交易方式（账户内互转）00 主账户互转',
  `lst_mdfdate` char(8) NOT NULL,
  `valid_flag` char(1) NOT NULL COMMENT '0：有效 1：无效',
  PRIMARY KEY (`product_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wa_trans_flow
-- ----------------------------
DROP TABLE IF EXISTS `wa_trans_flow`;
CREATE TABLE `wa_trans_flow` (
  `trans_flow` varchar(24) NOT NULL,
  `trans_date` varchar(8) NOT NULL,
  `trancode` varchar(6) NOT NULL,
  `trans_time` varchar(6) NOT NULL,
  `user_no` varchar(16) DEFAULT NULL,
  `trans_acct` varchar(16) DEFAULT NULL,
  `trans_item` varchar(6) DEFAULT NULL,
  `opp_acct` varchar(16) DEFAULT NULL,
  `opp_item` varchar(6) DEFAULT NULL,
  `trans_amt` int(11) DEFAULT '0',
  `cancel_flag` char(1) DEFAULT '0',
  `refund_flag` char(1) DEFAULT '0',
  `tran_status` char(1) NOT NULL DEFAULT '1' COMMENT '0：成功 1：未完成 2：失败',
  `refund_total` int(11) DEFAULT '0',
  `ret_code` char(8) DEFAULT NULL,
  `ret_remark` varchar(64) DEFAULT NULL,
  `order_no` char(18) DEFAULT NULL,
  `remark` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`trans_flow`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wa_transcode
-- ----------------------------
DROP TABLE IF EXISTS `wa_transcode`;
CREATE TABLE `wa_transcode` (
  `trans_code` varchar(6) NOT NULL,
  `trans_name` varchar(16) NOT NULL,
  `remark` varchar(64) DEFAULT NULL,
  `flag` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
