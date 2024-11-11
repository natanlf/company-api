CREATE TABLE `branch` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `employee` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `birth_datetime` datetime(6) DEFAULT NULL,
  `branch_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1ppr8greedyrey8nchpr0v4dn` (`branch_id`),
  CONSTRAINT `FK1ppr8greedyrey8nchpr0v4dn` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;