//package API.Service.AssignmentService;
//
//import API.Configurations.SpringBeanMockUtil;
//import API.MainApplicationClass;
//import API.Repository.Assignment.AAssignmentStatusTypeDAO;
//import API.Repository.Assignment.AssignmentDAO;
//import API.Repository.Assignment.AssignmentServiceProviderDAO;
//import API.Services.AssignmentService.IAssignmentService;
//import API.Services.ServiceProviderService.ServiceProviderService;
//import Shared.ForCreation.AssignmentForCreationDto;
//import Shared.ToReturn.AssignmentDto;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.anyInt;
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = MainApplicationClass.class)
//@ActiveProfiles("test")
//public class AssignmentServiceTest {
//
//    @Autowired
//    private AssignmentDAO assignmentDAO;
//
//    @Autowired
//    private ModelMapper mapper;
//
//    @Autowired
//    private IAssignmentService assignmentService;
//
//    @Autowired
//    private AssignmentServiceProviderDAO assignmentServiceProviderRepository;
//
//    @Autowired
//    private AAssignmentStatusTypeDAO assignmentStatusTypeRepository;
//
//    @Autowired
//    private ServiceProviderService serviceProviderService;
//
//    private AssignmentDAO assignmentDAOMock;
//    private AssignmentServiceProviderDAO assignmentServiceProviderDAOMock;
//    private AAssignmentStatusTypeDAO assignmentStatusTypeDAOMock;
//    private ServiceProviderService serviceProviderServiceMock;
//
//    private int assignmentOneId;
//    private int assignmentTwoId;
//
//public AssignmentServiceTest()
//{/*
//    assignmentServiceProviderDAOMock = SpringBeanMockUtil.mockFieldOnBean(assignmentServiceProviderRepository, AssignmentServiceProviderDAO.class);
//    assignmentStatusTypeDAOMock = SpringBeanMockUtil.mockFieldOnBean(assignmentStatusTypeRepository, AAssignmentStatusTypeDAO.class);
//    serviceProviderServiceMock = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, ServiceProviderService.class);
//    MockitoAnnotations.initMocks(this);*/
//
//}
//    private void setUp() {
//
//        AssignmentForCreationDto one = new AssignmentForCreationDto();
//        one.setAssignmentTitle("Assignment1");
//        one.setServiceUserId(1);
//        one.setStartTime(new Timestamp(System.currentTimeMillis()));
//        one.setEndTime(new Timestamp(System.currentTimeMillis()));
//        one.setTotalTime(100);
//        one.setAssignedStatus(true);
//
//        AssignmentForCreationDto two = new AssignmentForCreationDto();
//        two.setAssignmentTitle("Assignment2");
//        two.setServiceUserId(2);
//        two.setStartTime(new Timestamp(System.currentTimeMillis()));
//        two.setEndTime(new Timestamp(System.currentTimeMillis()));
//        two.setTotalTime(200);
//        two.setAssignedStatus(true);
//
//        AssignmentEntity ae1 = assignmentDAO.save(mapper.map(one, AssignmentEntity.class));
//        AssignmentEntity ae2 = assignmentDAO.save(mapper.map(two, AssignmentEntity.class));
//        assignmentOneId = (ae1.getId());
//        assignmentTwoId = (ae2.getId());
//    }
//
//
//    private void setDown() {
//        assignmentDAO.deleteAllInBatch();
//        assignmentDAO.flush();
//
//    }
//    private List<Integer> getIntegerList()
//    {
//        List<Integer> list = new ArrayList<Integer>();
//        list.add(1);
//        list.add(12);
//        list.add(13);
//        list.add(14);
//        return list;
//    }
//    private AssignmentForCreationDto getForCreationDto()
//    {
//        AssignmentForCreationDto two = new AssignmentForCreationDto();
//        two.setAssignmentTitle("Assignment");
//        two.setServiceUserId(2);
//        two.setStartTime(new Timestamp(System.currentTimeMillis()));
//        two.setEndTime(new Timestamp(System.currentTimeMillis()));
//        two.setTotalTime(200);
//        two.setAssignedStatus(true);
//        two.setServiceProviders(getIntegerList());
//        two.setAssignmentStatusTypeIds(getIntegerList());
//        return two;
//    }
//
//    @Test
//    public void testAddAccountShouldReturnObjectWithEANS() {
//        try {
//            setUp();
//            AssignmentServiceProviderDAO  assignmentServiceProviderDAOMock = SpringBeanMockUtil.mockFieldOnBean(assignmentService, AssignmentServiceProviderDAO.class);
//            MockitoAnnotations.initMocks(this);
//
//            Mockito.doReturn(getIntegerList()).when(assignmentServiceProviderDAOMock).findAllByAssignmentByAssignmentId(anyInt());
//            AssignmentDto result = assignmentService.add(getForCreationDto());
//            Assert.assertNotNull(result);
//        } catch (Exception e) {
//            Assert.fail();
//        } finally {
//            setDown();
//        }
//    }
//
//}
