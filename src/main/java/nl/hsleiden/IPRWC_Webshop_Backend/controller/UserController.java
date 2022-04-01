package nl.hsleiden.IPRWC_Webshop_Backend.controller;

import nl.hsleiden.IPRWC_Webshop_Backend.model.User;
import nl.hsleiden.IPRWC_Webshop_Backend.model.UserResponse;
import nl.hsleiden.IPRWC_Webshop_Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/register"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public UserResponse register(@RequestBody User user) {
        user = userService.register(user);
        return new UserResponse(user.getEmail(), List.of("User"));
    }
}
