package br.ufpb.dsc.lab3.dtos;

import br.ufpb.dsc.lab3.entidades.Disciplina;
import lombok.Data;

@Data
public class DisciplinaNotasDTO {
    private Long id;
    private String nome;
    private double nota;

    public DisciplinaNotasDTO(Disciplina disciplina) {
        this.id = disciplina.getId();
        this.nome = disciplina.getNome();
        this.nota = disciplina.getNota();
    }
}