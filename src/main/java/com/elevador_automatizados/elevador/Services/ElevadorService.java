package com.elevador_automatizados.elevador.Services;

import com.elevador_automatizados.elevador.Model.Elevador;
import com.elevador_automatizados.elevador.Model.ElevadorStatus;
import com.elevador_automatizados.elevador.Repository.ElevadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ElevadorService {

    @Autowired
    private ElevadorRepository repository;



    public Elevador subir(int andarSelecionado){
        Optional<Elevador> elevador = repository.findById(1);

        if (!elevador.get().getStatus().equals("Parado")) {
            // elevador ocupado
            return elevador.orElse(null);
        }

        if (andarSelecionado <= elevador.get().getAndarAtual()) {
            return elevador.orElse(null);
        }

        try {
            elevador.get().setStatus(ElevadorStatus.SUBINDO.getValue());
            elevador.get().setAndarAtual(andarSelecionado);
            elevador.get().setStatus(ElevadorStatus.PARADO.getValue());

        } catch (Exception e) {
            elevador.get().setStatus(ElevadorStatus.EM_FALHA.getValue());
            throw e;
        }

        return repository.save(elevador.orElse(null));

    }

    public Optional<Elevador> chamar(int andarOrigem, String direcao) {
        Optional<Elevador> elevador = repository.findById(1);

        if (andarOrigem == elevador.get().getAndarAtual()) {
            return elevador; // já está no andar
        }

        if (direcao.equals("Subir")){
            try {
                    elevador.get().setStatus(ElevadorStatus.SUBINDO.getValue());
                    elevador.get().setAndarAtual(andarOrigem);
                    elevador.get().setStatus(ElevadorStatus.PARADO.getValue());

                } catch (Exception e) {
                    elevador.get().setStatus(ElevadorStatus.EM_FALHA.getValue());
                    throw e;
            }
        }else {
            try {
                elevador.get().setStatus(ElevadorStatus.DESCENDO.getValue());
                elevador.get().setAndarAtual(andarOrigem);
                elevador.get().setStatus(ElevadorStatus.PARADO.getValue());

            } catch (Exception e) {
                elevador.get().setStatus(ElevadorStatus.EM_FALHA.getValue());
                throw e;
            }
        }

        repository.save(elevador.orElse(null));

        return elevador;
    }


}
