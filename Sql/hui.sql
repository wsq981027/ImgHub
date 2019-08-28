/*
 Navicat Premium Data Transfer

 Source Server         : JavaSSM
 Source Server Type    : MySQL
 Source Server Version : 50153
 Source Host           : localhost:3306
 Source Schema         : hui

 Target Server Type    : MySQL
 Target Server Version : 50153
 File Encoding         : 65001

 Date: 28/08/2019 16:26:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admins
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins`  (
  `ID` int(8) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PASSWORD` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PIC` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admins
-- ----------------------------
INSERT INTO `admins` VALUES (1, 'admin', '123456', 'defaultPic.jpg');

-- ----------------------------
-- Table structure for attentions
-- ----------------------------
DROP TABLE IF EXISTS `attentions`;
CREATE TABLE `attentions`  (
  `ID` int(8) NOT NULL AUTO_INCREMENT,
  `ATID` decimal(8, 0) NOT NULL,
  `FANID` decimal(8, 0) NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of attentions
-- ----------------------------
INSERT INTO `attentions` VALUES (1, 3, 4);
INSERT INTO `attentions` VALUES (22, 5, 3);
INSERT INTO `attentions` VALUES (23, 4, 3);

-- ----------------------------
-- Table structure for auditimages
-- ----------------------------
DROP TABLE IF EXISTS `auditimages`;
CREATE TABLE `auditimages`  (
  `ID` int(8) NOT NULL AUTO_INCREMENT,
  `IMGNAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TITLE` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INTRO` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `USERID` decimal(8, 0) NOT NULL,
  `TYPEID` decimal(8, 0) NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for collects
-- ----------------------------
DROP TABLE IF EXISTS `collects`;
CREATE TABLE `collects`  (
  `ID` int(8) NOT NULL AUTO_INCREMENT,
  `IMGID` decimal(8, 0) NOT NULL,
  `USERID` decimal(8, 0) NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 84 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of collects
-- ----------------------------
INSERT INTO `collects` VALUES (47, 62, 3);
INSERT INTO `collects` VALUES (50, 61, 3);
INSERT INTO `collects` VALUES (51, 14, 3);
INSERT INTO `collects` VALUES (61, 12, 3);
INSERT INTO `collects` VALUES (62, 13, 3);
INSERT INTO `collects` VALUES (81, 4, 4);
INSERT INTO `collects` VALUES (82, 82, 3);
INSERT INTO `collects` VALUES (83, 83, 3);

-- ----------------------------
-- Table structure for images
-- ----------------------------
DROP TABLE IF EXISTS `images`;
CREATE TABLE `images`  (
  `ID` int(8) NOT NULL AUTO_INCREMENT,
  `IMGNAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TITLE` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INTRO` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `THUMB` decimal(8, 0) NOT NULL DEFAULT 0,
  `USERID` decimal(8, 0) NULL DEFAULT NULL,
  `TYPEID` decimal(8, 0) NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 84 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of images
-- ----------------------------
INSERT INTO `images` VALUES (2, '1563519460327.jpg', 'T2', 't2', 0, 3, 2);
INSERT INTO `images` VALUES (3, '1563520569949.jpg', 'T3', 't3', 1, 3, 2);
INSERT INTO `images` VALUES (4, '1563524107931.jpg', 'T4', 't4', 2, 3, 2);
INSERT INTO `images` VALUES (5, '1563524142736.png', 'T5', 't5', 1, 4, 2);
INSERT INTO `images` VALUES (6, '1563526079218.jpg', 'T6', 't6', 0, 4, 3);
INSERT INTO `images` VALUES (7, '1563526097502.jpg', 'T7', 't7', 0, 5, 4);
INSERT INTO `images` VALUES (8, '1563526195136.jpg', 'T8', 't8', 0, 5, 5);
INSERT INTO `images` VALUES (9, '1563526547847.jpg', 'T9', 't9', 1, 4, 3);
INSERT INTO `images` VALUES (10, '1563526698722.png', 'T10', 't10', 0, 3, 4);
INSERT INTO `images` VALUES (11, '1563526932981.jpg', 'T11', 't11', 1, 5, 3);
INSERT INTO `images` VALUES (12, '1563527344449.png', 'T12', 't12', 1, 4, 4);
INSERT INTO `images` VALUES (13, '1563527827275.png', 'T13', 't13', 1, 5, 4);
INSERT INTO `images` VALUES (14, '1563527916517.jpg', 'T14', 't14', 0, 3, 3);
INSERT INTO `images` VALUES (15, '1563762675501.jpg', 'T15', 't15', 0, 4, 5);
INSERT INTO `images` VALUES (22, '1563787828919.jpg', 'qew', 'jjj', 0, 3, 3);
INSERT INTO `images` VALUES (23, '1563842555276.jpg', 'hhhh', 'hhhh', 0, 3, 5);
INSERT INTO `images` VALUES (24, '1563842686478.png', '一张图', '哈哈哈', 0, 3, 5);
INSERT INTO `images` VALUES (42, '1564106415357.jpg', '哈登', '哈登', 0, 3, 2);
INSERT INTO `images` VALUES (43, '1564107048550.jpg', 'eve', 'eve', 0, 3, 5);
INSERT INTO `images` VALUES (61, '1564126392917.jpg', '萌神', '库里', 0, 3, 2);
INSERT INTO `images` VALUES (62, '1564126434728.jpg', '库里过人', '库里', 1, 3, 2);
INSERT INTO `images` VALUES (81, '1564541146488.jpg', '欧文', '欧文关键球', 0, 3, 2);
INSERT INTO `images` VALUES (82, '1564556090618.jpg', '科比', '骑扣霍华德', 1, 3, 2);
INSERT INTO `images` VALUES (83, '1564556101706.jpg', '科比', '隔扣', 1, 3, 2);

-- ----------------------------
-- Table structure for points
-- ----------------------------
DROP TABLE IF EXISTS `points`;
CREATE TABLE `points`  (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `uid` int(8) NULL DEFAULT NULL,
  `point` int(8) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of points
-- ----------------------------
INSERT INTO `points` VALUES (1, 3, 6);
INSERT INTO `points` VALUES (2, 4, 7);
INSERT INTO `points` VALUES (3, 5, 10);
INSERT INTO `points` VALUES (4, 23, 10);
INSERT INTO `points` VALUES (5, 24, 10);

-- ----------------------------
-- Table structure for purchase
-- ----------------------------
DROP TABLE IF EXISTS `purchase`;
CREATE TABLE `purchase`  (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `imgid` int(8) NULL DEFAULT NULL,
  `uid` int(8) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of purchase
-- ----------------------------
INSERT INTO `purchase` VALUES (1, 4, 3);
INSERT INTO `purchase` VALUES (2, 5, 3);
INSERT INTO `purchase` VALUES (3, 82, 3);
INSERT INTO `purchase` VALUES (5, 81, 3);
INSERT INTO `purchase` VALUES (6, 2, 3);
INSERT INTO `purchase` VALUES (7, 62, 3);
INSERT INTO `purchase` VALUES (8, 61, 3);
INSERT INTO `purchase` VALUES (9, 81, 4);
INSERT INTO `purchase` VALUES (10, 62, 4);
INSERT INTO `purchase` VALUES (11, 61, 4);

-- ----------------------------
-- Table structure for thumb
-- ----------------------------
DROP TABLE IF EXISTS `thumb`;
CREATE TABLE `thumb`  (
  `ID` int(8) NOT NULL AUTO_INCREMENT,
  `IMGID` decimal(8, 0) NULL DEFAULT NULL,
  `USERID` decimal(8, 0) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 84 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of thumb
-- ----------------------------
INSERT INTO `thumb` VALUES (2, 3, 3);
INSERT INTO `thumb` VALUES (3, 4, 3);
INSERT INTO `thumb` VALUES (21, 16, 3);
INSERT INTO `thumb` VALUES (22, 11, 3);
INSERT INTO `thumb` VALUES (23, 9, 3);
INSERT INTO `thumb` VALUES (44, 5, 3);
INSERT INTO `thumb` VALUES (46, 62, 3);
INSERT INTO `thumb` VALUES (62, 12, 3);
INSERT INTO `thumb` VALUES (63, 13, 3);
INSERT INTO `thumb` VALUES (81, 4, 4);
INSERT INTO `thumb` VALUES (82, 82, 3);
INSERT INTO `thumb` VALUES (83, 83, 3);

-- ----------------------------
-- Table structure for types
-- ----------------------------
DROP TABLE IF EXISTS `types`;
CREATE TABLE `types`  (
  `ID` int(8) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of types
-- ----------------------------
INSERT INTO `types` VALUES (2, '篮球');
INSERT INTO `types` VALUES (3, '足球');
INSERT INTO `types` VALUES (4, '电影');
INSERT INTO `types` VALUES (5, '游戏');
INSERT INTO `types` VALUES (6, '风景');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `ID` int(8) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PASSWORD` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PIC` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'defaultPic.jpg',
  `BAN` decimal(1, 0) NOT NULL DEFAULT 0,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (3, 'wzh', 'XAwfCUyMEUP4vm+t9jJLVA==', '1564365884611.jpg', 0);
INSERT INTO `users` VALUES (4, 'kok', '4QrcOUm6Wau+VuBX8g+IPg==', 'defaultPic.jpg', 0);
INSERT INTO `users` VALUES (5, 'dqb', 'ICy5YqxZB1uWSwcVLSNLcA==', 'defaultPic.jpg', 0);
INSERT INTO `users` VALUES (23, 'qq2', 'ICy5YqxZB1uWSwcVLSNLcA==', 'defaultPic.jpg', 1);
INSERT INTO `users` VALUES (24, 'newstar', 'XAwfCUyMEUP4vm+t9jJLVA==', 'defaultPic.jpg', 0);

SET FOREIGN_KEY_CHECKS = 1;
