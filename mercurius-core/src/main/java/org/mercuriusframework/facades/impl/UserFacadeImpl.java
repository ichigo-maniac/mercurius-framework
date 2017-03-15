package org.mercuriusframework.facades.impl;

import org.mercuriusframework.constants.MercuriusConstants;
import org.mercuriusframework.converters.impl.EmployeeEntityConverter;
import org.mercuriusframework.dto.UserEntityDto;
import org.mercuriusframework.entities.EmployeeEntity;
import org.mercuriusframework.enums.EmployeeLoadOptions;
import org.mercuriusframework.facades.UserFacade;
import org.mercuriusframework.security.EmployeeUserDetails;
import org.mercuriusframework.services.EntityService;
import org.mercuriusframework.services.SessionService;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    @Qualifier("uniqueCodeEntityService")
    protected UniqueCodeEntityService uniqueCodeEntityService;

    /**
     * Session service
     */
    @Autowired
    @Qualifier("sessionService")
    protected SessionService sessionService;

    /**
     * Employee entity converter
     */
    @Autowired
    @Qualifier("employeeEntityConverter")
    protected EmployeeEntityConverter employeeEntityConverter;


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
        sessionService.setSessionAttribute(MercuriusConstants.SESSION_ATTRIBUTES.CURRENT_USER,
                employeeEntityConverter.convert(employee, EmployeeLoadOptions.ROLES));
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
        return authentication.getPrincipal() instanceof EmployeeUserDetails;
    }

    /**
     * Get current user
     * @return Current user
     */
    @Override
    public UserEntityDto getCurrentUser() {
        UserEntityDto sessionUser = (UserEntityDto) sessionService.getSessionAttribute(MercuriusConstants.SESSION_ATTRIBUTES.CURRENT_USER);
        if (sessionUser != null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                if (authentication.getDetails() instanceof UserDetails) {
                    UserDetails userDetails = (UserDetails) authentication.getDetails();
                    return sessionUser.getCode().equals(userDetails.getUsername()) ? sessionUser : null;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
