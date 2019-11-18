package AccountTests;

import API.MainApplicationClass;
import API.Repository.Account.AccountDAOImpl;
import Shared.ForCreation.AccountForCreationDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@RunWith(SpringRunner.class)
/*
@RunWith(SpringRunner.class) is used to provide a bridge between Spring Boot test features and JUnit.
Whenever we are using any Spring Boot testing features in our JUnit tests, this annotation will be
required.
*/
@DataJpaTest
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = MainApplicationClass.class

)
@AutoConfigureMockMvc
/*
@DataJpaTest provides some standard setup needed for testing the persistence layer:

configuring H2, an in-memory database
setting Hibernate, Spring Data, and the DataSource
performing an @EntityScan
turning on SQL logging
*/
@TestPropertySource(locations = "classpath:application-test.properties")
public class AccountAPITests {
    private static final String CREATE_ACCOUNT_SQL_SCRIPT = "classpath:Account.sql";
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private JdbcTemplate jdbc;
    @MockBean
    private AccountDAOImpl accountDAO;

    @Before
    public void setUp() throws SQLException {
        ScriptUtils.executeSqlScript(jdbc.getDataSource().getConnection(), new ClassPathResource(CREATE_ACCOUNT_SQL_SCRIPT));
    }

    @Test
    public void testAddig() {
        String name = "Adam";
        int accountTypeId = 3;
        AccountForCreationDto a = new AccountForCreationDto();
        a.setAccountName(name);
        a.setAccountTypeId(accountTypeId);
//        assertEquals(0, JdbcTestUtils.countRowsInTableWhere(jdbc,"Account","AccountName ='" + name + "'"));

    }

}
