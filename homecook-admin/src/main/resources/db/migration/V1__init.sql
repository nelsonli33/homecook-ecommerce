CREATE TABLE comm_address
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    type             INT                   NULL,
    created_at       datetime              NOT NULL,
    updated_at       datetime              NULL,
    version          INT                   NULL,
    country          VARCHAR(255)          NULL,
    city             VARCHAR(255)          NULL,
    district         VARCHAR(255)          NULL,
    zipcode          VARCHAR(255)          NULL,
    address          VARCHAR(255)          NULL,
    name             VARCHAR(255)          NULL,
    phone            VARCHAR(255)          NULL,
    store_id         VARCHAR(255)          NULL,
    store_name       VARCHAR(255)          NULL,
    customer_id      BIGINT                NULL,
    shipping_mode_id BIGINT                NULL,
    CONSTRAINT pk_comm_address PRIMARY KEY (id)
);

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

CREATE TABLE comm_checkout
(
    id                  BIGINT AUTO_INCREMENT NOT NULL,
    created_at          datetime              NOT NULL,
    updated_at          datetime              NULL,
    version             INT                   NULL,
    code                VARCHAR(255)          NULL,
    note                VARCHAR(255)          NULL,
    cart_id             BIGINT                NULL,
    subtotal            DOUBLE                NULL,
    shipping_cost       DOUBLE                NULL,
    total_price         DOUBLE                NULL,
    shipping_address_id BIGINT                NULL,
    payment_mode_id     BIGINT                NULL,
    checkout_invoice_id BIGINT                NULL,
    customer_id         BIGINT                NULL,
    CONSTRAINT pk_comm_checkout PRIMARY KEY (id)
);

CREATE TABLE comm_checkout_invoice
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    created_at       datetime              NOT NULL,
    updated_at       datetime              NULL,
    version          INT                   NULL,
    invoice_type     VARCHAR(255)          NULL,
    contact_email    VARCHAR(255)          NULL,
    company_name     VARCHAR(255)          NULL,
    business_number  VARCHAR(255)          NULL,
    charity_lovecode VARCHAR(255)          NULL,
    charity_name     VARCHAR(255)          NULL,
    checkout_code    VARCHAR(255)          NULL,
    CONSTRAINT pk_comm_checkout_invoice PRIMARY KEY (id)
);

CREATE TABLE comm_credit_card_payment_info
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    created_at     datetime              NOT NULL,
    updated_at     datetime              NULL,
    version        INT                   NULL,
    card_bin       VARCHAR(255)          NULL,
    card_last_four VARCHAR(255)          NULL,
    card_type      VARCHAR(255)          NULL,
    transaction_id BIGINT                NULL,
    CONSTRAINT pk_comm_credit_card_payment_info PRIMARY KEY (id)
);

CREATE TABLE comm_customer
(
    id                             BIGINT AUTO_INCREMENT NOT NULL,
    created_at                     datetime              NOT NULL,
    updated_at                     datetime              NULL,
    version                        INT                   NULL,
    name                           VARCHAR(255)          NULL,
    account                        VARCHAR(255)          NOT NULL,
    password                       VARCHAR(255)          NULL,
    email                          VARCHAR(255)          NULL,
    phone                          VARCHAR(255)          NULL,
    birthday                       date                  NULL,
    default_address_id             BIGINT                NULL,
    last_checkout_shipment_address BIGINT                NULL,
    default_invoice_type           VARCHAR(255)          NULL,
    company_invoice_setting_id     BIGINT                NULL,
    donation_invoice_setting_id    BIGINT                NULL,
    invoice_carrier_id             BIGINT                NULL,
    default_payment_mode_id        BIGINT                NULL,
    CONSTRAINT pk_comm_customer PRIMARY KEY (id)
);

CREATE TABLE comm_invoice_carrier
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    created_at   datetime              NOT NULL,
    updated_at   datetime              NULL,
    version      INT                   NULL,
    carrier_type INT                   NULL,
    barcode      VARCHAR(255)          NULL,
    CONSTRAINT pk_comm_invoice_carrier PRIMARY KEY (id)
);

CREATE TABLE comm_invoice_setting
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    created_at       datetime              NOT NULL,
    updated_at       datetime              NULL,
    version          INT                   NULL,
    invoice_type     VARCHAR(255)          NULL,
    contact_email    VARCHAR(255)          NULL,
    invoice_title    VARCHAR(255)          NULL,
    business_number  VARCHAR(255)          NULL,
    charity_lovecode VARCHAR(255)          NULL,
    charity_name     VARCHAR(255)          NULL,
    CONSTRAINT pk_comm_invoice_setting PRIMARY KEY (id)
);

