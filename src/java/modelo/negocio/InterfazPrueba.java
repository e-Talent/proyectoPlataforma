package modelo.negocio;

import java.util.List;
import persistencia.Imparticion;
import persistencia.Matricula;
import persistencia.Usuario;

public interface InterfazPrueba { 
         
    public List<Usuario> listarUsuarios();
    
    public List<Imparticion> listarImparticiones();
    
    public List<Matricula> listarMatriculas();
        
}
