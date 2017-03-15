package org.mercuriusframework.entities;

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
}
