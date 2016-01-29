package com.nwt.mailer;

import com.nwt.facade.EntityFacade;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Mailer
{
    private String registrationMail;
    private String password;
    private String APP_URL = "http://localhost:18080/PMS-NSI";

    public Mailer(String registrationMail, String password,String appUrl)
    {
        this.registrationMail = registrationMail;
        this.password = password;
        this.APP_URL=appUrl;
    }

    public boolean sendActivationMail(String email, String token)
    {
        MessageBody messageBody = new MessageBody("PMS - Registration", "Activation mail");
        messageBody.addParagraph(" ");
        messageBody.addParagraph("This is activation message form Project Managemens System. To activate your account click \"Activate\".");
        messageBody.addParagraph("<a href=\"" + APP_URL + "/auth/user/activate/" + token + "\">Activate</a>");
        return sendEmail(email, messageBody);
    }

    public boolean sendRecoveryMail(String email, String token)
    {
        MessageBody messageBody = new MessageBody("NSI - Recovery", "Recovery mail");
        messageBody.addParagraph(" ");
        messageBody.addParagraph("<a href=\"" + APP_URL + "/resetConfirmation?id=" + token + "\">Reset</a>");
        return sendEmail(email, messageBody);
    }

    public boolean sendNewPassword(String email, String pass)
    {
        MessageBody messageBody = new MessageBody("NSI", "New password");
        messageBody.addParagraph(" ");
        messageBody.addParagraph("Your new password: <b>" + pass + "</b>");
        return sendEmail(email, messageBody);
    }

    public boolean sendEmail(String mailTo, MessageBody messageBody, MailerAttachmentImpl attachment)
    {
        List<String> mails = new ArrayList<String>();
        mails.add(mailTo);
        return sendEmail(mails, messageBody, attachment);
    }

    public boolean sendEmail(String mailTo, MessageBody messageBody)
    {
        List<String> mails = new ArrayList<String>();
        mails.add(mailTo);
        return sendEmail(mails, messageBody, new ArrayList<MailerAttachmentImpl>());
    }

    public boolean sendEmail(List<String> mailTo, MessageBody messageBody)
    {
        return sendEmail(mailTo, messageBody, new ArrayList<MailerAttachmentImpl>());
    }

    public boolean sendEmail(List<String> mailTo, MessageBody messageBody, MailerAttachmentImpl attachment)
    {
        List<MailerAttachmentImpl> attachments = new ArrayList<MailerAttachmentImpl>();
        attachments.add(attachment);
        return sendEmail(mailTo, messageBody, attachments);
    }

    public boolean sendEmail(List<String> mailTo, MessageBody messageBody, List<MailerAttachmentImpl> attachments)
    {
        // Sender's email ID needs to be mentioned
        try
        {

            Boolean auth = true;
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.auth", "true");
            //props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.port", "587");

            final String username = registrationMail;
            final String password = this.password;
            Session session;
            if (auth)
            {
                session = Session.getInstance(props, new javax.mail.Authenticator()
                {
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication(username, password);
                    }
                });
            } else
            {
                session = Session.getInstance(props);
            }

            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(registrationMail));
            // Set To: header field of the header.
            InternetAddress[] addressTo = new InternetAddress[mailTo.size()];
            for (int i = 0; i < mailTo.size(); i++)
            {
                addressTo[i] = new InternetAddress(mailTo.get(i));
            }
            message.setRecipients(Message.RecipientType.TO, addressTo);
            // Set Subject: header field
            message.setSubject(messageBody.getSubject());

            if (attachments.size() == 0)
            {
                message.setContent(messageBody.getContent(), "text/html; charset=UTF-8");
            } else
            {
                BodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setText(messageBody.getText());
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);

                for (MailerAttachmentImpl attachment : attachments)
                {
                    DataSource dataSource = new ByteArrayDataSource(attachment.getFileBody(), "text/plain");
                    messageBodyPart = new MimeBodyPart();
                    messageBodyPart.setDataHandler(new DataHandler(dataSource));
                    messageBodyPart.setFileName(attachment.getFileName());
                    multipart.addBodyPart(messageBodyPart);
                }

                message.setContent(multipart);


            }
            Transport.send(message);
        } catch (Exception e)
        {

            return false;
        }

        return true;
    }

}
