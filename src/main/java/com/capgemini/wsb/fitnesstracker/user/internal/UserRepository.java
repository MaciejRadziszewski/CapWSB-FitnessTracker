package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserBasic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                        .filter(user -> Objects.equals(user.getEmail(), email))
                        .findFirst();
    }

    default Optional <List<UserBasic>> getBasicUsers () {
        List<UserBasic> basicUsers = new ArrayList<UserBasic>();
        List<User> users = findAll();
        for (User newUser: users
             ) {
            UserBasic userBasic = new UserBasic(newUser.getId(), newUser.getFirstName(), newUser.getLastName());
            basicUsers.add(userBasic);
        }
        return Optional.of(basicUsers);
    }

    default Optional<List<User>> getUsersOlderThan (int age) {
        List<User> users = findAll();
        List<User> olderUsers = new ArrayList<User>();
        for (User newUser: users
        ) {
            if(ChronoUnit.YEARS.between(newUser.getBirthdate(), LocalDate.now()) > age)
            {
                olderUsers.add(newUser);
            }
        }
        return Optional.of(olderUsers);

    }

}
