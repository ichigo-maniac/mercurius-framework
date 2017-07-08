package org.mercuriusframework.facades;

import org.mercuriusframework.dto.UserEntityDto;
import org.mercuriusframework.entities.AbstractUserEntity;
import org.mercuriusframework.enums.PasswordEncodingType;

import javax.servlet.ServletException;

/**
 * User facade interface
 */
public interface UserFacade {

    /**
     * Log in user
     * @param user User entity
     * @return Log in result
     */
    boolean logInUser(AbstractUserEntity user);

    /**
     * Log in user
     * @param username Username
     * @param password Password
     * @return Log in result
     */
    boolean logInUser(String username, String password);

    /**
     * Log in employee by password and username
     * @param username Username
     * @param password Password
     * @return Log in result
     */
    boolean logInEmployee(String username, String password);

    /**
     * Log in customer by password and username
     * @param username Username
     * @param password Password
     * @return Log in result
     */
    boolean logInCustomer(String username, String password);

    /**
     * Update user password
     * @param user User entity
     * @param rawPassword Raw password
     * @param passwordEncodingType Password encoding type
     */
    void updateUserPassword(AbstractUserEntity user, String rawPassword, PasswordEncodingType passwordEncodingType);

    /**
     * Is current user anonymous
     * @return Check result - Is current user anonymous
     */
    boolean isCurrentUserAnonymous();

    /**
     * Is current user employee
     * @return Check result - Is current user employee
     */
    boolean isCurrentUserEmployee();

    /**
     * Is current user customer
     * @return Check result - Is current user customer
     */
    boolean isCurrentUserCustomer();

    /**
     * Get current user
     * @return Current user
     */
    UserEntityDto getCurrentUser();

    /**
     * Does current user have a role
     * @param roleCode Role code
     * @return Check result
     */
    boolean hasCurrentUserRole(String roleCode);

    /**
     * Logout current user
     */
    void logOutCurrentUser() throws ServletException;
}
