
package controlador;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import modelo.DAO.InterfazDAO;
import persistencia.Imparticion;
import persistencia.Usuario;

@ManagedBean
@RequestScoped
public class Login {
    @ManagedProperty("#{cDAO}")   
    private InterfazDAO iDAO; 
    private String usuario;
    private String password;
    private Usuario user;    
    private List<Imparticion> imparticionesActivas;
    
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
               
    public String validacionLogin(){
        String ruta;
        user=iDAO.login(usuario, password);
        if (user!=null) {
            if (user.getRol().equals("admin")) {
            imparticionesActivas = iDAO.imparticionesActivas();
            ruta="menuAdmin";
            } else {
            ruta="menuAlumno";            
            }            
        } else {
        ruta=null;
        }
        return ruta;
    }

    public List<Imparticion> getImparticionesActivas() {
        return imparticionesActivas;
    }

    public void setImparticionesActivas(List<Imparticion> imparticionesActivas) {
        this.imparticionesActivas = imparticionesActivas;
    }
}
