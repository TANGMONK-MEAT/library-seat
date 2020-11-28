/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1_3306
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : 127.0.0.1:3306
 Source Schema         : db_library_seat

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 28/11/2020 17:03:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_leave
-- ----------------------------
DROP TABLE IF EXISTS `t_leave`;
CREATE TABLE `t_leave` (
  `l_id` int NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `seat_id` int NOT NULL COMMENT '座位id',
  `user_id` int NOT NULL COMMENT '用户id',
  `leave_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '离开时间',
  `back_time` datetime DEFAULT NULL COMMENT '返回的时间',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态，0-离开，1-合法返回，2-未归',
  PRIMARY KEY (`l_id`),
  KEY `leaveby_seat_key` (`seat_id`),
  KEY `leaveby_user_key` (`user_id`),
  CONSTRAINT `leaveby_seat_key` FOREIGN KEY (`seat_id`) REFERENCES `t_seat` (`s_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `leaveby_user_key` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='中途暂离记录表';

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `o_id` int NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `order_no` bigint NOT NULL COMMENT '订单编号',
  `seat_id` int NOT NULL COMMENT '座位id',
  `user_id` int NOT NULL COMMENT '用户id',
  `appoint _time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '预约时间',
  `sign_time` datetime DEFAULT NULL COMMENT '签到时间',
  `signback_time` datetime DEFAULT NULL COMMENT '签退时间',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '订单状态，01正在处理；1-已处理，默认0',
  PRIMARY KEY (`o_id`),
  UNIQUE KEY `order_no` (`order_no`) COMMENT '订单编号唯一',
  KEY `order_user_key` (`user_id`),
  KEY `order_seat_key` (`seat_id`),
  CONSTRAINT `order_seat_key` FOREIGN KEY (`seat_id`) REFERENCES `t_seat` (`s_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `order_user_key` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户订单表';

-- ----------------------------
-- Table structure for t_power
-- ----------------------------
DROP TABLE IF EXISTS `t_power`;
CREATE TABLE `t_power` (
  `p_id` int NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `p_level` int NOT NULL COMMENT '权限等级',
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='权限表；\n001（1）-普通 ；\n010（2）-管理 ；\n100（4）-授权 ；';

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `r_id` int NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `r_name` varchar(20) NOT NULL COMMENT '角色名',
  `r_power` int NOT NULL COMMENT '1-学生-001（1）\n2-管理员-011（3）\n3-授权管理员-111（7）',
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Table structure for t_seat
-- ----------------------------
DROP TABLE IF EXISTS `t_seat`;
CREATE TABLE `t_seat` (
  `s_id` int NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `floor` int NOT NULL COMMENT '楼层号',
  `room` int NOT NULL COMMENT '自习室号',
  `seat` int NOT NULL COMMENT '座位号',
  `status` int NOT NULL DEFAULT '0' COMMENT '座位状态，[0-空闲，1-预约，2-占用，3-暂离，4-贴条]',
  PRIMARY KEY (`s_id`),
  UNIQUE KEY `seat_id` (`floor`,`room`,`seat`) COMMENT '座位号唯一标识'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='座位表';

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `u_id` int NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `wx_openid` varchar(32) DEFAULT NULL COMMENT '微信openid',
  `u_account` varchar(50) DEFAULT NULL COMMENT '学号/教职工工号',
  `u_password` varchar(50) DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(32) DEFAULT NULL COMMENT '微信昵称',
  `gender` tinyint DEFAULT NULL COMMENT '性别，0-女；1-男',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `avatarUrl` varchar(255) DEFAULT NULL COMMENT '头像链接',
  `country` varchar(20) DEFAULT NULL COMMENT '国家',
  `province` varchar(20) DEFAULT NULL COMMENT '省份',
  `city` varchar(30) DEFAULT NULL COMMENT '城市名',
  `u_type` int DEFAULT NULL COMMENT '用户类型',
  PRIMARY KEY (`u_id`),
  UNIQUE KEY `user_openid` (`wx_openid`) COMMENT '微信用户的唯一标识',
  UNIQUE KEY `user_account` (`u_account`) COMMENT '账号唯一',
  KEY `user_role_key` (`u_type`),
  CONSTRAINT `user_role_key` FOREIGN KEY (`u_type`) REFERENCES `t_role` (`r_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

SET FOREIGN_KEY_CHECKS = 1;
