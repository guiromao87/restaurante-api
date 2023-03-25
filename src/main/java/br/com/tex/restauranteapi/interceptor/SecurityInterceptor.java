package br.com.tex.restauranteapi.interceptor;

import br.com.tex.restauranteapi.model.Usuario;
import br.com.tex.restauranteapi.repository.UsuarioRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityInterceptor extends OncePerRequestFilter {

    @Value("${spring.secret.jwt}")
    private String secret;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var header = request.getHeader("Authorization");

        if(header != null) {
            var token = header.replace("Bearer ", "");
            try {
                var login = JWT.require(Algorithm.HMAC256(secret))
                        .withIssuer("Restaurante API")
                        .build()
                        .verify(token)
                        .getSubject();

                var usuario = this.usuarioRepository.findByLogin(login);
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (JWTVerificationException exception){
                throw new RuntimeException("Token inválido");
            }
        }

        filterChain.doFilter(request, response);
    }
}
