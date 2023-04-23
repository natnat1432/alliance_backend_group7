CREATE TABLE `user_test` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`userId`)
);

CREATE TABLE `shop_test` (
  `shopId` int(11) NOT NULL AUTO_INCREMENT,
  `shopName` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  PRIMARY KEY (`shopId`)
);

CREATE TABLE `user_shop` (
  `userShopId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `shopId` int(11) NOT NULL,
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`userShopId`),
  KEY `user_shop_user_test_fk` (`userId`),
  KEY `user_shop_shop_test_fk` (`shopId`),
  CONSTRAINT `user_shop_shop_test_fk` FOREIGN KEY (`shopId`) REFERENCES `shop_test` (`shopid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_shop_user_test_fk` FOREIGN KEY (`userId`) REFERENCES `user_test` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
)