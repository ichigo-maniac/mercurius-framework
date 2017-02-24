package org.mercuriusframework.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Session service interface
 */
public interface SessionService {
    /**
     * Get current http-session
     * @return Http-session
     */
    HttpSession getCurrentSession();

    /**
     * Get current http-session
     * @param request Http-request
     * @return Http-session
     */
    HttpSession getCurrentSession(HttpServletRequest request);

    /**
     * Set session attribute
     * @param name Session attribute name
     * @param value Session attribute value
     */
    void setSessionAttribute(String name, Object value);

    /**
     * Get session attribute
     * @param request Http-request
     * @param name Session attribute name
     * @return Session attribute
     */
    Object getSessionAttribute(HttpServletRequest request, String name);

    /**
     * Get session attribute
     * @param name Session attribute name
     * @return Session attribute
     */
    Object getSessionAttribute(String name);

    /**
     * Set session attribute
     * @param request Http-request
     * @param name Session attribute name
     * @param value Session attribute value
     */
    void setSessionAttribute(HttpServletRequest request, String name, Object value);

}
