package br.com.saude.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import javax.persistence.*;
import javax.transaction.Transactional;

import br.com.saude.models.User;

@RequestScoped
public class UserDao {
    @Inject
    @PersistenceContext
    EntityManager em;

    @Transactional
    public List<User> getUserProfileList() {
        List<User> userList = em
            .createNamedQuery("listUserProfiles", User.class)
            .getResultList();
        
        return userList;
    }

    @Transactional
    public User getUserProfile(int userId) {
        return em.find(User.class, userId);
    }

    @Transactional
    public User insertUserProfile(User user) {
        em.persist(user);
        return user;
    }

    @Transactional
    public void deleteUserProfile(User user) {
        User userToDelete = em.merge(user);
        em.remove(userToDelete);
    }
}
