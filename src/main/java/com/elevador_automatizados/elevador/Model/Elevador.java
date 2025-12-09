package com.elevador_automatizados.elevador.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Elevador {
    private int andarAtual;
    private int andarDestino;
    private String status;

}
