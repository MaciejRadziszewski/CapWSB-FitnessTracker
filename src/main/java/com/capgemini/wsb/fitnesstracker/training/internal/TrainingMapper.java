package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.internal.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class TrainingMapper {

    UserMapper userMapper = new UserMapper();

    TrainingDto toDto(Training training) {
        return new TrainingDto(training.getId(), userMapper.toDto(training.getUser()), training.getStartTime(), training.getEndTime(), training.getActivityType(), training.getDistance(), training.getAverageSpeed());
    }

    Training toEntity (TrainingDto trainingDto) {
        return new Training(userMapper.toEntity(trainingDto.user()), trainingDto.startTime(), trainingDto.endTime(), trainingDto.activityType(), trainingDto.distance(), trainingDto.averageSpeed());
    }
}
