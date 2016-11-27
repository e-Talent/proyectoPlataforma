package modelo.DAO;

import java.util.List;
import persistencia.Matricula;

public interface InterfazMatricula {

    public List<Matricula> listarMatriculas();

    public Matricula buscarMatricula(String dni, int idImparticion);

    public Matricula buscarMatricula(int idMatricula);

    public void bajaAlumno(int idMatricula);

    public void ponerNota(String dni, int idImparticion, double nota);

}
