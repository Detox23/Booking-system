package API.Configurations;

import API.Configurations.Encryption.EncryptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EcryptionConfig {
    @Bean
    public EncryptionHandler encryptionHandler(){
        return new EncryptionHandler();
    }
}
