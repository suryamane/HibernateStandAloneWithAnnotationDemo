package com.websystique.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	
	private static final SessionFactory sessionFactory;
	
	static{
		try{
			//creating an object of configuration
			// hibernate.cfg.xml -src/main/resources
			Configuration configuration = new Configuration();
		    
			// configuring
			configuration.configure();
			
			// creating a service registry
		    ServiceRegistry serviceRegistry = 
					new StandardServiceRegistryBuilder().
					applySettings(configuration.getProperties()).
					build();
			
		    //initializing the sessionFactory
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			

		}catch (Throwable ex) {
			System.err.println("Session Factory could not be created." + ex);
			throw new ExceptionInInitializerError(ex);
		}	
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
}
