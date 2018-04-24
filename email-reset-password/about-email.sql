/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost
 Source Database       : cas_sso_record

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : utf-8

 Date: 04/24/2018 15:36:23 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(64) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `expired` int(11) DEFAULT NULL,
  `disabled` int(11) DEFAULT NULL,
  `locked` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `sys_user`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('1', 'admin', '4eef1e1ea34879a2ae60c60815927ed9', '1056856191@qq.com', '郑州新东区', '24', '0', '0', '0'), ('2', 'zhangsan', '25d55ad283aa400af464c76d713c07ad', 'zhangsan@foxmail.com', '广州越秀', '26', '0', '0', '0'), ('3', 'zhaosi', '81dc9bdb52d04dc20036dbd8313ed055', 'zhaosi@foxmail.com', '广州海珠', '25', '0', '1', '0'), ('4', 'wangwu', '827ccb0eea8a706c4c34a16891f84e7b', 'wangwu@foxmail.com', '广州番禺', '27', '1', '0', '0'), ('5', 'tingfeng', 'tingfeng', 'tingfeng@foxmail.com', '郑州新东区', '24', '0', '0', '0'), ('6', 'boss', '202cb962ac59075b964b07152d234b70', 'boss@foxmail.com', '郑州中原区', '30', '0', '0', '1');
COMMIT;

-- ----------------------------
--  Table structure for `sys_user_question`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_question`;
CREATE TABLE `sys_user_question` (
  `username` varchar(30) COLLATE utf8_bin NOT NULL,
  `question` varchar(200) COLLATE utf8_bin NOT NULL,
  `answer` varchar(100) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `sys_user_question`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_question` VALUES ('admin', '使用过的密码是？', '123'), ('admin', '你的年龄是？', '24'), ('zhangsan', '我的名字是？', 'zhangsan'), ('zhangsan', '我在哪里工作？', 'beijing');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
