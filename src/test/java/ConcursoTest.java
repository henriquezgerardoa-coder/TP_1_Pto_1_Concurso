package test.java;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import main.java.Participante;
import main.java.Concurso;
import java.time.LocalDate;


public class ConcursoTest {
    private Concurso unConcurso;
    private Participante jose;
    private LocalDate hoy;

    @BeforeEach
    public void setUp() {
        unConcurso = new Concurso("Un Concurso");
        jose = new Participante("234566", "Jose Perez");
        hoy = LocalDate.now();
        // Configurar rango de inscripción para hoy
        unConcurso.establecerRangoInscripcion(hoy, hoy.plusDays(7));
    }

    @Test
    public void inscriboUnParticipante() {
        // When
        unConcurso.nuevaInscripcion(jose);

        // Then
        assertTrue(unConcurso.participanteInscripto(jose));
        assertEquals(1, unConcurso.cantidadInscriptos());
    }

    @Test
    public void inscriboUnMismoParticipanteDosVecesSoloQuedaUnaVez() {
        var jose = new Participante("234566", "Jose Perez");
        unConcurso.nuevaInscripcion(jose);
        unConcurso.nuevaInscripcion(jose);
        assertEquals(1, unConcurso.cantidadInscriptos());
        assertTrue(unConcurso.participanteInscripto(jose));
    }


    @Test
    public void concursoNuevoNoTieneInscriptos() {
        assertEquals(0, unConcurso.cantidadInscriptos());
    }

    @Test
    public void participanteJoseNoDebeEstarInscripto() {
        var jose = new Participante("234566", "Jose Perez");
        assertFalse(unConcurso.participanteInscripto(jose));
    }

    @Test
    public void inscriboDosPersonasAmbasEstanInscriptas() {
        var jose1 = new Participante("234566", "Jose Perez");
        var jorge = new Participante("698712", "Jorge Saldivar");
        unConcurso.nuevaInscripcion(jose1);
        unConcurso.nuevaInscripcion(jorge);
        assertTrue(unConcurso.participanteInscripto(jorge));
        assertEquals(2, unConcurso.cantidadInscriptos());
    }

    @Test
    public void participanteInscriptoDurantePrimerDiaGana10Puntos() {
        // Given
        Participante juan = new Participante("123456", "Juan Gomez");
        assertEquals(0, juan.Puntos());

        // When
        unConcurso.nuevaInscripcion(juan);

        // Then
        assertEquals(10, juan.Puntos(), "El participante debe ganar 10 puntos por inscribirse en el primer día");
    }

    @Test
    public void participanteNoPuedoInscribirseAntesDelRango() {
        // Given
        Concurso concursoFuturo = new Concurso("Concurso Futuro");
        LocalDate mañana = LocalDate.now().plusDays(1);
        concursoFuturo.establecerRangoInscripcion(mañana, mañana.plusDays(7));
        Participante maria = new Participante("789123", "Maria Lopez");

        // Then
        assertThrows(IllegalArgumentException.class, () -> {
            concursoFuturo.nuevaInscripcion(maria);
        }, "No se debe permitir inscripción antes del rango permitido");
    }

    @Test
    public void participanteNoPuedoInscribirseAfueraDelRango() {
        // Given
        Concurso concursoPasado = new Concurso("Concurso Pasado");
        LocalDate ayer = LocalDate.now().minusDays(1);
        concursoPasado.establecerRangoInscripcion(ayer.minusDays(7), ayer);
        Participante carlos = new Participante("456789", "Carlos Martinez");

        // Then
        assertThrows(IllegalArgumentException.class, () -> {
            concursoPasado.nuevaInscripcion(carlos);
        }, "No se debe permitir inscripción después del rango permitido");
    }

    @Test
    public void validarRangoInscripcionRequiereConfiguration() {
        // Given
        Concurso concursoSinRango = new Concurso("Concurso Sin Rango");
        Participante luis = new Participante("111222", "Luis Garcia");

        // Then
        assertThrows(IllegalStateException.class, () -> {
            concursoSinRango.nuevaInscripcion(luis);
        }, "Se debe lanzar excepción si no se ha configurado el rango de inscripción");
    }
}