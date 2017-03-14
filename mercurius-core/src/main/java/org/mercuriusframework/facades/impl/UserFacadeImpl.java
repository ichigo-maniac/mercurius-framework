package org.mercuriusframework.facades.impl;

import org.mercuriusframework.entities.EmployeeEntity;
import org.mercuriusframework.facades.UserFacade;
import org.mercuriusframework.security.EmployeeUserDetails;
import org.mercuriusframework.services.EntityService;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * User facade
 */
@Service("userFacade")
public class UserFacadeImpl implements UserFacade {

    /**
     * Entity service
     */
    @Autowired
    @Qualifier("entityService")
    protected EntityService entityService;

    /**
     * Unique code entity service
     */
    @Autowired
    private UniqueCodeEntityService uniqueCodeEntityService;

    /**
     * Log in employee by password and username
     * @param username Username
     * @param password Password
     * @return Log in result
     */
    @Override
    public boolean logInEmployee(String username, String password) {
        EmployeeEntity employee = uniqueCodeEntityService.getEntityByCode(username, EmployeeEntity.class);
        if (employee == null) {
            return false;
        }
        if (employee.getPassword() == null || !employee.getPassword().equals(password)) {
            return false;
        }
        EmployeeUserDetails userDetails = new EmployeeUserDetails(employee);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return true;
    }

    /**
     * Is current user employee
     * @return Check result - Is current user employee
     */
    @Override
    public boolean isCurrentUserEmployee() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }
        if (authentication.getPrincipal() instanceof EmployeeUserDetails) {
            EmployeeUserDetails userDetails = (EmployeeUserDetails) authentication.getPrincipal();
            return userDetails.isEnabled();
        } else {
            return false;
        }
    }
}
