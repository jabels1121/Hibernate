package com.jaybe.hibernate.eager_vs_lazy_demo;

import com.jaybe.hibernate.one_to_many.entity.Course;
import com.jaybe.hibernate.one_to_many.entity.Instructor;
import com.jaybe.hibernate.one_to_many.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesDemo {

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
            int theId = 1;
            Instructor instructor = session.get(Instructor.class, theId);

            // create some courses
            Course course1 = new Course("Air Guitar - The Ultimate Guide");
            Course course2 = new Course("The Pinball MasterClass");
            Course course3 = new Course("The Magic Academy");

            // add courses to instructor
            instructor.addCourse(course1);
            instructor.addCourse(course2);
            instructor.addCourse(course3);

            // save the courses
            session.save(course1);
            session.save(course2);
            session.save(course3);

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
