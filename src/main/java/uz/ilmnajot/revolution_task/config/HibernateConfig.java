package uz.ilmnajot.revolution_task.config;

import lombok.NonNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import uz.ilmnajot.revolution_task.template.UserSession;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class HibernateConfig implements AuditorAware<Long> {

    private final UserSession userSession;

    public HibernateConfig(UserSession userSession) {
        this.userSession = userSession;
    }

    @Override
    @NonNull
    public Optional<Long> getCurrentAuditor() {
        return Optional.ofNullable(userSession.getUser() != null
                ? userSession.getUser().getId()
                : null);

    }
}

