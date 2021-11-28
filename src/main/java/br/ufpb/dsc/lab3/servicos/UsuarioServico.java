package br.ufpb.dsc.lab3.servicos;

import br.ufpb.dsc.lab3.daos.UsuarioDAO;
import br.ufpb.dsc.lab3.dtos.UsuarioDTO;
import br.ufpb.dsc.lab3.entidades.Usuario;
import br.ufpb.dsc.lab3.excecoes.UsuarioInvalidoException;
import br.ufpb.dsc.lab3.excecoes.UsuarioJaExisteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import java.util.Optional;

@Service
public class UsuarioServico {

    @Autowired
    private UsuarioDAO repositorioDeUsuarios;
    @Autowired
    private JWTServico jwtServico;

    public UsuarioDTO criaUsuario(Usuario usuario) {
        if (repositorioDeUsuarios.findByEmail(usuario.getEmail()).isPresent())
            throw new UsuarioJaExisteException();
        if (!usuario.isValid())
            throw new UsuarioInvalidoException();
        repositorioDeUsuarios.save(usuario);
        return new UsuarioDTO(usuario);
    }

    public Usuario getUsuario(String email) {
        Optional<Usuario> optUsuario = repositorioDeUsuarios.findByEmail(email);
        if(optUsuario.isEmpty())
            throw new IllegalArgumentException();
        return optUsuario.get();
    }
    private boolean usuarioTemPermissao(String authorizationHeader, String email) throws ServletException {
        String subject = jwtServico.getSujeitoDoToken(authorizationHeader);
        Optional<Usuario> optUsuario = repositorioDeUsuarios.findByEmail(subject);
        return optUsuario.isPresent() && optUsuario.get().getEmail().equals(email);
    }

    public UsuarioDTO deletaUsuario(String cabecalhoDeAutorizacao) {
        Optional<String> usuarioId = jwtServico.recuperaUsuario(cabecalhoDeAutorizacao);

        Usuario usuario = validaUsuario(usuarioId);
        repositorioDeUsuarios.delete(usuario);
        return new UsuarioDTO(usuario);
    }

    private Usuario validaUsuario(Optional<String> id) {
        if(id.isEmpty()) throw new UsuarioInvalidoException();
        Optional<Usuario> usuario = repositorioDeUsuarios.findByEmail(id.get());
        if (usuario.isEmpty()) throw new UsuarioInvalidoException();
        return usuario.get();
    }
}