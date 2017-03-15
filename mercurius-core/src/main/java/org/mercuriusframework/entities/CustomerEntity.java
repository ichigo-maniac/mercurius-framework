package org.mercuriusframework.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Customer entity class
 */
@Entity(name = CustomerEntity.ENTITY_NAME)
@DiscriminatorValue(CustomerEntity.ENTITY_NAME)
public class CustomerEntity extends AbstractUserEntity {

    private static final long serialVersionUID = -50488937299917912L;

    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "Customer";
}
