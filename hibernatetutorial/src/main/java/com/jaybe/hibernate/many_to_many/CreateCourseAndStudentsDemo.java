package com.jaybe.hibernate.many_to_many;

import com.jaybe.hibernate.many_to_many.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentsDemo {

    private static SessionFactory factory = new Configuration()
            .configure("hibernate_many-to-many.cfg.xml")
            .addAnnotatedClass(Instructor.class)
            .addAnnotatedClass(Course.class)
            .addAnnotatedClass(InstructorDetail.class)
            .addAnnotatedClass(Student.class)
            .addAnnotatedClass(Review.class)
            .buildSessionFactory();

    public static void main(String[] args) {
        // create session
        Session session = factory.getCurrentSession();
        try {
            // begin transaction
            session.beginTransaction();

            // create course object
            Course course = new Course("Pacman course!!!");

            // save the course
            System.out.println("\nSaving the course ... ");
            session.save(course);

            // create students objects
            Student student1 = new Student("John", "Smith", "jSmith@mail.com");
            Student student2= new Student("Paul", "Bright", "pBright@mail.com");
            Student student3 = new Student("Jane", "Layeers", "jLayeers@mail.com");

            // add students to the course
            course.addStudent(student1);
            course.addStudent(student2);
            course.addStudent(student3);

            // ... saving students
            System.out.println("\nSaving students ... ");
            session.save(student1);
            session.save(student2);
            session.save(student3);
            System.out.println("\nSaved students: " + course.getStudents());

            // commit the transaction
            session.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
            factory.close();
        }
    }

}
