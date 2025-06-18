package swp391_gr7.hivsystem.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import swp391_gr7.hivsystem.dto.request.GoogleRegisterRequest;
import swp391_gr7.hivsystem.dto.request.OAuth2CreateRequest;
import swp391_gr7.hivsystem.model.Customers;
import swp391_gr7.hivsystem.model.Users;
import swp391_gr7.hivsystem.repository.CustomerRepository;
import swp391_gr7.hivsystem.repository.OAuth2Repository;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.repository.UserRepository;

import java.time.LocalDate;
@Service
public class OAuth2ServiceImp implements OAuth2Service {
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public OAuth2ServiceImp(UserRepository userRepository,
                           CustomerRepository customerRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean registerOAuth2User(OAuth2CreateRequest request) {
        if (userRepository.existsByEmail(request.getEmail())
            || userRepository.existsByUsername(request.getUsername())
            || userRepository.existsByPhone(request.getPhone())) {
            return false;
        }

        String gender = request.getGender();
        if (gender == null || !(gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female"))) {
            return false;
        }

        try {
            LocalDate dob = LocalDate.parse(request.getDateOfBirth());
            int age = LocalDate.now().getYear() - dob.getYear();
            if (dob.plusYears(age).isAfter(LocalDate.now())) age--;
            if (age < 18) return false;

            Users user = Users.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .email(request.getEmail())
                    .phone(request.getPhone())
                    .fullName(request.getFullName())
                    .dateOfBirth(dob)
                    .gender(request.getGender())
                    .role("Customer")
                    .build();

            Customers customer = new Customers();
            customer.setUsers(user);
            customer.setAddress(request.getAddress());

            customerRepository.save(customer);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
