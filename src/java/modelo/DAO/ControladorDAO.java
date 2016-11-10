package modelo.DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import persistencia.Curso;
import persistencia.Imparticion;
import persistencia.Matricula;
import persistencia.Usuario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    @Override
    public Usuario login(String usuario, String password) {
        Query query = em.createNamedQuery("Usuario.login");
        query.setParameter("usuario", usuario);
        query.setParameter("password", password);
        Usuario resultado = (Usuario) query.getSingleResult();
        return resultado;
    }

//La fecha de fin debe ser menor a la de hoy
    @Override
    public List<Imparticion> imparticionesActivas() {
        Query query = em.createNamedQuery("Imparticion.findAll");
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaActual = null;
        try {
            fechaActual = sd.parse(sd.format(new Date()));
        } catch (ParseException ex) {
            ex.getMessage();
        }
        List<Imparticion> aux = query.getResultList();
        List<Imparticion> lista = new ArrayList<>();
        for (Imparticion imparticion : aux) {
            if (imparticion.getFechaFin().compareTo(fechaActual) > 0) {
                lista.add(imparticion);
            }
        }
        return lista;
    }
}
