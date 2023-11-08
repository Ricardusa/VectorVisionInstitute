package com.capstone.vectorvisioninstitute.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

    /** Method helps Spring Data JPA to identify who is the logged-in user
     * trying to perform certain actions inside the web application,
     * If the action is being performed by an Anonymous user(Ex:Contact Page)
     * the authentication information inside the security context folder will be Null
     * and the value that the method will be returning will be null in this case, that is
     * also why we need to be returning an Optional representation of String
     */
    @Override
    public Optional<String> getCurrentAuditor(){ //Anonymous User return value = Null
        //with this operation our application will now know who is which user
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    /*@Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(authentication -> {
                    if (authentication.getName() != null) {
                        return authentication.getName();
                    } else {
                        // Handle the case when the authentication has no name
                        return "anonymousUser";
                    }
                });
    }*/
}
