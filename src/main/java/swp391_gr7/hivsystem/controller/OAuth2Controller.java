package swp391_gr7.hivsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import swp391_gr7.hivsystem.dto.reponse.ApiReponse;
import swp391_gr7.hivsystem.dto.reponse.AuthenticationReponse;
import swp391_gr7.hivsystem.dto.reponse.OAuth2LoginResponse;
import swp391_gr7.hivsystem.dto.request.GoogleRegisterRequest;
import swp391_gr7.hivsystem.model.Customer;
import swp391_gr7.hivsystem.model.User;
import swp391_gr7.hivsystem.repository.CustomerRepository;
import swp391_gr7.hivsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("api/oauth2")
public class OAuth2Controller {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;

    public OAuth2Controller(UserRepository userRepository, CustomerRepository customerRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
    }



    @GetMapping("/loginSuccess")
    public RedirectView oauth2LoginSuccess(@AuthenticationPrincipal OAuth2User oauth2User) {
        String email = oauth2User.getAttribute("email");
        return new RedirectView("http://localhost:8080/login.html?email=" + email);
    }

    @PostMapping("/user/google/register")
    public ApiReponse<String> registerGoogleUser(@RequestBody GoogleRegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return ApiReponse.<String>builder()
                    .message("Email already exists")
                    .result("fail")
                    .build();
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            return ApiReponse.<String>builder()
                    .message("Username already exists")
                    .result("fail")
                    .build();
        }

        try {
            LocalDate dob = LocalDate.parse(request.getDateOfBirth());
            int age = LocalDate.now().getYear() - dob.getYear();
            if (dob.plusYears(age).isAfter(LocalDate.now())) {
                age--;
            }
            if (age < 18) {
                return ApiReponse.<String>builder()
                        .message("User must be at least 18 years old")
                        .result("fail")
                        .build();
            }

            User user = User.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .email(request.getEmail())
                    .phone(request.getPhone())
                    .fullName(request.getFullName())
                    .dateOfBirth(dob)
                    .gender(request.getGender())
                    .role("CUSTOMER")
                    .build();

            Customer customer = new Customer();
            customer.setUser(user);
            customer.setAddress(request.getAddress());

            customerRepository.save(customer);

            return ApiReponse.<String>builder()
                    .message("Google account registered successfully")
                    .result("success")
                    .build();

        } catch (Exception e) {
            return ApiReponse.<String>builder()
                    .message("Registration failed: " + e.getMessage())
                    .result("fail")
                    .build();
        }
    }

    @GetMapping("/check-email")
    public Map<String, Boolean> checkEmailExists(@RequestParam String email) {
        boolean exists = userRepository.existsByEmail(email);
        return Map.of("exists", exists);
    }

}
