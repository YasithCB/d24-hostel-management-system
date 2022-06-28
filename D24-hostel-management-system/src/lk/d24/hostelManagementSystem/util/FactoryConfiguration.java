package lk.d24.hostelManagementSystem.util;

import lk.d24.hostelManagementSystem.entity.Reserve;
import lk.d24.hostelManagementSystem.entity.Room;
import lk.d24.hostelManagementSystem.entity.Student;
import lk.d24.hostelManagementSystem.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

/**
 * author  Yasith C Bandara
 * created 6/18/2022 - 8:55 PM
 * project D24-hostel-management-system
 */

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private final SessionFactory sessionFactory;

    private FactoryConfiguration(){
        Properties properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        sessionFactory = new Configuration().mergeProperties(properties)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Room.class)
                .addAnnotatedClass(Reserve.class)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();

    }

    public static FactoryConfiguration getInstance(){
        return factoryConfiguration==null? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
