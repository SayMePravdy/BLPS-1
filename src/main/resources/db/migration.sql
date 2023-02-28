create table roles
(
    id   integer primary key,
    name text
);

create table users
(
    id       serial primary key,
    email    text            not null,
    password text,
    role_id  integer
        constraint users_roles_id_fk
            references roles not null
);

create table products
(
    id            serial primary key,
    name          text not null,
    weight        float,
    consumer_info text
);

create type purchase_status as enum (
    'CREATED',
    'WAIT_PAYMENT',
    'PAID'
);

create table purchases
(
    id         serial primary key,
    min_count  integer               not null,
    cur_count  integer default 0     not null, ,
    status     purchase_status,
    product_id integer
        constraint purchases_products_id_fk
            references products,
    is_deleted bool    default false not null
);

create table favourites
(
    product_id integer
        constraint favourite_product_id_fk
            references products,
    user_id    integer
        constraint favourites_users_id_fk
            references users,
    primary key (product_id, user_id)
);

create table orders
(
    purchase_id integer
        constraint orders_purchases_id_fk
            references purchases not null,
    count       integer          not null,
    user_id     integer
        constraint orders_users_id_fk
            references users     not null,
    primary key (purchase_id, user_id)
);

insert into roles (id, name)
VALUES (1, 'ROLE_USER'),
       (2, 'ROLE_ADMIN');