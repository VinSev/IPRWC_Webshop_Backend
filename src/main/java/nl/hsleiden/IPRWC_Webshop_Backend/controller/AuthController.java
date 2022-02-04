package nl.hsleiden.IPRWC_Webshop_Backend.controller;

import nl.hsleiden.IPRWC_Webshop_Backend.model.JwtRequest;
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
    public JwtResponse login(@RequestBody JwtRequest jwtRequest) throws Exception {
        authService.authenticate(jwtRequest.getEmail(), jwtRequest.getPassword());
        return jwtService.createJwtToken(jwtRequest);
    }
}
