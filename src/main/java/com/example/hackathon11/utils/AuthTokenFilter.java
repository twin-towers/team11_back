package com.example.hackathon11.utils;

import com.example.hackathon11.config.CustomUserDetailsService;
import com.example.hackathon11.constants.CustomConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter implements CustomConstants {

    private final JwtTokenUtil jwtTokenUtil;
    private final CustomUserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getTokenFromJwt(request);
            if (token != null && jwtTokenUtil.validateJwtToken(token).equals("")) {

                String username = jwtTokenUtil.getUsernameFromToken(token);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        catch (Exception ignored) {
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader(AUTHORIZATION);

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(BEARER)) {
            return headerAuth.substring(7);
        }

        return null;
    }
}
