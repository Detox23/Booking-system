package API.Repository.ServiceProvider;

import API.MainApplicationClass;
import API.Models.Database_Entities.*;
import API.Repository.AbsenceType.AbsenceTypeDAO;
import API.Repository.Department.DepartmentDAO;
import Shared.ToReturn.ServiceProviderAbsenceDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplicationClass.class)
@ActiveProfiles("test")
public class ServiceProviderAbsenceRepositoryTest {

    @Autowired
    private AbsenceTypeDAO absenceTypeDAO;

    @Autowired
    private ServiceProviderAbsenceDAO serviceProviderAbsenceDAO;

    @Autowired
    private ServiceProviderDAO serviceProviderDAO;

    @Autowired
    private TransportTypeDAO transportTypeDAO;

    @Autowired
    private ServiceProviderPreferredNotificationDAO serviceProviderPreferredNotificationDAO;

    @Autowired
    private DepartmentDAO departmentDAO;

    @Autowired
    private ServiceProviderSourceDAO serviceProviderSourceDAO;

    @Autowired
    private ServiceProviderCompetencyDAO serviceProviderCompetencyDAO;

    @Autowired
    private ServiceProviderTypeDAO serviceProviderTypeDAO;

    private ServiceProviderAbsenceEntity serviceProviderAbsenceOne;
    private ServiceProviderAbsenceEntity serviceProviderAbsenceTwo;
    private ServiceProviderAbsenceEntity serviceProviderAbsenceThree;
    private TransportTypeEntity transportTypeOne;
    private ServiceProviderPreferredNotificationEntity serviceProviderPreferredNotificationOne;
    private DepartmentEntity departmentOne;
    private ServiceProviderEntity serviceProviderOne;
    private ServiceProviderSourceEntity serviceProviderSourceOne;
    private ServiceProviderCompetencyEntity serviceProviderCompetencyOne;
    private ServiceProviderCompetencyEntity serviceProviderCompetencyTwo;
    private ServiceProviderTypeEntity serviceProviderTypeOne;
    private ServiceProviderTypeEntity serviceProviderTypeTwo;
    private AbsenceTypeEntity absenceTypeOne;

