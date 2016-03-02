package org.mercuriusframework.entities;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Entity with unique code class (abstract)
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class UniqueCodeEntity extends AbstractEntity implements Serializable {
    /**
     * Code (unique)
     */
    private String code;

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
