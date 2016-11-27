package modelo.DAO;

import java.util.List;
import persistencia.Curso;

public interface InterfazCurso {

    public Curso buscarCursoID(int idCurso);

    public List<Curso> listarCursosNombre(String nombre);
    
     public List<Curso> listarCursos();   

}
