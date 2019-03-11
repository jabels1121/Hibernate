package com.jaybe.hibernate.eager_vs_lazy_demo;


import com.jaybe.hibernate.eager_vs_lazy_demo.entity.Course;
import com.jaybe.hibernate.eager_vs_lazy_demo.entity.Instructor;
import com.jaybe.hibernate.eager_vs_lazy_demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class FetchJoinDemo {

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
            // option 2: Hibernate query with HQL
            Query<Instructor> query =
                    session.createQuery("select i from  Instructor i " +
                                    "JOIN FETCH i.courses " +
                                    "where i.id=:instructorId",
                            Instructor.class);
            query.setParameter("instructorId", id);

            Instructor instructor = query.getSingleResult();

            System.out.println("Instructor : " + instructor);
            // commit transaction
            session.getTransaction().commit();
            session.close();

            List<Course> courses = instructor.getCourses();
            System.out.println(courses);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close resources
            session.close();
            factory.close();
        }
    }
}
