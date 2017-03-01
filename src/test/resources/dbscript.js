CREATE DATABASE `blogzf` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `blogzf`.`t_system_dictionary_type` (
  `id` VARCHAR(32) NOT NULL,
  `code` VARCHAR(6) NOT NULL,
  `name` VARCHAR(200) NOT NULL,
  `remark` VARCHAR(1000) NULL,
  PRIMARY KEY (`id`))
COMMENT = '树形字典类型表';
INSERT INTO `blogzf`.`t_system_dictionary_type` (`id`, `code`, `name`) VALUES ('c02fdfa6cb3e11e6b042a08cfd1d3687', '0001', '博客分类');
INSERT INTO `blogzf`.`t_system_dictionary_tree` (`id`, `code`, `treeId`, `name`, `parent_id`, `type_code`, `isLeaf`) VALUES ('e63bd23ece6611e6b042a08cfd1d3687', '000101', '1.1', '程序人生', 'eaf05080c0974f098399d37626ff7706', '0001', '1');
INSERT INTO `blogzf`.`t_system_dictionary_tree` (`id`, `code`, `treeId`, `name`, `parent_id`, `type_code`, `isLeaf`) VALUES ('ede47737ce6611e6b042a08cfd1d3687', '000102', '1.2', '生活点滴', 'eaf05080c0974f098399d37626ff7706', '0001', '1');


