drop table if exists order_product;
drop table if exists orders;
drop table if exists products;

CREATE TABLE IF NOT EXISTS orders(
    id SERIAL PRIMARY KEY,
    date DATE,
    cost NUMERIC
);

CREATE TABLE IF NOT EXISTS products(
    id SERIAL PRIMARY KEY,
    orders integer references orders(id),
    orders_key integer,
    name TEXT,
    cost NUMERIC
);