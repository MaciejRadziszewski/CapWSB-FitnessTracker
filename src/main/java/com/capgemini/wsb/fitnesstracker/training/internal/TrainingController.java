package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.internal.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingServiceImpl trainingService;

    private final TrainingMapper trainingMapper;

    private final UserProvider userProvider;

    private final UserMapper userMapper;

    @PostMapping
    public Training addTraining(@RequestBody TrainingDto trainingDto) {
        final var training = trainingMapper.toEntity(trainingDto);
        return trainingService.createTraining(training);
    }

    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.getAllTrainings().stream().map(trainingMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public Optional<List<Training>> getAllUsersTrainings(Long id) {
        final var user = userProvider.getUser(id).orElse(null);
        assert user != null;
        return trainingService.getAllUsersTrainings(user);
    }

    @PutMapping("/{id}")
    public Training updateTraining(@PathVariable Long id, @RequestBody TrainingDto trainingDto)
    {
        final var training = trainingService.getTraining(id).orElse(null);
        assert training != null;
        training.setUser(userMapper.toEntity(trainingDto.user()));
        training.setStartTime(trainingDto.startTime());
        training.setEndTime(trainingDto.endTime());
        training.setActivityType(trainingDto.activityType());
        training.setDistance(trainingDto.distance());
        training.setAverageSpeed(trainingDto.averageSpeed());
        return trainingService.updateTraining(training);
    }
}
