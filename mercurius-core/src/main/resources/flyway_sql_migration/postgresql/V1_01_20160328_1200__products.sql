CREATE TABLE PRODUCT(
  uuid CHARACTER VARYING(36) NOT NULL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL,
  code CHARACTER VARYING(255) NOT NULL,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL,
  shortName CHARACTER VARYING(255),
  main_unit_uuid CHARACTER VARYING(36) NOT NULL REFERENCES UNIT(uuid),
  main_category_uuid CHARACTER VARYING(36) NOT NULL REFERENCES CATEGORY(uuid),
  catalog_uuid CHARACTER VARYING(36) NOT NULL REFERENCES SHOP_CATALOG(uuid)
);

CREATE TABLE PRODUCTS_UNITS_LINK(
  product_uuid CHARACTER VARYING(36) NOT NULL REFERENCES PRODUCT(uuid),
  unit_uuid CHARACTER VARYING(36) NOT NULL REFERENCES UNIT(uuid)
);

CREATE TABLE PRODUCTS_CATEGORIES_LINK(
  product_uuid CHARACTER VARYING(36) NOT NULL REFERENCES PRODUCT(uuid),
  category_uuid CHARACTER VARYING(36) NOT NULL REFERENCES CATEGORY(uuid)
);


