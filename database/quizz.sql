/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : quizz

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 21/12/2018 08:55:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_quizz_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_quizz_info`;
CREATE TABLE `tb_quizz_info`  (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `quizz_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '测试名称',
  `quizz_comment` tinytext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  `set_time` int(2) NULL DEFAULT 0 COMMENT '设置考试答题时间，0不设置，1设置',
  `quizz_time` int(5) NULL DEFAULT 0 COMMENT '答题时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `is_delete` int(2) NOT NULL DEFAULT 0 COMMENT '是否删除，1删除，0没有',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `create_user`(`create_user`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '测验信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_quizz_question
-- ----------------------------
DROP TABLE IF EXISTS `tb_quizz_question`;
CREATE TABLE `tb_quizz_question`  (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `test_id` int(5) NOT NULL DEFAULT 0 COMMENT '测验id',
  `question_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '题目名称',
  `analysis` tinytext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '题目解析',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `is_delete` int(2) NOT NULL DEFAULT 0 COMMENT '是否删除，1删除，0没有',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `create_user`(`create_user`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 82 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '测试题目' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_quizz_question_relation
-- ----------------------------
DROP TABLE IF EXISTS `tb_quizz_question_relation`;
CREATE TABLE `tb_quizz_question_relation`  (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `quizz_id` int(5) NOT NULL COMMENT '题目名称',
  `question_id` int(5) NULL DEFAULT NULL COMMENT '题目解析',
  `create_user` int(5) NOT NULL COMMENT '创建用户',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `create_user`(`quizz_id`) USING BTREE,
  INDEX `create_time`(`question_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '测试题目关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_quizz_question_selection
-- ----------------------------
DROP TABLE IF EXISTS `tb_quizz_question_selection`;
CREATE TABLE `tb_quizz_question_selection`  (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `question_id` int(5) NOT NULL DEFAULT 0 COMMENT '题目id',
  `selecion` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选项',
  `is_correct` int(2) NULL DEFAULT 0 COMMENT '是否是正确选项',
  `selection_content` tinytext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '选项内容',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `is_delete` int(2) NOT NULL DEFAULT 0 COMMENT '是否删除，1删除，0没有',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `create_user`(`create_user`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 136 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '测试选项表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_quizz_student_answer
-- ----------------------------
DROP TABLE IF EXISTS `tb_quizz_student_answer`;
CREATE TABLE `tb_quizz_student_answer`  (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '微信openid',
  `question_id` int(5) NOT NULL COMMENT '问题id',
  `test_id` int(5) NOT NULL COMMENT '考试id',
  `is_correct` int(2) NOT NULL COMMENT '是否正确',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `is_delete` int(2) NOT NULL DEFAULT 0 COMMENT '是否删除，1删除，0没有',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 163 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学生考试答题表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_quizz_teacher_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_quizz_teacher_user`;
CREATE TABLE `tb_quizz_teacher_user`  (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `is_delete` int(2) NOT NULL DEFAULT 0 COMMENT '是否删除，1删除，0没有',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '教师端用户表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
