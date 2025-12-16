package com.elevador_automatizados.elevador.Controller;


import com.elevador_automatizados.elevador.DTO.PainelExteriorDTO;
import com.elevador_automatizados.elevador.DTO.PainelInteriorDTO;
import com.elevador_automatizados.elevador.Model.Elevador;
import com.elevador_automatizados.elevador.Services.ElevadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("elevador")
public class ElevadorController {
    //TODO: Definir os Feedbacks ao usuario
    @Autowired
    private ElevadorService service;

    @PostMapping("/chamar")
    public ResponseEntity<?> chamarElevador(@RequestBody PainelExteriorDTO dto){

        try {
            Optional<Elevador> result = service.chamar(dto.getAndarOrigem(), dto.getDirecaoSolicitada());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }

        return ResponseEntity.ok().body("Abrir Porta");
    }

    @PostMapping("/ir")
    public ResponseEntity<?> subir(@RequestBody PainelInteriorDTO dto){
        try {
            service.subir(dto.getAndar());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }

        return ResponseEntity.ok().body("Voce chegou ao seu destino");
    }


}
