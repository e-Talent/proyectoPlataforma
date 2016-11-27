package modelo.DAO;

import java.util.List;
import persistencia.Usuario;

public interface InterfazUsuario {

    public List<Usuario> listarUsuarios();

    public Usuario login(String usuario, String password);

    public Usuario buscarUsuarioDNI(String DNI);

}
