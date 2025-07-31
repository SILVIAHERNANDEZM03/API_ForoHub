package com.alura.ForoHub.controller;

import com.alura.ForoHub.dto.DatosLogin;
import com.alura.ForoHub.model.Usuario;
import com.alura.ForoHub.security.DatosTokenJWT;
import com.alura.ForoHub.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosLogin datos){
        var authenticationToken = new UsernamePasswordAuthenticationToken(datos.login(), datos.contrasena());
        var autentificacion = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.generarToken((Usuario) autentificacion.getPrincipal());

        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
    }

}



