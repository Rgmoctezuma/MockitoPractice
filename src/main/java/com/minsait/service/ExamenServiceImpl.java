package com.minsait.service;

import com.minsait.models.Examen;
import com.minsait.repositories.ExamenRepository;
import com.minsait.repositories.PreguntaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExamenServiceImpl implements ExamenService {

    private ExamenRepository examenRepository;
    private PreguntaRepository preguntaRepository;


    @Override
    public Optional<Examen> findExamByNombre(String nombre) {
        return examenRepository.findAll().stream()
                .filter(examen -> examen.getNombre().equals(nombre))
                .findFirst();
    }

    @Override
    public Examen findExamenPorNombreConPreguntas(String nombre) {
        Optional<Examen> ex = examenRepository.findAll().stream()
                .filter(examen -> examen.getNombre().equals(nombre))
                .findFirst();

        List<String> preguntas;

        if (ex.isPresent()){
            preguntas = preguntaRepository.findPreguntasByExamenId(ex.get().getId());
            ex.get().setPreguntas(preguntas);
            return ex.get();
        }
        return null;
    }

    @Override
    public Examen saveExamen(Examen examen) {
        if (!examen.getPreguntas().isEmpty())
            preguntaRepository.savePreguntas(examen.getPreguntas());
        return examenRepository.save(examen);
    }
}
