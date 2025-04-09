package com.green.attaparunever2.config.security;

import com.green.attaparunever2.common.repository.CodeRepository;
import com.green.attaparunever2.config.jwt.JwtUser;
import com.green.attaparunever2.entity.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {

    public JwtUser getSignedUser() {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        return myUserDetails == null ? null : myUserDetails.getJwtUser();
    }

    public long getSignedUserId() {
        return getSignedUser().getSignedUserId();
    }

}
