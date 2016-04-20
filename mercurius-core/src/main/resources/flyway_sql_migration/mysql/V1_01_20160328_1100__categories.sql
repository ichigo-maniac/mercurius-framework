CREATE TABLE CATEGORY(
  uuid VARCHAR(36) NOT NULL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  code VARCHAR(255) NOT NULL,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL,
  catalog_uuid VARCHAR(36) NOT NULL REFERENCES SHOP_CATALOG(uuid)
);