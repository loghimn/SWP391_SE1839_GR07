package swp391_gr7.hivsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import swp391_gr7.hivsystem.dto.reponse.ApiResponse;
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
public class OAuth2Controller {
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private OAuth2Service oAuth2Service;

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
    public ApiResponse<Boolean> registerGoogleUser(@RequestBody GoogleRegisterRequest request) {
        boolean result = oAuth2Service.registerGoogleUsers(request);
        return ApiResponse.<Boolean>builder()
                .result(result)
                .message(result ? "Google account registered successfully" : "Registration failed")
                .build();
    }

    @GetMapping("/check-email")
    public Map<String, Boolean> checkEmailExists(@RequestParam String email) {
        boolean exists = userRepository.existsByEmail(email);
        return Map.of("exists", exists);
    }
}
