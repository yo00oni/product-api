create table brand
(
    id   int auto_increment comment '브랜드 고유 ID'
        primary key,
    name varchar(100) not null comment '브랜드명'
);

INSERT INTO domain_db.brand (id, name) VALUES (1, 'A');
INSERT INTO domain_db.brand (id, name) VALUES (2, 'B');
INSERT INTO domain_db.brand (id, name) VALUES (3, 'C');
INSERT INTO domain_db.brand (id, name) VALUES (4, 'D');
INSERT INTO domain_db.brand (id, name) VALUES (5, 'E');
INSERT INTO domain_db.brand (id, name) VALUES (6, 'F');
INSERT INTO domain_db.brand (id, name) VALUES (7, 'G');
INSERT INTO domain_db.brand (id, name) VALUES (8, 'H');
INSERT INTO domain_db.brand (id, name) VALUES (9, 'I');
