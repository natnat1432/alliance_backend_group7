package ph.com.alliance.jpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ph.com.alliance.jpa.interceptor.HttpInterceptor;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;

/**
 * Swagger configuration
 *
 */

@Configuration
@EnableSwagger2
@SuppressWarnings("deprecation")
public class SwaggerConfig extends WebMvcConfigurerAdapter{     
    
    @Autowired
    private ServletContext servletContext;
    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("ph.com.alliance.jpa"))              
          .paths(PathSelectors.any())                          
          .build()
          .securitySchemes(Collections.singletonList(oauth()));
    }
    
    @Bean
    SecurityScheme oauth() {
        try {
            Class.forName("ph.com.alliance.jpa.config.KeycloakSecurityConfig");
            return new ApiKey("Authorization", "Authorization", "header");
        } catch (ClassNotFoundException e) {
            return new OAuthBuilder().name("OAuth2").grantTypes(grantTypes()).build();
        }

    }

    @Bean
    List<GrantType> grantTypes() {
        List<GrantType> grantTypes = new ArrayList<>();
        grantTypes.add(new ResourceOwnerPasswordCredentialsGrant(servletContext.getContextPath() + "/oauth/token"));
        return grantTypes;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpInterceptor());
    }

}
