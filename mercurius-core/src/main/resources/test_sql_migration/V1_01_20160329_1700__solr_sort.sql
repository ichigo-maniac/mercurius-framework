CREATE TABLE SOLR_SORTS(
  uuid CHARACTER VARYING(36) NOT NULL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL,
  code CHARACTER VARYING(255) NOT NULL UNIQUE,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL,
  directionType CHARACTER VARYING(255) NOT NULL,
  solr_search_resolver_uuid CHARACTER VARYING(36) REFERENCES SOLR_SEARCH_RESOLVERS(uuid),
  solrField CHARACTER VARYING(255),
  solrFieldResolver CHARACTER VARYING(255)
);

CREATE UNIQUE INDEX SOLR_SORTS_UUID_IDX ON SOLR_SORTS(uuid);
CREATE UNIQUE INDEX SOLR_SORTS_CODE_IDX ON SOLR_SORTS(code);
CREATE INDEX SOLR_SORTS_NAME_IDX ON SOLR_SORTS(name);

INSERT INTO SOLR_SORTS(uuid, name, code, creationtime, modificationtime, directionType, solrField, solr_search_resolver_uuid) VALUES (
  '1111b636e-0277-11e6-5322-836adef2234', 'Name desc', 'name_desc', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  'DESC', 'dummy_field',
  (SELECT uuid FROM SOLR_SEARCH_RESOLVERS WHERE code = 'dummy_solr_search_resolver')
);

INSERT INTO SOLR_SORTS(uuid, name, code, creationtime, modificationtime, directionType, solrField, solr_search_resolver_uuid) VALUES (
  '1111b636e-0277-11e6-5322-836a5322234', 'Name asc', 'name_asc', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  'ASC', 'dummy_field',
  (SELECT uuid FROM SOLR_SEARCH_RESOLVERS WHERE code = 'dummy_solr_search_resolver')
);