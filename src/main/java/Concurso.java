package main.java;

import java.util.HashSet;
import java.util.Set;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class Concurso {
    private final String nombre;
    private final Set<Inscripcion> inscriptos;
    private LocalDate fechaInicioInscripcion;
    private LocalDate fechaFinInscripcion;
    private final int PUNTOS_PRIMER_DIA = 10;

    public Concurso(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre del concurso no puede ser nulo o vacío");
        }
        this.nombre = nombre;
        this.inscriptos = new HashSet<>();
    }

    public String Nombre() {
        return nombre;
    }

    public void establecerRangoInscripcion(LocalDate fechaInicio, LocalDate fechaFin) {
        if (fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException("Las fechas no pueden ser nulas");
        }
        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin");
        }
        this.fechaInicioInscripcion = fechaInicio;
        this.fechaFinInscripcion = fechaFin;
    }

    public void nuevaInscripcion(Participante unParticipante) {
        validarRangoInscripcion();
        LocalDateTime ahora = LocalDateTime.now();
        validarFechaInscripcion(ahora);

        Inscripcion nuevaInscripcion = new Inscripcion(unParticipante, ahora);
        
        boolean yaExiste = this.inscriptos.stream()
                .anyMatch(i -> i.estaInscripto(unParticipante));
        
        if (!yaExiste) {
            this.inscriptos.add(nuevaInscripcion);
            // Bonificar si es el primer día
            if (esPrimerDiaInscripcion(ahora)) {
                unParticipante.agregarPuntos(PUNTOS_PRIMER_DIA);
            }
        }
    }

    private void validarRangoInscripcion() {
        if (this.fechaInicioInscripcion == null || this.fechaFinInscripcion == null) {
            throw new IllegalStateException("El rango de inscripción no ha sido configurado");
        }
    }

    private void validarFechaInscripcion(LocalDateTime fecha) {
        LocalDate hoy = fecha.toLocalDate();
        if (hoy.isBefore(this.fechaInicioInscripcion) || hoy.isAfter(this.fechaFinInscripcion)) {
            throw new IllegalArgumentException("La inscripción está fuera del rango permitido");
        }
    }

    private boolean esPrimerDiaInscripcion(LocalDateTime fecha) {
        return fecha.toLocalDate().equals(this.fechaInicioInscripcion);
    }

    public boolean participanteInscripto(Participante participante) {
        return this.inscriptos.stream()
                .anyMatch(i -> i.estaInscripto(participante));
    }

    public int cantidadInscriptos() {
        return this.inscriptos.size();
    }
}