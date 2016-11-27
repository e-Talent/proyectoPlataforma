package modelo.negocio;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Component;

@Component(value = "email")
/**
 * Clase que desarrolla los métodos definidos en la interfaz para el envío
 * automático de email
 */
public class Email implements InterfazEmail {

    /**
     * Método en el que definimos los parámetros que se enviarán al usuario
     * cuando se de baja en curso
     *
     * @param destinatario
     * @param nombre
     * @param curso
     */
    @Override
    public void baja(String destinatario, String nombre, String curso) {
        String destino = destinatario;
        String asunto = "Baja del curso: " + curso;
        String mensaje = "Hola " + nombre + ",\nHas sido dado de baja en el curso: " + curso;
        enviar(destino, asunto, mensaje);
    }

    /**
     * Método en el que definimos los parámetros que se enviarán al usuario
     * cuando se matricula ee un curso
     *
     * @param destinatario
     * @param nombre
     * @param curso
     */
    @Override
    public void matricula(String destinatario, String nombre, String curso) {
        String destino = destinatario;
        String asunto = "Matricula " + curso;
        String mensaje = "Hola " + nombre + ",\nEstás correctamente matriculado en: " + curso;
        enviar(destino, asunto, mensaje);
    }

    /**
     * Método que define los parámetros para conectar a nuestro email y desde él
     * enviar los correos automáticos
     * @param destino
     * @param asunto
     * @param mensaje 
     */
    @Override
    public void enviar(String destino, String asunto, String mensaje) {
        String usuario = "talentumjava@gmail.com";
        String password = "escuadronsuicida";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(usuario));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destino));
            message.setSubject(asunto);
            message.setText(mensaje);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
