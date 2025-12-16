package com.elevador_automatizados.elevador.Config;

import com.elevador_automatizados.elevador.Model.Elevador;
import com.elevador_automatizados.elevador.Repository.ElevadorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElevadorInitializer {

    @Bean
    CommandLineRunner initElevador(ElevadorRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                Elevador e = new Elevador();
                e.setAndarAtual(0);
                e.setStatus("Parado");
                repository.save(e);
            }
        };
    }
}

