package ph.com.alliance.jpa.functions.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Service;

import ph.com.alliance.jpa.entity.UserEntity;
import ph.com.alliance.jpa.functions.user.dao.UserDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginService implements ILoginService, UserDetailsService, TokenEnhancer {

    @Autowired
    private UserDao userDao;
    
    private String userlogin;
    
    @Override
    public UserDetails loadUserByUsername(String strLoginId) throws UsernameNotFoundException {

    	UserEntity login = ((List<UserEntity>) userDao.findAll()).stream().filter(u -> u.getEmail().equals(strLoginId)).findFirst().orElse(null);
        userlogin = strLoginId;
        if (null != login ) {
        	
            return new User(strLoginId, login.getPassword(), getAuthorities(Arrays.asList("ROLE_ADMIN")));
        }
        throw new UsernameNotFoundException(strLoginId);
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        UserEntity login = ((List<UserEntity>) userDao.findAll()).stream().filter(u -> u.getEmail().equals(userlogin)).findFirst().orElse(null);
        final Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("userID", login.getUserID());
        additionalInfo.put("email", user.getUsername());
        additionalInfo.put("firstname", login.getFirstname());
        additionalInfo.put("lastname", login.getLastname());
        additionalInfo.put("user_type", login.getuser_type());
        additionalInfo.put("authorities", user.getAuthorities());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }

    private List<GrantedAuthority> getAuthorities(List<String> rolesList) {
        List<GrantedAuthority> authoritiesList = new ArrayList<>();
        
        if(!rolesList.isEmpty()){
            for (String role : rolesList) {
                authoritiesList.add(new SimpleGrantedAuthority(role));
            }
        }
        
        return authoritiesList;
    }
    
}
