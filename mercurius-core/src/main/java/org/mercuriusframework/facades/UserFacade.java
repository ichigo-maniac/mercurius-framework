package org.mercuriusframework.facades;

import org.mercuriusframework.dto.UserEntityDto;

import javax.servlet.ServletException;

/**
 * User facade interface
 */
public interface UserFacade {

    /**
     * Log in employee by password and username
     * @param username Username
     * @param password Password
     * @return Log in result
     */
    boolean logInEmployee(String username, String password);

    /**
     * Is current user employee
     * @return Check result - Is current user employee
     */
    boolean isCurrentUserEmployee();

    /**
     * Get current user
     * @return Current user
     */
    UserEntityDto getCurrentUser();

    /**
     * Logout current user
     */
    void logOutCurrentUser() throws ServletException;
}
