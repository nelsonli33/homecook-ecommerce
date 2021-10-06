INSERT INTO `comm_category`
VALUES (1, '2021-10-04 09:08:28', '2021-10-04 09:08:28', 0, '服裝', 1122, 'test', 'testdescription', NULL, NULL);
INSERT INTO `comm_category`
VALUES (2, '2021-10-04 09:08:39', '2021-10-04 09:08:39', 0, '男裝', 1122, 'test', 'testdescription', 1, NULL);
INSERT INTO `comm_category`
VALUES (3, '2021-10-04 09:08:54', '2021-10-04 09:08:54', 0, '男生 T-shirt', 1124, 'test', 'testdescription', 2, NULL);

INSERT INTO `comm_customer`
VALUES (1, '2021-10-01 19:01:43', '2021-10-01 19:01:43', 0, NULL, 'abcd1234',
        '$2a$10$msNkJQxg/TOwvK/1.oHHfeUTbVQ6utVh9ABkN32Ol6qZ2rXkWOhxu', '', NULL, NULL);

INSERT INTO `comm_product_attribute`
VALUES (1, 0, '2021-10-04 09:18:04', '2021-10-04 09:18:04', 0, '顏色', 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `comm_product_attribute`
VALUES (2, 0, '2021-10-04 09:18:04', '2021-10-04 09:18:04', 0, '尺寸', 2, 1, NULL, NULL, NULL, NULL);
INSERT INTO `comm_product_attribute_value`
VALUES (1, '2021-10-04 09:18:04', '2021-10-04 09:18:04', 0, '藍色', 1);
INSERT INTO `comm_product_attribute_value`
VALUES (2, '2021-10-04 09:18:04', '2021-10-04 09:18:04', 0, '綠色', 1);
INSERT INTO `comm_product_attribute_value`
VALUES (3, '2021-10-04 09:18:04', '2021-10-04 09:18:04', 0, 'S', 2);
INSERT INTO `comm_product_attribute_value`
VALUES (4, '2021-10-04 09:18:04', '2021-10-04 09:18:04', 0, 'M', 2);
INSERT INTO `comm_product_attribute_value`
VALUES (5, '2021-10-04 09:18:04', '2021-10-04 09:18:04', 0, 'L', 2);


INSERT INTO `comm_product_category_rel`
VALUES (1, 1);
INSERT INTO `comm_product_category_rel`
VALUES (1, 2);
INSERT INTO `comm_product_category_rel`
VALUES (2, 1);
INSERT INTO `comm_product_category_rel`
VALUES (3, 1);

INSERT INTO `comm_product_variant`
VALUES (1, '2021-10-04 09:18:04', '2021-10-04 09:18:04', 0, 780.00, 100, 'shirt-20210001', NULL, 1, NULL, 1, 3, NULL);
INSERT INTO `comm_product_variant`
VALUES (2, '2021-10-04 09:18:04', '2021-10-04 09:18:04', 0, 780.00, 100, 'shirt-20210002', NULL, 1, NULL, 1, 4, NULL);
INSERT INTO `comm_product_variant`
VALUES (3, '2021-10-04 09:18:04', '2021-10-04 09:18:04', 0, 1280.00, 100, 'shirt-20210003', NULL, 1, NULL, 1, 5, NULL);
INSERT INTO `comm_product_variant`
VALUES (4, '2021-10-04 09:18:04', '2021-10-04 09:18:04', 0, 780.00, 0, 'shirt-20210004', NULL, 1, NULL, 2, 3, NULL);
INSERT INTO `comm_product_variant`
VALUES (5, '2021-10-04 09:18:04', '2021-10-04 09:18:04', 0, 780.00, 100, 'shirt-20210005', NULL, 1, NULL, 2, 4, NULL);
INSERT INTO `comm_product_variant`
VALUES (6, '2021-10-04 09:18:04', '2021-10-04 09:18:04', 0, 780.00, 100, 'shirt-20210006', NULL, 1, NULL, 2, 5, NULL);

INSERT INTO `comm_product`
VALUES (1, '2021-10-04 09:18:04', '2021-10-04 09:18:04', 0, '測試商品2', NULL, '測試商品描述', 100, 780.00,
        'testproduct-20210001', 3, 1, NULL, NULL, NULL, NULL);
INSERT INTO `comm_product`
VALUES (2, '2021-10-04 09:18:58', '2021-10-04 09:18:58', 0, 'Hyperdrive 7-in-2', NULL, 'Hyperdrive 7-in-2 商品描述。', 100,
        980.00, 'testproduct-2222222', NULL, 1, NULL, NULL, NULL, NULL);

INSERT INTO `product_image_entity`
VALUES (1, '2021-10-04 09:17:15', '2021-10-04 09:18:04', 1, 'c9fbb662e6974cefb419e9982d214a42',
        '637616940741300000.png', 1, 'c9fbb662e6974cefb419e9982d214a42_100x100',
        'c9fbb662e6974cefb419e9982d214a42_450x450', 'c9fbb662e6974cefb419e9982d214a42_1000x1000', 1);
