package com.salesianostriana.dam.EventifyLite.repository;

import com.salesianostriana.dam.EventifyLite.model.Asistente;
import com.salesianostriana.dam.EventifyLite.model.Entrada;
import com.salesianostriana.dam.EventifyLite.model.Estado;
import com.salesianostriana.dam.EventifyLite.model.Evento;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EntradaRepository extends JpaRepository<Entrada, Long> {


    List<Entrada> findByAsistenteAndEstado(Asistente asistente, Estado estado);

    @Query("select e from Entrada e where e.evento = ?1")
    @EntityGraph(attributePaths = "asistente")
    List<Entrada> findByEvento(Evento evento);


}
