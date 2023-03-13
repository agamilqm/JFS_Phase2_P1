package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.entity.Subject;
import com.entity.Teacher;
import com.resource.DbResource;

public class TeacherDao {
	
	public int addTeacher(String fName,String lname) {

		SessionFactory sf  = DbResource.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction tx = session.getTransaction();
			
		Teacher teacher = new Teacher();
		teacher.setFname(fName);
		teacher.setLname(lname);
		
		tx.begin();
			session.save(teacher);
		tx.commit();
		
		session.close();
		
		return -1;
	}

	public int assignTeacher (String[] nameArray , String subject) {

		SessionFactory sf  = DbResource.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction tx = session.getTransaction();
		
		String hql_teacher= "from Teacher where fname='" + nameArray[0] + "'" + " and lname='" + nameArray[1] + "'";
		List<Teacher> teachers = session.createQuery(hql_teacher).list();
		
		String hql_subject = "update Subject s set s.teacher=:n where s.name=:sn";
		System.out.println(subject);
		Query<Subject> query = session.createQuery(hql_subject);
		query.setParameter("n", teachers.get(0));
		query.setParameter("sn", subject);
				
		tx.begin();
			query.executeUpdate();
		tx.commit();
		
		session.close();
		
		return -1;
		
	}
}
