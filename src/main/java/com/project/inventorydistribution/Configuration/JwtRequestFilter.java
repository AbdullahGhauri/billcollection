package com.project.inventorydistribution.Configuration;

import com.project.inventorydistribution.Configuration.Jwtutil;
import com.project.inventorydistribution.Exceptions.InvalidTokenException;
import com.project.inventorydistribution.Exceptions.MissingTokenException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    Jwtutil jwtutil;

    @Autowired
    UserDetailsService userDetailsService;

    private final String INVALID_JWT_TOKEN = "Invalid JWT token";
    private final String MISSING_HEADER_AUTH = "Missing Authorization header";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        System.out.println("jwtrequestfilter "+authorizationHeader);
        String username = null;
        String jwt = null;

        if(authorizationHeader !=null && authorizationHeader.startsWith("Bearer ")){
            try{
            jwt = authorizationHeader.substring(7);
            username = jwtutil.extractUsername(jwt);
        } catch (Exception e) {
            throw new InvalidTokenException(INVALID_JWT_TOKEN);
        }
        }else {
            throw new MissingTokenException(MISSING_HEADER_AUTH);
        }
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if(jwtutil.validateToken(jwt,userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities()
                );
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            else{
                throw new InvalidTokenException(INVALID_JWT_TOKEN);
            }
        }
        

        filterChain.doFilter(request,response);

    }
}
