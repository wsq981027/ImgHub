/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80019
Source Host           : localhost:3306
Source Database       : hui

Target Server Type    : MYSQL
Target Server Version : 80019
File Encoding         : 65001

Date: 2020-03-13 15:16:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admins`
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PASSWORD` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PIC` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of admins
-- ----------------------------
INSERT INTO `admins` VALUES ('1', 'admin', '123456', 'defaultPic.jpg');

-- ----------------------------
-- Table structure for `attentions`
-- ----------------------------
DROP TABLE IF EXISTS `attentions`;
CREATE TABLE `attentions` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `ATID` decimal(8,0) NOT NULL,
  `FANID` decimal(8,0) NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of attentions
-- ----------------------------
INSERT INTO `attentions` VALUES ('1', '3', '4');
INSERT INTO `attentions` VALUES ('22', '5', '3');
INSERT INTO `attentions` VALUES ('24', '4', '3');

-- ----------------------------
-- Table structure for `auditimages`
-- ----------------------------
DROP TABLE IF EXISTS `auditimages`;
CREATE TABLE `auditimages` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `IMGNAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TITLE` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INTRO` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `USERID` decimal(8,0) NOT NULL,
  `TYPEID` decimal(8,0) NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of auditimages
-- ----------------------------

-- ----------------------------
-- Table structure for `audittags`
-- ----------------------------
DROP TABLE IF EXISTS `audittags`;
CREATE TABLE `audittags` (
  `id` int NOT NULL AUTO_INCREMENT,
  `imgid` int NOT NULL,
  `name` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of audittags
-- ----------------------------

-- ----------------------------
-- Table structure for `collects`
-- ----------------------------
DROP TABLE IF EXISTS `collects`;
CREATE TABLE `collects` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `IMGID` decimal(8,0) NOT NULL,
  `USERID` decimal(8,0) NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of collects
-- ----------------------------
INSERT INTO `collects` VALUES ('61', '12', '3');
INSERT INTO `collects` VALUES ('62', '13', '3');
INSERT INTO `collects` VALUES ('81', '4', '4');
INSERT INTO `collects` VALUES ('82', '82', '3');
INSERT INTO `collects` VALUES ('83', '83', '3');
INSERT INTO `collects` VALUES ('86', '61', '3');
INSERT INTO `collects` VALUES ('92', '5', '3');
INSERT INTO `collects` VALUES ('94', '14', '4');
INSERT INTO `collects` VALUES ('95', '83', '4');
INSERT INTO `collects` VALUES ('96', '2', '4');

-- ----------------------------
-- Table structure for `comments`
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `imgid` int NOT NULL,
  `userid` int NOT NULL,
  `content` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES ('3', '42', '3', '火箭总冠军！！');
INSERT INTO `comments` VALUES ('4', '42', '3', '13号');
INSERT INTO `comments` VALUES ('5', '3', '3', '又是哈登');
INSERT INTO `comments` VALUES ('6', '2', '3', 'AJ');
INSERT INTO `comments` VALUES ('7', '9', '4', '鹰眼帅爆');
INSERT INTO `comments` VALUES ('10', '24', '3', '看了5遍');
INSERT INTO `comments` VALUES ('12', '4', '3', '乔丹，永远滴神');
INSERT INTO `comments` VALUES ('14', '42', '3', '再试试');
INSERT INTO `comments` VALUES ('16', '5', '3', '妇联');
INSERT INTO `comments` VALUES ('21', '62', '3', '库里');
INSERT INTO `comments` VALUES ('22', '82', '3', '科比');
INSERT INTO `comments` VALUES ('24', '83', '4', 'RIP');
INSERT INTO `comments` VALUES ('26', '42', '4', '威少被过了呀');

-- ----------------------------
-- Table structure for `complains`
-- ----------------------------
DROP TABLE IF EXISTS `complains`;
CREATE TABLE `complains` (
  `id` int NOT NULL AUTO_INCREMENT,
  `comid` int NOT NULL,
  `userid` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of complains
-- ----------------------------
INSERT INTO `complains` VALUES ('2', '3', '3');

-- ----------------------------
-- Table structure for `deposits`
-- ----------------------------
DROP TABLE IF EXISTS `deposits`;
CREATE TABLE `deposits` (
  `id` int NOT NULL AUTO_INCREMENT,
  `uid` int NOT NULL,
  `point` int NOT NULL,
  `money` int NOT NULL,
  `aliname` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of deposits
-- ----------------------------

-- ----------------------------
-- Table structure for `images`
-- ----------------------------
DROP TABLE IF EXISTS `images`;
CREATE TABLE `images` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `IMGNAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TITLE` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INTRO` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `THUMB` decimal(8,0) NOT NULL DEFAULT '0',
  `USERID` decimal(8,0) DEFAULT NULL,
  `TYPEID` decimal(8,0) NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE KEY `id` (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of images
-- ----------------------------
INSERT INTO `images` VALUES ('2', '1563519460327.jpg', 'T2', 't2', '2', '3', '2');
INSERT INTO `images` VALUES ('3', '1563520569949.jpg', 'T3', 't3', '2', '3', '2');
INSERT INTO `images` VALUES ('4', '1563524107931.jpg', 'T4', 't4', '2', '3', '2');
INSERT INTO `images` VALUES ('5', '1563524142736.png', 'T5', 't5', '2', '4', '2');
INSERT INTO `images` VALUES ('6', '1563526079218.jpg', 'T6', 't6', '0', '4', '3');
INSERT INTO `images` VALUES ('7', '1563526097502.jpg', 'T7', 't7', '0', '5', '4');
INSERT INTO `images` VALUES ('8', '1563526195136.jpg', 'T8', 't8', '0', '5', '5');
INSERT INTO `images` VALUES ('9', '1563526547847.jpg', 'T9', 't9', '1', '4', '3');
INSERT INTO `images` VALUES ('10', '1563526698722.png', 'T10', 't10', '0', '3', '4');
INSERT INTO `images` VALUES ('11', '1563526932981.jpg', 'T11', 't11', '1', '5', '3');
INSERT INTO `images` VALUES ('12', '1563527344449.png', 'T12', 't12', '1', '4', '4');
INSERT INTO `images` VALUES ('13', '1563527827275.png', 'T13', 't13', '1', '5', '4');
INSERT INTO `images` VALUES ('14', '1563527916517.jpg', 'T14', 't14', '1', '3', '3');
INSERT INTO `images` VALUES ('15', '1563762675501.jpg', 'T15', 't15', '0', '4', '5');
INSERT INTO `images` VALUES ('22', '1563787828919.jpg', 'qew', 'jjj', '0', '3', '3');
INSERT INTO `images` VALUES ('23', '1563842555276.jpg', 'hhhh', 'hhhh', '0', '3', '5');
INSERT INTO `images` VALUES ('24', '1563842686478.png', '一张图', '哈哈哈', '0', '3', '5');
INSERT INTO `images` VALUES ('42', '1564106415357.jpg', '哈登', '哈登', '1', '3', '2');
INSERT INTO `images` VALUES ('43', '1564107048550.jpg', 'eve', 'eve', '0', '3', '5');
INSERT INTO `images` VALUES ('61', '1564126392917.jpg', '萌神', '库里', '0', '3', '2');
INSERT INTO `images` VALUES ('62', '1564126434728.jpg', '库里过人', '库里', '1', '3', '2');
INSERT INTO `images` VALUES ('81', '1564541146488.jpg', '欧文', '欧文关键球', '0', '3', '2');
INSERT INTO `images` VALUES ('82', '1564556090618.jpg', '科比', '骑扣霍华德', '1', '3', '2');
INSERT INTO `images` VALUES ('83', '1564556101706.jpg', '科比', '隔扣', '2', '3', '2');

-- ----------------------------
-- Table structure for `points`
-- ----------------------------
DROP TABLE IF EXISTS `points`;
CREATE TABLE `points` (
  `id` int NOT NULL AUTO_INCREMENT,
  `uid` int DEFAULT NULL,
  `point` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=FIXED;

-- ----------------------------
-- Records of points
-- ----------------------------
INSERT INTO `points` VALUES ('1', '3', '28');
INSERT INTO `points` VALUES ('2', '4', '8');
INSERT INTO `points` VALUES ('3', '5', '11');
INSERT INTO `points` VALUES ('4', '23', '10');
INSERT INTO `points` VALUES ('5', '24', '9');
INSERT INTO `points` VALUES ('6', '25', '10');
INSERT INTO `points` VALUES ('7', '26', '10');

-- ----------------------------
-- Table structure for `purchase`
-- ----------------------------
DROP TABLE IF EXISTS `purchase`;
CREATE TABLE `purchase` (
  `id` int NOT NULL AUTO_INCREMENT,
  `imgid` int DEFAULT NULL,
  `uid` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 ROW_FORMAT=FIXED;

-- ----------------------------
-- Records of purchase
-- ----------------------------
INSERT INTO `purchase` VALUES ('1', '4', '3');
INSERT INTO `purchase` VALUES ('2', '5', '3');
INSERT INTO `purchase` VALUES ('3', '82', '3');
INSERT INTO `purchase` VALUES ('5', '81', '3');
INSERT INTO `purchase` VALUES ('6', '2', '3');
INSERT INTO `purchase` VALUES ('7', '62', '3');
INSERT INTO `purchase` VALUES ('8', '61', '3');
INSERT INTO `purchase` VALUES ('9', '81', '4');
INSERT INTO `purchase` VALUES ('10', '62', '4');
INSERT INTO `purchase` VALUES ('11', '61', '4');
INSERT INTO `purchase` VALUES ('12', '5', '24');
INSERT INTO `purchase` VALUES ('13', '42', '3');
INSERT INTO `purchase` VALUES ('14', '15', '3');
INSERT INTO `purchase` VALUES ('15', '8', '3');
INSERT INTO `purchase` VALUES ('16', '24', '3');
INSERT INTO `purchase` VALUES ('17', '14', '4');

-- ----------------------------
-- Table structure for `tags`
-- ----------------------------
DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `imgid` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of tags
-- ----------------------------
INSERT INTO `tags` VALUES ('3', '哈登', '42');
INSERT INTO `tags` VALUES ('6', '乔丹', '4');
INSERT INTO `tags` VALUES ('7', '哈登', '3');
INSERT INTO `tags` VALUES ('8', '金凯利', '10');
INSERT INTO `tags` VALUES ('9', '谍影重重', '14');
INSERT INTO `tags` VALUES ('10', '特工', '14');
INSERT INTO `tags` VALUES ('12', '复仇者联盟', '5');
INSERT INTO `tags` VALUES ('13', '超级英雄', '5');
INSERT INTO `tags` VALUES ('14', '巫师3', '6');
INSERT INTO `tags` VALUES ('15', '杰洛特', '6');
INSERT INTO `tags` VALUES ('16', '谍影重重', '9');
INSERT INTO `tags` VALUES ('17', '特工', '9');
INSERT INTO `tags` VALUES ('18', '火箭', '42');
INSERT INTO `tags` VALUES ('19', '战舰', '43');
INSERT INTO `tags` VALUES ('20', 'eve', '43');
INSERT INTO `tags` VALUES ('21', '雷霆', '42');

-- ----------------------------
-- Table structure for `thumb`
-- ----------------------------
DROP TABLE IF EXISTS `thumb`;
CREATE TABLE `thumb` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `IMGID` decimal(8,0) DEFAULT NULL,
  `USERID` decimal(8,0) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of thumb
-- ----------------------------
INSERT INTO `thumb` VALUES ('2', '3', '3');
INSERT INTO `thumb` VALUES ('21', '16', '3');
INSERT INTO `thumb` VALUES ('22', '11', '3');
INSERT INTO `thumb` VALUES ('23', '9', '3');
INSERT INTO `thumb` VALUES ('62', '12', '3');
INSERT INTO `thumb` VALUES ('63', '13', '3');
INSERT INTO `thumb` VALUES ('81', '4', '4');
INSERT INTO `thumb` VALUES ('82', '82', '3');
INSERT INTO `thumb` VALUES ('83', '83', '3');
INSERT INTO `thumb` VALUES ('84', '62', '3');
INSERT INTO `thumb` VALUES ('88', '4', '3');
INSERT INTO `thumb` VALUES ('89', '2', '3');
INSERT INTO `thumb` VALUES ('90', '5', '3');
INSERT INTO `thumb` VALUES ('91', '42', '3');
INSERT INTO `thumb` VALUES ('95', '3', '4');
INSERT INTO `thumb` VALUES ('96', '14', '4');
INSERT INTO `thumb` VALUES ('97', '87', '4');
INSERT INTO `thumb` VALUES ('99', '5', '4');
INSERT INTO `thumb` VALUES ('100', '83', '4');
INSERT INTO `thumb` VALUES ('102', '2', '4');

-- ----------------------------
-- Table structure for `types`
-- ----------------------------
DROP TABLE IF EXISTS `types`;
CREATE TABLE `types` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of types
-- ----------------------------
INSERT INTO `types` VALUES ('2', '篮球');
INSERT INTO `types` VALUES ('3', '足球');
INSERT INTO `types` VALUES ('4', '电影');
INSERT INTO `types` VALUES ('5', '游戏');
INSERT INTO `types` VALUES ('6', '风景');

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PASSWORD` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PIC` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'defaultPic.jpg',
  `BAN` decimal(1,0) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('3', 'wzh', 'XAwfCUyMEUP4vm+t9jJLVA==', '1583833030176.jpg', '0');
INSERT INTO `users` VALUES ('4', 'kok', '4QrcOUm6Wau+VuBX8g+IPg==', 'defaultPic.jpg', '0');
INSERT INTO `users` VALUES ('5', 'dqb', 'ICy5YqxZB1uWSwcVLSNLcA==', 'defaultPic.jpg', '0');
INSERT INTO `users` VALUES ('23', 'qq2', 'ICy5YqxZB1uWSwcVLSNLcA==', 'defaultPic.jpg', '1');
INSERT INTO `users` VALUES ('24', 'newstar', 'XAwfCUyMEUP4vm+t9jJLVA==', 'defaultPic.jpg', '0');
INSERT INTO `users` VALUES ('25', 'myh', 'XAwfCUyMEUP4vm+t9jJLVA==', 'defaultPic.jpg', '0');
