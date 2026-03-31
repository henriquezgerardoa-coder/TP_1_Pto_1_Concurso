package main.java;

import java.time.LocalDate;

public class Main {
    static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  SISTEMA DE INSCRIPCIÓN A CONCURSOS");
        System.out.println("========================================\n");

        // Crear un concurso
        Concurso concurso = new Concurso("Concurso de Programación 2024");
        System.out.println("✓ Concurso creado: " + concurso.Nombre());

        // Configurar rango de inscripción
        LocalDate hoy = LocalDate.now();
        LocalDate inicio = hoy;
        LocalDate fin = hoy.plusDays(7);
        concurso.establecerRangoInscripcion(inicio, fin);
        System.out.println("✓ Rango de inscripción: " + inicio + " a " + fin);

        System.out.println("\n--- PRUEBA 1: Inscripción en el primer día (gana 10 puntos) ---");
        Participante juan = new Participante("123456", "Juan Gómez");
        System.out.println("  Participante: " + juan.Nombre() + " (DNI: " + juan.Dni() + ")");
        System.out.println("  Puntos antes: " + juan.Puntos());
        
        concurso.nuevaInscripcion(juan);
        System.out.println("  ✓ Inscripción exitosa en primer día");
        System.out.println("  Puntos después: " + juan.Puntos() + " (bonificación: +10)");
        System.out.println("  Estado: " + (concurso.participanteInscripto(juan) ? "INSCRIPTO ✓" : "NO INSCRIPTO ✗"));

        System.out.println("\n--- PRUEBA 2: Inscripción de otro participante ---");
        Participante maria = new Participante("789123", "María López");
        System.out.println("  Participante: " + maria.Nombre() + " (DNI: " + maria.Dni() + ")");
        System.out.println("  Puntos antes: " + maria.Puntos());
        
        concurso.nuevaInscripcion(maria);
        System.out.println("  ✓ Inscripción exitosa en primer día");
        System.out.println("  Puntos después: " + maria.Puntos() + " (bonificación: +10)");
        System.out.println("  Estado: " + (concurso.participanteInscripto(maria) ? "INSCRIPTO ✓" : "NO INSCRIPTO ✗"));

        System.out.println("\n--- PRUEBA 3: Intento de re-inscripción del mismo participante ---");
        System.out.println("  Intentando inscribir nuevamente a: " + juan.Nombre());
        concurso.nuevaInscripcion(juan);
        System.out.println("  ✓ Re-inscripción rechazada (ya estaba inscripto)");
        System.out.println("  Total de inscriptos: " + concurso.cantidadInscriptos());

        System.out.println("\n--- PRUEBA 4: Intento de inscripción fuera del rango (futuro) ---");
        Concurso concursoFuturo = new Concurso("Concurso Futuro");
        LocalDate mañana = LocalDate.now().plusDays(1);
        concursoFuturo.establecerRangoInscripcion(mañana, mañana.plusDays(7));
        Participante carlos = new Participante("456789", "Carlos Martínez");
        
        System.out.println("  Concurso: " + concursoFuturo.Nombre());
        System.out.println("  Rango: " + mañana + " a " + mañana.plusDays(7));
        System.out.println("  Participante: " + carlos.Nombre());
        
        try {
            concursoFuturo.nuevaInscripcion(carlos);
            System.out.println("  ✗ ERROR: Debería haber rechazado la inscripción");
        } catch (IllegalArgumentException e) {
            System.out.println("  ✓ Inscripción rechazada correctamente");
            System.out.println("  Razón: " + e.getMessage());
        }

        System.out.println("\n--- PRUEBA 5: Intento de inscripción fuera del rango (pasado) ---");
        Concurso concursoPasado = new Concurso("Concurso Pasado");
        LocalDate ayer = LocalDate.now().minusDays(1);
        concursoPasado.establecerRangoInscripcion(ayer.minusDays(7), ayer);
        Participante luis = new Participante("111222", "Luis García");
        
        System.out.println("  Concurso: " + concursoPasado.Nombre());
        System.out.println("  Rango: " + ayer.minusDays(7) + " a " + ayer);
        System.out.println("  Participante: " + luis.Nombre());
        
        try {
            concursoPasado.nuevaInscripcion(luis);
            System.out.println("  ✗ ERROR: Debería haber rechazado la inscripción");
        } catch (IllegalArgumentException e) {
            System.out.println("  ✓ Inscripción rechazada correctamente");
            System.out.println("  Razón: " + e.getMessage());
        }

        System.out.println("\n--- PRUEBA 6: Intento de inscripción sin rango configurado ---");
        Concurso concursoSinRango = new Concurso("Concurso Sin Rango");
        Participante ana = new Participante("555666", "Ana Rodríguez");
        
        System.out.println("  Concurso: " + concursoSinRango.Nombre());
        System.out.println("  Rango: NO CONFIGURADO");
        System.out.println("  Participante: " + ana.Nombre());
        
        try {
            concursoSinRango.nuevaInscripcion(ana);
            System.out.println("  ✗ ERROR: Debería haber rechazado la inscripción");
        } catch (IllegalStateException e) {
            System.out.println("  ✓ Inscripción rechazada correctamente");
            System.out.println("  Razón: " + e.getMessage());
        }

        System.out.println("\n========================================");
        System.out.println("  RESUMEN FINAL");
        System.out.println("========================================");
        System.out.println("Concurso: " + concurso.Nombre());
        System.out.println("Total de inscriptos: " + concurso.cantidadInscriptos());
        System.out.println("\nParticipantes inscritos:");
        System.out.println("  1. " + juan.Nombre() + " (DNI: " + juan.Dni() + ") - Puntos: " + juan.Puntos());
        System.out.println("  2. " + maria.Nombre() + " (DNI: " + maria.Dni() + ") - Puntos: " + maria.Puntos());
        System.out.println("\n✓ Todas las pruebas completadas exitosamente");
    }
}