    private void setUp() throws ParseException {
        ServiceProviderSourceEntity serviceProviderSourceEntity = new ServiceProviderSourceEntity();
        serviceProviderSourceEntity.setProviderSource("TestServiceProviderSource");
        serviceProviderSourceEntity.setDeleted(false);
        serviceProviderSourceOne = serviceProviderSourceDAO.save(serviceProviderSourceEntity);

        TransportTypeEntity transportTypeEntity = new TransportTypeEntity();
        transportTypeEntity.setTransport("TestTransport");
        transportTypeEntity.setDeleted(false);
        transportTypeOne = transportTypeDAO.save(transportTypeEntity);

        ServiceProviderPreferredNotificationEntity providerPreferredNotificationEntity = new ServiceProviderPreferredNotificationEntity();
        providerPreferredNotificationEntity.setNotificationType("TestNotificationType");
        providerPreferredNotificationEntity.setDeleted(false);
        serviceProviderPreferredNotificationOne = serviceProviderPreferredNotificationDAO.save(providerPreferredNotificationEntity);

        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setDepartmentName("TestDepartmentNameOne");
        departmentEntity.setPostcode("9200");
        departmentOne = departmentDAO.save(departmentEntity);

        ServiceProviderCompetencyEntity serviceProviderCompetencyEntity = new ServiceProviderCompetencyEntity();
        serviceProviderCompetencyEntity.setCompetency("TestCompetency");
        serviceProviderCompetencyEntity.setDeleted(false);
        serviceProviderCompetencyOne = serviceProviderCompetencyDAO.save(serviceProviderCompetencyEntity);

        ServiceProviderCompetencyEntity serviceProviderCompetencyEntity2 = new ServiceProviderCompetencyEntity();
        serviceProviderCompetencyEntity2.setCompetency("TestCompetency2");
        serviceProviderCompetencyEntity2.setDeleted(false);
        serviceProviderCompetencyTwo = serviceProviderCompetencyDAO.save(serviceProviderCompetencyEntity2);


        ServiceProviderTypeEntity serviceProviderTypeEntity = new ServiceProviderTypeEntity();
        serviceProviderTypeEntity.setProviderType("TestProviderType");
        serviceProviderTypeEntity.setDeleted(false);
        serviceProviderTypeOne = serviceProviderTypeDAO.save(serviceProviderTypeEntity);

        ServiceProviderTypeEntity serviceProviderTypeEntity2 = new ServiceProviderTypeEntity();
        serviceProviderTypeEntity2.setProviderType("TestProviderType");
        serviceProviderTypeEntity2.setDeleted(false);
        serviceProviderTypeTwo = serviceProviderTypeDAO.save(serviceProviderTypeEntity2);

        ServiceProviderEntity serviceProviderEntity = new ServiceProviderEntity();
        serviceProviderEntity.setSource(serviceProviderSourceOne.getId());
        serviceProviderEntity.setFirstName("TestFirstName");
        serviceProviderEntity.setLastName("TestLastName");
        serviceProviderEntity.setDepartmentId(departmentOne.getId());
        serviceProviderEntity.setStatus(false);
        serviceProviderEntity.setPreferredNotificationId(serviceProviderPreferredNotificationOne.getId());
        serviceProviderEntity.setTransportId(transportTypeOne.getId());
        serviceProviderOne = serviceProviderDAO.save(serviceProviderEntity);

        AbsenceTypeEntity absenceTypeEntity = new AbsenceTypeEntity();
        absenceTypeEntity.setAbsenceTypeName("TestAbsenceType");
        absenceTypeEntity.setDeleted(false);
        absenceTypeOne = absenceTypeDAO.save(absenceTypeEntity);

        ServiceProviderAbsenceEntity serviceProviderAbsenceEntity = new ServiceProviderAbsenceEntity();
        SimpleDateFormat textFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fromDate = "2019-12-10 00:00:00";
        String toDate = "2019-12-15 00:00:00";
        String fromTime = "2019-12-10 15:00:00";
        String toTime = "2019-12-15 18:00:00";
        serviceProviderAbsenceEntity.setFromDate(new java.sql.Date(textFormat.parse(fromDate).getTime()));
        serviceProviderAbsenceEntity.setToDate(new java.sql.Date(textFormat.parse(toDate).getTime()));
        serviceProviderAbsenceEntity.setFromTime(new java.sql.Time(textFormat.parse(fromTime).getTime()));
        serviceProviderAbsenceEntity.setToTime(new java.sql.Time(textFormat.parse(toTime).getTime()));
        serviceProviderAbsenceEntity.setAbsenceTypeId(absenceTypeOne.getId());
        serviceProviderAbsenceEntity.setAbsenceReason("TestAbsenceReason");
        serviceProviderAbsenceEntity.setServiceProviderId(serviceProviderOne.getId());
        serviceProviderAbsenceOne = serviceProviderAbsenceDAO.save(serviceProviderAbsenceEntity);

        ServiceProviderAbsenceEntity serviceProviderAbsenceEntity2 = new ServiceProviderAbsenceEntity();
        SimpleDateFormat textFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fromDate2 = "2019-12-14 00:00:00";
        String toDate2 = "2019-12-20 00:00:00";
        String fromTime2 = "2019-12-14 08:00:00";
        String toTime2 = "2019-12-20 12:00:00";
        serviceProviderAbsenceEntity2.setFromDate(new java.sql.Date(textFormat2.parse(fromDate2).getTime()));
        serviceProviderAbsenceEntity2.setToDate(new java.sql.Date(textFormat2.parse(toDate2).getTime()));
        serviceProviderAbsenceEntity2.setFromTime(new java.sql.Time(textFormat2.parse(fromTime2).getTime()));
        serviceProviderAbsenceEntity2.setToTime(new java.sql.Time(textFormat2.parse(toTime2).getTime()));
        serviceProviderAbsenceEntity2.setAbsenceTypeId(absenceTypeOne.getId());
        serviceProviderAbsenceEntity2.setAbsenceReason("TestAbsenceReason2");
        serviceProviderAbsenceEntity2.setServiceProviderId(serviceProviderOne.getId());
        serviceProviderAbsenceTwo = serviceProviderAbsenceDAO.save(serviceProviderAbsenceEntity2);

        ServiceProviderAbsenceEntity serviceProviderAbsenceEntity3 = new ServiceProviderAbsenceEntity();
        SimpleDateFormat textFormat3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fromDate3 = "2019-12-17 00:00:00";
        String toDate3 = "2019-12-25 00:00:00";
        String fromTime3 = "2019-12-10 11:00:00";
        String toTime3 = "2019-12-15 16:00:00";
        serviceProviderAbsenceEntity3.setFromDate(new java.sql.Date(textFormat3.parse(fromDate3).getTime()));
        serviceProviderAbsenceEntity3.setToDate(new java.sql.Date(textFormat3.parse(toDate3).getTime()));
        serviceProviderAbsenceEntity3.setFromTime(new java.sql.Time(textFormat3.parse(fromTime3).getTime()));
        serviceProviderAbsenceEntity3.setToTime(new java.sql.Time(textFormat3.parse(toTime3).getTime()));
        serviceProviderAbsenceEntity3.setAbsenceTypeId(absenceTypeOne.getId());
        serviceProviderAbsenceEntity3.setAbsenceReason("TestAbsenceReason3");
        serviceProviderAbsenceEntity3.setServiceProviderId(serviceProviderOne.getId());
        serviceProviderAbsenceThree = serviceProviderAbsenceDAO.save(serviceProviderAbsenceEntity3);

    }

