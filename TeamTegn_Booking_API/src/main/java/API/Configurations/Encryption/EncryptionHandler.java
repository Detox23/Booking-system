package API.Configurations.Encryption;

import API.Exceptions.NotEnoughDataException;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class EncryptionHandler {

    public String encrypt(String value) {
        try {
            return Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));
        }catch(NullPointerException nullPointerException){
            throw new NotEnoughDataException("Providing CPR number is required to complete this operation.");
        }
    }


    public String decrypt(String value) {
        return new String(Base64.getDecoder().decode(value.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
    }
}
