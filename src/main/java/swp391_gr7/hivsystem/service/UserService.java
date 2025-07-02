package swp391_gr7.hivsystem.service;

import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.*;
import swp391_gr7.hivsystem.model.Users;

import java.util.List;

@Service
public interface UserService {
    Users createUser(UserCreateRequest request);

    boolean registerUserAndCustomer(UserAndCustomerCreateRequest request);

    boolean registerUserAndDoctor(UserAndDoctorCreateRequest request);

    boolean registerUserAndManager(UserAndManagerCreateRequest request);

    boolean registerUserAndStaff(UserAndStaffCreateRequest request);

    boolean registerUserAndAdmin(UserAndAdminCreateRequest request);

    Users findUserByUserId(int userId);

    Users updateUser(UserUpdateRequest request, Users users);

    boolean updateUserAndDoctor(int userId, UserAndDoctorUpdateRequest request);

    boolean updateUserAndStaff(int userId, UserAndStaffUpdateRequest request);

    boolean updateUserAndCustomer(int userId, UserAndCustomerUpdateRequest request);

    Users deleteUser(int userId);

    List<Users> findAllUsers();

    Users getMyInfo();

    Users getUserById(int id);

    Users deleteDoctorStaffCus(int userId);



}
