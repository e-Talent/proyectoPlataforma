
package controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class CorregirExamen {

    private String respuestaPregunta1;
    private String respuestaPregunta2;
    private String respuestaPregunta3;
    private String respuestaPregunta4;
    private String respuestaPregunta5;
    
    public CorregirExamen() {
    }

    public String corregir() {
        System.out.println("respuestaPregunta1");
        System.out.println("respuestaPregunta2");
        System.out.println("respuestaPregunta3");
        System.out.println("respuestaPregunta4");
        System.out.println("respuestaPregunta5");
        return "menuAlumno";
    }
    
    public String getRespuestaPregunta1() {
        return respuestaPregunta1;
    }

    public void setRespuestaPregunta1(String respuestaPregunta1) {
        this.respuestaPregunta1 = respuestaPregunta1;
    }

    public String getRespuestaPregunta2() {
        return respuestaPregunta2;
    }

    public void setRespuestaPregunta2(String respuestaPregunta2) {
        this.respuestaPregunta2 = respuestaPregunta2;
    }

    public String getRespuestaPregunta3() {
        return respuestaPregunta3;
    }

    public void setRespuestaPregunta3(String respuestaPregunta3) {
        this.respuestaPregunta3 = respuestaPregunta3;
    }

    public String getRespuestaPregunta4() {
        return respuestaPregunta4;
    }

    public void setRespuestaPregunta4(String respuestaPregunta4) {
        this.respuestaPregunta4 = respuestaPregunta4;
    }

    public String getRespuestaPregunta5() {
        return respuestaPregunta5;
    }

    public void setRespuestaPregunta5(String respuestaPregunta5) {
        this.respuestaPregunta5 = respuestaPregunta5;
    }
    
    
    
}
