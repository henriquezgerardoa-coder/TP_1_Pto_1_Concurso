package main.java;

import java.time.LocalDateTime;
import java.util.Objects;

public final class Inscripcion {
    private final Participante participante;
    private final LocalDateTime fechaInscripcion;

    public Inscripcion(Participante participante, LocalDateTime fechaInscripcion) {
        if (participante == null) {
            throw new IllegalArgumentException("Participante no puede ser nulo");
        }
        if (fechaInscripcion == null) {
            throw new IllegalArgumentException("Fecha de inscripción no puede ser nula");
        }
        this.participante = participante;
        this.fechaInscripcion = fechaInscripcion;
    }

    public Participante Participante() {
        return participante;
    }

    public LocalDateTime FechaInscripcion() {
        return fechaInscripcion;
    }

    public boolean estaInscripto(Participante participante) {
        return this.participante.equals(participante);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Inscripcion that)) return false;
        return Objects.equals(participante, that.participante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(participante);
    }
}