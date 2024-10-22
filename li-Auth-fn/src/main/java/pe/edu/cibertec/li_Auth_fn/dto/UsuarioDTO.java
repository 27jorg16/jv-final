package pe.edu.cibertec.li_Auth_fn.dto;

import jdk.jshell.Snippet;
import lombok.Data;

@Data
public class UsuarioDTO {

    private Long id;
    private String codigo;
    private String email;
    private String rol;
    private Boolean activo;

}
