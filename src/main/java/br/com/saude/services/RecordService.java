package br.com.saude.services;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.saude.dao.RecordDao;
import br.com.saude.dao.UserDao;
import br.com.saude.models.HydrationRecord;
import br.com.saude.models.WeightLossRecord;

@RequestScoped
public class RecordService {

    @Inject
    UserDao userDao;

    @Inject
    RecordDao recordDao;

    public List<?> getRecord(int userId, Class<?> targetCLass) {
        return recordDao.getRecord(userId, targetCLass);
    }

    public void postRecord(int userId, HydrationRecord record) {
        record.setUser(userDao.getUserProfile(userId));
        recordDao.insertRecord(record);
    }

    public void postRecord(int userId, WeightLossRecord record) {
        record.setUser(userDao.getUserProfile(userId));
        recordDao.insertRecord(record);
    }
}
