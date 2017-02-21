package org.mercuriusframework.dto;

/**
 * Unique code entity data transfer object
 */
public class UniqueCodeEntityDto extends EntityDto {
    /**
     * Name
     */
    private String name;

    /**
     * Code (unique)
     */
    private String code;

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
