package ph.com.alliance.jpa.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Bootstrap for service layer.
 * 
 */
@EnableWebMvc
@Configuration
@PropertySource({ "classpath:app.properties" })
@ComponentScan(basePackages = { "ph.com.alliance.jpa" })
public class RootConfig {

}