
# Spring-JPA-Hibernate Base Code

This Spring-JPA-Hibernate Base Code includes the following:

*  [Spring Rest Framework](https://spring.io/projects/spring-framework) 

*  [Spring Data JPA](https://spring.io/projects/spring-data-jpa) 

*  [Hibernate ORM](http://hibernate.org/orm/) 

*  [Swagger UI](https://idratherbewriting.com/learnapidoc/pubapis_swagger.html)

*  [OAuth2](https://stormpath.com/blog/what-the-heck-is-oauth)

*  [Keycloak Spring Security Adapter](https://wjw465150.gitbooks.io/keycloak-documentation/securing_apps/topics/oidc/java/spring-security-adapter.html)

*  [RabbitMQ](https://www.cloudamqp.com/blog/2015-05-18-part1-rabbitmq-for-beginners-what-is-rabbitmq.html)
  

## Requirements

1.  [Java 8](https://www.java.com/en/download/)

2.  [Eclipse](https://www.eclipse.org/downloads/packages/installer)

3.  [Tomcat Server](https://tomcat.apache.org/index.html)

4.  [Erlang/OTP](http://www.erlang.org/downloads)
  
5.  [Rabbit MQ Server](https://www.rabbitmq.com/download.html)

## Project setup

 - [ ] [Setup the database](/setup/DatabaseSetup.md).
  
 - [ ] Open this maven project on Eclipse.  [ **File** > **Import** > **Maven** > **Existing maven projects** ]

 - [ ] Check th JRE version the project is using. 
  In your Eclipse IDE,  [**Project Explorer** > **Right click project** > **Properties** > **Java Build Path** > **Library** ]
  If it is not on Java 1.8 or higher, download a JRE [here](https://www.oracle.com/technetwork/java/javase/downloads/index.html) and add it as an external library.

 - [ ] Check if you have an existing Tomcat server for Eclipse. If not, download a version [here](https://tomcat.apache.org/download-90.cgi), add  [**File** > **New** > **Server** > **Apache**] and use it as Target Runtime to the project [**Project Explorer** > **Right click project** > **Properties** > **Targeted runtimes** ].

 - **IF** this project is for an external client, delete ```KeycloakSecurityConfig.java``` under ```ph.com.alliance.jpa.config```, *Keycloak Adapter* section in ```pom.xml```, and ```keycloak.properties``` under ```src/main/resources``` folder and uncomment ```OAuth2SecurityConfig.java```. **ELSE**, if this is an Alliance internal project, refer to [Keycloak Integration](/setup/KeycloakIntegration.md) for more details.

 - [ ] Clean the project. 
	1. **Project** > **Clean** > **Select project** 
	2. **Project Explorer** > **Right click project** > **Debug As** > **Maven clean**
	3. **Project Explorer** > **Right click project** > **Maven** > **Update project**
 - [ ] Debug/Run. 
 - [ ] Proceed to ```\swagger-ui.html``` to [test the APIs](setup/SwaggerUI.md).
  

  