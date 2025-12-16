package com.elevador_automatizados.elevador.Services;

import com.elevador_automatizados.elevador.Model.Elevador;
import com.elevador_automatizados.elevador.Model.ElevadorStatus;
import com.elevador_automatizados.elevador.Repository.ElevadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ElevadorService {

    //TODO:melhorar os retornos dos erros.

    @Autowired
    private ElevadorRepository repository;

    public Elevador subir(int andarSelecionado) throws Exception {
        Optional<Elevador> elevador = repository.findById(1);

        if (!elevador.get().getStatus().equals("Parado")) {
            // elevador ocupado
            throw new Exception("Elevador em uso");
        }

        if (andarSelecionado <= elevador.get().getAndarAtual()){
            // elevador ocupado
            throw new Exception("O andar nao pode ser menor que o atual");
        }

        if (andarSelecionado > 14) {
            throw new Exception("Nao ha mais andares no predio");
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

    public Optional<Elevador> chamar(int andarOrigem, String direcao) throws Exception {
        Optional<Elevador> elevador = repository.findById(1);

        if (andarOrigem == elevador.get().getAndarAtual()) {
            throw new Exception("Ja esta no andar");// já está no andar
        }

        if (andarOrigem > 14) {
            throw new Exception("Andar nao existente");
        }

        if (direcao.equals("Subir")){

            if (andarOrigem >= 14) {
                throw new Exception("Nao é possivel subir mais que o limite");
            }

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
