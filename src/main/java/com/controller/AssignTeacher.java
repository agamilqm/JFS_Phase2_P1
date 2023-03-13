package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dao.TeacherDao;
import com.entity.Classes;
import com.entity.Student;
import com.entity.Subject;
import com.entity.Teacher;
import com.resource.DbResource;

/**
 * Servlet implementation class AssignTeacher
 */
@WebServlet("/assignTeacher")
public class AssignTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssignTeacher() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		// Step 1: Get details , user has entered
		String name = request.getParameter("name");
		String[] nameArray = name.split(" ");
		//System.out.println("NameList: " + nameArray[0] + nameArray[1]);
		String subject = request.getParameter("subject");
				
		TeacherDao teach = new TeacherDao();
		teach.assignTeacher(nameArray, subject);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/viewTeachers.jsp");
        dispatcher.forward(request, response); 
	}

}
