package main.java;

import java.util.HashSet;
import java.util.Set;
import java.time.LocalDateTime;

public class Concurso {
    private final String nombre;

    private final Set<Inscripcion> inscriptos;

    public Concurso(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre del concurso no puede ser nulo o vacío");
        }
        this.nombre = nombre;
        // HashSet para garantizar unicidad
        this.inscriptos = new HashSet<>();
    }

    public void nuevaInscripcion(Participante unParticipante) {
        // Ahora al intentar agregar si ya existe (según el equals), no se agrega
        this.inscriptos.add(new Inscripcion(unParticipante, LocalDateTime.now()));
    }

    public boolean participanteInscripto(Participante participante) {
        return this.inscriptos.stream()
                .anyMatch(i -> i.estaInscripto(participante));
    }

    public int cantidadInscriptos() {
        return this.inscriptos.size();
    }
}