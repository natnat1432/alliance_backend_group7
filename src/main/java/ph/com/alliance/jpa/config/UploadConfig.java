package ph.com.alliance.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

/**
 * Configuration for multipart file upload
 *
 */

@Configuration
public class UploadConfig {
    
     @Bean
     public StandardServletMultipartResolver multipartResolver() {
         return new StandardServletMultipartResolver();
     }
     
}