package com.example.GestioneDispositiviAziendali.sicurity;

import com.example.GestioneDispositiviAziendali.exceptionHandler.NotFoundException;
import com.example.GestioneDispositiviAziendali.exceptionHandler.UnAuthorizedException;
import com.example.GestioneDispositiviAziendali.model.entities.User;
import com.example.GestioneDispositiviAziendali.service.UserSvc;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTools jwtTools;
    @Autowired
    private UserSvc userSvc;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if(authorization==null||!authorization.startsWith("Bearer ")){
            throw new UnAuthorizedException("Token non presente");
        }

        String token = authorization.substring(7);

        jwtTools.validateToken(token);

        String username = jwtTools.extractUsernameFromToken(token);

        User utente = null;
        try {
            utente = userSvc.getUserByUserName(username);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(utente,
                null,utente.getAuthorities());
        System.out.println(authorization);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request,response);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String servletPath = request.getServletPath();
        AntPathMatcher pathMatcher = new AntPathMatcher();

        return pathMatcher.match("/auth/**",servletPath);
    }
}
