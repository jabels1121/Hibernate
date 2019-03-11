package com.jaybe.hibernate.one_to_many;

import com.jaybe.hibernate.one_to_many.entity.Course;
import com.jaybe.hibernate.one_to_many.entity.InstructorDetail;
import com.jaybe.hibernate.one_to_many.entity.Instructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {

    // create session factory once
    private static SessionFactory factory = new Configuration()
            .configure("hibernate_one-to-many.cfg.xml")
            .addAnnotatedClass(Instructor.class)
            .addAnnotatedClass(InstructorDetail.class)
            .addAnnotatedClass(Course.class)
            .buildSessionFactory();

    public static void main(String[] args) {
        // create the objects
        Instructor instructor =
                new Instructor("Billy", "Milligan", "bMilligan@mail.com");

        InstructorDetail instructorDetail =
                new InstructorDetail("http://www.youtube.com/channel/billyMil", "Killing people");

        // associate the objects
        instructor.setInstructorDetail(instructorDetail);

        // create session
        Session session = factory.getCurrentSession();
        try {
            // begin transaction
            session.beginTransaction();

            // save objects
            session.save(instructor);

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
