package com.github.wujie0628.auth.authorization.config;

import com.github.wujie0628.auth.authorization.exception.CustomWebResponseExceptionTranslator;
import com.github.wujie0628.auth.authorization.oauth2.CustomTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;

    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * jwt 对称加密密钥
     */
    @Value("${spring.security.oauth2.jwt.signingKey}")
    private String signingKey;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        // 支持将client参数放在header或body中
        oauthServer.allowFormAuthenticationForClients();
        oauthServer.tokenKeyAccess("isAuthenticated()")
                .checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 配置客户端信息，从数据库中读取，对应oauth_client_details表
//        clients.jdbc(dataSource);
        clients.inMemory()
                .withClient("clientA") //配置client_id
                .secret(passwordEncoder.encode("123456")) //配置client-secret
                .accessTokenValiditySeconds(3600) //配置访问token的有效期
                .refreshTokenValiditySeconds(864000) //配置刷新token的有效期
                .redirectUris("http://www.baidu.com") //配置redirect_uri，用于授权成功后跳转
                .scopes("all")  //配置申请的权限范围
                .authorizedGrantTypes("authorization_code", //授权码模式  //配置grant_type，表示授权类型
                        "password", //密码模式
                        "client_credentials",//客户端模式
                        "refresh_token"); //更新令牌

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        // 配置token的数据源、自定义的tokenServices等信息,配置身份认证器，配置认证方式，TokenStore，TokenGranter，OAuth2RequestFactory
        endpoints.tokenStore(tokenStore())
                .authorizationCodeServices(authorizationCodeServices())
                .approvalStore(approvalStore())
                .exceptionTranslator(customExceptionTranslator())
                .tokenEnhancer(tokenEnhancerChain())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
                //update by joe_chen add  granter
//                .tokenGranter(tokenGranter(endpoints));

    }

    /**
     * 自定义OAuth2异常处理
     *
     * @return CustomWebResponseExceptionTranslator
     */
    @Bean
    public WebResponseExceptionTranslator<OAuth2Exception> customExceptionTranslator() {
        return new CustomWebResponseExceptionTranslator();
    }

    /**
     * 授权信息持久化实现
     *
     * @return JdbcApprovalStore
     */
    @Bean
    public ApprovalStore approvalStore() {
        return new JdbcApprovalStore(dataSource);
    }

    /**
     * 授权码模式持久化授权码code
     *
     * @return JdbcAuthorizationCodeServices
     */
    @Bean
    protected AuthorizationCodeServices authorizationCodeServices() {
        // 授权码存储等处理方式类，使用jdbc，操作oauth_code表
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    /**
     * token的持久化
     *
     * @return JwtTokenStore
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    /**
     * 自定义token
     *
     * @return tokenEnhancerChain
     */
    @Bean
    public TokenEnhancerChain tokenEnhancerChain() {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(new CustomTokenEnhancer(), accessTokenConverter()));
        return tokenEnhancerChain;
    }

    /**
     * jwt token的生成配置
     *
     * @return
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(signingKey);
        return converter;
    }

    /**
     * 配置自定义的granter,手机号验证码登陆
     *
     * @param endpoints
     * @return
     * @auth joe_chen
     */
//    public TokenGranter tokenGranter(final AuthorizationServerEndpointsConfigurer endpoints) {
//        List<TokenGranter> granters = Lists.newArrayList(endpoints.getTokenGranter());
//        granters.add(new MobileTokenGranter(
//                authenticationManager,
//                endpoints.getTokenServices(),
//                endpoints.getClientDetailsService(),
//                endpoints.getOAuth2RequestFactory()));
//        return new CompositeTokenGranter(granters);
//    }

}