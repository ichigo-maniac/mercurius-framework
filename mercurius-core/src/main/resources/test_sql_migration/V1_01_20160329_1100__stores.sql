CREATE TABLE CURRENCIES(
  uuid CHARACTER VARYING(36) NOT NULL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL,
  code CHARACTER VARYING(255) NOT NULL UNIQUE,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL,
  symbol CHARACTER VARYING(255) NOT NULL
);

INSERT INTO CURRENCIES(uuid, name, code, creationtime, modificationtime, symbol) VALUES (
  '4a9b636e-f065-11e6-1123-0000def2f3a6', 'Russia ruble', 'rub', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'R'
);
INSERT INTO CURRENCIES(uuid, name, code, creationtime, modificationtime, symbol) VALUES (
  '4a9b636e-2222-11e6-7584-0000def2fa9b', 'Dollar USA', 'usd', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '$'
);

CREATE TABLE STORES(
  uuid CHARACTER VARYING(36) NOT NULL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL,
  code CHARACTER VARYING(255) NOT NULL UNIQUE,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL,
  enabled BOOLEAN,
  default_currency_uuid CHARACTER VARYING(36) REFERENCES CURRENCIES(uuid)
);

CREATE TABLE STORES_CURRENCIES_LINKS(
  store_uuid CHARACTER VARYING(36) NOT NULL REFERENCES STORES(uuid),
  currency_uuid CHARACTER VARYING(36) NOT NULL REFERENCES CURRENCIES(uuid)
);

INSERT INTO STORES(uuid, name, code, creationtime, modificationtime, enabled) VALUES (
  '12345e10-fa94-11e6-b6ff-bf2400ed1234', 'Default store', 'default_store', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE
);

INSERT INTO STORES(uuid, name, code, creationtime, modificationtime, enabled) VALUES (
  '12345555-fa94-11e6-b6ff-bf2400ed5555', 'First disabled store', 'first_disabled_store', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE
);

INSERT INTO STORES(uuid, name, code, creationtime, modificationtime, enabled) VALUES (
  '11115555-fa94-11e6-b6ff-bf2400ed1234', 'Second disabled store', 'second_disabled_store', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE
);

CREATE TABLE WAREHOUSES(
  uuid CHARACTER VARYING(36) NOT NULL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL,
  code CHARACTER VARYING(255) NOT NULL UNIQUE,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL,
  enabled BOOLEAN,
  store_uuid CHARACTER VARYING(36) NOT NULL REFERENCES STORES(uuid)
);

INSERT INTO WAREHOUSES(uuid, name, code, creationtime, modificationtime, enabled, store_uuid) VALUES (
  '12345e10-fa94-22e6-b6ff-abd400ed1234', 'Warehouse 1', 'warehouse_1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE ,
  (SELECT uuid FROM STORES WHERE code = 'default_store')
);

INSERT INTO WAREHOUSES(uuid, name, code, creationtime, modificationtime, enabled, store_uuid) VALUES (
  '12345e10-fa94-22e6-b6ff-abd400ed2222', 'Warehouse 2', 'warehouse_2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE ,
  (SELECT uuid FROM STORES WHERE code = 'default_store')
);

INSERT INTO WAREHOUSES(uuid, name, code, creationtime, modificationtime, enabled, store_uuid) VALUES (
  '12345e10-fa94-22e6-b6ff-abd400ed3333', 'Warehouse 3', 'warehouse_3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE ,
  (SELECT uuid FROM STORES WHERE code = 'default_store')
);

CREATE TABLE STOCKS(
  uuid CHARACTER VARYING(36) NOT NULL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL,
  code CHARACTER VARYING(255) NOT NULL UNIQUE,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL,
  enabled BOOLEAN,
  product_count BIGINT NOT NULL,
  warehouse_uuid CHARACTER VARYING(36) NOT NULL REFERENCES WAREHOUSES(uuid),
  product_uuid CHARACTER VARYING(36) NOT NULL REFERENCES PRODUCTS(uuid),
  unit_uuid CHARACTER VARYING(36) NOT NULL REFERENCES UNITS(uuid)
);

INSERT INTO STOCKS(uuid, name, code, creationtime, modificationtime, product_count, warehouse_uuid, product_uuid, unit_uuid) VALUES (
  '12345e10-1a94-22e6-b6ff-abd422223333', 'Prince of tennis 01 - pieces', 'stock_prince_of_tennis_01_pieces', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 15,
  (SELECT uuid FROM WAREHOUSES WHERE code = 'warehouse_1'),
  (SELECT uuid FROM PRODUCTS WHERE code ='product_prince_of_tennis_01' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM UNITS WHERE code ='pieces' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'))
);

INSERT INTO STOCKS(uuid, name, code, creationtime, modificationtime, product_count, warehouse_uuid, product_uuid, unit_uuid) VALUES (
  '12345e10-1594-22e6-b6ff-abd422555333', 'Prince of tennis 01 - boxes', 'stock_prince_of_tennis_01_boxes', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4,
  (SELECT uuid FROM WAREHOUSES WHERE code = 'warehouse_1'),
  (SELECT uuid FROM PRODUCTS WHERE code ='product_prince_of_tennis_01' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM UNITS WHERE code ='boxes' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'))
);

INSERT INTO STOCKS(uuid, name, code, creationtime, modificationtime, enabled, product_count, warehouse_uuid, product_uuid, unit_uuid) VALUES (
  '12345e10-0000-22e6-4444-abd422555333', 'Prince of tennis 01 - boxes', 'stock_prince_of_tennis_01_boxes_d', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false, 4,
  (SELECT uuid FROM WAREHOUSES WHERE code = 'warehouse_1'),
  (SELECT uuid FROM PRODUCTS WHERE code ='product_prince_of_tennis_01' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM UNITS WHERE code ='boxes' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'))
);

INSERT INTO STOCKS(uuid, name, code, creationtime, modificationtime, product_count, warehouse_uuid, product_uuid, unit_uuid) VALUES (
  '12345e10-0000-22e6-b6ff-abd422223333', 'Prince of tennis 01 - pieces', 'stock_prince_of_tennis_01_pieces - 2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 7,
  (SELECT uuid FROM WAREHOUSES WHERE code = 'warehouse_3'),
  (SELECT uuid FROM PRODUCTS WHERE code ='product_prince_of_tennis_01' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM UNITS WHERE code ='pieces' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'))
);
