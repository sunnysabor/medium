-- ----------------------------
-- 创建用户表
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `dept_id` bigint(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(255) DEFAULT NULL COMMENT '状态 0:禁用，1:正常',
  `user_id_create` bigint(255) DEFAULT NULL COMMENT '创建用户id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `sex` bigint(32) DEFAULT NULL COMMENT '性别',
  `birth` datetime DEFAULT NULL COMMENT '出身日期',
  `pic_id` bigint(32) DEFAULT NULL,
  `live_address` varchar(500) DEFAULT NULL COMMENT '现居住地',
  `hobby` varchar(255) DEFAULT NULL COMMENT '爱好',
  `province` varchar(255) DEFAULT NULL COMMENT '省份',
  `city` varchar(255) DEFAULT NULL COMMENT '所在城市',
  `district` varchar(255) DEFAULT NULL COMMENT '所在地区',
  `identity` varchar(255) DEFAULT NULL COMMENT '用户身份',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '超级管理员',
'd633268afedf209e1e4ea0f5f43228a8', '6', '1394367234@qq.com', '17699999999',
'1', '1', '2018-07-15 21:40:39', '2018-07-15 21:41:00', '96', '2018-06-15 00:00:00', '138', 'ccc', '122;121;', '上海市', '上海市郊区', '闵行区','admin');
INSERT INTO `sys_user` VALUES ('2', 'test', '临时用户',
'6cf3bb3deba2aadbd41ec9a22511084e', '6', '1394367234@qq.com', null, '1',
'1', '2018-07-14 13:43:05', '2018-07-14 21:15:36', null, null, null, null,
null, null, null, null,'user');


-- ----------------------------
-- 创建文件表
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL COMMENT '文件类型',
  `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `status` varchar(200) DEFAULT NULL COMMENT '状态',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8 COMMENT='文件上传';

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES ('110', '0', '/files/d64a62e3-6821-48f1-bac6-a1b9945f4afb.jpg', '2017-10-14 16:20:17','toAudit',null);
INSERT INTO `sys_file` VALUES ('111', '0', '/files/aa2c3dc6-495f-48cc-8e12-446eceb2535e.jpg', '2017-10-14 16:20:21','toAudit',null);
INSERT INTO `sys_file` VALUES ('118', '0', '/files/a973499e-3ec7-4d43-8a52-b6f6517c77e3.jpg', '2017-10-20 11:53:52','toAudit',null);
INSERT INTO `sys_file` VALUES ('132', '0', '/files/e6f13526-e31c-4ebe-a3cf-5fd88dd10be6.jpg', '2017-12-18 20:19:51','toAudit',null);
INSERT INTO `sys_file` VALUES ('134', '0', '/files/cd016e72-77f7-4425-afe2-b79dfbdc3ae9.jpeg', '2017-12-18 22:44:07','toAudit',null);
INSERT INTO `sys_file` VALUES ('138', '0', '/files/9ec12ee7-65b5-4cc5-9900-d2ec6185b336.jpg', '2017-12-19 19:55:27','toAudit',null);
INSERT INTO `sys_file` VALUES ('139', '0', '/files/f6aab9d4-00a1-4bc8-a695-40fc472d4ea9.jpg', '2018-01-02 19:53:24','toAudit',null);
INSERT INTO `sys_file` VALUES ('141', '0', '/files/445c5d98-b3b6-4226-a5af-249d1b2a6a34.jpg', '2018-01-09 19:28:53','toAudit',null);

-- ----------------------------
-- 创建菜单表
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '用户信息', '', '', '0', 'fa fa-bars', '1', '2017-08-09 22:49:47', null);
INSERT INTO `sys_menu` VALUES ('11', '1', '用户管理', 'sys/user/', 'sys:user:user', '1', 'fa fa-user', '0', '2017-08-10 14:12:11', null);
INSERT INTO `sys_menu` VALUES ('12', '1', '充值管理', 'sys/recharge/', 'sys:recharge:recharge', '2', 'fa fa-user', '0', '2017-08-10 14:12:11', null);
INSERT INTO `sys_menu` VALUES ('2', '0', '多媒体管理', '', '', '0', 'fa fa-bars', '2', '2017-08-09 22:49:47', null);
INSERT INTO `sys_menu` VALUES ('21', '2', '多媒体管理', '/common/sysFile','common:sysFile:sysFile', '1', 'fa fa-folder-open', '0', '2017-08-10 14:12:11', null);
INSERT INTO `sys_menu` VALUES ('22', '2', '评论管理', 'common/comment',
'common:comment:comment', '1', 'fa fa-user', '0', '2017-08-10 14:12:11', null);
INSERT INTO `sys_menu` VALUES ('23', '2', '收藏管理', 'common/collection',
'common:collection:collection', '1', 'fa fa-user', '0', '2017-08-10 14:12:11',
null);
INSERT INTO `sys_menu` VALUES ('3', '0', '征集信息管理', '', '', '0', 'fa fa-bars',
 '3', '2017-08-09 22:49:47', null);
INSERT INTO `sys_menu` VALUES ('31', '3', '征集表', 'common/consult',
'common:consult:consult', '1', 'fa fa-user', '0', '2017-08-10 14:12:11', null);

DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `time` int(11) DEFAULT NULL COMMENT '响应时间',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=609 DEFAULT CHARSET=utf8 COMMENT='系统日志';

DROP TABLE IF EXISTS `sys_recharge`;
CREATE TABLE `sys_recharge` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `type` varchar(50) DEFAULT NULL COMMENT '充值类型',
  `begin_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `amount` bigint(20) DEFAULT NULL COMMENT '充值金额',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=609 DEFAULT CHARSET=utf8 COMMENT='充值记录';

DROP TABLE IF EXISTS `sys_collection`;
CREATE TABLE `sys_collection` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `file_id` varchar(50) DEFAULT NULL COMMENT '多媒体id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=609 DEFAULT CHARSET=utf8 COMMENT='收藏记录';

DROP TABLE IF EXISTS `sys_comment`;
CREATE TABLE `sys_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `file_id` bigint(20) DEFAULT NULL COMMENT '多媒体id',
  `content` varchar(5000) DEFAULT NULL COMMENT '内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=609 DEFAULT CHARSET=utf8 COMMENT='评论';

DROP TABLE IF EXISTS `sys_consult`;
CREATE TABLE `sys_consult` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `content` varchar(5000) DEFAULT NULL COMMENT '征集内容',
  `readed` bigint(20) DEFAULT 0 COMMENT '阅读量',
  `gooded` bigint(20) DEFAULT 0 COMMENT '点赞数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=609 DEFAULT CHARSET=utf8 COMMENT='征集';