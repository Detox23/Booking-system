package API.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUsername("sa");
        dataSource.setPassword("90809988Qwe");
        dataSource.setUrl("jdbc:sqlserver://localhost\\MSSQLSERVER:1433;database=TeamTegn_BookingSystem_Devleopment;sendTimeAsDateTime=false");
        return dataSource;
    }
}
