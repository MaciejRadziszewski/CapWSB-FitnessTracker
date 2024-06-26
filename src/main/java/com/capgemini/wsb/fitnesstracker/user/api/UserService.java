package com.capgemini.wsb.fitnesstracker.user.api;

import java.util.List;
import java.util.Optional;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    User createUser(User user);

    Optional<List<UserBasic>> getBasicUsers();

    User updateUser (User user);

    void removeUser (User user);

    Optional<List<User>> getUsersOlderThan(int age);
}
