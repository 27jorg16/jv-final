package pe.edu.cibertec.app_server_oauth.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.app_server_oauth.service.UsuarioService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/oauth2")
public class AuthController {

    private final AuthenticationManager authenticationManager;


    private final UsuarioService usuarioService;

    private final String SECRET_KEY = "tu_clave_secreta"; // Cambia esto por una clave más segura
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hora

    @PostMapping("/token")
    public ResponseEntity<Map<String, String>> authenticate(@RequestParam String codigo, @RequestParam String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(codigo, password)
            );

            // Generar el token JWT
            String token = generarToken(authentication);
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("tipo", "Bearer");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("error", "Credenciales inválidas"));
        }
    }

    private String generarToken(Authentication authentication) {
        UserDetails userDetails = usuarioService.loadUserByUsername(authentication.getName());
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
