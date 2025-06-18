package swp391_gr7.hivsystem.service;


import java.text.ParseException;
import java.util.Date;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;

import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import swp391_gr7.hivsystem.dto.request.IntrospectRequest;
import swp391_gr7.hivsystem.dto.response.AuthenticationResponse;
import swp391_gr7.hivsystem.dto.request.AuthenticationRequest;
import swp391_gr7.hivsystem.dto.response.IntrospectReponse;
import swp391_gr7.hivsystem.exception.AppException;
import swp391_gr7.hivsystem.exception.ErrorCode;
import swp391_gr7.hivsystem.model.Users;
import swp391_gr7.hivsystem.repository.UserRepository;


@Slf4j
@Service
public class AuthenticationService {

    @NonFinal
    private static final String SECRET_KEY = "secret_la_bi_mat_thoi-lam-on-chay-dum-tao"; // tu tao
    @Autowired
    UserRepository userRepository;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest)
            throws JOSEException {
        var user = userRepository.findByUsername(authenticationRequest.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.WRONG_USERNAME_PASSWORD)); // nếu không tìm thấy người dùng, in ra lỗi được định nghĩa trong ErrorCode
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
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
    private String generateToken(int userId) throws JOSEException {
        //Header of token
        Users users = userRepository.findByUserId(userId);
        String role = users.getRole();

        // Create JWSHeader with algorithm HS256
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);

        // Create JWTClaimsSet with subject, issuer, issue time, expiration time and custom claim
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(users.getUsername()) // subject is the username of the user
                .issuer("gr7") // issuer is the name of the application or service
                .issueTime(new Date()) // issue time is the current time
                .expirationTime(new Date
                        (new Date().getTime() + 1000 * 60 * 60 * 24)) // expiration time is 24 hours from now
                .claim("role", role)
                .build();

        //Create payload
        Payload payload = new Payload(claimsSet.toJSONObject());

        //Create JWSObject -> JWSObject need 2 components: header and payload
        JWSObject jwsObject = new JWSObject(header, payload);

        // Sign the JWSObject with the secret key
        try {
            // SECRET_KEY is defined above with minimum 32 characters
            jwsObject.sign(new MACSigner(SECRET_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Error generating token, can not create token", e);
            throw new JOSEException(e.getMessage());
        }
    }

}
