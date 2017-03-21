CREATE TABLE USERS(
  uuid CHARACTER VARYING(36) NOT NULL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL,
  code CHARACTER VARYING(255) NOT NULL UNIQUE,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL,
  email CHARACTER VARYING(255),
  password CHARACTER VARYING(255),
  user_type CHARACTER VARYING(255)
);

CREATE TABLE ROLES(
  uuid CHARACTER VARYING(36) NOT NULL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL,
  code CHARACTER VARYING(255) NOT NULL UNIQUE,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL
);

CREATE TABLE EMPLOYEE_ROLE_LINK(
  employee_uuid CHARACTER VARYING(36) NOT NULL REFERENCES USERS(uuid),
  role_uuid CHARACTER VARYING(36) NOT NULL REFERENCES ROLES(uuid)
);

INSERT INTO USERS(uuid, name, code, creationtime, modificationtime, email, password, user_type) VALUES (
  uuid_generate_v1(), 'Admin', 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin@mercurius.org', '123123', 'Employee'
);

INSERT INTO ROLES(uuid, name, code, creationtime, modificationtime) VALUES (
  uuid_generate_v1(), 'Admin role', 'ROLE_ADMIN', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
);
INSERT INTO ROLES(uuid, name, code, creationtime, modificationtime) VALUES (
  uuid_generate_v1(), 'Dummy role', 'ROLE_DUMMY', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
);

INSERT INTO EMPLOYEE_ROLE_LINK(employee_uuid, role_uuid) VALUES(
  (SELECT uuid FROM USERS WHERE code = 'admin'),
  (SELECT uuid FROM ROLES WHERE code = 'ROLE_ADMIN')
);
INSERT INTO EMPLOYEE_ROLE_LINK(employee_uuid, role_uuid) VALUES(
  (SELECT uuid FROM USERS WHERE code = 'admin'),
  (SELECT uuid FROM ROLES WHERE code = 'ROLE_DUMMY')
);
