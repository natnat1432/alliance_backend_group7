package ph.com.alliance.jpa.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import ph.com.alliance.jpa.listener.CustomHttpListener;

import java.util.Set;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * This is the main bootstrap. Note the special interface, which is called on startup.
 * This declares the Spring contexts (root and mvc) and binds the dispatcher servlet.
 * 
 */
public class WebAppInitializer implements WebApplicationInitializer {
    private static final Logger logger = LoggerFactory.getLogger(WebApplicationInitializer.class);
    private static final String DISPATCHER_MAPPING_ROOT = "/*";
    private static final short STARTUP_PRIORITY = 1;

    public void onStartup(ServletContext servletContext) throws ServletException {
        
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(RootConfig.class);
        rootContext.setServletContext(servletContext);
        rootContext.refresh();

        servletContext.addListener(new ContextLoaderListener(rootContext));
        servletContext.setInitParameter("defaultHtmlEscape", "true");
        servletContext.addListener(new CustomHttpListener());
        
        AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
        rootContext.refresh();
        
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(mvcContext));
        dispatcher.setLoadOnStartup(STARTUP_PRIORITY);
        
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(
            rootContext.getEnvironment().getProperty("files.path"), 
            Integer.parseInt(rootContext.getEnvironment().getProperty("files.max_size")), 
            Integer.parseInt(rootContext.getEnvironment().getProperty("files.max_size")), 
            Integer.parseInt(rootContext.getEnvironment().getProperty("files.max_size"))
        );
                 
        dispatcher.setMultipartConfig(multipartConfigElement);
        
        Set<String> mappingConflicts = dispatcher.addMapping(DISPATCHER_MAPPING_ROOT);
        
        if (!mappingConflicts.isEmpty()) {
            for (String s : mappingConflicts) {
                logger.error("Mapping conflict: " + s);
            }
            throw new IllegalStateException("'dispatcher' cannot be mapped to '/' under Tomcat versions <= 7.0.14");
        }
        
        servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain")).addMappingForUrlPatterns(null, false, "/*");
        servletContext.addFilter("corsFilter", CorsConfig.class).addMappingForUrlPatterns(null, false, "/*");
    }
}