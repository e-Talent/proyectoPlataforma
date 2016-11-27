package controlador;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import modelo.DAO.InterfazImparticion;
import persistencia.Imparticion;

@ManagedBean
@SessionScoped
/**
 * Clase que mostrará una lista de las imparticiones que hay activas en un
 * determinado momento
 */
public class ImparticionesActivas {

    @ManagedProperty("#{iDAO}")
    private InterfazImparticion iDAO;
    private List<Imparticion> lista;

    public ImparticionesActivas() {
    }

    /**
     * Método que se encarga de llamar al método de la interfazDAO(implementado
     * por el controladorDAO) que determina cuales son las imparticiones
     * activas. Por último nos redigirá a la "matricularAlumnos.xhtml" para que
     * solo se pueda matricular alumnos en las imparticiones activas.
     *
     * @return matricularAlumnos
     */
    public String imparticionesActivas() {
        lista = iDAO.imparticionesActivas();
        return "matricularAlumnos";
    }

    /**
     * Método que se encarga de llamar al método de la interfazDAO(implementado
     * por el controladorDAO) que determina cuales son las imparticiones
     * activas. Por último nos redigirá a "bajaAlumno.xhtml" para que solo se
     * pueda dar de baja a los alumnos en imparticiones activas.
     *
     * @return bajaAlumno
     */
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

    public InterfazImparticion getiDAO() {
        return iDAO;
    }

    public void setiDAO(InterfazImparticion iDAO) {
        this.iDAO = iDAO;
    }

}
