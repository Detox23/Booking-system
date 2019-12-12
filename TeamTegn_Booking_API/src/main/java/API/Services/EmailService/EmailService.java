package API.Services.EmailService;

import API.Services.LogsService.EmailLogService;
import API.Services.LogsService.IEmailLogService;
import Shared.ForCreation.EmailLogDto;
import Shared.ForCreation.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EmailService implements IEmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private EmailLogService emailLogService;

   //
  // @Async
    public void sendSimpleMessage(final Mail mail){

       SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        message.setFrom(mail.getFrom());
       for (String receiver: mail.getTo() ) {
            message.setTo(receiver);
            emailLogService.log(new EmailLogDto(mail.getSenderType(), mail.getSenderId(), receiver));
            emailSender.send(message);

        }

    }
    }
