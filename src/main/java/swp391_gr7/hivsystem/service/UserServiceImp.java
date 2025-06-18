package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.*;
import swp391_gr7.hivsystem.exception.AppException;
import swp391_gr7.hivsystem.exception.ErrorCode;
import swp391_gr7.hivsystem.model.*;
import swp391_gr7.hivsystem.repository.CustomerRepository;
import swp391_gr7.hivsystem.repository.ManagerRepository;
import swp391_gr7.hivsystem.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final CustomerServiceImp customerService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final DoctorServiceImp doctorService;
    private final ManagerServiceImp managerService;
    private final StaffServiceImp staffService;
    private final AdminServiceImp adminService;
    private final ManagerRepository managerRepository;

    private final CustomerRepository customerRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository, CustomerServiceImp customerService, DoctorServiceImp doctorService, ManagerServiceImp managerService, StaffServiceImp staffService, AdminServiceImp adminService, CustomerRepository customerRepository, ManagerRepository managerRepository) {
        this.userRepository = userRepository;
        this.customerService = customerService;
        this.doctorService = doctorService;
        this.managerService = managerService;
        this.staffService = staffService;
        this.adminService = adminService;
        this.customerRepository = customerRepository;
        this.managerRepository = managerRepository;
    }

    @Override
    public Users createUser(CreateUserRequest request) {
        Users users = new Users();

        if(userRepository.existsByUsername(request.getUsername())) {
            // Nếu tên đăng nhập đã tồn tại, ném ngoại lệ đã được định nghĩa ở ErrorCode
            throw new AppException(ErrorCode.USER_EXIST_USERNAME);
        }
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.USER_EXIST_EMAIL);
        }
        if(userRepository.existsByPhone(request.getPhone())) {
            throw new AppException(ErrorCode.USER_EXIST_PHONE);
        }
        users.setUsername(request.getUsername());
        users.setPassword(passwordEncoder.encode(request.getPassword()));
        users.setEmail(request.getEmail());
        users.setPhone(request.getPhone());
        users.setFullName(request.getFullName());
        users.setDateOfBirth(request.getDateOfBirth());
        users.setGender(request.getGender());
        users.setStatus(true);
        // Default role
        return userRepository.save(users);
    }


    @Override
    public boolean registerUserAndCustomer(UserAndCustomerCreateRequest request) {
        Users users = this.createUser(request);
        users.setRole("Customer");
        System.out.println(users);
        Customers customers = customerService.saveCustomer(request, users);
        return users != null && customers != null;
    }

    @Override
    public boolean registerUserAndDoctor(UserAndDoctorCreateRequest request) {
        Users users = this.createUser(request);
        users.setRole("Doctor");
        System.out.println(users);
        Doctors doctors = doctorService.saveDoctor(request, users);
        return users != null && doctors != null;
    }

    @Override
    public boolean registerUserAndManager(UserAndManagerCreateRequest request) {
        Users users = this.createUser(request);
        users.setRole("Manager");
        System.out.println(users);
        Managers managers = managerService.saveManager(request, users);
        return users != null && managers != null;
    }

    @Override
    public boolean registerUserAndStaff(UserAndStaffCreateRequest request) {
        Users users = this.createUser(request);
        users.setRole("Staff");
        System.out.println(users);
        Staffs staffs = staffService.saveStaff(request, users);
        return users != null && staffs != null;
    }

    @Override
    public boolean registerUserAndAdmin(UserAndAdminCreateRequest request) {
        Users users = this.createUser(request);
        users.setRole("Admin");
        System.out.println(users);
        Admins admins = adminService.saveAdmin(request, users);
        return users != null && admins != null;
    }

    public Users findUserByUserId(int userId) {
        return userRepository.findByUserId(userId);
    }


    public Users updateUser(UserUpdateRequest request, Users users) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        users.setPassword(passwordEncoder.encode(request.getPassword()));
        users.setEmail(request.getEmail());
        users.setPhone(request.getPhone());
        users.setFullName(request.getFullName());
        users.setUsername(request.getUsername());
        users.setDateOfBirth(request.getDateOfBirth());
        users.setGender(request.getGender());
        return userRepository.save(users);
    }

    @Override
    public boolean updateUserAndDoctor(int userId, UserAndDoctorUpdateRequest request) {
        Users users = findUserByUserId(userId);
        this.updateUser(request, users);
        Doctors doctors = doctorService.updateDoctor(request, users);
        return users != null && doctors != null;
    }

    public Users deleteUser(int userId) {
        Users users = findUserByUserId(userId);
        users.setStatus(false);
        return userRepository.save(users);
    }

    @Override
    public List<Users> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        return userRepository.findByUsername(name)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }
}
