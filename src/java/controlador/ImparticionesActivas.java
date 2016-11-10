package controlador;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import modelo.DAO.InterfazDAO;
import persistencia.Imparticion;

@ManagedBean
@SessionScoped
public class ImparticionesActivas {

    @ManagedProperty("#{cDAO}")
    private InterfazDAO iDAO;
    private List<Imparticion> lista;

    public ImparticionesActivas() {
    }

    public String imparticionesActivas() {
        lista = iDAO.imparticionesActivas();
        return "matricularAlumnos";
    }

      public String darBajaAlumno() {
        lista = iDAO.imparticionesActivas();
        return "bajaAlumno";
    }
    
    public List<Imparticion> getLista() {
        return lista;
    }

    public void setLista(List<Imparticion> lista) {
        this.lista = lista;
    }

    public InterfazDAO getiDAO() {
        return iDAO;
    }

    public void setiDAO(InterfazDAO iDAO) {
        this.iDAO = iDAO;
    }

}
