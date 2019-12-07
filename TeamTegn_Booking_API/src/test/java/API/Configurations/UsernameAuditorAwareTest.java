package API.Configurations;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class UsernameAuditorAwareTest implements AuditorAware<Integer> {


    @Override
    public Optional<Integer> getCurrentAuditor() {

        return Optional.of(1);
    }
}
