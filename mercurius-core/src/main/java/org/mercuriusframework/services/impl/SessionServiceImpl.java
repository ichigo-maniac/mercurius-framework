package org.mercuriusframework.services.impl;

import org.mercuriusframework.services.SessionService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Session service
 */
@Service("sessionService")
public class SessionServiceImpl implements SessionService {

    /**
     * Get current http-session
     * @return Http-session
     */
    public HttpSession getCurrentSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(false);
    }

    /**
     * Get current http-session
     *
     * @param request Http-request
     * @return Http-session
     */
    @Override
    public HttpSession getCurrentSession(HttpServletRequest request) {
        return request.getSession();
    }

    /**
     * Set session attribute
     * @param name  Session attribute name
     * @param value Session attribute value
     */
    public void setSessionAttribute(String name, Object value) {
        getCurrentSession().setAttribute(name, value);
    }

    /**
     * Get session attribute
     *
     * @param request Http-request
     * @param name    Session attribute name
     * @return Session attribute
     */
    @Override
    public Object getSessionAttribute(HttpServletRequest request, String name) {
        return getCurrentSession(request).getAttribute(name);
    }

    /**
     * Get session attribute
     * @param name Session attribute name
     * @return Session attribute
     */
    public Object getSessionAttribute(String name) {
        return getCurrentSession().getAttribute(name);
    }

    /**
     * Set session attribute
     *
     * @param request Http-request
     * @param name    Session attribute name
     * @param value   Session attribute value
     */
    @Override
    public void setSessionAttribute(HttpServletRequest request, String name, Object value) {
        getCurrentSession(request).setAttribute(name, value);
    }
}
