CREATE TABLE TASKS(
  uuid CHARACTER VARYING(36) NOT NULL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL,
  code CHARACTER VARYING(255) NOT NULL UNIQUE,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL,
  query CHARACTER VARYING(2500),
  enabled BOOLEAN,
  task_type CHARACTER VARYING(255) NOT NULL
);

INSERT INTO TASKS(uuid, name, code, creationtime, modificationtime, query, enabled, task_type) VALUES (
  '4a4b636e-f065-11e6-9dac-836adef2f3a6', 'Test task 1', 'test_task_1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  'test-query', true, 'SolrIndexTask'
);

INSERT INTO TASKS(uuid, name, code, creationtime, modificationtime, query, enabled, task_type) VALUES (
  '224b636e-f065-11e6-9dac-836adef2f3a6', 'Test task 2', 'test_task_2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  'test-query2', true, 'SolrIndexTask'
);

INSERT INTO TASKS(uuid, name, code, creationtime, modificationtime, query, enabled, task_type) VALUES (
  '3a4b636e-f065-11e6-9dac-836adef2f3a6', 'Test task 3', 'test_task_3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  'test-query3', false, 'SolrIndexTask'
);