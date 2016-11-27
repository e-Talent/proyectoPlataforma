package modelo.DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Component;
import persistencia.Curso;

@Component(value = "cuDAO")
public class CursoDAO implements InterfazCurso {

    @PersistenceContext(name = "Proyecto_PlataformaPU")
    EntityManager em;

    /**
     * Método que nos devolverá el curso cuyo id coincida con el parámetro
     * introducido.
     *
     * @param idCurso
     * @return curso
     */
    @Override
    public Curso buscarCursoID(int idCurso) {
        // find es un método de jpa para buscar por la primary key objetos
        Curso curso = em.find(Curso.class, idCurso);
        return curso;
    }

    /**
     * Método con el que obtendremos una lista de todos los cursos existentes en
     * la BD que contengan la cadena pasada como parámetro
     *
     * @param nombre
     * @return lista
     */
    @Override
    public List<Curso> listarCursosNombre(String nombre) {
        Query query = em.createNamedQuery("Curso.findByNombre");
        query.setParameter("nombre", "%" + nombre + "%");
        List<Curso> lista = query.getResultList();
        return lista;
    }

    /**
     * Método con el que obtendremos una lista de todos los cursos existentes
     *
     * @return lista
     */
    @Override
    public List<Curso> listarCursos() {
        Query query = em.createNamedQuery("Curso.findAll");
        List<Curso> lista = query.getResultList();
        return lista;
    }

}
