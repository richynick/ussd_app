package com.richard.ussd_app.providers;

import com.richard.ussd_app.dto.EmailDetails;
import com.richard.ussd_app.interfaces.IEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SmtpEmailing implements IEmail {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
     private String senderEmail;
    @Override
    public void sendEmail(EmailDetails emailDetails) {
        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(senderEmail);
            mailMessage.setTo(emailDetails.getRecipient());
            mailMessage.setText(emailDetails.getMessageBody());
            mailMessage.setSubject(emailDetails.getSubject());

            javaMailSender.send(mailMessage);
            System.out.println("Mail sent successfully") ;
        } catch (MailException e) {
            throw new RuntimeException(e);
        }
    }
}
