/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : yantai_general_energy

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 09/10/2021 17:53:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_energy_data
-- ----------------------------
DROP TABLE IF EXISTS `tb_energy_data`;
CREATE TABLE `tb_energy_data`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
  `data_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '上传数据项编码（参照 NECC-NHJC-02）',
  `data_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '数据项的值，数据单位按“NECC-NHJC-02”要求',
  `input_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '数据采集类型：1 管理信息系统；2 生产监控管理系统；3 分布式控制系统；\r\n4 现场仪表；5 手工填报',
  `stat_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '数据采集频率：0 实时、1 日、2 月、3 年',
  `stat_date` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '数据统计时间 yyyy-MM-dd HH:mm:ss',
  `upload_date` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '数据上传时间 yyyy-MM-dd HH:mm:ss',
  `scope` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '数据范围：1 全厂；2 生产工序；3 生产工序单元；4 重点耗能设备',
  `valid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '数据有效性：0有效数据；1无效数据',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_energy_data
-- ----------------------------
INSERT INTO `tb_energy_data` VALUES ('1', '00-00-0000-023300-11', '123.45', '1', '1', '2021-10-08 00:00:00', '2021-10-08 23:00:00', '1', '1');
INSERT INTO `tb_energy_data` VALUES ('3', '00-00-0000-011500-11', '332.3', '1', '1', '2021-10-08 00:00:00', '2021-10-08 23:00:00', '1', '1');

-- ----------------------------
-- Table structure for tb_energy_request_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_energy_request_log`;
CREATE TABLE `tb_energy_request_log`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
  `request_time` datetime(0) DEFAULT NULL COMMENT '请求时间',
  `request_info` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '请求内容',
  `response_info` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '请求结果',
  `failed_message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '失败信息',
  `request_long_time` int(0) DEFAULT NULL COMMENT '请求时长（毫秒）',
  `request_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '请求地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_energy_request_log
