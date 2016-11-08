package negocio;

import DAO.Controlador;
import DAO.Imparticion;
import DAO.Matricula;
import DAO.Usuario;
import java.util.List;

public class ControladorPrueba {
    
    Controlador cn = new Controlador();
    
    public List<Usuario> listarUsuarios () {
    return cn.listarUsuarios();
    }
    
    public List<Imparticion> listarImparticiones() {
    return cn.listarImparticiones();
    }
    
       public List<Matricula> listarMatriculas() {
    return cn.listarMatriculas();
    }
    
}
