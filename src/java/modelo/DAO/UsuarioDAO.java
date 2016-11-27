package modelo.DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Component;
import persistencia.Usuario;

@Component(value = "uDAO")
public class UsuarioDAO implements InterfazUsuario {

    @PersistenceContext(name = "Proyecto_PlataformaPU")
    EntityManager em;

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

    /**
     * Método que nos devolverá el curso cuyo id coincida con el parámetro
     * introducido.
     *
     * @param DNI
     * @return curso
     */
    @Override
    public Usuario buscarUsuarioDNI(String DNI) {
        Usuario u = em.find(Usuario.class, DNI);
        return u;
    }

}
