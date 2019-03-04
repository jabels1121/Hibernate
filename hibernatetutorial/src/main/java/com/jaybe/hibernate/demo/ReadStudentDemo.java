package com.jaybe.hibernate.demo;

import com.jaybe.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {

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
            System.out.println("Creating new student Object....");
            // create a student object
            Student duffy = new Student("Duffy", "Duck", "jSmith@mail.com");

            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving the student....");
            session.save(duffy);

            // commit transaction
            session.getTransaction().commit();

            // MY NEW CODE

            // find out the student`s id: primary key
            System.out.println("Saved student. Generated id: " + duffy.getId());
            System.out.println("Done!");

            // create new hibernate session for query student
            session = factory.getCurrentSession();

            // start transaction
            System.out.println("\n\nStart new transaction for getting student");
            session.beginTransaction();

            // get the student
            System.out.println("Querying the student");
            Student student = session.get(Student.class, duffy.getId());

            // commit transaction
            System.out.println("Commit the transaction");
            session.getTransaction().commit();

            System.out.println("Retrieved student: " + student);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}
