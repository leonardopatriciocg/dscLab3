package br.ufpb.dsc.lab3.daos;

import br.ufpb.dsc.lab3.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioDAO extends JpaRepository<Comentario, Long> {

}