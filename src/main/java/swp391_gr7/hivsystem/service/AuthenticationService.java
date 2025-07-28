package swp391_gr7.hivsystem.service;


import java.text.ParseException;
import java.util.Collections;
import java.util.Date;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GooglePublicKeysManager;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import swp391_gr7.hivsystem.dto.request.IntrospectRequest;
import swp391_gr7.hivsystem.dto.response.AuthenticationResponse;
import swp391_gr7.hivsystem.dto.request.AuthenticationRequest;
import swp391_gr7.hivsystem.dto.response.IntrospectReponse;
import swp391_gr7.hivsystem.exception.AppException;
import swp391_gr7.hivsystem.exception.ErrorCode;
import swp391_gr7.hivsystem.model.*;
import swp391_gr7.hivsystem.repository.*;


@Slf4j
@Service
public class AuthenticationService {

    @NonFinal
    private static final String SECRET_KEY = "secret_key_is_very_long_and_secure_sum25_swp391_hiv"; // tu tao
    @Autowired
    UserRepository userRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Value("${google.clientId}")
    private String googleClientId;


    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest)
            throws JOSEException {
        var user = userRepository.findByUsername(authenticationRequest.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.WRONG_USERNAME_PASSWORD)); // nếu không tìm thấy người dùng, in ra lỗi được định nghĩa trong ErrorCode
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!user.isStatus()) {
            throw new AppException(ErrorCode.USER_WAS_DELETED);
        }
        boolean result = passwordEncoder.matches(authenticationRequest.getPassword(),
                user.getPassword()); // xác định xem user có đăng nhập thành công hay không
        if (!result) {
            throw new AppException(ErrorCode.WRONG_USERNAME_PASSWORD); // nếu mật khẩu không khớp, in ra lỗi UNAUTHENTICATED
        }
        var token = generateToken(user.getUserId());
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(result)
                .build();
    }

    //Token has three component:header, payload, signature
//    private String generateToken(int userId) throws JOSEException {
//        //Header of token
//        Users users = userRepository.findByUserId(userId);
//        String role = users.getRole();
//
//        // Create JWSHeader with algorithm HS256
//        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
//
//        // Create JWTClaimsSet with subject, issuer, issue time, expiration time and custom claim
//        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
//                .subject(users.getUsername()) // subject is the username of the user
//                .issuer("gr7") // issuer is the name of the application or service
//                .issueTime(new Date()) // issue time is the current time
//                .expirationTime(new Date
//                        (new Date().getTime() + 1000 * 60 * 60 * 24)) // expiration time is 24 hours from now
//                .claim("role", role)
//                .build();
//
//        //Create payload
//        Payload payload = new Payload(claimsSet.toJSONObject());
//
//        //Create JWSObject -> JWSObject need 2 components: header and payload
//        JWSObject jwsObject = new JWSObject(header, payload);
//
//        // Sign the JWSObject with the secret key
//        try {
//            // SECRET_KEY is defined above with minimum 32 characters
//            jwsObject.sign(new MACSigner(SECRET_KEY.getBytes()));
//            return jwsObject.serialize();
//        } catch (JOSEException e) {
//            log.error("Error generating token, can not create token", e);
//            throw new JOSEException(e.getMessage());
//        }
//    }


    public String generateToken(int userId) throws JOSEException {
        Users users = userRepository.findByUserId(userId);
        String role = users.getRole();

        JWTClaimsSet.Builder claimsBuilder = new JWTClaimsSet.Builder()
                .subject(users.getUsername())
                .issuer("gr7")
                .issueTime(new Date())
                .expirationTime(new Date(new Date().getTime() + 1000 * 60 * 60 * 24)) // 24 hours from now
                .claim("role", role);

        // Thêm ID tương ứng tùy theo role
        switch (role) {
            case "Admin":
                Admins admin = adminRepository.findByUsers(users);
                if (admin != null) {
                    claimsBuilder.claim("adminId", admin.getAdminID());
                }
                break;
            case "Staff":
                Staffs staff = staffRepository.findByUsers(users);
                if (staff != null) {
                    claimsBuilder.claim("staffId", staff.getStaffId());
                }
                break;
            case "Manager":
                Managers managers = managerRepository.findByUsers(users);
                if (managers != null) {
                    claimsBuilder.claim("managerId", managers.getManagerId());
                }
                break;
            case "Customer":
                Customers customer = customerRepository.findByUsers(users);
                if (customer != null) {
                    claimsBuilder.claim("customerId", customer.getCustomerId());
                }
                break;
            case "Doctor":
                Doctors doctor = doctorRepository.findByUsers(users);
                if (doctor != null) {
                    claimsBuilder.claim("doctorId", doctor.getDoctorId());
                }
                break;
        }

        JWTClaimsSet claimsSet = claimsBuilder.build();

        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        Payload payload = new Payload(claimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SECRET_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Error generating token", e);
            throw new JOSEException(e.getMessage());
        }
    }

    public AuthenticationResponse authenticateWithGoogle(String googleToken) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    JacksonFactory.getDefaultInstance())
                    .setAudience(Collections.singletonList(googleClientId))
                    .build();

            GoogleIdToken idToken = verifier.verify(googleToken);

            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();
                String email = payload.getEmail();
                String name = (String) payload.get("name");

                Users user = userRepository.findByUsername(email).orElse(null);
                boolean isNewUser = false;

                if (user == null) {
                    // Create new user with role "Customer"
                    user = new Users();
                    user.setUsername(email);
                    user.setPassword(""); // Password not needed for Google login
                    user.setRole("Customer");
                    user = userRepository.save(user);

                    // Create a default Customer record
                    Customers customer = new Customers();
                    customer.setUsers(user);
                    //customer.setFullName(name); // or payload.get("name")
                    customerRepository.save(customer);

                    isNewUser = true;
                }

                String token = generateToken(user.getUserId());

                return AuthenticationResponse.builder()
                        .token(token)
                        .authenticated(true)
                        .build();
            } else {
                throw new AppException(ErrorCode.UNAUTHENTICATED);
            }
        } catch (Exception e) {
            log.error("Google login failed", e);
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
    }

}
