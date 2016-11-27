package modelo.DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Component;
import persistencia.Imparticion;
import persistencia.Matricula;

@Component(value = "iDAO")
public class ImparticionDAO implements InterfazImparticion {

    @PersistenceContext(name = "Proyecto_PlataformaPU")
    EntityManager em;

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
     * Método que utiliza la entidad impartición de la base de datos. Recupera
     * todas las imparticiones guardadas y comparando la fecha final de la
     * impartición con la actual los guarda en la lista de imparticiones activas
     * o no.
     */
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
     * Método que hace una busqueda en la base de datos y devuelve un objeto
     * impartición en base a la idImpartición introducido
     *
     * @param idImparticion
     */
    @Override
    public Imparticion buscarTemario(int idImparticion) {
        return em.find(Imparticion.class, idImparticion);
    }

}
