package API.Services.EmailService;

import Shared.ForCreation.Mail;
import javax.transaction.Transactional;

public interface IEmailService {
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    void sendSimpleMessage(final Mail mail);

}
