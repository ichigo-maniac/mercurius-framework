CREATE TABLE SHOP_CATALOG(
  uuid VARCHAR(36) NOT NULL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  code VARCHAR(255) NOT NULL UNIQUE,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL
);

INSERT INTO SHOP_CATALOG(uuid, name, code, creationtime, modificationtime) VALUES (
  UUID(), 'Master catalog', 'master', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
);
INSERT INTO SHOP_CATALOG(uuid, name, code, creationtime, modificationtime) VALUES (
  UUID(), 'Online catalog', 'online', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
);

