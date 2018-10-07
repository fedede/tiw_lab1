DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `name` varchar(30) NOT NULL,
  `surname` varchar(30) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `is_admin` binary(1) NOT NULL,
  PRIMARY KEY (`email`),
  UNIQUE KEY `email_UNIQUE` (`email`)
);

DROP TABLE IF EXISTS `homes`;

CREATE TABLE `homes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `full_desc` text NOT NULL,
  `short_desc` tinytext NOT NULL,
  `is_private` binary(1) NOT NULL,
  `guest_num` int(11) NOT NULL,
  `img` varchar(45) NOT NULL,
  `price` decimal(3,2) NOT NULL,
  `init_date` date NOT NULL,
  `end_date` date NOT NULL,
  `owner` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `owner_idx` (`owner`),
  CONSTRAINT `fk_homes_users_email` FOREIGN KEY (`owner`) REFERENCES `users` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
);


DROP TABLE IF EXISTS `messages`;

CREATE TABLE `messages` (
  `sender` varchar(45) NOT NULL,
  `receiver` varchar(45) NOT NULL,
  `time` datetime NOT NULL,
  `msg` text NOT NULL,
  PRIMARY KEY (`sender`,`time`,`receiver`),
  KEY `fk_messages_rec_user_email_idx` (`receiver`),
  CONSTRAINT `fk_messages_rec_user_email` FOREIGN KEY (`receiver`) REFERENCES `users` (`email`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_messages_sen_user_email` FOREIGN KEY (`sender`) REFERENCES `users` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
);


DROP TABLE IF EXISTS `transactions`;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `transactions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guest` varchar(45) NOT NULL,
  `card_num` int(11) NOT NULL,
  `card_date` date NOT NULL,
  `cv2` int(11) NOT NULL,
  `house` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `status` char(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_transactions_user_email_idx` (`guest`),
  KEY `fk_transaction_homes_owner_idx` (`house`),
  CONSTRAINT `fk_transaction_homes_id` FOREIGN KEY (`house`) REFERENCES `homes` (`id`),
  CONSTRAINT `fk_transactions_users_email` FOREIGN KEY (`guest`) REFERENCES `users` (`email`) ON UPDATE CASCADE
);
