package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.*;
import swp391_gr7.hivsystem.model.*;
import swp391_gr7.hivsystem.repository.DoctorRepository;
import swp391_gr7.hivsystem.repository.StaffRepository;
import swp391_gr7.hivsystem.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final CustomerServiceImp customerService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final DoctorServiceImp doctorService;
    private final ManagerServiceImp managerService;
    private final StaffServiceImp staffService;
    @Autowired
    public UserServiceImp(UserRepository userRepository, CustomerServiceImp customerService, DoctorServiceImp doctorService, ManagerServiceImp managerService, ManagerServiceImp managerService1, StaffServiceImp staffService) {
        this.userRepository = userRepository;
        this.customerService = customerService;
        this.doctorService = doctorService;
        this.managerService = managerService;
        this.staffService = staffService;
    }

    @Override
    public User createUser(CreateUserRequest request) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setUsername(request.getUsername());
        user.setFullName(request.getFullName());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setGender(request.getGender());
        // Default role
        return userRepository.save(user);
    }

    public boolean registerUserAndCustomer(UserAndCustomerCreateRequest request) {
        User user = this.createUser(request);
        user.setRole("Customer");
        System.out.println(user);
        Customer customer = customerService.saveCustomer(request, user);
        return user != null && customer != null;
    }

    @Override
    public boolean registerUserAndDoctor(UserAndDoctorCreateRequest request) {
        User user = this.createUser(request);
        user.setRole("Doctor");
        System.out.println(user);
        Doctor doctor = doctorService.saveDoctor(request, user);
        return user != null && doctor != null;
    }

    @Override
    public boolean registerUserAndManager(UserAndManagerCreateRequest request) {
        User user = this.createUser(request);
        user.setRole("Manager");
        System.out.println(user);
        Manager manager = managerService.saveManager(request, user);
        return user != null && manager != null;
    }

    @Override
    public boolean registerUserAndStaff(UserAndStaffCreateRequest request) {
        User user = this.createUser(request);
        user.setRole("Staff");
        System.out.println(user);
        Staff staff = staffService.saveStaff(request, user);
        return user != null && staff != null;
    }



    public User findUserByUserId(int userId) {
        return userRepository.findByUserId(userId);
    }

    public User updateUser(int userId, UserUpdateRequest request) {
        User user = findUserByUserId(userId);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setFullName(request.getFullName());
        user.setUsername(request.getUsername());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setGender(request.getGender());
        return userRepository.save(user);
    }
}
