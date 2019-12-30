package API.Controllers;

import API.Services.EmailService.IEmailService;
import Shared.ForCreation.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/emails")
public class EmailController {

    private IEmailService emailService;

    @Autowired
    public void setEmailService(IEmailService emailService) {
        this.emailService = emailService;
    }


    /**
     * POST request method that allows to send emails.
     * @param mail [Request body variable] <Mail> Object that holds relevant information to send mail, such
     *            as message text, address email of a receiver.
     * @return If successfully code 200 (OK response), otherwise error with appreciate message.
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_Planner')")
    public ResponseEntity<?> sendMail(@RequestBody Mail mail) {
        emailService.sendSimpleMessage(mail);
        return new ResponseEntity<>( new HttpHeaders(), HttpStatus.OK);
    }
}
