package modelo.DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import persistencia.Imparticion;
import persistencia.Matricula;
import persistencia.Usuario;

@Component(value = "mDAO")
public class MatriculaDAO implements InterfazMatricula {

    @PersistenceContext(name = "Proyecto_PlataformaPU")
    EntityManager em;

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

    /**
     * Buscamos el objeto matrícula que coincida con el parámetro pasado
     * (idMatricula)
     *
     * @param idMatricula
     * @return matricula
     */
    @Override
    public Matricula buscarMatricula(int idMatricula) {
        Matricula m = em.find(Matricula.class, idMatricula);
        return m;
    }

    @Transactional
    @Override
    public void bajaAlumno(int idMatricula) {
        //Matricula matricula = em.find(Matricula.class, idMatricula);        
        //em.remove(matricula);
        //System.out.println(matricula.getIdMatricula());
        Query query = em.createNamedQuery("Matricula.borrarMatricula");
        query.setParameter("idMatricula", idMatricula);
        query.executeUpdate();
    }

    /**
     * Método que insertará la nota en la tabla matrícula de la BD
     *
     * @param dni
     * @param idImparticion
     * @param nota
     */
    @Transactional
    @Override
    public void ponerNota(String dni, int idImparticion, double nota) {
        Matricula resultado = buscarMatricula(dni, idImparticion);
        resultado.setNota(nota); //Añadimos la nota a la matrícula
    }

}
