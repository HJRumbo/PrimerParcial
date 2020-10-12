package com.example.primerparcial;

public class Materia {
    String Codigo;
    String Nombre;
    int NumCreditos;
    double Nota;

    public Materia() {
    }

    public Materia(String codigo, String nombre, int numCreditos, double nota) {
        Codigo = codigo;
        Nombre = nombre;
        NumCreditos = numCreditos;
        Nota = nota;
    }

    public Materia(String codigo, String nombre, int numCreditos) {
        Codigo = codigo;
        Nombre = nombre;
        NumCreditos = numCreditos;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getNumCreditos() {
        return NumCreditos;
    }

    public void setNumCreditos(int numCreditos) {
        NumCreditos = numCreditos;
    }

    public double getNota() {
        return Nota;
    }

    public void setNota(double nota) {
        Nota = nota;
    }
}
