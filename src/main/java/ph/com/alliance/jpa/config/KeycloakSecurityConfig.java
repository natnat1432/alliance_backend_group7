//package ph.com.alliance.jpa.config;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.PrintWriter;
//import java.security.Principal;
//import java.util.Collection;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.keycloak.adapters.KeycloakConfigResolver;
//import org.keycloak.adapters.KeycloakDeployment;
//import org.keycloak.adapters.KeycloakDeploymentBuilder;
//import org.keycloak.adapters.OidcKeycloakAccount;
//import org.keycloak.adapters.spi.HttpFacade.Request;
//import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
//import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
//import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
//import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
//import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
//import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
//import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
//import org.springframework.util.Assert;
//
///**
// *     Configuration to customize springSecurityFilterChain to use Keycloak
// *     authentication prior to accessing defined URL within the application.
// *
// */
//
//
//@Configuration
//@EnableWebSecurity
//@KeycloakConfiguration
//@ComponentScan(basePackageClasses = KeycloakSecurityComponents.class)
//public class KeycloakSecurityConfig extends KeycloakWebSecurityConfigurerAdapter implements KeycloakConfigResolver{
//    @Override
//    public KeycloakDeployment resolve(Request facade) {
//        InputStream is = getClass().getResourceAsStream("/keycloak.properties");
//        return KeycloakDeploymentBuilder.build(is);
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(keycloakAuthenticationProvider());
//    }
//    
//    @Bean
//    public KeycloakUserDetailsAuthenticationProvider keycloakUserDetailsAuthenticationProvider() {
//      KeycloakUserDetailsAuthenticationProvider auth = new KeycloakUserDetailsAuthenticationProvider();
//      auth.setGrantedAuthoritiesMapper(grantedAuthoritiesMapper());
//      return auth;
//    }
//    
//    @Bean
//    public GrantedAuthoritiesMapper grantedAuthoritiesMapper() {
//      SimpleAuthorityMapper mapper = new SimpleAuthorityMapper();
//      mapper.setConvertToUpperCase(true);
//      return mapper;
//    }
//  
//    @Bean
//    public AuthenticationEntryPoint authenticationEntryPoint(){
//      return new  AuthenticationEntryPoint(){
//
//        @Override
//        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
//                throws IOException, ServletException {
//            response.addHeader("Access-Control-Allow-Origin", "null");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            PrintWriter writer = response.getWriter();
//            writer.println("HTTP Status " + HttpServletResponse.SC_UNAUTHORIZED + " - " + authException.getMessage());
//            
//        }          
//      };
//    }
//    
//    @Bean
//    @Override
//    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
//      return new NullAuthenticatedSessionStrategy();
//    }
//
//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//      super.configure(http);
//      http.
//          csrf().disable().cors()
//          .and().authorizeRequests().anyRequest().authenticated()
//          .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
//          .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }
//    
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**/**");
//        web.ignoring().antMatchers("/v2/api-docs", "/configuration/**", "/swagger-resources/**",  "/swagger-ui.html", "/webjars/**", "/api-docs/**");
//    }
//
//}
//
//class KeycloakUserDetailsAuthenticationProvider extends KeycloakAuthenticationProvider{
//
//    
//    @Autowired
//    private UserDetailsService userDataService;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) super.authenticate(authentication);
//        String username;
//        UserDetails userDetails;
//
//        if (token == null) {
//            return null;
//        }
//
//        username = this.resolveUsername(token);
//        userDetails = userDataService.loadUserByUsername(username);
//
//        return new KeycloakUserDetailsAuthenticationToken(userDetails, token.getAccount(), token.getAuthorities());
//    }
//
//    protected String resolveUsername(KeycloakAuthenticationToken token) {
//
//        Assert.notNull(token, "KeycloakAuthenticationToken required");
//        Assert.notNull(token.getAccount(), "KeycloakAuthenticationToken.getAccount() cannot be return null");
//        OidcKeycloakAccount account = token.getAccount();
//        Principal principal = account.getPrincipal();
//
//        return principal.getName();
//    }
//}
//
//class KeycloakUserDetailsAuthenticationToken extends KeycloakAuthenticationToken{
//
//    private static final long serialVersionUID = 1L;
//    
//    private UserDetails userDetails;
//
//    public KeycloakUserDetailsAuthenticationToken(UserDetails userDetails, OidcKeycloakAccount account,
//            Collection<? extends GrantedAuthority> authorities) {
//        super(account, false ,authorities);
//        Assert.notNull(userDetails, "UserDetails required");
//        this.userDetails = userDetails;
//    }
//
//    @Override
//    public Object getPrincipal() {
//        return userDetails;
//    }
//}
