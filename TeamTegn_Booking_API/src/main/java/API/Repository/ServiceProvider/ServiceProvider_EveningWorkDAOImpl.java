package API.Repository.ServiceProvider;

import API.Configurations.Patcher.PatcherHandler;
import API.Exceptions.NotFoundException;
import API.Exceptions.UnknownAddingException;
import API.Exceptions.UpdatePatchException;
import API.Models.Database_Entities.ServiceProviderEveningWorkEntity;
import API.Repository.EveningWorkPrioritisation.EveningWorkPrioritisationDAO;
import Shared.ToReturn.ServiceProviderEveningWorkDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceProvider_EveningWorkDAOImpl implements ServiceProvider_EveningWorkDAOCustom {

    private ServiceProvider_EveningWorkDAO serviceProviderEveningWorkDAO;

    private EveningWorkPrioritisationDAO eveningWorkPrioritisationDAO;

    private PatcherHandler patcherHandler;

    private ModelMapper modelMapper;

    private final List<String> weekDays = new ArrayList<String>() {
        {
            add("Monday");
            add("Tuesday");
            add("Wednesday");
            add("Thursday");
            add("Friday");
            add("Saturday");
            add("Sunday");
        }
    };

    @Autowired
    public void setEveningWorkPrioritisationDAO(EveningWorkPrioritisationDAO eveningWorkPrioritisationDAO) {
        this.eveningWorkPrioritisationDAO = eveningWorkPrioritisationDAO;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Autowired
    public void setServiceProviderEveningWorkDAO(ServiceProvider_EveningWorkDAO serviceProviderEveningWorkDAO) {
        this.serviceProviderEveningWorkDAO = serviceProviderEveningWorkDAO;
    }

    @Override
    public ServiceProviderEveningWorkDto addOrUpdateServiceProviderEveningWork(ServiceProviderEveningWorkEntity serviceProviderEveningWork) {
        try {
            List<ServiceProviderEveningWorkEntity> list = serviceProviderEveningWorkDAO.findAllByWeekDayIsAndServiceProviderIdIs(serviceProviderEveningWork.getWeekDay(), serviceProviderEveningWork.getServiceProviderId());
            if (list.size() > 0) {
                if (!weekDays.stream().anyMatch(day -> day.trim().equalsIgnoreCase(serviceProviderEveningWork.getWeekDay()))) {
                    throw new UnknownAddingException("Provided week day is not correct");
                }
                serviceProviderEveningWork.setId(list.get(0).getId());
                ServiceProviderEveningWorkEntity toUpdate = list.get(0);
                patcherHandler.fillNotNullFields(toUpdate, serviceProviderEveningWork);
                ServiceProviderEveningWorkEntity updated = serviceProviderEveningWorkDAO.save(toUpdate);
                return modelMapper.map(updated, ServiceProviderEveningWorkDto.class);
            } else {
                if (!weekDays.stream().anyMatch(day -> day.trim().equalsIgnoreCase(serviceProviderEveningWork.getWeekDay()))) {
                    throw new UnknownAddingException("Provided week day is not correct");
                }
                ServiceProviderEveningWorkEntity saved = serviceProviderEveningWorkDAO.save(serviceProviderEveningWork);
                return modelMapper.map(saved, ServiceProviderEveningWorkDto.class);
            }
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("Error with updating service provider evening work.");
        }
    }

    @Override
    public List<ServiceProviderEveningWorkDto> listServiceProviderEveningWork(int serviceProviderID) {
        try {
            List<ServiceProviderEveningWorkEntity> foundList = serviceProviderEveningWorkDAO.findAllByServiceProviderIdIs(serviceProviderID);
            List<ServiceProviderEveningWorkDto> mappedList = new ArrayList<>();
            for (ServiceProviderEveningWorkEntity item : foundList) {
                ServiceProviderEveningWorkDto addToList = modelMapper.map(item, ServiceProviderEveningWorkDto.class);
                addToList.setEveningWorkPrioritisation(eveningWorkPrioritisationDAO.getByIdIs(item.getEveningWorkPrioritisationId()).getPrioritisation());
                mappedList.add(addToList);
            }
            return mappedList;
        } catch (Exception e) {
            throw e;
        }

    }


    @Override
    public ServiceProviderEveningWorkDto getServiceProviderEveningWorkForSpecificDay(String day, int serviceProviderID) {
        try {
            ServiceProviderEveningWorkEntity found = serviceProviderEveningWorkDAO.findByWeekDayIsAndServiceProviderIdIs(day, serviceProviderID);
            if (found == null) {
                throw new NotFoundException("The evening work was not found.");
            }
            ServiceProviderEveningWorkDto toReturn = modelMapper.map(found, ServiceProviderEveningWorkDto.class);
            toReturn.setEveningWorkPrioritisation(eveningWorkPrioritisationDAO.getByIdIs(found.getEveningWorkPrioritisationId()).getPrioritisation());
            return toReturn;
        } catch (Exception e) {
            throw e;
        }

    }


}
