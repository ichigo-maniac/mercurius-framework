package org.mercuriusframework.entities;

import org.mercuriusframework.enums.SocialNetworkType;

import javax.persistence.*;

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

    /**
     * Social network type
     */
    @Basic(optional = true)
    @Enumerated(EnumType.STRING)
    private SocialNetworkType socialNetworkType;
    public static final String SOCIAL_NETWORK_TYPE = "socialNetworkType";

    /**
     * Social network id
     */
    @Basic(optional = false)
    private String socialNetworkId;
    public static final String SOCIAL_NETWORK_ID = "socialNetworkId";

    /**
     * Get social network type
     * @return Social network type
     */
    public SocialNetworkType getSocialNetworkType() {
        return socialNetworkType;
    }

    /**
     * Set social network type
     * @param socialNetworkType Social network type
     */
    public void setSocialNetworkType(SocialNetworkType socialNetworkType) {
        this.socialNetworkType = socialNetworkType;
    }

    /**
     * Get social network id
     * @return Social network id
     */
    public String getSocialNetworkId() {
        return socialNetworkId;
    }

    /**
     * Set social network id
     * @param socialNetworkId Social network id
     */
    public void setSocialNetworkId(String socialNetworkId) {
        this.socialNetworkId = socialNetworkId;
    }
}
