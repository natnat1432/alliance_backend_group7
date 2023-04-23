## Test the APIs

APIs can be tested through Postman or using ```/swagger-ui.html```.
> Resources are protected with OAuth2 authorization, and therefore a valid access token is needed for each request.

#### What is OAuth2?
 OAuth 2 is an authorization framework that enables applications to obtain limited access to user accounts on an HTTP service, such as Facebook, GitHub, and DigitalOcean. It works by delegating user authentication to the service that hosts the user account, and authorizing third-party applications to access the user account. OAuth 2 provides authorization flows for web and desktop applications, and mobile devices.   

  > For a sample on how user authentication was implemented in the base code with a dummy user table, see ```ph.com.alliance.jpa.functions.login.service``` and how it was used in ```OAuth2Config```.
 ---

### Postman

1. Run/Debug.
2. On using postman, set Request Method type and enter the request URL.
3. Request an access token.[**Authorization** > **OAuth2.0** > **Get New Access Token**]. Choose **Password Credentials** as grant type. Fill in the details and click **Request Token**.
4. When the server grants the token, click **Use Token**.
5. Set the required headers, body, or parameters. 
6. click **Send**.

*For more information on using Postman, click [here](https://www.getpostman.com/docs/v6/postman/sending_api_requests/requests).*

 ---

### ```/swagger-ui.html```

1. Run/Debug.
2. Proceed to ```\swagger-ui.html```. Click **Authorize** button.
3. Fill in the details and click **Authorize**.
4. Go to desired request and set parameters.
5. Click **Execute**.

*For more information on using Swagger UI, click [here](https://idratherbewriting.com/learnapidoc/pubapis_swagger.html).*