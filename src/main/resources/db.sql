create database if not exists db;
use db;
create table if not exists users(
	id int NOT NULL auto_increment,
	username varchar(50) not null,
	password blob not null,
	primary key(id)
)ENGINE=InnoDB;

create table if not exists images(
	id int not null auto_increment,
    image longblob not null,
    primary key(id)
)ENGINE=InnoDB;

create table if not exists families(
	id int not null auto_increment,
	description varchar(50) not null,
	firstImage int not null,
	primary key(id),
    FOREIGN KEY(firstImage) REFERENCES images(id)
)ENGINE=InnoDB;

create table if not exists photos(
	id int not null auto_increment,
    uuid varchar(50) not null,
	description text not null,
	title varchar(100) not null,
	family_id int,
    _image int not null,
	primary key(id),
	FOREIGN KEY (family_id) REFERENCES families(id),
    FOREIGN KEY (_image) REFERENCES images(id)
)ENGINE=InnoDB;

create table if not exists coverPage(
    id int not null auto_increment,
    uuid varchar(50) not null,
    logo int,
    background int,
    title varchar(100),
    welcome text,
    primary key(id),
    FOREIGN KEY(logo) references images(id),
    foreign key(background) references images(id)
)ENGINE=InnoDB;



create table if not exists info(
	id int not null auto_increment,
    image1 int not null,
    image2 int not null,
    uuid varchar(50) not null,
    name varchar(50),
    mail varchar(100), 
    city varchar(50),
    phone varchar(50),
    description text,
    footer text,
    primary key(id),
    FOREIGN KEY(image1) REFERENCES images(id),
    FOREIGN KEY(image2) REFERENCES images(id)
)ENGINE=InnoDB;

create table if not exists skills(
	id int not null auto_increment,
    isSkill tinyint,
    percent integer,
    description varchar(50),
    info_id int,
    primary key(id),
    FOREIGN KEY (info_id) REFERENCES info (id)
)ENGINE=InnoDB;

create table if not exists education(
	id int not null auto_increment,
    _from datetime,
    _to datetime,
    place varchar(50),
    description text,
    info_id int,
    primary key(id),
    FOREIGN KEY (info_id) REFERENCES info (id)
)ENGINE=InnoDB;

create table if not exists experience(
	id int not null auto_increment,
    _from datetime,
    _to datetime,
    puesto varchar(50),
    empresa varchar(50),
    description text,
    info_id int,
    primary key(id),
    FOREIGN KEY (info_id) REFERENCES info(id)
)ENGINE=InnoDB;

create table if not exists geolocation(
	sesionId varchar(50) not null,
    locationCountry varchar(50) not null,
    primary key(sesionId)
)ENGINE=InnoDB;
