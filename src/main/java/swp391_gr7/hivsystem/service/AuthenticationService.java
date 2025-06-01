package swp391_gr7.hivsystem.service;


import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.reponse.AuthenticationReponse;
import swp391_gr7.hivsystem.dto.request.AuthenticationRequest;
import swp391_gr7.hivsystem.repository.UserRepository;

import java.nio.charset.StandardCharsets;
import java.util.Date;

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
        boolean result =  passwordEncoder.matches(authenticationRequest.getPassword(), user.get().getPasswordHash());
        var token = generateToken(authenticationRequest.getUsername());
        return AuthenticationReponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }
    //Token has three component:header, payload, signature
    private String generateToken(String username) throws JOSEException {
       //Header of token
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("gr7")
                .issueTime(new Date())
                .expirationTime(new Date(new Date().getTime() + 1000 * 60 * 60 * 24))
                .claim("username", username)
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
