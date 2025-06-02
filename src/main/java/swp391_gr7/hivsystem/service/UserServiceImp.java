package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.UserCreateRequest;
import swp391_gr7.hivsystem.dto.request.UserUpdateRequest;
import swp391_gr7.hivsystem.dto.roleDto.CustomerDto;
import swp391_gr7.hivsystem.dto.roleDto.DoctorDto;
import swp391_gr7.hivsystem.dto.roleDto.ManagerDto;
import swp391_gr7.hivsystem.dto.roleDto.StaffDto;
import swp391_gr7.hivsystem.message.registerMessage;
import swp391_gr7.hivsystem.model.*;
import swp391_gr7.hivsystem.repository.*;


@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User createUser(UserCreateRequest request) { //Lay information request in UserCreate request
        User user = new User();
        //user.setPassword(request.getPassword());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setUsername(request.getUsername());
        user.setFullName(request.getFullName());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setGender(request.getGender());
        user.setAddress(request.getAddress());
        user.setRole("Customer");
        return userRepository.save(user);
    }

    public User findUserByUserId(int userId) {
        return userRepository.findByUserId(userId);
    }

    public User updateUser(int UserId, UserUpdateRequest request) {//Lay information request in UserCreate request
        User user = findUserByUserId(UserId);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setFullName(request.getFullName());
        user.setUsername(request.getUsername());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setGender(request.getGender());
        user.setAddress(request.getAddress());
        return userRepository.save(user);
    }
}
