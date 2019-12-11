package API.Services.LogsService;

import API.Models.Database_Entities.EmailLogEntity;
import API.Repository.Log.EmailLogDAO;
import Shared.ForCreation.EmailLogDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailLogService implements IEmailLogService {

    private EmailLogDAO emailLogDAO;
    private ModelMapper mapper;

    @Autowired
    public void setEmailLogDAO(EmailLogDAO emailLogDAO) {
        this.emailLogDAO = emailLogDAO;
    }

    @Autowired
    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void log(EmailLogDto log) {
        EmailLogEntity logEntity = mapper.map(log, EmailLogEntity.class);
        emailLogDAO.save(logEntity);
    }
}
