package com.minsait.service;

import com.minsait.models.Examen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Datos {

    public static final List<Examen> EXAMENES = Arrays.asList(
            new Examen(1L, "matematicas"),
            new Examen(2L, "historia"),
            new Examen(3L, "Quimica")
    );

    public static final Examen examen = new Examen(4L, "Programacion");

    public static final List<String> PREGUNTAS = Arrays.asList(
            "Aritmetica",
            "Integrales",
            "Derivadas"
    );

}
