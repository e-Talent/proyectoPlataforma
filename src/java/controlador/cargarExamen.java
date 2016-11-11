package controlador;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import modelo.DAO.InterfazDAO;
import persistencia.Examen;

@ManagedBean
@RequestScoped
public class cargarExamen {

    @ManagedProperty("#{cDAO}")
    private InterfazDAO iDAO;
    private List<Examen> preguntas; 
    private String idImparticion;
      
    public cargarExamen() {
    }

    public List<Examen> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Examen> preguntas) {
        this.preguntas = preguntas;
    }

    public String getIdImparticion() {
        return idImparticion;
    }

    public void setIdImparticion(String idImparticion) {
        this.idImparticion = idImparticion;
    }

 
    public InterfazDAO getiDAO() {
        return iDAO;
    }

    public void setiDAO(InterfazDAO iDAO) {
        this.iDAO = iDAO;
    }       
    
    public String cargar() {
    preguntas = iDAO.cargarExamen(idImparticion);
    return "examen";
    }

  
    
}
