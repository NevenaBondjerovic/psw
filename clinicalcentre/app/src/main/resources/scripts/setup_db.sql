CREATE DATABASE clinical_centre;

CREATE USER 'clinical_centre_user'@'localhost' IDENTIFIED BY 'user';

GRANT ALL ON clinical_centre.* to 'clinical_centre_user'@'localhost';


DROP TABLE IF EXISTS clinical_centre.users;
DROP TABLE IF EXISTS clinical_centre.registration_requests;
DROP TABLE IF EXISTS clinical_centre.clinics;

CREATE TABLE clinical_centre.users (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  username VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(50) NOT NULL,
  name VARCHAR(100) NOT NULL,
  surname VARCHAR(100) NOT NULL,
  address VARCHAR(100) NOT NULL,
  city VARCHAR(50) NOT NULL,
  state VARCHAR(50) NOT NULL,
  phone_number VARCHAR(50) NOT NULL,
  insurance_number VARCHAR(50) NOT NULL,
  activated BOOLEAN NOT NULL DEFAULT FALSE
);

INSERT INTO clinical_centre.users (id, username, password, name, surname, address, city, state, phone_number, insurance_number, activated) VALUES
  (1, 'admin@admin.com', 'admin', 'Pera', 'Peric', 'Adresa 1', 'Novi Sad', 'Srbija', '1234567', '987654321', TRUE);
INSERT INTO clinical_centre.users (id, username, password, name, surname, address, city, state, phone_number, insurance_number, activated) VALUES
  (2, 'user1@email.com', 'user2', 'Zika', 'Peric', 'Adresa 2', 'Novi Sad', 'Srbija', '23456789', '876543210', FALSE);
INSERT INTO clinical_centre.users (id, username, password, name, surname, address, city, state, phone_number, insurance_number, activated) VALUES
  (3, 'user3@email.com', 'user3', 'Mika', 'Peric', 'Adresa 3', 'Novi Sad', 'Srbija', '23456780', '876543219', FALSE);
INSERT INTO clinical_centre.users (id, username, password, name, surname, address, city, state, phone_number, insurance_number, activated) VALUES
  (4, 'user4@email.com', 'user4', 'Mika', 'Peric', 'Adresa 3', 'Novi Sad', 'Srbija', '23456780', '876543219', FALSE);
INSERT INTO clinical_centre.users (id, username, password, name, surname, address, city, state, phone_number, insurance_number, activated) VALUES
  (5, 'user5@email.com', 'user5', 'Mika', 'Peric', 'Adresa 4', 'Novi Sad', 'Srbija', '23456780', '876543219', FALSE);
INSERT INTO clinical_centre.users (id, username, password, name, surname, address, city, state, phone_number, insurance_number, activated) VALUES
  (6, 'user6@email.com', 'user6', 'Mika', 'Peric', 'Adresa 5', 'Novi Sad', 'Srbija', '23456780', '876543219', FALSE);

CREATE TABLE clinical_centre.registration_requests (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  user_id INT NOT NULL,
  processed BOOLEAN NOT NULL DEFAULT FALSE,
  approved BOOLEAN NOT NULL DEFAULT FALSE,
  decline_reason VARCHAR(200) DEFAULT NULL,

  FOREIGN KEY (user_id) REFERENCES clinical_centre.users(id)
);

INSERT INTO clinical_centre.registration_requests (id, user_id, processed, approved, decline_reason) VALUES
  (1, 1, TRUE, TRUE, null);
INSERT INTO clinical_centre.registration_requests (id, user_id, processed, approved, decline_reason) VALUES
  (2, 2, TRUE, TRUE, null);
INSERT INTO clinical_centre.registration_requests (id, user_id, processed, approved, decline_reason) VALUES
  (3, 3, FALSE, FALSE, null);
INSERT INTO clinical_centre.registration_requests (id, user_id, processed, approved, decline_reason) VALUES
  (4, 4, FALSE, FALSE, null);
INSERT INTO clinical_centre.registration_requests (id, user_id, processed, approved, decline_reason) VALUES
  (5, 5, FALSE, FALSE, null);
INSERT INTO clinical_centre.registration_requests (id, user_id, processed, approved, decline_reason) VALUES
  (6, 6, FALSE, FALSE, null);


CREATE TABLE clinical_centre.clinics (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  address VARCHAR(100) NOT NULL,
  score INT DEFAULT NUll
);

INSERT INTO clinical_centre.clinics (id, name, address, score) VALUES
  (1, 'First clinic', 'Some address 1', null);
INSERT INTO clinical_centre.clinics (id, name, address, score) VALUES
  (2, 'Clinic with some name', 'Some address 1', 5);
INSERT INTO clinical_centre.clinics (id, name, address, score) VALUES
  (3, 'Bla bla clinic', 'Some address 1', 3);
INSERT INTO clinical_centre.clinics (id, name, address, score) VALUES
  (4, 'Some clinic', 'Some address 1', 4);
INSERT INTO clinical_centre.clinics (id, name, address, score) VALUES
  (5, 'Clinic number 5', 'Some address 1', 1);
INSERT INTO clinical_centre.clinics (id, name, address, score) VALUES
  (6, 'Clinic number 6', 'Some address 1', 5);
INSERT INTO clinical_centre.clinics (id, name, address, score) VALUES
  (7, 'Clinic number 7', 'Some address 1', 4);
INSERT INTO clinical_centre.clinics (id, name, address, score) VALUES
  (8, 'Clinic 8', 'Some address 1', null);