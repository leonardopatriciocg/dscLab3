package br.ufpb.dsc.lab3.dtos;

import br.ufpb.dsc.lab3.entidades.Comentario;
import br.ufpb.dsc.lab3.entidades.Disciplina;
import lombok.Data;

import java.util.List;

@Data
public class DisciplinaComentarioDTO {
    private Long id;
    private String nome;
    private List<Comentario> comentarios;

    public DisciplinaComentarioDTO(Disciplina disciplina) {
        this.id = disciplina.getId();
        this.nome = disciplina.getNome();
        this.comentarios = disciplina.getComentarios();
    }
}