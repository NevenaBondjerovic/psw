DROP TABLE IF EXISTS users;

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
  (2, 'user1@email.com', 'user1', 'Zika', 'Peric', 'Adresa 2', 'Novi Sad', 'Srbija', '23456789', '876543210', FALSE);
INSERT INTO users (id, username, password, name, surname, address, city, state, phone_number, insurance_number, activated) VALUES
  (3, 'user2@email.com', 'user2', 'Mika', 'Peric', 'Adresa 2', 'Novi Sad', 'Srbija', '23456780', '876543219', FALSE);

CREATE TABLE registration_request (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  user_id INT NOT NULL,
  processed BOOLEAN NOT NULL DEFAULT FALSE,
  approved BOOLEAN NOT NULL DEFAULT FALSE,
  decline_reason VARCHAR(200) DEFAULT NULL,

  FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO registration_request (id, user_id, processed, approved, decline_reason) VALUES
  (1, 1, TRUE, TRUE, '');
INSERT INTO registration_request (id, user_id, processed, approved, decline_reason) VALUES
  (2, 2, TRUE, TRUE, '');
INSERT INTO registration_request (id, user_id, processed, approved, decline_reason) VALUES
  (3, 3, FALSE, FALSE, '');