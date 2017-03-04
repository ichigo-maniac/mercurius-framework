package org.mercuriusframework.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity with unique code class (abstract)
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class UniqueCodeEntity extends AbstractEntity implements Serializable {


    private static final long serialVersionUID = 1901365109058504410L;

    /**
     * Name
     */
    @Basic(optional = false)
    private String name;
    public static final String NAME = "name";

    /**
     * Code (unique)
     */
    @Basic(optional = false)
    @Column(unique = true)
    private String code;
    public static final String CODE = "code";

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
