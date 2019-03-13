package com.shailesh.user.apis.UserManagementApi.dao;

import com.shailesh.user.apis.UserManagementApi.model.Users;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Repository class to handle database operations
 *
 * @author Shailesh
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Users getUserById(Integer id) {
        return entityManager.find(Users.class, id);
    }

    @Override
    public void addUser(Users user) {
        entityManager.persist(user);
    }

    @Override
    public boolean checkUserByUserName(String userName) {
        String hql = "FROM Users as u WHERE u.userName = ?";
        int count = entityManager.createQuery(hql).setParameter(1,userName).getResultList().size();
        return count > 0 ? true : false;
    }

    @Override
    public boolean checkUserByEmailId(String emailId) {
        String hql = "FROM Users as u WHERE u.emailId = ?";
        int count = entityManager.createQuery(hql).setParameter(1,emailId).getResultList().size();
        return count > 0 ? true : false;
    }

    @Override
    public boolean checkUserByMobileNumber(String mobileNumber) {
        String hql = "FROM Users as u WHERE u.mobileNumber = ?";
        int count = entityManager.createQuery(hql).setParameter(1,mobileNumber).getResultList().size();
        return count > 0 ? true : false;
    }

    @Override
    public List<Users> getAllUser() {
        String hql = "FROM Users as u ORDER BY u.userId";
        return (List<Users>) entityManager.createQuery(hql).getResultList();
    }


    @Override
    public void updateUser(Users user) {
        Users usr = getUserById(user.getUserId());
        usr.setEmailId(user.getEmailId());
        usr.setMobileNumber(user.getMobileNumber());
        usr.setActive(user.isActive());
        entityManager.flush();
    }

    @Override
    public void deleteUser(Integer userd) {
        entityManager.remove(getUserById(userd));
    }
}
