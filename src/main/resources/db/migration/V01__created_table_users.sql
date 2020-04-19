CREATE TABLE users (
 	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
 	name VARCHAR(100),
 	username VARCHAR(100) NOT NULL,
 	password VARCHAR(100) NOT NULL,
 	admin BOOLEAN DEFAULT FALSE,
 	deleted_at TIMESTAMP NULL,
 	created_at TIMESTAMP NULL DEFAULT NOW()
);
 
 INSERT INTO users (id, name, username, password) VALUES (1, 'Ian', 'ian@geobras.com', '123');
 INSERT INTO users (id, name, username, password) VALUES (2, 'Roger', 'roger@geobras.com', '123');
 INSERT INTO users (id, name, username, password) VALUES (3, 'Jandson', 'jandson@geobras.com', '123');
 
 
 