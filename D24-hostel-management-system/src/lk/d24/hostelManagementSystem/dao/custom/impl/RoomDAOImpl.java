package lk.d24.hostelManagementSystem.dao.custom.impl;

import lk.d24.hostelManagementSystem.dao.custom.RoomDAO;
import lk.d24.hostelManagementSystem.entity.Room;
import lk.d24.hostelManagementSystem.entity.User;
import lk.d24.hostelManagementSystem.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * author  Yasith C Bandara
 * created 6/19/2022 - 12:02 PM
 * project D24-hostel-management-system
 */

public class RoomDAOImpl implements RoomDAO {

    @Override
    public ArrayList<Room> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Room> list = session.createQuery("FROM Room").list();

        transaction.commit();
        session.close();
        return (ArrayList<Room>) list;
    }

    @Override
    public boolean save(Room entity) {
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

            session.delete(id, Room.class);

            transaction.commit();
            session.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Room entity) {
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
    public Room getSpecificEntity(String id) {
        try {
            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();

            Room room = (Room) session.createQuery("FROM Room WHERE roomId = :id").setParameter("id",id).list().get(0);

            transaction.commit();
            session.close();
            return room;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
