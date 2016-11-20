package controlador;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import modelo.DAO.InterfazDAO;
import persistencia.Imparticion;
import persistencia.Matricula;
import persistencia.Usuario;

@ManagedBean
@SessionScoped
public class Login {

    @ManagedProperty("#{cDAO}")
    private InterfazDAO iDAO;
    private String usuario;
    private String password;
    private Usuario user;
    private List<Imparticion> imparticionesActivas;
    private List<Matricula> listaMatriculadas;

    public Login() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public InterfazDAO getiDAO() {
        return iDAO;
    }

    public void setiDAO(InterfazDAO iDAO) {
        this.iDAO = iDAO;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public List<Imparticion> getImparticionesActivas() {
        return imparticionesActivas;
    }

    public void setImparticionesActivas(List<Imparticion> imparticionesActivas) {
        this.imparticionesActivas = imparticionesActivas;
    }

    public List<Matricula> getListaMatriculadas() {
        return listaMatriculadas;
    }

    public void setListaMatriculadas(List<Matricula> listaMatriculadas) {
        this.listaMatriculadas = listaMatriculadas;
    }

    /**
     * Método que recogerá los datos introducidos en "index.xhtml". Comprobará
     * si el usuario que está intentando entrar está registrado o no. Si está
     * registrado comprobará si es administrador o almuno
     *
     * @return ruta
     */
    public String validacionLogin() {
        String ruta;
        //Llamada al método login de la interfaz
        user = iDAO.login(usuario, password);
        if (user != null) {
            if (user.getRol().equals("admin")) {
                imparticionesActivas = iDAO.imparticionesActivas();//Muestra los cursos activos
                ruta = "menuAdmin";
            } else {
                //Muestra los cursos en los que un alumno está matrículado
                listaMatriculadas = iDAO.imparticionesAlumno(user.getDni());
                ruta = "menuAlumno";
            }
        } else {
            ruta = null;
        }
        return ruta;
    }
}
