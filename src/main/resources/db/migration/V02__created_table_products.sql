CREATE TABLE products (
 	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
 	name VARCHAR(100),
 	description  VARCHAR(250),
 	price NUMERIC(20,2),
 	deleted_at TIMESTAMP NULL,
 	created_at TIMESTAMP NULL DEFAULT NOW()
 );
 
 INSERT INTO products (id, name, description, price) VALUES (1, 'Samsung Galaxy S20', 'Smartphone Samsung Galaxy S20 Rosa 128GB, 8GB RAM, Tela Infinita de 6.2, Câmera Tripla Traseira, Câmera Frontal 10MP, IP68 e Leitor de Digital', 4999.99);
 INSERT INTO products(id, name, description, price) VALUES (2, 'Xaomi Mi 10T', 'Smartphone Xiaomi Redmi Note 10 128GB 6GB RAM Branco', 1999.99);
 