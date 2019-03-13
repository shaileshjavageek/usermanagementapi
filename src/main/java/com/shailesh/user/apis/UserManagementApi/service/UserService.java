package com.shailesh.user.apis.UserManagementApi.service;

import com.shailesh.user.apis.UserManagementApi.model.Users;

import java.util.List;

/**
 * All users operation
 *
 * @author Shailesh
 */
public interface UserService {

    public Users getUserById(Integer id);

    public boolean addUser(Users user);

    public List<Users> getAllUser();

    public void updateUser(Users users);

    public void deleteUser(Integer id);

}
