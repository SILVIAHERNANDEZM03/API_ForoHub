package com.alura.ForoHub.security;


import com.alura.ForoHub.model.Usuario;
import com.alura.ForoHub.repository.UsuarioRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class SecurityFilter extends OncePerRequestFilter{

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request);
        if(tokenJWT !=null){
            var subject = tokenService.getSubject(tokenJWT);

            var usuarioOptional = usuarioRepository.findByCorreoElectronico(subject);

            if (usuarioOptional.isPresent()) {
                var usuario = usuarioOptional.get();

                var authentication = new UsernamePasswordAuthenticationToken(
                        usuario, null, usuario.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null){
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}
