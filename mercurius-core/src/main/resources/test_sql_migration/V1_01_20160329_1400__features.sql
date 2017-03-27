CREATE TABLE FEATURE(
  uuid CHARACTER VARYING(36) NOT NULL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL,
  code CHARACTER VARYING(255) NOT NULL,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL,
  catalog_uuid CHARACTER VARYING(36) NOT NULL REFERENCES SHOP_CATALOG(uuid) ON DELETE RESTRICT,
  enumClass CHARACTER VARYING(255),
  featureType CHARACTER VARYING(255) NOT NULL
);

INSERT INTO FEATURE(uuid, name, code, creationtime, modificationtime, catalog_uuid, enumClass, featureType) VALUES (
  'b1f05e10-5522-11e6-4221-bf2400ed613a', 'Pages count', 'book_pages_feature', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'), null, 'NUMERIC_TYPE'
);

INSERT INTO FEATURE(uuid, name, code, creationtime, modificationtime, catalog_uuid, enumClass, featureType) VALUES (
  'b1f05e10-5522-5311-4221-bf2400ed613a', 'Cover type', 'book_cover_type_feature', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'), null, 'STRING_TYPE'
);

INSERT INTO FEATURE(uuid, name, code, creationtime, modificationtime, catalog_uuid, enumClass, featureType) VALUES (
  'b1001250-5522-5311-4221-bf2400ed613a', 'Weight', 'book_weight', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'), null, 'FLOAT_NUMERIC_TYPE'
);

INSERT INTO FEATURE(uuid, name, code, creationtime, modificationtime, catalog_uuid, enumClass, featureType) VALUES (
  'b1001250-1111-5311-0012-bf2400ed613a', 'For adult', 'book_for_adult', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'), null, 'BOOLEAN_TYPE'
);

CREATE TABLE FEATURE_VALUE(
  uuid CHARACTER VARYING(36) NOT NULL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL,
  code CHARACTER VARYING(255) NOT NULL,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL,
  catalog_uuid CHARACTER VARYING(36) NOT NULL REFERENCES SHOP_CATALOG(uuid) ON DELETE RESTRICT,
  product_uuid CHARACTER VARYING(36) NOT NULL REFERENCES PRODUCT(uuid),
  feature_uuid CHARACTER VARYING(36) NOT NULL REFERENCES FEATURE(uuid),
  featureValue CHARACTER VARYING(255) NOT NULL,
  groupName CHARACTER VARYING(255) NOT NULL
);

INSERT INTO FEATURE_VALUE(uuid, name, code, creationtime, modificationtime, catalog_uuid, feature_uuid, product_uuid, featureValue, groupName) VALUES (
  '12345e10-1a94-22e6-0000-abd000223330', 'Product sao 01 - pages_count', 'product_sao_01_pages_count', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'),
  (SELECT uuid FROM FEATURE WHERE code ='book_pages_feature' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog')),
  (SELECT uuid FROM PRODUCT WHERE code ='product_sao_01' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog')),
  'NUMERIC_TYPE:::256', 'Common'
);

INSERT INTO FEATURE_VALUE(uuid, name, code, creationtime, modificationtime, catalog_uuid, feature_uuid, product_uuid, featureValue, groupName) VALUES (
  '12345e10-1a94-22e6-0000-777000223337', 'Product sao 01 - weight', 'product_sao_01_weight', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'),
  (SELECT uuid FROM FEATURE WHERE code ='book_weight' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog')),
  (SELECT uuid FROM PRODUCT WHERE code ='product_sao_01' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog')),
  'FLOAT_NUMERIC_TYPE:::1.5', 'Common'
);

INSERT INTO FEATURE_VALUE(uuid, name, code, creationtime, modificationtime, catalog_uuid, feature_uuid, product_uuid, featureValue, groupName) VALUES (
  '00045e10-1a94-22e6-0000-abd000223111', 'Product sao 01 - covert_type', 'product_sao_01_cover_type', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'),
  (SELECT uuid FROM FEATURE WHERE code ='book_cover_type_feature' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog')),
  (SELECT uuid FROM PRODUCT WHERE code ='product_sao_01' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog')),
  'STRING_TYPE:::Paperback', 'Common'
);

INSERT INTO FEATURE_VALUE(uuid, name, code, creationtime, modificationtime, catalog_uuid, feature_uuid, product_uuid, featureValue, groupName) VALUES (
  '11115e0-1a94-22e6-0000-ccbd000223111', 'Product sao 01 - for adult', 'product_sao_01_for_adult', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'),
  (SELECT uuid FROM FEATURE WHERE code ='book_for_adult' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog')),
  (SELECT uuid FROM PRODUCT WHERE code ='product_sao_01' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog')),
  'BOOLEAN_TYPE:::FALSE', 'Common'
);