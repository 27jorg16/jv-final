package pe.edu.cibertec.li_Auth_fn.service;

import pe.edu.cibertec.li_Auth_fn.dto.UsuarioDTO;

public interface IUsuarioService {
    UsuarioDTO findByCodigo(String codigo);
}
