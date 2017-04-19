CREATE TABLE PRODUCTS(
  uuid CHARACTER VARYING(36) NOT NULL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL,
  code CHARACTER VARYING(255) NOT NULL,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL,
  shortName CHARACTER VARYING(255),
  main_unit_uuid CHARACTER VARYING(36) REFERENCES UNITS(uuid) ON DELETE SET NULL,
  main_category_uuid CHARACTER VARYING(36) REFERENCES CATEGORIES(uuid) ON DELETE SET NULL,
  catalog_uuid CHARACTER VARYING(36) NOT NULL REFERENCES SHOP_CATALOGS(uuid) ON DELETE RESTRICT,
  description_uuid CHARACTER VARYING(36) REFERENCES BIG_STRINGS(uuid) ON DELETE SET NULL
);

CREATE TABLE PRODUCTS_UNITS_LINKS(
  product_uuid CHARACTER VARYING(36) NOT NULL REFERENCES PRODUCTS(uuid),
  unit_uuid CHARACTER VARYING(36) NOT NULL REFERENCES UNITS(uuid)
);

CREATE TABLE PRODUCTS_CATEGORIES_LINKS(
  product_uuid CHARACTER VARYING(36) NOT NULL REFERENCES PRODUCTS(uuid),
  category_uuid CHARACTER VARYING(36) NOT NULL REFERENCES CATEGORIES(uuid)
);

-- Products --

INSERT INTO PRODUCTS(uuid, name, code, creationtime, modificationtime, catalog_uuid, main_category_uuid, main_unit_uuid) VALUES (
  'a1f05e10-fa94-11e6-b6ff-bf2400ed613a', 'Sword Art Online vol. 01', 'product_sao_01', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'),
  (SELECT uuid FROM CATEGORIES WHERE code ='fantasy_manga' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM UNITS WHERE code ='pieces' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'))
);

INSERT INTO PRODUCTS(uuid, name, code, creationtime, modificationtime, catalog_uuid, main_category_uuid, main_unit_uuid) VALUES (
  'a1f0970e-fa94-11e6-b700-c3c4039ff350', 'Sword Art Online vol. 02', 'product_sao_02', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'),
  (SELECT uuid FROM CATEGORIES WHERE code ='fantasy_manga' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM UNITS WHERE code ='pieces' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'))
);

INSERT INTO PRODUCTS(uuid, name, code, creationtime, modificationtime, catalog_uuid, main_category_uuid, main_unit_uuid) VALUES (
  'a1f0b496-fa94-11e6-b701-b31dc35d1050', 'Sword Art Online vol. 03', 'product_sao_03', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'),
  (SELECT uuid FROM CATEGORIES WHERE code ='fantasy_manga' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM UNITS WHERE code ='pieces' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'))
);

INSERT INTO BIG_STRINGS(uuid, name, code, creationtime, modificationtime, catalog_uuid, text_value) VALUES (
  '1116fb20-f065-11e6-9daf-a334a56d4444', 'Sword Art Online vol. 04 description', 'product_sao_04_description', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'),
  'Test description - product'
);

INSERT INTO PRODUCTS(uuid, name, shortName, code, creationtime, modificationtime, catalog_uuid, main_category_uuid, main_unit_uuid, description_uuid) VALUES (
  'a1f1016c-fa94-11e6-b704-cb129d9d0314', 'Sword Art Online vol. 04', 'Reki Kawahara', 'product_sao_04', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'),
  (SELECT uuid FROM CATEGORIES WHERE code ='fantasy_manga' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM UNITS WHERE code ='pieces' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM BIG_STRINGS WHERE code ='product_sao_04_description' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'))
);

INSERT INTO PRODUCTS(uuid, name, code, creationtime, modificationtime, catalog_uuid, main_category_uuid, main_unit_uuid) VALUES (
  'a1f0b496-fa94-11e6-b701-b31dc35d6666', 'Prince of tennis vol. 01', 'product_prince_of_tennis_01', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'),
  (SELECT uuid FROM CATEGORIES WHERE code ='spokon_manga' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM UNITS WHERE code ='pieces' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'))
);


-- Links - units  --

INSERT INTO PRODUCTS_UNITS_LINKS(product_uuid, unit_uuid) VALUES(
  (SELECT uuid FROM PRODUCTS WHERE code ='product_sao_01' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM UNITS WHERE code ='pieces' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'))
);

INSERT INTO PRODUCTS_UNITS_LINKS(product_uuid, unit_uuid) VALUES(
  (SELECT uuid FROM PRODUCTS WHERE code ='product_sao_02' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM UNITS WHERE code ='pieces' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'))
);

INSERT INTO PRODUCTS_UNITS_LINKS(product_uuid, unit_uuid) VALUES(
  (SELECT uuid FROM PRODUCTS WHERE code ='product_sao_03' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM UNITS WHERE code ='pieces' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'))
);

INSERT INTO PRODUCTS_UNITS_LINKS(product_uuid, unit_uuid) VALUES(
  (SELECT uuid FROM PRODUCTS WHERE code ='product_sao_04' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM UNITS WHERE code ='pieces' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'))
);

INSERT INTO PRODUCTS_UNITS_LINKS(product_uuid, unit_uuid) VALUES(
  (SELECT uuid FROM PRODUCTS WHERE code ='product_prince_of_tennis_01' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM UNITS WHERE code ='pieces' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'))
);

INSERT INTO PRODUCTS_UNITS_LINKS(product_uuid, unit_uuid) VALUES(
  (SELECT uuid FROM PRODUCTS WHERE code ='product_prince_of_tennis_01' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM UNITS WHERE code ='boxes' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'))
);

-- Link - categories --

INSERT INTO PRODUCTS_CATEGORIES_LINKS(product_uuid, category_uuid) VALUES(
  (SELECT uuid FROM PRODUCTS WHERE code ='product_sao_01' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM CATEGORIES WHERE code ='fantasy_manga' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'))
);

INSERT INTO PRODUCTS_CATEGORIES_LINKS(product_uuid, category_uuid) VALUES(
  (SELECT uuid FROM PRODUCTS WHERE code ='product_sao_02' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM CATEGORIES WHERE code ='fantasy_manga' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'))
);

INSERT INTO PRODUCTS_CATEGORIES_LINKS(product_uuid, category_uuid) VALUES(
  (SELECT uuid FROM PRODUCTS WHERE code ='product_sao_03' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM CATEGORIES WHERE code ='fantasy_manga' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'))
);

INSERT INTO PRODUCTS_CATEGORIES_LINKS(product_uuid, category_uuid) VALUES(
  (SELECT uuid FROM PRODUCTS WHERE code ='product_sao_04' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM CATEGORIES WHERE code ='fantasy_manga' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'))
);

INSERT INTO PRODUCTS_CATEGORIES_LINKS(product_uuid, category_uuid) VALUES(
  (SELECT uuid FROM PRODUCTS WHERE code ='product_prince_of_tennis_01' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM CATEGORIES WHERE code ='spokon_manga' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'))
);

