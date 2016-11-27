package controlador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import modelo.DAO.InterfazDAO;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;
import persistencia.Usuario;

@ManagedBean
@RequestScoped
public class AgregarUsuario {

    @ManagedProperty("#{cDAO}")
    private InterfazDAO iDAO;

    private String dni;
    private String nombre;
    private String apellido;
    private String usuario;
    private String password;
    private String telefono;
    private String email;
    private String rol;
    private UploadedFile foto;
    private String destination = "D:\\Proyecto_Plataforma_new\\web\\resources\\";

    public AgregarUsuario() {
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public UploadedFile getFoto() {
        return foto;
    }

    public void setFoto(UploadedFile foto) {
        this.foto = foto;
    }

    public InterfazDAO getiDAO() {
        return iDAO;
    }

    public void setiDAO(InterfazDAO iDAO) {
        this.iDAO = iDAO;
    }

    public byte[] prepararArchivo() {
        byte[] fotoBytes = null;
        try {
            fotoBytes = IOUtils.toByteArray(foto.getInputstream());
        } catch (IOException ex) {
            Logger.getLogger(AgregarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fotoBytes;
    }

    /**
     * Método que recibe el nombre del archivo y el archivo en bytes y lo guarda
     * en la ruta indicada (guardamos bytes).
     *
     * @param fileName
     * @param in
     */
    public void guardarFoto(String fileName, InputStream in) {

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

    /**
     * Metodo que se encarga de crear un nuevo objeto usuario y añadirle los
     * valores recibidos desde "altaUsuario.xhtml". Una vez creado el objeto se
     * guarda en la base de datos y se nos dirigirá a index.xhtml
     *
     * @return index
     */
    public String guardarUsuario() {
        //Creamos una impartición vacía y le damos atributos.
        Usuario u = new Usuario();
        // buscarCursoID busca un objeto curso que coincida con la ID
        u.setDni(dni);
        u.setNombre(nombre);
        u.setApellidos(apellido);
        u.setUsuario(usuario);
        u.setPassword(password);
        u.setTelefono(telefono);
        u.setEmail(email);
        u.setRol(rol);
        u.setFoto("resources/" + foto.getFileName());
        try {
            guardarFoto(foto.getFileName(), foto.getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //A través de este método de la interfaz, mandamos los datos a la bbdd.
        iDAO.persist(u);
        return "index";
    }

}
