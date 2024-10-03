create table category
(
    id   int auto_increment comment '상품 카테고리 고유키',
    type varchar(20) not null,
    primary key (id, type)
);

INSERT INTO domain_db.category (id, type) VALUES (1, '상의');
INSERT INTO domain_db.category (id, type) VALUES (2, '아우터');
INSERT INTO domain_db.category (id, type) VALUES (3, '바지');
INSERT INTO domain_db.category (id, type) VALUES (4, '스니커즈');
INSERT INTO domain_db.category (id, type) VALUES (5, '가방');
INSERT INTO domain_db.category (id, type) VALUES (6, '모자');
INSERT INTO domain_db.category (id, type) VALUES (7, '양말');
INSERT INTO domain_db.category (id, type) VALUES (8, '액세서리');
