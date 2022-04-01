package nl.hsleiden.IPRWC_Webshop_Backend.controller;

import nl.hsleiden.IPRWC_Webshop_Backend.model.UserRequest;
import nl.hsleiden.IPRWC_Webshop_Backend.model.JwtResponse;
import nl.hsleiden.IPRWC_Webshop_Backend.service.AuthService;
import nl.hsleiden.IPRWC_Webshop_Backend.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthService authService;

    @PostMapping({"/login"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public JwtResponse login(@RequestBody UserRequest userRequest) throws Exception {
        authService.authenticate(userRequest.getEmail(), userRequest.getPassword());
        return jwtService.createJwtToken(userRequest);
    }
}
