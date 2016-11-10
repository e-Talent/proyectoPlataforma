package modelo.DAO;

import persistencia.Curso;
import persistencia.Imparticion;
import persistencia.Matricula;
import persistencia.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component(value = "cDAO")
public class ControladorDAO implements InterfazDAO {

    @PersistenceContext(name = "Proyecto_PlataformaPU")
    EntityManager em;  
  
    @Transactional()
    @Override
    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        Query query = em.createNamedQuery("Usuario.findAll");
        List<Usuario> lista = query.getResultList();
        return lista;
    }

    @Override
    public List<Curso> listarCursos() {
        Query query = em.createNamedQuery("Curso.findAll");
        List<Curso> lista = query.getResultList();
        return lista;
    }

    @Override
    public List<Imparticion> listarImparticiones() {
        Query query = em.createNamedQuery("Imparticion.findAll");
        List<Imparticion> lista = query.getResultList();
        return lista;
    }

    @Override
    public List<Matricula> listarMatriculas() {
        Query query = em.createNamedQuery("Matricula.findAll");
        List<Matricula> lista = query.getResultList();
        return lista;
    }

}
