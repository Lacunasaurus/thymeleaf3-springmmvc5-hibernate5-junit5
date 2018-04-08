package com.lacunasaurus.tutorial.web.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class TutorialWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) throws ServletException {
        
        // Create the dispatcher servlet's Spring application context
        AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
        dispatcherServlet.register(ApplicationConfig.class);
        
        // Create and register the DispatcherServlet
        ServletRegistration.Dynamic registration = container.addServlet("dispatcher", new DispatcherServlet(dispatcherServlet));
        registration.setLoadOnStartup(1);
        registration.addMapping("/*");
    }
}
