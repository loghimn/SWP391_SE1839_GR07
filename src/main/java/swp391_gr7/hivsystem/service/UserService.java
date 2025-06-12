package swp391_gr7.hivsystem.service;

import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.*;
import swp391_gr7.hivsystem.model.Users;

@Service
public interface UserService {
    public Users createUser(CreateUserRequest request);
    public boolean registerUserAndCustomer(UserAndCustomerCreateRequest request);
    public boolean registerUserAndDoctor(UserAndDoctorCreateRequest request);
    public boolean registerUserAndManager(UserAndManagerCreateRequest request);
    public boolean registerUserAndStaff(UserAndStaffCreateRequest request);
    public boolean registerUserAndAdmin(UserAndAdminCreateRequest request);
    public Users findUserByUserId(int userId);
    public Users updateUser(UserUpdateRequest request, Users users);
    boolean updateUserAndDoctor(int userId, UserAndDoctorUpdateRequest request);
    Users deleteUser(int userId);
}
