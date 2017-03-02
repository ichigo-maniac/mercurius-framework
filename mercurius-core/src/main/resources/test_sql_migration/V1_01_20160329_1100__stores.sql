CREATE TABLE STORE(
  uuid CHARACTER VARYING(36) NOT NULL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL,
  code CHARACTER VARYING(255) NOT NULL UNIQUE,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL,
  disabled BOOLEAN
);

INSERT INTO STORE(uuid, name, code, creationtime, modificationtime, disabled) VALUES (
  '12345e10-fa94-11e6-b6ff-bf2400ed1234', 'Default store', 'default_store', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE
);

INSERT INTO STORE(uuid, name, code, creationtime, modificationtime, disabled) VALUES (
  '12345555-fa94-11e6-b6ff-bf2400ed5555', 'First disabled store', 'first_disabled_store', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE
);

INSERT INTO STORE(uuid, name, code, creationtime, modificationtime, disabled) VALUES (
  '11115555-fa94-11e6-b6ff-bf2400ed1234', 'Second disabled store', 'second_disabled_store', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE
);

CREATE TABLE WAREHOUSE(
  uuid CHARACTER VARYING(36) NOT NULL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL,
  code CHARACTER VARYING(255) NOT NULL UNIQUE,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL,
  disabled BOOLEAN,
  store_uuid CHARACTER VARYING(36) NOT NULL REFERENCES STORE(uuid)
);

INSERT INTO WAREHOUSE(uuid, name, code, creationtime, modificationtime, disabled, store_uuid) VALUES (
  '12345e10-fa94-22e6-b6ff-abd400ed1234', 'Warehouse 1', 'warehouse_1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE,
  (SELECT uuid FROM STORE WHERE code = 'default_store')
);

INSERT INTO WAREHOUSE(uuid, name, code, creationtime, modificationtime, disabled, store_uuid) VALUES (
  '12345e10-fa94-22e6-b6ff-abd400ed2222', 'Warehouse 2', 'warehouse_2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE,
  (SELECT uuid FROM STORE WHERE code = 'default_store')
);

INSERT INTO WAREHOUSE(uuid, name, code, creationtime, modificationtime, disabled, store_uuid) VALUES (
  '12345e10-fa94-22e6-b6ff-abd400ed3333', 'Warehouse 3', 'warehouse_3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE,
  (SELECT uuid FROM STORE WHERE code = 'default_store')
);

CREATE TABLE STOCK(
  uuid CHARACTER VARYING(36) NOT NULL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL,
  code CHARACTER VARYING(255) NOT NULL UNIQUE,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL,
  warehouse_uuid CHARACTER VARYING(36) NOT NULL REFERENCES WAREHOUSE(uuid),
  product_uuid CHARACTER VARYING(36) NOT NULL REFERENCES PRODUCT(uuid),
  unit_uuid CHARACTER VARYING(36) NOT NULL REFERENCES UNIT(uuid)
);
