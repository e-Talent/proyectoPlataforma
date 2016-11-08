package Managed_Beans;

import DAO.Imparticion;
import DAO.Matricula;
import DAO.Usuario;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import negocio.ControladorPrueba;

@ManagedBean
@RequestScoped
public class ListadoAlumnos {
    
ControladorPrueba cp = new ControladorPrueba();
private List<Usuario> listaUsuarios = null;
private List<Imparticion> listaImparticiones = null;
private List<Matricula> listaMatriculas = null;
    
     public ListadoAlumnos() {
    }
     
     public String listarAlumnos() {
     listaUsuarios = cp.listarUsuarios();
     listaImparticiones = cp.listarImparticiones();
     listaMatriculas = cp.listarMatriculas();
     return "listadoPrueba";
     }

    public List<Usuario> getLista() {
        return listaUsuarios;
    }

    public void setLista(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Imparticion> getListaImparticiones() {
        return listaImparticiones;
    }

    public void setListaImparticiones(List<Imparticion> listaImparticiones) {
        this.listaImparticiones = listaImparticiones;
    }

    public List<Matricula> getListaMatriculas() {
        return listaMatriculas;
    }

    public void setListaMatriculas(List<Matricula> listaMatriculas) {
        this.listaMatriculas = listaMatriculas;
    }
         
    
    
}
