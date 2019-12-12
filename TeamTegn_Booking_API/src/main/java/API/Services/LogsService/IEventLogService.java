package API.Services.LogsService;

import Shared.ForCreation.EventLogDto;

public interface IEventLogService {
    void addLog(EventLogDto log);
}
