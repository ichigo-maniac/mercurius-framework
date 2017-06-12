package org.mercuriusframework.dto;

import org.mercuriusframework.enums.AuthenticationType;

/**
 * User entity data transfer object
 */
public class UserEntityDto extends UniqueCodeEntityDto {


    private static final long serialVersionUID = 7200151198229674387L;

    /**
     * E-mail
     */
    private String email;

    /**
     * Password
     */
    private String password;

    /**
     * Authentication type
     */
    private AuthenticationType authenticationType;

    /**
     * Phone number
     */
    private String phoneNumber;

    /**
     * Get e-mail
     * @return E-mail
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set e-mail
     * @param email E-mail
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set password
     * @param password Password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get authentication type
     * @return Authentication type
     */
    public AuthenticationType getAuthenticationType() {
        return authenticationType;
    }

    /**
     * Set authentication type
     * @param authenticationType Authentication type
     */
    public void setAuthenticationType(AuthenticationType authenticationType) {
        this.authenticationType = authenticationType;
    }

    /**
     * Get phone number
     * @return Phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set phone number
     * @param phoneNumber Phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
