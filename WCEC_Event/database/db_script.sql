drop schema wcec;
create schema wcec;
use wcec;


  create TABLE `wcec`.`access_level` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `level` INT NOT NULL,
  `description` VARCHAR(20) unique NOT NULL,
  PRIMARY KEY (`id`),
  `last_updt_ts` DATETIME NOT NULL default CURRENT_TIMESTAMP 
);

 create TABLE `wcec`.`user_tbl` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_email_id` INT unique NOT NULL,
  `password` VARCHAR(40) NOT NULL,
  `access_level_id` INT, 
   FOREIGN KEY(`access_level_id`) REFERENCES `wcec`.`access_level`(`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,  
  PRIMARY KEY (`id`),
  `person_id` INT NOT NULL,
  `last_updt_ts` DATETIME NOT NULL default CURRENT_TIMESTAMP
); 
 
  
  
  create TABLE `wcec`.`user_login` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login_time` DATETIME NOT NULL,
  `logout_time` DATETIME NOT NULL,
  `session_id` VARCHAR(80) unique NOT NULL,
  `user_id` INT,
  FOREIGN KEY(`user_id`) REFERENCES `wcec`.`user_tbl`(`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE, 
  PRIMARY KEY (`id`),
  `last_updt_ts` DATETIME NOT NULL default CURRENT_TIMESTAMP 
);

create table `wcec`.`phone_type_tbl` (
`id` INT NOT NULL AUTO_INCREMENT,
`code` VARCHAR(10) unique NOT NULL  , 
PRIMARY KEY (`id`),
`last_updt_ts` DATETIME NOT NULL default CURRENT_TIMESTAMP); 


