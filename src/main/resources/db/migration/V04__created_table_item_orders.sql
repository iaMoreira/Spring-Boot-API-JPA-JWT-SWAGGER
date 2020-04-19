CREATE TABLE item_orders (
 	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	order_id BIGINT(20) REFERENCES orders (id),
	product_id BIGINT(20) REFERENCES products (id),
	amount INTEGER ,
	deleted_at TIMESTAMP NULL,
 	created_at TIMESTAMP NULL DEFAULT NOW()
);  