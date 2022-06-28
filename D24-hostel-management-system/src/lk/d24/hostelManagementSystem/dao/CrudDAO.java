package lk.d24.hostelManagementSystem.dao;

import java.util.ArrayList;

/**
 * author  Yasith C Bandara
 * created 6/19/2022 - 12:03 PM
 * project D24-hostel-management-system
 */

public interface CrudDAO<T> extends SuperDAO{

    ArrayList<T> getAll();
    boolean save(T entity);
    boolean delete(String id);
    boolean update(T entity);
    T getSpecificEntity(String id);
}
