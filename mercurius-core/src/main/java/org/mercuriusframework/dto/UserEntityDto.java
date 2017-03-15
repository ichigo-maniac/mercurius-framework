package org.mercuriusframework.dto;

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
