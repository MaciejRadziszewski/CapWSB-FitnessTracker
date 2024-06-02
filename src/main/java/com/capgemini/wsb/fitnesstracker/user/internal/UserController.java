package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }

    @PostMapping
    public User addUser(@RequestBody UserDto userDto) {

        // Demonstracja how to use @RequestBody
        System.out.println("User with e-mail: " + userDto.email() + "passed to the request");

        // TODO: saveUser with Service and return User
        final var user = userMapper.toEntity(userDto);

        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(Long id) {
        var user = userService.getUser(id).orElse(null);
        return ResponseEntity.ofNullable(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        final var user = userService.getUser(id).orElse(null);
        assert user != null;
        user.setFirstName(userDto.firstName());
        user.setLastName(userDto.lastName());
        user.setLastName(userDto.email());
        user.setBirthdate(userDto.birthdate());

        return userService.updateUser(user);
    }

    @DeleteMapping
    public void removeUser(@PathVariable Long id) {
        final var user = userService.getUser(id).orElse(null);
        userService.removeUser(user);
    }

}