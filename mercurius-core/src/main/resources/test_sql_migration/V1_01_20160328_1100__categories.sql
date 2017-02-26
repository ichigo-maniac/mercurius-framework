CREATE TABLE CATEGORY(
  uuid CHARACTER VARYING(36) NOT NULL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL,
  code CHARACTER VARYING(255) NOT NULL,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL,
  catalog_uuid CHARACTER VARYING(36) NOT NULL REFERENCES SHOP_CATALOG(uuid),
  main_supercategory_uuid CHARACTER VARYING(36) REFERENCES CATEGORY(uuid),
  description_uuid CHARACTER VARYING(36) REFERENCES BIG_STRING(uuid)
);

CREATE TABLE CATEGORIES_SUPERCATEGORIES_LINK(
  category_uuid CHARACTER VARYING(36) NOT NULL REFERENCES CATEGORY(uuid),
  supercategory_uuid CHARACTER VARYING(36) NOT NULL REFERENCES CATEGORY(uuid)
);

-- Test data --

INSERT INTO CATEGORY(uuid, name, code, creationtime, modificationtime, catalog_uuid) VALUES (
  '4aa6bb9c-f065-11e6-9dad-9b2db2c47a0f', 'Catalog', 'main_category',
  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog')
);

INSERT INTO CATEGORY(uuid, name, code, creationtime, modificationtime, catalog_uuid, main_supercategory_uuid) VALUES (
  '4aa6e108-f065-11e6-9dae-774f2dfc5358', 'Blu-ray discs', 'bluray',
  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'),
  (SELECT uuid FROM CATEGORY WHERE code = 'main_category' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'))
);

INSERT INTO CATEGORY(uuid, name, code, creationtime, modificationtime, catalog_uuid, main_supercategory_uuid) VALUES (
  '4aa6fb20-f065-11e6-9daf-a334a56d0d4c', 'Manga', 'manga',
  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'),
  (SELECT uuid FROM CATEGORY WHERE code = 'main_category' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'))
);

INSERT INTO CATEGORY(uuid, name, code, creationtime, modificationtime, catalog_uuid, main_supercategory_uuid) VALUES (
  '1116fb20-f065-11e6-9daf-a334a56d0111', 'Sport manga', 'spokon_manga',
  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'),
  (SELECT uuid FROM CATEGORY WHERE code = 'manga' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'))
);

INSERT INTO CATEGORY(uuid, name, code, creationtime, modificationtime, catalog_uuid, main_supercategory_uuid) VALUES (
  '1116fb20-f065-11e6-9daf-a334a56d2222', 'Fantasy manga', 'fantasy_manga',
  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'),
  (SELECT uuid FROM CATEGORY WHERE code = 'manga' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'))
);

INSERT INTO CATEGORIES_SUPERCATEGORIES_LINK(CATEGORY_UUID, SUPERCATEGORY_UUID) VALUES
(
  (SELECT uuid FROM CATEGORY WHERE code = 'bluray' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog')),
  (SELECT uuid FROM CATEGORY WHERE code = 'main_category' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'))
);

INSERT INTO CATEGORIES_SUPERCATEGORIES_LINK(CATEGORY_UUID, SUPERCATEGORY_UUID) VALUES
(
  (SELECT uuid FROM CATEGORY WHERE code = 'manga' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog')),
  (SELECT uuid FROM CATEGORY WHERE code = 'main_category' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'))
);

INSERT INTO CATEGORIES_SUPERCATEGORIES_LINK(CATEGORY_UUID, SUPERCATEGORY_UUID) VALUES
(
  (SELECT uuid FROM CATEGORY WHERE code = 'spokon_manga' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog')),
  (SELECT uuid FROM CATEGORY WHERE code = 'manga' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'))
);

INSERT INTO CATEGORIES_SUPERCATEGORIES_LINK(CATEGORY_UUID, SUPERCATEGORY_UUID) VALUES
(
  (SELECT uuid FROM CATEGORY WHERE code = 'fantasy_manga' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog')),
  (SELECT uuid FROM CATEGORY WHERE code = 'manga' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog'))
);