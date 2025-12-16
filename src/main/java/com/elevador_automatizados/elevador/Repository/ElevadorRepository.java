package com.elevador_automatizados.elevador.Repository;

import com.elevador_automatizados.elevador.Model.Elevador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ElevadorRepository extends JpaRepository<Elevador, Integer> {

    Elevador save(Elevador elevador);

    Optional<Elevador> findById(Integer integer);

}

