/*
 Navicat MySQL Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : booksdata

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 31/07/2022 19:50:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `bookId` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `bookName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '书名',
  `type` int NOT NULL COMMENT '类型',
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '作者',
  `price` float(255, 1) NOT NULL COMMENT '价格',
  `language` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '语言',
  PRIMARY KEY (`bookId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10011 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (10001, '水浒传', 1, '施耐庵', 20.8, '中文');
INSERT INTO `book` VALUES (10002, '三国演义', 1, '罗贯中', 32.8, '中文');
INSERT INTO `book` VALUES (10003, '红楼梦', 1, '曹雪芹', 53.2, '中文');
INSERT INTO `book` VALUES (10004, '西游记', 1, '吴承恩', 35.8, '中文');
INSERT INTO `book` VALUES (10005, '物种起源', 2, '达尔文', 58.0, '英文');
INSERT INTO `book` VALUES (10006, '资本论', 3, '马克思', 45.6, '英文');
INSERT INTO `book` VALUES (10007, 'Java编译原理', 2, '布朗松', 99.8, '英文');
INSERT INTO `book` VALUES (10008, '刑法学讲义', 4, '罗翔', 76.9, '中文');
INSERT INTO `book` VALUES (10009, '老人与海', 1, '海明威', 56.0, '英文');
INSERT INTO `book` VALUES (10010, '自然哲学的数学原理', 5, '艾萨克牛顿', 82.5, '英文');

SET FOREIGN_KEY_CHECKS = 1;
