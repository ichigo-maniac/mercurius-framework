package org.mercuriusframework.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Abstract entity class
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractEntity implements Serializable {
    /**
     * Entity id
     */
    @Id
    @GeneratedValue(generator = "entity_id_gen", strategy = GenerationType.SEQUENCE)
    private Long id;
    /**
     * Creation time
     */
    private Date creationDTime;
    /**
     * Modification time
     */
    private Date modificationTime;

    /**
     * Get entity id
     *
     * @return Entity id
     */
    public Long getId() {
        return id;
    }

    /**
     * Get creation time
     * @return Creation time
     */
    public Date getCreationDTime() {
        return creationDTime;
    }

    /**
     * Set creation time
     * @param creationDTime Creation time
     */
    public void setCreationDTime(Date creationDTime) {
        this.creationDTime = creationDTime;
    }

    /**
     * Get modification time
     * @return Modification time
     */
    public Date getModificationTime() {
        return modificationTime;
    }

    /**
     * Set modification time
     * @param modificationTime Modification time
     */
    public void setModificationTime(Date modificationTime) {
        this.modificationTime = modificationTime;
    }
}
