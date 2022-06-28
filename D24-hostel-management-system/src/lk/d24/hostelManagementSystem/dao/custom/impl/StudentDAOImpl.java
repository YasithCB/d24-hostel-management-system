package lk.d24.hostelManagementSystem.dao.custom.impl;

import lk.d24.hostelManagementSystem.dao.custom.StudentDAO;
import lk.d24.hostelManagementSystem.entity.Room;
import lk.d24.hostelManagementSystem.entity.Student;
import lk.d24.hostelManagementSystem.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * author  Yasith C Bandara
 * created 6/19/2022 - 12:02 PM
 * project D24-hostel-management-system
 */

public class StudentDAOImpl implements StudentDAO {
    @Override
    public ArrayList<Student> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Student> list = session.createQuery("FROM Student").list();

        transaction.commit();
        session.close();
        return (ArrayList<Student>) list;
    }

    @Override
    public boolean save(Student entity) {
        try {
            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();

            session.save(entity);

            transaction.commit();
            session.close();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        boolean b = session.createQuery("DELETE FROM Student WHERE studentId = :id").setParameter("id", id).executeUpdate() > 0;

        transaction.commit();
        session.close();
        return b;
    }

    @Override
    public boolean update(Student entity) {
        try {
            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();

            session.update(entity);

            transaction.commit();
            session.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Student getSpecificEntity(String id) {
        try {
            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();

            Student student = (Student) session.createQuery("FROM Student WHERE studentId = :id").setParameter("id",id).list().get(0);

            transaction.commit();
            session.close();
            return student;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
