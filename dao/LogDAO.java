package com.anudip.app.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.anudip.app.entities.Log;

public class LogDAO{

	Configuration config;
	SessionFactory factory;
	Session session;
	
	public LogDAO() {
		
		config = new Configuration();
		//Filling up the configuration object from the 
		//Hibernate configuraton file 'hibernate.cfg.xml'
        config.configure("hibernate.cfg.xml");
        factory = config.buildSessionFactory();  
	}
	
	public List getAllLog()
	{
		session=factory.openSession();
		Query query=session.createQuery("from Log");
		return query.list();
	}
	
	
	
	
	public void removeLog(int id)
	{
		session=factory.openSession();
		Query query=session.createQuery("delete from Log where LogID=:i");
		query.setParameter("i", id);
		Transaction t=session.beginTransaction();
		query.executeUpdate();
		t.commit();
		System.out.println("Log deleted permanently...");
		session.close();
		
		}
	
	
        
		
	
	public void registerLog(Log log)
	{
		session = factory.openSession();
		Transaction t = session.beginTransaction(); 
		session.save(log);
		t.commit();
		System.out.println("----->");
		session.close();
		
		
		
	}
	
	
}
