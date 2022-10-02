package br.com.saude.services;

import java.math.BigDecimal;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.saude.dao.GoalsDao;
import br.com.saude.models.Goals;

@RequestScoped
public class GoalService {

    @Inject
    GoalsDao goalsDao;

    public Goals getUserGoals(int userId) {
        return goalsDao.getUserGoals(userId);
    }

    public void patchHydrationGoals(int userId, int hydration) {
        goalsDao.patchHydrationGoals(userId, hydration);
    }

    public void patchWeightLossGoals(int userId, BigDecimal weightloss) {
        goalsDao.patchWeightLossGoals(userId, weightloss);
    }
}
