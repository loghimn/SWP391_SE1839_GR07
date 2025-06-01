package swp391_gr7.hivsystem.service;

import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.UserCreateRequest;
import swp391_gr7.hivsystem.dto.request.UserUpdateRequest;
import swp391_gr7.hivsystem.dto.roleDto.CustomerDto;
import swp391_gr7.hivsystem.dto.roleDto.DoctorDto;
import swp391_gr7.hivsystem.dto.roleDto.ManagerDto;
import swp391_gr7.hivsystem.dto.roleDto.StaffDto;
import swp391_gr7.hivsystem.message.registerMessage;
import swp391_gr7.hivsystem.model.User;

@Service
public interface UserService {
    public User createUser(UserCreateRequest request);
    public User findUserByUserId(int userId);
    public void deleteUser(int userId);
    public User updateUser(int UserId,UserUpdateRequest request);
    registerMessage save(CustomerDto customerDto);
    registerMessage save(StaffDto staffDto);
    registerMessage save(DoctorDto doctorDto);
    registerMessage save(ManagerDto managerDto);
}
