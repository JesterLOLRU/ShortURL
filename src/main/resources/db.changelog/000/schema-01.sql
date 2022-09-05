create table if not exists url
(
    id                      uuid not null
        constraint url_pkey
            primary key,
    original                varchar(255),
    link                    varchar(255),
    rank                    integer,
    count                   integer

);
