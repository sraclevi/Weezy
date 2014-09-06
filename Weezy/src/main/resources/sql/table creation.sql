CREATE TABLE cashflow (
`key` VARCHAR(36)  NOT NULL,
name VARCHAR(50),
amount INT(10),
frequency VARCHAR(50),
`from` DATETIME,
`to` DATETIME,
ref_type  VARCHAR(50),
PRIMARY KEY (`key`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB; 