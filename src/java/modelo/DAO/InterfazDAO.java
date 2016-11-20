package modelo.DAO;

import persistencia.Curso;
import persistencia.Imparticion;
import persistencia.Matricula;
import persistencia.Usuario;
import java.util.List;
import persistencia.Examen;

/**
 * La interfaz contendrá la declaración de todos los métodos que se implementarán
 * en en el controlador.
 * @author Talentum Java
 */
public interface InterfazDAO {

    public void persist(Object object);

    public List<Usuario> listarUsuarios();

    public List<Curso> listarCursos();

    public List<Imparticion> listarImparticiones();

    public List<Matricula> listarMatriculas();

    public Usuario login(String usuario, String password);

    public List<Imparticion> imparticionesActivas();

    public Curso buscarCursoID(int idCurso);

    public Imparticion buscarImparticionID(int idImparticion);

    public Usuario buscarUsuarioDNI(String DNI);

    public List<Curso> listarCursosNombre(String nombre);

    public List<Matricula> imparticionesAlumno(String DNI);

    public List<Examen> cargarExamen(int idImparticion);

    public Imparticion buscarTemario(int idImparticion);

    public void ponerNota(String dni, int idImparticion, double nota);
    
    public Matricula buscarMatricula(String dni, int idImparticion);

}
