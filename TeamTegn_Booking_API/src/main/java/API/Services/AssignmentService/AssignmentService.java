package API.Services.AssignmentService;

import API.Database_Entities.AssignmentAssignmentStatusTypeEntity;
import API.Database_Entities.AssignmentEntity;
import API.Database_Entities.AssignmentServiceProviderEntity;
import API.Database_Entities.ServiceProviderEntity;
import API.Repository.Assignment.*;
import API.Repository.ServiceProvider.ServiceProviderDAO;
import Shared.ForCreation.AssignmentForCreationDto;
import Shared.ForCreation.AssignmentForUpdateDto;
import Shared.ToReturn.AssignmentDto;
import Shared.ToReturn.ServiceProviderDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssignmentService implements IAssignmentService {

    private AssignmentDAO assignmentRepository;

    private AssignmentServiceProviderDAO assignmentServiceProviderRepository;

    private Assignment_AssignmentStatusTypeDAO assignmentStatusTypeRepository;

    private ServiceProviderDAO serviceProviderRepository;

    private ModelMapper mapper;

    @Autowired
    public void
    setAssignmentRepository(AssignmentDAO assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    @Autowired
    public void setAssignmentServiceProviderRepository(AssignmentServiceProviderDAO assignmentServiceProviderRepository) {
        this.assignmentServiceProviderRepository = assignmentServiceProviderRepository;
    }

    @Autowired
    public void setAssignmentStatusTypeRepository(Assignment_AssignmentStatusTypeDAO assignmentStatusTypeRepository) {
        this.assignmentStatusTypeRepository = assignmentStatusTypeRepository;
    }

    @Autowired
    public void setServiceProviderRepository(ServiceProviderDAO serviceProviderRepository) {
        this.serviceProviderRepository = serviceProviderRepository;
    }

    @Autowired
    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    @Transactional
    public AssignmentDto add(AssignmentForCreationDto assignment) {
        //Getting id of service providers.
        List<Integer> providers = assignment.getServiceProviders();

        AssignmentEntity dbEntity = mapper.map(assignment, AssignmentEntity.class);
        dbEntity.setDeleted(false);
        AssignmentEntity assignmentSaved = assignmentRepository.addOne(dbEntity);
        if (assignment.getAssignmentStatusTypeIds() != null) {
            assignment.getAssignmentStatusTypeIds()
                    .forEach((id) -> assignmentStatusTypeRepository.save(new AssignmentAssignmentStatusTypeEntity(assignmentSaved.getId(), id)));
        }
        if (providers != null) {
            providers.forEach((serviceProviderId) -> addAssignmentAssignmentStatusProvider(assignmentSaved, serviceProviderId));
        }
        return mapper.map(assignmentSaved, AssignmentDto.class);
    }

    @Override
    public AssignmentDto get(int id) {
        AssignmentEntity assignment = assignmentRepository.getOne(id);
        AssignmentDto dto = mapper.map(assignment, AssignmentDto.class);
        List<Integer> listOfAssignmentStatusTypeIds = assignmentStatusTypeRepository.findAllByAssignmentId(id);
        dto.setAssignmentStatusTypeIds(listOfAssignmentStatusTypeIds);
        List<AssignmentServiceProviderEntity> assignmentServiceProviderEntities = assignmentServiceProviderRepository.findAllByAssignmentId(id);
        List<ServiceProviderDto> serviceProviderDtos = new ArrayList<>();

        assignmentServiceProviderEntities.forEach((e) ->
                serviceProviderDtos.add(
                        serviceProviderRepository.findOne(e.getServiceProviderId())));
        dto.setServiceProviders(serviceProviderDtos);
        return dto;
    }


    private void addAssignmentAssignmentStatusProvider(AssignmentEntity assignment, int serviceProviderId) {
        ServiceProviderEntity serviceProviderEntity = serviceProviderRepository.findMiddleNameAndFirstNameAndLastNameAndServiceProviderInitialsById(serviceProviderId);

        AssignmentServiceProviderEntity assignmentServiceProviderEntity = new AssignmentServiceProviderEntity();
        assignmentServiceProviderEntity.setServiceProviderId(serviceProviderId);
        assignmentServiceProviderEntity.setServiceProviderFirstName(serviceProviderEntity.getFirstName());
        assignmentServiceProviderEntity.setServiceProviderLastName(serviceProviderEntity.getLastName());
        assignmentServiceProviderEntity.setServiceProviderInitials(serviceProviderEntity.getServiceProviderInitials());
        assignmentServiceProviderEntity.setServiceProviderMiddleName(serviceProviderEntity.getMiddleName());
        assignmentServiceProviderEntity.setAssignmentId(assignment.getId());

        assignmentServiceProviderRepository.save(assignmentServiceProviderEntity);
    }


    @Override
    public Page<AssignmentDto> getAll(Pageable pageable) {
        Page<AssignmentEntity> list = assignmentRepository.listAll(pageable);
        return list.map(x -> mapper.map(x, AssignmentDto.class));

    }

    @Override
    public boolean delete(int id) {
        return assignmentRepository.deleteOne(id);
    }

    @Override
    @Transactional
    public AssignmentDto update(int id, AssignmentForUpdateDto assignmentEntity) {
        AssignmentEntity as = mapper.map(assignmentEntity, AssignmentEntity.class);
        as.setId(id);
        List<Integer> oldList = assignmentStatusTypeRepository.findAllByAssignmentId(id);
        //nie wiem czy ma sens?
        if (!oldList.equals(assignmentEntity.getAssignmentStatusTypeIds())) {
            assignmentStatusTypeRepository.deleteAllByAssignmentId(id);
            for (Integer idStatusType : assignmentEntity.getAssignmentStatusTypeIds()) {
                assignmentStatusTypeRepository.save(new AssignmentAssignmentStatusTypeEntity(id, idStatusType));
            }
        }

        AssignmentEntity entity = assignmentRepository.updateOne(as);
        return mapper.map(entity, AssignmentDto.class);

    }


}