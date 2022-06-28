package lk.d24.hostelManagementSystem.bo;

import lk.d24.hostelManagementSystem.bo.custom.impl.ReserveBOImpl;
import lk.d24.hostelManagementSystem.bo.custom.impl.RoomBOImpl;
import lk.d24.hostelManagementSystem.bo.custom.impl.StudentBOImpl;
import lk.d24.hostelManagementSystem.bo.custom.impl.UserBOImpl;

/**
 * author  Yasith C Bandara
 * created 6/21/2022 - 6:35 PM
 * project D24-hostel-management-system
 */

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getInstance(){
        return boFactory == null? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        STUDENT, ROOM, RESERVE, USER
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case ROOM: return new RoomBOImpl();
            case RESERVE: return new ReserveBOImpl();
            case STUDENT: return new StudentBOImpl();
            case USER: return new UserBOImpl();
            default: return null;
        }
    }
}
