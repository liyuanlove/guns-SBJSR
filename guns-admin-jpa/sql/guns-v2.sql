/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : guns

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 13/08/2018 19:34:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '优惠券ID',
  `sellerid` int(11) NOT NULL COMMENT '卖家ID',
  `begin_day` date DEFAULT NULL COMMENT '起始时间',
  `end_day` date DEFAULT NULL COMMENT '结束时间',
  `recept_day` datetime(0) DEFAULT NULL COMMENT '领取时间',
  `user_tel` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '领取人的电话号码',
  `use_code` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '使用验证码',
  `used` int(11) DEFAULT NULL COMMENT '1:已使用，0：未使用',
  `createtime` datetime(0) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1027 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '优惠券信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of coupon
-- ----------------------------
INSERT INTO `coupon` VALUES (1013, 13, '2018-07-25', '2018-08-10', '2018-07-26 00:00:00', '12345678901', '7R5BU', 0, '2018-08-07 13:50:38');
INSERT INTO `coupon` VALUES (1014, 13, '2018-07-25', '2018-08-10', '2018-07-25 00:00:00', '123', 'G4VJC', 1, '2018-08-07 13:50:38');
INSERT INTO `coupon` VALUES (1015, 13, '2018-07-25', '2018-08-10', '2018-07-25 00:00:00', '123', 'MG9BI', 0, '2018-08-07 13:50:38');
INSERT INTO `coupon` VALUES (1016, 13, '2018-07-25', '2018-08-10', '2018-08-04 19:01:02', '123', 'E87IZ', 1, '2018-08-07 13:50:38');
INSERT INTO `coupon` VALUES (1018, 14, '2018-07-25', '2018-07-31', '2018-07-25 00:00:00', '123', '4Q43G', 0, '2018-08-07 13:50:38');
INSERT INTO `coupon` VALUES (1022, 15, '2018-07-31', '2018-08-29', '2018-08-07 19:41:41', '123', 'EJBGC', 0, '2018-08-07 13:50:38');
INSERT INTO `coupon` VALUES (1023, 15, '2018-07-31', '2018-08-29', '2018-08-07 19:23:35', '123', 'G5SAI', 0, '2018-08-07 13:50:38');
INSERT INTO `coupon` VALUES (1024, 12, '2018-08-13', '2018-08-30', NULL, NULL, NULL, 0, '2018-08-07 13:50:38');
INSERT INTO `coupon` VALUES (1025, 12, '2018-08-13', '2018-08-30', '2018-08-13 18:43:32', '123', 'JB5JN', 0, '2018-08-07 13:50:38');
INSERT INTO `coupon` VALUES (1026, 12, '2018-08-13', '2018-08-30', NULL, NULL, NULL, 0, '2018-08-07 13:50:38');

-- ----------------------------
-- Table structure for seller
-- ----------------------------
DROP TABLE IF EXISTS `seller`;
CREATE TABLE `seller`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商家ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商家名称',
  `addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商家地址',
  `status` int(11) DEFAULT NULL COMMENT '1 启用,0 禁用',
  `createtime` datetime(0) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of seller
-- ----------------------------
INSERT INTO `seller` VALUES (10, '蛋糕蛋挞', '长沙路', 1, '2018-08-07 13:50:38');
INSERT INTO `seller` VALUES (11, '火锅', '河畔', 1, '2018-08-07 13:50:38');
INSERT INTO `seller` VALUES (12, '酸菜鱼', '北京路', 1, '2018-08-07 13:50:38');
INSERT INTO `seller` VALUES (13, '米线', '宁夏路', 1, '2018-08-07 13:50:38');
INSERT INTO `seller` VALUES (14, '翅品', '油烟街', 1, '2018-08-07 13:50:38');
INSERT INTO `seller` VALUES (15, '烧烤', '11楼', 1, '2018-08-07 13:50:38');
INSERT INTO `seller` VALUES (17, '掉书袋和', '', 0, '2018-08-07 13:50:38');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `num` int(11) DEFAULT NULL COMMENT '排序',
  `pid` int(11) DEFAULT NULL COMMENT '父部门id',
  `pids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父级ids',
  `simplename` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '简称',
  `fullname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '全称',
  `tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '提示',
  `version` int(11) DEFAULT NULL COMMENT '版本（乐观锁保留字段）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (24, 1, 0, '[0],', '总公司', '总公司', '', NULL);
INSERT INTO `sys_dept` VALUES (25, 2, 24, '[0],[24],', '开发部', '开发部', '负责软件开发', NULL);
INSERT INTO `sys_dept` VALUES (26, 3, 24, '[0],[24],', '运营部', '运营部', '负责业务运营', NULL);
INSERT INTO `sys_dept` VALUES (27, 4, 24, '[0],[24],', '战略部', '战略部', '规划企业战略', NULL);
INSERT INTO `sys_dept` VALUES (28, 5, 24, '[0],[24],', '人力部', '人力资源部', '招聘等工作', NULL);
INSERT INTO `sys_dept` VALUES (29, 6, 0, '[0],', '卖家', '活动卖家', '参与优惠活动的卖家', NULL);
INSERT INTO `sys_dept` VALUES (30, 7, 29, '[0],[29],', 'CS', 'SDSAD', '啊啊', NULL);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `num` int(11) DEFAULT NULL COMMENT '排序',
  `pid` int(11) DEFAULT NULL COMMENT '父级字典',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '提示',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 82 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (50, 0, 0, '性别', NULL, 'sys_sex');
INSERT INTO `sys_dict` VALUES (51, 1, 50, '男', NULL, '1');
INSERT INTO `sys_dict` VALUES (52, 2, 50, '女', NULL, '2');
INSERT INTO `sys_dict` VALUES (53, 0, 0, '状态', NULL, 'sys_state');
INSERT INTO `sys_dict` VALUES (54, 1, 53, '启用', NULL, '1');
INSERT INTO `sys_dict` VALUES (55, 2, 53, '禁用', NULL, '2');
INSERT INTO `sys_dict` VALUES (56, 0, 0, '账号状态', NULL, 'account_state');
INSERT INTO `sys_dict` VALUES (57, 1, 56, '启用', NULL, '1');
INSERT INTO `sys_dict` VALUES (58, 2, 56, '冻结', NULL, '2');
INSERT INTO `sys_dict` VALUES (59, 3, 56, '已删除', NULL, '3');
INSERT INTO `sys_dict` VALUES (77, 0, 0, '学历等级', '达到', 'sys_grade');
INSERT INTO `sys_dict` VALUES (78, 0, 77, '小学', NULL, '0');
INSERT INTO `sys_dict` VALUES (79, 1, 77, '初中', NULL, '1');
INSERT INTO `sys_dict` VALUES (80, 2, 77, '高中', NULL, '2');
INSERT INTO `sys_dict` VALUES (81, 3, 77, '大学', NULL, '3');

