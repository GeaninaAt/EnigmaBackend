package com.app.parking.rest;

import com.app.parking.Md5Encryption;
import com.app.parking.model.User;
import com.app.parking.repository.UserRepository;
import com.app.parking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.security.Principal;

/**
 * Created by gatomulesei on 3/28/2017.
 */
@RestController
@RequestMapping("rest/users")
public class UserEndpoint{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void postInit(){
        if(userRepository.findByUsername("user") == null){
            final User defaultUser = new User();
            defaultUser.setUsername("user");
            defaultUser.setPassword("pass");
            defaultUser.setEmail("user@mail");
            defaultUser.setFirstName("default");
            defaultUser.setLastName("default");
            userService.addUser(defaultUser);
        }else{
            System.out.println("Default user already exists. Skipping creation.");
        }
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody User user){
        if(userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest()
                    .body(new ObjectError("user.email",
                            String.format("User with e-mail '%s' already exists!", user.getEmail())));
        }
        if(userRepository.findByUsername(user.getUsername()) != null){
            return ResponseEntity.badRequest()
                    .body(new ObjectError("user.username",
                            String.format("Username '%s' already exists! Registration cancelled.", user.getUsername())));
        }
        if(!user.getPassword().equals(user.getMatchPassword())){
            return ResponseEntity.badRequest()
                    .body(new ObjectError("user.password",
                            String.format("Passwords don't match! Registration cancelled.")));
        }

        user.setPassword(Md5Encryption.encrypt(user.getPassword()));
        final User createdUser = userService.addUser(user);
        URI location = URI.create("/rest/users/" + createdUser.getId());
        return ResponseEntity.created(location).body(createdUser.getId());
    }

    @RequestMapping(method = RequestMethod.GET)
    public Principal user(Principal principal){
        return principal;
    }
}