-- ----------------------------
INSERT INTO `tb_energy_request_log` VALUES ('24f9b43d1dc944d280cec27b4a099396', '2021-10-09 17:37:03', '{\"deviceId\":\"92ddac131d0517f12f754b9738efeb83\",\"enterpriseCode\":\"91370600710936591P\",\"data\":[{\"dataCode\":\"95d7b06994005f47de5367ffbb3b7f34100a70345ef39107385dae3dc82b239e\",\"dataValue\":\"2dc92937e5be615404f1e84aaf7e38b3\",\"inputType\":\"60fbe13e0d2622609a103290ad7af918\",\"statType\":\"60fbe13e0d2622609a103290ad7af918\",\"statDate\":\"e3f08d46daafebc46d5507cffff2ae40630ca523ed82b972565fff4dc37dda9c\",\"uploadDate\":\"d6f2ed2d17b062fdd332112d3eff2da6630ca523ed82b972565fff4dc37dda9c\",\"scope\":\"60fbe13e0d2622609a103290ad7af918\",\"valid\":\"60fbe13e0d2622609a103290ad7af918\"},{\"dataCode\":\"b99c70ed13b5115dc56210995cf53e01100a70345ef39107385dae3dc82b239e\",\"dataValue\":\"f61e87e3b04a6b18f20447b2b60c66e7\",\"inputType\":\"60fbe13e0d2622609a103290ad7af918\",\"statType\":\"60fbe13e0d2622609a103290ad7af918\",\"statDate\":\"e3f08d46daafebc46d5507cffff2ae40630ca523ed82b972565fff4dc37dda9c\",\"uploadDate\":\"d6f2ed2d17b062fdd332112d3eff2da6630ca523ed82b972565fff4dc37dda9c\",\"scope\":\"60fbe13e0d2622609a103290ad7af918\",\"valid\":\"60fbe13e0d2622609a103290ad7af918\"}]}', NULL, 'org.springframework.web.client.HttpClientErrorException$NotFound: 404 : [{\"timestamp\":\"2021-10-09T09:37:03.065+00:00\",\"status\":404,\"error\":\"Not Found\",\"message\":\"\",\"path\":\"/enterprise/uploadEnergyData2\"}]', 126, '\"https://60.208.20.233:9244/enterprise/uploadEnergyData2\"');
INSERT INTO `tb_energy_request_log` VALUES ('3aed2430da3e41f5987a393ce41d826c', '2021-10-09 17:21:01', '{\"deviceId\":\"92ddac131d0517f12f754b9738efeb83\",\"enterpriseCode\":\"91370600710936591P\",\"data\":[{\"dataCode\":\"95d7b06994005f47de5367ffbb3b7f34100a70345ef39107385dae3dc82b239e\",\"dataValue\":\"2dc92937e5be615404f1e84aaf7e38b3\",\"inputType\":\"60fbe13e0d2622609a103290ad7af918\",\"statType\":\"60fbe13e0d2622609a103290ad7af918\",\"statDate\":\"e3f08d46daafebc46d5507cffff2ae40630ca523ed82b972565fff4dc37dda9c\",\"uploadDate\":\"d6f2ed2d17b062fdd332112d3eff2da6630ca523ed82b972565fff4dc37dda9c\",\"scope\":\"60fbe13e0d2622609a103290ad7af918\",\"valid\":\"60fbe13e0d2622609a103290ad7af918\"},{\"dataCode\":\"b99c70ed13b5115dc56210995cf53e01100a70345ef39107385dae3dc82b239e\",\"dataValue\":\"f61e87e3b04a6b18f20447b2b60c66e7\",\"inputType\":\"60fbe13e0d2622609a103290ad7af918\",\"statType\":\"60fbe13e0d2622609a103290ad7af918\",\"statDate\":\"e3f08d46daafebc46d5507cffff2ae40630ca523ed82b972565fff4dc37dda9c\",\"uploadDate\":\"d6f2ed2d17b062fdd332112d3eff2da6630ca523ed82b972565fff4dc37dda9c\",\"scope\":\"60fbe13e0d2622609a103290ad7af918\",\"valid\":\"60fbe13e0d2622609a103290ad7af918\"}]}', NULL, 'org.springframework.web.client.HttpClientErrorException$NotFound: 404 : [{\"timestamp\":\"2021-10-09T09:20:53.018+00:00\",\"status\":404,\"error\":\"Not Found\",\"message\":\"\",\"path\":\"/enterprise/uploadEnergyData2\"}]', 8905, '\"https://60.208.20.233:9244/enterprise/uploadEnergyData2\"');
INSERT INTO `tb_energy_request_log` VALUES ('631ae5a86bce47feb02f928f65f5d643', '2021-10-09 17:20:52', '{\"region\":\"370672\",\"enterpriseCode\":\"91370600710936591P\"}', '{\"responseCode\":\"0\",\"responseMessage\":\"RECEIVE SUCCESS\",\"deviceId\":\"92ddac131d0517f12f754b9738efeb83\",\"loadDicVersionURL\":\"https://222.175.157.237:9007/enterprise/versionCheck\",\"centerDataURL\":\"https://222.175.157.237:9007/enterprise/uploadEnergyData\",\"centerInfoDownloadURL\":\"https://222.175.157.237:9007/enterprise/downloadConfigData\",\"downloadBaseDataURL\":\"https://222.175.157.237:9007/enterprise/downloadBaseData\",\"uploadTime\":\"01:38:00\",\"secretKey\":\"7c7ab08b3d934268996faf7eb5e0342d\"}', NULL, 954, '\"https://60.208.20.233:9244/enterprise/register\"');
INSERT INTO `tb_energy_request_log` VALUES ('6ba0dddd984f4d54ac9668a73a83bf88', '2021-10-09 17:36:41', '{\"region\":\"370672\",\"enterpriseCode\":\"91370600710936591P\"}', '{\"responseCode\":\"0\",\"responseMessage\":\"RECEIVE SUCCESS\",\"deviceId\":\"92ddac131d0517f12f754b9738efeb83\",\"loadDicVersionURL\":\"https://222.175.157.237:9007/enterprise/versionCheck\",\"centerDataURL\":\"https://222.175.157.237:9007/enterprise/uploadEnergyData\",\"centerInfoDownloadURL\":\"https://222.175.157.237:9007/enterprise/downloadConfigData\",\"downloadBaseDataURL\":\"https://222.175.157.237:9007/enterprise/downloadBaseData\",\"uploadTime\":\"01:38:00\",\"secretKey\":\"7c7ab08b3d934268996faf7eb5e0342d\"}', NULL, 596, '\"https://60.208.20.233:9244/enterprise/register\"');
INSERT INTO `tb_energy_request_log` VALUES ('878c5663d4cb4c54a1bef0c8502faf20', '2021-10-09 17:37:03', '{\"deviceId\":\"92ddac131d0517f12f754b9738efeb83\",\"enterpriseCode\":\"91370600710936591P\",\"data\":[{\"dataCode\":\"95d7b06994005f47de5367ffbb3b7f34100a70345ef39107385dae3dc82b239e\",\"dataValue\":\"2dc92937e5be615404f1e84aaf7e38b3\",\"inputType\":\"60fbe13e0d2622609a103290ad7af918\",\"statType\":\"60fbe13e0d2622609a103290ad7af918\",\"statDate\":\"e3f08d46daafebc46d5507cffff2ae40630ca523ed82b972565fff4dc37dda9c\",\"uploadDate\":\"d6f2ed2d17b062fdd332112d3eff2da6630ca523ed82b972565fff4dc37dda9c\",\"scope\":\"60fbe13e0d2622609a103290ad7af918\",\"valid\":\"60fbe13e0d2622609a103290ad7af918\"},{\"dataCode\":\"b99c70ed13b5115dc56210995cf53e01100a70345ef39107385dae3dc82b239e\",\"dataValue\":\"f61e87e3b04a6b18f20447b2b60c66e7\",\"inputType\":\"60fbe13e0d2622609a103290ad7af918\",\"statType\":\"60fbe13e0d2622609a103290ad7af918\",\"statDate\":\"e3f08d46daafebc46d5507cffff2ae40630ca523ed82b972565fff4dc37dda9c\",\"uploadDate\":\"d6f2ed2d17b062fdd332112d3eff2da6630ca523ed82b972565fff4dc37dda9c\",\"scope\":\"60fbe13e0d2622609a103290ad7af918\",\"valid\":\"60fbe13e0d2622609a103290ad7af918\"}]}', NULL, 'org.springframework.web.client.HttpClientErrorException$NotFound: 404 : [{\"timestamp\":\"2021-10-09T09:36:41.078+00:00\",\"status\":404,\"error\":\"Not Found\",\"message\":\"\",\"path\":\"/enterprise/uploadEnergyData2\"}]', 21966, '\"https://60.208.20.233:9244/enterprise/uploadEnergyData2\"');
INSERT INTO `tb_energy_request_log` VALUES ('93e71334e4eb45229d82dc50b010af37', '2021-10-09 17:18:54', '{\"deviceId\":\"92ddac131d0517f12f754b9738efeb83\",\"enterpriseCode\":\"91370600710936591P\",\"data\":[{\"dataCode\":\"95d7b06994005f47de5367ffbb3b7f34100a70345ef39107385dae3dc82b239e\",\"dataValue\":\"2dc92937e5be615404f1e84aaf7e38b3\",\"inputType\":\"60fbe13e0d2622609a103290ad7af918\",\"statType\":\"60fbe13e0d2622609a103290ad7af918\",\"statDate\":\"e3f08d46daafebc46d5507cffff2ae40630ca523ed82b972565fff4dc37dda9c\",\"uploadDate\":\"d6f2ed2d17b062fdd332112d3eff2da6630ca523ed82b972565fff4dc37dda9c\",\"scope\":\"60fbe13e0d2622609a103290ad7af918\",\"valid\":\"60fbe13e0d2622609a103290ad7af918\"},{\"dataCode\":\"b99c70ed13b5115dc56210995cf53e01100a70345ef39107385dae3dc82b239e\",\"dataValue\":\"f61e87e3b04a6b18f20447b2b60c66e7\",\"inputType\":\"60fbe13e0d2622609a103290ad7af918\",\"statType\":\"60fbe13e0d2622609a103290ad7af918\",\"statDate\":\"e3f08d46daafebc46d5507cffff2ae40630ca523ed82b972565fff4dc37dda9c\",\"uploadDate\":\"d6f2ed2d17b062fdd332112d3eff2da6630ca523ed82b972565fff4dc37dda9c\",\"scope\":\"60fbe13e0d2622609a103290ad7af918\",\"valid\":\"60fbe13e0d2622609a103290ad7af918\"}]}', NULL, 'org.springframework.web.client.HttpClientErrorException$NotFound: 404 : [{\"timestamp\":\"2021-10-09T09:18:51.512+00:00\",\"status\":404,\"error\":\"Not Found\",\"message\":\"\",\"path\":\"/enterprise/uploadEnergyData2\"}]', 2397, '\"https://60.208.20.233:9244/enterprise/uploadEnergyData2\"');
INSERT INTO `tb_energy_request_log` VALUES ('9cd76606aa254246b37e53557810ec95', '2021-10-09 17:45:49', '{\"deviceId\":\"92ddac131d0517f12f754b9738efeb83\",\"enterpriseCode\":\"91370600710936591P\",\"data\":[{\"dataCode\":\"95d7b06994005f47de5367ffbb3b7f34100a70345ef39107385dae3dc82b239e3\",\"dataValue\":\"2dc92937e5be615404f1e84aaf7e38b3\",\"inputType\":\"60fbe13e0d2622609a103290ad7af918\",\"statType\":\"60fbe13e0d2622609a103290ad7af918\",\"statDate\":\"df55badf2c4033aea6c19d3a8137d5ea630ca523ed82b972565fff4dc37dda9c\",\"uploadDate\":\"7e24930ea823bd632896ad0e319b661fd804bd93098e6f61699701612d01d8a0\",\"scope\":\"60fbe13e0d2622609a103290ad7af918\",\"valid\":\"60fbe13e0d2622609a103290ad7af918\"}]}', '{\"responseCode\":\"1\",\"responseMessage\":\"解密失败！\"}', NULL, 139, '\"https://60.208.20.233:9244/enterprise/uploadEnergyData\"');
INSERT INTO `tb_energy_request_log` VALUES ('ac427e842c824373801233c052b18e54', '2021-10-09 17:45:27', '{\"deviceId\":\"\",\"enterpriseCode\":\"\",\"data\":[{\"dataCode\":\"\",\"dataValue\":\"\",\"inputType\":\"\",\"statType\":\"\",\"statDate\":\"\",\"uploadDate\":\"\",\"scope\":\"\",\"valid\":\"\"}]}', '{\"responseCode\":\"1\",\"responseMessage\":\"序列号与企业统一社会信用代码不符\"}', NULL, 536, '\"https://60.208.20.233:9244/enterprise/uploadEnergyData\"');
INSERT INTO `tb_energy_request_log` VALUES ('b2b9536df72f44d19a849584a291ddef', '2021-10-09 17:40:30', '{\"deviceId\":\"\",\"enterpriseCode\":\"\",\"data\":[{\"dataCode\":\"\",\"dataValue\":\"\",\"inputType\":\"\",\"statType\":\"\",\"statDate\":\"\",\"uploadDate\":\"\",\"scope\":\"\",\"valid\":\"\"}]}', '{\"responseCode\":\"1\",\"responseMessage\":\"序列号与企业统一社会信用代码不符\"}', NULL, 1196, '\"https://60.208.20.233:9244/enterprise/uploadEnergyData\"');
INSERT INTO `tb_energy_request_log` VALUES ('bacfa641ae024aefae6067b07ead3018', '2021-10-09 17:38:49', '{\"deviceId\":\"\",\"enterpriseCode\":\"\",\"data\":[{\"dataCode\":\"\",\"dataValue\":\"\",\"inputType\":\"\",\"statType\":\"\",\"statDate\":\"\",\"uploadDate\":\"\",\"scope\":\"\",\"valid\":\"\"}]}', NULL, 'org.springframework.web.client.HttpClientErrorException$NotFound: 404 : [{\"timestamp\":\"2021-10-09T09:38:48.296+00:00\",\"status\":404,\"error\":\"Not Found\",\"message\":\"\",\"path\":\"/enterprise/uploadEnergyData2\"}]', 551, '\"https://60.208.20.233:9244/enterprise/uploadEnergyData2\"');
INSERT INTO `tb_energy_request_log` VALUES ('d2be8ff66a4f4626afd88a809925e2f8', '2021-10-09 17:18:51', '{\"region\":\"370672\",\"enterpriseCode\":\"91370600710936591P\"}', '{\"responseCode\":\"0\",\"responseMessage\":\"RECEIVE SUCCESS\",\"deviceId\":\"92ddac131d0517f12f754b9738efeb83\",\"loadDicVersionURL\":\"https://222.175.157.237:9007/enterprise/versionCheck\",\"centerDataURL\":\"https://222.175.157.237:9007/enterprise/uploadEnergyData\",\"centerInfoDownloadURL\":\"https://222.175.157.237:9007/enterprise/downloadConfigData\",\"downloadBaseDataURL\":\"https://222.175.157.237:9007/enterprise/downloadBaseData\",\"uploadTime\":\"01:38:00\",\"secretKey\":\"7c7ab08b3d934268996faf7eb5e0342d\"}', NULL, 3922, '\"https://60.208.20.233:9244/enterprise/register\"');
INSERT INTO `tb_energy_request_log` VALUES ('da3ec024cf034530a93c519a71a80573', '2021-10-09 17:43:48', '{\"deviceId\":\"\",\"enterpriseCode\":\"\",\"data\":[{\"dataCode\":\"\",\"dataValue\":\"\",\"inputType\":\"\",\"statType\":\"\",\"statDate\":\"\",\"uploadDate\":\"\",\"scope\":\"\",\"valid\":\"\"}]}', '{\"responseCode\":\"1\",\"responseMessage\":\"序列号与企业统一社会信用代码不符\"}', NULL, 547, '\"https://60.208.20.233:9244/enterprise/uploadEnergyData\"');

SET FOREIGN_KEY_CHECKS = 1;
