package org.mercuriusframework.configuration.develop;

import org.apache.commons.lang.StringUtils;
import org.mercuriusframework.constants.MercuriusConfigurationParameters;
import org.mercuriusframework.constants.MercuriusConstants;
import org.mercuriusframework.services.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.util.HashSet;
import java.util.Set;


/**
 * Session configuration
 */
@EnableRedisHttpSession
@Configuration
@Profile(MercuriusConstants.PROFILES.REDIS_SESSION_PROFILE)
public class SessionConfiguration {

    /**
     * Configuration service
     */
    @Autowired
    private ConfigurationService configurationService;


    /**
     * Redis connection factory bean
     * @return Redis connection factory
     */
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        String redisNode = configurationService.getParameter(MercuriusConfigurationParameters.REDIS_PARAMETERS.SESSION_REDIS_NODE);
        String redisClusterNodes = configurationService.getParameter(MercuriusConfigurationParameters.REDIS_PARAMETERS.SESSION_REDIS_CLUSTER_NODES);
        /** If there is no parameter - use default value (localhost:6379) */
        if (redisClusterNodes == null && redisNode == null) {
            return new LettuceConnectionFactory();
        } else {
            if (redisNode != null) {
                if (StringUtils.isEmpty(redisNode.trim())) {
                    return new LettuceConnectionFactory();
                }
                String[] parts = redisNode.trim().split(":");
                return new LettuceConnectionFactory(parts[0], Integer.valueOf(parts[1]));
            } else {
                if (StringUtils.isEmpty(redisClusterNodes.trim())) {
                    return new LettuceConnectionFactory();
                }
                String[] nodes = redisClusterNodes.split("\\|");
                Set<String> uniqueNodes = new HashSet<>();
                for (String node : nodes) {
                    uniqueNodes.add(node.trim());
                }
                return new LettuceConnectionFactory(new RedisClusterConfiguration(uniqueNodes));
            }
        }
    }

}
