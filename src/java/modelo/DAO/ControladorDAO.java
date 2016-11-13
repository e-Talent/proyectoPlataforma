package modelo.DAO;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import persistencia.Curso;
import persistencia.Imparticion;
import persistencia.Matricula;
import persistencia.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import persistencia.Examen;

@Component(value = "cDAO")
public class ControladorDAO implements InterfazDAO {

    @PersistenceContext(name = "Proyecto_PlataformaPU")
    EntityManager em;

    @Transactional()
    @Override
    //Guarda cualquier tipo de dato (objeto) en la bbdd.
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

    @Override
    public Curso buscarCursoID(int idCurso) {
        // find es un método de jpa para buscar por la primary key objetos
        Curso curso = em.find(Curso.class, idCurso);
        return curso;
    }

    @Override
    public Imparticion buscarImparticionID(int idImparticion) {
        Imparticion i = em.find(Imparticion.class, idImparticion);
        return i;
    }

    @Override
    public Usuario buscarUsuarioDNI(String DNI) {
        Usuario u = em.find(Usuario.class, DNI);
        return u;
    }

    @Override
    public List<Curso> listarCursosNombre(String nombre) {
        Query query = em.createNamedQuery("Curso.findByNombre");
        query.setParameter("nombre", "%" + nombre + "%");
        List<Curso> lista = query.getResultList();
        return lista;
    }

    @Override
    public List<Matricula> imparticionesAlumno(String DNI) {
        //Obtenemos una lista con todas las matrículas
        Query query = em.createNamedQuery("Matricula.findAll");
        List<Matricula> aux = query.getResultList();
        List<Matricula> lista = new ArrayList<>();
        //Buscamos las matrículas que coincidan con el alumno
        for (Matricula m : aux) {
            if (m.getDni().getDni().equals(DNI)) {
                lista.add(m);
            }
        }
        return lista;
    }

    @Override
    public List<Examen> cargarExamen(String idImparticion) {
        Query query = em.createNamedQuery("Examen.findAll");
        List<Examen> aux = query.getResultList();
        List<Examen> lista = new ArrayList<>();
        for (Examen e : aux) {
            if (e.getIdImparticion().getIdImparticion() == Integer.parseInt(idImparticion)) {
                lista.add(e);
            }
        }
        return lista;
    }

    @Override
    public StreamedContent descargarFoto(String dni) {
        Query query = em.createNamedQuery("Usuario.findByDni");
        query.setParameter("dni", dni);
        Usuario resultado = (Usuario) query.getSingleResult();
        byte[] f = resultado.getFoto();
        InputStream fotoStream = new ByteArrayInputStream(f);
        StreamedContent foto;
        foto = new DefaultStreamedContent(fotoStream);
        return foto;
    }
}
