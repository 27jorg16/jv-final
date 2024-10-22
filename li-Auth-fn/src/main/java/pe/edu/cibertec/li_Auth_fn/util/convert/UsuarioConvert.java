package pe.edu.cibertec.li_Auth_fn.util.convert;

import org.springframework.stereotype.Component;
import pe.edu.cibertec.li_Auth_fn.dto.UsuarioDTO;
import pe.edu.cibertec.li_Auth_fn.model.Usuario;

@Component
public class UsuarioConvert {

    public UsuarioDTO convertirAUsuarioDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setCodigo(usuario.getCodigo());
        dto.setEmail(usuario.getEmail());
        dto.setRol(usuario.getRol());
        dto.setActivo(usuario.getActivo());
        return dto;
    }
}
