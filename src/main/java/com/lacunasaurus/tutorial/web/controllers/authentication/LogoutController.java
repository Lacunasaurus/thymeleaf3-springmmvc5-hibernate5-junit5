package com.lacunasaurus.tutorial.web.controllers.authentication;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/logout", "/deconnexion"})
public class LogoutController {

    @Autowired
    HttpSession session;

    @GetMapping
    public String navigate() {
        session.removeAttribute("userAccount");
        return "redirect:/connexion";
    }
}
