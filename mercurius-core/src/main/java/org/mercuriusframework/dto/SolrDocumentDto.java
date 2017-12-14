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
     * Name
     */
    private String name;

    /**
     * Code (unique)
     */
    private String code;

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

    /**
     * Get name
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name
     * @param name Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get code
     * @return Code
     */
    public String getCode() {
        return code;
    }

    /**
     * Set code
     * @param code code
     */
    public void setCode(String code) {
        this.code = code;
    }
}
