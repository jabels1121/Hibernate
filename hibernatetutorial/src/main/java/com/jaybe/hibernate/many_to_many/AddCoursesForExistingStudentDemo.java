package com.jaybe.hibernate.many_to_many;

import com.jaybe.hibernate.many_to_many.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCoursesForExistingStudentDemo {

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

            // get the student from database
            int id = 1;
            Student student = session.get(Student.class, id);
            System.out.println("\nLoaded student: " + student);
            System.out.println("Course: " + student.getCourses());

            // create more courses
            Course course1 = new Course("Guitar course!!!");
            Course course2 = new Course("Cooking course!!!");
            Course course3 = new Course("Driving course!!!");

            // add student to courses
            course1.addStudent(student);
            course2.addStudent(student);
            course3.addStudent(student);

            // save the courses
            System.out.println("\nSaving the courses ... ");
            session.save(course1);
            session.save(course2);
            session.save(course3);

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
