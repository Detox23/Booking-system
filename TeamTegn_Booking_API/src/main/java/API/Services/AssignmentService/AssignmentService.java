package API.Services.AssignmentService;

import API.Database_Entities.AssignmentAssignmentStatusTypeEntity;
import API.Database_Entities.AssignmentEntity;
import API.Database_Entities.AssignmentServiceProviderEntity;
import API.Repository.Assignment.AssignmentDAO;
import API.Repository.Assignment.AssignmentStatusTypeDAO;
import API.Repository.Assignment.Assignment_AssignmentStatusTypeDAO;
import API.Repository.Assignment.Assignment_ServiceProviderDAO;
import API.Repository.ServiceProvider.ServiceProviderDAO;
import Shared.ForCreation.AssignmentForCreationDto;
import Shared.ForCreation.AssignmentForUpdateDto;
import Shared.ToReturn.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AssignmentService implements IAssignmentService {

    private AssignmentDAO assignmentDAO;

    private Assignment_ServiceProviderDAO assignmentServiceProviderDAO;

    private Assignment_AssignmentStatusTypeDAO assignmentAssignmentStatusTypeDAO;

    private ServiceProviderDAO serviceProviderDAO;

    @Autowired
    public void setAssignmentStatusTypeDAO(AssignmentStatusTypeDAO assignmentStatusTypeDAO) {
        this.assignmentStatusTypeDAO = assignmentStatusTypeDAO;
    }

    private AssignmentStatusTypeDAO assignmentStatusTypeDAO;

    private ModelMapper modelMapper;

    @Autowired
    public void setAssignmentDAO(AssignmentDAO assignmentDAO) {
        this.assignmentDAO = assignmentDAO;
    }

    @Autowired
    public void setAssignmentServiceProviderDAO(Assignment_ServiceProviderDAO assignmentServiceProviderDAO) {
        this.assignmentServiceProviderDAO = assignmentServiceProviderDAO;
    }

    @Autowired
    public void setAssignmentAssignmentStatusTypeDAO(Assignment_AssignmentStatusTypeDAO assignmentAssignmentStatusTypeDAO) {
        this.assignmentAssignmentStatusTypeDAO = assignmentAssignmentStatusTypeDAO;
    }

    @Autowired
    public void setServiceProviderDAO(ServiceProviderDAO serviceProviderDAO) {
        this.serviceProviderDAO = serviceProviderDAO;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    @Transactional(rollbackFor = Throwable.class)
    public AssignmentDto addAssignment(AssignmentForCreationDto assignment) {
        Map<Integer, ServiceProviderDto> helperServiceProviderMap = new HashMap<>();
        Map<Integer, AssignmentStatusTypeDto> helperAssignmentStatusTypeMap = new HashMap<>();
        try {
            AssignmentDto addedAssignment = assignmentDAO.addAssignment(modelMapper.map(assignment, AssignmentEntity.class), assignment.getServiceProviders(), assignment.getAssignmentStatusTypeIds());
            fillServiceProviderListToReturn(addedAssignment, helperServiceProviderMap);
            fillAssignmentStatusTypeListToReturn(addedAssignment, helperAssignmentStatusTypeMap);
            return addedAssignment;
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    public AssignmentDto findAssignment(int id) {
        Map<Integer, ServiceProviderDto> helperServiceProviderMap = new HashMap<>();
        Map<Integer, AssignmentStatusTypeDto> helperAssignmentStatusTypeMap = new HashMap<>();
        try{
            AssignmentDto found = assignmentDAO.findAssignment(id);
            fillServiceProviderListToReturn(found, helperServiceProviderMap);
            fillAssignmentStatusTypeListToReturn(found, helperAssignmentStatusTypeMap);
            return found;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Page<AssignmentDto> listAssignmentsPage(Pageable pageable) {
        Map<Integer, ServiceProviderDto> helperServiceProviderMap = new HashMap<>();
        Map<Integer, AssignmentStatusTypeDto> helperAssignmentStatusTypeMap = new HashMap<>();
        try{
            Page<AssignmentDto> foundList = assignmentDAO.listAssignmentsPage(pageable);
            for(AssignmentDto assignment: foundList){
                fillServiceProviderListToReturn(assignment, helperServiceProviderMap);
                fillAssignmentStatusTypeListToReturn(assignment, helperAssignmentStatusTypeMap);
            }
            return foundList;

        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public List<AssignmentViewDto> listAssignmentsDate(Date date) {
        return assignmentDAO.listAllAssignments(date);
    }


    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean deleteAssignment(int id) {
        return assignmentDAO.deleteAssignment(id);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public AssignmentDto updateAssignment(AssignmentForUpdateDto assignment) {
        Map<Integer, ServiceProviderDto> helperServiceProviderMap = new HashMap<>();
        Map<Integer, AssignmentStatusTypeDto> helperAssignmentStatusTypeMap = new HashMap<>();
        try{
            AssignmentDto updated = assignmentDAO.updateAssignment(modelMapper.map(assignment, AssignmentEntity.class), assignment.getServiceProviders(), assignment.getAssignmentStatusTypeIds());
            fillServiceProviderListToReturn(updated, helperServiceProviderMap);
            fillAssignmentStatusTypeListToReturn(updated, helperAssignmentStatusTypeMap);
            return updated;
        }catch (Exception e){
            throw e;
        }
    }

    private void fillServiceProviderListToReturn(AssignmentDto addedAssignment, Map<Integer, ServiceProviderDto> helperServiceProviderMap) {
        addedAssignment.setServiceProviders(new ArrayList<>());
        List<AssignmentServiceProviderEntity> foundList = assignmentServiceProviderDAO.findAllByAssignmentIdIs(addedAssignment.getId());
        List<AssignmentServiceProviderDto> listOfServiceUsers = modelMapper.map(foundList,new TypeToken<List<AssignmentServiceProviderDto>>() {}.getType());
        for (AssignmentServiceProviderDto serviceProvider : listOfServiceUsers) {
            if (helperServiceProviderMap.get(serviceProvider.getServiceProviderId()) == null) {
                ServiceProviderDto found = modelMapper.map(serviceProviderDAO.findById(serviceProvider.getServiceProviderId()).get(), ServiceProviderDto.class);
                helperServiceProviderMap.put(serviceProvider.getId(), found);
                addedAssignment.getServiceProviders().add(found);
            } else {
                addedAssignment.getServiceProviders().add(helperServiceProviderMap.get(serviceProvider.getServiceProviderId()));
            }
        }
    }

    private void fillAssignmentStatusTypeListToReturn(AssignmentDto addedAssignment, Map<Integer, AssignmentStatusTypeDto> helperAssignmentStatusTypeMap) {
        addedAssignment.setAssignmentStatusTypeIds(new ArrayList<>());
        List<AssignmentAssignmentStatusTypeEntity> foundList = assignmentAssignmentStatusTypeDAO.findAllByAssignmentIdIs(addedAssignment.getId());
        List<AssignmentAssignmentStatusTypeDto> listOfStatusTypes = modelMapper.map(foundList,new TypeToken<List<AssignmentAssignmentStatusTypeDto>>() {}.getType());
        for (AssignmentAssignmentStatusTypeDto statusType : listOfStatusTypes) {
            if (helperAssignmentStatusTypeMap.get(statusType.getAssignmentStatusTypeId()) == null) {
                AssignmentStatusTypeDto found = assignmentStatusTypeDAO.findAssignmentStatusType(statusType.getAssignmentStatusTypeId());
                helperAssignmentStatusTypeMap.put(found.getId(), found);
                addedAssignment.getAssignmentStatusTypeIds().add(found);
            } else {
                addedAssignment.getAssignmentStatusTypeIds().add(helperAssignmentStatusTypeMap.get(statusType.getAssignmentStatusTypeId()));
            }
        }
    }


}