CREATE TABLE DICTIONARY_TYPES(
  uuid CHARACTER VARYING(36) NOT NULL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL,
  code CHARACTER VARYING(255) NOT NULL UNIQUE,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL
);

CREATE TABLE DICTIONARY_ITEMS(
  uuid CHARACTER VARYING(36) NOT NULL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL,
  code CHARACTER VARYING(255) NOT NULL UNIQUE,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL,
  dictionary_type_uuid CHARACTER VARYING(36) NOT NULL REFERENCES DICTIONARY_TYPES(uuid)
);

INSERT INTO DICTIONARY_TYPES(uuid, name, code, creationtime, modificationtime) VALUES (
  '4a9b636e-f065-11e6-4444-836adef2f3a6', 'Country', 'dictionary_country', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
);

INSERT INTO DICTIONARY_ITEMS(uuid, name, code, creationtime, modificationtime, dictionary_type_uuid) VALUES (
  '2222636e-f065-11e6-4444-836adef2f3a6', 'Japan', 'country_japan', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  (SELECT uuid FROM DICTIONARY_TYPES WHERE code = 'dictionary_country')
);

INSERT INTO DICTIONARY_ITEMS(uuid, name, code, creationtime, modificationtime, dictionary_type_uuid) VALUES (
  '4a9b636e-f065-11e6-4444-836111f2f3a6', 'USA', 'country_usa', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  (SELECT uuid FROM DICTIONARY_TYPES WHERE code = 'dictionary_country')
);

CREATE TABLE FEATURES(
  uuid CHARACTER VARYING(36) NOT NULL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL,
  code CHARACTER VARYING(255) NOT NULL,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL,
  catalog_uuid CHARACTER VARYING(36) NOT NULL REFERENCES SHOP_CATALOGS(uuid) ON DELETE RESTRICT,
  featureType CHARACTER VARYING(255) NOT NULL,
  dictionary_type_uuid CHARACTER VARYING(36) REFERENCES DICTIONARY_TYPES(uuid),
  includeInIndex BOOLEAN,
  solrDocumentFieldName CHARACTER VARYING(255)
);

INSERT INTO FEATURES(uuid, name, code, creationtime, modificationtime, catalog_uuid, featureType, includeInIndex, solrDocumentFieldName) VALUES (
  'b1f05e10-5522-11e6-4221-bf2400ed613a', 'Pages count', 'book_pages_feature', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'), 'NUMERIC_TYPE', true, 'page_size'
);

INSERT INTO FEATURES(uuid, name, code, creationtime, modificationtime, catalog_uuid, featureType) VALUES (
  'b1f05e10-5522-5311-4221-bf2400ed613a', 'Cover type', 'book_cover_type_feature', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'), 'STRING_TYPE'
);

INSERT INTO FEATURES(uuid, name, code, creationtime, modificationtime, catalog_uuid, featureType) VALUES (
  'b1001250-5522-5311-4221-bf2400ed613a', 'Weight', 'book_weight', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'), 'FLOAT_NUMERIC_TYPE'
);

INSERT INTO FEATURES(uuid, name, code, creationtime, modificationtime, catalog_uuid, featureType) VALUES (
  'b1001250-1111-5311-0012-bf2400ed613a', 'For adult', 'book_for_adult', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'), 'BOOLEAN_TYPE'
);

INSERT INTO FEATURES(uuid, name, code, creationtime, modificationtime, solrDocumentFieldName, catalog_uuid, featureType, dictionary_type_uuid) VALUES (
  '55501250-1111-5311-2252-bf2400ed613a', 'Country', 'country', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'country',
  (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'), 'DICTIONARY_TYPE',
  (SELECT uuid FROM DICTIONARY_TYPES WHERE code = 'dictionary_country')
);

CREATE TABLE FEATURE_VALUES(
  uuid CHARACTER VARYING(36) NOT NULL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL,
  code CHARACTER VARYING(255) NOT NULL,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL,
  catalog_uuid CHARACTER VARYING(36) NOT NULL REFERENCES SHOP_CATALOGS(uuid) ON DELETE RESTRICT,
  product_uuid CHARACTER VARYING(36) NOT NULL REFERENCES PRODUCTS(uuid),
  feature_uuid CHARACTER VARYING(36) NOT NULL REFERENCES FEATURES(uuid),
  featureValue CHARACTER VARYING(255) NOT NULL,
  groupName CHARACTER VARYING(255) NOT NULL
);

INSERT INTO FEATURE_VALUES(uuid, name, code, creationtime, modificationtime, catalog_uuid, feature_uuid, product_uuid, featureValue, groupName) VALUES (
  '12345e10-1a94-22e6-0000-abd000223330', 'Product sao 01 - pages_count', 'product_sao_01_pages_count', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'),
  (SELECT uuid FROM FEATURES WHERE code ='book_pages_feature' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM PRODUCTS WHERE code ='product_sao_01' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  'NUMERIC_TYPE::256', 'Common'
);

INSERT INTO FEATURE_VALUES(uuid, name, code, creationtime, modificationtime, catalog_uuid, feature_uuid, product_uuid, featureValue, groupName) VALUES (
  '12345e10-1a94-22e6-0000-777000223337', 'Product sao 01 - weight', 'product_sao_01_weight', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'),
  (SELECT uuid FROM FEATURES WHERE code ='book_weight' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM PRODUCTS WHERE code ='product_sao_01' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  'FLOAT_NUMERIC_TYPE::1.5', 'Common'
);

INSERT INTO FEATURE_VALUES(uuid, name, code, creationtime, modificationtime, catalog_uuid, feature_uuid, product_uuid, featureValue, groupName) VALUES (
  '00045e10-1a94-22e6-0000-abd000223111', 'Product sao 01 - covert_type', 'product_sao_01_cover_type', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'),
  (SELECT uuid FROM FEATURES WHERE code ='book_cover_type_feature' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM PRODUCTS WHERE code ='product_sao_01' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  'STRING_TYPE::Paperback', 'Common'
);

INSERT INTO FEATURE_VALUES(uuid, name, code, creationtime, modificationtime, catalog_uuid, feature_uuid, product_uuid, featureValue, groupName) VALUES (
  '11115e0-1a94-22e6-0000-ccbd000223111', 'Product sao 01 - for adult', 'product_sao_01_for_adult', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'),
  (SELECT uuid FROM FEATURES WHERE code ='book_for_adult' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM PRODUCTS WHERE code ='product_sao_01' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  'BOOLEAN_TYPE::FALSE', 'Common'
);

INSERT INTO FEATURE_VALUES(uuid, name, code, creationtime, modificationtime, catalog_uuid, feature_uuid, product_uuid, featureValue, groupName) VALUES (
  '11115e0-1a94-22e6-53a5-ccbd000223111', 'Product sao 01 - country', 'product_sao_01_country', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'),
  (SELECT uuid FROM FEATURES WHERE code ='country' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM PRODUCTS WHERE code ='product_sao_01' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  'DICTIONARY_TYPE::country_japan', 'Common'
);
