package swp391_gr7.hivsystem.config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import swp391_gr7.hivsystem.service.TokenBlacklistService;
import swp391_gr7.hivsystem.service.JWTService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final TokenBlacklistService tokenBlacklistService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        String token = null;

        // 1. Lấy token từ header
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }

        // 2. Nếu không có token -> bỏ qua
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Kiểm tra token có nằm trong blacklist không
        if (tokenBlacklistService.isBlacklisted(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token is blacklisted");
            return;
        }

        // 4. Xác thực token và set Authentication
        try {
            if (jwtService.isTokenValid(token)) {
                var authentication = jwtService.getAuthentication(token);

                // Ép kiểu để gọi được setDetails()
                if (authentication instanceof UsernamePasswordAuthenticationToken authToken) {
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid token");
        }


        // 5. Tiếp tục filter chain
        filterChain.doFilter(request, response);
    }
}
