CREATE TABLE PRODUCT(
  id BIGINT NOT NULL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL,
  code CHARACTER VARYING(255) NOT NULL,
  entity_uid CHARACTER VARYING(36) NOT NULL UNIQUE,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL,
  main_unit_id BIGINT NOT NULL REFERENCES UNIT(id),
  main_category_id BIGINT NOT NULL REFERENCES CATEGORY(id),
  catalog_id BIGINT NOT NULL REFERENCES SHOP_CATALOG(id)
);

CREATE SEQUENCE PRODUCT_SEQ
  INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807
  START 1 CACHE 1;

CREATE TABLE PRODUCTS_UNITS_LINK(
  product_id BIGINT NOT NULL REFERENCES PRODUCT(id),
  unit_id BIGINT NOT NULL REFERENCES UNIT(id)
);

CREATE TABLE PRODUCTS_CATEGORIES_LINK(
  product_id BIGINT NOT NULL REFERENCES PRODUCT(id),
  category_id BIGINT NOT NULL REFERENCES CATEGORY(id)
);


