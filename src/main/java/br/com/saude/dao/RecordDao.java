package br.com.saude.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.saude.models.HydrationRecord;
import br.com.saude.models.Record;
import br.com.saude.models.WeightLossRecord;

@RequestScoped
public class RecordDao {

    @Inject
    @PersistenceContext
    EntityManager em;

    @Transactional
    public List<?> getRecord(int userId, Class<?> targetClass) {

        if (targetClass != HydrationRecord.class && targetClass != WeightLossRecord.class) {
            return new ArrayList<>();
        }

        String tableName = targetClass == HydrationRecord.class ? "hydration": "weightloss";

        String sqlString = String.format(
            "SELECT * FROM %s WHERE user_id = :selected_user", tableName);

        return em
            .createNativeQuery(sqlString, targetClass)
            .setParameter("selected_user", userId)
            .getResultList();
    }

    @Transactional
    public <T extends Record> void insertRecord(T record) {
        em.persist(record);
    }
    
}
