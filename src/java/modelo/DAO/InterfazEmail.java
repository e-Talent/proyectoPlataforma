package modelo.DAO;

public interface InterfazEmail {

    public void matricula(String destinatario, String nombre, String curso);
    
    public void baja(String destinatario, String nombre, String curso);

    public void enviar(String destino, String asunto, String mensaje);

}