CREATE TABLE comm_order
(
    id                  BIGINT AUTO_INCREMENT NOT NULL,
    created_at          datetime              NOT NULL,
    updated_at          datetime              NULL,
    version             INT                   NULL,
    code                VARCHAR(255)          NULL,
    order_status        VARCHAR(255)          NULL,
    subtotal            DOUBLE                NULL,
    total_discounts     DOUBLE                NULL,
    shipping_cost       DOUBLE                NULL,
    total_price         DOUBLE                NULL,
    payment_mode_id     BIGINT                NULL,
    payment_status      VARCHAR(255)          NULL,
    pay_time            datetime              NULL,
    shipping_mode_id    BIGINT                NULL,
    shipping_address_id BIGINT                NULL,
    note                VARCHAR(255)          NULL,
    invoice_id          BIGINT                NULL,
    shipped_at          datetime              NULL,
    exp_shipping_at     datetime              NULL,
    complete_at         datetime              NULL,
    cancelled_at        datetime              NULL,
    customer_id         BIGINT                NULL,
    CONSTRAINT pk_comm_order PRIMARY KEY (id)
);

CREATE TABLE comm_order_invoice
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_at      datetime              NOT NULL,
    updated_at      datetime              NULL,
    version         INT                   NULL,
    invoice_type    VARCHAR(255)          NULL,
    invoice_title   VARCHAR(255)          NULL,
    business_number VARCHAR(255)          NULL,
    contact_email   VARCHAR(255)          NULL,
    love_code       VARCHAR(255)          NULL,
    invoice_number  VARCHAR(255)          NULL,
    invoice_date    datetime              NULL,
    order_code      VARCHAR(255)          NULL,
    gateway_message VARCHAR(255)          NULL,
    invoice_status  VARCHAR(255)          NULL,
    CONSTRAINT pk_comm_order_invoice PRIMARY KEY (id)
);

CREATE TABLE comm_order_line_item
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
    order_id        BIGINT                NULL,
    CONSTRAINT pk_comm_order_line_item PRIMARY KEY (id)
);

CREATE TABLE comm_payment_mode
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_at    datetime              NOT NULL,
    updated_at    datetime              NULL,
    version       INT                   NULL,
    code          VARCHAR(255)          NOT NULL,
    name          VARCHAR(255)          NULL,
    active        BIT(1)                NULL,
    `description` VARCHAR(255)          NULL,
    CONSTRAINT pk_comm_payment_mode PRIMARY KEY (id)
);

CREATE TABLE comm_payment_transaction
(
    id                     BIGINT AUTO_INCREMENT NOT NULL,
    created_at             datetime              NOT NULL,
    updated_at             datetime              NULL,
    version                INT                   NULL,
    amount                 INT                   NULL,
    currency               VARCHAR(255)          NULL,
    gateway_transaction_id VARCHAR(255)          NULL,
    bank_transaction_id    VARCHAR(255)          NULL,
    transaction_type       VARCHAR(255)          NULL,
    transaction_date       datetime              NULL,
    gateway_message        VARCHAR(255)          NULL,
    order_id               BIGINT                NULL,
    parent_id              BIGINT                NULL,
    transaction_status     VARCHAR(255)          NULL,
    CONSTRAINT pk_comm_payment_transaction PRIMARY KEY (id)
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
    sales_unit         VARCHAR(255)          NOT NULL,
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

CREATE TABLE comm_product_image
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
    CONSTRAINT pk_comm_product_image PRIMARY KEY (id)
);

CREATE TABLE comm_product_shippingmode_rel
(
    product_id       BIGINT NOT NULL,
    shipping_mode_id BIGINT NOT NULL,
    CONSTRAINT pk_comm_product_shippingmode_rel PRIMARY KEY (product_id, shipping_mode_id)
);

CREATE TABLE comm_product_variant
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    created_at     datetime              NOT NULL,
    updated_at     datetime              NULL,
    version        INT                   NULL,
    price          DECIMAL(10, 2)        NULL,
    quantity       INT                   NULL,
    reserve_stock  INT DEFAULT 0         NOT NULL,
    sku            VARCHAR(255)          NULL,
    sort_order     INT                   NULL,
    product_id     BIGINT                NULL,
    image_id       BIGINT                NULL,
    spec_value1_id BIGINT                NULL,
    spec_value2_id BIGINT                NULL,
    spec_value3_id BIGINT                NULL,
    CONSTRAINT pk_comm_product_variant PRIMARY KEY (id)
);

