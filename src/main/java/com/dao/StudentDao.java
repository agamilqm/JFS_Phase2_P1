package com.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.entity.Classes;
import com.entity.Student;
import com.resource.DbResource;

public class StudentDao {
	
	public int addStudent(String name ,String lname) {
		SessionFactory sf  = DbResource.buildSessionFactory();
		Session session = sf.openSession();
		
		
		Transaction tx = session.getTransaction();
		
		Student student = new Student();
		
		student.setName(name);
		student.setFname(lname);
		
		tx.begin();
			session.save(student);
		tx.commit();
		
		session.close();
		
		return -1;
	}
	
	public int assignStudent (String[] nameList , String classes) {
		
		SessionFactory sf  = DbResource.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction tx = session.getTransaction();
		
		
		String hql_classes= "from Classes where name='" + classes + "'";
		List<Classes> classesFromQuery = session.createQuery(hql_classes).list();
		
		String hql_student = "update Student s set s.classes=:c where s.name=:sn and s.fname=:fn";
		
		TypedQuery<Student> query = session.createQuery(hql_student);
		query.setParameter("c", classesFromQuery.get(0));
		query.setParameter("sn", nameList[0]);
		query.setParameter("fn", nameList[1]);
		
		tx.begin();
			query.executeUpdate();
		tx.commit();
		
		session.close();
		
		return -1;
	}
	

}
