package br.com.saude.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public List<Integer> getWaterIntakeFromPeriod(int userId, LocalDate startDate, LocalDate endDate) {
        String sqlString = new StringBuilder()
            .append("SELECT * FROM hydration ")
            .append("WHERE user_id = :userId ")
            .append("AND ")
            .append("date >= :startDate ")
            .append("AND ")
            .append("date <= :endDate")
            .toString();

            // O atributo é do tipo integer sempre.
        @SuppressWarnings("unchecked")
        List<HydrationRecord> hydrationRecord = em
            .createNativeQuery(sqlString, HydrationRecord.class)
            .setParameter("userId", userId)
            .setParameter("startDate", startDate)
            .setParameter("endDate", endDate)
            .getResultList();

        return hydrationRecord
            .stream()
            .map(hydrationEntity -> hydrationEntity.getWaterIntake())
            .collect(Collectors.toList());
    }
    
}
