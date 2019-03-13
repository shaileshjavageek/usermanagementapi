package com.shailesh.user.apis.UserManagementApi.dao;

import com.shailesh.user.apis.UserManagementApi.model.Users;

import java.util.List;

public interface UserDAO {

    public Users getUserById(Integer id);

    public void addUser(Users user);

    public boolean checkUserByUserName(String userName);

    public boolean checkUserByEmailId(String emailId);

    public boolean checkUserByMobileNumber(String mobileNumber);

    public List<Users> getAllUser();

    public void updateUser(Users users);

    public void deleteUser(Integer id);

}
