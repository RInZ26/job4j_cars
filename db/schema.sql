create table if not exists cars
(
    id bigint generated by default as identity
    constraint cars_pkey
    primary key,
    carmodel varchar(255),
    carcasetype varchar(255)
    );

alter table cars owner to postgres;

create table if not exists users
(
    id bigint generated by default as identity
    constraint users_pkey
    primary key,
    email varchar(255),
    login varchar(255),
    password varchar(255)
    );

alter table users owner to postgres;

create table if not exists adverts
(
    id bigint generated by default as identity
    constraint adverts_pkey
    primary key,
    description varchar(255),
    sold boolean,
    author_id bigint
    constraint fktc2unw3hskn07u9vuilvby7sc
    references users
    );

alter table adverts owner to postgres;

create table if not exists adverts_cars
(
    advert_id bigint not null
    constraint fk41und4eepqi6eo2tyxt38w4ot
    references adverts,
    car_id bigint not null
    constraint fk5xdxkd0y8e5pbd31ow8hxm9dh
    references cars,
    constraint adverts_cars_pkey
    primary key (advert_id, car_id)
    );

alter table adverts_cars owner to postgres;

create table if not exists images
(
    id bigint generated by default as identity
    constraint images_pkey
    primary key,
    advertid bigint
    constraint fkkhtplb0s9y9knf9saxgmvw4yu
    references adverts
);

alter table images owner to postgres;
