package ph.com.alliance.jpa.functions.email.service;

import ph.com.alliance.jpa.functions.email.model.SampleEmailModel;
import ph.com.alliance.jpa.functions.email.model.UserCreationEmailModel;

public interface EmailService  {

    void sendMail(SampleEmailModel arg0);
    void sendUserCreationMail(UserCreationEmailModel arg0);

}
