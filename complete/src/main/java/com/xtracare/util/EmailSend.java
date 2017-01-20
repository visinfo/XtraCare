package com.xtracare.util;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;






public class EmailSend extends Thread {
    private class SMTPAuthenticator extends javax.mail.Authenticator {

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            String username = SMTP_AUTH_USER;
            String password = SMTP_AUTH_PWD;
            return new PasswordAuthentication(username, password);
        }
    }

    private static final String emailFromAddress =Utility.getConfigValue("sysEmail");

    private static final String emailSubjectTxt = "Xtra Care Services";
           
    //static Log logger = LogFactory.getLog(MailSend.class);
    private static final String SMTP_AUTH_PWD = Utility.getConfigValue("sysEmailPassword");
    private static final String SMTP_AUTH_USER = Utility.getConfigValue("sysEmail");
    private static final String SMTP_HOST_NAME = "smtp.gmail.com";
    private static final String SMTP_PORT = "465";
    private String emailAddress;

    private String emailMsgTxt;

    // default constructor
    public EmailSend() {

    }

    // argumented constructor
    public EmailSend(String emailAddress, String emailMsgTxt) {
      
        this.emailAddress = emailAddress;
        this.emailMsgTxt = emailMsgTxt;
    }

    private void postMail(String recipient, String subject, String message,
            String from){
        // Set the host smtp address
        Properties props = System.getProperties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.user", SMTP_AUTH_USER);
        props.setProperty("mail.password", SMTP_AUTH_PWD);

        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.setProperty("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.auth", "true");
        // props.put("mail.debug", "true");
        props.put("mail.smtp.socketFactory.port", SMTP_PORT);
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        try{
            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getDefaultInstance(props, auth);
            Message msg = new MimeMessage(session);
            InternetAddress addressFrom = new InternetAddress(from);
            msg.setFrom(addressFrom);
            InternetAddress addressTo = new InternetAddress(recipient);
            msg.setRecipient(Message.RecipientType.TO, addressTo);
            msg.setSubject(subject);
            msg.setContent(message, "text/html");
            Transport tr = session.getTransport("smtp");
            tr.connect(SMTP_HOST_NAME, SMTP_AUTH_USER, SMTP_AUTH_PWD);
           
            tr.sendMessage(msg, msg.getAllRecipients());
            tr.close();
            session = null;
            
        }catch(javax.mail.AuthenticationFailedException AFE){
        	AFE.printStackTrace();
            
        }catch(javax.mail.MessagingException ME){
        	ME.printStackTrace();
          
        }
    }

    @Override
    public void run() {
      
        try {
            postMail(emailAddress, emailSubjectTxt, emailMsgTxt,
                    emailFromAddress);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
