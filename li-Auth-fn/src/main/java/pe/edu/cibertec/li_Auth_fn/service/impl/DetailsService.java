package pe.edu.cibertec.li_Auth_fn.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.li_Auth_fn.dto.UsuarioDTO;
import pe.edu.cibertec.li_Auth_fn.service.IUsuarioService;

import java.util.Collections;


@RequiredArgsConstructor
@Service
public class DetailsService implements UserDetailsService {
    private final IUsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String codigo) throws UsernameNotFoundException {
        UsuarioDTO usuarioDTO = usuarioService.findByCodigo(codigo);
        if (usuarioDTO == null || !usuarioDTO.getActivo()) {
            throw new UsernameNotFoundException("Usuario no encontrado o inactivo");
        }
        return new org.springframework.security.core.userdetails.User(
                usuarioDTO.getCodigo(),
                usuarioDTO.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(usuarioDTO.getRol()))
        );
    }

}

