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

 Date: 06/12/2020 13:46:09
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
  `p_available` tinyint NOT NULL DEFAULT '1' COMMENT '是否可用；1-可用，0-不可用',
  `parent_id` int NOT NULL DEFAULT '0' COMMENT '父权限id；0-最低级权限，依次类推',
  `parent_ids` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '所有父级权限，例如：0/1/2',
  `p_name` varchar(20) NOT NULL COMMENT '资源权限，例如: user:update:12',
  `p_desc` varchar(20) NOT NULL COMMENT '资源权限的描述，例如：用户修改',
  `p_url` varchar(50) NOT NULL COMMENT '请求的url',
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='权限表；\n001（1）-普通 ；\n010（2）-管理 ；\n100（4）-授权 ；';

-- ----------------------------
-- Records of t_power
-- ----------------------------
BEGIN;
INSERT INTO `t_power` VALUES (1, 1, 0, '0', 'user:view', '用户管理', 'user/userList');
INSERT INTO `t_power` VALUES (2, 1, 0, '0/1', 'user:add', ' 用户添加', 'user/userAdd');
INSERT INTO `t_power` VALUES (3, 1, 0, '0/1', 'user:delete', '用户删除', 'user/userDelete');
INSERT INTO `t_power` VALUES (4, 1, 0, '0/1', 'user:update', '用户修改', 'user/userUpdate');
COMMIT;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `r_id` int NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `r_available` tinyint NOT NULL DEFAULT '1' COMMENT '是否可用，1-可用；0-不可用',
  `r_name` varchar(20) NOT NULL COMMENT '角色名',
  `r_desc` varchar(100) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of t_role
-- ----------------------------
BEGIN;
INSERT INTO `t_role` VALUES (1, 1, 'user', '普通用户');
INSERT INTO `t_role` VALUES (2, 1, 'admin', '管理员');
INSERT INTO `t_role` VALUES (3, 1, 'authAdmin', '授权管理员');
INSERT INTO `t_role` VALUES (4, 1, 'test', '测试');
COMMIT;

-- ----------------------------
-- Table structure for t_role_power
-- ----------------------------
DROP TABLE IF EXISTS `t_role_power`;
CREATE TABLE `t_role_power` (
  `rp_id` int NOT NULL COMMENT '主键自增',
  `role_id` int NOT NULL COMMENT '角色表主键',
  `power_id` int NOT NULL COMMENT '权限表主键',
  PRIMARY KEY (`rp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_power
-- ----------------------------
BEGIN;
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
  `u_account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学号',
  `u_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
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
  `salt` varchar(15) DEFAULT NULL COMMENT '盐值，用于密码认证',
  PRIMARY KEY (`u_id`),
  UNIQUE KEY `user_openid` (`wx_openid`) USING BTREE COMMENT '微信用户的唯一',
  UNIQUE KEY `user_account` (`u_account`) COMMENT '账号唯一',
  UNIQUE KEY `user_phone` (`phone`) USING BTREE COMMENT '电话号码唯一'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES (1, '1', 'zwl', '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2020-12-02 20:27:14', NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES (2, '2', 'zwl0', '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2020-12-03 16:46:24', NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `ur_id` int NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `user_id` int NOT NULL COMMENT '用户主键',
  `role_id` int NOT NULL COMMENT '角色主键',
  PRIMARY KEY (`ur_id`) USING BTREE,
  KEY `user_role_key1` (`user_id`),
  KEY `user_role_key2` (`role_id`),
  CONSTRAINT `user_role_key1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_role_key2` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`r_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
