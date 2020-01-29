DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS registration_requests;
DROP TABLE IF EXISTS clinics;

CREATE TABLE users (
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

INSERT INTO users (id, username, password, name, surname, address, city, state, phone_number, insurance_number, activated) VALUES
  (1, 'admin@admin.com', 'admin', 'Pera', 'Peric', 'Adresa 1', 'Novi Sad', 'Srbija', '1234567', '987654321', TRUE);
INSERT INTO users (id, username, password, name, surname, address, city, state, phone_number, insurance_number, activated) VALUES
  (2, 'user1@email.com', 'user2', 'Zika', 'Peric', 'Adresa 2', 'Novi Sad', 'Srbija', '23456789', '876543210', FALSE);
INSERT INTO users (id, username, password, name, surname, address, city, state, phone_number, insurance_number, activated) VALUES
  (3, 'user3@email.com', 'user3', 'Mika', 'Peric', 'Adresa 3', 'Novi Sad', 'Srbija', '23456780', '876543219', FALSE);
INSERT INTO users (id, username, password, name, surname, address, city, state, phone_number, insurance_number, activated) VALUES
  (4, 'user4@email.com', 'user4', 'Mika', 'Peric', 'Adresa 3', 'Novi Sad', 'Srbija', '23456780', '876543219', FALSE);
INSERT INTO users (id, username, password, name, surname, address, city, state, phone_number, insurance_number, activated) VALUES
  (5, 'user5@email.com', 'user5', 'Mika', 'Peric', 'Adresa 4', 'Novi Sad', 'Srbija', '23456780', '876543219', FALSE);
INSERT INTO users (id, username, password, name, surname, address, city, state, phone_number, insurance_number, activated) VALUES
  (6, 'user6@email.com', 'user6', 'Mika', 'Peric', 'Adresa 5', 'Novi Sad', 'Srbija', '23456780', '876543219', FALSE);

CREATE TABLE registration_requests (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  user_id INT NOT NULL,
  processed BOOLEAN NOT NULL DEFAULT FALSE,
  approved BOOLEAN NOT NULL DEFAULT FALSE,
  decline_reason VARCHAR(200) DEFAULT NULL,

  FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO registration_requests (id, user_id, processed, approved, decline_reason) VALUES
  (1, 1, TRUE, TRUE, null);
INSERT INTO registration_requests (id, user_id, processed, approved, decline_reason) VALUES
  (2, 2, TRUE, TRUE, null);
INSERT INTO registration_requests (id, user_id, processed, approved, decline_reason) VALUES
  (3, 3, FALSE, FALSE, null);
INSERT INTO registration_requests (id, user_id, processed, approved, decline_reason) VALUES
  (4, 4, FALSE, FALSE, null);
INSERT INTO registration_requests (id, user_id, processed, approved, decline_reason) VALUES
  (5, 5, FALSE, FALSE, null);
INSERT INTO registration_requests (id, user_id, processed, approved, decline_reason) VALUES
  (6, 6, FALSE, FALSE, null);


CREATE TABLE clinics (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  address VARCHAR(100) NOT NULL,
  score INT DEFAULT NUll
);

INSERT INTO clinics (id, name, address, score) VALUES
  (1, 'First clinic', 'Some address 1', null);
INSERT INTO clinics (id, name, address, score) VALUES
  (2, 'Clinic with some name', 'Some address 1', 5);
INSERT INTO clinics (id, name, address, score) VALUES
  (3, 'Bla bla clinic', 'Some address 1', 3);
INSERT INTO clinics (id, name, address, score) VALUES
  (4, 'Some clinic', 'Some address 1', 4);
INSERT INTO clinics (id, name, address, score) VALUES
  (5, 'Clinic number 5', 'Some address 1', 1);
INSERT INTO clinics (id, name, address, score) VALUES
  (6, 'Clinic number 6', 'Some address 1', 5);
INSERT INTO clinics (id, name, address, score) VALUES
  (7, 'Clinic number 7', 'Some address 1', 4);
INSERT INTO clinics (id, name, address, score) VALUES
  (8, 'Clinic 8', 'Some address 1', null);


ALTER TABLE clinical_centre.users
ADD type ENUM('PATIENT', 'MEDICAL_STAFF','DOCTOR', 'NURSE', 'CLINIC_ADMIN', 'CLINICAL_CENTRE_ADMIN', 'NEW_USER')
DEFAULT 'NEW_USER' NOT NULL;


UPDATE clinical_centre.users
    SET type = 'CLINICAL_CENTRE_ADMIN' where id = 1;
UPDATE clinical_centre.users
    SET type = 'PATIENT' where id = 2;


-----------HALLS----------------------

CREATE TABLE clinical_centre.halls (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  clinic_id INT NOT NULL,

  FOREIGN KEY (clinic_id) REFERENCES clinics(id)
);

INSERT INTO clinical_centre.halls VALUES
  (1, 'H1', 1);
INSERT INTO clinical_centre.halls VALUES
  (2, 'H2', 1);
INSERT INTO clinical_centre.halls VALUES
  (3, 'H3', 1);
INSERT INTO clinical_centre.halls VALUES
  (4, 'A1', 2);
INSERT INTO clinical_centre.halls VALUES
  (5, 'A2', 2);
INSERT INTO clinical_centre.halls VALUES
  (6, 'H1', 3);
INSERT INTO clinical_centre.halls VALUES
  (7, 'H2', 3);
INSERT INTO clinical_centre.halls VALUES
  (8, 'H3', 3);
INSERT INTO clinical_centre.halls VALUES
  (9, '1', 4);
INSERT INTO clinical_centre.halls VALUES
  (10, '2', 4);
INSERT INTO clinical_centre.halls VALUES
  (11, 'B1', 5);
INSERT INTO clinical_centre.halls VALUES
  (12, 'B2', 5);
INSERT INTO clinical_centre.halls VALUES
  (13, 'H1', 5);
INSERT INTO clinical_centre.halls VALUES
  (14, 'H2', 5);
INSERT INTO clinical_centre.halls VALUES
  (15, 'H1', 6);
INSERT INTO clinical_centre.halls VALUES
  (16, 'H2', 6);
INSERT INTO clinical_centre.halls VALUES
  (17, 'H3', 6);
INSERT INTO clinical_centre.halls VALUES
  (18, 'H1', 7);
INSERT INTO clinical_centre.halls VALUES
  (19, 'H2', 7);
INSERT INTO clinical_centre.halls VALUES
  (20, 'H1', 8);
INSERT INTO clinical_centre.halls VALUES
  (21, 'H2', 8);
INSERT INTO clinical_centre.halls VALUES
  (22, 'H3', 8);


----------TYPE OF APPOINTMENT-----------

CREATE TABLE clinical_centre.types_of_appointment (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(200) NOT NULL,
  clinic_id INT NOT NULL,

  FOREIGN KEY (clinic_id) REFERENCES clinics(id)

);

INSERT INTO clinical_centre.types_of_appointment VALUES
  (1, 'General medical examination', 1);
INSERT INTO clinical_centre.types_of_appointment VALUES
  (2, 'Consultations', 1);
INSERT INTO clinical_centre.types_of_appointment VALUES
  (3, 'Complete examination', 1);
INSERT INTO clinical_centre.types_of_appointment VALUES
  (4, 'Annual medical check-up', 1);
INSERT INTO clinical_centre.types_of_appointment VALUES
  (5, 'General medical examination', 3);
INSERT INTO clinical_centre.types_of_appointment VALUES
  (6, 'Consultations', 3);
INSERT INTO clinical_centre.types_of_appointment VALUES
  (7, 'Complete examination', 3);
INSERT INTO clinical_centre.types_of_appointment VALUES
  (8, 'Annual medical check-up', 3);

---------PRICELIST---------------------

CREA
CREATE TABLE clinical_centre.pricelist (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  type_id INT NOT NULL,
  price INT NOT NULL,
  discount INT NOT NULL,
  clinic_id INT NOT NULL,

  FOREIGN KEY (type_id) REFERENCES types_of_appointment(id),
  FOREIGN KEY (clinic_id) REFERENCES clinics(id)
);

INSERT INTO clinical_centre.pricelist VALUES
  (1, 1, 20, 15, 1);
INSERT INTO clinical_centre.pricelist VALUES
  (2, 2, 10, 0, 1);
INSERT INTO clinical_centre.pricelist VALUES
  (3, 3, 50, 5, 1);
INSERT INTO clinical_centre.pricelist VALUES
  (4, 4, 50, 0, 1);
INSERT INTO clinical_centre.pricelist VALUES
  (5, 5, 20, 0, 3);
INSERT INTO clinical_centre.pricelist VALUES
  (6, 6, 15, 0, 3);
INSERT INTO clinical_centre.pricelist VALUES
  (7, 7, 60, 10, 3);
INSERT INTO clinical_centre.pricelist VALUES
  (8, 8, 50, 5, 3);

-----------------------------------------

UPDATE clinical_centre.users
SET activated = true, type = 'DOCTOR'
WHERE id = 4 or id = 3;

UPDATE clinical_centre.users
SET activated = true, type = 'PATIENT'
WHERE id = 5 or id = 6;

----------CLINICS_DOCTORS---------------

CREATE TABLE clinical_centre.clinics_doctors (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  user_id INT NOT NULL,
  clinic_id INT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (clinic_id) REFERENCES clinics(id)
);

INSERT INTO clinical_centre.clinics_doctors VALUES
  (1, 3, 1);
INSERT INTO clinical_centre.clinics_doctors VALUES
  (2, 4, 3);

---------------APPOINTMENTS---------------

CREATE TABLE clinical_centre.appointments (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  date_of_appointment date NOT NULL,
  time_of_appointment VARCHAR(10) NOT NULL,
  clinic_id INT NOT NULL,
  hall_id INT NOT NULL,
  doctor_id INT NOT NULL,
  type_id INT NOT NULL,
  pricelist_id INT NOT NULL,
  scheduled_for_id INT NULL,

  FOREIGN KEY (clinic_id) REFERENCES clinics(id),
  FOREIGN KEY (hall_id) REFERENCES halls(id),
  FOREIGN KEY (doctor_id) REFERENCES users(id),
  FOREIGN KEY (type_id) REFERENCES types_of_appointment(id),
  FOREIGN KEY (pricelist_id) REFERENCES pricelist(id),
  FOREIGN KEY (scheduled_for_id) REFERENCES users(id)
);



INSERT INTO clinical_centre.appointments VALUES
  (1, '2020-02-26', ' 08:30' , 1, 2, 3, 1, 1, null);
INSERT INTO clinical_centre.appointments VALUES
  (2, '2020-02-26', ' 09:30' , 1, 3, 3, 2, 2, 6);
INSERT INTO clinical_centre.appointments VALUES
  (3, '2020-02-27', ' 12:30' , 3, 6, 4, 6, 6, null);
INSERT INTO clinical_centre.appointments VALUES
  (4, '2020-02-27', ' 13:30' , 3, 7, 4, 7, 7, 2);
INSERT INTO clinical_centre.appointments VALUES
  (5, '2020-02-27', ' 14:30' , 3, 8, 4, 8, 8, null);

INSERT INTO clinical_centre.appointments VALUES
  (6, '2020-02-26', ' 08:30' , 1, 3, 3, 1, 1, null);
INSERT INTO clinical_centre.appointments VALUES
  (7, '2020-02-26', ' 11:30' , 1, 1, 3, 1, 1, null);
INSERT INTO clinical_centre.appointments VALUES
  (8, '2020-02-26', ' 11:30' , 3, 6, 4, 7, 7, null);
INSERT INTO clinical_centre.appointments VALUES
  (9, '2020-02-26', ' 11:30' , 3, 6, 4, 5, 5, null);

update clinical_centre.clinics
set score = 4
where id = 1;