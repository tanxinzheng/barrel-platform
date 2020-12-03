ALTER TABLE `barrel`.`table_name`
ADD COLUMN `created_by` varchar(255) NOT NULL COMMENT '创建人',
ADD COLUMN `created_time` datetime(0) NOT NULL COMMENT '创建时间' AFTER `created_by`,
ADD COLUMN `updated_by` varchar(255) NULL COMMENT '更新人' AFTER `created_time`,
ADD COLUMN `updated_time` datetime(0) NULL COMMENT '更新时间' AFTER `updated_by`;