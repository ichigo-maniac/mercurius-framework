CREATE TABLE FACETS(
  uuid CHARACTER VARYING(36) NOT NULL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL,
  code CHARACTER VARYING(255) NOT NULL UNIQUE,
  creationtime TIMESTAMP NOT NULL,
  modificationtime TIMESTAMP NOT NULL,
  facetType CHARACTER VARYING(255) NOT NULL,
  forAllCategories BOOLEAN,
  feature_uuid CHARACTER VARYING(36) REFERENCES FEATURES(uuid),
  solr_index_field_uuid CHARACTER VARYING(36) REFERENCES SOLR_INDEX_FIELDS(uuid)
);

CREATE TABLE FACETS_CATEGORIES_LINKS(
  facet_uuid CHARACTER VARYING(36) NOT NULL REFERENCES FACETS(uuid),
  category_uuid CHARACTER VARYING(36) NOT NULL REFERENCES CATEGORIES(uuid)
);

INSERT INTO FACETS(uuid, name, code, creationtime, modificationtime, facetType, forAllCategories, feature_uuid) VALUES (
  '1111b636e-f065-11e6-5322-836adef2f3a', 'Country', 'facet_country', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
  'DICTIONARY', false,
  (SELECT uuid FROM FEATURES WHERE code = 'country')
);

INSERT INTO FACETS_CATEGORIES_LINKS(facet_uuid, category_uuid) VALUES(
  (SELECT uuid FROM FACETS WHERE code = 'facet_country'),
  (SELECT uuid FROM CATEGORIES WHERE code ='fantasy_manga' AND catalog_uuid = (SELECT uuid FROM SHOP_CATALOGS WHERE code = 'master_catalog'))
);