//package API.Services.EmailService;
//
//import API.Services.LogsService.EmailLogService;
//import Shared.ForCreation.EmailLogDto;
//import Shared.ForCreation.Mail;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EmailService {
//
//    @Autowired
//    private JavaMailSender emailSender;
//
//    @Autowired
//    private EmailLogService emailLogService;
//
//   // @Async
//    public void sendSimpleMessage(final Mail mail){
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setSubject(mail.getSubject());
//        message.setText(mail.getContent());
//        message.setFrom(mail.getFrom());
//        for (String receiver: mail.getTo() ) {
//            message.setTo(receiver);
//
//            emailSender.send(message);
//            emailLogService.log(new EmailLogDto("objectTyp", 2, mail.getFrom()));
//
//        }
//
//    }
//    }
