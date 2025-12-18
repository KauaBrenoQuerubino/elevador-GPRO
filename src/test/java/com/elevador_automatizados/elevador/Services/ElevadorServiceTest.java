package com.elevador_automatizados.elevador.Services;


import com.elevador_automatizados.elevador.Model.Elevador;
import com.elevador_automatizados.elevador.Model.ElevadorStatus;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
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


    public Elevador initElevadors(){
        Elevador elevador = new Elevador();

        elevador.setAndarAtual(0);
        elevador.setStatus(ElevadorStatus.PARADO.getValue());

        this.entityManager.persist(elevador);

        return elevador;
    }


    //CASOS: CHAMAR ELEVADOR

    @Test
    @DisplayName("Chamar elevador para subir")
    void chamarCase1() {

        int andarOrigem = 10;

        Optional<Elevador> result = service.chamar(andarOrigem, "Subir");

        assertThat(result.get().getAndarAtual() == andarOrigem
                && result.get().getStatus().equals(ElevadorStatus.PARADO.getValue()));

    }

    @Test
    @DisplayName("O elevador ja esta no andar")
    void chamarCase2() {

        int andarOrigem = 0;

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            Optional<Elevador> result = service.chamar(andarOrigem, "Subir");
        });


        assertThat(ex.getMessage()).isEqualTo("Ja esta no andar");

    }


    @Test
    @DisplayName("Pressionar subir no último andar")
    void chamarCase3()  {

        int andarOrigem = 14;

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            Optional<Elevador> result = service.chamar(andarOrigem, "Subir");
        });

        assertThat(ex.getMessage()).isEqualTo("Nao é possivel subir mais que o limite");

    }

    @Test
    @DisplayName("Chamar elevador em andar inválido")
    void chamarCase4()  {

        int andarOrigem = 15;

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            Optional<Elevador> result = service.chamar(andarOrigem, "Subir");
        });

        assertThat(ex.getMessage()).isEqualTo("Andar nao existente");

    }


    //CASOS: SUBIR


    @Test
    @DisplayName("Subir para andar superior")
    void subirCase1() {

        int andarOrigem = 10;

        Elevador result = service.subir(andarOrigem);

        assertThat(result.getAndarAtual() == andarOrigem
                && result.getStatus().equals(ElevadorStatus.PARADO.getValue()));

    }

    @Test
    @DisplayName("Selecionar andar inferior ao subir")
    void subirCase2() {

        int andarOrigem = 10;

        service.subir(andarOrigem);

        int finalAndarOrigem = 9;

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            Elevador result = service.subir(finalAndarOrigem);
        });

        assertThat(ex.getMessage()).isEqualTo ("O andar nao pode ser menor que o atual");
    }

    @Test
    @DisplayName("Selecionar o mesmo andar")
    void subirCase3() {

        int andarOrigem = 0;

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            Elevador result = service.subir(andarOrigem);
        });

        assertThat(ex.getMessage()).isEqualTo ("Ja esta no andar");
    }

}