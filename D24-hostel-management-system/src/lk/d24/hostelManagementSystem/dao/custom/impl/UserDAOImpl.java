package lk.d24.hostelManagementSystem.dao.custom.impl;

import lk.d24.hostelManagementSystem.dao.custom.UserDAO;
import lk.d24.hostelManagementSystem.entity.Student;
import lk.d24.hostelManagementSystem.entity.User;
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

public class UserDAOImpl implements UserDAO {

    @Override
    public ArrayList<User> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<User> list = session.createQuery("FROM User").list();

        transaction.commit();
        session.close();
        return (ArrayList<User>) list;
    }

    @Override
    public boolean save(User entity) {
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
        try {
            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();

            session.delete(id, User.class);

            transaction.commit();
            session.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(User entity) {
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
    public User getSpecificEntity(String id) {
        return null;
    }
}
