package com.example.selfcheckout2;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/users")
public class UserController {

    @Resource(name = "userService")
    private UserService userService;

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/user")
    public UserData saveUser(final @RequestBody UserData userData) {
        return userService.saveUser(userData);
    }

    @GetMapping("/userById")
    public UserData getUserById(final @Param("id_user") Long id_user) {
        return userService.getUserById(id_user);
    }


}
