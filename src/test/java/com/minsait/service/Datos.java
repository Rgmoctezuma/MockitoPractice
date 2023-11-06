package com.minsait.service;

import com.minsait.models.Examen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Datos {

    public static final List<Examen> EXAMENES = Arrays.asList(
            new Examen("matematicas"),
            new Examen("historia"),
            new Examen("Quimica")
    );

    public static final Examen examen = new Examen("Programacion");

    public static final List<String> PREGUNTAS = Arrays.asList(
            "Aritmetica",
            "Integrales",
            "Derivadas"
    );

}
