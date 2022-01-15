package fr.sacquet.association.web.controller;

import fr.sacquet.association.web.bean.User;
import fr.sacquet.association.web.conf.JwtTokenUtil;
import fr.sacquet.association.web.model.JwtRequest;
import fr.sacquet.association.web.model.JwtResponse;
import fr.sacquet.association.web.model.UserRequest;
import fr.sacquet.association.web.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static fr.sacquet.association.web.conf.Constante.PRIVATE_API;
import static fr.sacquet.association.web.conf.Constante.PUBLIC_API;

@RestController
@AllArgsConstructor
public class UserController {

    private AuthenticationManager authenticationManager;

    private JwtTokenUtil jwtTokenUtil;

    private UserService userDetailsService;

    @PostMapping(value = PUBLIC_API + "/authenticate")
    public JwtResponse createAuthenticationToken(@Validated @RequestBody JwtRequest authenticationRequest) throws Exception {
        String token = "";
        if (authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword())) {
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            token = jwtTokenUtil.generateToken(userDetails);
        }
        return new JwtResponse(token);
    }

    @PostMapping(value = PRIVATE_API + "/user")
    public User saveUser(@Validated @RequestBody UserRequest user) {
        return userDetailsService.save(user);
    }

    @GetMapping(value = PRIVATE_API + "/users")
    public Iterable<User> getAllUsers() {
        return userDetailsService.getAllUser();
    }

    @DeleteMapping(value = PRIVATE_API + "/user/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userDetailsService.deleteUser(id);
    }

    private boolean authenticate(String username, String password) throws Exception {
        Authentication authenticate;
        try {
            authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        return authenticate != null && authenticate.isAuthenticated();
    }
}
