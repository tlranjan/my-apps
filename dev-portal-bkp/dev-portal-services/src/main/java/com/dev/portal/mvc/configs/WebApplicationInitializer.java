package com.dev.portal.mvc.configs;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    public WebApplicationInitializer() {
        super(MvcConfig.class, SecurityConfig.class);
        //new InitializingMongoSchema().execute();
    }

    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
        //servletContext.addListener(new ServiceContextListener());
        servletContext.addListener(new net.bull.javamelody.SessionListener());
        servletContext.addFilter("CorsFilter", CORSFilter.class).addMappingForUrlPatterns(null, false, "/*");
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dev-portal-dispatcher",
                new DispatcherServlet(new AnnotationConfigWebApplicationContext()));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/*");
    }

}
