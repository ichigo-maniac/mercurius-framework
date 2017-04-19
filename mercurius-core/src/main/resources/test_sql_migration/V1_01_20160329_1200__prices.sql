CREATE TABLE PRICES(
  uuid CHARACTER VARYING(36) NOT NULL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL,
  code CHARACTER VARYING(255) NOT NULL,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL,
  catalog_uuid CHARACTER VARYING(36) NOT NULL REFERENCES SHOP_CATALOGS(uuid) ON DELETE RESTRICT,
  unit_uuid CHARACTER VARYING(36) NOT NULL REFERENCES UNITS(uuid),
  product_uuid CHARACTER VARYING(36) NOT NULL REFERENCES PRODUCTS(uuid),
  currency_uuid CHARACTER VARYING(36) NOT NULL REFERENCES CURRENCIES(uuid),
  priceValue DOUBLE NOT NULL
);

INSERT INTO PRICES(uuid, name, code, creationtime, modificationtime, priceValue, catalog_uuid, product_uuid, unit_uuid, currency_uuid) VALUES (
  'a7478e10-fa94-11e6-b6ff-bf2400ed613a', 'Sword Art Online vol. 01 - price', 'product_sao_01_price_pieces', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 500.0,
  (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'),
  (SELECT uuid FROM PRODUCTS WHERE code ='product_sao_01' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM UNITS WHERE code ='pieces' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM CURRENCIES WHERE code = 'rub')
);

INSERT INTO PRICES(uuid, name, code, creationtime, modificationtime, priceValue, catalog_uuid, product_uuid, unit_uuid, currency_uuid) VALUES (
  'a7433e10-004-11ae6-b6ff-bf2400ed613a', 'Sword Art Online vol. 02 - price', 'product_sao_02_price_pieces', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 500.0,
  (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'),
  (SELECT uuid FROM PRODUCTS WHERE code ='product_sao_02' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM UNITS WHERE code ='pieces' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM CURRENCIES WHERE code = 'rub')
);

INSERT INTO PRICES(uuid, name, code, creationtime, modificationtime, priceValue, catalog_uuid, product_uuid, unit_uuid, currency_uuid) VALUES (
  'a7478e10-5555-11e6-b6ff-bf7350ed613a', 'Sword Art Online vol. 03 - price', 'product_sao_03_price_pieces', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 500.0,
  (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'),
  (SELECT uuid FROM PRODUCTS WHERE code ='product_sao_03' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM UNITS WHERE code ='pieces' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM CURRENCIES WHERE code = 'rub')
);

INSERT INTO PRICES(uuid, name, code, creationtime, modificationtime, priceValue, catalog_uuid, product_uuid, unit_uuid, currency_uuid) VALUES (
  'a7478e10-2345-11e6-5372-bf2400ed613a', 'Sword Art Online vol. 04 - price', 'product_sao_04_price_pieces', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 550.0,
  (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'),
  (SELECT uuid FROM PRODUCTS WHERE code ='product_sao_04' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM UNITS WHERE code ='pieces' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM CURRENCIES WHERE code = 'rub')
);

INSERT INTO PRICES(uuid, name, code, creationtime, modificationtime, priceValue, catalog_uuid, product_uuid, unit_uuid, currency_uuid) VALUES (
  'a7478e10-fa94-0045-5372-bf2400ed613a', 'Sword Art Online vol. 04 - price', 'product_sao_04_price_boxes', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 550.0,
  (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'),
  (SELECT uuid FROM PRODUCTS WHERE code ='product_sao_04' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM UNITS WHERE code ='boxes' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM CURRENCIES WHERE code = 'rub')
);

INSERT INTO PRICES(uuid, name, code, creationtime, modificationtime, priceValue, catalog_uuid, product_uuid, unit_uuid, currency_uuid) VALUES (
  '07478e10-fa94-11e6-5372-bf2111ed613u', 'Sword Art Online vol. 04 - price', 'product_sao_04_price_pieces_usd', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 10.0,
  (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'),
  (SELECT uuid FROM PRODUCTS WHERE code ='product_sao_04' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM UNITS WHERE code ='pieces' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM CURRENCIES WHERE code = 'usd')
);

INSERT INTO PRICES(uuid, name, code, creationtime, modificationtime, priceValue, catalog_uuid, product_uuid, unit_uuid, currency_uuid) VALUES (
  'a7478e10-7423-11e6-5372-bf240073725a', 'Sword Art Online vol. 04 - price', 'product_sao_04_price_boxes_usd', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 10.0,
  (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'),
  (SELECT uuid FROM PRODUCTS WHERE code ='product_sao_04' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM UNITS WHERE code ='boxes' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM CURRENCIES WHERE code = 'usd')
);