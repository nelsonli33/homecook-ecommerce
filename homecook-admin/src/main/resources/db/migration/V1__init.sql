CREATE TABLE comm_cart
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_at      datetime              NOT NULL,
    updated_at      datetime              NULL,
    version         INT                   NULL,
    code            VARCHAR(255)          NULL,
    subtotal        DOUBLE                NULL,
    total_price     DOUBLE                NULL,
    total_discounts DOUBLE                NULL,
    customer_id     BIGINT                NULL,
    CONSTRAINT pk_comm_cart PRIMARY KEY (id)
);

CREATE TABLE comm_cart_line_item
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_at      datetime              NOT NULL,
    updated_at      datetime              NULL,
    version         INT                   NULL,
    name            VARCHAR(255)          NULL,
    price           DOUBLE                NULL,
    quantity        INT                   NULL,
    sku             VARCHAR(255)          NULL,
    item_key        VARCHAR(255)          NULL,
    product_id      BIGINT                NULL,
    variant_id      BIGINT                NULL,
    subtotal        DOUBLE                NULL,
    total_discounts DOUBLE                NULL,
    total_price     DOUBLE                NULL,
    cart_id         BIGINT                NULL,
    CONSTRAINT pk_comm_cart_line_item PRIMARY KEY (id)
);

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

CREATE TABLE comm_customer
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime              NOT NULL,
    updated_at datetime              NULL,
    version    INT                   NULL,
    name       VARCHAR(255)          NULL,
    account    VARCHAR(255)          NOT NULL,
    password   VARCHAR(255)          NULL,
    email      VARCHAR(255)          NULL,
    phone      VARCHAR(255)          NULL,
    birthday   date                  NULL,
    CONSTRAINT pk_comm_customer PRIMARY KEY (id)
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
    price              DECIMAL(10, 2)        NULL,
    sku                VARCHAR(255)          NULL,
    days_to_ship       INT                   NULL,
    status             INT                   NULL,
    max_order_quantity INT                   NULL,
    min_order_quantity INT                   NULL,
    meta_title         VARCHAR(255)          NULL,
    meta_description   TEXT                  NULL,
    CONSTRAINT pk_comm_product PRIMARY KEY (id)
);

CREATE TABLE comm_product_attribute
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    type            INT                   NULL,
    created_at      datetime              NOT NULL,
    updated_at      datetime              NULL,
    version         INT                   NULL,
    name            VARCHAR(255)          NULL,
    sort_order      INT                   NULL,
    product_id      BIGINT                NULL,
    select_type     INT                   NULL,
    input_type      INT                   NULL,
    input_list      VARCHAR(255)          NULL,
    manual_add_type INT                   NULL,
    CONSTRAINT pk_comm_product_attribute PRIMARY KEY (id)
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
    value        VARCHAR(255)          NULL,
    attribute_id BIGINT                NULL,
    CONSTRAINT pk_comm_product_attribute_value PRIMARY KEY (id)
);

CREATE TABLE comm_product_category_rel
(
    category_id BIGINT NOT NULL,
    product_id  BIGINT NOT NULL,
    CONSTRAINT pk_comm_product_category_rel PRIMARY KEY (category_id, product_id)
);

CREATE TABLE comm_product_variant
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    created_at     datetime              NOT NULL,
    updated_at     datetime              NULL,
    version        INT                   NULL,
    price          DECIMAL(10, 2)        NULL,
    quantity       INT                   NULL,
    sku            VARCHAR(255)          NULL,
    sort_order     INT                   NULL,
    product_id     BIGINT                NULL,
    image_id       BIGINT                NULL,
    spec_value1_id BIGINT                NULL,
    spec_value2_id BIGINT                NULL,
    spec_value3_id BIGINT                NULL,
    CONSTRAINT pk_comm_product_variant PRIMARY KEY (id)
);

CREATE TABLE product_image_entity
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    created_at     datetime              NOT NULL,
    updated_at     datetime              NULL,
    version        INT                   NULL,
    filename       VARCHAR(255)          NOT NULL,
    originfilename VARCHAR(255)          NULL,
    sort_order     INT                   NULL,
    thumbnail      VARCHAR(255)          NULL,
    normal         VARCHAR(255)          NULL,
    detail         VARCHAR(255)          NULL,
    product_id     BIGINT                NULL,
    CONSTRAINT pk_productimageentity PRIMARY KEY (id)
);

ALTER TABLE comm_cart
    ADD CONSTRAINT uc_comm_cart_code UNIQUE (code);

ALTER TABLE comm_customer
    ADD CONSTRAINT uc_comm_customer_account UNIQUE (account);

ALTER TABLE comm_customer
    ADD CONSTRAINT uc_comm_customer_email UNIQUE (email);

ALTER TABLE comm_customer
    ADD CONSTRAINT uc_comm_customer_phone UNIQUE (phone);

ALTER TABLE product_image_entity
    ADD CONSTRAINT uc_productimageentity_filename UNIQUE (filename);