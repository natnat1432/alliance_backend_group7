## Keycloak Integration

> This Keycloak bearer-only client will need another Keycloak confidential client to provide the Authorization token upon testing.

### 1. Prepare the keycloak server.

### 2. Create a bearer-only access type client.

### 3. Export the client installation JSON as ```keycloak.properties``` and place it under ```src/main/resources``` folder. E.g:

```json
{
  "realm": "Alliance",
  "bearer-only": true,
  "auth-server-url": "http://192.168.211.218:9000/auth",
  "ssl-required": "external",
  "resource": "SpringJPA",
  "confidential-port": 0
}
```

### 4. Uncomment ```KeycloakSecurityConfig.java``` and delete ```OAuth2SecurityConfig.java``` file under ```ph.com.alliance.jpa.config``` to delegate the token authorization to Keycloak server.
 The following classes should be in the file:

```java
public class KeycloakSecurityConfig extends KeycloakWebSecurityConfigurerAdapter implements KeycloakConfigResolver{ }
class KeycloakUserDetailsAuthenticationProvider extends KeycloakAuthenticationProvider{ }
class KeycloakUserDetailsAuthenticationToken extends KeycloakAuthenticationToken{ }
```
### 5. To test, retrieve a token from the other Keycloak confidential client. 
A sample token response looks like this:
```json
{
  "access_token":"eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIi...",
  "expires_in":300,
  "refresh_expires_in":1800,
  "refresh_token":"eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUI...",
  "token_type":"bearer",
  "id_token":"eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2...",
  "not-before-policy":1560409015,
  "session_state":"730948d9-b666-4eac-ab3c-e145a8e7f09f",
  "scope":"openid profile email"
}
```

### 6. In [```\swagger-ui.html```](/setup/SwaggerUI.md). Use **"Bearer <access_token>"** as Authorization header.  