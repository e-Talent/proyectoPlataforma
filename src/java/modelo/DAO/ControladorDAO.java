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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import persistencia.Examen;

@Component(value = "cDAO")
public class ControladorDAO implements InterfazDAO {

    @PersistenceContext(name = "Proyecto_PlataformaPU")
    EntityManager em;

    @Transactional()
    @Override
    /**
     * Método que guarda cualquier tipo de dato (objeto) en la bbdd.
     */
    public void persist(Object object) {
        em.persist(object);
    }

    /**
     * Método con el que obtendremos una lista de todos los usuarios existentes
     *
     * @return lista
     */
    @Override
    public List<Usuario> listarUsuarios() {
        Query query = em.createNamedQuery("Usuario.findAll");
        List<Usuario> lista = query.getResultList();
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
     * Método con el que obtendremos una lista de todas las imparticiones
     * existentes.
     *
     * @return lista
     */
    @Override
    public List<Imparticion> listarImparticiones() {
        Query query = em.createNamedQuery("Imparticion.findAll");
        List<Imparticion> lista = query.getResultList();
        return lista;
    }

    /**
     * Método con el que obtendremos una lista de todas las matrículas
     * existentes
     *
     * @return lista
     */
    @Override
    public List<Matricula> listarMatriculas() {
        Query query = em.createNamedQuery("Matricula.findAll");
        List<Matricula> lista = query.getResultList();
        return lista;
    }

    /**
     * Método con el que obtendremos el usuario de la BD cuyo usuario y
     * contraseña coincida con los pasados como parámetros
     *
     * @param usuario
     * @param password
     * @return
     */
    @Override
    public Usuario login(String usuario, String password) {
        Query query = em.createNamedQuery("Usuario.login");
        query.setParameter("usuario", usuario);
        query.setParameter("password", password);
        Usuario resultado = (Usuario) query.getSingleResult();
        return resultado;
    }

    @Override
    /**
     * Método que utiliza la entidad impartición de la base de datos. Recupera
     * todas las imparticiones guardadas y comparando la fecha final de la
     * impartición con la actual los guarda en la lista de imparticiones activas
     * o no.
     */
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
     * Método que nos devolverá la impartición cuya id coincida con el parámetro
     * introducido.
     *
     * @param idImparticion
     * @return imparticion
     */
    @Override
    public Imparticion buscarImparticionID(int idImparticion) {
        Imparticion i = em.find(Imparticion.class, idImparticion);
        return i;
    }

    /**
     * Método que nos devolverá el usuario cuyo dni coincida con el parámetro
     * introducido.
     *
     * @param DNI
     * @return usuario
     */
    @Override
    public Usuario buscarUsuarioDNI(String DNI) {
        Usuario u = em.find(Usuario.class, DNI);
        return u;
    }

    /**
     * Método que llevará a cabo una busqueda en la base de datos de las
     * imparticiones activas para un determinado alumno.
     *
     * @param DNI
     * @return lista
     */
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

    /**
     * Realizamos una consulta a la BD que nos devuelca todos los examenes
     * existentes para despues limitar la lista al examen que corresponde a la
     * id pasada como parámetros
     *
     * @param idImparticion
     * @return lista
     */
    @Override
    public List<Examen> cargarExamen(int idImparticion) {
        Query query = em.createNamedQuery("Examen.findAll");
        List<Examen> aux = query.getResultList();
        List<Examen> lista = new ArrayList<>();
        for (Examen e : aux) {
            //De exámen vamos al objeto impartición y de ahí a al int IdImparción
            //Comparamos este dato con el pasado como parámetro
            if (e.getIdImparticion().getIdImparticion() == idImparticion) {
                lista.add(e);
            }
        }
        return lista;
    }

    /**
     * Método que hace una busqueda en la base de datos y devuelve un objeto
     * impartición en base a la idImpartición introducido
     *
     * @param idImparticion
     */
    @Override
    public Imparticion buscarTemario(int idImparticion) {
        return em.find(Imparticion.class, idImparticion);
    }

    /**
     * Método que insertará la nota en la tabla matrícula de la BD
     *
     * @param dni
     * @param idImparticion
     * @param nota
     */
    @Transactional()
    @Override
    public void ponerNota(String dni, int idImparticion, double nota) {
        Matricula resultado = buscarMatricula(dni, idImparticion);
        resultado.setNota(nota); //Añadimos la nota a la matrícula
    }

    /**
     * Método que realiza una una consulta a la base de datos pasando como
     * parámetro el dni y la idImpartición, de esta forma se identifica de forma
     * única la matricula y por tanto podremos agregarle la nota.
     *
     * @param dni
     * @param idImparticion
     * @return resultado(objeto de la clase matricula)
     */
    @Override
    public Matricula buscarMatricula(String dni, int idImparticion) {
        //Obtengo el objeto usuario con el dni pasado por parámetro
        Usuario usuario = em.find(Usuario.class, dni);
        //Obtengo el objeto impartición con el idImpartición pasado por parámetro
        Imparticion imparticion = em.find(Imparticion.class, idImparticion);
        //Obtenemos el objeto con dni e impartición recuperados arriba
        Query query = em.createNamedQuery("Matricula.ponerNota");
        //Pasamos a la query dos obejtos como parámetros
        query.setParameter("dni", usuario);
        query.setParameter("idImparticion", imparticion);
        //Obtenemos la mátricula que nos interesa con los parametros que hemos pasado
        Matricula resultado = (Matricula) query.getSingleResult();
        return resultado;
    }

}
