package br.com.saude.services;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.saude.dao.UserDao;
import br.com.saude.models.User;

@RequestScoped
public class UserService {

    @Inject
    UserDao userDao;

    public List<User> getUserProfileList() {
        return userDao.getUserProfileList();
    }

    public User getUserProfile(int userId) {
        return userDao.getUserProfile(userId);
    }

    public void postUserProfile(User user) {
         userDao.insertUserProfile(user);
    }

    public void deleteUserProfile(int userId) {
        User user  = userDao.getUserProfile(userId);
        userDao.deleteUserProfile(user);
        //return user;
    }
    
}
