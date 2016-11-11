package modelo.negocio;

import modelo.DAO.ControladorDAO;
import persistencia.Imparticion;
import persistencia.Matricula;
import persistencia.Usuario;
import java.util.List;
import org.springframework.stereotype.Component;

@Component(value="cp")
public class ControladorPrueba implements InterfazPrueba {
    
    ControladorDAO cn = new ControladorDAO();
    
    @Override
    public List<Usuario> listarUsuarios () {
    return cn.listarUsuarios();
    }
    
    @Override
    public List<Imparticion> listarImparticiones() {
    return cn.listarImparticiones();
    }
    
    @Override
       public List<Matricula> listarMatriculas() {
    return cn.listarMatriculas();
    }
    
}
