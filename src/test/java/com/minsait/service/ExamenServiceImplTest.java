package com.minsait.service;

import com.minsait.models.Examen;
import com.minsait.repositories.ExamenRepository;
import com.minsait.repositories.PreguntaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ExamenServiceImplTest {

    @Mock
    ExamenRepository examenRepository;
    @Mock
    PreguntaRepository preguntasRepository;

    @InjectMocks
    ExamenServiceImpl service;

    @Test
    void testFindExamenPorNombre() {
        //given
        when(examenRepository.findAll()).thenReturn(Datos.EXAMENES);
        //when
        String nombre = "matematicas";
        Optional<Examen> examen = service.findExamByNombre(nombre);
        //then
        assertTrue(examen.isPresent());
        assertEquals(nombre, examen.get().getNombre());
    }

    @Test
    void testfindExamenPorNombreConPreguntas() {
        //given
        when(examenRepository.findAll()).thenReturn(Datos.EXAMENES);
        when(preguntasRepository.findPreguntasByExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);
        //when
        String nombreExamen = "matematicas";
        Examen examen = service.findExamenPorNombreConPreguntas(nombreExamen);
        //then
        assertTrue(examen.getPreguntas().contains("Aritmetica"));
        verify(preguntasRepository).findPreguntasByExamenId(anyLong());
    }

    @Test
    void testExceptions() {
        when(examenRepository.findAll()).thenReturn(Datos.EXAMENES);
        when(preguntasRepository.findPreguntasByExamenId(anyLong())).thenThrow(IllegalArgumentException.class);
        String nombre = "matematicas";

        assertThrows(IllegalArgumentException.class, ()->{
            service.findExamenPorNombreConPreguntas(nombre);
        });
    }

    @Test
    void testDoThrow() {
        //given
        Examen examen = Datos.examen;
        examen.setPreguntas(Datos.PREGUNTAS);
        doThrow(IllegalArgumentException.class).when(preguntasRepository).savePreguntas(anyList());

        assertThrows(IllegalArgumentException.class, ()->{
            service.saveExamen(examen);
        });
    }

    @Test
    void testDoAnswer() {
        //given
        when(examenRepository.findAll()).thenReturn(Datos.EXAMENES);
        Mockito.doAnswer( invocationOnMock -> {
            Long id = invocationOnMock.getArgument(0);
            return id == 1L?Datos.PREGUNTAS:Collections.EMPTY_LIST;
        }).when(preguntasRepository).findPreguntasByExamenId(anyLong());

        //when
        Examen examen = service.findExamenPorNombreConPreguntas("matematicas");

        //then
        assertAll(
                () -> assertEquals(1L, examen.getId()),
                () -> assertFalse(examen.getPreguntas().isEmpty())
        );
    }

    @Test
    void testSaveExamen() {
        //given
        Examen examen = Datos.EXAMENES.get(0);
        examen.setPreguntas(Datos.PREGUNTAS);
        //when
        service.saveExamen(examen);
        //then
        verify(examenRepository).save(examen);
    }

    @Test
    void testFindNonExistExam() {
        when(examenRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
        assertNull(service.findExamenPorNombreConPreguntas("non-exist"));
    }
}