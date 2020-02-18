package com.miciu.spring.app;

import com.miciu.spring.app.currentuser.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Slf4j
@Controller
public class IndexController {


    /**
     * Principal.getName() - currentUsername
     * principal not contains a password
     * <p>
     * authentication.getAuthorities() - contains roles for selected user
     */
    @GetMapping(value = {"/", "index.html"})
    public ModelAndView index(CurrentUser user) {//Principal principal, Authentication authentication

        log.info("user: " + user.getName() + " roles: " + user.getRoles());
        /**
         * sample log: user: admin roles: ROLE_ADMIN
         */
        return new ModelAndView("index");
    }
}