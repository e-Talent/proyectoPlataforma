package controlador;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import modelo.DAO.InterfazDAO;
import persistencia.Examen;
import persistencia.Imparticion;

@ManagedBean
@RequestScoped
public class cargarExamen {

    @ManagedProperty("#{cDAO}")
    private InterfazDAO iDAO;
    private List<Examen> preguntas; 
    private int idImparticion;
    private String urlTemario;
      
    public cargarExamen() {
    }
    
       public String cargar() {
    preguntas = iDAO.cargarExamen(idImparticion);      
    return "examen";
    }
       
    public String temario () {
    Imparticion i = iDAO.buscarTemario(idImparticion);
    String nombre = i.getIdCurso().getDocumento();
    urlTemario = "resources/"+nombre;
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

  
    
}
