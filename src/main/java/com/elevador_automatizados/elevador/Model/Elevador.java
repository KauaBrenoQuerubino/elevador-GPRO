package com.elevador_automatizados.elevador.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "elevador")
public class Elevador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int andarAtual;
    private String status;

}
