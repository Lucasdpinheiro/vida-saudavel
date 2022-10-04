package br.com.saude.services;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.saude.dao.RecordDao;
import br.com.saude.models.User;
import br.com.saude.models.WeightLossRecord;
import br.com.saude.models.enums.Sex;

@RequestScoped
public class StatisticService {
    
    @Inject
    UserService userService;

    @Inject
    RecordService recordService;

    @Inject
    RecordDao recordDao;

    public double getTDEE(int userId) {
        double activityFactor =  userService
            .getUserProfile(userId)
            .getActivityLevel()
            .getValue();
        double BMR = this.getBMR(userId);

        return BMR * activityFactor;
    }

    public double getBMR(int userId) {
        User user = userService.getUserProfile(userId);
        double weight = user.getWeight(); 
        double height = user.getHeight();
        double age = this.getAge(user);
        Sex sex = user.getSex();

        return weight + 6.25 * height - 5 * age + (sex == Sex.MALE? 5 : -161);
    }

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

    public HashMap<String, Double> getWaterIntakeStats(int userId, String startDate, String endDate) {

        List<Integer> waterIntakehistory = recordDao.getWaterIntakeFromPeriod(userId, parseDate(startDate), parseDate(endDate));
        double dayCount = waterIntakehistory.size();
        double sumIntake = waterIntakehistory.stream().reduce(0, (last, next) -> last + next);

        HashMap<String, Double> stats = new HashMap<String, Double>();

        stats.put("maxIntake", (double) waterIntakehistory.stream().reduce(0, (last, next) -> last > next? last: next));
        stats.put("avg", sumIntake / dayCount);
        return stats;
        
    }

    private int getAge(User user) {
        return Period.between(user.getBirthDay(), LocalDate.now()).getYears();
    }

    private LocalDate parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("uuuu-MM-dd"));
    }
}
