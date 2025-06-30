package swp391_gr7.hivsystem.service;

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
import swp391_gr7.hivsystem.repository.StaffRepository;
import swp391_gr7.hivsystem.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImp implements UserService {


    private final UserRepository userRepository;
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final DoctorService doctorService;
    private final ManagerService managerService;
    private final StaffService staffService;
    private final AdminService adminService;
    private final ManagerRepository managerRepository;
    private final CustomerRepository customerRepository;
    private final StaffRepository staffRepository;


    public UserServiceImp(UserRepository userRepository, CustomerService customerService, DoctorService doctorService, ManagerService managerService, StaffService staffService, AdminService adminService, ManagerRepository managerRepository, CustomerRepository customerRepository, StaffRepository staffRepository) {
        this.userRepository = userRepository;
        this.customerService = customerService;
        this.doctorService = doctorService;
        this.managerService = managerService;
        this.staffService = staffService;
        this.adminService = adminService;
        this.managerRepository = managerRepository;
        this.customerRepository = customerRepository;
        this.staffRepository = staffRepository;
    }

    @Override
    public Users createUser(UserCreateRequest request) {
        Users users = new Users();

        if (userRepository.existsByUsername(request.getUsername())) {
            // Nếu tên đăng nhập đã tồn tại, ném ngoại lệ đã được định nghĩa ở ErrorCode
            throw new AppException(ErrorCode.USER_EXIST_USERNAME);
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.USER_EXIST_EMAIL);
        }
        if (userRepository.existsByPhone(request.getPhone())) {
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
        Users users = userRepository.findByUserId(userId);
        if (users == null) {
            throw new AppException(ErrorCode.USER_NEED_UPDATE_NOT_FOUND);
        }
        return users;
    }


    public Users updateUser(UserUpdateRequest request, Users users) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_UPDATE_EXIST_USERNAME);
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.USER_UPDATE_EXIST_EMAIL);
        }
        if (userRepository.existsByPhone(request.getPhone())) {
            throw new AppException(ErrorCode.USER_UPDATE_EXIST_PHONE);
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        users.setUsername(request.getUsername());
        users.setPassword(passwordEncoder.encode(request.getPassword()));
        users.setEmail(request.getEmail());
        users.setPhone(request.getPhone());
        users.setFullName(request.getFullName());
        users.setDateOfBirth(request.getDateOfBirth());
        users.setGender(request.getGender());
        return userRepository.save(users);
    }

    @Override
    public boolean updateUserAndDoctor(int doctorid, UserAndDoctorUpdateRequest request) {
        Doctors doctors = doctorService.findDoctorById(doctorid);
        Users users = findUserByUserId(doctors.getUsers().getUserId());
        this.updateUser(request, users);
        doctors = doctorService.updateDoctor(request, users);
        return users != null && doctors != null;
    }

    @Override
    public boolean updateUserAndStaff(int staffid, UserAndStaffUpdateRequest request) {
        Staffs staffs = staffRepository.findByStaffId(staffid);
        Users user = findUserByUserId(staffs.getUsers().getUserId());
        this.updateUser(request, user);
        staffs = staffService.updateStaff(request, user);
        return user != null && staffs != null;
    }

    @Override
    public boolean updateUserAndCustomer(int customerid, UserAndCustomerUpdateRequest request) {
        Customers customers = customerRepository.findByCustomerId(customerid);
        Users users = findUserByUserId(customers.getUsers().getUserId());
        this.updateUser(request, users);
        customers = customerService.updateCustomer(request, users);
        return users != null && customers != null;
    }

    public Users deleteUser(int userId) {
        Users users = findUserByUserId(userId);
        users.setStatus(false);
        return userRepository.save(users);
    }

    public Users deleteDoctorStaffCus(int userId) {
        Users users = findUserByUserId(userId);
        if (users == null && !(users.getRole().equals("Doctor")) && !(users.getRole().equals("Staff")) && !(users.getRole().equals("Customer"))) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        users.setStatus(false);
        return userRepository.save(users);
    }

    public Users deleteCustomer(int userId) {
        Users users = findUserByUserId(userId);
        if (users == null && !(users.getRole().equals("Customer"))) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        users.setStatus(false);
        return userRepository.save(users);
    }

    public Users deleteStaff(int userId) {
        Users users = findUserByUserId(userId);
        if (users == null && !(users.getRole().equals("Staff"))) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        users.setStatus(false);
        return userRepository.save(users);
    }

    @Override
    public List<Users> findAllUsers() {
        if (userRepository.findAll() == null) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        return userRepository.findAll();
    }

    @Override
    public Users getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        return userRepository.findByUsername(name)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public Users getUserById(int id) {
        if (userRepository.findByUserId(id) == null) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        return userRepository.findByUserId(id);
    }
}
