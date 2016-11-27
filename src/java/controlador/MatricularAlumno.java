package controlador;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import modelo.DAO.InterfazDAO;
import modelo.negocio.InterfazEmail;
import persistencia.Imparticion;
import persistencia.Matricula;
import persistencia.Usuario;

@ManagedBean
@RequestScoped
public class MatricularAlumno {

    @ManagedProperty("#{cDAO}")
    private InterfazDAO iDAO;
    @ManagedProperty("#{email}")
    private InterfazEmail email;
    private int idImparticion;
    private String DNI;
    private int idMatricula;

    public MatricularAlumno() {
    }

    /**
     * Método con el que creamos un objeto matrícula y le introducimos el dni y
     * el IdImparticion para despues agregarlo a la BD. La navegación nos
     * llevará a "menuAdmin.xhtml"
     *
     * @return "menuAdmin"
     */
    public String matricular() {
        Matricula m = new Matricula();      
        Usuario usuario = iDAO.buscarUsuarioDNI(DNI);
        if (usuario != null) {
            m.setDni(usuario);
            Imparticion i = iDAO.buscarImparticionID(idImparticion);
            m.setIdImparticion(i);
            iDAO.persist(m);
            email.matricula(usuario.getEmail(),usuario.getNombre(),i.getNombre());
             FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Matricula correcta", usuario.getNombre()+" ha sio matriculado correctamente en "+i.getNombre()));
            DNI=null;
            return null;
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Error", "El DNI introducido no se encuentra en la base de datos"));
            return null;
        }
    }

    
    
    public String baja(int idMatricula) {       
        Matricula matricula = iDAO.buscarMatricula(idMatricula);
        iDAO.bajaAlumno(idMatricula);        
        String nombre = matricula.getDni().getNombre();
        String curso = matricula.getIdImparticion().getNombre();
        String destinatario = matricula.getDni().getEmail();
        email.baja(destinatario, nombre, curso);
        return "menuAdmin";
    }

    public int getIdImparticion() {
        return idImparticion;
    }

    public void setIdImparticion(int idImparticion) {
        this.idImparticion = idImparticion;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public InterfazDAO getiDAO() {
        return iDAO;
    }

    public void setiDAO(InterfazDAO iDAO) {
        this.iDAO = iDAO;
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }

    public InterfazEmail getEmail() {
        return email;
    }

    public void setEmail(InterfazEmail email) {
        this.email = email;
    }

}
