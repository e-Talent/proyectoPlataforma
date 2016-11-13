
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
public class AgregarCurso {

     @ManagedProperty("#{cDAO}")
     private InterfazDAO iDAO;
     private String urlDocumento;
     private String nombre;
     private String descripcion;  
     private UploadedFile temario;
     private String destination="D:\\";
    
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
       
    public String guardarCurso(){
        //Creamos un curso vacío y le damos nombre y descripción (y el documento, en los que los lleven)
        //Curso c= new Curso();
        //c.setNombre(nombre);
        //c.setDescripcion(descripcion);     
            try {
            guardarTemario(temario.getFileName(), temario.getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //A través de este método de la interfaz, mandamos los datos a la bbdd.
        //iDAO.persist(c);
        return "imparticion";
    } 

  public void guardarTemario(String fileName, InputStream in) {
    
           try {                            
                // write the inputStream to a FileOutputStream
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