-- ----------------------------
-- Table structure for sys_expense
-- ----------------------------
DROP TABLE IF EXISTS `sys_expense`;
CREATE TABLE `sys_expense`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `money` decimal(20, 2) DEFAULT NULL COMMENT '报销金额',
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '描述',
  `createtime` datetime(0) DEFAULT CURRENT_TIMESTAMP,
  `state` int(11) DEFAULT NULL COMMENT '状态: 1.待提交  2:待审核   3.审核通过 4:驳回',
  `userid` int(11) DEFAULT NULL COMMENT '用户id',
  `processId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '流程定义id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '报销表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log`  (
  `id` int(65) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `logname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '日志名称',
  `userid` int(65) DEFAULT NULL COMMENT '管理员id',
  `createtime` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `succeed` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否执行成功',
  `message` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '具体消息',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登录ip',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------
INSERT INTO `sys_login_log` VALUES (1, '登录日志', 1, '2018-08-10 10:24:06', '成功', '登录成功', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (2, '登录日志', 1, '2018-08-10 10:39:24', '成功', '登录成功', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (3, '登录日志', 1, '2018-08-10 11:12:02', '成功', '登录成功', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (4, '登录日志', 1, '2018-08-10 14:55:29', '成功', '登录成功', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (5, '登录日志', 1, '2018-08-12 18:35:12', '成功', '登录成功', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (6, '登录日志', 1, '2018-08-13 11:27:43', '成功', '登录成功', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (7, '登录日志', 1, '2018-08-13 17:58:28', '成功', '登录成功', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (8, '登录日志', 1, '2018-08-13 18:12:27', '成功', '登录成功', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (9, '登录日志', 1, '2018-08-13 18:42:14', '成功', '登录成功', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES (10, '登录日志', 1, '2018-08-13 19:21:05', '成功', '登录成功', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单名称',
  `pcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单父编号',
  `pcodes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '当前菜单的所有父菜单编号',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单图标',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'url地址',
  `levels` int(65) DEFAULT NULL COMMENT '菜单层级',
  `num` int(65) DEFAULT NULL COMMENT '内部排序号',
  `ismenu` int(11) DEFAULT NULL COMMENT '是否是菜单（1：是  0：不是）',
  `isopen` int(11) DEFAULT NULL COMMENT '是否打开:    1:打开   0:不打开',
  `status` int(65) DEFAULT NULL COMMENT '菜单状态 :  1:启用   0:不启用',
  `tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 196 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (105, 'system', '系统管理', '0', '[0],', 'fa-user', '#', 1, 4, 1, 1, 1, NULL);
INSERT INTO `sys_menu` VALUES (106, 'mgr', '用户管理', 'system', '[0],[system],', '', '/mgr', 2, 1, 1, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (107, 'mgr_add', '添加用户', 'mgr', '[0],[system],[mgr],', '', '/mgr/add', 3, 3, 0, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (108, 'mgr_edit', '修改用户', 'mgr', '[0],[system],[mgr],', '', '/mgr/edit', 3, 5, 0, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (109, 'mgr_delete', '删除用户', 'mgr', '[0],[system],[mgr],', '', '/mgr/delete', 3, 6, 0, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (110, 'mgr_reset', '重置密码', 'mgr', '[0],[system],[mgr],', '', '/mgr/reset', 3, 7, 0, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (111, 'mgr_freeze', '冻结用户', 'mgr', '[0],[system],[mgr],', '', '/mgr/freeze', 3, 8, 0, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (112, 'mgr_unfreeze', '解除冻结用户', 'mgr', '[0],[system],[mgr],', '', '/mgr/unfreeze', 3, 9, 0, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (113, 'mgr_setRole', '分配角色', 'mgr', '[0],[system],[mgr],', '', '/mgr/setRole', 3, 11, 0, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (114, 'role', '角色管理', 'system', '[0],[system],', NULL, '/role', 2, 2, 1, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (115, 'role_add', '添加角色', 'role', '[0],[system],[role],', '', '/role/add', 3, 3, 0, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (116, 'role_edit', '修改角色', 'role', '[0],[system],[role],', '', '/role/edit', 3, 5, 0, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (117, 'role_remove', '删除角色', 'role', '[0],[system],[role],', '', '/role/remove', 3, 6, 0, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (118, 'role_setAuthority', '角色配置权限', 'role', '[0],[system],[role],', '', '/role/setAuthority', 3, 8, 0, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (119, 'menu', '菜单管理', 'system', '[0],[system],', NULL, '/menu', 2, 4, 1, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (120, 'menu_add', '添加菜单', 'menu', '[0],[system],[menu],', '', '/menu/add', 3, 3, 0, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (121, 'menu_edit', '修改菜单', 'menu', '[0],[system],[menu],', '', '/menu/edit', 3, 5, 0, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (122, 'menu_delete', '删除菜单', 'menu', '[0],[system],[menu],', '', '/menu/delete', 3, 6, 0, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (128, 'log', '业务日志', 'system', '[0],[system],', NULL, '/log', 2, 6, 1, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (130, 'druid', '监控管理', 'system', '[0],[system],', NULL, '/druid', 2, 7, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (131, 'dept', '部门管理', 'system', '[0],[system],', NULL, '/dept', 2, 3, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (132, 'dict', '字典管理', 'system', '[0],[system],', NULL, '/dict', 2, 4, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (133, 'loginLog', '登录日志', 'system', '[0],[system],', NULL, '/loginLog', 2, 6, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (134, 'log_clean', '清空日志', 'log', '[0],[system],[log],', NULL, '/log/delLog', 3, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (135, 'dept_add', '添加部门', 'dept', '[0],[system],[dept],', NULL, '/dept/add', 3, 1, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (136, 'dept_update', '修改部门', 'dept', '[0],[system],[dept],', NULL, '/dept/update', 3, 1, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (137, 'dept_delete', '删除部门', 'dept', '[0],[system],[dept],', NULL, '/dept/delete', 3, 1, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (138, 'dict_add', '添加字典', 'dict', '[0],[system],[dict],', NULL, '/dict/add', 3, 1, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (139, 'dict_update', '修改字典', 'dict', '[0],[system],[dict],', NULL, '/dict/update', 3, 1, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (140, 'dict_delete', '删除字典', 'dict', '[0],[system],[dict],', NULL, '/dict/delete', 3, 1, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (141, 'notice', '通知管理', 'system', '[0],[system],', NULL, '/notice', 2, 9, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (142, 'notice_add', '添加通知', 'notice', '[0],[system],[notice],', NULL, '/notice/add', 3, 1, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (143, 'notice_update', '修改通知', 'notice', '[0],[system],[notice],', NULL, '/notice/update', 3, 2, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (144, 'notice_delete', '删除通知', 'notice', '[0],[system],[notice],', NULL, '/notice/delete', 3, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (145, 'hello', '通知', '0', '[0],', 'fa-rocket', '/notice/hello', 1, 1, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (148, 'code', '代码生成', '0', '[0],', 'fa-code', '/code', 1, 3, 1, NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (149, 'api_mgr', '接口文档', '0', '[0],', 'fa-leaf', '/swagger-ui.html', 1, 2, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (150, 'to_menu_edit', '编辑菜单页', 'menu', '[0],[system],[menu],', '', '/menu/menu_edit', 3, 4, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (151, 'menu_list', '菜单列表', 'menu', '[0],[system],[menu],', '', '/menu/list', 3, 1, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (152, 'to_dept_update', '修改部门跳转', 'dept', '[0],[system],[dept],', '', '/dept/dept_update', 3, 4, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (153, 'dept_list', '部门列表', 'dept', '[0],[system],[dept],', '', '/dept/list', 3, 5, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (154, 'dept_detail', '部门详情', 'dept', '[0],[system],[dept],', '', '/dept/detail', 3, 6, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (155, 'to_dict_edit', '修改菜单跳转', 'dict', '[0],[system],[dict],', '', '/dict/dict_edit', 3, 4, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (156, 'dict_list', '字典列表', 'dict', '[0],[system],[dict],', '', '/dict/list', 3, 5, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (157, 'dict_detail', '字典详情', 'dict', '[0],[system],[dict],', '', '/dict/detail', 3, 6, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (158, 'log_list', '日志列表', 'log', '[0],[system],[log],', '', '/log/list', 3, 2, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (159, 'log_detail', '日志详情', 'log', '[0],[system],[log],', '', '/log/detail', 3, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (160, 'del_login_log', '清空登录日志', 'loginLog', '[0],[system],[loginLog],', '', '/loginLog/delLoginLog', 3, 1, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (161, 'login_log_list', '登录日志列表', 'loginLog', '[0],[system],[loginLog],', '', '/loginLog/list', 3, 2, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (162, 'to_role_edit', '修改角色跳转', 'role', '[0],[system],[role],', '', '/role/role_edit', 3, 4, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (163, 'to_role_assign', '角色分配权限', 'role', '[0],[system],[role],', '', '/role/role_assign', 3, 7, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (164, 'role_list', '角色列表', 'role', '[0],[system],[role],', '', '/role/list', 3, 1, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (165, 'to_assign_role', '分配角色页面', 'mgr', '[0],[system],[mgr],', '', '/mgr/role_assign', 3, 10, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (166, 'to_user_edit', '修改用户页面', 'mgr', '[0],[system],[mgr],', '', '/mgr/user_edit', 3, 4, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (167, 'mgr_list', '用户列表', 'mgr', '[0],[system],[mgr],', '', '/mgr/list', 3, 1, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (168, 'biz', '业务管理', '0', '[0],', 'fa-leaf', '#', 1, 5, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (169, 'seller', '卖家管理', 'biz', '[0],[biz],', '', '/seller', 2, 1, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (170, 'seller_add', '添加卖家', 'seller', '[0],[biz],[seller],', '', '/seller/add', 3, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (171, 'seller_edit', '修改卖家', 'seller', '[0],[biz],[seller],', '', '/seller/edit', 3, 5, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (172, 'seller_delete', '删除卖家', 'seller', '[0],[biz],[seller],', '', '/seller/delete', 3, 6, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (173, 'to_seller_edit', '修改卖家跳转', 'seller', '[0],[biz],[seller],', '', '/seller/seller_edit', 3, 4, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (174, 'to_seller_add', '添加卖家跳转', 'seller', '[0],[biz],[seller],', '', '/seller/seller_add', 3, 2, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (175, 'seller_list', '卖家列表', 'seller', '[0],[biz],[seller],', '', '/seller/list', 3, 1, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (176, 'coupon', '优惠券', 'biz', '[0],[biz],', '', '/coupon', 2, 2, 1, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (177, 'coupon_add', '添加优惠券', 'coupon', '[0],[biz],[coupon],', '', '/coupon/add', 3, 3, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (178, 'coupon_qrcode', '二维码', 'coupon', '[0],[biz],[coupon],', '', '/coupon/qrcode/**', 3, 4, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (179, 'coupon_delete', '删除优惠券', 'coupon', '[0],[biz],[coupon],', '', '/coupon/delete', 3, 5, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (181, 'to_coupon_add', '添加优惠券跳转', 'coupon', '[0],[biz],[coupon],', '', '/coupon/coupon_add', 3, 2, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (182, 'coupon_list', '优惠券列表', 'coupon', '[0],[biz],[coupon],', '', '/coupon/list', 3, 1, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (183, 'mgr_setSeller', '分配卖家', 'mgr', '[0],[system],[mgr],', '', '/mgr/setSeller', 3, 13, 0, 0, 1, NULL);
INSERT INTO `sys_menu` VALUES (184, 'to_seller_assign', '卖家分配页面', 'mgr', '[0],[system],[mgr],', '', '/mgr/seller_assign', 3, 12, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (185, 'coupon_use', '使用优惠券', 'coupon', '[0],[biz],[coupon],', '', '/coupon/use', 3, 7, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (186, 'index', '首页', '0', '[0],', '', '/', 1, 0, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (187, 'to_user_add', '新增用户页面', 'mgr', '[0],[system],[mgr],', '', '/mgr/user_add', 3, 2, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (188, 'to_menu_add', '新增菜单页', 'menu', '[0],[system],[menu],', '', '/menu/menu_add', 3, 2, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (189, 'menu_switch', '菜单状态切换', 'menu', '[0],[system],[menu],', '', '/menu/switch', 3, 7, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (190, 'menu_tree', '菜单树', 'menu', '[0],[system],[menu],', '', '/menu/tree', 3, 20, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (191, 'menuTreeByRoleId', '角色配置权限', 'menu', '[0],[system],[menu],', '', '/menu/treeByRoleId/**', 3, 21, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (192, 'mgr_img', '用户获取头像', 'mgr', '[0],[system],[mgr],', '', '/mgr/img/**', 3, 20, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (193, 'mgr_upload', '用户上传头像', 'mgr', '[0],[system],[mgr],', '', '/mgr/upload', 3, 21, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (194, 'seller_tree', '卖家树', 'seller', '[0],[biz],[seller],', '', '/seller/tree', 3, 15, 0, NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (195, 'seller_treeByUserId', '卖家树（配置）', 'seller', '[0],[biz],[seller],', '', '/seller/treeByUserId', 3, 16, 0, NULL, 1, NULL);

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '标题',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '内容',
  `createtime` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `creater` int(11) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通知表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (8, 'Redis', NULL, '采用spring-data-redis作为缓存，并自定义了protostuff的序列化方案', '2017-05-10 19:28:57', 1);
INSERT INTO `sys_notice` VALUES (10, '日期格式', NULL, '重新配置fastjson，修复了页面日期显示为long数值的bug', '2018-07-22 15:10:06', 1);
INSERT INTO `sys_notice` VALUES (11, '组织结构', NULL, '修复了变更实体属性名组织结构树未能正确展开的BUG', '2018-07-22 16:13:40', 1);
INSERT INTO `sys_notice` VALUES (13, '代码生成', NULL, '移除了前端代码生成功能，并变更为JPA版本的代码生成功能', '2018-07-24 19:32:43', 1);
INSERT INTO `sys_notice` VALUES (14, '代码优化', NULL, '<p>删除了废弃代码，并规范化命名，对冗长代码进行了重构</p>', '2018-08-07 13:25:04', 1);
INSERT INTO `sys_notice` VALUES (15, '通知管理BUG', NULL, '乱码BUG需要解决', '2018-08-07 13:37:26', 1);
INSERT INTO `sys_notice` VALUES (16, '业务日志', NULL, '简化AOP业务日志代码', '2018-08-10 14:56:25', 1);
INSERT INTO `sys_notice` VALUES (17, '版本升级', NULL, '将spring boot升级到2.0.3版本,并升级各个依赖包的版本！', '2018-08-13 19:22:12', 1);
INSERT INTO `sys_notice` VALUES (18, '持久层的框架', NULL, '将持久层的框架替换为spring-boot-starter-&gt;，自定义了父接口BaseDao,service层的IBaseService和BaseServiceImpl', '2018-08-13 19:22:33', 1);
INSERT INTO `sys_notice` VALUES (19, 'btl文件', NULL, '实现了代码自动生成的JPA版本代码,优化了模板文件并实现了JPA的dao,service层的btl文件', '2018-08-13 19:22:52', 1);
INSERT INTO `sys_notice` VALUES (20, '移除', NULL, '移除了可视化代码生成功能(对开发者的帮助较为鸡肋)', '2018-08-13 19:23:08', 1);
INSERT INTO `sys_notice` VALUES (21, '重写', NULL, '结合代码自动生成功能重写了service层和DAO层代码', '2018-08-13 19:23:24', 1);
INSERT INTO `sys_notice` VALUES (22, '缓存', NULL, '选取sp选取spring-boot-starter-&gt;作为整个项目的缓存,整合redis作为shiro的缓存', '2018-08-13 19:24:17', 1);
INSERT INTO `sys_notice` VALUES (23, 'protostuff', NULL, '选取protostuff作为redis的序列化方案并整合到redis中', '2018-08-13 19:24:36', 1);
INSERT INTO `sys_notice` VALUES (24, '功能测试', NULL, '业务管理为测试模块，仅供参考', '2018-08-13 19:31:06', 1);

-- ----------------------------
-- Table structure for sys_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_log`;
CREATE TABLE `sys_operation_log`  (
  `id` int(65) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `logtype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '日志类型',
  `logname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '日志名称',
  `userid` int(65) DEFAULT NULL COMMENT '用户id',
  `classname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '类名称',
  `method` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '方法名称',
  `createtime` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `succeed` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否成功',
  `message` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 341 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_operation_log
-- ----------------------------
INSERT INTO `sys_operation_log` VALUES (322, '业务日志', '清空业务日志', 1, 'com.stylefeng.guns.modular.system.controller.OperationLogController', 'delLog', '2018-08-09 14:27:41', '成功', '主键id=null');
INSERT INTO `sys_operation_log` VALUES (323, '异常日志', '', 1, NULL, NULL, '2018-08-09 14:29:06', '失败', 'GunsException(code=600, message=不能删除被领取的数据(存档数据))\r\n	at com.stylefeng.guns.service.impl.CouponServiceImpl.deleteCoupon(CouponServiceImpl.java:56)\r\n	at com.stylefeng.guns.service.impl.CouponServiceImplTTFastClassBySpringCGLIBTT3eb43643.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\r\n	at org.springframework.aop.framework.CglibAopProxyTCglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:746)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:294)\r\n	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:98)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\r\n	at org.springframework.aop.framework.CglibAopProxyTDynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)\r\n	at com.stylefeng.guns.service.impl.CouponServiceImplTTEnhancerBySpringCGLIBTTe629c3a1.deleteCoupon(<generated>)\r\n	at com.stylefeng.guns.modular.biz.controller.CouponController.delete(CouponController.java:153)\r\n	at com.stylefeng.guns.modular.biz.controller.CouponControllerTTFastClassBySpringCGLIBTT9763a88c.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\r\n	at org.springframework.aop.framework.CglibAopProxyTCglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:746)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:88)\r\n	at com.stylefeng.guns.core.interceptor.SessionHolderInterceptor.sessionKit(SessionHolderInterceptor.java:29)\r\n	at sun.reflect.GeneratedMethodAccessor139.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\r\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\r\n	at org.springframework.aop.framework.CglibAopProxyTDynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)\r\n	at com.stylefeng.guns.modular.biz.controller.CouponControllerTTEnhancerBySpringCGLIBTT501ebb40.delete(<generated>)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:209)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:136)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:877)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:783)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:991)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:925)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:974)\r\n	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:877)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:661)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:851)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:112)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.stylefeng.guns.core.xss.XssFilter.doFilter(XssFilter.java:31)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61)\r\n	at org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)\r\n	at org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\r\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilterT1.call(AbstractShiroFilter.java:365)\r\n	at org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90)\r\n	at org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83)\r\n	at org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:357)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:270)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:496)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:803)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\r\n	at org.apache.coyote.AbstractProtocolTConnectionHandler.process(AbstractProtocol.java:790)\r\n	at org.apache.tomcat.util.net.NioEndpointTSocketProcessor.doRun(NioEndpoint.java:1468)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)\r\n	at java.util.concurrent.ThreadPoolExecutorTWorker.run(ThreadPoolExecutor.java:624)\r\n	at org.apache.tomcat.util.threads.TaskThreadTWrappingRunnable.run(TaskThread.java:61)\r\n	at java.lang.Thread.run(Thread.java:748)\r\n');
INSERT INTO `sys_operation_log` VALUES (324, '异常日志', '', 1, NULL, NULL, '2018-08-09 14:29:10', '失败', 'GunsException(code=600, message=不能删除被领取的数据(存档数据))\r\n	at com.stylefeng.guns.service.impl.CouponServiceImpl.deleteCoupon(CouponServiceImpl.java:56)\r\n	at com.stylefeng.guns.service.impl.CouponServiceImplTTFastClassBySpringCGLIBTT3eb43643.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\r\n	at org.springframework.aop.framework.CglibAopProxyTCglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:746)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:294)\r\n	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:98)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\r\n	at org.springframework.aop.framework.CglibAopProxyTDynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)\r\n	at com.stylefeng.guns.service.impl.CouponServiceImplTTEnhancerBySpringCGLIBTTe629c3a1.deleteCoupon(<generated>)\r\n	at com.stylefeng.guns.modular.biz.controller.CouponController.delete(CouponController.java:153)\r\n	at com.stylefeng.guns.modular.biz.controller.CouponControllerTTFastClassBySpringCGLIBTT9763a88c.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\r\n	at org.springframework.aop.framework.CglibAopProxyTCglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:746)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:88)\r\n	at com.stylefeng.guns.core.interceptor.SessionHolderInterceptor.sessionKit(SessionHolderInterceptor.java:29)\r\n	at sun.reflect.GeneratedMethodAccessor139.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\r\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\r\n	at org.springframework.aop.framework.CglibAopProxyTDynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)\r\n	at com.stylefeng.guns.modular.biz.controller.CouponControllerTTEnhancerBySpringCGLIBTT501ebb40.delete(<generated>)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:209)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:136)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:877)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:783)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:991)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:925)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:974)\r\n	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:877)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:661)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:851)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:112)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.stylefeng.guns.core.xss.XssFilter.doFilter(XssFilter.java:31)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61)\r\n	at org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)\r\n	at org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\r\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilterT1.call(AbstractShiroFilter.java:365)\r\n	at org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90)\r\n	at org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83)\r\n	at org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:357)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:270)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:496)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:803)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\r\n	at org.apache.coyote.AbstractProtocolTConnectionHandler.process(AbstractProtocol.java:790)\r\n	at org.apache.tomcat.util.net.NioEndpointTSocketProcessor.doRun(NioEndpoint.java:1468)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)\r\n	at java.util.concurrent.ThreadPoolExecutorTWorker.run(ThreadPoolExecutor.java:624)\r\n	at org.apache.tomcat.util.threads.TaskThreadTWrappingRunnable.run(TaskThread.java:61)\r\n	at java.lang.Thread.run(Thread.java:748)\r\n');
INSERT INTO `sys_operation_log` VALUES (325, '异常日志', '', 1, NULL, NULL, '2018-08-09 14:29:15', '失败', 'GunsException(code=600, message=不能删除被领取的数据(存档数据))\r\n	at com.stylefeng.guns.service.impl.CouponServiceImpl.deleteCoupon(CouponServiceImpl.java:56)\r\n	at com.stylefeng.guns.service.impl.CouponServiceImplTTFastClassBySpringCGLIBTT3eb43643.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\r\n	at org.springframework.aop.framework.CglibAopProxyTCglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:746)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:294)\r\n	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:98)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\r\n	at org.springframework.aop.framework.CglibAopProxyTDynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)\r\n	at com.stylefeng.guns.service.impl.CouponServiceImplTTEnhancerBySpringCGLIBTTe629c3a1.deleteCoupon(<generated>)\r\n	at com.stylefeng.guns.modular.biz.controller.CouponController.delete(CouponController.java:153)\r\n	at com.stylefeng.guns.modular.biz.controller.CouponControllerTTFastClassBySpringCGLIBTT9763a88c.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\r\n	at org.springframework.aop.framework.CglibAopProxyTCglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:746)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:88)\r\n	at com.stylefeng.guns.core.interceptor.SessionHolderInterceptor.sessionKit(SessionHolderInterceptor.java:29)\r\n	at sun.reflect.GeneratedMethodAccessor139.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\r\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\r\n	at org.springframework.aop.framework.CglibAopProxyTDynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)\r\n	at com.stylefeng.guns.modular.biz.controller.CouponControllerTTEnhancerBySpringCGLIBTT501ebb40.delete(<generated>)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:209)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:136)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:877)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:783)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:991)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:925)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:974)\r\n	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:877)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:661)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:851)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:112)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.stylefeng.guns.core.xss.XssFilter.doFilter(XssFilter.java:31)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61)\r\n	at org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)\r\n	at org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\r\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilterT1.call(AbstractShiroFilter.java:365)\r\n	at org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90)\r\n	at org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83)\r\n	at org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:357)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:270)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:496)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:803)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\r\n	at org.apache.coyote.AbstractProtocolTConnectionHandler.process(AbstractProtocol.java:790)\r\n	at org.apache.tomcat.util.net.NioEndpointTSocketProcessor.doRun(NioEndpoint.java:1468)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)\r\n	at java.util.concurrent.ThreadPoolExecutorTWorker.run(ThreadPoolExecutor.java:624)\r\n	at org.apache.tomcat.util.threads.TaskThreadTWrappingRunnable.run(TaskThread.java:61)\r\n	at java.lang.Thread.run(Thread.java:748)\r\n');
INSERT INTO `sys_operation_log` VALUES (326, '异常日志', '', 1, NULL, NULL, '2018-08-09 14:29:24', '失败', 'GunsException(code=600, message=不能删除被领取的数据(存档数据))\r\n	at com.stylefeng.guns.service.impl.CouponServiceImpl.deleteCoupon(CouponServiceImpl.java:56)\r\n	at com.stylefeng.guns.service.impl.CouponServiceImplTTFastClassBySpringCGLIBTT3eb43643.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\r\n	at org.springframework.aop.framework.CglibAopProxyTCglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:746)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:294)\r\n	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:98)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\r\n	at org.springframework.aop.framework.CglibAopProxyTDynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)\r\n	at com.stylefeng.guns.service.impl.CouponServiceImplTTEnhancerBySpringCGLIBTTe629c3a1.deleteCoupon(<generated>)\r\n	at com.stylefeng.guns.modular.biz.controller.CouponController.delete(CouponController.java:153)\r\n	at com.stylefeng.guns.modular.biz.controller.CouponControllerTTFastClassBySpringCGLIBTT9763a88c.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\r\n	at org.springframework.aop.framework.CglibAopProxyTCglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:746)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:88)\r\n	at com.stylefeng.guns.core.interceptor.SessionHolderInterceptor.sessionKit(SessionHolderInterceptor.java:29)\r\n	at sun.reflect.GeneratedMethodAccessor139.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\r\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\r\n	at org.springframework.aop.framework.CglibAopProxyTDynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)\r\n	at com.stylefeng.guns.modular.biz.controller.CouponControllerTTEnhancerBySpringCGLIBTT501ebb40.delete(<generated>)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:209)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:136)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:877)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:783)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:991)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:925)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:974)\r\n	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:877)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:661)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:851)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:112)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.stylefeng.guns.core.xss.XssFilter.doFilter(XssFilter.java:31)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61)\r\n	at org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)\r\n	at org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\r\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilterT1.call(AbstractShiroFilter.java:365)\r\n	at org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90)\r\n	at org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83)\r\n	at org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:357)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:270)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:496)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:803)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\r\n	at org.apache.coyote.AbstractProtocolTConnectionHandler.process(AbstractProtocol.java:790)\r\n	at org.apache.tomcat.util.net.NioEndpointTSocketProcessor.doRun(NioEndpoint.java:1468)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)\r\n	at java.util.concurrent.ThreadPoolExecutorTWorker.run(ThreadPoolExecutor.java:624)\r\n	at org.apache.tomcat.util.threads.TaskThreadTWrappingRunnable.run(TaskThread.java:61)\r\n	at java.lang.Thread.run(Thread.java:748)\r\n');
INSERT INTO `sys_operation_log` VALUES (327, '业务日志', '修改管理员', 1, 'com.stylefeng.guns.modular.system.controller.UserController', 'edit', '2018-08-09 14:30:16', '成功', '账号=admin;;;');
INSERT INTO `sys_operation_log` VALUES (328, '业务日志', '修改管理员', 1, 'com.stylefeng.guns.modular.system.controller.UserController', 'edit', '2018-08-09 14:30:38', '成功', '账号=admin;;;');
INSERT INTO `sys_operation_log` VALUES (329, '业务日志', '修改管理员', 1, 'com.stylefeng.guns.modular.system.controller.UserController', 'edit', '2018-08-09 14:30:55', '成功', '账号=admin;;;');
INSERT INTO `sys_operation_log` VALUES (330, '业务日志', '添加部门', 1, 'com.stylefeng.guns.modular.system.controller.DeptController', 'add', '2018-08-10 10:29:31', '成功', '部门简称=CS');
INSERT INTO `sys_operation_log` VALUES (331, '业务日志', '修改部门', 1, 'com.stylefeng.guns.modular.system.controller.DeptController', 'update', '2018-08-10 10:32:29', '成功', '部门简称=CS;;;');
INSERT INTO `sys_operation_log` VALUES (332, '业务日志', '修改部门', 1, 'com.stylefeng.guns.modular.system.controller.DeptController', 'update', '2018-08-10 10:39:55', '成功', '部门简称=CS;;;');
INSERT INTO `sys_operation_log` VALUES (333, '业务日志', '修改部门', 1, 'com.stylefeng.guns.modular.system.controller.DeptController', 'update', '2018-08-10 11:12:37', '成功', '部门简称=CS;;;');
INSERT INTO `sys_operation_log` VALUES (334, '业务日志', '修改部门', 1, 'com.stylefeng.guns.modular.system.controller.DeptController', 'update', '2018-08-10 11:14:30', '成功', '部门简称=CS;;;');
INSERT INTO `sys_operation_log` VALUES (335, '业务日志', '新增通知', 1, 'com.stylefeng.guns.modular.system.controller.NoticeController', 'add', '2018-08-10 14:56:25', '成功', 'test');
INSERT INTO `sys_operation_log` VALUES (336, '异常日志', '', 1, NULL, NULL, '2018-08-10 14:56:43', '失败', 'org.springframework.data.redis.serializer.SerializationException: Cannot serialize; nested exception is org.springframework.core.serializer.support.SerializationFailedException: Failed to serialize object using DefaultSerializer; nested exception is java.lang.IllegalArgumentException: DefaultSerializer requires a Serializable payload but received an object of type [com.stylefeng.guns.po.Dept]\r\n	at org.springframework.data.redis.serializer.JdkSerializationRedisSerializer.serialize(JdkSerializationRedisSerializer.java:96)\r\n	at org.springframework.data.redis.serializer.DefaultRedisElementWriter.write(DefaultRedisElementWriter.java:43)\r\n	at org.springframework.data.redis.serializer.RedisSerializationContextTSerializationPair.write(RedisSerializationContext.java:219)\r\n	at org.springframework.data.redis.cache.RedisCache.serializeCacheValue(RedisCache.java:238)\r\n	at org.springframework.data.redis.cache.RedisCache.put(RedisCache.java:144)\r\n	at org.tc.redis.cache.RedisCacheDaoImpl.put(RedisCacheDaoImpl.java:24)\r\n	at com.stylefeng.guns.modular.system.controller.DeptController.update(DeptController.java:133)\r\n	at com.stylefeng.guns.modular.system.controller.DeptControllerTTFastClassBySpringCGLIBTTb168ad81.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\r\n	at org.springframework.aop.framework.CglibAopProxyTCglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:746)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:88)\r\n	at com.stylefeng.guns.core.interceptor.SessionHolderInterceptor.sessionKit(SessionHolderInterceptor.java:29)\r\n	at sun.reflect.GeneratedMethodAccessor126.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\r\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:88)\r\n	at com.stylefeng.guns.core.aop.LogAop.recordSysLog(LogAop.java:47)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)\r\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)\r\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:174)\r\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)\r\n	at org.springframework.aop.framework.CglibAopProxyTDynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)\r\n	at com.stylefeng.guns.modular.system.controller.DeptControllerTTEnhancerBySpringCGLIBTT4c040db7.update(<generated>)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:209)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:136)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:877)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:783)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:991)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:925)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:974)\r\n	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:877)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:661)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:851)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:112)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.stylefeng.guns.core.xss.XssFilter.doFilter(XssFilter.java:31)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61)\r\n	at org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)\r\n	at org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\r\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilterT1.call(AbstractShiroFilter.java:365)\r\n	at org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90)\r\n	at org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83)\r\n	at org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:357)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:270)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:496)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:803)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\r\n	at org.apache.coyote.AbstractProtocolTConnectionHandler.process(AbstractProtocol.java:790)\r\n	at org.apache.tomcat.util.net.NioEndpointTSocketProcessor.doRun(NioEndpoint.java:1468)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)\r\n	at java.util.concurrent.ThreadPoolExecutorTWorker.run(ThreadPoolExecutor.java:624)\r\n	at org.apache.tomcat.util.threads.TaskThreadTWrappingRunnable.run(TaskThread.java:61)\r\n	at java.lang.Thread.run(Thread.java:748)\r\nCaused by: org.springframework.core.serializer.support.SerializationFailedException: Failed to serialize object using DefaultSerializer; nested exception is java.lang.IllegalArgumentException: DefaultSerializer requires a Serializable payload but received an object of type [com.stylefeng.guns.po.Dept]\r\n	at org.springframework.core.serializer.support.SerializingConverter.convert(SerializingConverter.java:68)\r\n	at org.springframework.core.serializer.support.SerializingConverter.convert(SerializingConverter.java:35)\r\n	at org.springframework.data.redis.serializer.JdkSerializationRedisSerializer.serialize(JdkSerializationRedisSerializer.java:94)\r\n	... 100 more\r\nCaused by: java.lang.IllegalArgumentException: DefaultSerializer requires a Serializable payload but received an object of type [com.stylefeng.guns.po.Dept]\r\n	at org.springframework.core.serializer.DefaultSerializer.serialize(DefaultSerializer.java:43)\r\n	at org.springframework.core.serializer.support.SerializingConverter.convert(SerializingConverter.java:63)\r\n	... 102 more\r\n');
INSERT INTO `sys_operation_log` VALUES (337, '业务日志', '修改部门', 1, 'com.stylefeng.guns.modular.system.controller.DeptController', 'update', '2018-08-10 15:02:00', '成功', '更新前：Dept{id=30, num=7, pid=29, pids=\'[0],[29],\', simplename=\'CS\', fullname=\'SDSAD\', tips=\'12\', version=null};;;更新后：Dept{id=30, num=7, pid=29, pids=\'[0],[29],\', simplename=\'CS\', fullname=\'SDSAD\', tips=\'阿斯\', version=null}');
INSERT INTO `sys_operation_log` VALUES (338, '业务日志', '修改部门', 1, 'com.stylefeng.guns.modular.system.controller.DeptController', 'update', '2018-08-10 15:03:46', '成功', '更新前：Dept{id=30, num=7, pid=29, pids=\'[0],[29],\', simplename=\'CS\', fullname=\'SDSAD\', tips=\'阿斯\', version=null};;;更新后：Dept{id=30, num=7, pid=29, pids=\'[0],[29],\', simplename=\'CS\', fullname=\'SDSAD\', tips=\'45\', version=null}');
INSERT INTO `sys_operation_log` VALUES (339, '业务日志', '更新部门', 1, 'com.stylefeng.guns.modular.system.controller.DeptController', 'update', '2018-08-13 11:28:26', '成功', '更新前：Dept{id=30, num=7, pid=29, pids=\'[0],[29],\', simplename=\'CS\', fullname=\'SDSAD\', tips=\'45\', version=null};;;更新后：Dept{id=30, num=7, pid=29, pids=\'[0],[29],\', simplename=\'CS\', fullname=\'SDSAD\', tips=\'34\', version=null}');
INSERT INTO `sys_operation_log` VALUES (340, '业务日志', '更新部门', 1, 'com.stylefeng.guns.modular.system.controller.DeptController', 'update', '2018-08-13 11:52:18', '成功', '更新前：Dept{id=30, num=7, pid=29, pids=\'[0],[29],\', simplename=\'CS\', fullname=\'SDSAD\', tips=\'34\', version=null};;;更新后：Dept{id=30, num=7, pid=29, pids=\'[0],[29],\', simplename=\'CS\', fullname=\'SDSAD\', tips=\'啊啊\', version=null}');

-- ----------------------------
-- Table structure for sys_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_relation`;
CREATE TABLE `sys_relation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menuid` bigint(11) DEFAULT NULL COMMENT '菜单id',
  `roleid` int(11) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4376 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_relation
-- ----------------------------
INSERT INTO `sys_relation` VALUES (3377, 105, 5);
INSERT INTO `sys_relation` VALUES (3378, 106, 5);
INSERT INTO `sys_relation` VALUES (3379, 107, 5);
INSERT INTO `sys_relation` VALUES (3380, 108, 5);
INSERT INTO `sys_relation` VALUES (3381, 109, 5);
INSERT INTO `sys_relation` VALUES (3382, 110, 5);
INSERT INTO `sys_relation` VALUES (3383, 111, 5);
INSERT INTO `sys_relation` VALUES (3384, 112, 5);
INSERT INTO `sys_relation` VALUES (3385, 113, 5);
INSERT INTO `sys_relation` VALUES (3386, 114, 5);
INSERT INTO `sys_relation` VALUES (3387, 115, 5);
INSERT INTO `sys_relation` VALUES (3388, 116, 5);
INSERT INTO `sys_relation` VALUES (3389, 117, 5);
INSERT INTO `sys_relation` VALUES (3390, 118, 5);
INSERT INTO `sys_relation` VALUES (3391, 119, 5);
INSERT INTO `sys_relation` VALUES (3392, 120, 5);
INSERT INTO `sys_relation` VALUES (3393, 121, 5);
INSERT INTO `sys_relation` VALUES (3394, 122, 5);
INSERT INTO `sys_relation` VALUES (3395, 150, 5);
INSERT INTO `sys_relation` VALUES (3396, 151, 5);
INSERT INTO `sys_relation` VALUES (3792, 138, 6);
INSERT INTO `sys_relation` VALUES (3793, 139, 6);
INSERT INTO `sys_relation` VALUES (3794, 141, 6);
INSERT INTO `sys_relation` VALUES (3795, 145, 6);
INSERT INTO `sys_relation` VALUES (3796, 148, 6);
INSERT INTO `sys_relation` VALUES (3797, 149, 6);
INSERT INTO `sys_relation` VALUES (4293, 105, 1);
INSERT INTO `sys_relation` VALUES (4294, 106, 1);
INSERT INTO `sys_relation` VALUES (4295, 107, 1);
INSERT INTO `sys_relation` VALUES (4296, 108, 1);
INSERT INTO `sys_relation` VALUES (4297, 109, 1);
INSERT INTO `sys_relation` VALUES (4298, 110, 1);
INSERT INTO `sys_relation` VALUES (4299, 111, 1);
INSERT INTO `sys_relation` VALUES (4300, 112, 1);
INSERT INTO `sys_relation` VALUES (4301, 113, 1);
INSERT INTO `sys_relation` VALUES (4302, 114, 1);
INSERT INTO `sys_relation` VALUES (4303, 115, 1);
INSERT INTO `sys_relation` VALUES (4304, 116, 1);
INSERT INTO `sys_relation` VALUES (4305, 117, 1);
INSERT INTO `sys_relation` VALUES (4306, 118, 1);
INSERT INTO `sys_relation` VALUES (4307, 119, 1);
INSERT INTO `sys_relation` VALUES (4308, 120, 1);
INSERT INTO `sys_relation` VALUES (4309, 121, 1);
INSERT INTO `sys_relation` VALUES (4310, 122, 1);
INSERT INTO `sys_relation` VALUES (4311, 128, 1);
INSERT INTO `sys_relation` VALUES (4312, 130, 1);
INSERT INTO `sys_relation` VALUES (4313, 131, 1);
INSERT INTO `sys_relation` VALUES (4314, 132, 1);
INSERT INTO `sys_relation` VALUES (4315, 133, 1);
INSERT INTO `sys_relation` VALUES (4316, 134, 1);
INSERT INTO `sys_relation` VALUES (4317, 135, 1);
INSERT INTO `sys_relation` VALUES (4318, 136, 1);
INSERT INTO `sys_relation` VALUES (4319, 137, 1);
INSERT INTO `sys_relation` VALUES (4320, 138, 1);
INSERT INTO `sys_relation` VALUES (4321, 139, 1);
INSERT INTO `sys_relation` VALUES (4322, 140, 1);
INSERT INTO `sys_relation` VALUES (4323, 141, 1);
INSERT INTO `sys_relation` VALUES (4324, 142, 1);
INSERT INTO `sys_relation` VALUES (4325, 143, 1);
INSERT INTO `sys_relation` VALUES (4326, 144, 1);
INSERT INTO `sys_relation` VALUES (4327, 145, 1);
INSERT INTO `sys_relation` VALUES (4328, 148, 1);
INSERT INTO `sys_relation` VALUES (4329, 149, 1);
INSERT INTO `sys_relation` VALUES (4330, 150, 1);
INSERT INTO `sys_relation` VALUES (4331, 151, 1);
INSERT INTO `sys_relation` VALUES (4332, 152, 1);
INSERT INTO `sys_relation` VALUES (4333, 153, 1);
INSERT INTO `sys_relation` VALUES (4334, 154, 1);
INSERT INTO `sys_relation` VALUES (4335, 155, 1);
INSERT INTO `sys_relation` VALUES (4336, 156, 1);
INSERT INTO `sys_relation` VALUES (4337, 157, 1);
INSERT INTO `sys_relation` VALUES (4338, 158, 1);
INSERT INTO `sys_relation` VALUES (4339, 159, 1);
INSERT INTO `sys_relation` VALUES (4340, 160, 1);
INSERT INTO `sys_relation` VALUES (4341, 161, 1);
INSERT INTO `sys_relation` VALUES (4342, 162, 1);
INSERT INTO `sys_relation` VALUES (4343, 163, 1);
INSERT INTO `sys_relation` VALUES (4344, 164, 1);
INSERT INTO `sys_relation` VALUES (4345, 165, 1);
INSERT INTO `sys_relation` VALUES (4346, 166, 1);
INSERT INTO `sys_relation` VALUES (4347, 167, 1);
INSERT INTO `sys_relation` VALUES (4348, 168, 1);
INSERT INTO `sys_relation` VALUES (4349, 169, 1);
INSERT INTO `sys_relation` VALUES (4350, 170, 1);
INSERT INTO `sys_relation` VALUES (4351, 171, 1);
INSERT INTO `sys_relation` VALUES (4352, 172, 1);
INSERT INTO `sys_relation` VALUES (4353, 173, 1);
INSERT INTO `sys_relation` VALUES (4354, 174, 1);
INSERT INTO `sys_relation` VALUES (4355, 175, 1);
INSERT INTO `sys_relation` VALUES (4356, 176, 1);
INSERT INTO `sys_relation` VALUES (4357, 177, 1);
INSERT INTO `sys_relation` VALUES (4358, 178, 1);
INSERT INTO `sys_relation` VALUES (4359, 179, 1);
INSERT INTO `sys_relation` VALUES (4360, 181, 1);
INSERT INTO `sys_relation` VALUES (4361, 182, 1);
INSERT INTO `sys_relation` VALUES (4362, 183, 1);
INSERT INTO `sys_relation` VALUES (4363, 184, 1);
INSERT INTO `sys_relation` VALUES (4364, 185, 1);
INSERT INTO `sys_relation` VALUES (4365, 186, 1);
INSERT INTO `sys_relation` VALUES (4366, 187, 1);
INSERT INTO `sys_relation` VALUES (4367, 188, 1);
INSERT INTO `sys_relation` VALUES (4368, 189, 1);
INSERT INTO `sys_relation` VALUES (4369, 0, 1);
INSERT INTO `sys_relation` VALUES (4370, 141, 8);
INSERT INTO `sys_relation` VALUES (4371, 173, 8);
INSERT INTO `sys_relation` VALUES (4372, 174, 8);
INSERT INTO `sys_relation` VALUES (4373, 170, 8);
INSERT INTO `sys_relation` VALUES (4374, 175, 8);
INSERT INTO `sys_relation` VALUES (4375, 171, 8);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `num` int(11) DEFAULT NULL COMMENT '序号',
  `pid` int(11) DEFAULT NULL COMMENT '父角色id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名称',
  `deptid` int(11) DEFAULT NULL COMMENT '部门名称',
  `tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '提示',
  `version` int(11) DEFAULT NULL COMMENT '保留字段(暂时没用）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 0, 0, '超级管理员', 24, 'administrator', 1);
INSERT INTO `sys_role` VALUES (5, 6, 1, '临时工', 26, 'temp', NULL);
INSERT INTO `sys_role` VALUES (6, 4, 1, '开发工程师', 25, 'dev', NULL);
INSERT INTO `sys_role` VALUES (8, 5, 1, '卖家管理员', 29, 'seller', NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  `account` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '账号',
  `password` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `salt` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'md5密码盐',
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名字',
  `birthday` datetime(0) DEFAULT NULL COMMENT '生日',
  `sex` int(11) DEFAULT NULL COMMENT '性别（1：男 2：女）',
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电子邮件',
  `phone` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话',
  `roleid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色id',
  `deptid` int(11) DEFAULT NULL COMMENT '部门id',
  `status` int(11) DEFAULT NULL COMMENT '状态(1：启用  2：冻结  3：删除）',
  `createtime` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `version` int(11) DEFAULT NULL COMMENT '保留字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'ab563428-dd55-4c17-a6e3-df93f9a0979d.jpg', 'admin', '6cd3423f077da4d9465408654b7366ef', '8pgby', 'TC', '2017-05-05 00:00:00', 1, 'a@s', '18200000000', '1', 24, 1, '2016-01-29 08:49:53', 25);
INSERT INTO `sys_user` VALUES (2, NULL, 'test', '45abb7879f6a8268f1ef600e6038ac73', 'ssts3', 'test', '2017-05-01 00:00:00', 1, 'abc@123.com', 'www', '5,', 25, 3, '2017-05-16 20:33:37', NULL);
INSERT INTO `sys_user` VALUES (3, NULL, 'boss', '71887a5ad666a18f709e1d4e693d5a35', '1f7bf', '老板', '2017-12-04 00:00:00', 1, '', '', '1', 24, 1, '2017-12-04 22:24:02', NULL);
INSERT INTO `sys_user` VALUES (4, NULL, 'manager', 'b53cac62e7175637d4beb3b16b2f7915', 'j3cs9', '经理', '2017-12-04 00:00:00', 1, '', '', '1', 28, 1, '2017-12-04 22:24:24', NULL);
INSERT INTO `sys_user` VALUES (5, '', 'tan', '4772c53d942ff8a27e5a88676d7b7cb1', 'nfukw', 'TC', '2018-08-01 00:00:00', 1, 'aa@bb.com', '123', '8', 29, 1, '2018-07-22 17:23:52', NULL);

-- ----------------------------
-- Table structure for sys_user_seller
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_seller`;
CREATE TABLE `sys_user_seller`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` int(11) DEFAULT NULL COMMENT '用户ID',
  `sellerid` int(11) DEFAULT NULL COMMENT '卖家ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3924 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户的卖家范围表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_seller
-- ----------------------------
INSERT INTO `sys_user_seller` VALUES (3377, 105, 5);
INSERT INTO `sys_user_seller` VALUES (3378, 106, 5);
INSERT INTO `sys_user_seller` VALUES (3379, 107, 5);
INSERT INTO `sys_user_seller` VALUES (3380, 108, 5);
INSERT INTO `sys_user_seller` VALUES (3381, 109, 5);
INSERT INTO `sys_user_seller` VALUES (3382, 110, 5);
INSERT INTO `sys_user_seller` VALUES (3383, 111, 5);
INSERT INTO `sys_user_seller` VALUES (3384, 112, 5);
INSERT INTO `sys_user_seller` VALUES (3385, 113, 5);
INSERT INTO `sys_user_seller` VALUES (3386, 114, 5);
INSERT INTO `sys_user_seller` VALUES (3387, 115, 5);
INSERT INTO `sys_user_seller` VALUES (3388, 116, 5);
INSERT INTO `sys_user_seller` VALUES (3389, 117, 5);
INSERT INTO `sys_user_seller` VALUES (3390, 118, 5);
INSERT INTO `sys_user_seller` VALUES (3391, 119, 5);
INSERT INTO `sys_user_seller` VALUES (3392, 120, 5);
INSERT INTO `sys_user_seller` VALUES (3393, 121, 5);
INSERT INTO `sys_user_seller` VALUES (3394, 122, 5);
INSERT INTO `sys_user_seller` VALUES (3395, 150, 5);
INSERT INTO `sys_user_seller` VALUES (3396, 151, 5);
INSERT INTO `sys_user_seller` VALUES (3792, 138, 6);
INSERT INTO `sys_user_seller` VALUES (3793, 139, 6);
INSERT INTO `sys_user_seller` VALUES (3794, 141, 6);
INSERT INTO `sys_user_seller` VALUES (3795, 145, 6);
INSERT INTO `sys_user_seller` VALUES (3796, 148, 6);
INSERT INTO `sys_user_seller` VALUES (3797, 149, 6);
INSERT INTO `sys_user_seller` VALUES (3854, 105, 1);
INSERT INTO `sys_user_seller` VALUES (3855, 106, 1);
INSERT INTO `sys_user_seller` VALUES (3856, 107, 1);
INSERT INTO `sys_user_seller` VALUES (3857, 108, 1);
INSERT INTO `sys_user_seller` VALUES (3858, 109, 1);
INSERT INTO `sys_user_seller` VALUES (3859, 110, 1);
INSERT INTO `sys_user_seller` VALUES (3860, 111, 1);
INSERT INTO `sys_user_seller` VALUES (3861, 112, 1);
INSERT INTO `sys_user_seller` VALUES (3862, 113, 1);
INSERT INTO `sys_user_seller` VALUES (3863, 114, 1);
INSERT INTO `sys_user_seller` VALUES (3864, 115, 1);
INSERT INTO `sys_user_seller` VALUES (3865, 116, 1);
INSERT INTO `sys_user_seller` VALUES (3866, 117, 1);
INSERT INTO `sys_user_seller` VALUES (3867, 118, 1);
INSERT INTO `sys_user_seller` VALUES (3868, 119, 1);
INSERT INTO `sys_user_seller` VALUES (3869, 120, 1);
INSERT INTO `sys_user_seller` VALUES (3870, 121, 1);
INSERT INTO `sys_user_seller` VALUES (3871, 122, 1);
INSERT INTO `sys_user_seller` VALUES (3872, 128, 1);
INSERT INTO `sys_user_seller` VALUES (3873, 130, 1);
INSERT INTO `sys_user_seller` VALUES (3874, 131, 1);
INSERT INTO `sys_user_seller` VALUES (3875, 132, 1);
INSERT INTO `sys_user_seller` VALUES (3876, 133, 1);
INSERT INTO `sys_user_seller` VALUES (3877, 134, 1);
INSERT INTO `sys_user_seller` VALUES (3878, 135, 1);
INSERT INTO `sys_user_seller` VALUES (3879, 136, 1);
INSERT INTO `sys_user_seller` VALUES (3880, 137, 1);
INSERT INTO `sys_user_seller` VALUES (3881, 138, 1);
INSERT INTO `sys_user_seller` VALUES (3882, 139, 1);
INSERT INTO `sys_user_seller` VALUES (3883, 140, 1);
INSERT INTO `sys_user_seller` VALUES (3884, 141, 1);
INSERT INTO `sys_user_seller` VALUES (3885, 142, 1);
INSERT INTO `sys_user_seller` VALUES (3886, 143, 1);
INSERT INTO `sys_user_seller` VALUES (3887, 144, 1);
INSERT INTO `sys_user_seller` VALUES (3888, 145, 1);
INSERT INTO `sys_user_seller` VALUES (3889, 148, 1);
INSERT INTO `sys_user_seller` VALUES (3890, 149, 1);
INSERT INTO `sys_user_seller` VALUES (3891, 150, 1);
INSERT INTO `sys_user_seller` VALUES (3892, 151, 1);
INSERT INTO `sys_user_seller` VALUES (3893, 152, 1);
INSERT INTO `sys_user_seller` VALUES (3894, 153, 1);
INSERT INTO `sys_user_seller` VALUES (3895, 154, 1);
INSERT INTO `sys_user_seller` VALUES (3896, 155, 1);
INSERT INTO `sys_user_seller` VALUES (3897, 156, 1);
INSERT INTO `sys_user_seller` VALUES (3898, 157, 1);
INSERT INTO `sys_user_seller` VALUES (3899, 158, 1);
INSERT INTO `sys_user_seller` VALUES (3900, 159, 1);
INSERT INTO `sys_user_seller` VALUES (3901, 160, 1);
INSERT INTO `sys_user_seller` VALUES (3902, 161, 1);
INSERT INTO `sys_user_seller` VALUES (3903, 162, 1);
INSERT INTO `sys_user_seller` VALUES (3904, 163, 1);
INSERT INTO `sys_user_seller` VALUES (3905, 164, 1);
INSERT INTO `sys_user_seller` VALUES (3906, 165, 1);
INSERT INTO `sys_user_seller` VALUES (3907, 166, 1);
INSERT INTO `sys_user_seller` VALUES (3908, 167, 1);
INSERT INTO `sys_user_seller` VALUES (3909, 168, 1);
INSERT INTO `sys_user_seller` VALUES (3910, 169, 1);
INSERT INTO `sys_user_seller` VALUES (3911, 170, 1);
INSERT INTO `sys_user_seller` VALUES (3912, 171, 1);
INSERT INTO `sys_user_seller` VALUES (3913, 172, 1);
INSERT INTO `sys_user_seller` VALUES (3914, 173, 1);
INSERT INTO `sys_user_seller` VALUES (3915, 174, 1);
INSERT INTO `sys_user_seller` VALUES (3916, 175, 1);
INSERT INTO `sys_user_seller` VALUES (3918, 47, 13);
INSERT INTO `sys_user_seller` VALUES (3921, 2, 15);
INSERT INTO `sys_user_seller` VALUES (3922, 5, 11);
INSERT INTO `sys_user_seller` VALUES (3923, 5, 12);

SET FOREIGN_KEY_CHECKS = 1;