CREATE TABLE `t_system_dictionary_tree` (
		  `id` varchar(32) NOT NULL,
		  `code` varchar(20) NOT NULL,
		  `treeId` varchar(20) NOT NULL COMMENT '1 1.1 1.2 ',
		  `name` varchar(200) NOT NULL,
		  `parent_id` varchar(32) NOT NULL,
		  `type_code` varchar(20) NOT NULL,
		  `remark` varchar(1000) DEFAULT NULL,
		  `isLeaf` varchar(1) NOT NULL COMMENT '指定是否为叶子节点，0：不是，1：是',
		  `orderNo` int(11) DEFAULT '0',
		  `levelNumber` int(11) DEFAULT '0',
		  PRIMARY KEY (`id`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='树形字典信息表';


CREATE TABLE `blogzf`.`t_blog_info` (
		  `id` VARCHAR(32) NOT NULL,
		  `title` VARCHAR(600) NOT NULL COMMENT '标题',
		  `blogType` VARCHAR(20) NOT NULL COMMENT '博客分类',
		  `summary` VARCHAR(1000) NULL COMMENT '摘要',
		  `blogTag` VARCHAR(200) NULL COMMENT '博客标签(Tag)',
		  `blogStatus` VARCHAR(1) NOT NULL DEFAULT 0 COMMENT '博客状态',
		  `createUser` VARCHAR(100) NULL COMMENT '创建人',
		  `createTime` DATETIME NULL,
		  `createAddr` VARCHAR(45) NULL,
		  `visitTimes` INT NULL DEFAULT 0 COMMENT '访问次数',
		  PRIMARY KEY (`id`))
		COMMENT = '博客信息表';

CREATE TABLE `blogzf`.`t_blog_comment` (
		  `id` VARCHAR(32) NOT NULL,
		  `nickname` VARCHAR(100) NULL COMMENT '昵称',
		  `comment` VARCHAR(2000) NULL COMMENT '评论内容',
		  `lastUpdateDate` TIMESTAMP NULL,
		  `blogId` VARCHAR(32) NULL COMMENT '关联的博客信息表id',
		  `commentId` VARCHAR(32) NULL COMMENT '引用的评论id',
		  PRIMARY KEY (`id`))
		COMMENT = '博客评论表';

CREATE TABLE `t_blog_comment` (
		  `id` varchar(32) NOT NULL,
		  `nickname` varchar(100) DEFAULT NULL COMMENT '昵称',
		  `comment` varchar(2000) DEFAULT NULL COMMENT '评论内容',
		  `lastUpdateDate` timestamp NULL DEFAULT NULL,
		  `blogId` varchar(32) DEFAULT NULL COMMENT '关联的博客信息表id',
		  `commentId` varchar(32) DEFAULT NULL COMMENT '引用的评论id',
		  `supportTimes` int(11) DEFAULT NULL COMMENT '支持次数',
		  `opposeTimes` int(11) DEFAULT NULL COMMENT '反对次数',
		  PRIMARY KEY (`id`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客评论表';

		CREATE TABLE `t_blog_info` (
		  `id` int(11) NOT NULL AUTO_INCREMENT,
		  `title` varchar(600) NOT NULL COMMENT '标题',
		  `content` longblob,
		  `blogType` varchar(20) NOT NULL COMMENT '博客分类',
		  `summary` varchar(800) DEFAULT NULL COMMENT '摘要',
		  `blogTag` varchar(200) DEFAULT NULL COMMENT '博客标签(Tag)',
		  `blogStatus` varchar(1) NOT NULL DEFAULT '0' COMMENT '博客状态',
		  `createUser` varchar(100) DEFAULT NULL COMMENT '创建人',
		  `createTime` datetime DEFAULT NULL,
		  `createAddr` varchar(45) DEFAULT NULL,
		  `visitTimes` int(11) DEFAULT '0' COMMENT '访问次数',
		  `lastUpdateUser` varchar(100) DEFAULT NULL,
		  `lastUpdateTime` datetime DEFAULT NULL,
		  `imagePath` varchar(200) DEFAULT NULL COMMENT '用于在首页显示文章列表时显示',
		  `comments` int(11) DEFAULT '0' COMMENT '评论次数',
		  PRIMARY KEY (`id`)
		) ENGINE=InnoDB AUTO_INCREMENT=100010 DEFAULT CHARSET=utf8 COMMENT='博客信息表';

		CREATE TABLE `t_system_config` (
		  `id` varchar(32) NOT NULL,
		  `name` varchar(200) DEFAULT NULL COMMENT '系统名称',
		  `visitTimes` int(11) DEFAULT NULL COMMENT '访问次数',
		  `lastUpdateDate` timestamp NULL DEFAULT NULL COMMENT '最后访问时间',
		  PRIMARY KEY (`id`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统表';

		CREATE TABLE `t_system_dictionary_tree` (
		  `id` varchar(32) NOT NULL,
		  `code` varchar(20) NOT NULL,
		  `treeId` varchar(20) NOT NULL COMMENT '1 1.1 1.2 ',
		  `name` varchar(200) NOT NULL,
		  `parent_id` varchar(32) NOT NULL,
		  `type_code` varchar(20) NOT NULL,
		  `remark` varchar(1000) DEFAULT NULL,
		  `isLeaf` varchar(1) NOT NULL COMMENT '指定是否为叶子节点，0：不是，1：是',
		  `orderNo` int(11) DEFAULT '0',
		  `levelNumber` int(11) DEFAULT '0',
		  PRIMARY KEY (`id`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='树形字典信息表';

		CREATE TABLE `t_system_dictionary_type` (
		  `id` varchar(32) NOT NULL,
		  `code` varchar(6) NOT NULL,
		  `name` varchar(200) NOT NULL,
		  `remark` varchar(1000) DEFAULT NULL,
		  PRIMARY KEY (`id`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='树形字典类型表';

		CREATE TABLE `t_system_visit_times` (
		  `id` varchar(32) NOT NULL,
		  `visitIp` varchar(45) DEFAULT NULL COMMENT '访问的IP地址',
		  `visitTime` timestamp NULL DEFAULT NULL COMMENT '访问的时间',
		  `visitUrl` varchar(100) DEFAULT NULL COMMENT '访问的url',
		  `visitTitleId` varchar(100) DEFAULT NULL COMMENT '如果是阅读文章，记录文章的id',
		  PRIMARY KEY (`id`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统访问次数详细记录表';
