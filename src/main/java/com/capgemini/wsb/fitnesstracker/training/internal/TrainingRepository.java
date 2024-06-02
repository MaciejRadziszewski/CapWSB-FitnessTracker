package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

interface TrainingRepository extends JpaRepository<Training, Long> {

    default Optional<List<Training>> getAllUsersTrainings (User user) {
        List<Training> allTrainings = findAll().stream().toList();
        List<Training> userTrainings = new ArrayList<Training>();
        for (Training training: allTrainings
             ) {
            if(training.getUser().equals(user)){
                userTrainings.add(training);
            }
        }
        return Optional.of(userTrainings);
    }

    default Optional<List<Training>> getAllTrainingsFinishedAfter (Date date) {
        List<Training> allTrainings = findAll().stream().toList();
        List<Training> finishedTrainings = new ArrayList<Training>();
        for (Training training : allTrainings
        ) {
            if (training.getEndTime().after(date)) {
                finishedTrainings.add(training);
            }
        }
        return Optional.of(finishedTrainings);
    }

    default Optional<List<Training>> getAllTrainingsByActivity(ActivityType activityType) {
        List<Training> allTrainings = findAll().stream().toList();
        List<Training> activityTrainings = new ArrayList<Training>();
        for (Training training : allTrainings
        ) {
            if (training.getActivityType().equals(activityType)) {
                activityTrainings.add(training);
            }
        }
        return Optional.of(activityTrainings);
    };
}
