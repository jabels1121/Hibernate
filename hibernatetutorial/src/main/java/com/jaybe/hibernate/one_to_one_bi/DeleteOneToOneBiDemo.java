package com.jaybe.hibernate.one_to_one_bi;

import com.jaybe.hibernate.one_to_one_bi.entity.Instructor;
import com.jaybe.hibernate.one_to_one_bi.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteOneToOneBiDemo {

    // create session factory
    private static SessionFactory factory = new Configuration()
            .configure("hibernate_one-to-one.cfg.xml")
            .addAnnotatedClass(Instructor.class)
            .addAnnotatedClass(InstructorDetail.class)
            .buildSessionFactory();

    public static void main(String[] args) {
        System.out.println("Start program...\nCreate session...");
        // create session
        Session session = factory.getCurrentSession();
        try {
            // begin transaction
            // ...
            session.beginTransaction();

            int theId = 2;
            // get the instructorDetail object
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, theId);

            // print instructor detail
            System.out.println("InstructorDetail is: " + instructorDetail);

            // print the associated instructor
            System.out.println("The associated instructor: " + instructorDetail.getInstructor());

            // remove the associated object reference
            // break bi-directional link
            instructorDetail.getInstructor().setInstructorDetail(null);

            // delete instructorDetail object
            // Note: will ALSO delete Instructor object that mapped on retrieved instructorDetail
            // because CascadeType.ALL
            System.out.println("Deleting instructorDetail object...");
            session.delete(instructorDetail);

            // ...
            // commit transaction
            System.out.println("Commit transaction");
            session.getTransaction().commit();
            System.out.println("Done!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();

            factory.close();
        }
    }
}
