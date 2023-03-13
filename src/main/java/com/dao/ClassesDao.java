package com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.entity.Classes;
import com.resource.DbResource;

public class ClassesDao {
	
	public int addClasses(String name) {

				SessionFactory sf  = DbResource.buildSessionFactory();
				Session session = sf.openSession();
				
				Transaction tx = session.getTransaction();
					
				Classes classes = new Classes();
				classes.setName(name);
				
				
				tx.begin();
				session.save(classes);
				tx.commit();
				
				session.close();
				
				return -1; //-1 mean success
				
	}

}
