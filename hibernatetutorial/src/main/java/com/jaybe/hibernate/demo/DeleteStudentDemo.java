package com.jaybe.hibernate.demo;

import com.jaybe.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {

    private static SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Student.class)
            .buildSessionFactory();

    public static void main(String[] args) {
        deleteByGettingObjectAndCommit(7);
        deleteOnFlyByUsingHQLsyntax(8);
    }

    private static void deleteByGettingObjectAndCommit(int studentId) {

        try {
            // create session
            Session session = factory.getCurrentSession();

            // begin transaction
            session.beginTransaction();

            // get student object from db
            Student student = session.get(Student.class, studentId);

            // delete student
            session.delete(student);

            // commit the transaction
            session.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void deleteOnFlyByUsingHQLsyntax(int studentId) {

        try {
            // create session
            Session session = factory.getCurrentSession();

            // begin transaction
            session.beginTransaction();

            // deleting student on fly by HQL syntax
            session.createQuery("delete from Student s where s.id='"+studentId+"'").executeUpdate();

            // commit transaction
            session.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
