CREATE TABLE `games`(
`id` int(11) not null unique auto_increment,
`time` timestamp default current_timestamp,
`played_id` varchar(30) not null,
`win` int(11) not null ,
`lose` int(11) not null ,
PRIMARY kEY(`id`)
)ENGINE=INNODB DEFAULT CHARSET=`utf8`;
