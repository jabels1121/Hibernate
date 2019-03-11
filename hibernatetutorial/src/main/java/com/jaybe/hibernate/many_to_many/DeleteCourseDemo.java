package com.jaybe.hibernate.many_to_many;

import com.jaybe.hibernate.many_to_many.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseDemo {

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

            // get the pacman course
            int courseId = 10;
            Course course = session.get(Course.class, courseId);

            // delete the course
            System.out.println("\nDeleting course: " + course);
            session.delete(course);

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
