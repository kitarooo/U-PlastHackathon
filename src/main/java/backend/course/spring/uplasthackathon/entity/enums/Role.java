package backend.course.spring.uplasthackathon.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN, ROLE_USER, EMPLOYEE;

    @Override
    public String getAuthority() {
        return null;
    }
}
