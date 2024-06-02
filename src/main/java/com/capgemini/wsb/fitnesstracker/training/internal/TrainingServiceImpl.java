package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

// TODO: Provide Impl
@Service
@RequiredArgsConstructor
@Slf4j
class TrainingServiceImpl implements TrainingProvider, TrainingService {

    private final TrainingRepository trainingRepository;

    @Override
    public Optional<Training> getTraining(final Long trainingId) {
        return trainingRepository.findById(trainingId);
    }

    @Override
    public Training createTraining(final Training training) {
        return trainingRepository.save(training);
    }

    @Override
    public List<Training> getAllTrainings() {return trainingRepository.findAll();}

    @Override
    public Optional<List<Training>> getAllUsersTrainings(User user) {return trainingRepository.getAllUsersTrainings(user);}

    @Override
    public Optional<List<Training>> getAllTrainingsFinishedAfter(Date date) {return trainingRepository.getAllTrainingsFinishedAfter(date);}

    @Override
    public Optional<List<Training>> getAllTrainingsByActivity(ActivityType activityType) {return  trainingRepository.getAllTrainingsByActivity(activityType);}

    @Override
    public Training updateTraining(Training training) {return trainingRepository.save(training);}
}
