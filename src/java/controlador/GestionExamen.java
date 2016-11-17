package controlador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import modelo.DAO.InterfazDAO;
import persistencia.Examen;
import persistencia.Imparticion;
import persistencia.Pregunta;

@ManagedBean
@SessionScoped
public class GestionExamen {

    @ManagedProperty("#{cDAO}")
    private InterfazDAO iDAO;
    private List<Examen> preguntas;
    private List<PreguntaExamen> preguntasExamen;
    private int idImparticion;
    private String urlTemario;
    private int nota;

    public GestionExamen() {
    }

    public String corregir() {
        nota = 0;
        for (PreguntaExamen pe : preguntasExamen) {
            if (pe.getValueRespuesta()==pe.getPregunta().getRespuestaCorrecta()) {
             nota+=2;   
            }
        }
        System.out.println(nota);
        return "menuAlumno";
    }

    public String cargar() {
        preguntasExamen = new ArrayList<>();
        preguntas = iDAO.cargarExamen(idImparticion);
        for (Examen p : preguntas) {
            PreguntaExamen pe = new PreguntaExamen();
            Collection respuestas = p.getIdPregunta().getRespuestaCollection();
            Pregunta pregunta = p.getIdPregunta();
            pe.setPregunta(pregunta);
            pe.setRespuestas(respuestas);
            preguntasExamen.add(pe);
        }
        return "examen";
    }

    public String temario() {
        Imparticion i = iDAO.buscarTemario(idImparticion);
        String nombre = i.getIdCurso().getDocumento();
        urlTemario = "resources/" + nombre;
        return "temario";
    }

    public List<Examen> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Examen> preguntas) {
        this.preguntas = preguntas;
    }

    public int getIdImparticion() {
        return idImparticion;
    }

    public void setIdImparticion(int idImparticion) {
        this.idImparticion = idImparticion;
    }

    public InterfazDAO getiDAO() {
        return iDAO;
    }

    public void setiDAO(InterfazDAO iDAO) {
        this.iDAO = iDAO;
    }

    public String getUrlTemario() {
        return urlTemario;
    }

    public void setUrlTemario(String urlTemario) {
        this.urlTemario = urlTemario;
    }

    public List<PreguntaExamen> getPreguntasExamen() {
        return preguntasExamen;
    }

    public void setPreguntasExamen(List<PreguntaExamen> preguntasExamen) {
        this.preguntasExamen = preguntasExamen;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

}
