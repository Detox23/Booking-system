package API.Services.LogsService;

import API.Models.Database_Entities.EventLogEntity;
import API.Repository.Log.EventLogDAO;
import Shared.ForCreation.EventLogDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventLogService implements IEventLogService {

    private EventLogDAO eventLogDAO;
    private ModelMapper mapper;

    @Autowired
    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setEventLogDAO(EventLogDAO eventLogDAO) {
        this.eventLogDAO = eventLogDAO;
    }



    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void addLog(EventLogDto log) {
        EventLogEntity logEntity = mapper.map(log, EventLogEntity.class);
        eventLogDAO.save(logEntity);
    }
}
