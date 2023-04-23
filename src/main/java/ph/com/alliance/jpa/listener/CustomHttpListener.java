package ph.com.alliance.jpa.listener;

import org.springframework.web.context.support.WebApplicationContextUtils;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *     Implementation class for receiving notification events about HttpSession life cycle changes
 *
 */

public class CustomHttpListener implements HttpSessionListener, ServletContextListener {
    
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        WebApplicationContextUtils
            .getRequiredWebApplicationContext(sce.getServletContext())
            .getAutowireCapableBeanFactory()
            .autowireBean(this);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}