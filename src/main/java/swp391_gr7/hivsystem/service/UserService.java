package swp391_gr7.hivsystem.service;

import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.*;
import swp391_gr7.hivsystem.model.User;

@Service
public interface UserService {
    public User createUser(CreateUserRequest request);
    public boolean registerUserAndCustomer(UserAndCustomerCreateRequest request);
    public boolean registerUserAndDoctor(UserAndDoctorCreateRequest request);
    public boolean registerUserAndManager(UserAndManagerCreateRequest request);
    public boolean registerUserAndStaff(UserAndStaffCreateRequest request);
    public boolean registerUserAndAdmin(UserAndAdminCreateRequest request);
    public User findUserByUserId(int userId);
    //public void deleteUser(int userId);
    public User updateUser(int UserId,UserUpdateRequest request);

}
