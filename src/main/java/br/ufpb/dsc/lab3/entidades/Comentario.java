package br.ufpb.dsc.lab3.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Comentario implements Serializable {
    private static final long serialVersionUID = 1265022599564907145L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String texto;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="disciplina_id")
    private Disciplina disciplina;

    public Comentario(Disciplina disciplina, String texto){
        super();
    }

    public Comentario(String texto) {
        this.texto = texto;
    }

    public Comentario() {
        super();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

}