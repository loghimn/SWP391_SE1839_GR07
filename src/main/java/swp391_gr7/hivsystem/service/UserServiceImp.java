package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.*;
import swp391_gr7.hivsystem.model.*;
import swp391_gr7.hivsystem.repository.CustomerRepository;
import swp391_gr7.hivsystem.repository.UserRepository;

import java.time.LocalDate;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final CustomerServiceImp customerService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final DoctorServiceImp doctorService;
    private final ManagerServiceImp managerService;
    private final StaffServiceImp staffService;
    private final AdminServiceImp adminService;

    private final CustomerRepository customerRepository;
    @Autowired
    public UserServiceImp(UserRepository userRepository, CustomerServiceImp customerService, DoctorServiceImp doctorService, ManagerServiceImp managerService, StaffServiceImp staffService, AdminServiceImp adminService, CustomerRepository customerRepository) {
        this.userRepository = userRepository;
        this.customerService = customerService;
        this.doctorService = doctorService;
        this.managerService = managerService;
        this.staffService = staffService;
        this.adminService = adminService;
        this.customerRepository = customerRepository;
    }

    @Override
    public Users createUser(CreateUserRequest request) {
        Users users = new Users();
        users.setPassword(passwordEncoder.encode(request.getPassword()));
        users.setEmail(request.getEmail());
        users.setPhone(request.getPhone());
        users.setUsername(request.getUsername());
        users.setFullName(request.getFullName());
        users.setDateOfBirth(request.getDateOfBirth());
        users.setGender(request.getGender());
        users.setStatus(true);
        // Default role
        return userRepository.save(users);
    }


    @Override
    public boolean registerUserAndCustomer(UserAndCustomerCreateRequest request) {
        if (request.getDateOfBirth() == null) {
            return false;
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            return false;
        }
        if (userRepository.existsByPhone(request.getPhone())) {
            return false;
        }
        String gender = request.getGender();
        if (gender == null || !(gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female"))) {
            return false;
        }
        LocalDate dob = request.getDateOfBirth();
        LocalDate now = LocalDate.now();
        int age = now.getYear() - dob.getYear();
        if (dob.plusYears(age).isAfter(now)) {
            age--;
        }
        if (age < 18) {
            return false;
        }
        try {
            Users users = Users.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .email(request.getEmail())
                    .phone(request.getPhone())
                    .fullName(request.getFullName())
                    .dateOfBirth(dob)
                    .gender(request.getGender())
                    .role("CUSTOMER")
                    .status(true)
                    .build();

            Customers customers = new Customers();
            customers.setUsers(users);
            customers.setAddress(request.getAddress());

            customerRepository.save(customers);
            return true;
        } catch (Exception e) {
            return false;
        }
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


}
