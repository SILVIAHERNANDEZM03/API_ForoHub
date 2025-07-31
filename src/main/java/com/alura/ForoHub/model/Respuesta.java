package com.alura.ForoHub.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;

    private LocalDateTime fechaCreacion = LocalDateTime.now();

    private Boolean solucion = false;

    @ManyToOne
    private Topico topico;

    @ManyToOne
    private Usuario autor;
}
