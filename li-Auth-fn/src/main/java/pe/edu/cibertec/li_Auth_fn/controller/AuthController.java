package pe.edu.cibertec.li_Auth_fn.controller;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.li_Auth_fn.dto.UsuarioDTO;
import pe.edu.cibertec.li_Auth_fn.dto.UsuarioResponseDto;
import pe.edu.cibertec.li_Auth_fn.service.IUsuarioService;
import pe.edu.cibertec.li_Auth_fn.service.impl.DetailsService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final DetailsService detailsService;
    private final AuthenticationManager authenticationManager;
    private final IUsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponseDto> autenticarUsuario(
            @RequestParam("usuario") String codigo,
            @RequestParam("password") String password
    ) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(codigo, password));
            if (authentication.isAuthenticated()) {
                UsuarioDTO objUsuario = usuarioService.findByCodigo(codigo);
                String token = generarToken(objUsuario);
                UsuarioResponseDto response = new UsuarioResponseDto();
                response.setIdusuario(objUsuario.getId().intValue());
                response.setCodigo(objUsuario.getCodigo());
                response.setToken(token);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String generarToken(UsuarioDTO usuario) {
        String clave = "@Libreria2024";
        List<GrantedAuthority> authorityList = List.of(new SimpleGrantedAuthority(usuario.getRol())); // Cambia según tu lógica de roles
        return Jwts.builder()
                .setId(usuario.getId().toString())
                .setSubject(usuario.getCodigo())
                .claim("authorities", authorityList.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS512, clave.getBytes())
                .compact();
    }
}
