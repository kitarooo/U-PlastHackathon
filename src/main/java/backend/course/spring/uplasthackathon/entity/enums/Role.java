package backend.course.spring.uplasthackathon.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN, ROLE_USER, ROLE_EMPLOYEE;

    @Override
    public String getAuthority() {
        return name();
    }
}