create table `wcec`.phone_tbl (
`id` INT NOT NULL AUTO_INCREMENT,
`phone_type_id` INT NOT NULL ,
`person_id` INT NOT NULL ,
`area_code` VARCHAR(3) NOT NULL,
`phone_number` VARCHAR(7) NOT NULL,
PRIMARY KEY (`id`),
FOREIGN KEY(`phone_type_id`) REFERENCES `wcec`.`phone_type_tbl`(`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
`last_updt_ts` DATETIME NOT NULL default CURRENT_TIMESTAMP); 


create table `wcec`.email_tbl (
`id` INT NOT NULL AUTO_INCREMENT,
`person_id` INT NOT NULL,
`email` VARCHAR(40) NOT NULL,
PRIMARY KEY (`id`),
`last_updt_ts` DATETIME NOT NULL default CURRENT_TIMESTAMP 
);

create table `wcec`.address_tbl (
`id` INT NOT NULL AUTO_INCREMENT,
`line1` VARCHAR(80) NOT NULL,
`line2` VARCHAR(80),
`city`  VARCHAR(40) NOT NULL,
`state` VARCHAR(2) NOT NULL,
`zip_code` VARCHAR(10) NOT NULL,
PRIMARY KEY (`id`),
`last_updt_ts` DATETIME NOT NULL default CURRENT_TIMESTAMP 
);


CREATE TABLE `wcec`.`event_type_tbl` (
  `id` INT NOT NULL AUTO_INCREMENT, 
  `description` VARCHAR(50) unique NOT NULL,
  `last_updt_ts` DATETIME NOT NULL default CURRENT_TIMESTAMP,
  primary key(`id`)
);
  


CREATE TABLE `wcec`.`event_host_tbl` (
  `id` INT NOT NULL AUTO_INCREMENT, 
  `host_name` VARCHAR(60) NOT NULL, 
  `person_id` INT NOT NULL,  
  `last_updt_ts` DATETIME NOT NULL default CURRENT_TIMESTAMP,
  `last_updt_user_id` INT NOT NULL,
  PRIMARY KEY (`id`)
);
  

CREATE TABLE `wcec`.`event_tbl` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `event_type_id` INT NOT NULL,
   FOREIGN KEY(`event_type_id`) REFERENCES `wcec`.`event_type_tbl`(`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  `start_dt` DATE NOT NULL,
  `end_dt` DATE NOT NULL,
  `early_registration_dt` DATETIME NOT NULL, 
  `registration_fee` DECIMAL(7,2) Not null,
  `check_in_dt` DATETIME NOT NULL, 
  `special_note` VARCHAR(200),
  `event_host_id` INT NOT NULL,
  `active_flag` bool default true,
   FOREIGN KEY(`event_host_id`) REFERENCES `wcec`.`event_host_tbl`(`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  PRIMARY KEY (`id`),
  `last_updt_ts` DATETIME NOT NULL default CURRENT_TIMESTAMP,
`last_updt_user_id` INT NOT NULL 
);
 
 
/* domain (reference) table */ 
CREATE TABLE `wcec`.`church_tbl` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) unique NOT NULL,
  PRIMARY KEY (`id`),
  `last_updt_ts` DATETIME NOT NULL default CURRENT_TIMESTAMP 
);
 
 
CREATE TABLE `wcec`.`building_tbl` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) unique NOT NULL,
  PRIMARY KEY (`id`),
  `last_updt_ts` DATETIME NOT NULL default CURRENT_TIMESTAMP 
);
 
 
CREATE TABLE `wcec`.`bed_type_tbl` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `bed_type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  `last_updt_ts` DATETIME NOT NULL default CURRENT_TIMESTAMP 
);
 
 
CREATE TABLE `wcec`.`meal_tbl` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `meal_description`  VARCHAR(45) unique NOT NULL,
  `meal_price` DECIMAL(7,2) NOT NULL,
  `age_limit` INT NOT NULL, 
  PRIMARY KEY (`id`),
  `event_id` INT NOT NULL,
  FOREIGN KEY(`event_id`) REFERENCES `wcec`.`event_tbl`(`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE, 
  `last_updt_ts` DATETIME NOT NULL default CURRENT_TIMESTAMP 
);
 
/* room_price is set only for part-time attendees */
CREATE TABLE `wcec`.`room_tbl` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `building_id` INT(11),
  `room_price` DECIMAL(7,2) NOT NULL,
  `handicap_access_ind` bool default false,
  FOREIGN KEY(`building_id`) REFERENCES `wcec`.`building_tbl`(`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  `room_no` VARCHAR(6) NOT NULL,
  PRIMARY KEY (`id`),
  `last_updt_ts` DATETIME NOT NULL default CURRENT_TIMESTAMP  
);
 
CREATE TABLE `wcec`.`group_type_tbl` (
  `id` INT NOT NULL AUTO_INCREMENT,  
  `description` VARCHAR(40) unique not null,  
  PRIMARY KEY (`id`),
  `last_updt_ts` DATETIME NOT NULL default CURRENT_TIMESTAMP 
);
 
CREATE TABLE `wcec`.`group_tbl` (
  `id` INT NOT NULL AUTO_INCREMENT,  
  `group_nm` VARCHAR(40) unique not null,
  `person_id` INT,
  `parent_id` INT,
  `group_type` INT,
  `cancel_flag` bool not null default false,
  FOREIGN KEY(`group_type`) REFERENCES `wcec`.`group_type_tbl`(`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,   
  PRIMARY KEY (`id`),
  `last_updt_ts` DATETIME NOT NULL default CURRENT_TIMESTAMP 
);
  
CREATE TABLE `wcec`.`person_tbl` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `last_nm` VARCHAR(45) NOT NULL,
  `first_nm` VARCHAR(45) NOT NULL,
  `birth_dt` DATE NOT NULL,
  `gender` VARCHAR(6) NOT NULL,
  `chinese_nm` VARCHAR(60) NULL, 
  `primary_group_id` INT,
  `is_adult_flag` bool not null default true,
  `is_greater_than_five` bool not null default false,
  `is_less_than_five` bool not null default false,
  `is_five_to_twelve` bool not null default false,
  `is_thirteen_to_eighteen` bool not null default false,
  
  FOREIGN KEY(`primary_group_id`) REFERENCES `wcec`.`group_tbl`(`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE, 
   `secondary_group_id` INT,
  FOREIGN KEY(`secondary_group_id`) REFERENCES `wcec`.`group_tbl`(`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE, 
  `church_id` INT,
  FOREIGN KEY(`church_id`) REFERENCES `wcec`.`church_tbl`(`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
 `address_id` INT NOT NULL,
  FOREIGN KEY(`address_id`) REFERENCES `wcec`.`address_tbl`(`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE, 
  PRIMARY KEY (`id`),
   `last_updt_ts` DATETIME NOT NULL default CURRENT_TIMESTAMP 
  
);
 
ALTER TABLE `wcec`.`group_tbl` ADD CONSTRAINT fk_person_id FOREIGN KEY (person_id) REFERENCES `wcec`.`group_tbl`(`id`)
ON DELETE CASCADE
  ON UPDATE CASCADE;


ALTER TABLE `wcec`.`group_tbl` ADD CONSTRAINT fk_parent_person_id FOREIGN KEY (parent_id) REFERENCES `wcec`.`group_tbl`(`id`)
ON DELETE CASCADE
  ON UPDATE CASCADE;
 
 
 
CREATE TABLE `wcec`.`lodging_assignment_tbl` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `building_id` INT,
  FOREIGN KEY(`building_id`) REFERENCES `wcec`.`building_tbl`(`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  `bed_id` INT,
  FOREIGN KEY(`bed_id`) REFERENCES `wcec`.`bed_type_tbl`(`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  `room_id` INT,
  FOREIGN KEY(`room_id`) REFERENCES `wcec`.`room_tbl`(`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  `person_id` INT,
  FOREIGN KEY(`person_id`) REFERENCES `wcec`.`person_tbl`(`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  PRIMARY KEY (`id`),
  `last_updt_ts` DATETIME NOT NULL default CURRENT_TIMESTAMP 
);
 
 


CREATE TABLE `wcec`.`payment_tbl` (
 `id` INT NOT NULL AUTO_INCREMENT, 
  `paid_in_full` bool default false,
  `amount_paid` decimal(7,2) DEFAULT 0.0,
  PRIMARY KEY (`id`),
  `payment_type_tx` VARCHAR(20) NOT NULL,
  `free_offering` decimal(7,2) default 0.0,
  `last_updt_ts` DATETIME NOT NULL default CURRENT_TIMESTAMP 
);
   
  /* attending_date = null means full time */  
  
CREATE TABLE `wcec`.`registration_tbl` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `event_id` INT,
  FOREIGN KEY(`event_id`) REFERENCES `wcec`.`event_tbl`(`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE, 
  
  `registration_date` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `discount_percentage` DECIMAL(7,2),
  `part_time_ind` bool not null default false,
  `attending_date` DATE,
  `lodging_assignment_id` INT,
  FOREIGN KEY(`lodging_assignment_id`) REFERENCES `wcec`.`lodging_assignment_tbl`(`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  `meal_plan_id` INT,
   `person_id` INT not null,  
   FOREIGN KEY(`person_id`) REFERENCES `wcec`.`person_tbl`(`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE, 
   PRIMARY KEY (`id`), 
   unique (`event_id`, `person_id`, `attending_date`),
   `payment_id` INT ,
    FOREIGN KEY(`payment_id`) REFERENCES `wcec`.`payment_tbl`(`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  `group_id` INT not null,
   FOREIGN KEY(`group_id`) REFERENCES `wcec`.`group_tbl`(`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,  
  `comment` VARCHAR(2048) ,
  
  `cancel_flag` bool not null default false,
  `last_updt_ts` DATETIME NOT NULL default CURRENT_TIMESTAMP 
);


CREATE TABLE `wcec`.`attending_tbl` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `registration_id` INT not null,
    FOREIGN KEY(`registration_id`) REFERENCES `wcec`.`registration_tbl`(`id`) on delete cascade on update cascade,
  `attending_date` DATE Not null,
  PRIMARY KEY (`id`)
);


  
CREATE TABLE `wcec`.`meal_plan_tbl` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `registration_id` INT,
  FOREIGN KEY(`registration_id`) REFERENCES `wcec`.`registration_tbl`(`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,  
  `meal_id` INT,
  FOREIGN KEY(`meal_id`) REFERENCES `wcec`.`meal_tbl`(`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE, 
  `last_updt_ts` DATETIME NOT NULL default CURRENT_TIMESTAMP,
  
  PRIMARY KEY (`id`)
);


ALTER TABLE `wcec`.`registration_tbl` ADD CONSTRAINT fk_meal_plan_id FOREIGN KEY (meal_plan_id) REFERENCES `wcec`.`meal_plan_tbl`(`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE; 

ALTER TABLE `wcec`.`user_tbl` ADD CONSTRAINT fk_user_person_id FOREIGN KEY (person_id) REFERENCES `wcec`.`person_tbl`(`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE; 
  
  
ALTER TABLE `phone_tbl` ADD CONSTRAINT fk_person_id_1 FOREIGN KEY(person_id) REFERENCES `wcec`.`person_tbl`(`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  
ALTER TABLE `email_tbl` ADD CONSTRAINT fk_person_id_2 FOREIGN KEY(person_id) REFERENCES `wcec`.`person_tbl`(`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE `event_host_tbl` ADD CONSTRAINT fk_person_id_3 FOREIGN KEY(person_id) REFERENCES `wcec`.`person_tbl`(`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  
ALTER TABLE `room_tbl` 
ADD CONSTRAINT id_building_id UNIQUE (`id`,  `building_id`);


INSERT INTO `wcec`.`access_level`  (level, description)  VALUES (100,  'user');
INSERT INTO `wcec`.`access_level`  (level, description)  VALUES (200,  'admin');
INSERT INTO `wcec`.`access_level`  (level, description)  VALUES (300,  'developer');



select * from `wcec`.`access_level`;

insert into wcec.event_type_tbl (description) values ('church retreat');





insert into wcec.building_tbl (name) values ('Maranatha Retreat Center');
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 101);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 102);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 103);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 104);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 105);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 106);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 107);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 108);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 109);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 110);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 111);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 112);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 113);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 114);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 115);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 151);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 152);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 153);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 154);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 155);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 156);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 157);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 158);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 159);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 160);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 202);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 203);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 204);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 205);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 206);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 207);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 208);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 209);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 210);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 211);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 212);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 213);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 214);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 215);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 216);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 217);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 218);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 219);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 220);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 251);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 252);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 253);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 254);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 255);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 256);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 257);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 258);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 259);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 260);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 261);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 262);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 263);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 264);


insert into wcec.building_tbl (name) values ('Dogwood Motel');


#Dogwood Motel
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (2, 0, false, 1);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (2, 0, false, 2);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (2, 0, false, 3);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (2, 0, false, 4);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (2, 0, false, 5);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (2, 0, false, 6);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (2, 0, false, 7);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (2, 0, false, 8);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (2, 0, false, 9);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (2, 0, false, 10);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (2, 0, false, 11);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (2, 0, false, 12);



insert into wcec.building_tbl (name) values ('Whip-Poor-Will Lodge');
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (3, 0, false, 1);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (3, 0, false, 2);

insert into wcec.building_tbl (name) values ('Partridge Cottage');
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (4, 0, false, 1);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (4, 0, false, 2);


insert into wcec.building_tbl (name) values ('House Wren Cottage');
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (5, 0, false, 1);

insert into wcec.building_tbl (name) values ('Rock Wren Cottage');
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (6, 0, false, 1);


insert into wcec.building_tbl (name) values ('Bunk Cabin');
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (7, 0, false, 1);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (7, 0, false, 2);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (7, 0, false, 3);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (7, 0, false, 4);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (7, 0, false, 5);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (7, 0, false, 6);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (7, 0, false, 7);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (7, 0, false, 8);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (7, 0, false, 9);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (7, 0, false, 10);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (7, 0, false, 11);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (7, 0, false, 12);


select * from wcec.room_tbl;

insert into wcec.bed_type_tbl (bed_type) values ('Single');
insert into wcec.bed_type_tbl (bed_type) values ('Double');
insert into wcec.bed_type_tbl (bed_type) values ('Bunk');



insert into wcec.church_tbl (name) values ('WCEC');
insert into wcec.church_tbl (name) values ('WCCEC');
insert into wcec.church_tbl (name) values ('New Castle');





insert into wcec.group_type_tbl(description) values ('Cell Group');
insert into wcec.group_type_tbl(description) values ('UD college student');
insert into wcec.group_type_tbl(description) values ('UD graduate student');
insert into wcec.group_type_tbl(description) values ('UD young professional');
insert into wcec.group_type_tbl(description) values ('UD visiting scholar');
insert into wcec.group_type_tbl(description) values ('Cell group FRIDAY FELLOWSHIP');
insert into wcec.group_type_tbl(description) values ('Cell group ENGLISH MINISTRY');
insert into wcec.group_type_tbl(description) values ('Cell group CROSS CULTURE');
insert into wcec.group_type_tbl(description) values ('Cell group WCCEC');
insert into wcec.group_type_tbl(description) values ('Cell group NCCEC');
insert into wcec.group_type_tbl(description) values ('Cell group NEW FRIENDS');
insert into wcec.group_type_tbl(description) values ('Cell group DOVER');
insert into wcec.group_type_tbl(description) values ('Cell group OTHER');
insert into wcec.group_type_tbl(description) values ('Registration group');


insert into wcec.group_tbl(group_nm, group_type) values ('仁愛小組', 1);
insert into wcec.group_tbl(group_nm, group_type) values ('喜樂小組', 1);
insert into wcec.group_tbl(group_nm, group_type) values ('和平小組', 1);
insert into wcec.group_tbl(group_nm, group_type) values ('忍耐小組', 1);
insert into wcec.group_tbl(group_nm, group_type) values ('豐盛小組', 1);
insert into wcec.group_tbl(group_nm, group_type) values ('恩友團契', 1);
insert into wcec.group_tbl(group_nm, group_type) values ('恩慈小組', 1);
insert into wcec.group_tbl(group_nm, group_type) values ('良善小組', 1);
insert into wcec.group_tbl(group_nm, group_type) values ('信實小組', 1);
insert into wcec.group_tbl(group_nm, group_type) values ('溫柔小組', 1);
insert into wcec.group_tbl(group_nm, group_type) values ('節制小組', 1);
insert into wcec.group_tbl(group_nm, group_type) values ('粵語團契', 1);
insert into wcec.group_tbl(group_nm, group_type) values ('松柏團契', 1);
insert into wcec.group_tbl(group_nm, group_type) values ('徳大校园团契', 1);
insert into wcec.group_tbl(group_nm, group_type) values ('退修会报名群组', 14);





