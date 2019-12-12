package API.Repository.Assignment;

import API.Configurations.Patcher.PatcherHandler;
import API.Exceptions.NotFoundException;
import API.Exceptions.UnknownAddingException;
import API.Exceptions.UpdatePatchException;
import API.Models.Database_Entities.*;
import API.Repository.CityPostcodes.WI_PostcodeDAO;
import API.Repository.ServiceProvider.ServiceProviderDAO;
import Shared.ToReturn.AssignmentDto;
import Shared.ToReturn.AssignmentViewDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Component
public class AssignmentDAOImpl implements AssignmentDAOCustom {

    private ModelMapper modelMapper;

    private WI_PostcodeDAO wiPostcodeDAO;

    private PatcherHandler patcherHandler;

    private Assignment_ServiceProviderDAO assignmentServiceProviderDAO;

    private Assignment_STUKYearCodeDAO assignmentStukYearCodeDAO;

    private Assignment_AssignmentStatusTypeDAO assignmentAssignmentStatusTypeDAO;

    private ServiceProviderDAO serviceProviderRepository;

    private AssignmentDAO assignmentDAO;

    @Autowired
    public void setWiPostcodeDAO(WI_PostcodeDAO wiPostcodeDAO) {
        this.wiPostcodeDAO = wiPostcodeDAO;
    }

    @Autowired
    public void setAssignmentStukYearCodeDAO(Assignment_STUKYearCodeDAO assignmentStukYearCodeDAO) {
        this.assignmentStukYearCodeDAO = assignmentStukYearCodeDAO;
    }

