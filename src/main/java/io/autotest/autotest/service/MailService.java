package io.autotest.autotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

@Service
public class MailService {

    private final JavaMailSender mailSender;
    private final String smtpAuthEnabled;
    private final String smtpUser;
    private final String smtpPassword;

    @Autowired
    public MailService(JavaMailSender mailSender,
                       @Value("${smtp.auth-enabled}") String smtpAuthEnabled,
                       @Value("${smtp.user}") String smtpUser,
                       @Value("${smtp.password}") String smtpPassword) {
        this.mailSender = mailSender;
        this.smtpAuthEnabled = smtpAuthEnabled;
        this.smtpUser = smtpUser;
        this.smtpPassword = smtpPassword;
    }

    public void sendEmail(String from, String subject, String content, File attachment, String... receivers) throws MessagingException {

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.auth", smtpAuthEnabled);
        props.setProperty("mail.smtp.user", smtpUser);
        props.setProperty("mail.smtp.password", smtpUser);
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.debug", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(smtpUser, smtpPassword);
                    }
                });

        Transport transport = session.getTransport();
        InternetAddress addressFrom = new InternetAddress(from);


        MimeMessage message = new MimeMessage(session);
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        helper.setText(content, true);
        helper.setTo(receivers);
        helper.setSubject(subject);
        helper.setFrom(from);

//        3) create MimeBodyPart object and set your message text
        BodyPart messageBodyPart1 = new MimeBodyPart();
        messageBodyPart1.setText(content);
        messageBodyPart1.setContent(content, "text/html");

        //4) create new MimeBodyPart object and set DataHandler object to this object
        MimeBodyPart messageBodyPart2 = new MimeBodyPart();

        DataSource source = new FileDataSource(attachment);
        messageBodyPart2.setDataHandler(new DataHandler(source));
        messageBodyPart2.setFileName(attachment.getName());


        //5) create Multipart object and add MimeBodyPart objects to this object
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart1);
        multipart.addBodyPart(messageBodyPart2);

        //6) set the multiplart object to the message object
        message.setContent(multipart);
//        message.setFrom(new InternetAddress(from));
//        message.setSender(addressFrom);
//        message.setSubject(subject);
//        message.setContent(content, "text/plain");

//        Set<InternetAddress> addresses = new HashSet<>();
//        for (String receiver : receivers) {
//            addresses.add(new InternetAddress(receiver));
//        }
//        message.setRecipients(Message.RecipientType.TO, addresses.toArray(InternetAddress[]::new));

        transport.connect();
        Transport.send(message);
        transport.close();
    }
}
