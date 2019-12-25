package API.Services.EmailService;

import API.Services.LogsService.EmailLogService;
import Shared.ForCreation.EmailLogDto;
import Shared.ForCreation.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService implements IEmailService {


    private JavaMailSender emailSender;

    private EmailLogService emailLogService;

    @Autowired
    public void setEmailSender(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Autowired
    public void setEmailLogService(EmailLogService emailLogService) {
        this.emailLogService = emailLogService;
    }

    public void sendSimpleMessage(final Mail mail) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        message.setFrom(mail.getFrom());
        for (String receiver : mail.getTo()) {
            message.setTo(receiver);
            emailLogService.log(new EmailLogDto(mail.getSenderType(), mail.getSenderId(), receiver));
            emailSender.send(message);
        }
    }
}
