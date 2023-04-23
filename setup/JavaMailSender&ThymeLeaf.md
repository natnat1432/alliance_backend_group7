## JavaMailSender+ThymeLeaf

### 1. Update ```resources\app.properties``` to configure the **JavaMailSender** settings.

The code block below shows the default mail properties using [mailtrap.io](https://mailtrap.io/). To use it, create an account and copy the inbox SMTP Credentials to the said properties file.

```properties
#Mail Settings
mail.server=smtp.mailtrap.io
mail.port=2525
mail.userId=93adff233cf496
mail.password=c066912cc376b1
mail.from=test@email.com
```

### 2. Create the html mail template under ```resources\templates```.

```SampleEmailTemplate.html```

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<body>
    <table>
        <tr>
            <td>
                <p>Hi <span th:text="${name}"></span>,</p>
                <p>Sending Sample Email</p>
                <p>Thanks,</p>
                <p><span th:text="${signature}"></span></p>
            </td>
        </tr>
    </table>
</body>
</html>
```

> ```th:text="${..}"``` expressions hold the variables to be used by the template.

### 3. Create a model to hold the email template variables.

```java

public class SampleEmailModel {

	private String name;

	private String signature;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
}
```

### 4. Provide all email details in the email sending service method to the **MailModel**.

* __mailTo__ *(required)* –  List of email recipients
 * __mailFrom__ *(required)* – Sender email address, set as ```${mail.from}``` in ```resources\app.properties```
 * __mailCC__ – Additional mail recipients
 * __mailSubject__ *(required)* – Mail subject
 * __mailTemplate__ *(required)* – Name of email template file.
 * __filePath__ – File attachment path
 * __fileName__ – File attachment name
 * __mailVariables__ *(required)* – Email template model converted to Map object

 ### 5. Send the MailModel object to the Rabbit queue and the MailSender will process it. Read more about RabbitMQ configuration and usage [here](setup/RabbitMQ.md).