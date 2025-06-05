package com.theiris.testproject.Controller;


import com.theiris.testproject.Dto.LoginDto;
import com.theiris.testproject.Dto.UserDTO;
import com.theiris.testproject.Entity.User;
import com.theiris.testproject.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@Valid  @RequestBody UserDTO dto){
        UserDTO savedUser = userService.addUser(dto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDto dto){
        String token = userService.loginUser(dto);
        return new ResponseEntity<>(token,HttpStatus.OK);
    }

    @GetMapping("/allUsers")
    public ResponseEntity<?> getAllUser(){
        List<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers,HttpStatus.OK);

    }
   @DeleteMapping("/{id}")
    public String DeleteUser(@PathVariable String id){
       String s = userService.deleteUser(id);
       return s;
   }
   @PutMapping("/{id}")
    public ResponseEntity<User> UpdateUser(@PathVariable String id,@RequestBody User user){
       User user1 = userService.UpdateUserById(id, user);
       return  new ResponseEntity<>(user1,HttpStatus.OK);
   }


}
