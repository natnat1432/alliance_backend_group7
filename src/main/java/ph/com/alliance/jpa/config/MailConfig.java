package ph.com.alliance.jpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import java.util.Properties;

/**
 *     Mail configuration for email sending functions.
 *     Note: Environment variables are defined under 'server.properties' file.
 *    
 */

@Configuration

public class MailConfig {
    
    @Autowired
    private Environment env;
    
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        
        Properties mailProperties = new Properties();
        mailProperties.setProperty("mail.smtp.auth", "true");
        mailProperties.setProperty("mail.transport.protocol", "smtp");
        mailProperties.setProperty("mail.smtp.starttls.enable", "true");
        mailProperties.setProperty("mail.smtp.auth", "true");
        
        mailSender.setHost(env.getProperty("mail.server"));
        mailSender.setPort(Integer.parseInt(env.getProperty("mail.port")));
        mailSender.setUsername(env.getProperty("mail.userId"));
        mailSender.setPassword(env.getProperty("mail.password"));
        mailSender.setJavaMailProperties(mailProperties);
        return mailSender;
    }
}