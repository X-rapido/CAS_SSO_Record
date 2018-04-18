
create table sys_user (
 `id` int(11) not null auto_increment,
 `username` varchar(30) not null,
 `password` varchar(64) not null,
 `email`    varchar(50),
 `address`  varchar(100),
 `age`      int,
 `expired`  int,    -- 是否过期
 `disabled` int,    -- 是否禁用
 `locked`   int,    -- 是否锁定
  primary key (`id`)
) engine=innodb auto_increment=1 default charset=utf8;


/*
 * 账号加盐表
 */
create table sys_user_encode (
  `id` int(11) not null auto_increment,
  `username` varchar(30) not null,
  `password` varchar(64) not null,
  `email`    varchar(50),
  `address`  varchar(100),
  `age`      int,
  `expired` int,
  `disabled` int,
  primary key (`id`)
) engine=innodb auto_increment=1 default charset=utf8;




/*
初始化数据
*/


---明文MD5数据
/*123*/
insert into sys_user values ('1', 'admin', '202cb962ac59075b964b07152d234b70', 'admin@foxmail.com', '广州天河', 24, 0, 0, 0);
/*12345678*/
insert into sys_user values ('2', 'zhangsan', '25d55ad283aa400af464c76d713c07ad', 'zhangsan@foxmail.com', '广州越秀', 26, 0, 0, 0);
/*1234*/
/*禁用账户*/
insert into sys_user values('3', 'zhaosi','81dc9bdb52d04dc20036dbd8313ed055', 'zhaosi@foxmail.com', '广州海珠', 25, 0 , 1, 0);
/*12345*/
/*过期账户*/
insert into sys_user values('4', 'wangwu','827ccb0eea8a706c4c34a16891f84e7b', 'wangwu@foxmail.com', '广州番禺', 27, 1 , 0, 0);
/*123*/
/*锁定账户*/
insert into sys_user values('5', 'boss','202cb962ac59075b964b07152d234b70', 'boss@foxmail.com', '深圳', 30, 0 , 0, 1);

/*未加密，用来测试加密策略未NONE情况*/
insert into sys_user values ('6', 'tingfeng', 'tingfeng', 'admin@foxmail.com', '郑州东区', 24, 0, 0, 0);



---明文MD5+盐值数据

-- 明文密码 123
insert into sys_user_encode values ('1', 'admin_en', 'bfb194d5bd84a5fc77c1d303aefd36c3', 'huang.wenbin@foxmail.com', '江门蓬江', 24, 0, 0);
-- 明文密码 12345678
insert into sys_user_encode values ('2', 'zhangsan_en', '68ae075edf004353a0403ee681e45056',  'zhangsan@foxmail.com', '深圳宝安', 21, 0, 0);
-- 明文密码 1234
insert into sys_user_encode values ('3', 'zhaosi_en', 'd66108d0409f68af538301b637f13a18',  'zhaosi@foxmail.com', '清远清新', 20, 0, 1);
-- 明文密码 1234
insert into sys_user_encode values ('4', 'wangwu_en', '44b907d6fee23a552348eabf5fcf1ac7',  'wangwu@foxmail.com', '佛山顺德', 19, 1, 0);