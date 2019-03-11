package com.jaybe.hibernate.one_to_many_uni;

import com.jaybe.hibernate.one_to_many_uni.entity.Course;
import com.jaybe.hibernate.one_to_many_uni.entity.Instructor;
import com.jaybe.hibernate.one_to_many_uni.entity.InstructorDetail;
import com.jaybe.hibernate.one_to_many_uni.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class GetCourseAndReviewsDemo {

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

            // get the course
            int id = 10;
            Course course = session.get(Course.class, id);

            // print the course
            System.out.println("Course: " + course);

            // print the course reviews
            List<Review> reviews = course.getReviews();
            for (Review r : reviews) {
                System.out.println(r);
            }

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
