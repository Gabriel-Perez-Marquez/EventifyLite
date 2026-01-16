package com.salesianostriana.dam.EventifyLite.dto;

import com.salesianostriana.dam.EventifyLite.model.Estado;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateEntradaRequest(LocalDateTime fechaCompra,
                                   Estado estado, Long idAsistente, Long idEvento ) {
}
