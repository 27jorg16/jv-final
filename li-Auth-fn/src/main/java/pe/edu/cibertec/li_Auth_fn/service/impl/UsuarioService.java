package pe.edu.cibertec.li_Auth_fn.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.li_Auth_fn.dto.UsuarioDTO;
import pe.edu.cibertec.li_Auth_fn.model.Usuario;
import pe.edu.cibertec.li_Auth_fn.repository.UsuarioRepository;
import pe.edu.cibertec.li_Auth_fn.service.IUsuarioService;
import pe.edu.cibertec.li_Auth_fn.util.convert.UsuarioConvert;

@RequiredArgsConstructor
@Service
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConvert usuarioConvert;

    @Override
    @CircuitBreaker(name = "usuarioService", fallbackMethod = "fallbackObtenerUsuarioPorCodigo")
    public UsuarioDTO findByCodigo(String codigo) {
        Usuario usuario = usuarioRepository.findByCodigo(codigo);
        return usuarioConvert.convertirAUsuarioDTO(usuario);
    }
}
