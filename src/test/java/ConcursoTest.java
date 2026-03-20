package test.java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.java.Participante;
import main.java.Concurso;


public class ConcursoTest {
    @Test
    public void inscriboUnParticipante() {
        // Given
        Participante jose = new Participante("234566", "Jose Perez");
        Concurso unConcurso = new Concurso("Un Concurso");

        // When
        unConcurso.nuevaInscripcion(jose);

        // Then
        assertTrue(unConcurso.participanteInscripto(jose));
        assertEquals(1, unConcurso.cantidadInscriptos());
    }

    @Test
    public void inscriboUnMismoParticipanteDosVecesSoloQuedaUnaVez() {
        var jose = new Participante("234566", "Jose Perez");
        Concurso unConcurso = new Concurso("Un Concurso");
        unConcurso.nuevaInscripcion(jose);
        unConcurso.nuevaInscripcion(jose);
        assertEquals(1, unConcurso.cantidadInscriptos());
        assertTrue(unConcurso.participanteInscripto(jose));
    }


    @Test
    public void concursoNuevoNoTieneInscriptos() {
        Concurso unConcurso = new Concurso("Un Concurso");
        assertEquals(0, unConcurso.cantidadInscriptos());
    }

    @Test
    public void participanteJoseNoDebeEstarInscripto() {
        var jose = new Participante("234566", "Jose Perez");
        Concurso unConcurso = new Concurso("Un Concurso");
        assertFalse(unConcurso.participanteInscripto(jose));
    }

    @Test
    public void inscriboDosPersonasAmbasEstanInscriptas() {
        var jose1 = new Participante("234566", "Jose Perez");
        var jorge = new Participante("698712", "Jorge Saldivar");
        Concurso unConcurso = new Concurso("Un Concurso");
        unConcurso.nuevaInscripcion(jose1);
        unConcurso.nuevaInscripcion(jorge);
        assertTrue(unConcurso.participanteInscripto(jorge));
        assertEquals(2, unConcurso.cantidadInscriptos());
    }
}