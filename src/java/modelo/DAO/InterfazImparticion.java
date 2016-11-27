package modelo.DAO;

import java.util.List;
import persistencia.Imparticion;
import persistencia.Matricula;

public interface InterfazImparticion {

    public List<Imparticion> listarImparticiones();

    public List<Imparticion> imparticionesActivas();

    public Imparticion buscarImparticionID(int idImparticion);

    public List<Matricula> imparticionesAlumno(String DNI);
    
     public Imparticion buscarTemario(int idImparticion);

}
