create table brand
(
    id   int auto_increment comment '브랜드 고유 ID'
        primary key,
    name varchar(100) not null comment '브랜드명',
    constraint idx_brand_1
        unique (name)
);

create table category
(
    id   int auto_increment comment '상품 카테고리 고유키',
    type varchar(20) not null,
    primary key (id, type),
    constraint idx_category_1
        unique (type)
);

create table brand_category
(
    id          int auto_increment comment '브랜드의 상품 카테고리별 Key'
        primary key,
    brand_id    int           not null comment '브랜드 고유 ID',
    category_id int           not null comment '상품 카테고리 ID',
    price       int default 0 null comment '가격',
    constraint idx_brand_category_1
        unique (brand_id, category_id)
);
