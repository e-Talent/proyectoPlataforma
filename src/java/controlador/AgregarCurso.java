package controlador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import modelo.DAO.InterfazDAO;
import org.primefaces.model.UploadedFile;
import persistencia.Curso;

@ManagedBean
@RequestScoped
/**
 * Clase que se encargará de guardar un nuevo curso en la base de datos
 */
public class AgregarCurso {

    @ManagedProperty("#{cDAO}")
    private InterfazDAO iDAO;
    private String urlDocumento;
    private String nombre;
    private String descripcion;
    private UploadedFile temario;
    private String destination = "C:\\NeatBean\\plataformaCursos\\web\\resources\\";

    public AgregarCurso() {
    }

    public InterfazDAO getiDAO() {
        return iDAO;
    }

    public void setiDAO(InterfazDAO iDAO) {
        this.iDAO = iDAO;
    }

    public String getUrlDocumento() {
        return urlDocumento;
    }

    public void setUrlDocumento(String urlDocumento) {
        this.urlDocumento = urlDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public UploadedFile getTemario() {
        return temario;
    }

    public void setTemario(UploadedFile temario) {
        this.temario = temario;
    }
/**
     * Metodo que se encarga de crear un nuevo objeto curso y añadirle los
     * valores recibidos desde "altacurso.xhtml". Una vez creado el objeto se
     * guarda en la base de datos y se nos dirigirá a "imparticion.xhtml"
     *
     * @return menuAdmin
     * @return imparticion (.xhtml a la que nos dirigimos tras ejecutar el
     * método)
     */
    public String guardarCurso() {
        //Creamos un objeto curso vacío y le damos nombre, descripción y el 
        //documento, (aunque no es obligatorio).
        Curso c = new Curso();
        urlDocumento = temario.getFileName();
        c.setNombre(nombre);
        c.setDescripcion(descripcion);
        c.setDocumento(urlDocumento);        
        try {
            guardarTemario(temario.getFileName(), temario.getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //A través de este método de la interfaz iDAO, mandamos los datos a la bbdd.
        iDAO.persist(c);
        return "imparticion";
    }

    /**
     * Método que recibe el nombre del archivo y el archivo en bytes y lo guarda
     * en la ruta indicada (guardamos bytes)
     *
     * @param fileName (Nombre del archivo)
     * @param in (Archivo en formato Stream)
     */
    public void guardarTemario(String fileName, InputStream in) {

        try {
            // Pasamos de InputStream a FileOutputStream e indicamos donde se guarda
            OutputStream out = new FileOutputStream(new File(destination + fileName));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
            System.out.println("New file created!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
