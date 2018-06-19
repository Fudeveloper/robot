package com.robotcms.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.robotcms.api.shiro.JWTAuthenticationFilter;
import com.robotcms.api.shiro.JWTAuthorizingRealm;
import com.robotcms.shiro.realm.IFastModularRealm;
import com.robotcms.sys.config.BDSessionListener;
import com.robotcms.sys.dao.SysLogoutConfigMapper;
import com.robotcms.sys.domain.SysLogoutConfig;
import com.robotcms.sys.service.SysLogoutConfigService;
import com.robotcms.sys.shiro.SysUserAuthorizingRealm;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import java.util.*;

/**
 * <pre>
 * . cache ehcache
 * . realm(cache)
 * . securityManager（realm）
 * . ShiroFilterFactoryBean 注册
 *
 * </pre>
 * |
 */

@Configuration
@ConfigurationProperties(prefix = "custom")
@AutoConfigureAfter(FirstShiroConfig.class)
public class ShiroConfig implements EnvironmentAware {

    @Autowired
    private SysLogoutConfigMapper sysLogoutConfigMapper;

    private RelaxedPropertyResolver propertyResolver;


    @Bean
    SessionDAO sessionDAO() {

        MemorySessionDAO sessionDAO = new MemorySessionDAO();
        return sessionDAO;
    }

    @Bean
    public SessionManager sessionManager() {

        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        Collection<SessionListener> listeners = new ArrayList<SessionListener>();
        listeners.add(new BDSessionListener());
        sessionManager.setSessionListeners(listeners);
        sessionManager.setSessionDAO(sessionDAO());


        SysLogoutConfig sysLogoutConfig = new SysLogoutConfig();
        sysLogoutConfig.setName("status");
        String status= sysLogoutConfigMapper.selectOne(sysLogoutConfig).getValue();

        Integer timeout = -1000;


        if ("1".equals(status)) {
            SysLogoutConfig sysLogoutConfig1 = new SysLogoutConfig();
            sysLogoutConfig1.setName("timeout");
            String stringTimeOut = sysLogoutConfigMapper.selectOne(sysLogoutConfig1).getValue();
            timeout = Integer.parseInt(stringTimeOut);
        }

//        SysLogoutConfigService sysLogoutConfigMapper = applicationContext.getBean(SysLogoutConfigService.class);
//        SysLogoutConfig status = sysLogoutConfigService.selectById("status");
//        String name = status.getName();
//      读取配置文件的
//        String autoLogOutTime = propertyResolver.getProperty("autoLogOutTime");
//        long aLong = Long.parseLong(autoLogOutTime);
        sessionManager.setGlobalSessionTimeout(timeout);
        return sessionManager;
    }

    @Bean
    public CacheManager getCacheManager() {
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:config/ehcache.xml");
//        CacheManager cacheManager = new MemoryConstrainedCacheManager();
        return cacheManager;
    }

    @Bean
    SysUserAuthorizingRealm userRealm() {
        SysUserAuthorizingRealm userRealm = new SysUserAuthorizingRealm();
        userRealm.setCacheManager(getCacheManager());
        return userRealm;
    }

    @Bean
    JWTAuthorizingRealm jwtAuthorizingRealm() {
        JWTAuthorizingRealm jwtAuthorizingRealm = new JWTAuthorizingRealm();
        return jwtAuthorizingRealm;
    }

    @Bean
    IFastModularRealm getAuthenticator() {
        IFastModularRealm authenticator = new IFastModularRealm();
        authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        List<Realm> realms = new ArrayList<>();
        realms.add(userRealm());
        realms.add(jwtAuthorizingRealm());
        authenticator.setRealms(realms);
        return authenticator;
    }

    @Bean
    Authorizer getAuthorizer() {
        ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
        List<Realm> realms = new ArrayList<>();
        realms.add(userRealm());
        realms.add(jwtAuthorizingRealm());
        authorizer.setRealms(realms);
        return authorizer;
    }

    @Bean
    SecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        List<Realm> realms = new ArrayList<>();
        realms.add(userRealm());
        realms.add(jwtAuthorizingRealm());
        manager.setRealms(realms);
        manager.setAuthenticator(getAuthenticator());
        manager.setAuthorizer(getAuthorizer());
        manager.setCacheManager(getCacheManager());
        manager.setSessionManager(sessionManager());
        return manager;
    }

    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 添加jwt过滤器
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new JWTAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        shiroFilterFactoryBean.setSecurityManager(securityManager());
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/shiro/415");
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/api/**", "jwt"); // api
        filterChainDefinitionMap.put("/shiro/**", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/docs/**", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/upload/**", "anon");
        filterChainDefinitionMap.put("/files/**", "anon");
        filterChainDefinitionMap.put("/test/**", "anon");
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    //
    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment, "custom.");
    }
}
