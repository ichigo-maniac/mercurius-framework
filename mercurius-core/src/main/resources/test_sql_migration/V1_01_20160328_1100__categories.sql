CREATE TABLE CATEGORIES(
  uuid CHARACTER VARYING(36) NOT NULL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL,
  code CHARACTER VARYING(255) NOT NULL,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL,
  catalog_uuid CHARACTER VARYING(36) NOT NULL REFERENCES SHOP_CATALOGS(uuid) ON DELETE RESTRICT,
  main_supercategory_uuid CHARACTER VARYING(36) REFERENCES CATEGORIES(uuid) ON DELETE SET NULL,
  description_uuid CHARACTER VARYING(36) REFERENCES BIG_STRINGS(uuid) ON DELETE SET NULL
);

CREATE TABLE CATEGORIES_SUPERCATEGORIES_LINKS(
  category_uuid CHARACTER VARYING(36) NOT NULL REFERENCES CATEGORIES(uuid),
  supercategory_uuid CHARACTER VARYING(36) NOT NULL REFERENCES CATEGORIES(uuid)
);

-- Test data --

INSERT INTO CATEGORIES(uuid, name, code, creationtime, modificationtime, catalog_uuid) VALUES (
  '4aa6bb9c-f065-11e6-9dad-9b2db2c47a0f', 'Catalog', 'main_category',
  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')
);

INSERT INTO CATEGORIES(uuid, name, code, creationtime, modificationtime, catalog_uuid, main_supercategory_uuid) VALUES (
  '4aa6e108-f065-11e6-9dae-774f2dfc5358', 'Blu-ray discs', 'bluray',
  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'),
  (SELECT uuid FROM CATEGORIES WHERE code = 'main_category' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'))
);

INSERT INTO CATEGORIES(uuid, name, code, creationtime, modificationtime, catalog_uuid, main_supercategory_uuid) VALUES (
  '4aa6fb20-f065-11e6-9daf-a334a56d0d4c', 'Manga', 'manga',
  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'),
  (SELECT uuid FROM CATEGORIES WHERE code = 'main_category' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'))
);

INSERT INTO CATEGORIES(uuid, name, code, creationtime, modificationtime, catalog_uuid, main_supercategory_uuid) VALUES (
  '1116fb20-f065-11e6-9daf-a334a56d0111', 'Sport manga', 'spokon_manga',
  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'),
  (SELECT uuid FROM CATEGORIES WHERE code = 'manga' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'))
);

INSERT INTO BIG_STRINGS(uuid, name, code, creationtime, modificationtime, catalog_uuid, text_value) VALUES (
  '1116fb20-f065-11e6-9daf-a334a56d3323', 'Fantasy manga description', 'fantasy_manga_description', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'),
  'Test description - category'
);

INSERT INTO CATEGORIES(uuid, name, code, creationtime, modificationtime, catalog_uuid, main_supercategory_uuid, description_uuid) VALUES (
  '1116fb20-f065-11e6-9daf-a334a56d2222', 'Fantasy manga', 'fantasy_manga',
  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'),
  (SELECT uuid FROM CATEGORIES WHERE code = 'manga' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM BIG_STRINGS WHERE code ='fantasy_manga_description' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'))
);

INSERT INTO CATEGORIES_SUPERCATEGORIES_LINKS(CATEGORY_UUID, SUPERCATEGORY_UUID) VALUES
(
  (SELECT uuid FROM CATEGORIES WHERE code = 'bluray' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM CATEGORIES WHERE code = 'main_category' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'))
);

INSERT INTO CATEGORIES_SUPERCATEGORIES_LINKS(CATEGORY_UUID, SUPERCATEGORY_UUID) VALUES
(
  (SELECT uuid FROM CATEGORIES WHERE code = 'manga' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM CATEGORIES WHERE code = 'main_category' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'))
);

INSERT INTO CATEGORIES_SUPERCATEGORIES_LINKS(CATEGORY_UUID, SUPERCATEGORY_UUID) VALUES
(
  (SELECT uuid FROM CATEGORIES WHERE code = 'spokon_manga' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM CATEGORIES WHERE code = 'manga' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'))
);

INSERT INTO CATEGORIES_SUPERCATEGORIES_LINKS(CATEGORY_UUID, SUPERCATEGORY_UUID) VALUES
(
  (SELECT uuid FROM CATEGORIES WHERE code = 'fantasy_manga' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')),
  (SELECT uuid FROM CATEGORIES WHERE code = 'manga' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'))
);