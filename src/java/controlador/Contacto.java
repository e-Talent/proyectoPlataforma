/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author Joaquín
 */
@ManagedBean
@RequestScoped
public class Contacto implements Serializable {

    private MapModel simpleModel;

    @PostConstruct
    public void init() {
        simpleModel = new DefaultMapModel();
        //Shared coordinates
        LatLng coord1 = new LatLng(37.869052, -4.181127);
        //Basic marker
        simpleModel.addOverlay(new Marker(coord1, "Konyaalti"));
    }

    public MapModel getSimpleModel() {
        return simpleModel;
    }
}
