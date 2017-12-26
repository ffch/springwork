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
-- Table structure for se_app_user
-- ----------------------------
DROP TABLE IF EXISTS `se_app_user`;
CREATE TABLE `se_app_user` (
  `uuid` varchar(32) NOT NULL,
  `user_name` varchar(40) DEFAULT NULL,
  `passwd` varchar(32) DEFAULT NULL,
  `user_type` varchar(4) DEFAULT NULL,
  `user_no` varchar(16) NOT NULL,
  PRIMARY KEY (`user_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
