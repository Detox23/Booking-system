//package API.Helpers;
//
////
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by Fernflower decompiler)
////
//
//import API.Models.Database_Entities.*;
//import API.Repository.Account.AccountDAO;
//import API.Repository.Assignment.AssignmentDAO;
//import API.Repository.Assignment.AssignmentTitleDAO;
//import API.Repository.Assignment.AssignmentTypeDAO;
//import API.Repository.Assignment.Assignment_ServiceProviderDAO;
//import API.Repository.ServiceUser.ServiceUserAccountsDAO;
//import API.Repository.ServiceUser.ServiceUserDAO;
//import com.google.common.io.Files;
//import com.itextpdf.text.DocumentException;
//import org.apache.commons.io.FileUtils;
//import org.hibernate.ObjectNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.xhtmlrenderer.pdf.ITextRenderer;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.IOException;
//import java.io.Serializable;
//import java.nio.charset.Charset;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//@Service
//public class PdfGeneratorHelper {
//
//    private ServiceUserDAO serviceUserDAO;
//
//    private ServiceUserAccountsDAO serviceUserAccountsDAO;
//
//    private Assignment_ServiceProviderDAO providerDAO;
//
//    private AssignmentTitleDAO titleDAO;
//
//    private AssignmentTypeDAO assignmentTypeDAO;
//
//    private AccountDAO accountDAO;
//
//    private AssignmentDAO assignmentDAO;
//
//    @Autowired
//    public void setServiceUserDAO(ServiceUserDAO serviceUserDAO) {
//        this.serviceUserDAO = serviceUserDAO;
//    }
//
//    @Autowired
//    public void setServiceUserAccountsDAO(ServiceUserAccountsDAO serviceUserAccountsDAO) {
//        this.serviceUserAccountsDAO = serviceUserAccountsDAO;
//    }
//
//    @Autowired
//    public void setProviderDAO(Assignment_ServiceProviderDAO providerDAO) {
//        this.providerDAO = providerDAO;
//    }
//
//    @Autowired
//    public void setTitleDAO(AssignmentTitleDAO titleDAO) {
//        this.titleDAO = titleDAO;
//    }
//
//    @Autowired
//    public void setAssignmentTypeDAO(AssignmentTypeDAO assignmentTypeDAO) {
//        this.assignmentTypeDAO = assignmentTypeDAO;
//    }
//
//    @Autowired
//    public void setAccountDAO(AccountDAO accountDAO) {
//        this.accountDAO = accountDAO;
//    }
//
//    @Autowired
//    public void setAssignmentDAO(AssignmentDAO assignmentDAO) {
//        this.assignmentDAO = assignmentDAO;
//    }
//
//    public PdfGeneratorHelper() {
//    }
//
//    public void generatePDFFromHTML(int id) throws IOException {
//        AssignmentEntity assignmentEntity = assignmentDAO.findById(id).get();
//        ServiceUserEntity userEntity = serviceUserDAO.findById(assignmentEntity.getServiceUserId()).get();
//        AssignmentTitleEntity assignmentTitleEntity = titleDAO.findById(assignmentEntity.getAssignmentTitle()).get();
//        ServiceUserAccountEntity serviceUserAccountEntity = serviceUserAccountsDAO.findById(assignmentEntity.getServiceUserAccountId()).get();
//        AccountEntity accountEntity = accountDAO.findById(serviceUserAccountEntity.getAccountId()).get();
//        List<AssignmentServiceProviderEntity> serviceProviderList = providerDAO.findAllByAssignmentIdIs(assignmentEntity.getId());
//        AssignmentTypeEntity assignmentTypeEntity = assignmentTypeDAO.findById(assignmentEntity.getAssignmentTitle()).get();
//
//        if (assignmentEntity != null && userEntity != null && assignmentTitleEntity != null && serviceUserAccountEntity != null && accountEntity != null && serviceProviderList != null && assignmentTypeEntity != null) {
//            List<String> serviceProviderNamesList = new ArrayList();
//            serviceProviderList.forEach((n) -> {
//                serviceProviderNamesList.add(n.getServiceProviderFirstName() + " " + n.getServiceProviderMiddleName() + " " + n.getServiceProviderLastName());
//            });
//            String absolutePath = Paths.get("").toAbsolutePath().toString();
//            String content = Files.toString(new File(absolutePath + "\\TeamTegn_Booking_API\\src\\main\\java\\API\\HTML/Report.html"), Charset.defaultCharset());
//            content = content.replace("{PersonFullName}", userEntity.getFirstName() == null ? "" : (userEntity.getFirstName() + " " + userEntity.getMiddleName() == null ? "" : (userEntity.getMiddleName() + " " + userEntity.getLastName() == null ? "" : userEntity.getLastName()))).replace("{CPRNumber}", userEntity.getCpr() == null ? "" : userEntity.getCpr()).replace("{PhoneNumber}", userEntity.getMobileCode() == null ? "" : (userEntity.getMobileCode() + " " + userEntity.getMobileNumber() == null ? "" : userEntity.getMobileNumber())).replace("{Interpret}", serviceProviderNamesList == null ? "" : serviceProviderNamesList.toString()).replace("{AssignmentDate}", assignmentEntity.getAssignmentDate() == null ? "" : assignmentEntity.getAssignmentDate().toString()).replace("{AssignmentTitle}", assignmentTitleEntity.getTitle() == null ? "" : assignmentTitleEntity.getTitle()).replace("{AssignmentDescription}", assignmentEntity.getAssignmentDescription() == null ? "" : assignmentEntity.getAssignmentDescription()).replace("{AssignmentPlace}", assignmentEntity.getAssignmentPlace() == null ? "" : assignmentEntity.getAssignmentPlace()).replace("{AssignmentTypeName}", assignmentTypeEntity.getAssignmentTypeName() == null ? "" : assignmentTypeEntity.getAssignmentTypeName()).replace("{AssignmentDestination}", assignmentEntity.getDestinationStreet() == null ? "" : assignmentEntity.getDestinationStreet()).replace("{DestinationPostCode}", assignmentEntity.getDestinationPostCode() == null ? "" : assignmentEntity.getDestinationPostCode()).replace("{DestinationCity}", assignmentEntity.getDestinationCity() == null ? "" : assignmentEntity.getDestinationCity()).replace("{DestinationCountry}", assignmentEntity.getDestinationCountry() == null ? "" : assignmentEntity.getDestinationCountry()).replace("{Zone}", assignmentEntity.getZone() == null ? "" : assignmentEntity.getZone()).replace("{KTSTID}", assignmentEntity.getKtstid() == null ? "" : assignmentEntity.getKtstid()).replace("{AccountName}", accountEntity.getAccountName() == null ? "" : accountEntity.getAccountName()).replace("{AccountStreet}", accountEntity.getStreet() == null ? "" : accountEntity.getStreet()).replace("{AccountZipcode}", accountEntity.getPostcode() == null ? "" : accountEntity.getPostcode()).replace("{AccountDestinationCity}", accountEntity.getCity() == null ? "" : accountEntity.getCity()).replace("{DestinationCountry}", accountEntity.getCountry() == null ? "" : accountEntity.getCountry()).replace("{AccountEAN}", assignmentEntity.getServiceUserAccountEan() == null ? "" : assignmentEntity.getServiceUserAccountEan()).replace("{AccountCVR}", accountEntity.getCvrNumber() == null ? "" : accountEntity.getCvrNumber()).replace("{AssignmentStartTime}", assignmentEntity.getStartTime() == null ? "" : assignmentEntity.getStartTime().toString()).replace("{AssignmentEndTime}", assignmentEntity.getEndTime() == null ? "" : assignmentEntity.getEndTime().toString()).replace("{AssignmentHours}", "" + assignmentEntity.getTotalTime());
//            ITextRenderer renderer = new ITextRenderer();
//            renderer.setDocumentFromString(content);
//            renderer.layout();
//            try {
//                ByteArrayOutputStream fos = new ByteArrayOutputStream(content.length());
//                try {
//                    renderer.createPDF(fos);
//                    byte[] donePdf = fos.toByteArray();
//                    UUID uuid = UUID.randomUUID();
//                    FileUtils.writeByteArrayToFile(new File("" + uuid + ".pdf"), donePdf);
//                } catch (Throwable var16) {
//                    try {
//                        fos.close();
//                    } catch (Throwable var15) {
//                        var16.addSuppressed(var15);
//                    }
//                    throw var16;
//                }
//                fos.close();
//            } catch (Exception var17) {
//            }
//
//        } else {
//            throw new ObjectNotFoundException(Serializable.class, "Not found atributes that are  needed for generating report pdf");
//        }
//    }
//}
