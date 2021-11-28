package br.ufpb.dsc.lab3.controladores;

import br.ufpb.dsc.lab3.dtos.UsuarioDTO;
import br.ufpb.dsc.lab3.entidades.Usuario;
import br.ufpb.dsc.lab3.excecoes.UsuarioInvalidoException;
import br.ufpb.dsc.lab3.excecoes.UsuarioJaExisteException;
import br.ufpb.dsc.lab3.servicos.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioServico usuarioServico;

    @PostMapping
    public ResponseEntity<UsuarioDTO> criaUsuario(@RequestBody Usuario usuario) {
        try {
            return new ResponseEntity<UsuarioDTO>(usuarioServico.criaUsuario(usuario), HttpStatus.CREATED);
        } catch (UsuarioJaExisteException uee) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (UsuarioInvalidoException uie) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<UsuarioDTO> deletaUsuario(@RequestHeader("Authorization") String token) {
        try {
            return new ResponseEntity<>(usuarioServico.deletaUsuario(token), HttpStatus.OK);
        } catch (UsuarioJaExisteException uee) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (UsuarioInvalidoException uie) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

}