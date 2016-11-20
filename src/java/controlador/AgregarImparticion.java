package controlador;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import modelo.DAO.InterfazDAO;
import persistencia.Imparticion;

@ManagedBean
@RequestScoped
/**
 * Clase que se encargará de guardar las nuevas implantaciones en la base de
 * datos
 */
public class AgregarImparticion {

    @ManagedProperty("#{cDAO}")
    private InterfazDAO iDAO;
    private int idCurso;
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

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
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

    /**
     * Metodo que se encarga de crear un nuevo objeto imparticion y añadirle los
     * valores recibidos desde "imparticion.xhtml". Una vez creado el objeto se
     * guarda en la base de datos y se nos dirigirá a menuAdmin.xhtml
     *
     * @return menuAdmin
     */
    public String guardarImparticion() {
        //Creamos una impartición vacía y le damos atributos.
        Imparticion i = new Imparticion();
        // buscarCursoID busca un objeto curso que coincida con la ID que se ha 
        //recibido de imparticion.xhtml       
        i.setIdCurso(iDAO.buscarCursoID(idCurso));
        i.setFechaInicio(fechaInicio);
        i.setFechaFin(fechaFin);
        i.setNombre(nombre);
        //A través de este método de la interfaz, mandamos los datos a la bbdd.
        iDAO.persist(i);
        return "menuAdmin";
    }
}
