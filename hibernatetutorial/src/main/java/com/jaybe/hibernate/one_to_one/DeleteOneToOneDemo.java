package com.jaybe.hibernate.one_to_one;

import com.jaybe.hibernate.one_to_one.entity.Instructor;
import com.jaybe.hibernate.one_to_one.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteOneToOneDemo {

    // create session factory
    private static SessionFactory factory = new Configuration()
            .configure("hibernate_one-to-one.cfg.xml")
            .addAnnotatedClass(Instructor.class)
            .addAnnotatedClass(InstructorDetail.class)
            .buildSessionFactory();

    public static void main(String[] args) {

        try {
            System.out.println("Start program...\nCreate session...");
            // create session
            Session session = factory.getCurrentSession();

            // begin transaction
            // ...
            System.out.println("Begin transaction...");
            session.beginTransaction();

            // get the instructor by primary kei /id
            int theId = 1;
            Instructor tmpInstructor =
                    session.get(Instructor.class, theId);
            System.out.println("Found instructor: "+tmpInstructor);

            // delete the instructor
            if (tmpInstructor != null) {
                System.out.println("Deleting instructor: " + tmpInstructor);
                // Note: will ALSO delete associated "details" object
                // because of CascadeType.ALL
                session.delete(tmpInstructor);
            }

            // ...
            // commit transaction
            System.out.println("Commit transaction");
            session.getTransaction().commit();
            System.out.println("Done!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
