CREATE TABLE TASKS(
  uuid CHARACTER VARYING(36) NOT NULL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL,
  code CHARACTER VARYING(255) NOT NULL UNIQUE,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL,
  query CHARACTER VARYING(2500),
  enabled BOOLEAN,
  status CHARACTER VARYING(255) NOT NULL,
  taskRunBeanName CHARACTER VARYING(255) NOT NULL,
  laststarttime TIMESTAMP,
  lastfinishtime TIMESTAMP,
  indexEntityName CHARACTER VARYING(255),
  task_type CHARACTER VARYING(255) NOT NULL
);

INSERT INTO TASKS(uuid, name, code, creationtime, modificationtime, query, enabled, task_type, taskRunBeanName, status) VALUES (
  '4a4b636e-f065-11e6-9dac-836adef2f3a6', 'Test task 1', 'test_task_1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  'test-query', true, 'SolrIndexTask', 'testBean1', 'NEW'
);

INSERT INTO TASKS(uuid, name, code, creationtime, modificationtime, query, enabled, task_type, taskRunBeanName, status) VALUES (
  '224b636e-f065-11e6-9dac-836adef2f3a6', 'Test task 2', 'test_task_2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  'test-query2', true, 'SolrIndexTask', 'testBean2', 'NEW'
);

INSERT INTO TASKS(uuid, name, code, creationtime, modificationtime, query, enabled, task_type, taskRunBeanName, status) VALUES (
  '3a4b636e-f065-11e6-9dac-836adef2f3a6', 'Test task 3', 'test_task_3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  'test-query3', false, 'SolrIndexTask', 'testBean3', 'NEW'
);