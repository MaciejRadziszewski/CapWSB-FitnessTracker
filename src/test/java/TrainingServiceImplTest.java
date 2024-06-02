import com.capgemini.wsb.FitnessTracker;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = FitnessTracker.class)
public class TrainingServiceImplTest {

    @Autowired
    private TrainingService trainingService;

    @Autowired
    private TrainingProvider trainingProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private UserProvider userProvider;

    @Test
    void shouldGetTrainingIfCreated() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse("2024-02-01");
        Date date2 = dateFormat.parse("2024-02-02");
        final User user = new User("Jan", "Kowalski", LocalDate.parse("1969-10-12"), "123@gmail.com");
        Long userId = userService.createUser(user).getId();
        var userFromId = userProvider.getUser(userId);
        Training training = new Training(user, date1, date2, ActivityType.CYCLING, 123.0, 12.5);

        userFromId.ifPresent(training::setUser);
        Long id = trainingService.createTraining(training).getId();

        Optional<Training> fetchedTraining = trainingProvider.getTraining(id);

        assertThat(fetchedTraining.isPresent()).isTrue();

    }

    @Test
    void shouldGetAllTrainings() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse("2024-02-01");
        Date date2 = dateFormat.parse("2024-02-02");
        final User user = new User("Jan", "Kowalski", LocalDate.parse("1969-10-12"), "123@gmail.com");
        Long userId = userService.createUser(user).getId();
        var userFromId = userProvider.getUser(userId);
        Training training = new Training(user, date1, date2, ActivityType.CYCLING, 123.0, 12.5);
        userFromId.ifPresent(training::setUser);
        trainingService.createTraining(training);

        List<Training> allTrainings = trainingService.getAllTrainings();

        assertThat(allTrainings.size() == 1).isTrue();
    }

    @Test
    void shouldFindTrainingForUser () throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse("2024-02-01");
        Date date2 = dateFormat.parse("2024-02-02");
        final User user = new User("Jan", "Kowalski", LocalDate.parse("1969-10-12"), "123@gmail.com");
        Long userId = userService.createUser(user).getId();
        var userFromId = userProvider.getUser(userId);
        Training training = new Training(user, date1, date2, ActivityType.CYCLING, 123.0, 12.5);
        userFromId.ifPresent(training::setUser);
        trainingService.createTraining(training);

        Optional<List<Training>> allUsersTrainings = trainingService.getAllUsersTrainings(training.getUser());
        List<Training> usersTestTrainings = new ArrayList<Training>();
        usersTestTrainings.add(training);

        System.out.println(allUsersTrainings);
        System.out.println(usersTestTrainings);

        assertThat(allUsersTrainings.stream().findFirst().toString().equals(Optional.of(usersTestTrainings).toString())).isTrue();

    }
}
