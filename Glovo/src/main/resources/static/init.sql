CREATE TABLE IF NOT EXISTS orders(
    id SERIAL PRIMARY KEY,
    date DATE NOT NULL,
    cost DOUBLE PRECISION NOT NULL
);

CREATE TABLE IF NOT EXISTS products(
id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cost DOUBLE PRECISION NOT NULL
);

--many to many relationship
CREATE TABLE IF NOT EXISTS order_product(
    id_order INTEGER REFERENCES orders(id) ON DELETE CASCADE,
    id_product INTEGER REFERENCES products(id),
    CONSTRAINT order_product_pk PRIMARY KEY (id_order, id_product)
);