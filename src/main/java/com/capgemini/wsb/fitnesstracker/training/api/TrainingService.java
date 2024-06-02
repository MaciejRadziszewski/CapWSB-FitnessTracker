package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TrainingService {
    Training createTraining(Training training);

    List<Training> getAllTrainings();

    Optional<List<Training>> getAllUsersTrainings(User user);

    Optional<List<Training>> getAllTrainingsFinishedAfter(Date date);

    Optional<List<Training>> getAllTrainingsByActivity(ActivityType activityType);

    Training updateTraining(Training training);
}
