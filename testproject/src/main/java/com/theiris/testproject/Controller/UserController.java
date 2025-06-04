package com.theiris.testproject.Controller;


import com.theiris.testproject.Dto.LoginDto;
import com.theiris.testproject.Dto.UserDTO;
import com.theiris.testproject.Entity.User;
import com.theiris.testproject.Service.UserService;
import org.apache.coyote.Response;
import org.apache.el.parser.Token;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody UserDTO dto){
        UserDTO savedUser = userService.addUser(dto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDto dto){
        String token = userService.loginUser(dto);
        return new ResponseEntity<>(token,HttpStatus.OK);
    }



}
