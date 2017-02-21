package org.mercuriusframework.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity data transfer object
 */
public class EntityDto implements Serializable {
    /**
     * Entity id
     */
    private String uuid;

    /**
     * Creation time
     */
    private Date creationTime;

    /**
     * Modification time
     */
    private Date modificationTime;

    /**
     * Sey uuid
     * @param uuid Uuid string
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * Get uuid
     * @return Uuid string
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Get creation time
     *
     * @return Creation time
     */
    public Date getCreationTime() {
        return creationTime;
    }

    /**
     * Set creation time
     *
     * @param creationTime Creation time
     */
    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    /**
     * Get modification time
     *
     * @return Modification time
     */
    public Date getModificationTime() {
        return modificationTime;
    }

    /**
     * Set modification time
     *
     * @param modificationTime Modification time
     */
    public void setModificationTime(Date modificationTime) {
        this.modificationTime = modificationTime;
    }

    /**
     * Get hash code
     * @return Hash code
     */
    @Override
    public int hashCode() {
        return uuid != null ? uuid.hashCode() : 0;
    }

    /**
     * Equals
     * @param object Object
     * @return Compare result
     */
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (getClass() != object.getClass()) {
            return false;
        }
        final EntityDto other = (EntityDto) object;
        if (this.getUuid() != other.getUuid()
                && (this.getUuid() == null || !this.getUuid().equals(other.getUuid()))) {
            return false;
        }
        return true;
    }
}
