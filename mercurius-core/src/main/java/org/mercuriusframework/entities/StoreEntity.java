package org.mercuriusframework.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Store entity class
 */
@Entity(name = StoreEntity.ENTITY_NAME)
@Table(name = "STORE")
public class StoreEntity extends UniqueCodeEntity {

    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "Store";

    /**
     * Is a store enabled
     */
    @Basic(optional = true)
    private Boolean enabled;
    public static final String ENABLED = "enabled";

    /**
     * Warehouses
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = WarehouseEntity.STORE)
    private List<WarehouseEntity> warehouses;
    public static final String WAREHOUSES = "warehouses";

    /**
     * Get "Is a store enabled"
     * @return "Is a store enabled" flag
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * Set "Is a store enabled"
     * @param enabled "Is a store enabled" flag
     */
    public void setDisabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Get warehouses
     * @return Warehouses
     */
    public List<WarehouseEntity> getWarehouses() {
        return warehouses;
    }

    /**
     * Set warehouses
     * @param warehouses Warehouses
     */
    public void setWarehouses(List<WarehouseEntity> warehouses) {
        this.warehouses = warehouses;
    }
}
