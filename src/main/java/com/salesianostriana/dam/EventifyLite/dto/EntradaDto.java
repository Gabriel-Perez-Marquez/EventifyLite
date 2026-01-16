package com.salesianostriana.dam.EventifyLite.dto;

import com.salesianostriana.dam.EventifyLite.model.Entrada;
import com.salesianostriana.dam.EventifyLite.model.Estado;

import java.time.LocalDateTime;

public record EntradaDto(LocalDateTime fechaCompra, Estado estado, String tituloEvento, String nomreAsistente) {

    public static EntradaDto of(Entrada e){
        return new EntradaDto(
                e.getFechaCompra(),
                e.getEstado(),
                e.getEvento().getTitulo(),
                e.getAsistente().getNombre()
        );
    }

}
