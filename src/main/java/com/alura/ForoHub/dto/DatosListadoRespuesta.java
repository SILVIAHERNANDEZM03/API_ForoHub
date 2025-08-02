package com.alura.ForoHub.dto;

import com.alura.ForoHub.model.Respuesta;

import java.time.LocalDateTime;

public record DatosListadoRespuesta(
        Long id,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean solucion
) {
    public DatosListadoRespuesta(Respuesta r) {
        this(r.getId(), r.getMensaje(), r.getFechaCreacion(), r.getSolucion());
    }
}
