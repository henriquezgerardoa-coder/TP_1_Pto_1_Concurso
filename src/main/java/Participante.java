package main.java;

import java.util.Objects;

public class Participante {

    private String dni;
    private final String nombre;
    private int puntos = 0;

    public Participante(String dni, String nombre) {
        if (dni == null || dni.isBlank()) {
            throw new IllegalArgumentException("DNI no puede ser nulo o vacío");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre no puede ser nulo o vacío");
        }
        this.dni = dni;
        this.nombre = nombre;
    }

    public String Dni() {
        return dni;
    }

    public String Nombre() {
        return nombre;
    }

    public int Puntos() {
        return puntos;
    }

    public void agregarPuntos(int puntos) {
        if (puntos > 0) {
            this.puntos += puntos;
        } else {
            throw new IllegalArgumentException("Los puntos a agregar deben ser mayores a cero");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Participante that)) return false;
        return Objects.equals(dni, that.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }
}

