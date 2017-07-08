package org.mercuriusframework.configuration.develop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md4PasswordEncoder;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

/**
 * Password encoders configuration
 */
@Configuration
public class PasswordEncodersConfiguration {

    /**
     * Plain text password encoder bean
     * @return Plain text password encoder
     */
    @Bean(name = "plaintextPasswordEncoder")
    public PlaintextPasswordEncoder plaintextPasswordEncoder() {
        return new PlaintextPasswordEncoder();
    }

    /**
     * MD4 password encoder bean
     * @return MD4 password encoder
     */
    @Bean(name = "md4PasswordEncoder")
    public Md4PasswordEncoder md4PasswordEncoder() {
        return new Md4PasswordEncoder();
    }

    /**
     * MD5 password encoder bean
     * @return MD5 password encoder
     */
    @Bean(name = "md5PasswordEncoder")
    public Md5PasswordEncoder md5PasswordEncoder() {
        return new Md5PasswordEncoder();
    }

    /**
     * SHA password encoder bean
     * @return SHA password encoder
     */
    @Bean(name = "shaPasswordEncoder")
    public ShaPasswordEncoder shaPasswordEncoder() {
        return new ShaPasswordEncoder();
    }
}
