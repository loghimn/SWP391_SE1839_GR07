package swp391_gr7.hivsystem.service;

import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.UserCreateRequest;
import swp391_gr7.hivsystem.dto.request.UserUpdateRequest;
import swp391_gr7.hivsystem.model.User;

@Service
public interface UserService {
    public User createUser(UserCreateRequest request);
    public User findUserByUserId(String userId);
    public void deleteUser(String userId);
    public User updateUser(String UserId,UserUpdateRequest request);
}
