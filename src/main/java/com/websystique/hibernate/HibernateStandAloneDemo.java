package com.websystique.hibernate;

import java.util.List;

import org.hibernate.Session;

import com.websystique.hibernate.model.Student;

/**
 * Class used to perform CRUD operation on database with Hibernate API's
 * 
 */
public class HibernateStandAloneDemo {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		HibernateStandAloneDemo application = new HibernateStandAloneDemo();

		/*
		 * Save few objects with hibernate
		 */
		int studentId1 = application.saveStudent("Sam", "Disilva", "Maths");
		int studentId2 = application.saveStudent("Joshua", "Brill", "Science");
		int studentId3 = application.saveStudent("Peter", "Pan", "Physics");
		int studentId4 = application.saveStudent("Bill", "Laurent", "Maths");
		
	    List<Student> students = application.getAllStudents();
		System.out.println("List of all persisted students >>>");
		for (Student student : students) {
			System.out.println("Persisted Student :" + student);
		}
		
		List<Student> remaingStudents = application.getAllStudents();
		System.out.println("List of all remained persisted students >>>");
		for (Student student : remaingStudents) {
			System.out.println("Persisted Student :" + student);
		}
		application.updateStudent(studentId4, "ARTS");
	
		//Deletes an object
		application.deleteStudent(55);

		
		/* * Retrieve all saved objects
		 
		List<Student> remaingStudents1 = application.getAllStudents();
		System.out.println("List of all remained persisted students >>>");
		for (Student student : remaingStudents1) {
			System.out.println("Persisted Student :" + student);
		}
*/
	}

	/**
	 * This method saves a Student object in database
	 */
	public int saveStudent(String firstName, String lastName, String section) {
		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setSection(section);

		// getting a new Connection
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		// session.save
		int id = (Integer) session.save(student);
		session.getTransaction().commit();
		session.close();
		return id;
	}

	/**
	 * This method returns list of all persisted Student 
	 * objects from
	 * database
	 */
	public List<Student> getAllStudents() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		//select * from student ORDER BY first_name ASC;		
		//Hibernate Query Language(HQL)
		List<Student> students = session.createQuery("FROM Student s ORDER BY s.firstName ASC").list();
		
		session.getTransaction().commit();
		session.close();
		return students;
	}

	/**
	 * This method updates a specific Student object
	 */
	public void updateStudent(int id, String section) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Student student = (Student) session.get(Student.class, id); //fetching the students with id 
		student.setSection(section); // update query is fired to update the data
		
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * This method deletes a specific Student object
	 */
	public void deleteStudent(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Student student = (Student) session.get(Student.class, id);
		session.delete(student);
		
		
		session.getTransaction().commit();
		session.close();
	}
}
