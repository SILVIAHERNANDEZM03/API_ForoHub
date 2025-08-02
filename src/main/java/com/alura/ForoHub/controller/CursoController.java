package com.alura.ForoHub.controller;

import com.alura.ForoHub.model.Curso;
import com.alura.ForoHub.repository.CursoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    // Crear curso
    @PostMapping
    public ResponseEntity<Curso> crearCurso(@RequestBody @Valid Curso curso) {
        Curso nuevoCurso = cursoRepository.save(curso);
        return ResponseEntity.ok(nuevoCurso);
    }

    // Listar todos los cursos
    @GetMapping
    public ResponseEntity<List<Curso>> listarCursos() {
        return ResponseEntity.ok(cursoRepository.findAll());
    }

    // Obtener un curso por id
    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerCurso(@PathVariable Long id) {
        Optional<Curso> curso = cursoRepository.findById(id);
        return curso.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar un curso existente
    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizarCurso(@PathVariable Long id, @RequestBody @Valid Curso datosActualizados) {
        Optional<Curso> cursoOptional = cursoRepository.findById(id);
        if (cursoOptional.isPresent()) {
            Curso curso = cursoOptional.get();
            curso.setNombre(datosActualizados.getNombre());
            curso.setCategoria(datosActualizados.getCategoria());
            return ResponseEntity.ok(cursoRepository.save(curso));
        }
        return ResponseEntity.notFound().build();
    }

    // Eliminar curso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

