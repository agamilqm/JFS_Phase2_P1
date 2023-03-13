package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.entity.Classes;
import com.entity.Subject;
import com.resource.DbResource;

public class SubjectDao {
	
	public int addStudent(String name) {
		SessionFactory sf  = DbResource.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction tx = session.getTransaction();
		
			
		
		Subject subject = new Subject();
		subject.setName(name);
		tx.begin();
			session.save(subject);
		tx.commit();
		
		session.close();
		
		return -1;
	}
	
	public int assignSubject(String classe,String subject) {
		
		SessionFactory sf  = DbResource.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction tx = session.getTransaction();
		
		String hql_clas= "from Classes where name='" + classe + "'";
		List<Classes> classes = session.createQuery(hql_clas).list();
		
		String hql_subject = "update Subject s set s.classes=:n where s.name=:sn";
		
		Query<Subject> query = session.createQuery(hql_subject);
		query.setParameter("n", classes.get(0));
		query.setParameter("sn", subject);
				
		tx.begin();
			query.executeUpdate();
		tx.commit();
		
		session.close();
		
		return -1;
	}

}
