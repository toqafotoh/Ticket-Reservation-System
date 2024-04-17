CREATE DATABASE TicketReservation;
USE TicketReservation;
drop database TicketReservation;
CREATE TABLE `Account` (
    `name` VARCHAR(50),
    nationality VARCHAR(50),
    national_id char(25) PRIMARY KEY NOT NULL,
    age INT,
    Gender ENUM('male', 'female') NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    hashed_password CHAR(64),
    `Role` ENUM('admin', 'passenger', 'flatOwner')
);

CREATE TABLE Passenger (
    passenger_id char(25) primary key,
    FOREIGN KEY (passenger_id) REFERENCES Account (national_id),
    loyaltyPoints INT default(0)
);

CREATE TABLE FlatOwner (
    flatOwnerId char(25) primary key,
    FOREIGN KEY (flatOwnerId) REFERENCES Passenger (passenger_id),
    phone_num DOUBLE
);

create table Flats(
	flat_id int primary key auto_increment,
	address varchar(100),
    flat_description varchar(150),
    country_name varchar(50),
    capacity int,
    price double,
    flatOwnerId char(25),
    foreign key (flatOwnerId) REFERENCES flatOwner (flatOwnerId)
);

create table Flight(
	flight_code varchar(10) primary key,
    class ENUM ('A','B','C','D'),
    origin varchar (20),
	destination varchar (50),
	flight_time date,
    available_seats int,
    duration int,
    airline varchar(50),
    price double
);

create table Entertainment(
		entertainment_tour_id int primary key auto_increment,
		`description` varchar (50),
		`time` date,
        price double,
        `destination` varchar (50)
);

create table `Logs`(
	setDate Date,
    adminId char(25),
    flight_code varchar(10),
    foreign key (adminId) references Account(national_id),
    foreign key (flight_code) references flight(flight_code)
);

create table Ticket(
    paymentId int ,
    ticket_id int primary key auto_increment,
    price double
);

create table EntertainmentTicket(
	entertainment_tour_id int ,
    foreign key (entertainment_tour_id) references entertainment(entertainment_tour_id),
    entertainment_ticket int,
	foreign key (entertainment_ticket) references Ticket(ticket_id)
);

create table FlightTicket(
	flight_ticket_id int,
    foreign key (flight_ticket_id) references Ticket(ticket_id),
	QRCode varchar(50),
    Seat varchar(50),
	flight_code varchar(10),
    foreign key (flight_code) references Flight(flight_code)
);

create table TicketOwner(
    passenger_ID char(25),
    foreign key (passenger_ID) references Passenger(passenger_id),
	ticket_id int,
    foreign key (ticket_id) references Ticket(ticket_id),
    purchase_date date
);

create table Feedback(
	feedback_id int primary key auto_increment,
    content varchar(50),
    feedback_date date,
	rate int check (rate >= 0 AND rate <= 5) default 0,
    passenger_id char(25),
    foreign key (passenger_ID) references Passenger(passenger_id)
);

CREATE TABLE FlatOwnerFeedback (
    feedback_id INT PRIMARY KEY AUTO_INCREMENT,
    flatOwnerId CHAR(25),
    FOREIGN KEY (flatOwnerId) REFERENCES flatOwner(flatOwnerId),
    passenger_id CHAR(25),
    FOREIGN KEY (passenger_id) REFERENCES Passenger(passenger_id),
    feedback_text TEXT,
    feedback_date DATE,
    rate int check (rate >= 0 AND rate <= 5) default 0
);


-- create table f(
 -- 	id int primary key,
 -- 	flatOwner_id  char(25),
 -- 	foreign key (flatOwner_id) references Flatowner(flatOwnerId),
 -- 	rating int 
 -- );
 
-- CREATE TABLE Feedbacks(
 -- 	feedback_id int primary key auto_increment,
 -- 	giver char(25),
 -- 	foreign key (giver) references Passenger(passenger_id),
 --  	receiver int,
 --  	foreign key (receiver) references f(id)
 -- );