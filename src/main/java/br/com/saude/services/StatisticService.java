package br.com.saude.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.saude.models.User;
import br.com.saude.models.WeightLossRecord;
import br.com.saude.models.enums.Sex;

@RequestScoped
public class StatisticService {
    
    @Inject
    UserService userService;

    @Inject
    RecordService recordService;

    public double getBMI(int userId) {
        User user = userService.getUserProfile(userId);
        int weight = user.getWeight(); 
        int height = user.getHeight();

        return 1e3 * weight / (double) (height * height);                                   
    }

    public int getWeightLost(int userId) {
        List<?> record = recordService.getRecord(userId, WeightLossRecord.class);
        
        WeightLossRecord firstRecord = (WeightLossRecord) record.get(0);
        WeightLossRecord lastRecord = (WeightLossRecord) record.get(record.size() - 1);

        return firstRecord.getCurrentWeight() - lastRecord.getCurrentWeight();
    }

    public double getBMR(int userId) {
        User user = userService.getUserProfile(userId);
        double weight = user.getWeight(); 
        double height = user.getHeight();
        double age = this.getAge(user);
        Sex sex = user.getSex();

        return weight + 6.25 * height - 5 * age + (sex == Sex.MALE? 5 : -161);
    }

    private int getAge(User user) {
        return Period.between(user.getBirthDay(), LocalDate.now()).getYears();
    }
}
