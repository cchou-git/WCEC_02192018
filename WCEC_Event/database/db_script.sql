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
`state` VARCHAR(2),
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
  `payer_id` INT NOT NULL,
  FOREIGN KEY(`payer_id`) REFERENCES `wcec`.`person_tbl`(`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
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
  `attending_date` DATE NOT NULL,
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
   `payment_id` INT NOT NULL,
    FOREIGN KEY(`payment_id`) REFERENCES `wcec`.`payment_tbl`(`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  `group_id` INT not null,
   FOREIGN KEY(`group_id`) REFERENCES `wcec`.`group_tbl`(`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,  
  `last_updt_ts` DATETIME NOT NULL default CURRENT_TIMESTAMP 
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
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 1);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 2);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 3);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 4);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 5);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 6);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 7);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 8);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 9);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 10);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 11);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 12);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 13);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 14);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 15);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 16);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 17);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 18);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 19);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 20);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 21);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 22);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 23);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 24);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 25);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 26);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 27);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 28);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 29);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 30);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 31);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 32);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 33);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 34);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 35);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 36);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 37);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 38);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 39);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 40);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 41);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 42);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 43);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 44);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 45);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 46);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 47);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 48);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 49);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 50);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 51);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 52);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 53);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 54);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 55);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 56);
insert into wcec.room_tbl (building_id, room_price, handicap_access_ind, room_no) values (1, 0, false, 57);


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



insert into wcec.group_type_tbl(description) values ('Family');
insert into wcec.group_type_tbl(description) values ('UD college student');
insert into wcec.group_type_tbl(description) values ('UD graduate student');
insert into wcec.group_type_tbl(description) values ('UD young professional');
insert into wcec.group_type_tbl(description) values ('UD visiting scholar'); 
insert into wcec.group_type_tbl(description) values ('Cell group LOVE');
insert into wcec.group_type_tbl(description) values ('Cell group JOY');
insert into wcec.group_type_tbl(description) values ('Cell group PEACE');
insert into wcec.group_type_tbl(description) values ('Cell group PATIENCE');
insert into wcec.group_type_tbl(description) values ('Cell group KINDNESS');
insert into wcec.group_type_tbl(description) values ('Cell group GOODNESS');
insert into wcec.group_type_tbl(description) values ('Cell group FAITHFULNESS');
insert into wcec.group_type_tbl(description) values ('Cell group GENTLENESS');
insert into wcec.group_type_tbl(description) values ('Cell group SELF-CONTROL');
insert into wcec.group_type_tbl(description) values ('Cell group CANTONESE');
insert into wcec.group_type_tbl(description) values ('Cell group EVERGREEN');
insert into wcec.group_type_tbl(description) values ('Cell group UD FELLOWSHIP');
insert into wcec.group_type_tbl(description) values ('Cell group FRIDAY FELLOWSHIP');
insert into wcec.group_type_tbl(description) values ('Cell group KOINONIA');
insert into wcec.group_type_tbl(description) values ('Cell group ENGLISH MINISTRY');
insert into wcec.group_type_tbl(description) values ('Cell group CROSS CULTURE');
insert into wcec.group_type_tbl(description) values ('Cell group WCCEC');
insert into wcec.group_type_tbl(description) values ('Cell group NCCEC');
insert into wcec.group_type_tbl(description) values ('Cell group NEW FRIENDS');
insert into wcec.group_type_tbl(description) values ('Cell group DOVER');
insert into wcec.group_type_tbl(description) values ('Cell group OTHER');



 




