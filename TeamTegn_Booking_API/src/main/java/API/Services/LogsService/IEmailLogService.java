package API.Services.LogsService;

import Shared.ForCreation.EmailLogDto;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

public interface IEmailLogService {
   void  log(EmailLogDto log);
}
