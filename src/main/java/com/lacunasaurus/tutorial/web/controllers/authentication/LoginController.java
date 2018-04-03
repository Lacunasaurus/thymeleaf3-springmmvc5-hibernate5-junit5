package com.lacunasaurus.tutorial.web.controllers.authentication;

import com.lacunasaurus.tutorial.entities.UserAccount;
import com.lacunasaurus.tutorial.web.controllers.website.WebController;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

public class LoginController implements WebController {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, ITemplateEngine templateEngine) throws Exception {
        if (request.getMethod().equalsIgnoreCase("POST")) {
            signUp(request, response, servletContext, templateEngine);
        } else {
            navigate(request, response, servletContext, templateEngine);
        }
    }

    private void navigate(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, ITemplateEngine templateEngine) throws Exception {
        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        templateEngine.process("pages/authentication/login", ctx, response.getWriter());
    }

    private void signUp(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, ITemplateEngine templateEngine) throws Exception {
        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());

        Map<String, UserAccount> userAccounts = (HashMap<String, UserAccount>) ctx.getServletContext().getAttribute("accountList");
        UserAccount userAccount = userAccounts.get(request.getParameter("login_pseudo"));
        
        if (userAccount != null && userAccount.getPassword().equalsIgnoreCase(request.getParameter("login_password"))) {
            request.getSession().setAttribute("userAccount", userAccount);
            templateEngine.process("pages/website/home", ctx, response.getWriter());
        } else {
            ctx.setVariable("fail", "No pseudo / password for given informations");
            templateEngine.process("pages/authentication/login", ctx, response.getWriter());
        }
    }
}
