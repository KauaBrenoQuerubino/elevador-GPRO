package com.elevador_automatizados.elevador.Repository;

import com.elevador_automatizados.elevador.Model.Elevador;
import com.elevador_automatizados.elevador.Model.ElevadorStatus;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.*;

import java.util.Optional;


@DataJpaTest
@ActiveProfiles("test")
class ElevadorRepositoryTest {

    @Autowired
    ElevadorRepository repository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Retorne o elevador se existir")
    void findByIdSucess() {

        Elevador dto = iniciaElevador();

        Optional<Elevador> result = this.repository.findById(dto.getId());

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Retorne o elevador se nao existir")
    void findByIdFaild() {

        Elevador dto = new Elevador();

        Optional<Elevador> result = this.repository.findById(1);

        assertThat(result.isEmpty()).isTrue();
    }


    private Elevador iniciaElevador(){

        Elevador elevador = new Elevador();

        elevador.setAndarAtual(0);
        elevador.setStatus(ElevadorStatus.PARADO.getValue());

        this.entityManager.persist(elevador);
        return elevador;
    }
}