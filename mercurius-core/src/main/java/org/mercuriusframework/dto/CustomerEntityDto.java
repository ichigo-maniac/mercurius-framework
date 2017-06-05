package org.mercuriusframework.dto;

import org.mercuriusframework.enums.SocialNetworkType;

/**
 * Customer entity data transfer object
 */
public class CustomerEntityDto extends UserEntityDto {

    private static final long serialVersionUID = 2031877874326938970L;

    /**
     * Social network type
     */
    private SocialNetworkType socialNetworkType;

    /**
     * Social network id
     */
    private String socialNetworkId;

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
