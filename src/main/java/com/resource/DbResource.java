package com.resource;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.*;

import com.entity.Classes;
import com.entity.Student;
import com.entity.Subject;
import com.entity.Teacher;



public class DbResource {

	public static SessionFactory buildSessionFactory() {

		Configuration con = new Configuration();
		con.configure("hibernate.cfg.xml");

		
		SessionFactory sessionFactory = con.addAnnotatedClass(Teacher.class)
				.addAnnotatedClass(Subject.class)
				.addAnnotatedClass(Classes.class)
				.addAnnotatedClass(Student.class).buildSessionFactory();
	
		
				
		return sessionFactory;

		
	}
}