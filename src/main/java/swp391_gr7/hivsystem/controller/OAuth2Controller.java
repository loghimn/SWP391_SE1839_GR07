package swp391_gr7.hivsystem.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import swp391_gr7.hivsystem.dto.request.OAuth2CreateRequest;
import swp391_gr7.hivsystem.dto.response.ApiResponse;
import swp391_gr7.hivsystem.dto.request.GoogleRegisterRequest;
import swp391_gr7.hivsystem.repository.CustomerRepository;
import swp391_gr7.hivsystem.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import swp391_gr7.hivsystem.service.OAuth2Service;
import swp391_gr7.hivsystem.service.UserService;
import swp391_gr7.hivsystem.service.UserServiceImp;

import java.util.Map;

@RestController
@RequestMapping("/api/oauth2")
@SecurityRequirement(name = "bearerAuth")
public class OAuth2Controller {
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private OAuth2Service oAuth2Service;

    public OAuth2Controller(UserRepository userRepository, CustomerRepository customerRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/login")
    public ApiResponse<String> oauth2Login(@AuthenticationPrincipal OAuth2User oauth2User) {
        String email = oauth2User.getAttribute("email");
        boolean exists = userRepository.existsByEmail(email);

        if (exists) {
            return ApiResponse.<String>builder()
                    .code(200)
                    .result("LOGIN_SUCCESS")
                    .message("Login successful")
                    .build();
        } else {
            return ApiResponse.<String>builder()
                    .code(302)
                    .result("REGISTRATION_REQUIRED")
                    .message("Please complete registration")
                    .build();
        }
    }

    @PostMapping("/register")
    public ApiResponse<Boolean> registerOAuth2User(@RequestBody @Valid OAuth2CreateRequest request) {
        boolean result = oAuth2Service.registerOAuth2User(request);

        return ApiResponse.<Boolean>builder()
                .code(result ? 200 : 400)
                .result(result)
                .message(result ? "Registration successful" : "Registration failed")
                .build();
    }
}
