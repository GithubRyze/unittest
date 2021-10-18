DROP TABLE IF EXISTS MESSAGE_ITINERARY_INFO;
CREATE TABLE MESSAGE_ITINERARY_INFO (
 id varchar(64) unsigned NOT NULL  COMMENT '主键',
 message_origin_id varchar(64)  NOT NULL DEFAULT '' COMMENT '消息源主键',
 message_target_id varchar(64)  NOT NULL DEFAULT '' COMMENT '消息目标源主键',
 message_status tinyint(1) NOT NULL DEFAULT '0' COMMENT '消息状态，0-成功，1-失败',
 message_content TEXT NOT NULL DEFAULT '' COMMENT '消息内容',
 created_at DATETIME  NOT NULL DEFAULT sysdate COMMENT '创建时间',
 created_by varchar(32) NOT NULL DEFAULT '0' COMMENT '创建人',
 updated_at DATETIME NOT NULL DEFAULT sysdate COMMENT '更新时间',
 updated_by varchar(32) NOT NULL DEFAULT '0' COMMENT '更新人',
 version int(8) NOT NULL DEFAULT 0 COMMENT '版本号',
 PRIMARY KEY (`id`)
)