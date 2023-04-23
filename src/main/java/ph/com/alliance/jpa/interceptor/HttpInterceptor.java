package ph.com.alliance.jpa.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HTTP request interceptor to avoid repetitive handler code
 *
 */
public class HttpInterceptor extends HandlerInterceptorAdapter{
    
    /**
     * Method before the actual handler will be executed
     * @param request
     * @param response
     * @param handler
     * 
     */
    public boolean preHandle(HttpServletRequest request, 
        HttpServletResponse response, Object handler)
        throws Exception {
                
        return true;
    }

    /**
     * Method after the handler is executed
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * 
     */
    public void postHandle(
        HttpServletRequest request, HttpServletResponse response, 
        Object handler, ModelAndView modelAndView)
        throws Exception {
        
    
    }
    
    /**
     * Method after the view is executed
     * @param request
     * @param response
     * @param handler
     * @param ex
     * 
     */
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
       
    }
}
