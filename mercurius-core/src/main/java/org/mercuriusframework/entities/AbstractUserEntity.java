package org.mercuriusframework.entities;

import org.mercuriusframework.enums.AuthenticationType;
import javax.persistence.*;

/**
 * Abstract user entity
 */
@Entity(name = AbstractUserEntity.ENTITY_NAME)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE", discriminatorType = DiscriminatorType.STRING)
@Table(name = "USERS")
public abstract class AbstractUserEntity extends UniqueCodeEntity {

    private static final long serialVersionUID = -6492733529577799536L;

    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "AbstractUser";

    /**
     * E-mail
     */
    @Basic(optional = true)
    private String email;
    public static final String EMAIL = "email";

    /**
     * Password
     */
    @Basic(optional = true)
    private String password;
    public static final String PASSWORD = "password";

    /**
     * Authentication type
     */
    @Basic(optional = false)
    @Column(updatable = false)
    @Enumerated(EnumType.STRING)
    private AuthenticationType authenticationType;
    public static final String AUTHENTICATION_TYPE = "authenticationType";

    /**
     * Phone number
     */
    @Basic(optional = true)
    private String phoneNumber;
    public static final String PHONE_NUMBER = "phoneNumber";

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
