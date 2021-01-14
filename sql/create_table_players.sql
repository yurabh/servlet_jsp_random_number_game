CREATE TABLE `players`(
`id` int(30) not null unique auto_increment,
`userName` varchar(30) not null unique,
`balance` int(11) not null,
`lastBet` int(12) not null,
`created` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
`update` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY(`id`)
)ENGINE=INNODB DEFAULT CHARSET=`utf8`;
