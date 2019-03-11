package com.jaybe.hibernate.one_to_many_uni;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.jaybe.hibernate.one_to_many_uni.entity.*;

public class CreateCourseAndReviewsDemo {

    private static SessionFactory factory = new Configuration()
                    .configure("hibernate_one-to-many-uni.cfg.xml")
                    .addAnnotatedClass(Instructor.class)
                    .addAnnotatedClass(Course.class)
                    .addAnnotatedClass(InstructorDetail.class)
                    .addAnnotatedClass(Review.class)
                    .buildSessionFactory();

    public static void main(String[] args) {
        // create session
        Session session = factory.getCurrentSession();
        try {
            // begin transaction
            session.beginTransaction();

            // create a course
            Course course = new Course("Pacman - How to Score one million points");

            // add some reviews
            course.add(new Review("Great course... loved it!"));
            course.add(new Review("Cool course, job well done"));
            course.add(new Review("What a dumb course, you are an idiot!"));

            // save the course ... and leverage the cascade all
            session.save(course);

            // commit transaction
            session.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            // close session
            session.close();
            // close factory
            factory.close();
        }
    }
}
