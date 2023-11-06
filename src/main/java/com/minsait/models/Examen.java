package com.minsait.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Examen {

    private static Long idIdentity = 1L;
    private Long id;
    private String nombre;
    List<String> preguntas;

    public Examen(String nombre) {
        this.id = Examen.idIdentity;
        Examen.idIdentity++;
        this.nombre = nombre;
        this.preguntas = new ArrayList<>();
    }
}
