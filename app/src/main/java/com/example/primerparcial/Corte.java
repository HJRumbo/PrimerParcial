package com.example.primerparcial;

public class Corte {
    int Id;
    int NumCorte;
    String Actividad;
    int Porcentaje;
    double Nota;
    double NotaCalculada;
    String CodigoMateria;

    public Corte() {
    }

    public Corte(int id, int numCorte, String actividad, int porcentaje, double nota, double notaCalculada, String codigoMateria) {
        Id = id;
        NumCorte = numCorte;
        Actividad = actividad;
        Porcentaje = porcentaje;
        Nota = nota;
        NotaCalculada = notaCalculada;
    }

    public Corte(int numCorte, String actividad, int porcentaje, double nota, double notaCalculada, String codigoMateria) {
        NumCorte = numCorte;
        Actividad = actividad;
        Porcentaje = porcentaje;
        Nota = nota;
        NotaCalculada = notaCalculada;
        CodigoMateria = codigoMateria;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getNumCorte() {
        return NumCorte;
    }

    public void setNumCorte(int numCorte) {
        NumCorte = numCorte;
    }

    public String getActividad() {
        return Actividad;
    }

    public void setActividad(String actividad) {
        Actividad = actividad;
    }

    public int getPorcentaje() {
        return Porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        Porcentaje = porcentaje;
    }

    public double getNota() {
        return Nota;
    }

    public void setNota(double nota) {
        Nota = nota;
    }

    public double getNotaCalculada() {
        return NotaCalculada;
    }

    public void setNotaCalculada(double notaCalculada) {
        NotaCalculada = notaCalculada;
    }

    public String getCodigoMateria() {
        return CodigoMateria;
    }

    public void setCodigoMateria(String codigoMateria) {
        CodigoMateria = codigoMateria;
    }
}
