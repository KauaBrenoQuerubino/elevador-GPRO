package com.elevador_automatizados.elevador.Services;


import com.elevador_automatizados.elevador.Model.Elevador;
import com.elevador_automatizados.elevador.Model.ElevadorStatus;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;



@SpringBootTest
@ActiveProfiles("test")
@Transactional
class ElevadorServiceTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    ElevadorService service;


    @BeforeEach
    public void initElevadors(){
        Elevador elevador = new Elevador();
        elevador.setAndarAtual(0);
        elevador.setStatus(ElevadorStatus.PARADO.getValue());
        entityManager.persist(elevador);

        this.entityManager.persist(elevador);
    }


    //CASOS: CHAMAR ELEVADOR

    @Test
    @DisplayName("Chamar elevador para subir")
    void chamarCase1() throws Exception {

        int andarOrigem = 10;

        Optional<Elevador> result = service.chamar(andarOrigem, "Subir");

        assertThat(result.get().getAndarAtual() == andarOrigem
                && result.get().getStatus().equals(ElevadorStatus.PARADO.getValue()));

    }

    @Test
    @DisplayName("O elevador ja esta no andar")
    void chamarCase2() throws Exception {

        int andarOrigem = 0;
        Exception ex = assertThrows(Exception.class, () -> {
            Optional<Elevador> result = service.chamar(andarOrigem, "Subir");
        });


        assertThat(ex.getMessage().equals("Ja esta no andar"));

    }

    @Test
    @DisplayName("Pressionar subir no último andar")
    void chamarCase3() throws Exception {

        int andarOrigem = 14;

        Exception ex = assertThrows(Exception.class, () -> {
            Optional<Elevador> result = service.chamar(andarOrigem, "Subir");
        });

        assertThat(ex.getMessage().equals("Nao é possivel subir mais que o limite"));

    }


    //CASOS: SUBIR

}