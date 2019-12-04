package API.Configurations;

import API.Repository.SystemUser.SystemUserDAO;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public class UsernameAuditorAware implements AuditorAware<Integer> {

    @Autowired
    private SystemUserDAO systemUserDAO;

    @Override
    public Optional<Integer> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        int id = systemUserDAO.findDistinctByUserNameIs(((User) authentication.getPrincipal()).getUsername()).get().getId();
        return Optional.of(id);
    }
}