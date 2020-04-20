CREATE TABLE users (
 	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
 	name VARCHAR(100),
 	email VARCHAR(100) NOT NULL,
 	password VARCHAR(100) NOT NULL,
 	admin BOOLEAN DEFAULT FALSE,
 	deleted_at TIMESTAMP NULL,
 	created_at TIMESTAMP NULL DEFAULT NOW()
);
 
 INSERT INTO users (id, name, email, password) VALUES (1, 'Ian', 'ian@geobras.com', '$2a$10$8mF1.E9EQ.KiJKwALDgN5OqZBeRdKG/1fG/GF1UXCss34//58Z4k.');
 INSERT INTO users (id, name, email, password) VALUES (2, 'Roger', 'roger@geobras.com', '$2a$10$8mF1.E9EQ.KiJKwALDgN5OqZBeRdKG/1fG/GF1UXCss34//58Z4k.');
 INSERT INTO users (id, name, email, password) VALUES (3, 'Jandson', 'jandson@geobras.com', '$2a$10$8mF1.E9EQ.KiJKwALDgN5OqZBeRdKG/1fG/GF1UXCss34//58Z4k.');
 
