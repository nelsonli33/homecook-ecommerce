CREATE TABLE categories
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    created_at       datetime              NOT NULL,
    updated_at       datetime              NULL,
    version          INT                   NULL,
    name             VARCHAR(255)          NULL,
    sort_order       INT                   NULL,
    meta_title       VARCHAR(255)          NULL,
    meta_description TEXT                  NULL,
    parent_id        BIGINT                NULL,
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

CREATE TABLE product2catrel
(
    category_id BIGINT NOT NULL,
    product_id  BIGINT NOT NULL,
    CONSTRAINT pk_product2catrel PRIMARY KEY (category_id, product_id)
);

CREATE TABLE productimages
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    created_at     datetime              NOT NULL,
    updated_at     datetime              NULL,
    version        INT                   NULL,
    filename       VARCHAR(255)          NOT NULL,
    originfilename VARCHAR(255)          NULL,
    position       INT                   NULL,
    thumbnail      VARCHAR(255)          NULL,
    normal         VARCHAR(255)          NULL,
    detail         VARCHAR(255)          NULL,
    zoom           VARCHAR(255)          NULL,
    product_id     BIGINT                NULL,
    CONSTRAINT pk_productimages PRIMARY KEY (id)
);

CREATE TABLE productoptions
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime              NOT NULL,
    updated_at datetime              NULL,
    version    INT                   NULL,
    name       VARCHAR(255)          NULL,
    position   INT                   NULL,
    product_id BIGINT                NULL,
    CONSTRAINT pk_productoptions PRIMARY KEY (id)
);

CREATE TABLE products
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
    CONSTRAINT pk_products PRIMARY KEY (id)
);

CREATE TABLE productvariants
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime              NOT NULL,
    updated_at datetime              NULL,
    version    INT                   NULL,
    name       VARCHAR(255)          NULL,
    price      DOUBLE                NULL,
    quantity   INT                   NULL,
    sku        VARCHAR(255)          NULL,
    option1    VARCHAR(255)          NULL,
    option2    VARCHAR(255)          NULL,
    product_id BIGINT                NULL,
    image_id   BIGINT                NULL,
    CONSTRAINT pk_productvariants PRIMARY KEY (id)
);

ALTER TABLE productimages
    ADD CONSTRAINT uc_productimages_filename UNIQUE (filename);

CREATE INDEX idx_productimagemodel ON productimages (filename);

ALTER TABLE categories
    ADD CONSTRAINT FK_CATEGORIES_ON_PARENT FOREIGN KEY (parent_id) REFERENCES categories (id);

ALTER TABLE productimages
    ADD CONSTRAINT FK_PRODUCTIMAGES_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id);

ALTER TABLE productoptions
    ADD CONSTRAINT FK_PRODUCTOPTIONS_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id);

ALTER TABLE productvariants
    ADD CONSTRAINT FK_PRODUCTVARIANTS_ON_IMAGE FOREIGN KEY (image_id) REFERENCES productimages (id);

ALTER TABLE productvariants
    ADD CONSTRAINT FK_PRODUCTVARIANTS_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id);

ALTER TABLE product2catrel
    ADD CONSTRAINT fk_pro_on_category_model FOREIGN KEY (category_id) REFERENCES categories (id);

ALTER TABLE product2catrel
    ADD CONSTRAINT fk_pro_on_product_model FOREIGN KEY (product_id) REFERENCES products (id);