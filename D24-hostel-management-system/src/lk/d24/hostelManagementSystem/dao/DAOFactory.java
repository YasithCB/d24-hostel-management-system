package lk.d24.hostelManagementSystem.dao;

import lk.d24.hostelManagementSystem.dao.custom.impl.*;

/**
 * author  Yasith C Bandara
 * created 6/21/2022 - 6:58 PM
 * project D24-hostel-management-system
 */

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return daoFactory == null? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        QUERY, STUDENT, ROOM, RESERVE, USER
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case STUDENT: return new StudentDAOImpl();
            case ROOM: return new RoomDAOImpl();
            case RESERVE: return new ReserveDAOImpl();
            case USER: return new UserDAOImpl();
            case QUERY: return new QueryDAOImpl();
            default: return null;
        }
    }
}
