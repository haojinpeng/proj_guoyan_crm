/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.5.56 : Database - crm
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`crm` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `crm`;

/*Table structure for table `approval_process` */

DROP TABLE IF EXISTS `approval_process`;

CREATE TABLE `approval_process` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` int(11) DEFAULT NULL COMMENT '上级流程id',
  `process_name` varchar(40) NOT NULL COMMENT '流程名称',
  `approval_role` int(11) DEFAULT NULL COMMENT '审批角色',
  `approval_user` int(11) DEFAULT NULL COMMENT '审批用户',
  `approval_department` int(11) DEFAULT NULL COMMENT '审批部门',
  `type` int(11) DEFAULT NULL COMMENT '适用类型',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `process_approval_role` (`approval_role`) USING BTREE,
  KEY `process_approval_user` (`approval_user`) USING BTREE,
  KEY `approval_department` (`approval_department`) USING BTREE,
  CONSTRAINT `approval_department` FOREIGN KEY (`approval_department`) REFERENCES `department` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `process_approval_role` FOREIGN KEY (`approval_role`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `process_approval_user` FOREIGN KEY (`approval_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `approval_process` */

insert  into `approval_process`(`id`,`parent_id`,`process_name`,`approval_role`,`approval_user`,`approval_department`,`type`,`remarks`) values (1,0,'流程名称1',1,1,1002,13,'备注1'),(2,1,'流程名称2',2,2,1002,13,'备注2'),(3,2,'流程名称3',3,3,1002,NULL,NULL),(4,1,'流程名称4',2,5,1002,NULL,NULL);

/*Table structure for table `approval_type` */

DROP TABLE IF EXISTS `approval_type`;

