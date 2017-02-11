CREATE TABLE SHOP_CATALOG(
  uuid CHARACTER VARYING(36) NOT NULL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL,
  code CHARACTER VARYING(255) NOT NULL UNIQUE,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL
);

INSERT INTO SHOP_CATALOG(uuid, name, code, creationtime, modificationtime) VALUES (
  uuid_generate_v1(), 'Master catalog', 'master_catalog', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
);