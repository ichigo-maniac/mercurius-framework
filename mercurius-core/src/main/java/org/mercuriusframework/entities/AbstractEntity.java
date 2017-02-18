package org.mercuriusframework.entities;

import org.hibernate.annotations.GenericGenerator;
import org.mercuriusframework.listeners.CommonAbstractEntityListener;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Abstract entity class
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({CommonAbstractEntityListener.class})
public abstract class AbstractEntity implements Serializable {
    /**
     * Entity id
     */
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "UUID", updatable = false, unique = true, nullable = false, length = 36)
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
        final AbstractEntity other = (AbstractEntity) object;
        if (this.getUuid() != other.getUuid()
                && (this.getUuid() == null || !this.getUuid().equals(other.getUuid()))) {
            return false;
        }
        return true;
    }
}
