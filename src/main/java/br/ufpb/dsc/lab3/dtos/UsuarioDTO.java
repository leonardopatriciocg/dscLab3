package br.ufpb.dsc.lab3.dtos;

import br.ufpb.dsc.lab3.entidades.Usuario;
import lombok.Data;

@Data
public class UsuarioDTO {
    private String email;
    private String senha;

    public UsuarioDTO(Usuario usuario) {
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
    }
}