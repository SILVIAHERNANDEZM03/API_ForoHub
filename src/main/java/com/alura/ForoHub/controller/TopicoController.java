package com.alura.ForoHub.controller;


import com.alura.ForoHub.dto.DatosActualizarTopico;
import com.alura.ForoHub.dto.DatosRegistroTopico;
import com.alura.ForoHub.dto.DatosRespuestaTopico;
import com.alura.ForoHub.model.Curso;
import com.alura.ForoHub.model.Topico;
import com.alura.ForoHub.model.Usuario;
import com.alura.ForoHub.repository.CursoRepository;
import com.alura.ForoHub.repository.TopicoRepository;
import com.alura.ForoHub.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    // Crear un nuevo tópico
    @PostMapping
    public void registrarTopico(@RequestBody @Valid DatosRegistroTopico datos) {
        Usuario autor = usuarioRepository.findById(datos.autorId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Curso curso = cursoRepository.findById(datos.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        Topico topico = new Topico();
        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());
        topico.setAutor(autor);
        topico.setCurso(curso);

        topicoRepository.save(topico);
    }

    // Listar todos los tópicos
    @GetMapping
    public List<DatosRespuestaTopico> listar() {
        return topicoRepository.findAll().stream()
                .map(t -> new DatosRespuestaTopico(t.getId(), t.getTitulo(), t.getMensaje(), t.getFechaCreacion()))
                .toList();
    }

    // Mostrar tópico específico
    @GetMapping("/{id}")
    public DatosRespuestaTopico mostrar(@PathVariable Long id) {
        Topico t = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));
        return new DatosRespuestaTopico(t.getId(), t.getTitulo(), t.getMensaje(), t.getFechaCreacion());
    }

    // Actualizar tópico
    @PutMapping
    public void actualizar(@RequestBody @Valid DatosActualizarTopico datos) {
        Topico topico = topicoRepository.findById(datos.id())
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));
        if (datos.titulo() != null) topico.setTitulo(datos.titulo());
        if (datos.mensaje() != null) topico.setMensaje(datos.mensaje());
    }

    // Eliminar tópico
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        topicoRepository.deleteById(id);
    }
}
