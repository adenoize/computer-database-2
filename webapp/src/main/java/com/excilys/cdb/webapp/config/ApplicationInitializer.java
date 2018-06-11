package com.excilys.cdb.webapp.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.excilys.cdb.service.config.ServiceConfig;

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
      return new Class[] { ServiceConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
      return new Class[] { WebAppConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
      return new String[] {"/"};
    }

    @Override
    protected DispatcherServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
        final DispatcherServlet dispatcherServlet = (DispatcherServlet) super.createDispatcherServlet(servletAppContext);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        return dispatcherServlet;
    }

  }
//implements WebApplicationInitializer {
//
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.register(WebAppConfig.class);
//        context.setServletContext(servletContext);
//
//        ContextLoaderListener contextLoaderListener = new ContextLoaderListener(context);
//        servletContext.addListener(contextLoaderListener);
//
//        Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
//        servlet.setLoadOnStartup(1);
//        servlet.addMapping("/");
//        servlet.setInitParameter("throwExceptionIfNoHandlerFound", "true");
//    }
//
//}