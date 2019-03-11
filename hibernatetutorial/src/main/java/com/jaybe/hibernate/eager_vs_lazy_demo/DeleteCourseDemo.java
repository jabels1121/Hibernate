package com.jaybe.hibernate.eager_vs_lazy_demo;

import com.jaybe.hibernate.one_to_many.entity.Course;
import com.jaybe.hibernate.one_to_many.entity.Instructor;
import com.jaybe.hibernate.one_to_many.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseDemo {

    // create session factory once
    private static SessionFactory factory = new Configuration()
            .configure("hibernate_one-to-many.cfg.xml")
            .addAnnotatedClass(Instructor.class)
            .addAnnotatedClass(InstructorDetail.class)
            .addAnnotatedClass(Course.class)
            .buildSessionFactory();

    public static void main(String[] args) {

        // create session
        Session session = factory.getCurrentSession();
        try {
            // begin transaction
            session.beginTransaction();

            // get course from db
            Course course = session.get(Course.class, 10);

            // delete the course
            System.out.println("Deleting course - " + course);
            session.delete(course);

            // commit transaction
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close resources
            session.close();
            factory.close();
        }
    }
}
