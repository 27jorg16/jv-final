package pe.edu.cibertec.li_Auth_fn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.li_Auth_fn.dto.UsuarioDTO;
import pe.edu.cibertec.li_Auth_fn.service.IUsuarioService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final IUsuarioService iUsuarioService;

    @GetMapping("/{codigo}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorCodigo(@PathVariable String codigo) {
        UsuarioDTO usuario = iUsuarioService.findByCodigo(codigo);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> manejarExcepciones(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor: " + e.getMessage());
    }
}
