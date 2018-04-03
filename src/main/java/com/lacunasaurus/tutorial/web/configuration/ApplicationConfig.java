package com.lacunasaurus.tutorial.web.configuration;

import com.lacunasaurus.tutorial.web.controllers.admin.AdminUserAccountController;
import com.lacunasaurus.tutorial.web.controllers.authentication.LoginController;
import com.lacunasaurus.tutorial.web.controllers.authentication.LogoutController;
import com.lacunasaurus.tutorial.web.controllers.website.HomeController;
import com.lacunasaurus.tutorial.web.controllers.authentication.SignUpController;
import com.lacunasaurus.tutorial.web.controllers.website.WebController;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

public class ApplicationConfig {

    private TemplateEngine templateEngine;
    private Map<String, WebController> controllersByURL;

    public ApplicationConfig(final ServletContext servletContext) {

        super();

        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);

        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheTTLMs(3600000L);
        templateResolver.setCacheable(false);

        this.templateEngine = new TemplateEngine();
        this.templateEngine.setTemplateResolver(templateResolver);

        this.controllersByURL = new HashMap<>();
        this.controllersByURL.put("/", new HomeController());
        this.controllersByURL.put("/sign-up", new SignUpController());
        this.controllersByURL.put("/login", new LoginController());
        this.controllersByURL.put("/logout", new LogoutController());
        this.controllersByURL.put("/admin/user-accounts", new AdminUserAccountController());
    }

    public WebController resolveControllerForRequest(final HttpServletRequest request) {
        final String path = getRequestPath(request);
        return this.controllersByURL.get(path);
    }

    public ITemplateEngine getTemplateEngine() {
        return this.templateEngine;
    }

    private static String getRequestPath(final HttpServletRequest request) {

        String requestURI = request.getRequestURI();
        final String contextPath = request.getContextPath();

        final int fragmentIndex = requestURI.indexOf(';');
        if (fragmentIndex != -1) {
            requestURI = requestURI.substring(0, fragmentIndex);
        }

        if (requestURI.startsWith(contextPath)) {
            return requestURI.substring(contextPath.length());
        }
        return requestURI;
    }
}
