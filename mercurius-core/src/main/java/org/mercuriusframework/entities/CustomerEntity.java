package org.mercuriusframework.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Customer entity class
 */
@Entity(name = CustomerEntity.ENTITY_NAME)
@DiscriminatorValue(CustomerEntity.ENTITY_NAME)
public class CustomerEntity extends AbstractUserEntity {

    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "Customer";
}
