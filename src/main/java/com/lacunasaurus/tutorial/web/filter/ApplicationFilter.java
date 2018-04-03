package com.lacunasaurus.tutorial.web.filter;

import com.lacunasaurus.tutorial.entities.UserAccount;
import com.lacunasaurus.tutorial.web.configuration.ApplicationConfig;
import com.lacunasaurus.tutorial.web.controllers.website.WebController;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.thymeleaf.ITemplateEngine;

public class ApplicationFilter implements Filter {

    private ServletContext servletContext;
    private ApplicationConfig application;

    public ApplicationFilter() {
        super();
    }

    private void initSampleData(ServletContext servletContext) {
        Map<String, UserAccount> userAccounts = (HashMap<String, UserAccount>) servletContext.getAttribute("accountList");

        if (userAccounts == null) {
            userAccounts = new HashMap<>();
            userAccounts.put("login1", new UserAccount("email1@domain.com", "login1", "password1"));
            userAccounts.put("login2",new UserAccount("email2@domain.com", "login2", "password2"));
            userAccounts.put("login3",new UserAccount("email3@domain.com", "login3", "password3"));
            
            servletContext.setAttribute("accountList", userAccounts);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.servletContext = filterConfig.getServletContext();
        this.application = new ApplicationConfig(this.servletContext);
        initSampleData(servletContext);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!process((HttpServletRequest) request, (HttpServletResponse) response)) {
            chain.doFilter(request, response);
        }
    }

    private boolean process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {

        try {
            if (request.getRequestURI().startsWith("/resources/css")
                    || request.getRequestURI().startsWith("/resources/images")
                    || request.getRequestURI().startsWith("/resources/favicon")) {
                return false;
            }

            WebController controller = this.application.resolveControllerForRequest(request);
            if (controller == null) {
                return false;
            }

            ITemplateEngine templateEngine = this.application.getTemplateEngine();

            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            controller.process(request, response, this.servletContext, templateEngine);

            return true;

        } catch (Exception e) {
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (final IOException ignored) {
            }
            throw new ServletException(e);
        }
    }

    @Override
    public void destroy() {
    }

}
