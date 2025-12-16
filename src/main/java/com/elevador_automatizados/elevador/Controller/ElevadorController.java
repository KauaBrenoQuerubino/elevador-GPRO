package com.elevador_automatizados.elevador.Controller;


import com.elevador_automatizados.elevador.DTO.PainelExteriorDTO;
import com.elevador_automatizados.elevador.DTO.PainelInteriorDTO;
import com.elevador_automatizados.elevador.Services.ElevadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("elevador")
public class ElevadorController {

    @Autowired
    private ElevadorService service;

    @PostMapping("/chamar")
    public void chamarElevador(@RequestBody PainelExteriorDTO dto){
        service.chamar(dto.getAndarOrigem(), dto.getDirecaoSolicitada());
    }

    @PostMapping("/ir")
    public void subir(@RequestBody PainelInteriorDTO dto){
        service.subir(dto.getAndar());
    }


}
