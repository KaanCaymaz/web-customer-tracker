create user 'springstudent'@'localhost' identified by 'springstudent';

GRANT ALL PRIVILEGES ON * . * TO 'springstudent'@'localhost';

create DATABASE	IF NOT EXISTS web_customer_tracker;

use web_customer_tracker;


drop table if exists customer;

create table customer(
	id int(11) not null AUTO_INCREMENT,
    first_name varchar(45) default null,
    last_name varchar(45) default null,
    email varchar(45) default null,
    
    primary key (id)
    )ENGINE=InnoDB AUTO_INCREMENT=6 default CHARSET=latin1;
    
INSERT INTO customer VALUES
	(1,'David','Adams','david@luv2code.com'),
	(2,'John','Doe','john@luv2code.com'),
	(3,'Ajay','Rao','ajay@luv2code.com'),
	(4,'Mary','Public','mary@luv2code.com'),
	(5,'Maxwell','Dixon','max@luv2code.com');
    

    