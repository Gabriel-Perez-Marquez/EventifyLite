package com.salesianostriana.dam.EventifyLite.service;

import com.salesianostriana.dam.EventifyLite.dto.CreateEntradaRequest;
import com.salesianostriana.dam.EventifyLite.model.Asistente;
import com.salesianostriana.dam.EventifyLite.model.Entrada;
import com.salesianostriana.dam.EventifyLite.model.Estado;
import com.salesianostriana.dam.EventifyLite.model.Evento;
import com.salesianostriana.dam.EventifyLite.repository.AsistenteRepository;
import com.salesianostriana.dam.EventifyLite.repository.EntradaRepository;
import com.salesianostriana.dam.EventifyLite.repository.EventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntradaService {

    private final EntradaRepository entradaRepository;
    private final EventoRepository eventoRepository;
    private final AsistenteRepository asistenteRepository;


    public Entrada comprarEntrada (CreateEntradaRequest dto) {
        Evento e = eventoRepository.findById(dto.idEvento()).orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        Asistente a = asistenteRepository.findById(dto.idAsistente()).orElseThrow(() -> new RuntimeException("Asistente no encontrado"));


        if(e.getEntradasVendidas() >= e.getAforoMax()){
            throw new RuntimeException("El número de entradas vendidas es igual al aforo máximo. No hay entradas disponibles.");
        }

        e.setEntradasVendidas(e.getEntradasVendidas() + 1);

        eventoRepository.save(e);

        return entradaRepository.save(Entrada.builder()
                        .fechaCompra(dto.fechaCompra())
                        .estado(dto.estado())
                        .evento(e)
                        .asistente(a)
                        .build());
    }


    public Entrada cancelarEntrada (Long idEntrada){
        Entrada e = entradaRepository.findById(idEntrada).orElseThrow(() -> new RuntimeException("Entrada no encontrada"));

        if(e.getEstado() == Estado.CANCELADA){
            throw new RuntimeException("No se puede cancelar una entrada ya cancelada");
        }

        e.getEvento().setEntradasVendidas(e.getEvento().getEntradasVendidas() - 1);

        eventoRepository.save(e.getEvento());

        e.setEstado(Estado.CANCELADA);

        return entradaRepository.save(e);
    }

    public Page<Entrada> mostrarEntradas (Pageable pageable){
        return entradaRepository.findAll(pageable);
    }



}
