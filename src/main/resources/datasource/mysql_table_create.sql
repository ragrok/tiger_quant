CREATE DATABASE tiger_quant;
USE tiger_quant;

CREATE TABLE `bar` (
	`id` INT(10) NOT NULL AUTO_INCREMENT,
    `symbol` varchar(20) NOT NULL DEFAULT 0,
    `duration` bigint NOT NULL DEFAULT 0,
    `open`double(15,4) NOT NULL DEFAULT 0,
    `high`double(15,4) NOT NULL DEFAULT 0,
    `low`double(15,4) NOT NULL DEFAULT 0,
    `close`double(15,4) NOT NULL DEFAULT 0,
    `volume` int NOT NULL DEFAULT 0,
    `amount` double(15,4) NOT NULL DEFAULT 0,
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	  PRIMARY KEY (`id`),
    KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;


CREATE TABLE `t_symbol_info` (
  `f_id` int(11) NOT NULL AUTO_INCREMENT,
  `f_contract_id` int(11) NOT NULL,
  `f_symbol` varchar(20) NOT NULL DEFAULT '',
  `f_currency` varchar(5) NOT NULL DEFAULT '',
  `f_exchange` varchar(10) NOT NULL DEFAULT '',
  `f_identifier` varchar(10) NOT NULL DEFAULT '',
  `f_local_symbol` varchar(10) NOT NULL DEFAULT '',
  `f_long_initial_margin` double(4,2) NOT NULL DEFAULT '0.00',
  `f_long_maintenance_margin` double(4,2) NOT NULL DEFAULT '0.00',
  `f_market` varchar(10) NOT NULL DEFAULT '',
  `f_min_tick` double(4,2) NOT NULL DEFAULT '0.00',
  `f_name` varchar(255) NOT NULL DEFAULT '',
  `f_primary_exchange` varchar(10) NOT NULL DEFAULT '',
  `f_sec_type` varchar(10) NOT NULL DEFAULT '',
  `f_short_fee_rate` double(4,2) NOT NULL DEFAULT '0.00',
  `f_short_margin` double(4,2) NOT NULL DEFAULT '0.00',
  `f_shortable` int(10) NOT NULL DEFAULT '0',
  `f_tiger_status` tinyint(1) NOT NULL,
  `f_continuous` tinyint(1) unsigned zerofill NOT NULL,
  `f_trade` tinyint(1) unsigned zerofill NOT NULL,
  `f_trading_class` varchar(10) NOT NULL DEFAULT '',
  `f_expiry` varchar(255) NOT NULL DEFAULT '',
  `f_contract_month` varchar(255) NOT NULL DEFAULT '',
  `f_strike` double(4,2) NOT NULL DEFAULT '0.00',
  `f_right` varchar(255) NOT NULL DEFAULT '',
  `f_multiplier` double(4,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`f_id`),
  UNIQUE KEY `idx_contract_id` (`f_contract_id`) USING BTREE,
  KEY `idx_symbol` (`f_symbol`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12360 DEFAULT CHARSET=utf8;
