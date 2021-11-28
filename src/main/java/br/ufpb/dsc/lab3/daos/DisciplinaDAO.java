package br.ufpb.dsc.lab3.daos;

import br.ufpb.dsc.lab3.entidades.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplinaDAO extends JpaRepository<Disciplina, Long> {
    List<Disciplina> findByOrderByNota();
    List<Disciplina> findByOrderByCurtidas();
}