package lk.d24.hostelManagementSystem.bo.custom.impl;

import lk.d24.hostelManagementSystem.bo.custom.UserBO;
import lk.d24.hostelManagementSystem.dao.DAOFactory;
import lk.d24.hostelManagementSystem.dao.custom.UserDAO;
import lk.d24.hostelManagementSystem.dto.UserDTO;
import lk.d24.hostelManagementSystem.entity.Student;
import lk.d24.hostelManagementSystem.entity.User;

import java.util.ArrayList;

/**
 * author  Yasith C Bandara
 * created 6/19/2022 - 11:59 AM
 * project D24-hostel-management-system
 */

public class UserBOImpl implements UserBO {

    private final UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean saveUser(UserDTO userDTO) {
        return userDAO.save(new User(userDTO.getUserid(),userDTO.getUserName(), userDTO.getPassword(), userDTO.getDateCreated()));
    }

    @Override
    public UserDTO checkUserExists(String name) {
        ArrayList<User> all = userDAO.getAll();
        for (User user : all) {
            if (user.getUserName().equals(name))
                return new UserDTO(user.getUserid(), user.getUserName(), user.getPassword(), user.getDateCreated());
        }
        return null;
    }

    @Override
    public String generateNextUserId() {
        ArrayList<User> all = userDAO.getAll();
        if (all.size() > 0) {
            int newId = Integer.parseInt(all.get(all.size()-1).getUserid().replace("U","")) +1;
            return String.format("U%03d",newId);
        }else
            return "U001";
    }
}
