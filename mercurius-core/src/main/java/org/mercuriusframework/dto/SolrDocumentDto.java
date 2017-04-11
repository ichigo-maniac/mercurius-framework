package org.mercuriusframework.dto;

import java.io.Serializable;

/**
 * Solr document data transfer object
 */
public class SolrDocumentDto implements Serializable {

    /**
     * Document id
     */
    private String id;

    /**
     * Get document id
     * @return Document id
     */
    public String getId() {
        return id;
    }

    /**
     * Set document id
     * @param id Document id
     */
    public void setId(String id) {
        this.id = id;
    }
}
