package com.elevador_automatizados.elevador.Model;


import lombok.Getter;

@Getter
public enum ElevadorStatus {
    PARADO("Parado"),
    SUBINDO("Subindo"),
    DESCENDO("Descendo"),
    EM_FALHA("Em Falha");

    private final String Value;
    ElevadorStatus(String s) {
        this.Value = s;
    }
}
