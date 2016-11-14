/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import persistencia.Pregunta;
import persistencia.Respuesta;

@ManagedBean
@RequestScoped
public class PreguntaExamen {
    
    private Pregunta pregunta;
    private List<Respuesta> respuestas;
    private String valorGuardado;

    public PreguntaExamen() {
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

    public String getValorGuardado() {
        return valorGuardado;
    }

    public void setValorGuardado(String valorGuardado) {
        this.valorGuardado = valorGuardado;
    }   
            
}
