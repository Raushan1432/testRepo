package com.theiris.testproject.Service;

import com.theiris.testproject.Dto.LoginDto;
import com.theiris.testproject.Dto.UserDTO;
import com.theiris.testproject.Entity.User;
import com.theiris.testproject.Exception.UserAlreadyExist;
import com.theiris.testproject.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


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


}
