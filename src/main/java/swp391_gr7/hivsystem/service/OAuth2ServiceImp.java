package swp391_gr7.hivsystem.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import swp391_gr7.hivsystem.dto.request.GoogleRegisterRequest;
import swp391_gr7.hivsystem.dto.request.OAuth2CreateRequest;
import swp391_gr7.hivsystem.exception.AppException;
import swp391_gr7.hivsystem.exception.ErrorCode;
import swp391_gr7.hivsystem.model.Customers;
import swp391_gr7.hivsystem.model.Managers;
import swp391_gr7.hivsystem.model.Users;
import swp391_gr7.hivsystem.repository.CustomerRepository;
import swp391_gr7.hivsystem.repository.ManagerRepository;
import swp391_gr7.hivsystem.repository.OAuth2Repository;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class OAuth2ServiceImp implements OAuth2Service {
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final ManagerRepository managerRepository;

    public OAuth2ServiceImp(UserRepository userRepository,
                            CustomerRepository customerRepository,
                            PasswordEncoder passwordEncoder,
                            ManagerRepository managerRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.managerRepository = managerRepository;
    }

    @Override
    public boolean registerOAuth2User(OAuth2CreateRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.OAUTH2_INVALID_USERNAME_EXIST);
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.OAUTH2_INVALID_EMAIL_EXIST);
        }
        if (userRepository.existsByPhone(request.getPhone())) {
            throw new AppException(ErrorCode.OAUTH2_INVALID_PHONE_EXIST);
        }
        Managers manager = managerRepository.findManagerById(1);
        if (manager == null) {
            throw new AppException(ErrorCode.MANAGER_NOT_FOUND);
        }

        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setFullName(request.getFullName());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setGender(request.getGender());
        user.setRole("Customer");
        user.setStatus(true);
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        Customers customer = new Customers();
        customer.setUsers(user);
        customer.setAddress(request.getAddress());
        customer.setManagers(manager);
        customerRepository.save(customer);
        return true;
    }
}
