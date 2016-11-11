
package controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import modelo.DAO.InterfazDAO;
import persistencia.Curso;


@ManagedBean
@RequestScoped
public class AgregarCurso {

     @ManagedProperty("#{cDAO}")
     private InterfazDAO iDAO;
     private String urlDocumento;
     private String nombre;
     private String descripcion;
    
    public AgregarCurso() {
    }

    public InterfazDAO getiDAO() {
        return iDAO;
    }

    public void setiDAO(InterfazDAO iDAO) {
        this.iDAO = iDAO;
    }

    public String getUrlDocumento() {
        return urlDocumento;
    }

    public void setUrlDocumento(String urlDocumento) {
        this.urlDocumento = urlDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String guardarCurso(){
        //Creamos un curso vacío y le damos nombre y descripción (y el documento, en los que los lleven)
        Curso c= new Curso();
        c.setNombre(nombre);
        c.setDescripcion(descripcion);
        //A través de este método de la interfaz, mandamos los datos a la bbdd.
        iDAO.persist(c);
        return "imparticion";
    }
}
