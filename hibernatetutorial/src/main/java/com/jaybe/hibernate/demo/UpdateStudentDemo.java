package com.jaybe.hibernate.demo;

import com.jaybe.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {

    // create Session factory
    private static SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Student.class)
            .buildSessionFactory();

    private static int studentId = 3;

    public static void main(String[] args) {

        try {
            // create a session
            Session session = factory.getCurrentSession();

            // start transaction
            System.out.println("Start transaction...");
            session.beginTransaction();

            // retrieve student that needed to update some field
            System.out.println("Retrieving student that's id is 3...");
            Student student = session.get(Student.class, studentId);

            // update the student field by using setters methods
            System.out.println("Update student email....");
            student.setEmail("CHANGED@luv2code.com");

            // commit the transaction that saved our updates
            System.out.println("Commit the transaction...");
            session.getTransaction().commit();
            System.out.println("Done!");

            // Assert that email has updated
            System.out.println("\nStart new session for checked update");
            session = factory.getCurrentSession();

            // start transaction
            System.out.println("Start new transaction");
            session.beginTransaction();

            // get student with id = 3;
            System.out.println("Get student with id equal 3...");
            Student student1 = session.get(Student.class, studentId);

            // verify the students email was updated
            boolean result = student1.getEmail().equals("CHANGED@luv2code.com");
            System.out.println("Update result is: " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
