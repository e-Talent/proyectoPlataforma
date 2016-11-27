package controlador;

import persistencia.Matricula;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import modelo.DAO.InterfazMatricula;

@ManagedBean
@RequestScoped
/**
 * Clase que se encargará de recuperar una lista de alumnos
 */
public class ListadoAlumnos {
 
@ManagedProperty("#{mDAO}")   
private InterfazMatricula mDAO; 

private List<Matricula> listaMatriculas = null;
    
     public ListadoAlumnos() {
    }
     /**
      * Método con el que obtendremos una lista de todos los alumnos que están
      * o han estado matriculados en alguna impartición
      * @return listadoPrueba
      */
     public String listarAlumnos() {  
     listaMatriculas = mDAO.listarMatriculas();
     return "listadoPrueba";
     }
  
    public List<Matricula> getListaMatriculas() {
        return listaMatriculas;
    }

    public void setListaMatriculas(List<Matricula> listaMatriculas) {
        this.listaMatriculas = listaMatriculas;
    }

    public InterfazMatricula getmDAO() {
        return mDAO;
    }

    public void setmDAO(InterfazMatricula mDAO) {
        this.mDAO = mDAO;
    }
   
    
}
