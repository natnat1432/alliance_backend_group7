package ph.com.alliance.jpa.functions.email.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import ph.com.alliance.jpa.common.MailModel;
import ph.com.alliance.jpa.functions.email.model.SampleEmailModel;
import ph.com.alliance.jpa.functions.email.model.UserCreationEmailModel;

@Service
public class EmailServiceImp implements EmailService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbit.exchange}")
    private String exchange;
    
    @Value("${mail.from}")
    private String mailFrom;
    
    @SuppressWarnings("unchecked")
    @Override
    public void sendMail(SampleEmailModel input) {
        
        MailModel mail =  new MailModel();
        ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> mailMap = oMapper.convertValue(input, Map.class);
        mail.setMailVariables(mailMap);
        
        mail.setMailFrom(mailFrom);
        
        mail.setMailSubject("[SampleEmail] Subject");
        
        List<String> mailTo = new ArrayList<String>();
        mailTo.add("sampleTo@mail.com");
        mail.setMailTo(mailTo);
        
        List<String> mailCC = new ArrayList<String>();
        mailCC.add("sampleCC@mail.com");
        mail.setMailCC(mailCC);
        
        mail.setMailTemplate("SampleEmailTemplate");

        try {
            rabbitTemplate.convertAndSend(exchange, "spring.rabbit.mail", mail);
        } catch (AmqpException e) {
            e.printStackTrace();
        }       
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public void sendUserCreationMail(UserCreationEmailModel input) {
        
        MailModel mail =  new MailModel();
        ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> mailMap = oMapper.convertValue(input, Map.class);
        mail.setMailVariables(mailMap);
        
        mail.setMailFrom(mailFrom);
        
        mail.setMailSubject("Alliance Service Charge User Credential");
        
        List<String> mailTo = new ArrayList<String>();
        mailTo.add(input.getEmail_to());
        mail.setMailTo(mailTo);
       
        mail.setMailTemplate("UserCreationEmailTemplate");

        try {
            rabbitTemplate.convertAndSend(exchange, "spring.rabbit.mail", mail);
        } catch (AmqpException e) {
            e.printStackTrace();
        }       
    }
    
}
