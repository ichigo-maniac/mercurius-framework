package org.mercuriusframework.facades;

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
}
