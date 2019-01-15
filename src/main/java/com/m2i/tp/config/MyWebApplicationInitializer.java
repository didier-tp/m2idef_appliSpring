package com.m2i.tp.config;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class MyWebApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
			WebApplicationContext context = new AnnotationConfigWebApplicationContext ();
			
			((AnnotationConfigWebApplicationContext) context).register (AppConfig.class );
			servletContext .addListener (new ContextLoaderListener (context ));
			
			ServletRegistration.Dynamic facesServlet = servletContext.addServlet("Faces Servlet", new FacesServlet());
	        facesServlet.setLoadOnStartup(1);
	        facesServlet.addMapping("*.jsf");
				
	}
	
}