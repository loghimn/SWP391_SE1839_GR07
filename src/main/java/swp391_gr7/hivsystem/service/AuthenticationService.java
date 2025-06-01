package swp391_gr7.hivsystem.service;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;

import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import swp391_gr7.hivsystem.dto.reponse.AuthenticationReponse;
import swp391_gr7.hivsystem.dto.request.AuthenticationRequest;
import swp391_gr7.hivsystem.model.User;
import swp391_gr7.hivsystem.repository.UserRepository;

<<<<<<< HEAD
=======
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;

>>>>>>> 3684022 (Update user-related logic and security config)
@Slf4j
@Service
public class AuthenticationService {
    @NonFinal
    private static final String SECRET_KEY = "secret_la_bi_mat_thoi-lam-on-chay-dum-tao"; // tu tao
    @Autowired
    UserRepository userRepository;
   public AuthenticationReponse authenticate(AuthenticationRequest authenticationRequest)
           throws JOSEException {
        var user = userRepository.findByUsername(authenticationRequest.getUsername());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean result =  passwordEncoder.matches(authenticationRequest.getPassword(), user.get().getPassword());
        var token = generateToken(user.get().getUserId());
        return AuthenticationReponse.builder()
                .token(token)
                .authenticated(result)
                .build();
    }
    //Token has three component:header, payload, signature
    private String generateToken(int userId) throws JOSEException {
       //Header of token
        User user = userRepository.findByUserId(userId);
        String role = user.getRole();
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("gr7")
                .issueTime(new Date())
                .expirationTime(new Date(new Date().getTime() + 1000 * 60 * 60 * 24))
                .claim("role", role)
                .build();
        //Create payload
        Payload payload = new Payload(claimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        //Add signature
        try{
            jwsObject.sign(new MACSigner(SECRET_KEY.getBytes()));
            return jwsObject.serialize();
        }catch (JOSEException e){
            log.error("Error generating token", e);
            throw new JOSEException(e.getMessage());
        }

    }
}
