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
     * Is a store disabled
     */
    @Basic(optional = true)
    private Boolean disabled;
    public static final String DISABLED = "disabled";

    /**
     * Warehouses
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = WarehouseEntity.STORE)
    private List<WarehouseEntity> warehouses;
    public static final String WAREHOUSES = "warehouses";

    /**
     * Get "Is a store disabled"
     * @return "Is a store disabled" flag
     */
    public Boolean getDisabled() {
        return disabled;
    }

    /**
     * Set "Is a store disabled"
     * @param disabled "Is a store disabled" flag
     */
    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
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