CREATE TABLE `approval_type` (
  `id` int(11) NOT NULL COMMENT '主键',
  `name` varchar(40) NOT NULL COMMENT '类型名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `approval_type` */

insert  into `approval_type`(`id`,`name`) values (1,'吃饭'),(2,'喝酒'),(3,'乘出租车'),(4,'乘地铁'),(5,'乘公交车'),(6,'商务'),(7,'奶茶'),(8,'下午茶'),(12,'夜宵'),(13,'项目费用审核'),(14,'商机费用审核'),(15,'采购费用审核'),(16,'客户拜访审核');

/*Table structure for table `business_tracking` */

DROP TABLE IF EXISTS `business_tracking`;

CREATE TABLE `business_tracking` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键唯一',
  `business` int(11) NOT NULL COMMENT '所属商机id',
  `type` int(11) NOT NULL COMMENT '跟进类型对应跟进类型表id',
  `createdate` varchar(50) NOT NULL COMMENT '创建时间',
  `recorder` int(11) DEFAULT NULL COMMENT '记录人',
  `message` varchar(255) DEFAULT NULL COMMENT '纪要',
  `feedback_result` varchar(255) DEFAULT NULL COMMENT '记录反馈',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `business_tracking_user` (`recorder`) USING BTREE,
  KEY `business_id` (`business`) USING BTREE,
  CONSTRAINT `business_id` FOREIGN KEY (`business`) REFERENCES `businessopporitunity` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `business_tracking_user` FOREIGN KEY (`recorder`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `business_tracking` */

insert  into `business_tracking`(`id`,`business`,`type`,`createdate`,`recorder`,`message`,`feedback_result`,`remarks`) values (7,1002,1,'2020-11-11',2,'qwewq','sadsadasdasdsad','qwewqewq'),(8,1001,4,'2020-10-10',2,'qwewqewq','21312321312321','zxczxcxc'),(9,1001,1,'2020-10-10',2,'qwewqe','12312312321312312312321','xzczxczxc'),(10,1001,1,'2020-10-10',2,'wqeiuowq','45rrdsadasdsad','ksadkasds'),(12,1001,6,'2020-11-11',2,'wqyeiuwqe','21213312','shkasjdhksajd'),(13,1001,1,'2020-10-10',2,'213212312','iqueoqiwu','iwqueoqiw');

/*Table structure for table `business_type` */

DROP TABLE IF EXISTS `business_type`;

CREATE TABLE `business_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键唯一',
  `name` varchar(40) NOT NULL COMMENT '类型名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `business_type` */

insert  into `business_type`(`id`,`name`) values (1,'聚餐'),(2,'喝酒'),(3,'聚会'),(4,'旅游'),(5,'喝咖啡'),(6,'早点'),(7,'唱歌'),(8,'洗浴');

/*Table structure for table `businessopporitunity` */

DROP TABLE IF EXISTS `businessopporitunity`;

CREATE TABLE `businessopporitunity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键唯一',
  `customers_id` int(11) NOT NULL COMMENT '商机所属客户',
  `business_type_id` int(11) DEFAULT NULL COMMENT '商机类型',
  `contact` varchar(100) NOT NULL COMMENT '联系人主键数据格式[1,2,3,4]',
  `name` varchar(50) NOT NULL COMMENT '商机名称',
  `status` int(11) NOT NULL COMMENT '1跟进中2已报备3已流失',
  `intention` int(11) NOT NULL COMMENT '客户意向（1：弱2：中3：强）',
  `userid` int(11) NOT NULL COMMENT '洽谈用户',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `businessOpporitunity_cutomer` (`customers_id`) USING BTREE,
  KEY `businessOpporitunity_type` (`business_type_id`) USING BTREE,
  CONSTRAINT `businessOpporitunity_cutomer` FOREIGN KEY (`customers_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `businessOpporitunity_type` FOREIGN KEY (`business_type_id`) REFERENCES `business_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1016 DEFAULT CHARSET=utf8;

/*Data for the table `businessopporitunity` */

insert  into `businessopporitunity`(`id`,`customers_id`,`business_type_id`,`contact`,`name`,`status`,`intention`,`userid`,`remarks`) values (1001,102,5,'1','智慧设施',2,2,2,'aqweqweq'),(1002,102,4,'1','智慧学堂',3,2,2,'aqweqweq'),(1005,102,5,'1','智慧设施',2,2,2,'aqweqweq'),(1006,102,1,'4','智慧学堂',1,2,0,'aqweqweq'),(1008,102,7,'1','智慧城市',1,2,0,'345345'),(1009,102,1,'4','智慧学堂',3,2,0,'aqweqweq'),(1010,102,5,'1','智慧设施',2,2,2,'aqweqweq'),(1011,102,4,'1','智慧学堂',1,2,2,'aqweqweq'),(1012,102,5,'1','智慧设施',3,2,2,'aqweqweq'),(1014,102,4,'1','智慧城市',1,2,2,'aqweqweq'),(1015,102,5,'1','智慧设施',3,2,2,'aqweqweq');

/*Table structure for table `businessopporitunity_cost` */

DROP TABLE IF EXISTS `businessopporitunity_cost`;

CREATE TABLE `businessopporitunity_cost` (
  `id` int(55) NOT NULL COMMENT '主键唯一',
  `business_id` int(11) NOT NULL COMMENT '所属商机id',
  `business_tracking` int(11) DEFAULT NULL COMMENT '所属跟进记录id',
  `consume_type` int(11) NOT NULL COMMENT '消费类型',
  `reality_consume` double(11,2) NOT NULL COMMENT '实际消费',
  `perdict_consume` double(11,2) NOT NULL COMMENT '计划消费',
  `audit_status` int(11) NOT NULL DEFAULT '0' COMMENT '审核状态0：审核中1：成功2：驳回',
  `cost_declarant` int(11) DEFAULT NULL COMMENT '费用申报人',
  `cost_description` varchar(255) DEFAULT NULL COMMENT '费用详细说明',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `businessopporitunitycost_user` (`cost_declarant`) USING BTREE,
  KEY `cost_business` (`business_id`) USING BTREE,
  KEY `business_tracking` (`business_tracking`) USING BTREE,
  CONSTRAINT `businessopporitunitycost_user` FOREIGN KEY (`cost_declarant`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `business_tracking` FOREIGN KEY (`business_tracking`) REFERENCES `business_tracking` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cost_business` FOREIGN KEY (`business_id`) REFERENCES `businessopporitunity` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `businessopporitunity_cost` */

insert  into `businessopporitunity_cost`(`id`,`business_id`,`business_tracking`,`consume_type`,`reality_consume`,`perdict_consume`,`audit_status`,`cost_declarant`,`cost_description`) values (18,1006,9,3,234.00,234234.00,1,NULL,'12321'),(20,1001,7,1,324324.00,2343242.00,1,1,'wqewqewq'),(21,1001,7,4,12321.00,12321.00,1,1,'12321'),(22,1002,7,1,12312.00,12321.00,0,1,'市委区委区委'),(23,1001,7,1,12312.00,12321.00,0,1,'12321'),(24,1001,7,1,21321.00,213213.00,0,1,'12321'),(25,1001,7,1,12312.00,12312.00,0,1,'wqewqewq'),(26,1001,8,1,12321.00,213123.00,0,2,'12321312'),(27,1001,7,1,12312.00,12321.00,0,1,'12312'),(28,1006,7,1,12321.00,123213.00,0,5,'12321321'),(1064,1001,7,1,21321.00,123.00,0,1,'12321312'),(1182620,1001,7,1,2131.00,123.00,0,1,'12321'),(1643955,1001,7,1,213213.00,123213.00,0,1,'12312321'),(1965002,1001,7,1,21321.00,12321.00,0,1,'12312321'),(2294810,1001,7,1,12321.00,21321123.00,0,1,'123123213'),(3040618,1001,7,1,21312.00,12321.00,0,3,'12321'),(3147326,1001,9,1,2312.00,123213.00,0,1,'213123'),(4236890,1001,7,1,21321.00,123123.00,0,1,'21321213'),(4834853,1009,7,1,213123.00,123213.00,0,7,'12321321'),(6251203,1001,7,1,12321.00,123213.00,0,1,'123213213'),(7575704,1001,7,1,12312.00,123123.00,0,5,'12312312'),(7805591,1001,7,1,123.00,123213.00,0,1,'12312321'),(7938605,1001,7,1,12312.00,123213.00,0,1,'12321312');

/*Table structure for table `city` */

DROP TABLE IF EXISTS `city`;

CREATE TABLE `city` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键唯一',
  `province` int(11) NOT NULL COMMENT '所属省州id',
  `city_name` varchar(25) NOT NULL COMMENT '城市名称',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `province_city` (`province`) USING BTREE,
  CONSTRAINT `city_ibfk_1` FOREIGN KEY (`province`) REFERENCES `city` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=393 DEFAULT CHARSET=utf8;

/*Data for the table `city` */

insert  into `city`(`id`,`province`,`city_name`) values (1,1,'北京市'),(2,2,'天津市'),(3,3,'石家庄市'),(4,3,'唐山市'),(5,3,'秦皇岛市'),(6,3,'邯郸市'),(7,3,'邢台市'),(8,3,'保定市'),(9,3,'张家口市'),(10,3,'承德市'),(11,3,'沧州市'),(12,3,'廊坊市'),(13,3,'衡水市'),(14,4,'太原市'),(15,4,'大同市'),(16,4,'阳泉市'),(17,4,'长治市'),(18,4,'晋城市'),(19,4,'朔州市'),(20,4,'晋中市'),(21,4,'运城市'),(22,4,'忻州市'),(23,4,'临汾市'),(24,4,'吕梁市'),(25,32,'台北市'),(26,32,'高雄市'),(27,32,'基隆市'),(28,32,'台中市'),(29,32,'台南市'),(30,32,'新竹市'),(31,32,'嘉义市'),(32,32,'台北县'),(33,32,'宜兰县'),(34,32,'桃园县'),(35,32,'新竹县'),(36,32,'苗栗县'),(37,32,'台中县'),(38,32,'彰化县'),(39,32,'南投县'),(40,32,'云林县'),(41,32,'嘉义县'),(42,32,'台南县'),(43,32,'高雄县'),(44,32,'屏东县'),(45,32,'澎湖县'),(46,32,'台东县'),(47,32,'花莲县'),(48,6,'沈阳市'),(49,6,'大连市'),(50,6,'鞍山市'),(51,6,'抚顺市'),(52,6,'本溪市'),(53,6,'丹东市'),(54,6,'锦州市'),(55,6,'营口市'),(56,6,'阜新市'),(57,6,'辽阳市'),(58,6,'盘锦市'),(59,6,'铁岭市'),(60,6,'朝阳市'),(61,6,'葫芦岛市'),(62,7,'长春市'),(63,7,'吉林市'),(64,7,'四平市'),(65,7,'辽源市'),(66,7,'通化市'),(67,7,'白山市'),(68,7,'松原市'),(69,7,'白山市'),(70,7,'延边朝鲜族自治州'),(71,8,'哈尔滨市'),(72,8,'齐齐哈尔市'),(73,8,'鹤 岗 市'),(74,8,'双鸭山市'),(75,8,'鸡 西 市'),(76,8,'大 庆 市'),(77,8,'伊 春 市'),(78,8,'牡丹江市'),(79,8,'佳木斯市'),(80,8,'七台河市'),(81,8,'黑 河 市'),(82,8,'绥 化 市'),(83,8,'大兴安岭地区'),(84,10,'南京市'),(85,10,'无锡市'),(86,10,'徐州市'),(87,10,'常州市'),(88,10,'苏州市'),(89,10,'南通市'),(90,10,'连云港市'),(91,10,'淮安市'),(92,10,'盐城市'),(93,10,'扬州市'),(94,10,'镇江市'),(95,10,'泰州市'),(96,10,'宿迁市'),(97,11,'杭州市'),(98,11,'宁波市'),(99,11,'温州市'),(100,11,'嘉兴市'),(101,11,'湖州市'),(102,11,'绍兴市'),(103,11,'金华市'),(104,11,'衢州市'),(105,11,'舟山市'),(106,11,'台州市'),(107,11,'丽水市'),(108,12,'合肥市'),(109,12,'芜湖市'),(110,12,'蚌埠市'),(111,12,'淮南市'),(112,12,'马鞍山市'),(113,12,'淮北市'),(114,12,'铜陵市'),(115,12,'安庆市'),(116,12,'黄山市'),(117,12,'滁州市'),(118,12,'阜阳市'),(119,12,'宿州市'),(120,12,'巢湖市'),(121,12,'六安市'),(122,12,'亳州市'),(123,12,'池州市'),(124,12,'宣城市'),(125,13,'福州市'),(126,13,'厦门市'),(127,13,'莆田市'),(128,13,'三明市'),(129,13,'泉州市'),(130,13,'漳州市'),(131,13,'南平市'),(132,13,'龙岩市'),(133,13,'宁德市'),(134,14,'南昌市'),(135,14,'景德镇市'),(136,14,'萍乡市'),(137,14,'九江市'),(138,14,'新余市'),(139,14,'鹰潭市'),(140,14,'赣州市'),(141,14,'吉安市'),(142,14,'宜春市'),(143,14,'抚州市'),(144,14,'上饶市'),(145,15,'济南市'),(146,15,'青岛市'),(147,15,'淄博市'),(148,15,'枣庄市'),(149,15,'东营市'),(150,15,'烟台市'),(151,15,'潍坊市'),(152,15,'济宁市'),(153,15,'泰安市'),(154,15,'威海市'),(155,15,'日照市'),(156,15,'莱芜市'),(157,15,'临沂市'),(158,15,'德州市'),(159,15,'聊城市'),(160,15,'滨州市'),(161,15,'菏泽市'),(162,16,'郑州市'),(163,16,'开封市'),(164,16,'洛阳市'),(165,16,'平顶山市'),(166,16,'安阳市'),(167,16,'鹤壁市'),(168,16,'新乡市'),(169,16,'焦作市'),(170,16,'濮阳市'),(171,16,'许昌市'),(172,16,'漯河市'),(173,16,'三门峡市'),(174,16,'南阳市'),(175,16,'商丘市'),(176,16,'信阳市'),(177,16,'周口市'),(178,16,'驻马店市'),(179,16,'济源市'),(180,17,'武汉市'),(181,17,'黄石市'),(182,17,'十堰市'),(183,17,'荆州市'),(184,17,'宜昌市'),(185,17,'襄樊市'),(186,17,'鄂州市'),(187,17,'荆门市'),(188,17,'孝感市'),(189,17,'黄冈市'),(190,17,'咸宁市'),(191,17,'随州市'),(192,17,'仙桃市'),(193,17,'天门市'),(194,17,'潜江市'),(195,17,'神农架林区'),(196,17,'恩施土家族苗族自治州'),(197,18,'长沙市'),(198,18,'株洲市'),(199,18,'湘潭市'),(200,18,'衡阳市'),(201,18,'邵阳市'),(202,18,'岳阳市'),(203,18,'常德市'),(204,18,'张家界市'),(205,18,'益阳市'),(206,18,'郴州市'),(207,18,'永州市'),(208,18,'怀化市'),(209,18,'娄底市'),(210,18,'湘西土家族苗族自治州'),(211,19,'广州市'),(212,19,'深圳市'),(213,19,'珠海市'),(214,19,'汕头市'),(215,19,'韶关市'),(216,19,'佛山市'),(217,19,'江门市'),(218,19,'湛江市'),(219,19,'茂名市'),(220,19,'肇庆市'),(221,19,'惠州市'),(222,19,'梅州市'),(223,19,'汕尾市'),(224,19,'河源市'),(225,19,'阳江市'),(226,19,'清远市'),(227,19,'东莞市'),(228,19,'中山市'),(229,19,'潮州市'),(230,19,'揭阳市'),(231,19,'云浮市'),(232,28,'兰州市'),(233,28,'金昌市'),(234,28,'白银市'),(235,28,'天水市'),(236,28,'嘉峪关市'),(237,28,'武威市'),(238,28,'张掖市'),(239,28,'平凉市'),(240,28,'酒泉市'),(241,28,'庆阳市'),(242,28,'定西市'),(243,28,'陇南市'),(244,28,'临夏回族自治州'),(245,28,'甘南藏族自治州'),(246,23,'成都市'),(247,23,'自贡市'),(248,23,'攀枝花市'),(249,23,'泸州市'),(250,23,'德阳市'),(251,23,'绵阳市'),(252,23,'广元市'),(253,23,'遂宁市'),(254,23,'内江市'),(255,23,'乐山市'),(256,23,'南充市'),(257,23,'眉山市'),(258,23,'宜宾市'),(259,23,'广安市'),(260,23,'达州市'),(261,23,'雅安市'),(262,23,'巴中市'),(263,23,'资阳市'),(264,23,'阿坝藏族羌族自治州'),(265,23,'甘孜藏族自治州'),(266,23,'凉山彝族自治州'),(267,24,'贵阳市'),(268,24,'六盘水市'),(269,24,'遵义市'),(270,24,'安顺市'),(271,24,'铜仁地区'),(272,24,'毕节地区'),(273,24,'黔西南布依族苗族自治州'),(274,24,'黔东南苗族侗族自治州'),(275,24,'黔南布依族苗族自治州'),(276,21,'海口市'),(277,21,'三亚市'),(278,21,'五指山市'),(279,21,'琼海市'),(280,21,'儋州市'),(281,21,'文昌市'),(282,21,'万宁市'),(283,21,'东方市'),(284,21,'澄迈县'),(285,21,'定安县'),(286,21,'屯昌县'),(287,21,'临高县'),(288,21,'白沙黎族自治县'),(289,21,'昌江黎族自治县'),(290,21,'乐东黎族自治县'),(291,21,'陵水黎族自治县'),(292,21,'保亭黎族苗族自治县'),(293,21,'琼中黎族苗族自治县'),(294,25,'昆明市'),(295,25,'曲靖市'),(296,25,'玉溪市'),(297,25,'保山市'),(298,25,'昭通市'),(299,25,'丽江市'),(300,25,'思茅市'),(301,25,'临沧市'),(302,25,'文山壮族苗族自治州'),(303,25,'红河哈尼族彝族自治州'),(304,25,'西双版纳傣族自治州'),(305,25,'楚雄彝族自治州'),(306,25,'大理白族自治州'),(307,25,'德宏傣族景颇族自治州'),(308,25,'怒江傈傈族自治州'),(309,25,'迪庆藏族自治州'),(310,29,'西宁市'),(311,29,'海东地区'),(312,29,'海北藏族自治州'),(313,29,'黄南藏族自治州'),(314,29,'海南藏族自治州'),(315,29,'果洛藏族自治州'),(316,29,'玉树藏族自治州'),(317,29,'海西蒙古族藏族自治州'),(318,27,'西安市'),(319,27,'铜川市'),(320,27,'宝鸡市'),(321,27,'咸阳市'),(322,27,'渭南市'),(323,27,'延安市'),(324,27,'汉中市'),(325,27,'榆林市'),(326,27,'安康市'),(327,27,'商洛市'),(328,20,'南宁市'),(329,20,'柳州市'),(330,20,'桂林市'),(331,20,'梧州市'),(332,20,'北海市'),(333,20,'防城港市'),(334,20,'钦州市'),(335,20,'贵港市'),(336,20,'玉林市'),(337,20,'百色市'),(338,20,'贺州市'),(339,20,'河池市'),(340,20,'来宾市'),(341,20,'崇左市'),(342,26,'拉萨市'),(343,26,'那曲地区'),(344,26,'昌都地区'),(345,26,'山南地区'),(346,26,'日喀则地区'),(347,26,'阿里地区'),(348,26,'林芝地区'),(349,30,'银川市'),(350,30,'石嘴山市'),(351,30,'吴忠市'),(352,30,'固原市'),(353,30,'中卫市'),(354,31,'乌鲁木齐市'),(355,31,'克拉玛依市'),(356,31,'石河子市　'),(357,31,'阿拉尔市'),(358,31,'图木舒克市'),(359,31,'五家渠市'),(360,31,'吐鲁番市'),(361,31,'阿克苏市'),(362,31,'喀什市'),(363,31,'哈密市'),(364,31,'和田市'),(365,31,'阿图什市'),(366,31,'库尔勒市'),(367,31,'昌吉市　'),(368,31,'阜康市'),(369,31,'米泉市'),(370,31,'博乐市'),(371,31,'伊宁市'),(372,31,'奎屯市'),(373,31,'塔城市'),(374,31,'乌苏市'),(375,31,'阿勒泰市'),(376,5,'呼和浩特市'),(377,5,'包头市'),(378,5,'乌海市'),(379,5,'赤峰市'),(380,5,'通辽市'),(381,5,'鄂尔多斯市'),(382,5,'呼伦贝尔市'),(383,5,'巴彦淖尔市'),(384,5,'乌兰察布市'),(385,5,'锡林郭勒盟'),(386,5,'兴安盟'),(387,5,'阿拉善盟'),(388,33,'香港特别行政区'),(389,34,'澳门特别行政区'),(390,9,'上海市'),(391,9,'上海市'),(392,22,'重庆市');

/*Table structure for table `company` */

DROP TABLE IF EXISTS `company`;

CREATE TABLE `company` (
  `company_id` int(11) NOT NULL AUTO_INCREMENT,
  `company_type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `company` */

insert  into `company`(`company_id`,`company_type`) values (1,'环境维护'),(2,'形象维护'),(3,'财产维护'),(6,'形象维护');

/*Table structure for table `contact` */

DROP TABLE IF EXISTS `contact`;

CREATE TABLE `contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(25) NOT NULL COMMENT '联系人姓名',
  `sex` int(11) NOT NULL COMMENT '性别0为女性1为男性',
  `age` int(11) NOT NULL COMMENT '年龄',
  `type` int(11) NOT NULL COMMENT '联系人类型对应联系人类型表id',
  `nation` int(11) DEFAULT NULL COMMENT '民族对应民族表id',
  `country` int(11) DEFAULT NULL COMMENT '联系人所在国家',
  `province` int(11) DEFAULT NULL COMMENT '联系人所在省州',
  `city` int(11) DEFAULT NULL COMMENT '联系人所在城市',
  `position` varchar(40) DEFAULT NULL COMMENT '所在单位职位',
  `company` varchar(40) DEFAULT NULL COMMENT '所在单位',
  `phone` varchar(15) NOT NULL COMMENT '联系人电话',
  `email` varchar(25) DEFAULT NULL COMMENT '联系人邮箱',
  `qq` varchar(25) DEFAULT NULL COMMENT '联系人qq',
  `wechat` varchar(25) DEFAULT NULL COMMENT '联系人微信',
  `address` varchar(150) DEFAULT NULL COMMENT '联系人地址',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `contant_conuntry` (`country`) USING BTREE,
  KEY `contant_province` (`province`) USING BTREE,
  KEY `contant_city` (`city`) USING BTREE,
  KEY `contant_nation` (`nation`) USING BTREE,
  KEY `type` (`type`),
  CONSTRAINT `contact_ibfk_1` FOREIGN KEY (`country`) REFERENCES `country` (`id`),
  CONSTRAINT `contact_ibfk_2` FOREIGN KEY (`province`) REFERENCES `province` (`id`),
  CONSTRAINT `contact_ibfk_3` FOREIGN KEY (`city`) REFERENCES `city` (`id`),
  CONSTRAINT `contact_ibfk_4` FOREIGN KEY (`type`) REFERENCES `contact_type` (`id`),
  CONSTRAINT `contant_nation` FOREIGN KEY (`nation`) REFERENCES `nation` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `contact` */

insert  into `contact`(`id`,`name`,`sex`,`age`,`type`,`nation`,`country`,`province`,`city`,`position`,`company`,`phone`,`email`,`qq`,`wechat`,`address`) values (1,'张三',1,25,1,3,4,3,5,'李经理','支付宝','13578762346','123@qq.com','123124212','1232131123','中国成都'),(2,'李四',0,25,2,3,4,3,5,'项目部长','字节跳动','13578762346','123@qq.com','123124212','1232131123','中国北京'),(3,'111111',1,111,2,33,23,26,23,'项目组长','腾讯','1111111','123@qq.com','123124212','1232131123','1111111'),(4,'提莫',0,25,1,3,4,3,5,'项目组员','支付宝','13578762346','123@qq.com','123124212','1232131123','中国杭州'),(5,'牛头',0,25,4,3,4,3,5,'项目经理','字节跳动','13578762346','123@qq.com','123124212','1232131123','中国北京'),(6,'猴子',1,25,2,3,4,3,5,'项目总监','腾讯','13578762346','123@qq.com','123124212','1232131123','中国深圳'),(8,'123123',1,1231,2,1,1,2,3,NULL,NULL,'2133123',NULL,NULL,NULL,'123123'),(9,'132',0,23,1,2,1,2,3,NULL,NULL,'123123',NULL,NULL,NULL,'123123'),(10,'2',0,12313,2,3,1,2,3,NULL,NULL,'123123',NULL,NULL,NULL,'12312312'),(11,'001',0,30,2,15,1,1,1,NULL,NULL,'123123',NULL,NULL,NULL,'12312'),(12,'123',1,23,2,6,5,5,6,NULL,NULL,'123123',NULL,NULL,NULL,'12312312'),(13,'deli',1,23,2,4,9,6,6,NULL,NULL,'123123',NULL,NULL,NULL,'2312312');

/*Table structure for table `contact_type` */

DROP TABLE IF EXISTS `contact_type`;

CREATE TABLE `contact_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(40) NOT NULL COMMENT '联系人类型名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `contact_type` */

insert  into `contact_type`(`id`,`name`) values (1,'联系人1'),(2,'联系人2'),(3,'联系人3'),(4,'联系人4');

/*Table structure for table `contract` */

DROP TABLE IF EXISTS `contract`;

CREATE TABLE `contract` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `contract_num` varchar(50) NOT NULL COMMENT '合同编号',
  `project_id` int(11) NOT NULL COMMENT '所属项目id',
  `path` varchar(255) NOT NULL COMMENT '合同存储路径',
  `upload_user` int(11) DEFAULT NULL COMMENT '上传者主键',
  `createtime` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `custom_id` int(11) DEFAULT NULL COMMENT '所属客户id',
  `contract_sign_time` varchar(50) NOT NULL COMMENT '合同开始服务时间',
  `contract_expire` varchar(50) NOT NULL COMMENT '合同到期时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `delay_time` varchar(50) DEFAULT NULL COMMENT '服务延期时长',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `contract_project` (`project_id`) USING BTREE,
  KEY `contract_custom_id` (`custom_id`) USING BTREE,
  KEY `contract_upload_user` (`upload_user`) USING BTREE,
  CONSTRAINT `contract_custom_id` FOREIGN KEY (`custom_id`) REFERENCES `customer` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `contract_project` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `contract_upload_user` FOREIGN KEY (`upload_user`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `contract` */

insert  into `contract`(`id`,`contract_num`,`project_id`,`path`,`upload_user`,`createtime`,`custom_id`,`contract_sign_time`,`contract_expire`,`remark`,`delay_time`) values (1,'1111',1,'位置一',1,'2020-12-12',100,'2020-12-12','2022-12-12','备注一',NULL),(2,'2222',2,'位置二',1,'2020-12-12',104,'2020-12-12','2026-12-12','备注二',NULL),(3,'3333',5,'位置三',1,'2020-12-11',101,'1999-12-12','2029-12-12','备注三',NULL),(4,'4444',7,'位置四',1,'2011-11-11',103,'1999-03-02','2098-12-12','备注四',NULL),(5,'5555',1,'位置五',1,'2011-12-12',102,'2020-12-12','2021-12-12','备注五',NULL);

/*Table structure for table `contractbill_apply` */

DROP TABLE IF EXISTS `contractbill_apply`;

CREATE TABLE `contractbill_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `bill_num` varchar(50) NOT NULL COMMENT '票据编号',
  `contract_id` int(11) NOT NULL COMMENT '所属合同id',
  `process_id` int(11) DEFAULT NULL COMMENT '审批流程主键',
  `billinfo` varchar(255) DEFAULT NULL COMMENT '票据信息',
  `money` double(12,2) NOT NULL COMMENT '票据金额',
  `issue` int(11) DEFAULT NULL COMMENT '申报人',
  `createtime` varchar(50) DEFAULT NULL COMMENT '申报时间',
  `status` int(11) DEFAULT NULL COMMENT '申报状态0：审核中，1：审核通过2：驳回',
  `remarks` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `contractbill_contract_id` (`contract_id`) USING BTREE,
  KEY `contractbill_issue` (`issue`) USING BTREE,
  KEY `contractbill_process_id` (`process_id`) USING BTREE,
  CONSTRAINT `contractbill_contract_id` FOREIGN KEY (`contract_id`) REFERENCES `contact` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `contractbill_issue` FOREIGN KEY (`issue`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `contractbill_process_id` FOREIGN KEY (`process_id`) REFERENCES `approval_process` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `contractbill_apply` */

/*Table structure for table `contractbill_approval` */

DROP TABLE IF EXISTS `contractbill_approval`;

CREATE TABLE `contractbill_approval` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `apply_id` int(11) NOT NULL COMMENT '所对应申请表id',
  `status` int(11) NOT NULL COMMENT '审核状态0：待审核1：审核通过2：驳回',
  `approval_user` int(11) DEFAULT NULL COMMENT '审核人id',
  `createtime` varchar(50) NOT NULL COMMENT '创建时间',
  `approval_description` varchar(255) DEFAULT NULL COMMENT '审核描述',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `constactbill_apply_id` (`apply_id`) USING BTREE,
  KEY `constactbill_approval_user` (`approval_user`) USING BTREE,
  CONSTRAINT `constactbill_apply_id` FOREIGN KEY (`apply_id`) REFERENCES `contractbill_apply` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `constactbill_approval_user` FOREIGN KEY (`approval_user`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `contractbill_approval` */

/*Table structure for table `cost_check` */

DROP TABLE IF EXISTS `cost_check`;

CREATE TABLE `cost_check` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键唯一',
  `cost_id` int(55) NOT NULL COMMENT '费用申请表id',
  `userp` int(11) DEFAULT NULL COMMENT '审核人员编号',
  `check_condition` int(11) NOT NULL DEFAULT '0' COMMENT '审核状态：0：待审核1：审核通过2：审核驳回',
  `check_idea` varchar(255) DEFAULT NULL COMMENT '审核意见',
  `check_date` varchar(50) DEFAULT NULL COMMENT '审核日期',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `business_id` int(11) NOT NULL COMMENT '商机编号',
  `approval_process` int(11) DEFAULT NULL COMMENT '所属流程表id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `check_user` (`userp`) USING BTREE,
  KEY `check_cost` (`cost_id`) USING BTREE,
  KEY `check_business` (`business_id`),
  CONSTRAINT `check_business` FOREIGN KEY (`business_id`) REFERENCES `businessopporitunity` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `check_cost` FOREIGN KEY (`cost_id`) REFERENCES `businessopporitunity_cost` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `check_user` FOREIGN KEY (`userp`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

/*Data for the table `cost_check` */

insert  into `cost_check`(`id`,`cost_id`,`userp`,`check_condition`,`check_idea`,`check_date`,`remarks`,`business_id`,`approval_process`) values (1,21,1,0,'1','2020-10-10','王企鹅王企鹅',1008,NULL),(2,24,1,0,'2','2020-10-10','王企鹅王企鹅',1008,NULL),(3,24,1,0,'2','2020-10-10','王企鹅王企鹅',1001,NULL),(4,21,1,0,'1','2020-10-10','王企鹅王企鹅',1008,NULL),(5,21,3,0,'1','2020-10-10','王企鹅王企鹅',1008,NULL),(6,21,1,0,'2','2020-10-10','王企鹅王企鹅',1005,NULL),(7,21,1,0,'1','2020-10-10','王企鹅王企鹅',1008,NULL),(8,20,1,0,'2','2020-10-10','王企鹅王企鹅',1008,NULL),(9,21,1,0,'1','2020-10-10','王企鹅王企鹅',1008,NULL),(10,21,1,0,'2','2020-10-10','王企鹅王企鹅',1005,NULL),(11,21,5,0,'1','2020-10-10','王企鹅王企鹅',1008,NULL),(12,20,5,0,'2','2020-10-10','王企鹅王企鹅',1008,NULL),(13,7805591,3,0,'123213','2020-08-31','12321321',1001,NULL),(14,6251203,3,0,'项目有可参考价值','2020-09-16','王企鹅王企鹅请问',1001,NULL),(15,1643955,3,0,'2eqwewq','2020-09-10','请问我去额',1001,NULL),(16,4834853,3,0,'wqewqe','2020-09-01',' 亲吻青蛙',1009,NULL),(17,23,3,0,'我去恶趣味','2020-09-02','趣味无穷',1002,NULL),(18,4236890,3,0,'qweqw','2020-09-02',' 亲吻青蛙',1001,NULL),(19,23,2,0,'我去恶趣味','2020-09-02','趣味无穷',1002,NULL),(20,7805591,2,0,'123213','2020-08-31','12321321',1001,NULL),(21,7575704,5,0,NULL,NULL,NULL,1001,NULL),(22,6251203,2,0,'项目有可参考价值','2020-09-16','王企鹅王企鹅请问',1001,NULL),(23,1643955,2,0,'2eqwewq','2020-09-10','请问我去额',1001,NULL),(24,1182620,1,0,NULL,NULL,NULL,1001,NULL),(25,3040618,3,0,'12312','2020-09-16','12321',1001,NULL),(26,3040618,2,1,'12312','2020-09-16','12321',1001,NULL),(27,3040618,1,1,'12312','2020-09-16','12321',1001,NULL);

/*Table structure for table `cost_type` */

DROP TABLE IF EXISTS `cost_type`;

CREATE TABLE `cost_type` (
  `id` int(11) NOT NULL COMMENT '主键id',
  `name` varchar(40) NOT NULL COMMENT '费用类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cost_type` */

/*Table structure for table `country` */

DROP TABLE IF EXISTS `country`;

CREATE TABLE `country` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键唯一',
  `c_name` varchar(40) NOT NULL COMMENT '国家名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=241 DEFAULT CHARSET=utf8;

/*Data for the table `country` */

insert  into `country`(`id`,`c_name`) values (1,'中国'),(2,'阿尔巴尼亚'),(3,'阿尔及利亚'),(4,'阿富汗'),(5,'阿根廷'),(6,'阿拉伯联合酋长国'),(7,'阿鲁巴'),(8,'阿曼'),(9,'阿塞拜疆'),(10,'阿森松岛'),(11,'埃及'),(12,'埃塞俄比亚'),(13,'爱尔兰'),(14,'爱沙尼亚'),(15,'安道尔'),(16,'安哥拉'),(17,'安圭拉'),(18,'安提瓜岛和巴布达'),(19,'澳大利亚'),(20,'奥地利'),(21,'奥兰群岛'),(22,'巴巴多斯岛'),(23,'巴布亚新几内亚'),(24,'巴哈马'),(25,'巴基斯坦'),(26,'巴拉圭'),(27,'巴勒斯坦'),(28,'巴林'),(29,'巴拿马'),(30,'巴西'),(31,'白俄罗斯'),(32,'百慕大'),(33,'保加利亚'),(34,'北马里亚纳群岛'),(35,'贝宁'),(36,'比利时'),(37,'冰岛'),(38,'波多黎各'),(39,'波兰'),(40,'玻利维亚'),(41,'波斯尼亚和黑塞哥维那'),(42,'博茨瓦纳'),(43,'伯利兹'),(44,'不丹'),(45,'布基纳法索'),(46,'布隆迪'),(47,'布韦岛'),(48,'朝鲜'),(49,'丹麦'),(50,'德国'),(51,'东帝汶'),(52,'多哥'),(53,'多米尼加'),(54,'多米尼加共和国'),(55,'俄罗斯'),(56,'厄瓜多尔'),(57,'厄立特里亚'),(58,'法国'),(59,'法罗群岛'),(60,'法属波利尼西亚'),(61,'法属圭亚那'),(62,'法属南部领地'),(63,'梵蒂冈'),(64,'菲律宾'),(65,'斐济'),(66,'芬兰'),(67,'佛得角'),(68,'弗兰克群岛'),(69,'冈比亚'),(70,'刚果'),(71,'刚果民主共和国'),(72,'哥伦比亚'),(73,'哥斯达黎加'),(74,'格恩西岛'),(75,'格林纳达'),(76,'格陵兰'),(77,'古巴'),(78,'瓜德罗普'),(79,'关岛'),(80,'圭亚那'),(81,'哈萨克斯坦'),(82,'海地'),(83,'韩国'),(84,'荷兰'),(85,'荷属安地列斯'),(86,'赫德和麦克唐纳群岛'),(87,'洪都拉斯'),(88,'基里巴斯'),(89,'吉布提'),(90,'吉尔吉斯斯坦'),(91,'几内亚'),(92,'几内亚比绍'),(93,'加拿大'),(94,'加纳'),(95,'加蓬'),(96,'柬埔寨'),(97,'捷克共和国'),(98,'津巴布韦'),(99,'喀麦隆'),(100,'卡塔尔'),(101,'开曼群岛'),(102,'科科斯群岛'),(103,'科摩罗'),(104,'科特迪瓦'),(105,'科威特'),(106,'克罗地亚'),(107,'肯尼亚'),(108,'库克群岛'),(109,'拉脱维亚'),(110,'莱索托'),(111,'老挝'),(112,'黎巴嫩'),(113,'利比里亚'),(114,'利比亚'),(115,'立陶宛'),(116,'列支敦士登'),(117,'留尼旺岛'),(118,'卢森堡'),(119,'卢旺达'),(120,'罗马尼亚'),(121,'马达加斯加'),(122,'马尔代夫'),(123,'马耳他'),(124,'马拉维'),(125,'马来西亚'),(126,'马里'),(127,'马其顿'),(128,'马绍尔群岛'),(129,'马提尼克'),(130,'马约特岛'),(131,'曼岛'),(132,'毛里求斯'),(133,'毛里塔尼亚'),(134,'美国'),(135,'美属萨摩亚'),(136,'美属外岛'),(137,'蒙古'),(138,'蒙特塞拉特'),(139,'孟加拉'),(140,'密克罗尼西亚'),(141,'秘鲁'),(142,'缅甸'),(143,'摩尔多瓦'),(144,'摩洛哥'),(145,'摩纳哥'),(146,'莫桑比克'),(147,'墨西哥'),(148,'纳米比亚'),(149,'南非'),(150,'南极洲'),(151,'南乔治亚和南桑德威奇群岛'),(152,'瑙鲁'),(153,'尼泊尔'),(154,'尼加拉瓜'),(155,'尼日尔'),(156,'尼日利亚'),(157,'纽埃'),(158,'挪威'),(159,'诺福克'),(160,'帕劳群岛'),(161,'皮特凯恩'),(162,'葡萄牙'),(163,'乔治亚'),(164,'日本'),(165,'瑞典'),(166,'瑞士'),(167,'萨尔瓦多'),(168,'萨摩亚'),(169,'塞尔维亚,黑山'),(170,'塞拉利昂'),(171,'塞内加尔'),(172,'塞浦路斯'),(173,'塞舌尔'),(174,'沙特阿拉伯'),(175,'圣诞岛'),(176,'圣多美和普林西比'),(177,'圣赫勒拿'),(178,'圣基茨和尼维斯'),(179,'圣卢西亚'),(180,'圣马力诺'),(181,'圣皮埃尔和米克隆群岛'),(182,'圣文森特和格林纳丁斯'),(183,'斯里兰卡'),(184,'斯洛伐克'),(185,'斯洛文尼亚'),(186,'斯瓦尔巴和扬马廷'),(187,'斯威士兰'),(188,'苏丹'),(189,'苏里南'),(190,'所罗门群岛'),(191,'索马里'),(192,'塔吉克斯坦'),(193,'泰国'),(194,'坦桑尼亚'),(195,'汤加'),(196,'特克斯和凯克特斯群岛'),(197,'特里斯坦达昆哈'),(198,'特立尼达和多巴哥'),(199,'突尼斯'),(200,'图瓦卢'),(201,'土耳其'),(202,'土库曼斯坦'),(203,'托克劳'),(204,'瓦利斯和福图纳'),(205,'瓦努阿图'),(206,'危地马拉'),(207,'维尔京群岛，美属'),(208,'维尔京群岛，英属'),(209,'委内瑞拉'),(210,'文莱'),(211,'乌干达'),(212,'乌克兰'),(213,'乌拉圭'),(214,'乌兹别克斯坦'),(215,'西班牙'),(216,'希腊'),(217,'新加坡'),(218,'新喀里多尼亚'),(219,'新西兰'),(220,'匈牙利'),(221,'叙利亚'),(222,'牙买加'),(223,'亚美尼亚'),(224,'也门'),(225,'伊拉克'),(226,'伊朗'),(227,'以色列'),(228,'意大利'),(229,'印度'),(230,'印度尼西亚'),(231,'英国'),(232,'英属印度洋领地'),(233,'约旦'),(234,'越南'),(235,'赞比亚'),(236,'泽西岛'),(237,'乍得'),(238,'直布罗陀'),(239,'智利'),(240,'中非共和国');

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `id` int(11) NOT NULL COMMENT '主键唯一',
  `customer_name` varchar(100) NOT NULL COMMENT '客户名称',
  `country` int(11) DEFAULT NULL COMMENT '所在国家',
  `province` int(11) DEFAULT NULL COMMENT '所在省州',
  `city` int(11) DEFAULT NULL COMMENT '所在城市',
  `address` varchar(50) NOT NULL COMMENT '详细地址',
  `clientType` int(11) NOT NULL COMMENT '客户类型',
  `contact` varchar(50) DEFAULT NULL COMMENT '联系人数据格式[1,2,3]',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `ccountry` (`country`) USING BTREE,
  KEY `ccity` (`city`) USING BTREE,
  KEY `province` (`province`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`country`) REFERENCES `country` (`id`),
  CONSTRAINT `customer_ibfk_2` FOREIGN KEY (`province`) REFERENCES `province` (`id`),
  CONSTRAINT `customer_ibfk_3` FOREIGN KEY (`city`) REFERENCES `city` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `customer` */

insert  into `customer`(`id`,`customer_name`,`country`,`province`,`city`,`address`,`clientType`,`contact`) values (100,'司马',1,1,2,'西安大雁塔 ',2,'诸葛亮'),(101,'张飞',1,2,2,'河北',0,'关羽'),(102,'小乔',1,2,2,'江东',0,'刘备'),(103,'吕布',1,1,1,'中国北京',1,'貂蝉'),(104,'貂蝉',1,9,85,'中国上海',1,'吕布'),(105,'李云龙',1,1,1,'西北独立团',0,'秀芹'),(106,'赵子龙',1,3,3,'常山',3,'石家庄'),(107,'意大利',16,5,17,'我单位',2,'位符'),(108,'夫子',1,16,10,'微分热',3,'发热管'),(109,'宁缺',1,2,3,'非国大',4,'沟通的'),(110,'昊天',51,32,21,'据库vgdz',2,' 额头和'),(111,'馆主',1,2,1,' 而通过',3,'fdd'),(113,'vgd',2,3,3,'eafedvgfrtb',3,'dsqe'),(120,'qqqq',5,3,2,'wdde',2,'wde');

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(40) NOT NULL COMMENT '部门名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8;

/*Data for the table `department` */

insert  into `department`(`id`,`name`) values (1001,'销售'),(1002,'售后'),(1003,'类型3'),(1004,'类型4');

/*Table structure for table `education` */

DROP TABLE IF EXISTS `education`;

CREATE TABLE `education` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键唯一',
  `e_name` varchar(25) NOT NULL COMMENT '教育程度',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `education` */

insert  into `education`(`id`,`e_name`) values (1,'小学 '),(2,'初级中学'),(3,'高级中学'),(4,'专科'),(5,'本科'),(6,'硕士'),(7,'博士'),(8,'专家');

/*Table structure for table `employeecontribution` */

DROP TABLE IF EXISTS `employeecontribution`;

CREATE TABLE `employeecontribution` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `station_id` int(11) NOT NULL,
  `project_id` int(11) NOT NULL,
  `projWork_id` int(11) NOT NULL COMMENT '参与工时主键',
  `projApply_id` int(11) NOT NULL COMMENT '创建时间主键',
  `projImpl_id` int(11) NOT NULL COMMENT '实施工时主键',
  `workProcess_id` int(11) DEFAULT NULL COMMENT '解决工单主键',
  `workoders_id` int(11) DEFAULT NULL COMMENT '接受工单主键',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `station_id` (`station_id`),
  KEY `project_id` (`project_id`),
  KEY `projWork_id` (`projWork_id`),
  KEY `projApply_id` (`projApply_id`),
  KEY `projImpl_id` (`projImpl_id`),
  KEY `workProcess_id` (`workProcess_id`),
  KEY `workoders_id` (`workoders_id`),
  CONSTRAINT `employeecontribution_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `employeecontribution_ibfk_10` FOREIGN KEY (`workoders_id`) REFERENCES `workorder` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `employeecontribution_ibfk_2` FOREIGN KEY (`station_id`) REFERENCES `station` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `employeecontribution_ibfk_3` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `employeecontribution_ibfk_6` FOREIGN KEY (`projWork_id`) REFERENCES `project_work` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `employeecontribution_ibfk_7` FOREIGN KEY (`projApply_id`) REFERENCES `project_expenses_apply` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `employeecontribution_ibfk_8` FOREIGN KEY (`projImpl_id`) REFERENCES `project_implementation` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `employeecontribution_ibfk_9` FOREIGN KEY (`workProcess_id`) REFERENCES `workorder_process` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `employeecontribution` */

insert  into `employeecontribution`(`id`,`user_id`,`station_id`,`project_id`,`projWork_id`,`projApply_id`,`projImpl_id`,`workProcess_id`,`workoders_id`) values (1,1,10002,1,2,2,3,1,2),(2,2,10001,3,2,17,2,3,2),(3,4,10003,4,5,19,2,3,3),(4,7,10003,4,4,23,2,2,6),(7,7,10011,3,3,18,2,1,1),(8,51,10002,2,4,19,2,1,4),(10,3,10005,5,3,21,2,2,5),(11,1,10008,4,3,2,3,3,6),(12,2,10004,7,5,17,2,3,3),(13,8,10011,9,5,23,2,1,1),(14,7,10009,7,2,21,2,3,6),(15,3,10010,9,2,24,3,1,4),(16,5,10011,2,3,2,3,2,6),(17,1,10007,5,4,25,2,1,1),(18,8,10001,2,2,2,2,1,5),(19,49,10003,7,5,21,3,3,4),(20,8,10005,4,5,24,2,3,1);

/*Table structure for table `jurisdiction` */

DROP TABLE IF EXISTS `jurisdiction`;

CREATE TABLE `jurisdiction` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键唯一',
  `name` varchar(40) NOT NULL COMMENT '权限名称',
  `path` varchar(40) NOT NULL COMMENT '权限对应的请求路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*Data for the table `jurisdiction` */

insert  into `jurisdiction`(`id`,`name`,`path`) values (1,'目录','目录'),(2,'添加','add'),(3,'删除','del'),(4,'编辑','edit'),(5,'查询所有','queryAll'),(6,'模糊查询','doSearch'),(7,'查找单个','select'),(8,'批量删除','batchDelete'),(9,'预览','preview'),(10,'报备','rep'),(11,'添加费用','addPrice'),(12,'导入','import'),(13,'审核','examine'),(14,'延长申请','extension'),(15,'转出','roll_out'),(16,'添加体验','add_experience'),(17,'查看审批进度','view_approval_progress'),(18,'查看进度','view_progress'),(19,'添加开票','addticket'),(20,'添加任务','add_task'),(21,'添加日报','add_daily_report'),(22,'申请','apply'),(23,'反馈实施','feedback_implement'),(24,'处理','handle'),(25,'评价','evaluate'),(26,'去重','duplicate_removal');

/*Table structure for table `knowledge` */

DROP TABLE IF EXISTS `knowledge`;

CREATE TABLE `knowledge` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` int(11) DEFAULT NULL COMMENT '知识库分类',
  `create_time` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '知识内容',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人',
  `update_user` int(10) DEFAULT NULL COMMENT '更新人',
  `update_time` varchar(50) DEFAULT NULL COMMENT '更新时间',
  `time` int(11) DEFAULT NULL COMMENT '查看数',
  `top` char(1) DEFAULT NULL COMMENT 'y置顶n不置顶',
  `image` varchar(255) DEFAULT NULL COMMENT '上传的图片',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `knowledge_knowledge_type` (`type`) USING BTREE,
  KEY `knowledge_user_u` (`update_user`) USING BTREE,
  KEY `knowledge_user_c` (`create_user`) USING BTREE,
  CONSTRAINT `knowledge_knowledge_type` FOREIGN KEY (`type`) REFERENCES `knowledge_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `knowledge_user_c` FOREIGN KEY (`create_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `knowledge_user_u` FOREIGN KEY (`update_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `knowledge` */

insert  into `knowledge`(`id`,`type`,`create_time`,`title`,`content`,`create_user`,`update_user`,`update_time`,`time`,`top`,`image`) values (2,1,'2020-12-12','标题1','内容1',1,NULL,NULL,NULL,NULL,''),(3,6,'2020-09-06 17:38:38','范德萨',NULL,3,NULL,NULL,NULL,NULL,NULL),(4,7,'2020-09-06 17:39:09','范德萨',NULL,3,NULL,NULL,NULL,NULL,NULL),(5,4,'2020-09-06 17:41:57','交底书',NULL,3,NULL,NULL,NULL,NULL,NULL),(6,6,'2020-09-06 17:52:37','范德萨',NULL,3,NULL,NULL,NULL,NULL,NULL),(7,6,'2020-09-06 17:56:48','fds','s fdsfa',3,NULL,NULL,NULL,NULL,NULL),(8,6,'2020-09-06 18:06:16','fds','fsd',3,NULL,NULL,NULL,NULL,NULL),(9,6,'2020-09-06 18:16:07','fds','fds',3,NULL,NULL,NULL,NULL,NULL),(10,6,'2020-09-06 18:17:21','fds','fdsdsf',3,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `knowledge_type` */

DROP TABLE IF EXISTS `knowledge_type`;

CREATE TABLE `knowledge_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '对应的知识分类类型',
  PRIMARY KEY (`id`,`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='知识分类';

/*Data for the table `knowledge_type` */

insert  into `knowledge_type`(`id`,`name`) values (1,'产品资料'),(2,'产品文档'),(3,'销售资料'),(4,'会计资料'),(5,'开发资料'),(6,'专家库'),(7,'测试');

/*Table structure for table `konwledge_commentary` */

DROP TABLE IF EXISTS `konwledge_commentary`;

CREATE TABLE `konwledge_commentary` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_user` int(11) DEFAULT NULL COMMENT '评论人',
  `konwlege_id` int(11) DEFAULT NULL COMMENT '评论所对应的知识id',
  `content` text COMMENT '评论内容',
  `parent` int(11) DEFAULT NULL COMMENT '针对那条评论进行评论',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `konwledge_commentary_user` (`create_user`) USING BTREE,
  KEY `konwledge_commentary_konwledge` (`konwlege_id`) USING BTREE,
  CONSTRAINT `konwledge_commentary_konwledge` FOREIGN KEY (`konwlege_id`) REFERENCES `knowledge` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `konwledge_commentary_user` FOREIGN KEY (`create_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `konwledge_commentary` */

/*Table structure for table `konwledge_type_role` */

DROP TABLE IF EXISTS `konwledge_type_role`;

CREATE TABLE `konwledge_type_role` (
  `konwlege_type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '知识库类型id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`role_id`,`konwlege_type_id`) USING BTREE,
  KEY `konwlege_type_id` (`konwlege_type_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='该表为知识库中类型与角色中间表，对于部分角色可进行查看相关的知识';

/*Data for the table `konwledge_type_role` */

/*Table structure for table `log` */

DROP TABLE IF EXISTS `log`;

CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) DEFAULT NULL COMMENT '操作人id',
  `createtime` varchar(50) NOT NULL COMMENT '创建时间',
  `ipaddress` varchar(15) DEFAULT NULL COMMENT '访问IP',
  `country` int(11) DEFAULT NULL COMMENT 'ip所属国家',
  `province` int(11) DEFAULT NULL COMMENT 'ip所属省份',
  `city` int(11) DEFAULT NULL COMMENT 'ip城市',
  `option_resources` int(11) NOT NULL COMMENT '操作资源',
  `option_jurisdiction` int(11) NOT NULL COMMENT '操作权限',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `log_user_id` (`user_id`) USING BTREE,
  KEY `log_country` (`country`) USING BTREE,
  KEY `log_province` (`province`) USING BTREE,
  KEY `log_city` (`city`) USING BTREE,
  KEY `option_resources` (`option_resources`) USING BTREE,
  KEY `option_jurisdiction` (`option_jurisdiction`) USING BTREE,
  CONSTRAINT `log_city` FOREIGN KEY (`city`) REFERENCES `city` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `log_country` FOREIGN KEY (`country`) REFERENCES `country` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `log_province` FOREIGN KEY (`province`) REFERENCES `province` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `log_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `option_jurisdiction` FOREIGN KEY (`option_jurisdiction`) REFERENCES `jurisdiction` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `option_resources` FOREIGN KEY (`option_resources`) REFERENCES `resource` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `log` */

/*Table structure for table `nation` */

DROP TABLE IF EXISTS `nation`;

CREATE TABLE `nation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键唯一',
  `n_name` varchar(20) NOT NULL COMMENT '民族名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

/*Data for the table `nation` */

insert  into `nation`(`id`,`n_name`) values (1,'汉族'),(2,'蒙古族'),(3,'回族'),(4,'藏族'),(5,'维吾尔族'),(6,'苗族'),(7,'彝族'),(8,'壮族'),(9,'布依族'),(10,'朝鲜族'),(11,'满族'),(12,'侗族'),(13,'瑶族'),(14,'白族'),(15,'土家族'),(16,'哈尼族'),(17,'哈萨克族'),(18,'傣族'),(19,'黎族'),(20,'傈僳族'),(21,'佤族'),(22,'畲族'),(23,'高山族'),(24,'拉祜族'),(25,'水族'),(26,'东乡族'),(27,'纳西族'),(28,'景颇族'),(29,'柯尔克孜族'),(30,'土族'),(31,'达斡尔族'),(32,'仫佬族'),(33,'羌族'),(34,' 布朗族'),(35,' 撒拉族'),(36,' 毛难族'),(37,' 仡佬族'),(38,' 锡伯族'),(39,' 阿昌族'),(40,' 普米族'),(41,' 塔吉克族'),(42,' 怒族'),(43,' 乌孜别克族'),(44,' 俄罗斯族'),(45,' 鄂温克族'),(46,' 崩龙族'),(47,' 保安族'),(48,' 裕固族'),(49,' 京族'),(50,' 塔塔尔族'),(51,' 独龙族'),(52,' 鄂伦春族'),(53,' 赫哲族'),(54,' 门巴族'),(55,' 珞巴族'),(56,' 基诺族');

/*Table structure for table `procurement` */

DROP TABLE IF EXISTS `procurement`;

CREATE TABLE `procurement` (
  `procurement_id` int(11) NOT NULL AUTO_INCREMENT,
  `procurement_type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`procurement_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `procurement` */

insert  into `procurement`(`procurement_id`,`procurement_type`) values (1,'集中采购'),(2,'集中采购'),(11,'分散采购'),(16,'集中采购'),(17,'集中采购');

/*Table structure for table `project` */

DROP TABLE IF EXISTS `project`;

CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键唯一',
  `identifier` varchar(40) NOT NULL COMMENT '项目编号',
  `project_name` varchar(25) NOT NULL COMMENT '项目名称',
  `business_id` int(11) DEFAULT NULL COMMENT '项目对应商机',
  `createp_id` int(11) DEFAULT NULL COMMENT '申请人id',
  `status` int(11) NOT NULL COMMENT '项目状态0：开发中1：实施中2：上线',
  `uptime` varchar(50) DEFAULT NULL COMMENT '上线时间',
  `prolong` varchar(50) DEFAULT NULL COMMENT '延长上线时间',
  `createtime` varchar(50) NOT NULL COMMENT '申请时间',
  `description` varchar(255) DEFAULT NULL COMMENT '项目描述',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `project_create_user` (`createp_id`) USING BTREE,
  KEY `business_projecct` (`business_id`) USING BTREE,
  CONSTRAINT `business_projecct` FOREIGN KEY (`business_id`) REFERENCES `businessopporitunity` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `project_create_user` FOREIGN KEY (`createp_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `project` */

insert  into `project`(`id`,`identifier`,`project_name`,`business_id`,`createp_id`,`status`,`uptime`,`prolong`,`createtime`,`description`) values (1,'A001','项目一',1001,2,1,'2020-09-07','2020-05-12','2020-08-11','ff'),(2,'A002','项目三',1005,1,2,'2020-02-01','2020-04-02','2020-02-01','jjjjjjjj'),(3,'A003','项目三',1001,1,2,'2020-12-12','2020-12-14','2020-12-12',NULL),(4,'A004','项目四',1006,2,2,'2019-12-12','2019-12-12','2020-08-26',NULL),(5,'A005','项目五',1006,2,1,'2020-05-12','2020-05-12','2020-08-04',NULL),(7,'A007','项目七',1006,2,1,'2019-12-12','2019-12-12','2020-08-28',NULL),(8,'A002','项目八',1005,1,1,'2020-03-02','2020-03-17','2020-03-01','sss'),(9,'A002','项目九',1005,1,2,'2020-09-05','2020-09-10','2020-09-04','yyy');

/*Table structure for table `project_expenses_apply` */

DROP TABLE IF EXISTS `project_expenses_apply`;

CREATE TABLE `project_expenses_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_id` int(11) NOT NULL COMMENT '所属项目',
  `apply_user` int(11) DEFAULT NULL COMMENT '申请人id',
  `process_id` int(11) DEFAULT NULL COMMENT '审批流程主键',
  `createtime` varchar(50) NOT NULL COMMENT '创建时间',
  `apply_money` double(10,2) NOT NULL COMMENT '申请金额',
  `apply_type` int(11) NOT NULL COMMENT '申请类型',
  `apply_status` int(11) DEFAULT NULL COMMENT '申请状态0：审批中1：成功2：驳回',
  `apply_description` varchar(255) DEFAULT NULL COMMENT '申请描述',
  `gistaddr` varchar(255) DEFAULT NULL COMMENT '申请凭证（发票照片）',
  `commodity` varchar(255) DEFAULT NULL COMMENT '商品信息',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `reviewer_id` int(11) DEFAULT NULL COMMENT '审核人id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `expenses_project` (`project_id`) USING BTREE,
  KEY `expenses_apply_user` (`apply_user`) USING BTREE,
  KEY `expenses_process_id` (`process_id`) USING BTREE,
  CONSTRAINT `expenses_apply_user` FOREIGN KEY (`apply_user`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `expenses_process_id` FOREIGN KEY (`process_id`) REFERENCES `approval_process` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `expenses_project` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

/*Data for the table `project_expenses_apply` */

insert  into `project_expenses_apply`(`id`,`project_id`,`apply_user`,`process_id`,`createtime`,`apply_money`,`apply_type`,`apply_status`,`apply_description`,`gistaddr`,`commodity`,`remarks`,`reviewer_id`) values (2,5,2,2,'2020-08-28',10.00,2,0,'描述2','发票2','商品信息2','备注2',1),(17,1,3,1,'2020-08-28',11111.00,1,1,'描述3','发票3','商品信息','备注3',2),(18,4,4,2,'2020-08-28',20.00,1,0,'描述4','发票4','商品信息4','备注4',2),(19,2,5,1,'2020-08-28',40.00,2,0,'描述5','发票5','商品信息5','备注5',1),(21,5,2,1,'2020-08-25',342.00,2,0,'范德萨',NULL,'商品信息6',NULL,1),(23,4,3,1,'2020-08-12',32.00,1,2,'范德萨',NULL,'商品信息',NULL,2),(24,1,2,2,'2020-09-22',32.00,2,0,'范德萨',NULL,'示范法',NULL,1),(25,1,2,3,'2020-09-22',21.00,2,0,'范德萨',NULL,'范德萨',NULL,1),(27,2,3,1,'2020-09-21',23.00,2,3,'富士达',NULL,'商品信息2',NULL,2),(31,1,3,2,'2020-09-07',1111.00,1,1,'范德萨',NULL,'收到',NULL,2),(32,1,3,3,'2020-09-22',89.00,2,3,'hk',NULL,'hk',NULL,2),(33,3,3,4,'2020-09-29',56.00,1,3,'hgf',NULL,'hfh',NULL,2),(34,1,3,4,'2020-10-01',78.00,1,3,'jhb',NULL,'bj',NULL,2),(35,1,3,NULL,'2020-09-22',23.00,1,NULL,'审核描述',NULL,'商品信息',NULL,2),(36,4,3,NULL,'2020-09-07',12.00,2,NULL,'描述',NULL,'信息',NULL,2);

/*Table structure for table `project_expenses_approval` */

DROP TABLE IF EXISTS `project_expenses_approval`;

CREATE TABLE `project_expenses_approval` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `expenses_id` int(11) NOT NULL COMMENT '所属费用申请表id',
  `reviewer_id` int(11) DEFAULT NULL COMMENT '审核人id',
  `createtime` varchar(50) DEFAULT NULL COMMENT '审核时间',
  `status` int(11) DEFAULT NULL COMMENT '审核状态1：未审核2：驳回3：同意',
  `description` varchar(255) DEFAULT NULL COMMENT '审核描述',
  `parent_Reviewer` int(11) DEFAULT '0' COMMENT '新增,上级审核人id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `expense_aplly_id` (`expenses_id`) USING BTREE,
  KEY `expense_reviewer_user` (`reviewer_id`) USING BTREE,
  CONSTRAINT `expense_aplly_id` FOREIGN KEY (`expenses_id`) REFERENCES `project_expenses_apply` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `expense_reviewer_user` FOREIGN KEY (`reviewer_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;

/*Data for the table `project_expenses_approval` */

insert  into `project_expenses_approval`(`id`,`expenses_id`,`reviewer_id`,`createtime`,`status`,`description`,`parent_Reviewer`) values (54,17,2,'2020-08-30',1,'fds',1),(55,23,2,'2020-08-30',1,'fds',1),(56,27,2,'2020-09-14',2,'fds',0),(57,17,1,'2020-09-14',1,'fds',0),(58,23,1,'2020-08-13',2,'fsd',0),(59,27,2,'2020-08-30',1,'fsd',1),(60,27,1,NULL,NULL,NULL,0),(61,33,2,NULL,NULL,NULL,0),(62,31,2,'2020-09-07',1,'fdjsi',1),(63,32,2,'2020-09-07',1,'fdjsi',1),(64,34,2,'2020-09-07',2,'fds',0),(65,31,1,'2020-09-16',1,'fds',0),(66,32,1,'2020-09-16',2,'fds',0),(67,32,2,NULL,NULL,NULL,0),(68,34,2,NULL,NULL,NULL,0);

/*Table structure for table `project_experience` */

DROP TABLE IF EXISTS `project_experience`;

CREATE TABLE `project_experience` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_id` int(11) NOT NULL COMMENT '所对应的项目表主键',
  `content` varchar(255) DEFAULT NULL COMMENT '项目初验汇报',
  `tester` int(11) DEFAULT NULL COMMENT '测试人主键',
  `key` int(11) NOT NULL COMMENT '1：关键点0：非关键点',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `experienceproj_project_id` (`project_id`) USING BTREE,
  KEY `tester` (`tester`) USING BTREE,
  CONSTRAINT `experienceproj_project_id` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tester` FOREIGN KEY (`tester`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `project_experience` */

/*Table structure for table `project_implementation` */

DROP TABLE IF EXISTS `project_implementation`;

CREATE TABLE `project_implementation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_id` int(11) NOT NULL COMMENT '所属项目id',
  `manager` int(11) DEFAULT NULL COMMENT '负责人',
  `start_time` varchar(50) NOT NULL COMMENT '实施开始时间',
  `end_time` varchar(50) DEFAULT NULL COMMENT '实施预计结束时间',
  `actual_time` varchar(50) DEFAULT NULL COMMENT '实施实际时间',
  `cycle` int(11) DEFAULT NULL COMMENT '实施工时',
  `actual_cycle` int(11) DEFAULT NULL COMMENT '实际工时',
  `remarks` varchar(255) DEFAULT NULL COMMENT '描述',
  `station_id` int(11) NOT NULL COMMENT '岗位名称主键',
  `user_id` int(11) NOT NULL COMMENT '员工编号主键',
  `projApply_id` int(11) NOT NULL COMMENT '创建时间主键',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `implproj_project_id` (`project_id`) USING BTREE,
  KEY `implproj_manager` (`manager`) USING BTREE,
  KEY `station_id` (`station_id`),
  KEY `user_id` (`user_id`),
  KEY `projApply_id` (`projApply_id`),
  CONSTRAINT `implproj_manager` FOREIGN KEY (`manager`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `project_implementation_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `project_implementation_ibfk_2` FOREIGN KEY (`station_id`) REFERENCES `station` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `project_implementation_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `project_implementation_ibfk_4` FOREIGN KEY (`projApply_id`) REFERENCES `project_expenses_apply` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `project_implementation` */

insert  into `project_implementation`(`id`,`project_id`,`manager`,`start_time`,`end_time`,`actual_time`,`cycle`,`actual_cycle`,`remarks`,`station_id`,`user_id`,`projApply_id`) values (2,1,1,'2020-03-04','2020-05-06','2020-04-28',52,30,'2',10001,1,2),(3,3,5,'2020-03-05','2020-08-12','2020-09-12',33,20,'1',10002,7,23);

/*Table structure for table `project_implementation_cost_apply` */

DROP TABLE IF EXISTS `project_implementation_cost_apply`;

CREATE TABLE `project_implementation_cost_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `number` varchar(25) NOT NULL COMMENT '编号',
  `process_id` int(11) DEFAULT NULL COMMENT '审批流程主键',
  `project_implementation_id` int(11) DEFAULT NULL COMMENT '所属项目实施表主键',
  `apply_user` int(11) DEFAULT NULL COMMENT '申请人id',
  `money` double(12,2) NOT NULL COMMENT '申请金额',
  `type` int(11) DEFAULT NULL COMMENT '申请类型',
  `path` varchar(255) DEFAULT NULL COMMENT '申请账单收据图片地址',
  `description` varchar(255) DEFAULT NULL COMMENT '申请描述',
  `status` int(11) DEFAULT NULL COMMENT '0:审批中1：审批同意2：申请驳回',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `project_implementation_id_apply` (`project_implementation_id`) USING BTREE,
  KEY `apply_user` (`apply_user`) USING BTREE,
  KEY `process_id` (`process_id`) USING BTREE,
  CONSTRAINT `apply_user` FOREIGN KEY (`apply_user`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `process_id` FOREIGN KEY (`process_id`) REFERENCES `approval_process` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `project_implementation_id_apply` FOREIGN KEY (`project_implementation_id`) REFERENCES `project_implementation` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `project_implementation_cost_apply` */

insert  into `project_implementation_cost_apply`(`id`,`number`,`process_id`,`project_implementation_id`,`apply_user`,`money`,`type`,`path`,`description`,`status`) values (1,'2',2,2,1,10000.00,0,'0','0',1);

/*Table structure for table `project_implementation_cost_approval` */

DROP TABLE IF EXISTS `project_implementation_cost_approval`;

CREATE TABLE `project_implementation_cost_approval` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `project_implementation_cost_apply_id` int(11) NOT NULL COMMENT '所对应的申请表id',
  `approval_user` int(11) DEFAULT NULL COMMENT '审批人id',
  `createtime` varchar(50) NOT NULL COMMENT '创建时间',
  `status` int(11) NOT NULL COMMENT '0：待审批1：审批同意2：驳回',
  `description` varchar(255) DEFAULT NULL COMMENT '审批意见',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `project_implementation_cost_apply_id` (`project_implementation_cost_apply_id`) USING BTREE,
  KEY `approval_user` (`approval_user`) USING BTREE,
  CONSTRAINT `approval_user` FOREIGN KEY (`approval_user`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `project_implementation_cost_apply_id` FOREIGN KEY (`project_implementation_cost_apply_id`) REFERENCES `project_implementation_cost_apply` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `project_implementation_cost_approval` */

/*Table structure for table `project_task` */

DROP TABLE IF EXISTS `project_task`;

CREATE TABLE `project_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_id` int(11) NOT NULL COMMENT '所属项目',
  `project_work_id` int(11) NOT NULL COMMENT '所属项目总工时',
  `user_ids` varchar(100) DEFAULT NULL COMMENT '责任人数据格式为{‘2‘：1,''0'':2}key为状态value为责任人id',
  `manager_id` int(11) DEFAULT NULL COMMENT '负责人',
  `name` varchar(50) DEFAULT NULL COMMENT '任务名称',
  `plan_time` int(11) NOT NULL COMMENT '计划工时',
  `actul_time` int(11) DEFAULT NULL COMMENT '实际工时',
  `status` int(20) DEFAULT NULL COMMENT '0：开发中1：开发完成2：转发',
  `description` varchar(255) DEFAULT NULL COMMENT '任务描述',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `task_project` (`project_id`) USING BTREE,
  KEY `task_manager` (`manager_id`) USING BTREE,
  KEY `task_project_work` (`project_work_id`) USING BTREE,
  CONSTRAINT `task_manager` FOREIGN KEY (`manager_id`) REFERENCES `user` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `task_project` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `task_project_work` FOREIGN KEY (`project_work_id`) REFERENCES `project_work` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `project_task` */

insert  into `project_task`(`id`,`project_id`,`project_work_id`,`user_ids`,`manager_id`,`name`,`plan_time`,`actul_time`,`status`,`description`) values (1,1,3,'3',3,'任务二',12,12,2,'无'),(3,5,4,'1',2,'任务四',23,23,2,'无'),(4,2,5,'4',4,'任务五',12,12,1,'无');

/*Table structure for table `project_work` */

DROP TABLE IF EXISTS `project_work`;

CREATE TABLE `project_work` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_id` int(11) NOT NULL COMMENT '所属项目',
  `join_user` varchar(150) NOT NULL COMMENT '参与人员数据格式{1,2,3,4}',
  `manager_id` int(11) DEFAULT NULL COMMENT '管理人员id',
  `estimated_man_hours` int(11) NOT NULL COMMENT '预计使用工时（小时制）',
  `actual_working_hours` int(11) DEFAULT NULL COMMENT '实际使用工时（小时制）',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `work_time_project` (`project_id`) USING BTREE,
  KEY `project_manager_id` (`manager_id`) USING BTREE,
  CONSTRAINT `project_manager_id` FOREIGN KEY (`manager_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `work_time_project` FOREIGN KEY (`project_id`) REFERENCES `businessopporitunity` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `project_work` */

insert  into `project_work`(`id`,`project_id`,`join_user`,`manager_id`,`estimated_man_hours`,`actual_working_hours`) values (2,1001,'2',2,20,45),(3,1002,'3',3,30,45),(4,1005,'4',4,40,45),(5,1006,'5',5,50,45);

/*Table structure for table `province` */

DROP TABLE IF EXISTS `province`;

CREATE TABLE `province` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键唯一',
  `country` int(11) NOT NULL COMMENT '对应国家主键',
  `p_name` varchar(25) NOT NULL COMMENT '省州名称',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `country_province` (`country`) USING BTREE,
  CONSTRAINT `province_ibfk_1` FOREIGN KEY (`country`) REFERENCES `country` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

/*Data for the table `province` */

insert  into `province`(`id`,`country`,`p_name`) values (1,1,'北京'),(2,1,'天津'),(3,1,'河北'),(4,1,'山西'),(5,1,'内蒙古'),(6,1,'辽宁'),(7,1,'吉林'),(8,1,'黑龙江'),(9,1,'上海'),(10,1,'江苏'),(11,1,'浙江'),(12,1,'安徽'),(13,1,'福建'),(14,1,'江西'),(15,1,'山东'),(16,1,'河南'),(17,1,'湖北'),(18,1,'湖南'),(19,1,'广东'),(20,1,'广西'),(21,1,'海南'),(22,1,'重庆'),(23,1,'四川'),(24,1,'贵州'),(25,1,'云南'),(26,1,'西藏'),(27,1,'陕西'),(28,1,'甘肃'),(29,1,'青海'),(30,1,'宁夏'),(31,1,'新疆'),(32,1,'台湾'),(33,1,'香港'),(34,1,'澳门');

/*Table structure for table `resource` */

DROP TABLE IF EXISTS `resource`;

CREATE TABLE `resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键唯一',
  `name` varchar(50) NOT NULL COMMENT '资源名称',
  `path` varchar(50) NOT NULL COMMENT '资源访问路径',
  `type` char(1) NOT NULL COMMENT '资源类型C为目录M为菜单',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

/*Data for the table `resource` */

insert  into `resource`(`id`,`name`,`path`,`type`) values (1,'权限管理','jurisdiction.html','M'),(2,'资源管理','resource.html','M'),(3,'角色管理','role.html','M'),(5,'项目成本统计','project_cost_statistics.html','M'),(7,'系统维护','权限管理,资源管理,角色管理,审批流程,类型管理,用户角色,用户管理','C'),(12,'客户管理','customer.html','M'),(13,'客户','客户管理','C'),(14,'商机管理','商机管理首页,商机跟进管理,商机费用管理,费用审核管理','C'),(15,'商机管理首页','content.html','M'),(16,'商机跟进管理','follow.html','M'),(17,'费用审核管理','check.html','M'),(18,'商机费用管理','price.html','M'),(20,'合同管理','合同信息管理,开票管理','C'),(21,'合同信息管理','合同信息管理.html','M'),(22,'开票管理','开票管理.html','M'),(23,'工时管理','项目工时管理,项目任务管理,日报管理,延期管理','C'),(24,'项目工时管理','章展负责模块/项目工时管理.html','M'),(25,'项目任务管理','章展负责模块/项目任务管理.html','M'),(26,'日报管理','章展负责模块/日报管理.html','M'),(27,'延期管理','章展负责模块/延期管理.html','M'),(28,'采购管理','采购信息管理,采购审核','C'),(29,'采购信息管理','郝进鹏负责模块项目/采购信息管理.html','M'),(30,'采购审核','郝进鹏负责模块项目/采购审核.html','M'),(31,'实施管理','实施信息管理,实施费用管理,实施日报管理,实施体验管理','C'),(32,'实施信息管理','黎英涛proj.xiangmu/information.html','M'),(33,'实施费用管理','黎英涛proj.xiangmu/cost.html','M'),(34,'实施日报管理','黎英涛proj.xiangmu/daily.html','M'),(35,'实施体验管理','黎英涛proj.xiangmu/experence.html','M'),(36,'项目','项目管理','C'),(37,'项目管理','田景娜crm/project.html','M'),(38,'售后管理','工单处理,工单回访,工单记录','C'),(39,'工单处理','李晨proj_项目/工单处理.html','M'),(40,'工单回访','李晨proj_项目/工单回访.html','M'),(41,'工单记录','李晨proj_项目/工单记录.html','M'),(42,'统计管理','项目成本统计,员工贡献统计,商机统计,项目工单统计','C'),(45,'员工贡献统计','田景娜crm/employeeContribution.html','M'),(47,'项目工单统计','郝进鹏负责模块项目/项目工单统计.html','M'),(48,'商机统计','follow.html','M'),(49,'审批流程','吴新宇08-06CRM/审批流程1_1.html','M'),(50,'类型管理','类型管理/main.html','M'),(51,'用户角色','user_role.html','M'),(53,'用户管理','user.html','M'),(54,'客户拜访管理','客户拜访,客户拜访审核,拜访日程','C'),(55,'客户拜访','李丹迪crm-ldd/ClientVisit.html','M'),(56,'客户拜访审核','李丹迪crm-ldd/AuditManagement.html','M'),(57,'拜访日程','李丹迪crm-ldd/vs.html','M'),(58,'通讯录','通讯录详情','C'),(59,'通讯录详情','通讯录.html','M'),(60,'知识库','知识库详情','C'),(61,'知识库详情','李倩楠-crm-0805/knowledge.html','M');

/*Table structure for table `resources_jurisdiction` */

DROP TABLE IF EXISTS `resources_jurisdiction`;

CREATE TABLE `resources_jurisdiction` (
  `resourceid` int(11) NOT NULL COMMENT '资源id',
  `jurisdiction` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`resourceid`,`jurisdiction`) USING BTREE,
  KEY `jurisdictionid` (`jurisdiction`) USING BTREE,
  CONSTRAINT `jurisdictionid` FOREIGN KEY (`jurisdiction`) REFERENCES `jurisdiction` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `resourceid` FOREIGN KEY (`resourceid`) REFERENCES `resource` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `resources_jurisdiction` */

insert  into `resources_jurisdiction`(`resourceid`,`jurisdiction`) values (7,1),(13,1),(14,1),(20,1),(23,1),(28,1),(31,1),(36,1),(38,1),(42,1),(54,1),(58,1),(60,1),(1,2),(2,2),(3,2),(12,2),(15,2),(16,2),(18,2),(21,2),(24,2),(29,2),(32,2),(41,2),(49,2),(51,2),(55,2),(59,2),(1,3),(2,3),(3,3),(12,3),(15,3),(16,3),(18,3),(21,3),(22,3),(24,3),(29,3),(32,3),(33,3),(34,3),(49,3),(51,3),(59,3),(1,4),(2,4),(12,4),(15,4),(16,4),(18,4),(21,4),(22,4),(24,4),(25,4),(29,4),(32,4),(49,4),(51,4),(55,4),(59,4),(5,5),(7,5),(12,5),(14,5),(15,5),(16,5),(17,5),(18,5),(21,5),(22,5),(24,5),(25,5),(26,5),(29,5),(30,5),(32,5),(33,5),(34,5),(35,5),(37,5),(39,5),(40,5),(41,5),(45,5),(47,5),(48,5),(49,5),(50,5),(51,5),(53,5),(55,5),(57,5),(59,5),(61,5),(1,6),(2,6),(3,6),(5,6),(7,6),(12,6),(14,6),(15,6),(16,6),(17,6),(18,6),(21,6),(22,6),(24,6),(25,6),(26,6),(29,6),(30,6),(32,6),(33,6),(34,6),(35,6),(37,6),(39,6),(40,6),(41,6),(45,6),(47,6),(48,6),(49,6),(50,6),(51,6),(53,6),(55,6),(57,6),(59,6),(61,6),(1,8),(2,8),(3,8),(7,8),(12,8),(15,8),(16,8),(18,8),(49,8),(51,8),(53,8),(21,9),(22,9),(26,9),(27,9),(29,9),(30,9),(35,9),(55,9),(56,9),(16,11),(32,11),(55,11),(12,12),(53,12),(17,13),(25,13),(26,13),(27,13),(30,13),(33,13),(34,13),(56,13),(24,14),(25,15),(32,16),(18,17),(17,18),(21,19),(24,20),(25,21),(29,22),(35,23),(39,24),(40,25),(59,26);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键唯一',
  `name` varchar(25) NOT NULL COMMENT '角色名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`name`) values (1,'超级管理员'),(2,'管理员'),(3,'主管1'),(13,'超级管理'),(14,'辅助管理'),(15,'客户管理员'),(16,'商机管理员'),(17,'合同管理员'),(18,'工时管理员'),(19,'采购管理员'),(20,'实施管理员'),(24,'项目管理员'),(25,'售后管理员'),(26,'统计管理员'),(27,'客户拜访管理员'),(28,'通讯录管理员'),(29,'知识库管理员'),(30,'系统维护员'),(33,'fhgdjh'),(34,'gfyd');

/*Table structure for table `role_resources_jurisdiction` */

DROP TABLE IF EXISTS `role_resources_jurisdiction`;

CREATE TABLE `role_resources_jurisdiction` (
  `rid` int(11) NOT NULL COMMENT '角色id',
  `resourceid` int(11) NOT NULL COMMENT '资源id',
  `jurisdiction` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`rid`,`resourceid`,`jurisdiction`) USING BTREE,
  KEY `resourcesid` (`resourceid`) USING BTREE,
  KEY `jurisdiction` (`jurisdiction`) USING BTREE,
  KEY `resourceid` (`resourceid`,`jurisdiction`),
  CONSTRAINT `role_id` FOREIGN KEY (`rid`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role_resources_jurisdiction_ibfk_3` FOREIGN KEY (`resourceid`, `jurisdiction`) REFERENCES `resources_jurisdiction` (`resourceid`, `jurisdiction`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role_resources_jurisdiction` */

insert  into `role_resources_jurisdiction`(`rid`,`resourceid`,`jurisdiction`) values (13,1,2),(13,1,3),(13,1,4),(13,1,6),(13,1,8),(14,1,2),(14,1,3),(14,1,4),(14,1,6),(14,1,8),(30,1,2),(30,1,4),(30,1,6),(33,1,2),(33,1,3),(33,1,4),(33,1,6),(33,1,8),(2,2,2),(2,2,3),(2,2,4),(2,2,6),(2,2,8),(13,2,2),(13,2,3),(13,2,4),(13,2,6),(13,2,8),(14,2,2),(14,2,3),(14,2,4),(14,2,6),(14,2,8),(30,2,2),(30,2,4),(30,2,6),(34,2,2),(34,2,3),(34,2,4),(34,2,8),(2,3,2),(13,3,2),(13,3,3),(13,3,6),(13,3,8),(14,3,2),(14,3,3),(14,3,6),(14,3,8),(30,3,2),(30,3,3),(30,3,6),(30,3,8),(13,5,5),(13,5,6),(26,5,5),(26,5,6),(2,7,1),(13,7,1),(14,7,1),(30,7,1),(33,7,1),(34,7,1),(13,12,2),(13,12,3),(13,12,4),(13,12,5),(13,12,6),(13,12,8),(13,12,12),(15,12,2),(15,12,3),(15,12,4),(15,12,5),(15,12,6),(15,12,8),(15,12,12),(13,13,1),(15,13,1),(13,14,1),(16,14,1),(13,15,2),(13,15,3),(13,15,4),(13,15,5),(13,15,6),(13,15,8),(16,15,2),(16,15,3),(16,15,4),(16,15,5),(16,15,6),(16,15,8),(13,16,2),(13,16,3),(13,16,4),(13,16,5),(13,16,6),(13,16,8),(13,16,11),(16,16,2),(16,16,3),(16,16,4),(16,16,5),(16,16,6),(16,16,8),(16,16,11),(13,17,5),(13,17,6),(13,17,13),(13,17,18),(16,17,5),(16,17,6),(16,17,13),(16,17,18),(13,18,2),(13,18,3),(13,18,4),(13,18,5),(13,18,6),(13,18,8),(13,18,17),(16,18,2),(16,18,3),(16,18,4),(16,18,5),(16,18,6),(16,18,8),(16,18,17),(13,20,1),(17,20,1),(13,21,2),(13,21,3),(13,21,4),(13,21,5),(13,21,6),(13,21,9),(13,21,19),(17,21,2),(17,21,3),(17,21,4),(17,21,5),(17,21,6),(17,21,9),(17,21,19),(13,22,3),(13,22,4),(13,22,5),(13,22,6),(13,22,9),(17,22,3),(17,22,4),(17,22,5),(17,22,6),(17,22,9),(13,23,1),(18,23,1),(13,24,2),(13,24,3),(13,24,4),(13,24,5),(13,24,6),(13,24,14),(13,24,20),(18,24,2),(18,24,3),(18,24,4),(18,24,5),(18,24,6),(18,24,14),(18,24,20),(13,25,4),(13,25,5),(13,25,6),(13,25,13),(13,25,15),(13,25,21),(18,25,4),(18,25,5),(18,25,6),(18,25,13),(18,25,15),(18,25,21),(13,26,5),(13,26,6),(13,26,9),(13,26,13),(18,26,5),(18,26,6),(18,26,9),(18,26,13),(13,27,9),(13,27,13),(18,27,9),(18,27,13),(13,28,1),(19,28,1),(13,29,2),(13,29,3),(13,29,4),(13,29,5),(13,29,6),(13,29,9),(13,29,22),(19,29,2),(19,29,3),(19,29,4),(19,29,5),(19,29,6),(19,29,9),(19,29,22),(13,30,5),(13,30,6),(13,30,9),(13,30,13),(19,30,5),(19,30,6),(19,30,9),(19,30,13),(13,31,1),(20,31,1),(13,32,2),(13,32,3),(13,32,4),(13,32,5),(13,32,6),(13,32,11),(13,32,16),(20,32,2),(20,32,3),(20,32,4),(20,32,5),(20,32,6),(20,32,11),(20,32,16),(13,33,3),(13,33,5),(13,33,6),(13,33,13),(20,33,3),(20,33,5),(20,33,6),(20,33,13),(13,34,3),(13,34,5),(13,34,6),(13,34,13),(20,34,3),(20,34,5),(20,34,6),(20,34,13),(13,35,5),(13,35,6),(13,35,9),(13,35,23),(20,35,5),(20,35,6),(20,35,9),(20,35,23),(13,36,1),(24,36,1),(13,37,5),(13,37,6),(24,37,5),(24,37,6),(13,38,1),(25,38,1),(13,39,5),(13,39,6),(13,39,24),(25,39,5),(25,39,6),(25,39,24),(13,40,5),(13,40,6),(13,40,25),(25,40,5),(25,40,6),(25,40,25),(13,41,2),(13,41,5),(13,41,6),(25,41,2),(25,41,5),(25,41,6),(13,42,1),(26,42,1),(13,45,5),(13,45,6),(26,45,5),(26,45,6),(13,47,5),(13,47,6),(26,47,5),(26,47,6),(13,48,5),(13,48,6),(26,48,5),(26,48,6),(13,49,2),(13,49,3),(13,49,4),(13,49,5),(13,49,6),(13,49,8),(14,49,2),(14,49,3),(14,49,4),(14,49,5),(14,49,6),(14,49,8),(30,49,2),(30,49,3),(30,49,4),(30,49,5),(30,49,6),(30,49,8),(13,50,5),(13,50,6),(14,50,5),(14,50,6),(30,50,5),(30,50,6),(13,51,2),(13,51,3),(13,51,4),(13,51,5),(13,51,6),(13,51,8),(14,51,2),(14,51,3),(14,51,4),(14,51,5),(14,51,6),(14,51,8),(30,51,2),(30,51,3),(30,51,4),(30,51,5),(30,51,6),(30,51,8),(13,53,5),(13,53,6),(13,53,8),(13,53,12),(14,53,5),(14,53,6),(14,53,8),(14,53,12),(30,53,5),(30,53,6),(30,53,8),(30,53,12),(13,54,1),(27,54,1),(13,55,2),(13,55,4),(13,55,5),(13,55,6),(13,55,9),(13,55,11),(27,55,2),(27,55,4),(27,55,5),(27,55,6),(27,55,9),(27,55,11),(13,56,9),(13,56,13),(27,56,9),(27,56,13),(13,57,5),(13,57,6),(27,57,5),(27,57,6),(13,58,1),(28,58,1),(13,59,2),(13,59,3),(13,59,4),(13,59,5),(13,59,6),(13,59,26),(28,59,2),(28,59,3),(28,59,4),(28,59,5),(28,59,6),(28,59,26),(13,60,1),(29,60,1),(13,61,5),(13,61,6),(29,61,5),(29,61,6);

/*Table structure for table `station` */

DROP TABLE IF EXISTS `station`;

CREATE TABLE `station` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `station_name` varchar(30) NOT NULL COMMENT '岗位名称',
  `department_id` int(11) NOT NULL COMMENT '部门编号',
  PRIMARY KEY (`id`),
  KEY `department_id` (`department_id`),
  CONSTRAINT `station_ibfk_1` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10012 DEFAULT CHARSET=utf8;

/*Data for the table `station` */

insert  into `station`(`id`,`station_name`,`department_id`) values (10001,'a7',1001),(10002,'a6',1001),(10003,'a4',1002),(10004,'a1',1004),(10005,'a2',1003),(10006,'a3',1002),(10007,'a5',1003),(10008,'a7',1004),(10009,'a8',1004),(10010,'a9',1003),(10011,'a10',1003);

/*Table structure for table `task_forword` */

DROP TABLE IF EXISTS `task_forword`;

CREATE TABLE `task_forword` (
  `id` int(11) NOT NULL COMMENT '主键',
  `task_id` int(11) NOT NULL COMMENT '所属任务主键',
  `applicant_id` int(11) DEFAULT NULL COMMENT '申请人id',
  `reviewer_id` int(11) DEFAULT NULL COMMENT '审核人id',
  `receiver_id` int(11) DEFAULT NULL COMMENT '接收人id',
  `apply_description` varchar(255) DEFAULT NULL COMMENT '申请描述',
  `approval_description` varchar(255) DEFAULT NULL COMMENT '审批描述',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `forward_task_id` (`task_id`) USING BTREE,
  KEY `applicant_id` (`applicant_id`) USING BTREE,
  KEY `reviewer_id` (`reviewer_id`) USING BTREE,
  KEY `receiver_id` (`receiver_id`) USING BTREE,
  CONSTRAINT `applicant_id` FOREIGN KEY (`applicant_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `forward_task_id` FOREIGN KEY (`task_id`) REFERENCES `project_task` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `receiver_id` FOREIGN KEY (`receiver_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `reviewer_id` FOREIGN KEY (`reviewer_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `task_forword` */

insert  into `task_forword`(`id`,`task_id`,`applicant_id`,`reviewer_id`,`receiver_id`,`apply_description`,`approval_description`) values (1,1,1,1,1,NULL,NULL);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键唯一',
  `employee_number` varchar(25) NOT NULL COMMENT '员工号',
  `username` varchar(25) NOT NULL COMMENT '姓名(创建人)',
  `login_name` varchar(25) NOT NULL COMMENT '登录名唯一',
  `password` varchar(50) NOT NULL COMMENT '登录密码',
  `payroll` int(11) NOT NULL COMMENT '0不在职1在职',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `sex` int(11) DEFAULT NULL COMMENT '0代表男性1代表女性',
  `nation` int(11) DEFAULT NULL COMMENT '民族对应民族表id',
  `education_level` int(11) DEFAULT NULL COMMENT '教育程度对应教育表id',
  `tel` varchar(15) NOT NULL COMMENT '联系方式',
  `country` int(11) DEFAULT NULL COMMENT '所在国家主键对应国家id',
  `province` int(11) DEFAULT NULL COMMENT '所在省州对应省州表ID',
  `city` int(11) DEFAULT NULL COMMENT '所在城市对应城市表主键',
  `address` varchar(50) NOT NULL COMMENT '详细地址',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `login_name` (`login_name`),
  KEY `country` (`country`) USING BTREE,
  KEY `city` (`city`) USING BTREE,
  KEY `education` (`education_level`) USING BTREE,
  KEY `province` (`province`) USING BTREE,
  KEY `nation` (`nation`) USING BTREE,
  CONSTRAINT `user_ibfk_4` FOREIGN KEY (`nation`) REFERENCES `nation` (`id`),
  CONSTRAINT `user_ibfk_5` FOREIGN KEY (`education_level`) REFERENCES `education` (`id`),
  CONSTRAINT `user_ibfk_6` FOREIGN KEY (`country`) REFERENCES `country` (`id`),
  CONSTRAINT `user_ibfk_7` FOREIGN KEY (`province`) REFERENCES `province` (`id`),
  CONSTRAINT `user_ibfk_8` FOREIGN KEY (`city`) REFERENCES `city` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`employee_number`,`username`,`login_name`,`password`,`payroll`,`age`,`sex`,`nation`,`education_level`,`tel`,`country`,`province`,`city`,`address`) values (1,'003','admin','admin','CF7B35A0CDD4BDA97F4A9EEDAFDB6E80',1,25,1,1,1,'12356894',1,1,1,'1'),(2,'003','王二','admin1','CF7B35A0CDD4BDA97F4A9EEDAFDB6E80',0,20,1,2,2,'15946875',2,2,2,'天地会'),(3,'003','王三','admin2','CF7B35A0CDD4BDA97F4A9EEDAFDB6E80',0,20,1,2,2,'15946875',2,2,2,'天地会'),(4,'003','王四','admin3','CF7B35A0CDD4BDA97F4A9EEDAFDB6E80',0,20,1,2,2,'15946875',2,2,2,'天地会'),(5,'003','张三','张三丰','CF7B35A0CDD4BDA97F4A9EEDAFDB6E80',1,30,1,1,1,'9865243',1,1,1,'西安软件园'),(7,'003','王五','aaaaa','CF7B35A0CDD4BDA97F4A9EEDAFDB6E80',1,25,0,1,2,'15346',3,1,1,'xxxx'),(8,'001','李云龙','安安','7D027A22A6C9A4F86C4204553A560A84',1,36,1,5,5,'12345687',6,1,1,'dxtft'),(49,'213','253','qqqqq','809367017D1F389B0148A4031CA3E046',1,20,1,1,2,'3585',2,1,2,'3863'),(51,'51','51','sssss','B2296D7470D179401DA6EFEC03BA6F31',1,21,0,3,4,'258',4,2,4,'629'),(52,'001','qqq','00000','DDB61EE0C786231C55B7F2F354197273',0,22,1,38,5,'00000000',8,19,19,'0000000'),(54,'002','qqq','22222','BD16AAE820873B72CA5ED26626140202',0,24,1,6,4,'5726',26,15,1,'2222222'),(57,'001','ew','www','429532E0EEE6921F1F05AD28C331F4C1',1,NULL,NULL,4,3,'1235565',6,9,4,'esxew');

/*Table structure for table `user_dept` */

DROP TABLE IF EXISTS `user_dept`;

CREATE TABLE `user_dept` (
  `id` int(11) NOT NULL,
  `did` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_dept` (`did`),
  CONSTRAINT `user_dept` FOREIGN KEY (`did`) REFERENCES `department` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_user` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_dept` */

insert  into `user_dept`(`id`,`did`) values (4,1001),(7,1001),(1,1002),(2,1002),(3,1002),(5,1002);

/*Table structure for table `user_project_implementation` */

DROP TABLE IF EXISTS `user_project_implementation`;

CREATE TABLE `user_project_implementation` (
  `project_implementation_id` int(11) NOT NULL COMMENT '项目实施表主键',
  `user_id` int(11) NOT NULL COMMENT '实施人员id',
  PRIMARY KEY (`project_implementation_id`,`user_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  CONSTRAINT `project_implementation_id` FOREIGN KEY (`project_implementation_id`) REFERENCES `project_implementation` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_project_implementation` */

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `uid` int(11) NOT NULL COMMENT '用户id',
  `rid` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`uid`,`rid`) USING BTREE,
  KEY `rid` (`rid`) USING BTREE,
  CONSTRAINT `rid` FOREIGN KEY (`rid`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `uid` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`uid`,`rid`) values (5,1),(7,1),(2,2),(3,2),(1,13),(4,30);

/*Table structure for table `workorder` */

DROP TABLE IF EXISTS `workorder`;

CREATE TABLE `workorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键唯一 ',
  `number` varchar(255) NOT NULL COMMENT '工单号',
  `recive_uer` int(11) DEFAULT NULL COMMENT '接受人主键',
  `customer_id` int(11) DEFAULT NULL COMMENT '客户主键',
  `project_id` int(11) NOT NULL COMMENT '项目主键',
  `receive_time` varchar(50) NOT NULL COMMENT '接受时间',
  `require_time` varchar(50) NOT NULL COMMENT '截止时间',
  `status` int(11) NOT NULL COMMENT '工单状态0：已处理1：未处理2：已转发',
  `problem_description` varchar(255) DEFAULT NULL COMMENT '问题描述',
  `station_id` int(11) NOT NULL COMMENT '岗位id',
  `user_id` int(11) NOT NULL COMMENT '员工编号主键',
  `projectApply_id` int(11) NOT NULL COMMENT '创建时间主键',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `recive_uer` (`recive_uer`) USING BTREE,
  KEY `workorder_customer_id` (`customer_id`) USING BTREE,
  KEY `workorder_project_id` (`project_id`) USING BTREE,
  KEY `status` (`status`),
  KEY `station_id` (`station_id`),
  KEY `user_id` (`user_id`),
  KEY `projectApply_id` (`projectApply_id`),
  CONSTRAINT `recive_uer` FOREIGN KEY (`recive_uer`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `workorder_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `workorder_ibfk_1` FOREIGN KEY (`station_id`) REFERENCES `station` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `workorder_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `workorder_ibfk_3` FOREIGN KEY (`projectApply_id`) REFERENCES `project_expenses_apply` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `workorder_project_id` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `workorder` */

insert  into `workorder`(`id`,`number`,`recive_uer`,`customer_id`,`project_id`,`receive_time`,`require_time`,`status`,`problem_description`,`station_id`,`user_id`,`projectApply_id`) values (1,'g123',4,100,1,'2012-01-20','2020-02-25',1,'描述',10001,1,2),(2,'g123',2,100,1,'2019-08-09','2020-02-01',1,'描述',10001,2,17),(3,'g123',3,104,2,'2020-03-04','2020-05-06',1,'描述',10002,3,18),(4,'g123',5,101,5,'2020-06-07','2020-08-13',2,'描述',10002,5,19),(5,'g124',4,102,1,'2020-10-02','2020-11-11',1,'描述',10003,5,17),(6,'g125',1,103,7,'2020-09-03','2020-09-30',2,'描述',10001,4,2);

/*Table structure for table `workorder_process` */

DROP TABLE IF EXISTS `workorder_process`;

CREATE TABLE `workorder_process` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `workorder_id` int(11) NOT NULL COMMENT '工单主键',
  `forward_user` int(11) DEFAULT NULL COMMENT '转发人主键',
  `handle_user` int(11) DEFAULT NULL COMMENT '处理人主键',
  `problem_solve` varchar(255) DEFAULT NULL COMMENT '问题解决方案',
  `status` int(11) NOT NULL COMMENT '状态0：未解决1：已解决2：转发',
  `remarks` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `workorder_id` (`workorder_id`) USING BTREE,
  KEY `forward_user` (`forward_user`) USING BTREE,
  KEY `handle_user` (`handle_user`) USING BTREE,
  KEY `status` (`status`),
  CONSTRAINT `forward_user` FOREIGN KEY (`forward_user`) REFERENCES `workorder` (`recive_uer`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `handle_user` FOREIGN KEY (`handle_user`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `workorder_process_ibfk_2` FOREIGN KEY (`workorder_id`) REFERENCES `workorder` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `workorder_process` */

insert  into `workorder_process`(`id`,`workorder_id`,`forward_user`,`handle_user`,`problem_solve`,`status`,`remarks`) values (1,1,1,1,'问题解决1',2,NULL),(2,2,4,1,'解决问题',1,'hh'),(3,1,2,2,'dd',1,'ddd');

/*Table structure for table `workorder_visit` */

DROP TABLE IF EXISTS `workorder_visit`;

CREATE TABLE `workorder_visit` (
  `id` int(11) NOT NULL COMMENT '主键',
  `order_id` int(11) NOT NULL COMMENT '工单主键',
  `evaluate` int(11) DEFAULT NULL COMMENT '客户满意度1：很满意2：一般3：不满意',
  `suggest` varchar(500) DEFAULT NULL COMMENT '客户满意度',
  `visit_time` varchar(50) NOT NULL COMMENT '回访时间',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人主键',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `order_id` (`order_id`) USING BTREE,
  KEY `workorder_visit_create_user` (`create_user`) USING BTREE,
  CONSTRAINT `order_id` FOREIGN KEY (`order_id`) REFERENCES `workorder` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `workorder_visit_create_user` FOREIGN KEY (`create_user`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `workorder_visit` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
