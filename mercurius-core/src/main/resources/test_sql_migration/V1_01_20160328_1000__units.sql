CREATE TABLE UNITS(
  uuid CHARACTER VARYING(36) NOT NULL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL,
  code CHARACTER VARYING(255) NOT NULL,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL,
  catalog_uuid CHARACTER VARYING(36) NOT NULL REFERENCES SHOP_CATALOGS(uuid) ON DELETE RESTRICT
);

INSERT INTO UNITS(uuid, name, code, creationtime, modificationtime, catalog_uuid) VALUES(
  'a1e2ae50-fa94-11e6-b6f6-67b357732118', 'Pieces', 'pieces', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')
);

INSERT INTO UNITS(uuid, name, code, creationtime, modificationtime, catalog_uuid) VALUES(
  '55e2ae50-fa94-11e6-abcd-67b357732118', 'Barrels', 'barrels', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')
);

INSERT INTO UNITS(uuid, name, code, creationtime, modificationtime, catalog_uuid) VALUES(
  '6662ae50-fa94-6666-b6f6-67b35773266', 'Boxes', 'boxes', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog')
);