package swp391_gr7.hivsystem.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.model.Customers;
import swp391_gr7.hivsystem.model.Users;
import swp391_gr7.hivsystem.repository.CustomerRepository;
import swp391_gr7.hivsystem.repository.UserRepository;

import java.security.Key;
import java.util.*;

@Service
public class JWTService {
    private static final String SECRET_KEY = "secret_key_is_very_long_and_secure_sum25_swp391_hiv";

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
    public boolean isTokenValid(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(SECRET_KEY.getBytes())
                    .build()
                    .parseClaimsJws(token);

            // Check expiration
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();

        String role = claims.get("role", String.class);
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + role)
        );

        return new UsernamePasswordAuthenticationToken(null, token, authorities);
    }
}
