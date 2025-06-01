package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.UserCreateRequest;
import swp391_gr7.hivsystem.dto.request.UserUpdateRequest;
import swp391_gr7.hivsystem.model.User;
import swp391_gr7.hivsystem.repository.UserRepository;


@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User createUser(UserCreateRequest request) { //Lay information request in UserCreate request
        User user = new User();
        user.setPasswordHash(request.getPasswordHash());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPasswordHash(passwordEncoder.encode(request.getPasswordHash()));
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setUsername(request.getUsername());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setGender(request.getGender());
        user.setRole(request.getRole());
        return userRepository.save(user);
    }
    public User findUserByUserId(String userId){
        return userRepository.findByUserId(userId);
    }
    public User updateUser(String UserId, UserUpdateRequest request){//Lay information request in UserCreate request
        User user = findUserByUserId(UserId);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPasswordHash(passwordEncoder.encode(request.getPasswordHash()));
        user.setPasswordHash(request.getPasswordHash());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setUsername(request.getUsername());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setGender(request.getGender());
        user.setRole(request.getRole());
        return userRepository.save(user);
    }
    public void deleteUser(String userId){
        userRepository.delete(findUserByUserId(userId));
    }
}
