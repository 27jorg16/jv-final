package pe.edu.cibertec.li_Auth_fn.dto;

import lombok.Data;

@Data
public class UsuarioDTO {

    private Long id;
    private String codigo;
    private String email;
    private String password;
    private String rol;
    private Boolean activo;

}
