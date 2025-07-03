package swp391_gr7.hivsystem.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.model.Customers;
import swp391_gr7.hivsystem.model.Users;
import swp391_gr7.hivsystem.repository.CustomerRepository;
import swp391_gr7.hivsystem.repository.UserRepository;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class JWTService {
    private static final String SECRET_KEY = "secret_la_bi_mat_thoi-lam-on-chay-dum-tao";

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;

    public JWTService(UserRepository userRepository, CustomerRepository customerRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
    }

    public String generateToken(Authentication authentication, boolean registered) {
        long expirationMillis = 1000 * 60 * 60;
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");

        Map<String, Object> claims = new HashMap<>();
        claims.put("iss", "gr7");
        claims.put("sub", email);
        claims.put("email", email);
        if (registered) {
            Optional<Users> optionalUser = userRepository.findByEmail(email);
            if (optionalUser.isPresent()) {
                Users user = optionalUser.get();
                Customers customer = customerRepository.findByUsers(user);
                if (customer != null) {
                    claims.put("customerId", customer.getCustomerId());
                }
            }
            claims.put("sub", "customer");
            claims.put("role", "Customer");
        } else {
            claims.put("role", "Guest");
        }

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
