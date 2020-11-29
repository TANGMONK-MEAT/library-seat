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

 Date: 29/11/2020 17:46:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_Illegal
-- ----------------------------
DROP TABLE IF EXISTS `t_Illegal`;
CREATE TABLE `t_Illegal` (
  `i_id` int NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `stu_no` int NOT NULL COMMENT '学号',
  `stu_name` varchar(255) NOT NULL COMMENT '学生姓名',
  `time` datetime NOT NULL COMMENT '违章时间',
  `score` int NOT NULL COMMENT '扣分',
  `remarks` varchar(255) NOT NULL COMMENT '描述',
  PRIMARY KEY (`i_id`) USING BTREE,
  KEY `Illegal_user_key` (`stu_no`),
  CONSTRAINT `Illegal_user_key` FOREIGN KEY (`stu_no`) REFERENCES `t_user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='违规记录表';

-- ----------------------------
-- Records of t_Illegal
-- ----------------------------
BEGIN;
COMMIT;

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
-- Records of t_leave
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_look
-- ----------------------------
DROP TABLE IF EXISTS `t_look`;
CREATE TABLE `t_look` (
  `lk_id` int NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `stu_no` int NOT NULL COMMENT '学号',
  `stu_name` varchar(255) NOT NULL COMMENT '姓名',
  `detail` text NOT NULL COMMENT '监督描述',
  `time` datetime NOT NULL COMMENT '提交时间',
  PRIMARY KEY (`lk_id`),
  KEY `look_user_key` (`stu_no`),
  CONSTRAINT `look_user_key` FOREIGN KEY (`stu_no`) REFERENCES `t_user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='监督描述表';

-- ----------------------------
-- Records of t_look
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_look_img
-- ----------------------------
DROP TABLE IF EXISTS `t_look_img`;
CREATE TABLE `t_look_img` (
  `img_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `img_url` varchar(255) NOT NULL COMMENT '图片链接',
  `stu_no` int NOT NULL COMMENT '学号',
  `time` datetime NOT NULL COMMENT '添加时间',
  `stu_name` varchar(255) NOT NULL COMMENT '姓名',
  PRIMARY KEY (`img_id`),
  KEY `img_user_key` (`stu_no`),
  CONSTRAINT `img_user_key` FOREIGN KEY (`stu_no`) REFERENCES `t_user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='监督图片表';

-- ----------------------------
-- Records of t_look_img
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice` (
  `n_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) NOT NULL COMMENT '通知标题',
  `content` text NOT NULL COMMENT '内容',
  `time` datetime NOT NULL COMMENT '发表时间',
  `user_id` int NOT NULL COMMENT '管理员id',
  PRIMARY KEY (`n_id`),
  KEY `notice_user_key` (`user_id`),
  CONSTRAINT `notice_user_key` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统通知表';

-- ----------------------------
-- Records of t_notice
-- ----------------------------
BEGIN;
COMMIT;

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
-- Records of t_order
-- ----------------------------
BEGIN;
COMMIT;

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
-- Records of t_power
-- ----------------------------
BEGIN;
INSERT INTO `t_power` VALUES (1, 1);
INSERT INTO `t_power` VALUES (2, 2);
INSERT INTO `t_power` VALUES (3, 4);
COMMIT;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `r_id` int NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `r_name` varchar(20) NOT NULL COMMENT '角色名',
  `r_power` int NOT NULL COMMENT '1-学生-001（1）\n2-管理员-011（3）\n3-授权管理员-111（7）',
  `r_desc` varchar(100) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of t_role
-- ----------------------------
BEGIN;
INSERT INTO `t_role` VALUES (1, '用户', 1, '普通用户权限');
INSERT INTO `t_role` VALUES (2, '管理员', 3, '拥有操作用户的权限');
INSERT INTO `t_role` VALUES (3, '授权管理员', 7, '授权管理员，拥有所有操作权限，可以设置管理员，和授权');
COMMIT;

-- ----------------------------
-- Table structure for t_score
-- ----------------------------
DROP TABLE IF EXISTS `t_score`;
CREATE TABLE `t_score` (
  `se_id` int NOT NULL COMMENT '主键自增',
  `stu_no` int NOT NULL COMMENT '学号',
  `stu_name` varchar(255) NOT NULL COMMENT '姓名',
  `total` int NOT NULL COMMENT '信誉值',
  PRIMARY KEY (`se_id`),
  KEY `scroe_user_key` (`stu_no`),
  CONSTRAINT `scroe_user_key` FOREIGN KEY (`stu_no`) REFERENCES `t_user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='信誉分表';

-- ----------------------------
-- Records of t_score
-- ----------------------------
BEGIN;
COMMIT;

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
-- Records of t_seat
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `u_id` int NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `wx_openid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '微信openid',
  `u_account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号',
  `u_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `nick_name` varchar(32) DEFAULT NULL COMMENT '微信昵称',
  `gender` tinyint DEFAULT NULL COMMENT '性别，0-女；1-男；2-保密',
  `email` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系电话',
  `avatarUrl` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像链接',
  `country` varchar(20) DEFAULT NULL COMMENT '国家',
  `province` varchar(20) DEFAULT NULL COMMENT '省份',
  `city` varchar(30) DEFAULT NULL COMMENT '城市名',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态，0-锁定；1-正常',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最近登录时间',
  `desc` varchar(100) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`u_id`),
  UNIQUE KEY `user_account` (`u_account`) COMMENT '账号唯一',
  UNIQUE KEY `user_openid` (`wx_openid`) USING BTREE COMMENT '微信用户的唯一',
  UNIQUE KEY `user_phone` (`phone`) USING BTREE COMMENT '电话号码唯一'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `user_id` int NOT NULL COMMENT '用户主键',
  `role_id` int NOT NULL COMMENT '角色主键',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
