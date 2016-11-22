package controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import modelo.DAO.InterfazDAO;

@ManagedBean
@RequestScoped
public class CrearExamen {

    @ManagedProperty("#{cDAO}")
    private InterfazDAO iDAO;
    private String textoRespuesta1;
    private String textoRespuesta2;
    private String textoRespuesta3;
    private String textoRespuesta4;
    private String textoPregunta;
    private int respuestaCorrecta;
    private int idImparticion=18;

    public CrearExamen() {

    }

    public String getTextoRespuesta1() {
        return textoRespuesta1;
    }

    public void setTextoRespuesta1(String textoRespuesta1) {
        this.textoRespuesta1 = textoRespuesta1;
    }

    public String getTextoRespuesta2() {
        return textoRespuesta2;
    }

    public void setTextoRespuesta2(String textoRespuesta2) {
        this.textoRespuesta2 = textoRespuesta2;
    }

    public String getTextoRespuesta3() {
        return textoRespuesta3;
    }

    public void setTextoRespuesta3(String textoRespuesta3) {
        this.textoRespuesta3 = textoRespuesta3;
    }

    public String getTextoRespuesta4() {
        return textoRespuesta4;
    }

    public void setTextoRespuesta4(String textoRespuesta4) {
        this.textoRespuesta4 = textoRespuesta4;
    }

    public String getTextoPregunta() {
        return textoPregunta;
    }

    public void setTextoPregunta(String textoPregunta) {
        this.textoPregunta = textoPregunta;
    }

    public int getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(int respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
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

    public String preguntaSiguiente() {
    iDAO.crearExamen(textoRespuesta1, textoRespuesta2, textoRespuesta3, textoRespuesta4, textoPregunta, respuestaCorrecta, idImparticion);    
    textoRespuesta1=null;
    textoRespuesta2=null;
    textoRespuesta3=null;
    textoRespuesta4=null;
    textoPregunta=null;    
    return null;   
    }
    
    public String crearExamen() {
        iDAO.crearExamen(textoRespuesta1, textoRespuesta2, textoRespuesta3, textoRespuesta4, textoPregunta, respuestaCorrecta, idImparticion);
        return "menuAdmin";
    }

}