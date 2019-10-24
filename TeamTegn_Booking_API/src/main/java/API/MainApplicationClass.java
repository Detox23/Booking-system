package API;
import DAO.AccountDAO;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
public class MainApplicationClass {
    public static void main(String[] args){
        SpringApplication.run(MainApplicationClass.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Bean
    public AccountDAO accountDao() {
        return new AccountDAO();
    }
}
