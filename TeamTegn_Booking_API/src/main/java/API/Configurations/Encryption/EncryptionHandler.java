package API.Configurations.Encryption;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class EncryptionHandler {

    public String encrypt(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }


    public String decrypt(String value) {
        return new String(Base64.getDecoder().decode(value.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
    }
}
