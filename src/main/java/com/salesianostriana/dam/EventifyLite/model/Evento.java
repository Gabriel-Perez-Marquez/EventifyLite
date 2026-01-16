package com.salesianostriana.dam.EventifyLite.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private LocalDate fecha;
    private int aforoMax;
    private int entradasVendidas;

    @OneToMany(fetch = FetchType.LAZY)
    @Builder.Default
    List<Entrada> entradas = new ArrayList<>();


}
