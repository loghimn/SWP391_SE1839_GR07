package swp391_gr7.hivsystem.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import swp391_gr7.hivsystem.dto.request.GoogleRegisterRequest;
import swp391_gr7.hivsystem.model.Customers;
import swp391_gr7.hivsystem.model.Users;
import swp391_gr7.hivsystem.repository.CustomerRepository;
import swp391_gr7.hivsystem.repository.OAuth2Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class OAuth2ServiceImp implements OAuth2Service {
    private final OAuth2Repository OAuth2Repository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    public OAuth2ServiceImp(OAuth2Repository OAuth2Repository, CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.OAuth2Repository = OAuth2Repository;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean registerGoogleUsers(GoogleRegisterRequest request) {
        if (OAuth2Repository.existsByEmail(request.getEmail())
            || OAuth2Repository.existsByUsername(request.getUsername())
            || OAuth2Repository.existsByPhone(request.getPhone())) {
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

            Users users = Users.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .email(request.getEmail())
                    .phone(request.getPhone())
                    .fullName(request.getFullName())
                    .dateOfBirth(dob)
                    .gender(request.getGender())
                    .role("CUSTOMER")
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
}
