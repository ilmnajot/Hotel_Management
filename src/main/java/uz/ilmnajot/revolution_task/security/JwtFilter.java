package uz.ilmnajot.revolution_task.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.ilmnajot.revolution_task.utils.RestConstant;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {


    private final JwtProvider jwtProvider;
    private final UserDetailsService userDetailsService;

    public JwtFilter(JwtProvider jwtProvider, UserDetailsService userDetailsService) {
        this.jwtProvider = jwtProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("api/v1/auth")){
            filterChain.doFilter(request, response);
            return;
        }
        final String authHeader = request.getHeader(RestConstant.AUTHORIZATION_HEADER);
        final String username;
        final String jwt;
        if (authHeader == null || !authHeader.startsWith(RestConstant.TOKEN_TYPE)) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        username = jwtProvider.extractUsername(jwt);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            try {
                if (jwtProvider.isTokenValid(userDetails, jwt)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }catch (Exception exception){
                throw new ServletException(exception.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }
}
