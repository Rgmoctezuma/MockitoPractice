package com.minsait.repositories;

import java.util.List;

public interface PreguntaRepository {
    List<String> findPreguntasByExamenId(Long id);
    void savePreguntas(List<String> preguntas);
}
