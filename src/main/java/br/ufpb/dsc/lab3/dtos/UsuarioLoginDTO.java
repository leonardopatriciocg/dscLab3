package br.ufpb.dsc.lab3.dtos;

import br.ufpb.dsc.lab3.entidades.Usuario;
import lombok.Data;

@Data
public class UsuarioLoginDTO {
    private String email;
    private String senha;


    public UsuarioLoginDTO(){}

    public UsuarioLoginDTO(Usuario usuario){
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
    }
}