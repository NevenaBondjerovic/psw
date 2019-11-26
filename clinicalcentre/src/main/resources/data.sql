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
  approved BOOLEAN NOT NULL DEFAULT FALSE,
  activated BOOLEAN NOT NULL DEFAULT FALSE
);

INSERT INTO users (username, password, name, surname, address, city, state, phone_number, insurance_number, approved, activated) VALUES
  ('admin@admin.com', 'admin', 'Pera', 'Peric', 'Adresa 1', 'Novi Sad', 'Srbija', '1234567', '987654321', TRUE, TRUE);
INSERT INTO users (username, password, name, surname, address, city, state, phone_number, insurance_number, approved, activated) VALUES
  ('user1@email.com', 'user1', 'Zika', 'Peric', 'Adresa 2', 'Novi Sad', 'Srbija', '23456789', '876543210', TRUE, TRUE);
