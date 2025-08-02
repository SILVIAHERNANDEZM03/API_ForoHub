package com.alura.ForoHub.controller;
import com.alura.ForoHub.dto.*;
import com.alura.ForoHub.model.Respuesta;
import com.alura.ForoHub.model.Topico;
import com.alura.ForoHub.model.Usuario;
import com.alura.ForoHub.repository.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {
    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity crear(@RequestBody @Valid DatosRegistroRespuesta datos) {
        Topico topico = topicoRepository.getReferenceById(datos.topicoId());
        Usuario autor = usuarioRepository.getReferenceById(datos.autorId());

        Respuesta respuesta = new Respuesta();
        respuesta.setMensaje(datos.mensaje());
        respuesta.setTopico(topico);
        respuesta.setAutor(autor);
        respuestaRepository.save(respuesta);

        return ResponseEntity.ok(new DatosListadoRespuesta(respuesta));
    }

    @GetMapping
    public ResponseEntity<List<DatosListadoRespuesta>> listar() {
        var lista = respuestaRepository.findAll().stream().map(DatosListadoRespuesta::new).toList();
        return ResponseEntity.ok(lista);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizar(@RequestBody DatosActualizarRespuesta datos) {
        Respuesta respuesta = respuestaRepository.getReferenceById(datos.id());

        if (datos.mensaje() != null) respuesta.setMensaje(datos.mensaje());
        if (datos.solucion() != null) respuesta.setSolucion(datos.solucion());

        return ResponseEntity.ok(new DatosListadoRespuesta(respuesta));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar(@PathVariable Long id) {
        respuestaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
