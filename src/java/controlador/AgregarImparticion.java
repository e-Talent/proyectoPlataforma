
package controlador;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import modelo.DAO.InterfazDAO;
import persistencia.Imparticion;


@ManagedBean
@RequestScoped
public class AgregarImparticion {

     @ManagedProperty("#{cDAO}")
     private InterfazDAO iDAO;
     private String idCurso;
     private Date fechaInicio;
     private Date fechaFin;
     private String nombre;
     
    public AgregarImparticion() {
    }

    public InterfazDAO getiDAO() {
        return iDAO;
    }

    public void setiDAO(InterfazDAO iDAO) {
        this.iDAO = iDAO;
    }

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String guardarImparticion(){
        //Creamos una impartición vacía y le damos atributos.
       // Imparticion i= new Imparticion();
        // buscarCursoID busca un objeto curso que coincida con la ID
       // i.setIdCurso(iDAO.buscarCursoID(idCurso));
       // i.setFechaInicio(fechaInicio);
        //i.setFechaFin(fechaFin);
       // i.setNombre(nombre);
        //A través de este método de la interfaz, mandamos los datos a la bbdd.
        //iDAO.persist(i);
        System.out.println(idCurso);
        System.out.println(fechaInicio);
        System.out.println(fechaFin);
        System.out.println(nombre);
        return "menuAdmin";
    }
}
