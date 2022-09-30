package br.com.saude.dao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.saude.models.User;

@RequestScoped
public class UserDao {
    @Inject
    @PersistenceContext
    EntityManager em;

    @Transactional
    public void getUserProfile(int userId) {
        em.find(User.class, userId);
    }

    @Transactional
    public void insertUserProfile(User user) {
        em.persist(user);
    }
}
