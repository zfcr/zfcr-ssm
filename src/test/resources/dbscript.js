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
		  PRIMARY KEY (`id`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='树形字典信息表';