    private void tearDown(){
        serviceProviderAbsenceDAO.deleteAllInBatch();
        serviceProviderAbsenceDAO.flush();
        serviceProviderDAO.deleteAllInBatch();
        serviceProviderDAO.flush();
        absenceTypeDAO.deleteAllInBatch();
        absenceTypeDAO.flush();
        serviceProviderTypeDAO.deleteAllInBatch();
        serviceProviderTypeDAO.flush();
        serviceProviderCompetencyDAO.deleteAllInBatch();
        serviceProviderCompetencyDAO.flush();
        departmentDAO.deleteAllInBatch();
        departmentDAO.flush();
        serviceProviderPreferredNotificationDAO.deleteAllInBatch();
        serviceProviderPreferredNotificationDAO.flush();
        transportTypeDAO.deleteAllInBatch();
        transportTypeDAO.flush();
        serviceProviderSourceDAO.deleteAllInBatch();
        serviceProviderSourceDAO.flush();
    }

    @Test
    public void testAddingServiceProviderAbsence(){
        try{
            setUp();
            ServiceProviderAbsenceEntity serviceProviderAbsenceEntity = new ServiceProviderAbsenceEntity();
            SimpleDateFormat textFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fromDate = "2019-12-10 00:00:00";
            String toDate = "2019-12-15 00:00:00";
            String fromTime = "2019-12-10 12:00:00";
            String toTime = "2019-12-15 12:00:00";
            serviceProviderAbsenceEntity.setFromDate(new java.sql.Date(textFormat.parse(fromDate).getTime()));
            serviceProviderAbsenceEntity.setToDate(new java.sql.Date(textFormat.parse(toDate).getTime()));
            serviceProviderAbsenceEntity.setFromTime(new java.sql.Time(textFormat.parse(fromTime).getTime()));
            serviceProviderAbsenceEntity.setToTime(new java.sql.Time(textFormat.parse(toTime).getTime()));
            serviceProviderAbsenceEntity.setAbsenceTypeId(absenceTypeOne.getId());
            serviceProviderAbsenceEntity.setAbsenceReason("TestAbsenceReasonAdded");
            serviceProviderAbsenceEntity.setServiceProviderId(serviceProviderOne.getId());
            serviceProviderAbsenceDAO.addServiceProviderAbsence(serviceProviderAbsenceEntity);
            Assert.fail();
        }catch (ParseException e){
            Assert.fail();
        }catch (Exception e){
            Assert.assertEquals("There is already absence registered between the dates for the service provider.", e.getMessage());
        }finally {
            tearDown();
        }
    }