CREATE TABLE comm_shipping_mode
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_at    datetime              NOT NULL,
    updated_at    datetime              NULL,
    version       INT                   NULL,
    code          VARCHAR(255)          NOT NULL,
    name          VARCHAR(255)          NULL,
    `description` VARCHAR(255)          NULL,
    active        BIT(1)                NULL,
    shipping_cost DOUBLE                NULL,
    mode_type     VARCHAR(255)          NULL,
    CONSTRAINT pk_comm_shipping_mode PRIMARY KEY (id)
);

CREATE TABLE comm_voucher
(
    id                  BIGINT AUTO_INCREMENT NOT NULL,
    created_at          datetime              NOT NULL,
    updated_at          datetime              NULL,
    version             INT                   NULL,
    code                VARCHAR(255)          NOT NULL,
    name                VARCHAR(255)          NULL,
    note                VARCHAR(255)          NULL,
    use_type            INT                   NULL,
    value_type          INT                   NULL,
    usage_count         INT                   NULL,
    receive_count       INT                   NULL,
    usage_limit         INT                   NULL,
    max_discount_value  DOUBLE                NULL,
    percentage          DOUBLE                NULL,
    value               DOUBLE                NULL,
    minimum_amount      DOUBLE                NULL,
    current_usage_count INT                   NULL,
    start_at            datetime              NULL,
    end_at              datetime              NULL,
    display             BIT(1)                NULL,
    CONSTRAINT pk_comm_voucher PRIMARY KEY (id)
);

CREATE TABLE comm_voucher_buy_record
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    created_at  datetime              NOT NULL,
    updated_at  datetime              NULL,
    version     INT                   NULL,
    voucher_id  BIGINT                NULL,
    customer_id BIGINT                NULL,
    order_id    BIGINT                NULL,
    use_at      datetime              NULL,
    CONSTRAINT pk_comm_voucher_buy_record PRIMARY KEY (id)
);

CREATE TABLE comm_voucher_category_restrictions_rel
(
    category_id BIGINT NOT NULL,
    voucher_id  BIGINT NOT NULL,
    CONSTRAINT pk_comm_voucher_category_restrictions_rel PRIMARY KEY (category_id, voucher_id)
);

CREATE TABLE comm_voucher_product_restrictions_rel
(
    product_id BIGINT NOT NULL,
    voucher_id BIGINT NOT NULL,
    CONSTRAINT pk_comm_voucher_product_restrictions_rel PRIMARY KEY (product_id, voucher_id)
);

CREATE TABLE comm_voucher_receive
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    created_at  datetime              NOT NULL,
    updated_at  datetime              NULL,
    version     INT                   NULL,
    customer_id BIGINT                NULL,
    voucher_id  BIGINT                NULL,
    use_status  INT                   NULL,
    receive_at  datetime              NULL,
    CONSTRAINT pk_comm_voucher_receive PRIMARY KEY (id)
);

ALTER TABLE comm_cart
    ADD CONSTRAINT uc_comm_cart_code UNIQUE (code);

ALTER TABLE comm_checkout
    ADD CONSTRAINT uc_comm_checkout_code UNIQUE (code);

ALTER TABLE comm_customer
    ADD CONSTRAINT uc_comm_customer_account UNIQUE (account);

ALTER TABLE comm_customer
    ADD CONSTRAINT uc_comm_customer_email UNIQUE (email);

ALTER TABLE comm_customer
    ADD CONSTRAINT uc_comm_customer_phone UNIQUE (phone);

ALTER TABLE comm_order
    ADD CONSTRAINT uc_comm_order_code UNIQUE (code);

ALTER TABLE comm_payment_mode
    ADD CONSTRAINT uc_comm_payment_mode_code UNIQUE (code);

ALTER TABLE comm_product_image
    ADD CONSTRAINT uc_comm_product_image_filename UNIQUE (filename);

ALTER TABLE comm_shipping_mode
    ADD CONSTRAINT uc_comm_shipping_mode_code UNIQUE (code);

CREATE INDEX idx_cartentity_code ON comm_cart (code);

CREATE INDEX idx_orderentity_code ON comm_order (code);