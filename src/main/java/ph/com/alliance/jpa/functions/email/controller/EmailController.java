
package ph.com.alliance.jpa.functions.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ph.com.alliance.jpa.common.ApiResult;
import ph.com.alliance.jpa.functions.email.model.SampleEmailModel;
import ph.com.alliance.jpa.functions.email.service.EmailService;


@RestController
@RequestMapping("/mail")
public class EmailController {
    
    @Autowired
    private EmailService emailService;
    
    private static final String STR_CONTROLLER_NAME ="Email";
    
    @RequestMapping(value = "/sendMail", method = RequestMethod.POST)
    public ApiResult sendMail(SampleEmailModel arg0) {
        emailService.sendMail(arg0);
        return ApiResult.CreateSuccess(STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.UPDATE);
    }    
}
