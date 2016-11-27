package modelo.DAO;

import java.util.List;
import persistencia.Examen;

public interface InterfazExamen {

    public void crearExamen(String textoRespuesta1, String textoRespuesta2, String textoRespuesta3,
            String textoRespuesta4, String textoPregunta, int respuestaCorrecta, int idImparticion);

    public List<Examen> cargarExamen(int idImparticion);

  
}
