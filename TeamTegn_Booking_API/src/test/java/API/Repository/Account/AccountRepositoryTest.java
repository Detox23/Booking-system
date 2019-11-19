package API.Repository.Account;


import API.Database_Entities.AccountEntity;
import API.Database_Entities.AccountTypeEntity;
import API.MainApplicationClass;
import Shared.ForCreation.AccountForCreationDto;
import Shared.ToReturn.AccountDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplicationClass.class)
@ActiveProfiles("test")
public class AccountRepositoryTest {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AccountTypeDAO accountTypeDAO;

    private void setUp() {
        AccountForCreationDto accountOne = new AccountForCreationDto();
        accountOne.setAccountName("TestAccountName");

        AccountForCreationDto accountTwo = new AccountForCreationDto();
        accountTwo.setAccountName("TestAccountName2");

        List<String> eans = new ArrayList<>();
        eans.add("123456789");
        eans.add("987654321");

        AccountTypeEntity accountTypeEntity = new AccountTypeEntity();
        accountTypeEntity.setAccountType("TestAccountType");
        AccountTypeEntity result = accountTypeDAO.save(accountTypeEntity);

        accountDAO.addOneAccount(mapper.map(accountOne, AccountEntity.class), null, result.getId());

        accountDAO.addOneAccount(mapper.map(accountTwo, AccountEntity.class), eans, result.getId());
    }


    private void setYo() {
        accountDAO.deleteAll();
        accountTypeDAO.deleteAll();
    }

    @Test
    public void testListAllAccounts() {
        setUp();
        List<AccountDto> returnList = accountDAO.listAllAccounts();
        Assert.assertEquals(2, returnList.size());
    }


}
