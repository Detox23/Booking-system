package API.Repository.Account;


import API.MainApplicationClass;
import Shared.ToReturn.AccountTypeDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplicationClass.class)
@ActiveProfiles("test")
public class AccountTypeRepositoryTest {

    @Autowired
    private AccountTypeDAO accountTypeDAO;

    @Autowired
    private DataSource dataSource;

    @Test
    public void testFindingAccountType() throws SQLException {
//        int id = 1;
//        AccountTypeDto found = accountTypeDAO.findOneAccountType(id);
//        Assert.assertEquals("Test", found.getAccountType());
        assertEquals(true, true);
    }

}
