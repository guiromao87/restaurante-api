package br.com.tex.restauranteapi.controller;

import br.com.tex.restauranteapi.model.Usuario;
import br.com.tex.restauranteapi.model.dto.TokenDto;
import br.com.tex.restauranteapi.model.dto.UsuarioLoginDto;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;

@RestController
public class UsuarioController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${spring.secret.jwt}")
    private String secret;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UsuarioLoginDto loginDto) {
        var user = new UsernamePasswordAuthenticationToken(loginDto.login(), loginDto.senha());
        var usuario = (Usuario)authenticationManager.authenticate(user).getPrincipal();

        return ResponseEntity.ok(
                new TokenDto("Bearer",JWT.create()
                .withIssuer("Restaurante API")
                .withSubject(usuario.getLogin())
                .sign(Algorithm.HMAC256(secret))));
    }
}
