package org.mercuriusframework.entities;

import org.mercuriusframework.enums.AuthenticationType;
import org.mercuriusframework.enums.PasswordEncodingType;

import javax.persistence.*;

/**
 * Abstract user entity
 */
@Entity(name = AbstractUserEntity.ENTITY_NAME)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE", discriminatorType = DiscriminatorType.STRING)
@Table(name = "USERS")
public class AbstractUserEntity extends UniqueCodeEntity {

    private static final long serialVersionUID = -6492733529577799536L;

    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "AbstractUser";


    /**
     * First name
     */
    @Basic(optional = true)
    private String firstName;
    public static final String FIRST_NAME = "firstName";

    /**
     * Last name
     */
    @Basic(optional = true)
    private String lastName;
    public static final String LAST_NAME = "lastName";


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
     * Password encoding type
     */
    @Basic(optional = true)
    @Enumerated(EnumType.STRING)
    private PasswordEncodingType passwordEncodingType;
    public static final String PASSWORD_ENCODING_TYPE = "passwordEncodingType";

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
     * Get first name
     * @return First name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set first name
     * @param firstName First name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get last name
     * @return Last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set last name
     * @param lastName Last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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

    /**
     * Get password encoding type
     * @return Password encoding type
     */
    public PasswordEncodingType getPasswordEncodingType() {
        return passwordEncodingType;
    }

    /**
     * Set password encoding type
     * @param passwordEncodingType Password encoding type
     */
    public void setPasswordEncodingType(PasswordEncodingType passwordEncodingType) {
        this.passwordEncodingType = passwordEncodingType;
    }
}