    @Autowired
    public void setAssignmentAssignmentStatusTypeDAO(Assignment_AssignmentStatusTypeDAO assignmentAssignmentStatusTypeDAO) {
        this.assignmentAssignmentStatusTypeDAO = assignmentAssignmentStatusTypeDAO;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setServiceProviderRepository(ServiceProviderDAO serviceProviderRepository) {
        this.serviceProviderRepository = serviceProviderRepository;
    }

    @Autowired
    public void setAssignmentDAO(AssignmentDAO assignmentDAO) {
        this.assignmentDAO = assignmentDAO;
    }

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Autowired
    public void setAssignmentServiceProviderDAO(Assignment_ServiceProviderDAO assignmentServiceProviderDAO) {
        this.assignmentServiceProviderDAO = assignmentServiceProviderDAO;
    }

    @Override
    public List<AssignmentViewDto> listAllAssignments(Date date) {
        Type listType = new TypeToken<List<AssignmentViewDto>>() {
        }.getType();
        List<AssignmentEntity> found = assignmentDAO.findAllByAssignmentDateEquals(date);
        return modelMapper.map(found, listType);

    }

    @Override
    public Page<AssignmentDto> listAssignmentsPage(Pageable pageable) {
        try {
            Page<AssignmentEntity> list = assignmentDAO.findAll(pageable);
            return list.map(x -> modelMapper.map(x, AssignmentDto.class));
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public AssignmentDto findAssignment(int id) {
        try {
            AssignmentEntity found = findIfExistsAndReturn(id);
            return modelMapper.map(found, AssignmentDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public AssignmentDto addAssignment(
            AssignmentEntity assignmentEntity,
            List<Integer> serviceProviders,
            List<Integer> assignmentStatusTypes,
            List<Integer> stukYearCodes
    ) {
        try {
            assignmentEntity.setTotalTime((int) calculateHoursFromDates(assignmentEntity.getEndTime(), assignmentEntity.getStartTime()));
            addStateRegion(assignmentEntity);
            AssignmentEntity saved = assignmentDAO.save(assignmentEntity);
            addServiceProviders(serviceProviders, saved.getId());
            addStatusTypes(assignmentStatusTypes, saved.getId());
            addAssignmentSTUKYearCodes(stukYearCodes, saved.getId());
            return modelMapper.map(saved, AssignmentDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public AssignmentDto updateAssignment(
            AssignmentEntity assignmentEntity,
            List<Integer> serviceProviders,
            List<Integer> assignmentStatusTypes,
            List<Integer> stukYearCodes
    ) {
        try {
            AssignmentEntity found = findIfExistsAndReturn(assignmentEntity.getId());
            patcherHandler.fillNotNullFields(found, assignmentEntity);
            found.setTotalTime((int) calculateHoursFromDates(assignmentEntity.getEndTime(), assignmentEntity.getStartTime()));
            addStateRegion(found);
            AssignmentEntity updated = assignmentDAO.save(found);
            addServiceProviders(serviceProviders, found.getId());
            addStatusTypes(assignmentStatusTypes, found.getId());
            addAssignmentSTUKYearCodes(stukYearCodes, found.getId());
            return modelMapper.map(updated, AssignmentDto.class);
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("There was an error while updating an account [PATCHING].");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean deleteAssignment(int id) {
        try {
            AssignmentEntity found = findIfExistsAndReturn(id);
            found.setDeleted(true);
            AssignmentEntity deletionResult = assignmentDAO.save(found);
            return deletionResult.isDeleted();
        } catch (Exception e) {
            throw e;
        }
    }

    private float calculateHoursFromDates(Timestamp toTime, Timestamp fromTime) {
        return (toTime.getTime() - fromTime.getTime()) / 3600000;
    }

    private AssignmentEntity findIfExistsAndReturn(int id) {
        Optional<AssignmentEntity> found = assignmentDAO.findByIdAndDeletedIsFalse(id);
        if (!found.isPresent()) {
            throw new NotFoundException(String.format("Assignment with id: %d was not found.", id));
        }
        return found.get();
    }

    private void addAssignmentSTUKYearCodes(List<Integer> stukYearCodes, int id) {
        try {
            if (stukYearCodes != null) {
                assignmentStukYearCodeDAO.deleteAllByAssignmentIdIs(id);
                for (Integer stukYearCode : stukYearCodes) {
                    Assignment_StukYearCodeEntity stukYear = new Assignment_StukYearCodeEntity();
                    stukYear.setAssignmentId(id);
                    stukYear.setStukYearCodeId(stukYearCode);
                    Assignment_StukYearCodeEntity savedResult = assignmentStukYearCodeDAO.save(stukYear);
                    if (savedResult == null) {
                        throw new UnknownAddingException(String.format("There was a problem with assigning status codes to assignment."));
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void addStateRegion(AssignmentEntity assignment) {
        if (assignment.getDestinationStateRegion() == null) {
            Optional<WiPostcodeEntity> wiPostcode = wiPostcodeDAO.findByPostcodeIs(assignment.getDestinationPostCode());
            if (wiPostcode.isPresent()) {
                if (wiPostcode.get().getArhus()) {
                    assignment.setDestinationStateRegion("Aarhus");
                } else if (wiPostcode.get().getCopenhagen()) {
                    assignment.setDestinationStateRegion("Copenhagen");
                } else if (wiPostcode.get().getFredericia()) {
                    assignment.setDestinationStateRegion("Fredericia");
                }
            }
        }
    }

    private void addServiceProviders(List<Integer> serviceProviders, int id) {
        try {
            if (serviceProviders != null) {
                assignmentServiceProviderDAO.deleteAllByAssignmentIdIs(id);
                for (Integer serviceProvider : serviceProviders) {
                    ServiceProviderEntity found = serviceProviderRepository.findMiddleNameAndFirstNameAndLastNameAndServiceProviderInitialsById(serviceProvider);
                    AssignmentServiceProviderEntity serviceProviderEntity = new AssignmentServiceProviderEntity();
                    serviceProviderEntity.setAssignmentId(id);
                    serviceProviderEntity.setServiceProviderId(serviceProvider);
                    serviceProviderEntity.setServiceProviderFirstName(found.getFirstName());
                    serviceProviderEntity.setServiceProviderMiddleName(found.getMiddleName());
                    serviceProviderEntity.setServiceProviderLastName(found.getLastName());
                    serviceProviderEntity.setServiceProviderInitials(found.getServiceProviderInitials());
                    AssignmentServiceProviderEntity savedResult = assignmentServiceProviderDAO.save(serviceProviderEntity);
                    if (savedResult == null) {
                        throw new UnknownAddingException(String.format("There was a problem with assigning service provider to" +
                                "assignment. [%s, %s, %s. ID: %d]", found.getFirstName(), found.getMiddleName(), found.getLastName(), serviceProvider));
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void addStatusTypes(List<Integer> statusTypes, int id) {
        try {
            if (statusTypes != null) {
                assignmentAssignmentStatusTypeDAO.deleteAllByAssignmentIdIs(id);
                for (Integer statusType : statusTypes) {
                    AssignmentAssignmentStatusTypeEntity statusTypeEntity = new AssignmentAssignmentStatusTypeEntity();
                    statusTypeEntity.setAssignmentId(id);
                    statusTypeEntity.setAssignmentStatusTypeId(statusType);
                    AssignmentAssignmentStatusTypeEntity savedResult = assignmentAssignmentStatusTypeDAO.save(statusTypeEntity);
                    if (savedResult == null) {
                        throw new UnknownAddingException(String.format("There was a problem with assigning status type to assignment."));
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }

    }
}