package API.Configurations;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class UsernameAuditorAwareTest implements AuditorAware<Integer> {

    @Override
    @Primary
    public Optional<Integer> getCurrentAuditor() {
        return Optional.of(1);
    }
}
