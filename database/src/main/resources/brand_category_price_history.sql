create table brand_category_price_history
(
    id                int auto_increment comment '브랜드별 상품 카테고리의 가격정보'
        primary key,
    brand_category_id int                                  not null comment 'brand_category.id',
    price             int                                  not null comment '상품가',
    create_at         datetime default current_timestamp() null comment '생성일시'
);

INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (1, 24, 11200, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (2, 25, 5500, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (3, 26, 4200, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (4, 27, 9000, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (5, 28, 2000, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (6, 29, 1700, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (7, 30, 1800, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (8, 31, 2300, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (9, 32, 10500, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (10, 33, 5900, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (11, 34, 3800, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (12, 35, 9100, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (13, 36, 2100, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (14, 37, 2000, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (15, 38, 2000, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (16, 39, 2200, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (17, 40, 10000, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (18, 41, 6200, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (19, 42, 3300, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (20, 43, 9200, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (21, 44, 2200, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (22, 45, 1900, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (23, 46, 2200, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (24, 47, 2100, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (25, 49, 10100, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (26, 50, 5100, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (27, 51, 3000, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (28, 52, 9500, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (29, 53, 2500, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (30, 54, 1600, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (31, 55, 2400, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (32, 56, 2000, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (33, 57, 10700, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (34, 58, 5000, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (35, 59, 3800, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (36, 60, 9900, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (37, 61, 2300, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (38, 62, 1800, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (39, 63, 2100, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (40, 64, 2100, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (41, 65, 11200, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (42, 66, 7200, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (43, 67, 4000, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (44, 68, 9300, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (45, 69, 2100, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (46, 70, 1600, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (47, 71, 2300, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (48, 72, 1900, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (49, 73, 10500, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (50, 74, 5800, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (51, 75, 3900, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (52, 76, 9000, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (53, 77, 2200, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (54, 78, 1700, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (55, 79, 2100, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (56, 80, 2000, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (57, 81, 10800, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (58, 82, 6300, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (59, 83, 3100, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (60, 84, 9700, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (61, 85, 2100, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (62, 86, 1600, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (63, 87, 2000, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (64, 88, 2000, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (65, 89, 11400, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (66, 90, 6700, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (67, 91, 3200, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (68, 92, 9500, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (69, 93, 2400, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (70, 94, 1700, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (71, 95, 1700, '2024-10-01 14:14:00');
INSERT INTO domain_db.brand_category_price_history (id, brand_category_id, price, create_at) VALUES (72, 96, 2400, '2024-10-01 14:14:00');
