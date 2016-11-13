package controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import modelo.DAO.InterfazDAO;
import org.primefaces.model.StreamedContent;

@ManagedBean
@RequestScoped
public class DescargarFoto {

    @ManagedProperty("#{cDAO}")
    private InterfazDAO iDAO;
    private StreamedContent foto;
    private String dni;

    public DescargarFoto() {
    }

    public InterfazDAO getiDAO() {
        return iDAO;
    }

    public void setiDAO(InterfazDAO iDAO) {
        this.iDAO = iDAO;
    }

    public StreamedContent getFile() {
        return foto;
    }

    public void setFile(StreamedContent file) {
        this.foto = file;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
    public String cargarFoto() {  
     foto = iDAO.descargarFoto(dni);
     return "index";
     }

}
