package modelo.DAO;

/**
 * Interfaz en la que definimos las clases necesarias par el envío autmático de
 * email cuando ocurren distintos procesos
 * 
 * @author Talentum Java
 */
public interface InterfazEmail {

    public void matricula(String destinatario, String nombre, String curso);
    
    public void baja(String destinatario, String nombre, String curso);

    public void enviar(String destino, String asunto, String mensaje);

}
