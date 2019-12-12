package API.Services.EmailService;

import Shared.ForCreation.Mail;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
public interface IEmailService {
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    void sendSimpleMessage(final Mail mail);

}
