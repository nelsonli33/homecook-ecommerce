CREATE TABLE comm_category
(
    id                    BIGINT AUTO_INCREMENT NOT NULL,
    created_at            datetime              NOT NULL,
    updated_at            datetime              NULL,
    version               INT                   NULL,
    name                  VARCHAR(255)          NULL,
    sort_order            INT                   NULL,
    meta_title            VARCHAR(255)          NULL,
    meta_description      TEXT                  NULL,
    parent_id             BIGINT                NULL,
    attribute_category_id BIGINT                NULL,
    CONSTRAINT pk_comm_category PRIMARY KEY (id)
);

CREATE TABLE comm_product
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    created_at         datetime              NOT NULL,
    updated_at         datetime              NULL,
    version            INT                   NULL,
    name               VARCHAR(255)          NULL,
    summary            VARCHAR(255)          NULL,
    `description`      TEXT                  NULL,
    quantity           INT                   NULL,
    price              DOUBLE                NULL,
    sku                VARCHAR(255)          NULL,
    days_to_ship       INT                   NULL,
    status             INT                   NULL,
    max_order_quantity INT                   NULL,
    min_order_quantity INT                   NULL,
    meta_title         VARCHAR(255)          NULL,
    meta_description   TEXT                  NULL,
    CONSTRAINT pk_comm_product PRIMARY KEY (id)
);

CREATE TABLE comm_product_attribute_category
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime              NOT NULL,
    updated_at datetime              NULL,
    version    INT                   NULL,
    name       VARCHAR(255)          NULL,
    CONSTRAINT pk_comm_product_attribute_category PRIMARY KEY (id)
);

CREATE TABLE comm_product_attribute_value
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    created_at   datetime              NOT NULL,
    updated_at   datetime              NULL,
    version      INT                   NULL,
    attribute_id BIGINT                NULL,
    value        VARCHAR(255)          NULL,
    CONSTRAINT pk_comm_product_attribute_value PRIMARY KEY (id)
);

CREATE TABLE comm_product_image
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    created_at     datetime              NOT NULL,
    updated_at     datetime              NULL,
    version        INT                   NULL,
    code           VARCHAR(255)          NOT NULL,
    filename       VARCHAR(255)          NULL,
    originfilename VARCHAR(255)          NULL,
    position       INT                   NULL,
    thumbnail      VARCHAR(255)          NULL,
    normal         VARCHAR(255)          NULL,
    detail         VARCHAR(255)          NULL,
    zoom           VARCHAR(255)          NULL,
    product_id     BIGINT                NULL,
    CONSTRAINT pk_comm_product_image PRIMARY KEY (id)
);

CREATE TABLE comm_product_variant
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    created_at     datetime              NOT NULL,
    updated_at     datetime              NULL,
    version        INT                   NULL,
    name           VARCHAR(255)          NULL,
    price          DOUBLE                NULL,
    quantity       INT                   NULL,
    sku            VARCHAR(255)          NULL,
    sort_order     INT                   NULL,
    spec_value1_id BIGINT                NULL,
    spec_value2_id BIGINT                NULL,
    spec_value3_id BIGINT                NULL,
    product_id     BIGINT                NULL,
    image_id       BIGINT                NULL,
    CONSTRAINT pk_comm_product_variant PRIMARY KEY (id)
);

CREATE TABLE product2catrel
(
    category_id BIGINT NOT NULL,
    product_id  BIGINT NOT NULL,
    CONSTRAINT pk_product2catrel PRIMARY KEY (category_id, product_id)
);

ALTER TABLE comm_product_image
    ADD CONSTRAINT uc_comm_product_image_code UNIQUE (code);

CREATE INDEX idx_productimagemodel_code ON comm_product_image (code);

ALTER TABLE comm_category
    ADD CONSTRAINT FK_COMM_CATEGORY_ON_ATTRIBUTE_CATEGORY FOREIGN KEY (attribute_category_id) REFERENCES comm_product_attribute_category (id);

ALTER TABLE comm_category
    ADD CONSTRAINT FK_COMM_CATEGORY_ON_PARENT FOREIGN KEY (parent_id) REFERENCES comm_category (id);

ALTER TABLE comm_product_attribute_value
    ADD CONSTRAINT FK_COMM_PRODUCT_ATTRIBUTE_VALUE_ON_ATTRIBUTE FOREIGN KEY (attribute_id) REFERENCES comm_product_attribute_category (id);

ALTER TABLE comm_product_image
    ADD CONSTRAINT FK_COMM_PRODUCT_IMAGE_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES comm_product (id);

ALTER TABLE comm_product_variant
    ADD CONSTRAINT FK_COMM_PRODUCT_VARIANT_ON_IMAGE FOREIGN KEY (image_id) REFERENCES comm_product_image (id);

ALTER TABLE comm_product_variant
    ADD CONSTRAINT FK_COMM_PRODUCT_VARIANT_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES comm_product (id);

ALTER TABLE comm_product_variant
    ADD CONSTRAINT FK_COMM_PRODUCT_VARIANT_ON_SPEC_VALUE1 FOREIGN KEY (spec_value1_id) REFERENCES comm_product_attribute_value (id);

ALTER TABLE comm_product_variant
    ADD CONSTRAINT FK_COMM_PRODUCT_VARIANT_ON_SPEC_VALUE2 FOREIGN KEY (spec_value2_id) REFERENCES comm_product_attribute_value (id);

ALTER TABLE comm_product_variant
    ADD CONSTRAINT FK_COMM_PRODUCT_VARIANT_ON_SPEC_VALUE3 FOREIGN KEY (spec_value3_id) REFERENCES comm_product_attribute_value (id);

ALTER TABLE product2catrel
    ADD CONSTRAINT fk_pro_on_category_entity FOREIGN KEY (category_id) REFERENCES comm_category (id);

ALTER TABLE product2catrel
    ADD CONSTRAINT fk_pro_on_product_entity FOREIGN KEY (product_id) REFERENCES comm_product (id);