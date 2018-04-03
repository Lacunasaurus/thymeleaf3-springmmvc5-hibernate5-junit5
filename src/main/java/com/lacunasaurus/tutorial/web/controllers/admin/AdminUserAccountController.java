package com.lacunasaurus.tutorial.web.controllers.admin;

import com.lacunasaurus.tutorial.entities.UserAccount;
import com.lacunasaurus.tutorial.web.controllers.website.WebController;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

public class AdminUserAccountController implements WebController {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, ITemplateEngine templateEngine) throws Exception {
        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        
        ctx.setVariable("accountList", ((HashMap<String, UserAccount>) servletContext.getAttribute("accountList")));
        
        templateEngine.process("pages/admin/user-accounts", ctx, response.getWriter());
    }

}
