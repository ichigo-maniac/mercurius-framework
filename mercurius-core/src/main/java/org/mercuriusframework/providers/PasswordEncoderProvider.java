package org.mercuriusframework.providers;

import org.mercuriusframework.enums.PasswordEncodingType;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.authentication.encoding.*;
import org.springframework.stereotype.Service;


/**
 * Password encoder provider
 */
@Service("passwordEncoderProvider")
public class PasswordEncoderProvider implements ApplicationContextAware {

    /**
     * Plain text password encoder
     */
    private static PlaintextPasswordEncoder plaintextPasswordEncoder;

    /**
     * MD4 password encoder
     */
    private static Md4PasswordEncoder md4PasswordEncoder;

    /**
     * MD5 password encoder
     */
    private static Md5PasswordEncoder md5PasswordEncoder;

    /**
     * SHA password encoder
     */
    private static ShaPasswordEncoder shaPasswordEncoder;

    /**
     * Get password encoder by type
     * @param passwordEncodingType Password encoding type
     * @return Password encoder
     */
    public static PasswordEncoder getPasswordEncoderByType(PasswordEncodingType passwordEncodingType) {
        if (passwordEncodingType == null || passwordEncodingType == PasswordEncodingType.PLAIN_TEXT) {
            return plaintextPasswordEncoder;
        }
        if (passwordEncodingType == PasswordEncodingType.MD4) {
            return md4PasswordEncoder;
        }
        if (passwordEncodingType == PasswordEncodingType.MD5) {
            return md5PasswordEncoder;
        }
        if (passwordEncodingType == PasswordEncodingType.SHA) {
            return shaPasswordEncoder;
        }
        return plaintextPasswordEncoder;
    }

    /**
     * Set spring application context
     * @param applicationContext Application context
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        plaintextPasswordEncoder = applicationContext.getBean(PlaintextPasswordEncoder.class);
        md4PasswordEncoder = applicationContext.getBean(Md4PasswordEncoder.class);
        md5PasswordEncoder = applicationContext.getBean(Md5PasswordEncoder.class);
        shaPasswordEncoder = applicationContext.getBean(ShaPasswordEncoder.class);
    }
}
