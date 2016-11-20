/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.Collection;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import persistencia.Pregunta;

@ManagedBean
@RequestScoped
/**
 * Con esta clase unimos una pregunta con sus respuestas y el valor de la
 * respuesta introducida por el usuario
 */
public class PreguntaExamen {
private Collection respuestas; //Interfaz que es “la raíz” de todas las colecciones
private Pregunta pregunta; //Objeto de la entity pregunta(paquete persistencia)
private int valueRespuesta;
       
    public PreguntaExamen() {
    }

    public Collection getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(Collection respuestas) {
        this.respuestas = respuestas;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public int getValueRespuesta() {
        return valueRespuesta;
    }

    public void setValueRespuesta(int valueRespuesta) {
        this.valueRespuesta = valueRespuesta;
    }              
          
}
