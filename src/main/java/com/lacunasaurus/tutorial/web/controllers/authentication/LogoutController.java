package com.lacunasaurus.tutorial.web.controllers.authentication;

import com.lacunasaurus.tutorial.entities.UserAccount;
import com.lacunasaurus.tutorial.web.controllers.website.WebController;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

public class LogoutController implements WebController {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, ITemplateEngine templateEngine) throws Exception {
        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        
        UserAccount userAccount = (UserAccount) request.getSession().getAttribute("userAccount");
        if(userAccount != null) {
            request.getSession().removeAttribute("userAccount");
        }
        
        templateEngine.process("pages/authentication/login", ctx, response.getWriter());
    }

}
