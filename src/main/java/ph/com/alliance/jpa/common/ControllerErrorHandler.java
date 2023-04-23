package ph.com.alliance.jpa.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;


/**
 * The ControllerErrorHandler class is the global exception handler
 * for classes using the @Controller or @RestController annotation.
 * 
 */

@ControllerAdvice
public class ControllerErrorHandler {
    
    @SuppressWarnings("unused")
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResult> handleContentNotAllowedException(Exception e, HandlerMethod handlerMethod) {
        Class<?> controllerClass = handlerMethod.getMethod().getDeclaringClass();
        return new ResponseEntity<>(ApiResult.CreateError(e.toString()), HttpStatus.BAD_REQUEST);
    }
}
