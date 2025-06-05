package com.theiris.testproject.Service;

import com.theiris.testproject.Dto.LoginDto;
import com.theiris.testproject.Dto.UserDTO;
import com.theiris.testproject.Entity.User;
import com.theiris.testproject.Exception.UserAlreadyExist;
import com.theiris.testproject.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private ModelMapper modelMapper;
    private JWTService jwtService;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, JWTService jwtService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }

   public UserDTO maptoDto(User user){
       UserDTO dto = modelMapper.map(user, UserDTO.class);
       return dto;
   }
   public User maptoEntity(UserDTO dto){
       User entity = modelMapper.map(dto, User.class);
       return entity;
   }
    public UserDTO addUser(UserDTO dto){
        Optional<User> byUserName = userRepository.findByUsername(dto.getUsername());
        if (byUserName.isPresent()){
            throw new UserAlreadyExist("this username already exist");
        }
        Optional<User> byEmail = userRepository.findByEmail(dto.getEmail());
        if (byEmail.isPresent()){
            throw new UserAlreadyExist("this email Already Exist");
        }
        Optional<User> byPhone = userRepository.findByPhone(dto.getPhone());
        if (byPhone.isPresent()){
            throw new UserAlreadyExist("this user already exist with this phone number");
        }
        User user = maptoEntity(dto);
        user.setUserRole("ROLE_USER");
        User saveduser = userRepository.save(user);
        UserDTO dto1 = maptoDto(saveduser);
        return dto1;
    }

    public String loginUser(LoginDto dto){
        Optional<User> byUsername = userRepository.findByUsername(dto.getUsername());
        if (byUsername.isPresent()){
            User user = byUsername.get();
            String token = jwtService.generateToken(user.getUsername());
            if (user.getPassword().equals(dto.getPassword())){
                return token;
            }
            else{
                return "the password is incorrect";
            }
        }
        else {
             return "the username and password is incorrect";
        }
    }


    public List<User> getAllUsers() {
        List<User> all = userRepository.findAll();
        return all;
    }


    public String deleteUser(String id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            userRepository.deleteById(id);
            return "User deleted successfully.";
        } else {
            return "User not found with ID: " + id;
        }
    }

    public User UpdateUserById(String id, User user) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()){
            User user1 = byId.get();
            user1.setUsername(user.getUsername());
            user1.setEmail(user.getEmail());
            user1.setPassword(user.getPassword());
            user1.setPhone(user.getPassword());
            User savedUser = userRepository.save(user1);
            return savedUser;
        }
        throw new RuntimeException("user not found with this id" +id);
    }
}
