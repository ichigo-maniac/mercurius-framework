CREATE TABLE PRODUCT(
  uuid CHARACTER VARYING(36) NOT NULL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL,
  code CHARACTER VARYING(255) NOT NULL,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL,
  shortName CHARACTER VARYING(255),
  main_unit_uuid CHARACTER VARYING(36) NOT NULL REFERENCES UNIT(uuid),
  main_category_uuid CHARACTER VARYING(36) NOT NULL REFERENCES CATEGORY(uuid),
  catalog_uuid CHARACTER VARYING(36) NOT NULL REFERENCES SHOP_CATALOG(uuid),
  description_uuid CHARACTER VARYING(36) REFERENCES BIG_STRING(uuid)
);

CREATE TABLE PRODUCTS_UNITS_LINK(
  product_uuid CHARACTER VARYING(36) NOT NULL REFERENCES PRODUCT(uuid),
  unit_uuid CHARACTER VARYING(36) NOT NULL REFERENCES UNIT(uuid)
);

CREATE TABLE PRODUCTS_CATEGORIES_LINK(
  product_uuid CHARACTER VARYING(36) NOT NULL REFERENCES PRODUCT(uuid),
  category_uuid CHARACTER VARYING(36) NOT NULL REFERENCES CATEGORY(uuid)
);

-- Products --

INSERT INTO PRODUCT(uuid, name, code, creationtime, modificationtime, catalog_uuid, main_category_uuid, main_unit_uuid) VALUES (
  'a1f05e10-fa94-11e6-b6ff-bf2400ed613a', 'Sword Art Online vol. 01', 'product_sao_01', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'),
  (SELECT uuid FROM CATEGORY WHERE code ='fantasy_manga' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog')),
  (SELECT uuid FROM UNIT WHERE code ='pieces' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'))
);

INSERT INTO PRODUCT(uuid, name, code, creationtime, modificationtime, catalog_uuid, main_category_uuid, main_unit_uuid) VALUES (
  'a1f0970e-fa94-11e6-b700-c3c4039ff350', 'Sword Art Online vol. 02', 'product_sao_02', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'),
  (SELECT uuid FROM CATEGORY WHERE code ='fantasy_manga' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog')),
  (SELECT uuid FROM UNIT WHERE code ='pieces' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'))
);

INSERT INTO PRODUCT(uuid, name, code, creationtime, modificationtime, catalog_uuid, main_category_uuid, main_unit_uuid) VALUES (
  'a1f0b496-fa94-11e6-b701-b31dc35d1050', 'Sword Art Online vol. 03', 'product_sao_03', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'),
  (SELECT uuid FROM CATEGORY WHERE code ='fantasy_manga' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog')),
  (SELECT uuid FROM UNIT WHERE code ='pieces' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'))
);

INSERT INTO PRODUCT(uuid, name, code, creationtime, modificationtime, catalog_uuid, main_category_uuid, main_unit_uuid) VALUES (
  'a1f1016c-fa94-11e6-b704-cb129d9d0314', 'Sword Art Online vol. 04', 'product_sao_04', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'),
  (SELECT uuid FROM CATEGORY WHERE code ='fantasy_manga' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog')),
  (SELECT uuid FROM UNIT WHERE code ='pieces' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'))
);


-- Links - units  --

INSERT INTO PRODUCTS_UNITS_LINK(product_uuid, unit_uuid) VALUES(
  (SELECT uuid FROM PRODUCT WHERE code ='product_sao_01' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog')),
  (SELECT uuid FROM UNIT WHERE code ='pieces' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'))
);

INSERT INTO PRODUCTS_UNITS_LINK(product_uuid, unit_uuid) VALUES(
  (SELECT uuid FROM PRODUCT WHERE code ='product_sao_02' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog')),
  (SELECT uuid FROM UNIT WHERE code ='pieces' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'))
);

INSERT INTO PRODUCTS_UNITS_LINK(product_uuid, unit_uuid) VALUES(
  (SELECT uuid FROM PRODUCT WHERE code ='product_sao_03' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog')),
  (SELECT uuid FROM UNIT WHERE code ='pieces' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'))
);

INSERT INTO PRODUCTS_UNITS_LINK(product_uuid, unit_uuid) VALUES(
  (SELECT uuid FROM PRODUCT WHERE code ='product_sao_04' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog')),
  (SELECT uuid FROM UNIT WHERE code ='pieces' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'))
);

-- Link - categories --

INSERT INTO PRODUCTS_CATEGORIES_LINK(product_uuid, category_uuid) VALUES(
  (SELECT uuid FROM PRODUCT WHERE code ='product_sao_01' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog')),
  (SELECT uuid FROM CATEGORY WHERE code ='fantasy_manga' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'))
);

INSERT INTO PRODUCTS_CATEGORIES_LINK(product_uuid, category_uuid) VALUES(
  (SELECT uuid FROM PRODUCT WHERE code ='product_sao_02' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog')),
  (SELECT uuid FROM CATEGORY WHERE code ='fantasy_manga' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'))
);

INSERT INTO PRODUCTS_CATEGORIES_LINK(product_uuid, category_uuid) VALUES(
  (SELECT uuid FROM PRODUCT WHERE code ='product_sao_03' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog')),
  (SELECT uuid FROM CATEGORY WHERE code ='fantasy_manga' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'))
);

INSERT INTO PRODUCTS_CATEGORIES_LINK(product_uuid, category_uuid) VALUES(
  (SELECT uuid FROM PRODUCT WHERE code ='product_sao_04' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog')),
  (SELECT uuid FROM CATEGORY WHERE code ='fantasy_manga' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'))
);