    @Test
    public void testAddingServiceProviderAbsenceAbsenceReasonShouldPass(){
        try{
            setUp();
            ServiceProviderAbsenceEntity serviceProviderAbsenceEntity = new ServiceProviderAbsenceEntity();
            SimpleDateFormat textFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fromDate = "2019-01-25 00:00:00";
            String toDate = "2019-01-28 00:00:00";
            String fromTime = "2019-01-25 12:00:00";
            String toTime = "2019-01-28 12:00:00";
            serviceProviderAbsenceEntity.setFromDate(new java.sql.Date(textFormat.parse(fromDate).getTime()));
            serviceProviderAbsenceEntity.setToDate(new java.sql.Date(textFormat.parse(toDate).getTime()));
            serviceProviderAbsenceEntity.setFromTime(new java.sql.Time(textFormat.parse(fromTime).getTime()));
            serviceProviderAbsenceEntity.setToTime(new java.sql.Time(textFormat.parse(toTime).getTime()));
            serviceProviderAbsenceEntity.setAbsenceTypeId(absenceTypeOne.getId());
            serviceProviderAbsenceEntity.setAbsenceReason("TestAbsenceReasonAdded23654");
            serviceProviderAbsenceEntity.setServiceProviderId(serviceProviderOne.getId());
            ServiceProviderAbsenceDto serviceProviderAbsence = serviceProviderAbsenceDAO.addServiceProviderAbsence(serviceProviderAbsenceEntity);
            Assert.assertEquals("TestAbsenceReasonAdded23654", serviceProviderAbsence.getAbsenceReason());
        }catch (ParseException e){
            Assert.fail();
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testListingServiceProviderAbsencesSizeShouldBeThree(){
        try{
            setUp();
            Assert.assertEquals(3, serviceProviderAbsenceDAO.listAllServiceProviderAbsences().size());
        }catch (ParseException e){
            Assert.fail();
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testListingServiceProviderByDateSizeShouldBeTwo(){
        try{
            setUp();
            SimpleDateFormat textFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int size = serviceProviderAbsenceDAO.findServiceProviderAbsencesInPeriod(new java.sql.Date(textFormat.parse("2019-12-09 00:00:00").getTime()),new java.sql.Date(textFormat.parse("2019-12-21 00:00:00").getTime())).size();
            Assert.assertEquals(2, size);
        }catch (ParseException e){
            Assert.fail();
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }


    @Test
    public void testListingServiceProviderByDateSizeShouldBeOneServiceProvider(){
        try{
            setUp();
            SimpleDateFormat textFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int size = serviceProviderAbsenceDAO.findServiceProviderAbsencesForServiceProviderInPeriod(new java.sql.Date(textFormat.parse("2019-12-01 00:00:00").getTime()),new java.sql.Date(textFormat.parse("2020-01-31 00:00:00").getTime()),serviceProviderOne.getId()).size();
            Assert.assertEquals(3,size);
        }catch (ParseException e){
            Assert.fail();
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testListingServiceProviderBeforeNoon(){
        try{
            setUp();
            SimpleDateFormat textFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int size = serviceProviderAbsenceDAO.findServiceProviderAbsencesInTime(new java.sql.Time(textFormat.parse("2019-12-01 06:00:00").getTime()),new java.sql.Time(textFormat.parse("2020-01-31 13:00:00").getTime())).size();
            Assert.assertEquals(1,size);
        }catch (ParseException e){
            Assert.fail();
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testListingServiceProviderBeforeNoonAndServiceProvider(){
        try{
            setUp();
            SimpleDateFormat textFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int size = serviceProviderAbsenceDAO.findServiceProviderAbsencesForServiceProviderInTime(new java.sql.Time(textFormat.parse("2019-12-01 06:00:00").getTime()),new java.sql.Time(textFormat.parse("2020-01-31 13:00:00").getTime()), serviceProviderOne.getId()).size();
            Assert.assertEquals(1,size);
        }catch (ParseException e){
            Assert.fail();
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }



    @Test
    public void testListingServiceProviderAbsencesById(){
        try{
            setUp();
            int size = serviceProviderAbsenceDAO.findServiceProviderAbsencesForServiceProvider(serviceProviderOne.getId()).size();
            Assert.assertEquals(3,size);
        }catch (ParseException e){
            Assert.fail();
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testDeletingServiceProviderAbsenceNewListSizeShouldBeTwo(){
        try{
            setUp();
            serviceProviderAbsenceDAO.deleteServiceProviderAbsence(serviceProviderAbsenceOne.getId());
            int size = serviceProviderAbsenceDAO.findServiceProviderAbsencesForServiceProvider(serviceProviderOne.getId()).size();
            Assert.assertEquals(2,size);
        }catch (ParseException e){
            Assert.fail();
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }


    @Test
    public void testDeletingServiceProviderAbsenceWithNotExistingIdShouldThrowException(){
        try{
            setUp();
            serviceProviderAbsenceDAO.deleteServiceProviderAbsence(-1);
            Assert.fail();
        }catch (ParseException e){
            Assert.fail();
        }catch (Exception e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }
}
