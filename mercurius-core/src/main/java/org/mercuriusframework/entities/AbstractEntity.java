package org.mercuriusframework.entities;

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
    @GeneratedValue(generator = "entity_id_gen", strategy = GenerationType.SEQUENCE)
    private Long id;
    /**
     * Entity uid
     */
    @Column(name = "ENTITY_UID", unique = true, nullable = false, updatable = true, length = 36)
    private String uid;
    /**
     * Creation time
     */
    private Date creationTime;
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
     * Get entity uid
     *
     * @return Uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * Set entity uid
     *
     * @param uid Uid
     */
    public void setUid(String uid) {
        this.uid = uid;
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
}
