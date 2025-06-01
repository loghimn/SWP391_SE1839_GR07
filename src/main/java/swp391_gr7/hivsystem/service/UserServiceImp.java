package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.UserCreateRequest;
import swp391_gr7.hivsystem.dto.request.UserUpdateRequest;
import swp391_gr7.hivsystem.dto.roleDto.CustomerDto;
import swp391_gr7.hivsystem.dto.roleDto.DoctorDto;
import swp391_gr7.hivsystem.dto.roleDto.ManagerDto;
import swp391_gr7.hivsystem.dto.roleDto.StaffDto;
import swp391_gr7.hivsystem.message.registerMessage;
import swp391_gr7.hivsystem.model.*;
import swp391_gr7.hivsystem.repository.*;


@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private PasswordEncoder PasswordEncoder;


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

    @Override
    public registerMessage save(CustomerDto customerDto) {
        User existingPhone = userRepository.findByPhone(customerDto.getPhone());
        User existingEmail = userRepository.findByEmail(customerDto.getEmail());
        if (existingEmail != null || existingPhone != null) {
            return new registerMessage("Email or Phone already exists", false);
        } else {
            User user = new User();
            user.setUsername(customerDto.getUsername());
            user.setPasswordHash(this.PasswordEncoder.encode(customerDto.getPassword()));
            user.setEmail(customerDto.getEmail());
            user.setPhone(customerDto.getPhone());
            user.setFullName(customerDto.getFullName());
            user.setDateOfBirth(customerDto.getDateOfBirth());
            user.setGender(customerDto.getGender());
            user.setRole(customerDto.getRole());
            userRepository.save(user);
            if (customerDto.getRole().equalsIgnoreCase("customer")) {
                Customer customer = new Customer();
                customer.setUser(user);
                customer.setAddress(customerDto.getAddress());
                customerRepository.save(customer);
                return new registerMessage("Customer saved successfully", true);
            } else {
                return new registerMessage("Customer saved failure", false);
            }
        }
    }

    @Override
    public registerMessage save(StaffDto staffDto) {
        User existtingEmail = userRepository.findByEmail(staffDto.getEmail());
        User existingPhone = userRepository.findByPhone(staffDto.getPhone());
        if (existtingEmail != null || existingPhone != null) {
            return new registerMessage("Email or Phone already exists", false);
        } else {
            User user = new User();
            user.setUsername(staffDto.getUsername());
            user.setPasswordHash(this.PasswordEncoder.encode(staffDto.getPassword()));
            user.setEmail(staffDto.getEmail());
            user.setPhone(staffDto.getPhone());
            user.setFullName(staffDto.getFullName());
            user.setDateOfBirth(staffDto.getDateOfBirth());
            user.setGender(staffDto.getGender());
            user.setRole(staffDto.getRole());
            userRepository.save(user);
            if (staffDto.getRole().equalsIgnoreCase("staff")) {
                Staff staff = new Staff();
                staff.setUser(user);
                staff.setDepartment(staffDto.getDepartment());
                staff.setWorkShift(staffDto.getWorkShift());
                staff.setAssignedModule(staffDto.getAssignedModule());
                staffRepository.save(staff);
                return new registerMessage("Staff saved successfully", true);
            } else {
                return new registerMessage("Staff saved failure", false);
            }
        }
    }

    @Override
    public registerMessage save(DoctorDto doctorDto) {
        Doctor existingLisenceNumber = doctorRepository.findByLicenseNumber(doctorDto.getLicenseNumber());
        User existingEmail = userRepository.findByEmail(doctorDto.getEmail());
        User existingPhone = userRepository.findByPhone(doctorDto.getPhone());
        if (existingLisenceNumber != null) {
            return new registerMessage("License number already exists", false);
        }
        if (existingEmail != null || existingPhone != null) {
            return new registerMessage("Email or Phone already exists", false);
        } else {
            User user = new User();
            user.setUsername(doctorDto.getUsername());
            user.setPasswordHash(this.PasswordEncoder.encode(doctorDto.getPassword()));
            user.setEmail(doctorDto.getEmail());
            user.setPhone(doctorDto.getPhone());
            user.setFullName(doctorDto.getFullName());
            user.setDateOfBirth(doctorDto.getDateOfBirth());
            user.setGender(doctorDto.getGender());
            user.setRole(doctorDto.getRole());
            userRepository.save(user);
            if (doctorDto.getRole().equalsIgnoreCase("doctor")) {
                Doctor doctor = new Doctor();
                doctor.setUser(user);
                doctor.setDepartment(doctorDto.getDepartment());
                doctor.setYearExperience(doctorDto.getYearExperience());
                doctor.setLicenseNumber(doctorDto.getLicenseNumber());
                doctorRepository.save(doctor);
                return new registerMessage("Doctor saved successfully", true);
            } else {
                return new registerMessage("Doctor saved failure", false);
            }
        }
    }

    @Override
    public registerMessage save(ManagerDto managerDto) {
        User existtingEmail = userRepository.findByEmail(managerDto.getEmail());
        User existingPhone = userRepository.findByPhone(managerDto.getPhone());
        if (existtingEmail != null || existingPhone != null) {
            return new registerMessage("Email or Phone already exists", false);
        } else {
            User user = new User();
            user.setUsername(managerDto.getUsername());
            user.setPasswordHash(this.PasswordEncoder.encode(managerDto.getPassword()));
            user.setEmail(managerDto.getEmail());
            user.setPhone(managerDto.getPhone());
            user.setFullName(managerDto.getFullName());
            user.setDateOfBirth(managerDto.getDateOfBirth());
            user.setGender(managerDto.getGender());
            user.setRole(managerDto.getRole());
            userRepository.save(user);
            if (managerDto.getRole().equalsIgnoreCase("manager")) {
                Manager manager = new Manager();
                manager.setUser(user);
                manager.setDepartment(managerDto.getDepartment());
                manager.setOfficePhone(managerDto.getOfficePhone());
                managerRepository.save(manager);
                return new registerMessage("Manager saved successfully", true);
            } else {
                return new registerMessage("Manager saved failure", false);
            }
        }
    }

    public void deleteUser(String userId){
        userRepository.delete(findUserByUserId(userId));
    }
}
