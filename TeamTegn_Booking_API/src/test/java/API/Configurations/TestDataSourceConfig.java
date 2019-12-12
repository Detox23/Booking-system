package API.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;


@Configuration
@EnableJpaRepositories
@EnableJpaAuditing
public class TestDataSourceConfig {




    @Bean
    @Primary
    public DataSource dataSourceTest() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUsername("sa");
        dataSource.setPassword("90809988Qwe");
        dataSource.setUrl("jdbc:sqlserver://localhost\\SQLP:1433;database=Test;sendTimeAsDateTime=false");
        return dataSource;
    }

    @Bean
    @Primary
    public EntityManager entityManagerFactory() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceTest");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }

}
