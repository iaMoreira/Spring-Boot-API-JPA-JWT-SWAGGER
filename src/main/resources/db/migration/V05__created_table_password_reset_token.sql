CREATE TABLE password_reset_token (
 	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
 	token VARCHAR(191) NOT NULL,
 	expiry_date TIMESTAMP NULL,
 	user_id BIGINT(20) REFERENCES users (id),
 	deleted_at TIMESTAMP NULL,
 	created_at TIMESTAMP NULL DEFAULT NOW()
);
 