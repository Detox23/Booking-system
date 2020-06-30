package AccountTests;
import API.Repository.Account.IAccountDAOImpl;
import Objects.Factory.Database_Entities.AccountEntity;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
/*
@RunWith(SpringRunner.class) is used to provide a bridge between Spring Boot test features and JUnit.
Whenever we are using any Spring Boot testing features in our JUnit tests, this annotation will be
required.
*/

@DataJpaTest
/*
@DataJpaTest provides some standard setup needed for testing the persistence layer:

configuring H2, an in-memory database
setting Hibernate, Spring Data, and the DataSource
performing an @EntityScan
turning on SQL logging
*/

public class AccountAPITests {
    @Autowired
    private TestEntityManager entityManager;

    @MockBean
    private IAccountDAOImpl IAccountDAOImpl;


    @Before
    public void setUp(){
        AccountEntity account = new AccountEntity();
        account.setId(123);
        Mockito.when(IAccountDAOImpl.findAccountByID(account.getId())).thenReturn(account);
    }

}
