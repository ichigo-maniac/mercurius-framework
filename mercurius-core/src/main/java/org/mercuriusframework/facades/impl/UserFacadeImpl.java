package org.mercuriusframework.facades.impl;

import org.mercuriusframework.constants.MercuriusConstants;
import org.mercuriusframework.converters.impl.CustomerEntityConverter;
import org.mercuriusframework.converters.impl.EmployeeEntityConverter;
import org.mercuriusframework.dto.UserEntityDto;
import org.mercuriusframework.entities.AbstractUserEntity;
import org.mercuriusframework.entities.CustomerEntity;
import org.mercuriusframework.entities.EmployeeEntity;
import org.mercuriusframework.enums.AuthenticationType;
import org.mercuriusframework.enums.EmployeeLoadOptions;
import org.mercuriusframework.enums.PasswordEncodingType;
import org.mercuriusframework.facades.UserFacade;
import org.mercuriusframework.providers.PasswordEncoderProvider;
import org.mercuriusframework.security.CustomerUserDetails;
import org.mercuriusframework.security.EmployeeUserDetails;
import org.mercuriusframework.security.SaltService;
import org.mercuriusframework.services.EntityService;
import org.mercuriusframework.services.SessionService;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;

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
     * Customer entity converter
     */
    @Autowired
    @Qualifier("customerEntityConverter")
    protected CustomerEntityConverter customerEntityConverter;

    /**
     * Salt service
     */
    @Autowired
    @Qualifier("saltService")
    protected SaltService saltService;

    /**
     * Log in user
     * @param user User entity
     * @return Log in result
     */
    @Override
    public boolean logInUser(AbstractUserEntity user) {
        AbstractUserEntity currentUser = entityService.findByUuid(user.getUuid(), AbstractUserEntity.class);
        if (currentUser == null) {
            return false;
        }
        if (currentUser instanceof EmployeeEntity) {
            EmployeeEntity employee = (EmployeeEntity) currentUser;
            EmployeeUserDetails userDetails = new EmployeeUserDetails(employee);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            sessionService.setSessionAttribute(MercuriusConstants.SESSION_ATTRIBUTES.CURRENT_USER,
                    employeeEntityConverter.convert(employee, EmployeeLoadOptions.ROLES));
            return true;
        }
        if (currentUser instanceof CustomerEntity) {
            CustomerEntity customer = (CustomerEntity) currentUser;
            CustomerUserDetails userDetails = new CustomerUserDetails(customer);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            sessionService.setSessionAttribute(MercuriusConstants.SESSION_ATTRIBUTES.CURRENT_USER,
                    customerEntityConverter.convert(customer));
            return true;
        } else {
            return false;
        }
    }

    /**
     * Log in user
     * @param username Username
     * @param password Password
     * @return Log in result
     */
    @Override
    public boolean logInUser(String username, String password) {
        AbstractUserEntity userEntity = uniqueCodeEntityService.getEntityByCode(username, AbstractUserEntity.class);
        if (userEntity == null) {
            return false;
        }
        if (userEntity.getAuthenticationType() != AuthenticationType.PASSWORD) {
            return false;
        }
        PasswordEncoder passwordEncoder = PasswordEncoderProvider.getPasswordEncoderByType(userEntity.getPasswordEncodingType());
        String salt = null;
        /** No salt for plaintext passwords */
        if (!(passwordEncoder instanceof PlaintextPasswordEncoder)) {
            salt = saltService.getSalt(userEntity);
        }
        boolean isValid = passwordEncoder.isPasswordValid(userEntity.getPassword(), password, salt);
        if (isValid) {
            return logInUser(userEntity);
        } else {
            return false;
        }
    }

    /**
     * Log in employee by password and username
     * @param username Username
     * @param password Password
     * @return Log in result
     */
    @Override
    public boolean logInEmployee(String username, String password) {
        AbstractUserEntity employee = uniqueCodeEntityService.getEntityByCode(username, AbstractUserEntity.class);
        if (!(employee instanceof EmployeeEntity)) {
            return false;
        }
        return logInUser(username, password);
    }

    /**
     * Log in customer by password and username
     *
     * @param username Username
     * @param password Password
     * @return Log in result
     */
    @Override
    public boolean logInCustomer(String username, String password) {
        AbstractUserEntity customerEntity = uniqueCodeEntityService.getEntityByCode(username, AbstractUserEntity.class);
        if (!(customerEntity instanceof CustomerEntity)) {
            return false;
        }
        return logInUser(username, password);
    }

    /**
     * @param user                 User entity
     * @param rawPassword          Raw password
     * @param passwordEncodingType Password encoding type
     */
    @Override
    public void updateUserPassword(AbstractUserEntity user, String rawPassword, PasswordEncodingType passwordEncodingType) {
        AbstractUserEntity currentUser = entityService.findByUuid(user.getUuid(), AbstractUserEntity.class);
        if (currentUser == null || currentUser.getAuthenticationType() != AuthenticationType.PASSWORD) {
            return;
        }
        PasswordEncoder passwordEncoder = PasswordEncoderProvider.getPasswordEncoderByType(passwordEncodingType);
        String salt = null;
        /** No salt for plaintext passwords */
        if (!(passwordEncoder instanceof PlaintextPasswordEncoder)) {
            salt = saltService.getSalt(user);
        }
        String storedValue = passwordEncoder.encodePassword(rawPassword, salt);
        currentUser.setPassword(storedValue);
        currentUser.setPasswordEncodingType(passwordEncodingType);
        entityService.save(currentUser);
    }

    /**
     * Is current user anonymous
     * @return Check result - Is current user anonymous
     */
    @Override
    public boolean isCurrentUserAnonymous() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }
        if ((authentication.getPrincipal() instanceof EmployeeUserDetails) || (authentication.getPrincipal() instanceof CustomerUserDetails)) {
            return false;
        } else {
            return true;
        }
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
     * Is current user customer
     * @return Check result - Is current user customer
     */
    @Override
    public boolean isCurrentUserCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }
        return authentication.getPrincipal() instanceof CustomerUserDetails;
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
                if (authentication.getPrincipal() instanceof UserDetails) {
                    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
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

    /**
     * Does current user have a role
     * @param roleCode Role code
     * @return Check result
     */
    @Override
    public boolean hasCurrentUserRole(String roleCode) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().isUserInRole(roleCode);
    }

    /**
     * Logout current user
     */
    @Override
    public void logOutCurrentUser() throws ServletException {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        attr.getRequest().logout();
    }
}
