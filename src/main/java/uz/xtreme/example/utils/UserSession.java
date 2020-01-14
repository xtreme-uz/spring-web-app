package uz.xtreme.example.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.xtreme.example.domain.auth.User;
import uz.xtreme.example.dto.auth.CustomUserDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: Rustambekov Avazbek
 * Date: 25/11/2019
 * Time: 15:45
 */
@Component
public class UserSession {

    HttpServletRequest request;
    BaseUtils utils;

    @Autowired
    public UserSession(HttpServletRequest request, BaseUtils utils) {
        this.request = request;
        this.utils = utils;
    }

    public User getUser() {
        User user = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof User) {
                user = (User) authentication.getPrincipal();
            }
            if (authentication.getPrincipal() instanceof CustomUserDetails) {
                user = ((CustomUserDetails) authentication.getPrincipal()).getUser();
            }
        }
        return user;
    }

}
