package com.minsait.service;

import com.minsait.models.Examen;

import java.util.Optional;

interface ExamenService {

    Optional<Examen> findExamByNombre(String nombre);

    Examen findExamenPorNombreConPreguntas(String nombre);

    Examen saveExamen(Examen examen);

}
