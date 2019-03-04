package com.jaybe.hibernate.demo;

import com.jaybe.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class CreateStudentDemo {

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
            Student student1 = new Student("John", "Smith", "jSmith@mail.com");
            Student student2= new Student("Paul", "Bright", "pBright@mail.com");
            Student student3 = new Student("Jane", "Layeers", "jLayeers@mail.com");
            Student student4 = new Student("Lilly", "Dolly", "lDolly@mail.com");
            Student student5 = new Student("Bla", "Bombom", "bBomBom@mail.com");
            Student student6 = new Student("Tommy", "Cash", "tCash@mail.com");
            Student student7 = new Student("Tony", "Violette", "tVoilette@mail.com");
            Student student8 = new Student("Mike", "Morchano", "mMorchano@mail.com");
            List<Student> students = new ArrayList<>();
            students.add(student1);
            students.add(student2);
            students.add(student3);
            students.add(student4);
            students.add(student5);
            students.add(student6);
            students.add(student7);
            students.add(student8);

            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving the student....");
            for (Student s : students) {
                session.save(s);
            }

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}
