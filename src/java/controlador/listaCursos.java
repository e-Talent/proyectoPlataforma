package controlador;



import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import modelo.DAO.InterfazDAO;
import persistencia.Curso;

@ManagedBean
@RequestScoped
public class listaCursos {

    @ManagedProperty("#{cDAO}")   
    private InterfazDAO iDAO; 
    private List<Curso> lista;
    
    public listaCursos() {
    }

    public InterfazDAO getiDAO() {
        return iDAO;
    }

    public void setiDAO(InterfazDAO iDAO) {
        this.iDAO = iDAO;
    }

    public List<Curso> getLista() {
        return lista;
    }

    public void setLista(List<Curso> lista) {
        this.lista = lista;
    }
            
    public String listarCursos () {
    lista=iDAO.listarCursos();
    return "imparticion";
    }
}
