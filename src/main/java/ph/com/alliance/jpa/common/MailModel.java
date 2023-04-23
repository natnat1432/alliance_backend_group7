package ph.com.alliance.jpa.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * The persistent class for the email structure
 * 
 */

public class MailModel implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private List<String> mailTo ;
	private String mailFrom;
	private List<String> mailCC;
	private String mailSubject;
	private String mailTemplate;
	private String filePath;
	private String fileName;	
	private Map<String, Object> mailVariables;
	
	public List<String> getMailTo() {
		return mailTo;
	}

	public void setMailTo(List<String> mailTo) {
		this.mailTo = mailTo;
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public List<String> getMailCC() {
		return mailCC;
	}

	public void setMailCC(List<String> mailCC) {
		this.mailCC = mailCC;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public String getFilePath() {
		return filePath;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

    public Map<String, Object> getMailVariables() {
        return mailVariables;
    }

    public void setMailVariables(Map<String, Object> mailVariables) {
        this.mailVariables = mailVariables;
    }

    public String getMailTemplate() {
        return mailTemplate;
    }

    public void setMailTemplate(String mailTemplate) {
        this.mailTemplate = mailTemplate;
    }
	
}
