CREATE TABLE SOLR_INDEX_TASK_PROPERTY(
  uuid CHARACTER VARYING(36) NOT NULL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL,
  code CHARACTER VARYING(255) NOT NULL UNIQUE,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL,
  solrCollectionName CHARACTER VARYING(255) NOT NULL,
  query CHARACTER VARYING(2500) NOT NULL,
  indexEntityName CHARACTER VARYING(255) NOT NULL
);

CREATE TABLE SOLR_INDEX_FIELD(
  uuid CHARACTER VARYING(36) NOT NULL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL,
  code CHARACTER VARYING(255) NOT NULL UNIQUE,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL,
  solrDocumentFieldName CHARACTER VARYING(255) NOT NULL,
  entityFieldName CHARACTER VARYING(2500) NOT NULL,
  solrFieldConverterBeanName CHARACTER VARYING(255),
  solr_index_property_uuid CHARACTER VARYING(36) REFERENCES SOLR_INDEX_TASK_PROPERTY(uuid)
);

CREATE TABLE TASKS(
  uuid CHARACTER VARYING(36) NOT NULL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL,
  code CHARACTER VARYING(255) NOT NULL UNIQUE,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL,
  enabled BOOLEAN,
  status CHARACTER VARYING(255) NOT NULL,
  taskRunBeanName CHARACTER VARYING(255) NOT NULL,
  laststarttime TIMESTAMP,
  lastfinishtime TIMESTAMP,
  task_type CHARACTER VARYING(255) NOT NULL,
  solr_index_property_uuid CHARACTER VARYING(36) REFERENCES SOLR_INDEX_TASK_PROPERTY(uuid)
);

INSERT INTO SOLR_INDEX_TASK_PROPERTY(uuid, name, code, creationtime, modificationtime, solrCollectionName, query, indexEntityName) VALUES (
  '222b636e-f065-11e6-9dac-836adef2f3a6', 'Test index property', 'test_index_property', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  'test_collection_1', 'test-query', 'Product'
);

INSERT INTO SOLR_INDEX_TASK_PROPERTY(uuid, name, code, creationtime, modificationtime, solrCollectionName, query, indexEntityName) VALUES (
  '3a4b636e-0022-11e6-9dac-836adef2f3a6', 'Test index property 2', 'test_index_property_2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  'test_collection_2', 'test-query2', 'Product'
);

INSERT INTO SOLR_INDEX_TASK_PROPERTY(uuid, name, code, creationtime, modificationtime, solrCollectionName, query, indexEntityName) VALUES (
  '3a4b636e-f065-2222-9dac-836adef2f3a6', 'Test index property 3', 'test_index_property_3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  'test_collection_3', 'test-query3', 'Product'
);

INSERT INTO TASKS(uuid, name, code, creationtime, modificationtime, enabled, task_type, taskRunBeanName, status, solr_index_property_uuid) VALUES (
  '4a4b636e-f065-11e6-9dac-836adef2f3a6', 'Test task 1', 'test_task_1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  true, 'SolrIndexTask', 'testBean1', 'NEW',
  (SELECT uuid FROM SOLR_INDEX_TASK_PROPERTY WHERE code = 'test_index_property')
);

INSERT INTO TASKS(uuid, name, code, creationtime, modificationtime, enabled, task_type, taskRunBeanName, status, solr_index_property_uuid) VALUES (
  '224b636e-f065-11e6-9dac-836adef2f3a6', 'Test task 2', 'test_task_2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  true, 'SolrIndexTask', 'testBean2', 'NEW',
  (SELECT uuid FROM SOLR_INDEX_TASK_PROPERTY WHERE code = 'test_index_property_2')
);

INSERT INTO TASKS(uuid, name, code, creationtime, modificationtime, enabled, task_type, taskRunBeanName, status, solr_index_property_uuid) VALUES (
  '3a4b636e-f065-11e6-9dac-836adef2f3a6', 'Test task 3', 'test_task_3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  false, 'SolrIndexTask', 'testBean3', 'NEW',
  (SELECT uuid FROM SOLR_INDEX_TASK_PROPERTY WHERE code = 'test_index_property_3')
);