package API.Services.AssignmentService;

import API.Models.Database_Entities.AssignmentAssignmentStatusTypeEntity;
import API.Models.Database_Entities.AssignmentEntity;
import API.Models.Database_Entities.AssignmentServiceProviderEntity;
import API.Models.Database_Entities.Assignment_StukYearCodeEntity;
import API.Repository.Assignment.*;
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

    private Assignment_STUKYearCodeDAO assignment_stukYearCodeDAO;

    private Assignment_ServiceProviderDAO assignmentServiceProviderDAO;

    private Assignment_AssignmentStatusTypeDAO assignmentAssignmentStatusTypeDAO;

    private ServiceProviderDAO serviceProviderDAO;

    private AssignmentStatusTypeDAO assignmentStatusTypeDAO;

    private ModelMapper modelMapper;

    private AssignmentSTUKYearCodeDAO assignmentSTUKYearCodeDAO;

    @Autowired
    public void setAssignmentDAO(AssignmentDAO assignmentDAO) {
        this.assignmentDAO = assignmentDAO;
    }
    @Autowired
    public void setAssignment_stukYearCodeDAO(Assignment_STUKYearCodeDAO assignment_stukYearCodeDAO) {
        this.assignment_stukYearCodeDAO = assignment_stukYearCodeDAO;
    }
    @Autowired
    public void setAssignmentStatusTypeDAO(AssignmentStatusTypeDAO assignmentStatusTypeDAO) {
        this.assignmentStatusTypeDAO = assignmentStatusTypeDAO;
    }
    @Autowired
    public void setAssignmentSTUKYearCodeDAO(AssignmentSTUKYearCodeDAO assignmentSTUKYearCodeDAO) {
        this.assignmentSTUKYearCodeDAO = assignmentSTUKYearCodeDAO;
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
        Map<Integer, AssignmentStukYearCodeDto> helperStukYearCode = new HashMap<>();
        try {
            AssignmentDto addedAssignment = assignmentDAO.addAssignment(modelMapper.map(assignment, AssignmentEntity.class),
                    assignment.getServiceProviders(),
                    assignment.getAssignmentStatusTypeIds(),
                    assignment.getStukYearCodes());
            fillServiceProviderListToReturn(addedAssignment, helperServiceProviderMap);
            fillAssignmentStatusTypeListToReturn(addedAssignment, helperAssignmentStatusTypeMap);
            fillStukYearCodesToReturn(addedAssignment, helperStukYearCode);
            return addedAssignment;
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    public AssignmentDto findAssignment(int id) {
        Map<Integer, ServiceProviderDto> helperServiceProviderMap = new HashMap<>();
        Map<Integer, AssignmentStatusTypeDto> helperAssignmentStatusTypeMap = new HashMap<>();
        Map<Integer, AssignmentStukYearCodeDto> helperStukYearCode = new HashMap<>();
        try{
            AssignmentDto found = assignmentDAO.findAssignment(id);
            fillServiceProviderListToReturn(found, helperServiceProviderMap);
            fillAssignmentStatusTypeListToReturn(found, helperAssignmentStatusTypeMap);
            fillStukYearCodesToReturn(found, helperStukYearCode);
            return found;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Page<AssignmentDto> listAssignmentsPage(Pageable pageable) {
        Map<Integer, ServiceProviderDto> helperServiceProviderMap = new HashMap<>();
        Map<Integer, AssignmentStatusTypeDto> helperAssignmentStatusTypeMap = new HashMap<>();
        Map<Integer, AssignmentStukYearCodeDto> helperStukYearCode = new HashMap<>();
        try{
            Page<AssignmentDto> foundList = assignmentDAO.listAssignmentsPage(pageable);
            for(AssignmentDto assignment: foundList){
                fillServiceProviderListToReturn(assignment, helperServiceProviderMap);
                fillAssignmentStatusTypeListToReturn(assignment, helperAssignmentStatusTypeMap);
                fillStukYearCodesToReturn(assignment, helperStukYearCode);
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
        Map<Integer, AssignmentStukYearCodeDto> helperStukYearCode = new HashMap<>();
        try{
            AssignmentDto updated = assignmentDAO.updateAssignment(modelMapper.map(assignment, AssignmentEntity.class),
                    assignment.getServiceProviders(),
                    assignment.getAssignmentStatusTypeIds(),
                    assignment.getStukYearCodes());
            fillServiceProviderListToReturn(updated, helperServiceProviderMap);
            fillAssignmentStatusTypeListToReturn(updated, helperAssignmentStatusTypeMap);
            fillStukYearCodesToReturn(updated, helperStukYearCode);
            return updated;
        }catch (Exception e){
            throw e;
        }
    }

    private void fillServiceProviderListToReturn(AssignmentDto assignment, Map<Integer, ServiceProviderDto> map) {
        assignment.setServiceProviders(new ArrayList<>());
        List<AssignmentServiceProviderEntity> foundList = assignmentServiceProviderDAO.findAllByAssignmentIdIs(assignment.getId());
        List<AssignmentServiceProviderDto> listOfServiceUsers = modelMapper.map(foundList, new TypeToken<List<AssignmentServiceProviderDto>>() {}.getType());
        for (AssignmentServiceProviderDto serviceProvider : listOfServiceUsers) {
            if (map.get(serviceProvider.getServiceProviderId()) == null) {
                ServiceProviderDto found = modelMapper.map(serviceProviderDAO.findById(serviceProvider.getServiceProviderId()).get(), ServiceProviderDto.class);
                map.put(serviceProvider.getId(), found);
                assignment.getServiceProviders().add(found);
            } else {
                assignment.getServiceProviders().add(map.get(serviceProvider.getServiceProviderId()));
            }
        }
    }

    private void fillAssignmentStatusTypeListToReturn(AssignmentDto assignment, Map<Integer, AssignmentStatusTypeDto> map) {
        assignment.setAssignmentStatusTypeIds(new ArrayList<>());
        List<AssignmentAssignmentStatusTypeEntity> foundList = assignmentAssignmentStatusTypeDAO.findAllByAssignmentIdIs(assignment.getId());
        List<AssignmentAssignmentStatusTypeDto> listOfStatusTypes = modelMapper.map(foundList,new TypeToken<List<AssignmentAssignmentStatusTypeDto>>() {}.getType());
        for (AssignmentAssignmentStatusTypeDto statusType : listOfStatusTypes) {
            if (map.get(statusType.getAssignmentStatusTypeId()) == null) {
                AssignmentStatusTypeDto found = assignmentStatusTypeDAO.findAssignmentStatusType(statusType.getAssignmentStatusTypeId());
                map.put(found.getId(), found);
                assignment.getAssignmentStatusTypeIds().add(found);
            } else {
                assignment.getAssignmentStatusTypeIds().add(map.get(statusType.getAssignmentStatusTypeId()));
            }
        }
    }

    private void fillStukYearCodesToReturn(AssignmentDto assignment, Map<Integer, AssignmentStukYearCodeDto> map){
        assignment.setStukYearCodes(new ArrayList<>());
        List<Assignment_StukYearCodeEntity> foundList = assignment_stukYearCodeDAO.findAllByAssignmentIdIs(assignment.getId());
        List<Assignment_StukYearCodeDto> listOfStukYearCodes = modelMapper.map(foundList, new TypeToken<List<Assignment_StukYearCodeDto>>() {}.getType());
        for (Assignment_StukYearCodeDto yearCode: listOfStukYearCodes){
            if(map.get(yearCode.getStukYearCodeId()) == null){
                AssignmentStukYearCodeDto found = assignmentSTUKYearCodeDAO.findAssignmentStukYearCode(yearCode.getStukYearCodeId());
                map.put(found.getId(), found);
                assignment.getStukYearCodes().add(found);
            }else{
                assignment.getStukYearCodes().add(map.get(yearCode.getStukYearCodeId()));
            }
        }
    }


}