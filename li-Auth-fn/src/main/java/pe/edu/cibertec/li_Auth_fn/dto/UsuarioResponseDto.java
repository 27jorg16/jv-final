package pe.edu.cibertec.li_Auth_fn.dto;


import lombok.Data;


@Data
public class UsuarioResponseDto {
    private Integer idusuario;
    private String codigo;
    private String token;
}