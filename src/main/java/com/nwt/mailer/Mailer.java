package com.nwt.mailer;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Mailer {
    private static String APP_URL = "http://localhost:8080/PMS-NWT";

    public static boolean sendActivationMail(String email, String token) {
        MessageBody messageBody = new MessageBody("NWT-Leptirici", "Activation mail");
        messageBody.addParagraph(" ");
        messageBody.addParagraph("<a href=\"" + APP_URL + "/auth/user/activate/" + token + "\">Activate</a>");
        return sendEmail(email, messageBody);
    }

    public static boolean sendRecoveryMail(String email, String token) {
        MessageBody messageBody = new MessageBody("NWT-Leptirici", "Recovery mail");
        messageBody.addParagraph(" ");
        messageBody.addParagraph("<a href=\"" + APP_URL + "/resetConfirmation?id=" + token + "\">Reset</a>");
        return sendEmail(email, messageBody);
    }

    public static boolean sendNewPassword(String email, String pass) {
        MessageBody messageBody = new MessageBody("NWT-Leptirici", "New password");
        messageBody.addParagraph(" ");
        messageBody.addParagraph("Your new password: <b>" + pass + "</b>");
        return sendEmail(email, messageBody);
    }

    public static boolean sendEmail(String mailTo, MessageBody messageBody, MailerAttachment attachment) {
        List<String> mails = new ArrayList<String>();
        mails.add(mailTo);
        return sendEmail(mails, messageBody, attachment);
    }

    public static boolean sendEmail(String mailTo, MessageBody messageBody) {
        List<String> mails = new ArrayList<String>();
        mails.add(mailTo);
        return sendEmail(mails, messageBody, new ArrayList<MailerAttachment>());
    }

    public static boolean sendEmail(List<String> mailTo, MessageBody messageBody) {
        return sendEmail(mailTo, messageBody, new ArrayList<MailerAttachment>());
    }

    public static boolean sendEmail(List<String> mailTo, MessageBody messageBody, MailerAttachment attachment) {
        List<MailerAttachment> attachments = new ArrayList<MailerAttachment>();
        attachments.add(attachment);
        return sendEmail(mailTo, messageBody, attachments);
    }

    public static boolean sendEmail(List<String> mailTo, MessageBody messageBody, List<MailerAttachment> attachments) {
        // Sender's email ID needs to be mentioned
        try {

            Boolean auth = true;
            String from = "leptirici.nwt@gmail.com";
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.auth", "true");
            //props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.port", "587");

            final String username = from;
            final String password = "sinansakic";
            Session session;
            if (auth) {
                session = Session.getInstance(props,
                        new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(username, password);
                            }
                        });
            } else {
                session = Session.getInstance(props);
            }

            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));
            // Set To: header field of the header.
            InternetAddress[] addressTo = new InternetAddress[mailTo.size()];
            for (int i = 0; i < mailTo.size(); i++) {
                addressTo[i] = new InternetAddress(mailTo.get(i));
            }
            message.setRecipients(Message.RecipientType.TO, addressTo);
            // Set Subject: header field
            message.setSubject(messageBody.getSubject());

            if (attachments.size() == 0) {
                message.setContent(messageBody.getContent(),
                        "text/html; charset=UTF-8");
            } else {
                BodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setText(messageBody.getText());
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);

                for (MailerAttachment attachment : attachments) {
                    DataSource dataSource = new ByteArrayDataSource(attachment.getFileBody(), "text/plain");
                    messageBodyPart = new MimeBodyPart();
                    messageBodyPart.setDataHandler(new DataHandler(dataSource));
                    messageBodyPart.setFileName(attachment.getFileName());
                    multipart.addBodyPart(messageBodyPart);
                }

                message.setContent(multipart);


            }
            Transport.send(message);
        } catch (Exception e) {

            return false;
        }

        return true;
    }

}
