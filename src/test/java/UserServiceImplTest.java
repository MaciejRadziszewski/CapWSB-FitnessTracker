
import com.capgemini.wsb.FitnessTracker;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = FitnessTracker.class)
class UserServiceImplTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserProvider userProvider;

    @Test
    void shouldGetUserIfCreated() {
        final User user = new User("Jan", "Kowalski", LocalDate.parse("1969-10-12"), "123@gmail.com");
        var id = userService.createUser(user).getId();

        var userFromId = userProvider.getUser(id);
        assertThat(userFromId.isPresent()).isTrue();
    }

    @Test
    void shouldFindAllUsersIfCreated() {
        final User user1 = new User("Iks", "Iksinski", LocalDate.parse("1911-04-22"), "321@gmail.com");
        final User user2 = new User("Jan", "Kowalski", LocalDate.parse("1969-10-12"), "123@gmail.com");

        userService.createUser(user1);
        userService.createUser(user2);
        var basicUsers = userService.getBasicUsers();

        assertThat(basicUsers.isEmpty()).isFalse();
    }

    @Test
    void shouldFindAllUsersOlderThan () {
        final User user1 = new User("Iks", "Iksinski", LocalDate.parse("1911-04-22"), "321@gmail.com");
        final User user2 = new User("Jan", "Kowalski", LocalDate.parse("1969-10-12"), "123@gmail.com");

        userService.createUser(user1);
        userService.createUser(user2);

        var olderUsers = userService.getUsersOlderThan(100);
        List<User> olderTestUsers = new ArrayList<User>();
        olderTestUsers.add(user1);

        assertThat(olderUsers.stream().findFirst().toString().equals(Optional.of(olderTestUsers).toString())).isTrue();
    }
}