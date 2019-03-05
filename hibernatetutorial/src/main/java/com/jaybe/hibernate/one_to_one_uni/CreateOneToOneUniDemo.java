package com.jaybe.hibernate.one_to_one_uni;

import com.jaybe.hibernate.one_to_one_uni.entity.Instructor;
import com.jaybe.hibernate.one_to_one_uni.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateOneToOneUniDemo {

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

            System.out.println("Create objects in memory...");
            // create the objects (Instructor and InstructorDetail
            Instructor john = new Instructor("John", "Doe", "jDoe@mail.com");
            InstructorDetail johnDetail =
                    new InstructorDetail("https://www.youtube.com/channel/jonnyDoe", "anime");

            // associate the objects(inject InstructorDetail to Instructor)
            john.setInstructorDetail(johnDetail);

            // begin transaction
            // ...
            System.out.println("Begin transaction...");
            session.beginTransaction();

            // save Instructor object to the database
            //
            // Note: this will ALSO save the instructorDetails object
            // because of CascadeType.ALL
            System.out.println("Save mapped objects into db...");
            session.save(john);

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
