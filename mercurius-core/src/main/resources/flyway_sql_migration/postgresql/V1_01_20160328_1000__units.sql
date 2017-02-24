CREATE TABLE UNIT(
  uuid CHARACTER VARYING(36) NOT NULL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL,
  code CHARACTER VARYING(255) NOT NULL,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL,
  catalog_uuid CHARACTER VARYING(36) NOT NULL REFERENCES SHOP_CATALOG(uuid)
);

INSERT INTO UNIT(uuid, name, code, creationtime, modificationtime, catalog_uuid) VALUES(
  uuid_generate_v1(), 'Pieces', 'pieces', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, (SELECT uuid FROM SHOP_CATALOG WHERE code = 'master_catalog')
);
