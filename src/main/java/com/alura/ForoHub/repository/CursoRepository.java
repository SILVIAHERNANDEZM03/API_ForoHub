package com.alura.ForoHub.repository;

import com.alura.ForoHub.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
