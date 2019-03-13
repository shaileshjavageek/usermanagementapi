package com.shailesh.user.apis.UserManagementApi.service;


import com.shailesh.user.apis.UserManagementApi.dao.UserDAO;
import com.shailesh.user.apis.UserManagementApi.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public Users getUserById(Integer id) {
        return userDAO.getUserById(id);
    }

    @Override
    @Transactional
    public synchronized boolean addUser(Users user){
        if (userDAO.checkUserByEmailId(user.getEmailId())
                || userDAO.checkUserByMobileNumber(user.getMobileNumber())) {
            return false;
        } else {
            userDAO.addUser(user);
            return true;
        }
    }

    @Override
    @Transactional
    public List<Users> getAllUser() {
        return  userDAO.getAllUser();
    }

    @Override
    @Transactional
    public void updateUser(Users users) {
        userDAO.updateUser(users);
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        userDAO.deleteUser(id);
    }
}
