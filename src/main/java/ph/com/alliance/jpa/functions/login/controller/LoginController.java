
package ph.com.alliance.jpa.functions.login.controller;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {
        
    @Resource(name="tokenServices")
    ConsumerTokenServices tokenServices;
    
    @Resource(name="tokenStore")
    TokenStore tokenStore;

    @RequestMapping(value = "/logout/{tokenId}", method = RequestMethod.DELETE)
    public void logout(@PathVariable String tokenId)  {
        tokenServices.revokeToken(tokenId);    
    }
    
    @RequestMapping(value = "/tokenValidation/{tokenId}", method = RequestMethod.GET)
    public ResponseEntity<String> validateToken(@PathVariable String tokenId) {
    	  if (!tokenStore.readAccessToken(tokenId).isExpired()) {
              return ResponseEntity.ok("Valid Token");
          }
    	  else {
    		  return ResponseEntity.ok("Invalid Token");
    	  }
    }
}
