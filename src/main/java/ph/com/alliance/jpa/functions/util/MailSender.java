package ph.com.alliance.jpa.functions.util;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import ph.com.alliance.jpa.common.MailModel;

/**
 * Service implementation class for Mail Service module
 * 
 */

@Component
public class MailSender  {

    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private SpringTemplateEngine templateEngine;

    @RabbitListener(queues = "${rabbit.Q}")
	public void sendMail(MailModel mailModel) throws MessagingException {
        
        try {
        	
        	MimeMessage message = mailSender.createMimeMessage();
        	MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

        	
        	for(String sendTo:mailModel.getMailTo()){
        		messageHelper.addTo(sendTo);
        	}
        	
        	
        	if(null!=mailModel.getMailCC()){
        		for(String sendCc:mailModel.getMailCC()){
        		messageHelper.addCc(sendCc);
        		}
        	}    
        	
        	messageHelper.setFrom(mailModel.getMailFrom());
        	        	
        	messageHelper.setSubject(mailModel.getMailSubject());
        	
            Context context = new Context();
            context.setVariables(mailModel.getMailVariables());
            String html = templateEngine.process(mailModel.getMailTemplate(), context);
            messageHelper.setText(html,true);
            
            mailSender.send(message);
        } catch (MailException exception) {
            throw exception;
        } catch (MessagingException exception) {
            throw exception;
        } catch (Exception exception) {
            throw exception;
        }
    }

    public void sendMailWithAttachment(MailModel mailModel) throws MessagingException {
    	try {        	
        	MimeMessage message = mailSender.createMimeMessage();
        	MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
        	
        	for(String sendTo:mailModel.getMailTo()){
        		messageHelper.addTo(sendTo);
        	}
        	        	
        	for(String sendCc:mailModel.getMailCC()){
        		messageHelper.addCc(sendCc);
        	}        	
        	
        	messageHelper.setFrom(mailModel.getMailFrom());
        	        	
        	messageHelper.setSubject(mailModel.getMailSubject());
        	        	
        	Context context = new Context();
            context.setVariables(mailModel.getMailVariables());
            String html = templateEngine.process(mailModel.getMailTemplate(), context);
            messageHelper.setText(html,true);
        	
        	messageHelper.addAttachment(mailModel.getFileName(), new FileSystemResource(
					new File(mailModel.getFilePath())));
        	
            mailSender.send(message);
        } catch (MailException exception) {
            throw exception;
        } catch (MessagingException exception) {
            throw exception;
        } catch (Exception exception) {
            throw exception;
        }
    }
}