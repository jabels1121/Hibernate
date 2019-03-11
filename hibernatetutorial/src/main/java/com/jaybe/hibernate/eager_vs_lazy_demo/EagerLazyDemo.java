package com.jaybe.hibernate.eager_vs_lazy_demo;


import com.jaybe.hibernate.eager_vs_lazy_demo.entity.Course;
import com.jaybe.hibernate.eager_vs_lazy_demo.entity.Instructor;
import com.jaybe.hibernate.eager_vs_lazy_demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class EagerLazyDemo {

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

            // get the instructor from db
            int id = 1;
            Instructor instructor = session.get(Instructor.class, id);
            System.out.println("Instructor : " + instructor);

            // get course for the instructor
            List<Course> courses = instructor.getCourses();

            for (Course c : courses) {
                System.out.println(c);
            }

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
