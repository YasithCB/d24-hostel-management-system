package lk.d24.hostelManagementSystem.dao;

import lk.d24.hostelManagementSystem.data.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * author  Yasith C Bandara
 * created 6/19/2022 - 11:44 AM
 * project D24-hostel-management-system
 */

public class SQLUtil {
    private static PreparedStatement getPreparedStatement(String sql, Object... args) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        // set objects to statement
        for (int i = 0; i < args.length; i++) {
            pstm.setObject(i+1,args[i]);
        }
        return pstm;
    }

    public static boolean executeUpdate(String sql,Object...args) throws SQLException, ClassNotFoundException {
        return getPreparedStatement(sql,args).executeUpdate() > 0;
    }

    public static ResultSet executeQuery(String sql, Object...args) throws SQLException, ClassNotFoundException {
        return getPreparedStatement(sql,args).executeQuery();
    }
}
