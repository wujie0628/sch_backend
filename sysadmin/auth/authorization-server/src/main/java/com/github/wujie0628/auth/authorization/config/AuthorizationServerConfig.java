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
     * jwt ??????????????????
     */
    @Value("${spring.security.oauth2.jwt.signingKey}")
    private String signingKey;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        // ?????????client????????????header???body???
        oauthServer.allowFormAuthenticationForClients();
        oauthServer.tokenKeyAccess("isAuthenticated()")
                .checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // ??????????????????????????????????????????????????????oauth_client_details???
//        clients.jdbc(dataSource);
        clients.inMemory()
                .withClient("clientA") //??????client_id
                .secret(passwordEncoder.encode("123456")) //??????client-secret
                .accessTokenValiditySeconds(3600) //????????????token????????????
                .refreshTokenValiditySeconds(864000) //????????????token????????????
                .redirectUris("http://www.baidu.com") //??????redirect_uri??????????????????????????????
                .scopes("all")  //???????????????????????????
                .authorizedGrantTypes("authorization_code", //???????????????  //??????grant_type?????????????????????
                        "password", //????????????
                        "client_credentials",//???????????????
                        "refresh_token"); //????????????

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        // ??????token???????????????????????????tokenServices?????????,?????????????????????????????????????????????TokenStore???TokenGranter???OAuth2RequestFactory
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
     * ?????????OAuth2????????????
     *
     * @return CustomWebResponseExceptionTranslator
     */
    @Bean
    public WebResponseExceptionTranslator<OAuth2Exception> customExceptionTranslator() {
        return new CustomWebResponseExceptionTranslator();
    }

    /**
     * ???????????????????????????
     *
     * @return JdbcApprovalStore
     */
    @Bean
    public ApprovalStore approvalStore() {
        return new JdbcApprovalStore(dataSource);
    }

    /**
     * ?????????????????????????????????code
     *
     * @return JdbcAuthorizationCodeServices
     */
    @Bean
    protected AuthorizationCodeServices authorizationCodeServices() {
        // ??????????????????????????????????????????jdbc?????????oauth_code???
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    /**
     * token????????????
     *
     * @return JwtTokenStore
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    /**
     * ?????????token
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
     * jwt token???????????????
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
     * ??????????????????granter,????????????????????????
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