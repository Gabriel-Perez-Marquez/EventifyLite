package com.salesianostriana.dam.EventifyLite.controller;


import com.salesianostriana.dam.EventifyLite.dto.CreateEntradaRequest;
import com.salesianostriana.dam.EventifyLite.dto.EntradaDto;
import com.salesianostriana.dam.EventifyLite.model.Entrada;
import com.salesianostriana.dam.EventifyLite.service.EntradaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/entradas")
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public class EntradaController {


    private final EntradaService entradaService;

    @GetMapping("")
    public Page<EntradaDto> mostrarEntradas (@PageableDefault(page = 0, size = 20) Pageable pageable) {
        return entradaService.mostrarEntradas(pageable).map(EntradaDto::of);
    }

    @PutMapping("/{id}/cancelar")
    public EntradaDto cancelarEntrada (@PathVariable(name = "id") Long idEntrada){
        return EntradaDto.of(entradaService.cancelarEntrada(idEntrada));
    }

    @PostMapping("")
    public EntradaDto comprarEntrada(CreateEntradaRequest dto) {
        return EntradaDto.of(entradaService.comprarEntrada(dto));
    }


}
