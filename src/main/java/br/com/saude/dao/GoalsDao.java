package br.com.saude.dao;

import java.math.BigDecimal;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.saude.models.Goals;

@RequestScoped
public class GoalsDao {
    
    @Inject
    @PersistenceContext
    EntityManager em;

    @Transactional
    public Goals getUserGoals(int userId) {
        return em.find(Goals.class, userId);
    }

    @Transactional
    public void patchHydrationGoals(int userId, int hydration) {
        Goals goal = em.find(Goals.class, userId);
        goal.setHydration(hydration);
        em.persist(goal);
    }

    @Transactional
    public void patchWeightLossGoals(int userId, BigDecimal weightloss) {
        Goals goal = em.find(Goals.class, userId);
        goal.setWeightLoss(weightloss);
        em.persist(goal);
    }
}
