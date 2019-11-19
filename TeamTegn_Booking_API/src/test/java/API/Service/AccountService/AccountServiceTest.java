package API.Service.AccountService;


import API.Database_Entities.AccountEanEntity;
import API.Database_Entities.AccountEntity;
import API.MainApplicationClass;
import API.Repository.Account.AccountDAO;
import API.Repository.Account.AccountEanDAO;
import API.Services.AccountService.AccountService;
import Shared.ForCreation.AccountForCreationDto;
import Shared.ToReturn.AccountDto;
import Shared.ToReturn.AccountEanDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplicationClass.class)
@ActiveProfiles("test")
public class AccountServiceTest {

    @MockBean
    private AccountDAO accountDAO;

    @MockBean
    private AccountEanDAO accountEanDAO;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ModelMapper modelMapper;


    @Test
    public void testAddAccountShouldReturnObjectWithEANS(){
        try {
            AccountForCreationDto accountForCreationDto = new AccountForCreationDto();
            AccountDto returned = new AccountDto();
            List<AccountEanDto> eans = new ArrayList<>();
            AccountEanDto eanDto = new AccountEanDto();
            eanDto.setAccountId(0);
            eanDto.setEanNumber("123456");
            eans.add(eanDto);
            AccountEanDto eanDto1 = new AccountEanDto();
            eanDto1.setAccountId(0);
            eanDto1.setEanNumber("654321");
            eans.add(eanDto1);

            when(accountDAO.addOneAccount(modelMapper.map(accountForCreationDto, AccountEntity.class), accountForCreationDto.getEan(), accountForCreationDto.getAccountTypeId())).thenReturn(returned);
            when(accountEanDAO.findListOfAccountEANNumbers(returned.getId())).thenReturn(eans);
            AccountDto result = accountService.addAccount(accountForCreationDto);
            Assert.assertEquals(2, result.getEan().size());
        } catch(Exception e){
            Assert.assertFalse(false);
        } finally {

        }

    }
}
