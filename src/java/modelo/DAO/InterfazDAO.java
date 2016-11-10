package modelo.DAO;

import persistencia.Curso;
import persistencia.Imparticion;
import persistencia.Matricula;
import persistencia.Usuario;
import java.util.List;

public interface InterfazDAO {

    public void persist(Object object);

    public List<Usuario> listarUsuarios();

    public List<Curso> listarCursos();

    public List<Imparticion> listarImparticiones();

    public List<Matricula> listarMatriculas();

}
