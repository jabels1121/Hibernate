package com.jaybe.hibernate.demo;

import com.jaybe.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        // use the session object to save Java object
        try {
            System.out.println("Start a transaction");
            // start a transaction
            session.beginTransaction();

            // query students
            List<Student> students = session.createQuery("from Student").list();

            // display the students
            displayTheStudents(students);

            // query students: lastName contains i symbol
            students = session.createQuery("from Student s where s.lastName LIKE '%i%'").list();
            System.out.println("\n\n");

            // display the students that lastName contains i symbol
            displayTheStudents(students);
            System.out.println("\n\n");

            // query students: lastName='Smith' OR firstName contains l symbol
            students =
                    session.createQuery("from Student s where s.lastName = 'Smith' " +
                            "OR s.firstName LIKE '%l%'").list();

            displayTheStudents(students);
            System.out.println("\n\n");

            // query students: email contains %lL%
            students =
                    session.createQuery("from Student s where s.email LIKE '%ll%'").list();

            displayTheStudents(students);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }

    private static void displayTheStudents(List<Student> students) {
        for (Student s : students) {
            System.out.println(s);
        }
    }
}
