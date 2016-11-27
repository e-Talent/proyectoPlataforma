package modelo.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import persistencia.Examen;
import persistencia.Imparticion;
import persistencia.Pregunta;
import persistencia.Respuesta;

@Component(value = "eDAO")
public class ExamenDAO implements InterfazExamen {

    @PersistenceContext(name = "Proyecto_PlataformaPU")
    EntityManager em;

    /**
     * Método con el que creamos exámenes para una determinada impartición.
     *
     * @param textoRespuesta1
     * @param textoRespuesta2
     * @param textoRespuesta3
     * @param textoRespuesta4
     * @param textoPregunta
     * @param respuestaCorrecta
     * @param idImparticion
     */
    @Transactional
    @Override
    public void crearExamen(String textoRespuesta1, String textoRespuesta2, String textoRespuesta3, String textoRespuesta4, String textoPregunta, int respuestaCorrecta, int idImparticion) {
        //Creamos un objeto impartición que coincidirá con el pasado con la idImpartición
        Imparticion imparticion = em.find(Imparticion.class, idImparticion);
        Pregunta pregunta = new Pregunta(); //Creamos un objeto pregunta vacio
        pregunta.setEnunciado(textoPregunta); //Añadimos el enunciado al objeto anterior
        em.persist(pregunta);//Guardamos los datos en la base de datos
        //Creamos varios objetos respuesta
        Respuesta respuesta1 = new Respuesta();
        respuesta1.setRespuesta(textoRespuesta1);
        respuesta1.setIdPregunta(pregunta);
        em.persist(respuesta1);
        if (respuestaCorrecta == 1) {
            //Cogemos la idRespuesta que necesitamos para la respuestaa
            pregunta.setRespuestaCorrecta(respuesta1.getIdRespuesta());
        }
        Respuesta respuesta2 = new Respuesta();
        respuesta2.setRespuesta(textoRespuesta2);
        respuesta2.setIdPregunta(pregunta);
        em.persist(respuesta2);
        if (respuestaCorrecta == 2) {
            pregunta.setRespuestaCorrecta(respuesta2.getIdRespuesta());
        }
        Respuesta respuesta3 = new Respuesta();
        respuesta3.setRespuesta(textoRespuesta3);
        respuesta3.setIdPregunta(pregunta);
        em.persist(respuesta3);
        if (respuestaCorrecta == 3) {
            pregunta.setRespuestaCorrecta(respuesta3.getIdRespuesta());
        }
        Respuesta respuesta4 = new Respuesta();
        respuesta4.setRespuesta(textoRespuesta4);
        respuesta4.setIdPregunta(pregunta);
        em.persist(respuesta4);
        if (respuestaCorrecta == 4) {
            pregunta.setRespuestaCorrecta(respuesta4.getIdRespuesta());
        }
        Examen examen = new Examen(); //Creamo un objeto exámen
        examen.setIdImparticion(imparticion); //Añadimos el exámen a una idImpartición
        examen.setIdPregunta(pregunta); //Añadimos el exámen a una idPregunta
        em.persist(examen);
    }

    /**
     * Realizamos una consulta a la BD que nos devuelca todos los examenes
     * existentes para despues limitar la lista al examen que corresponde a la
     * id pasada como parámetros
     *
     * @param idImparticion
     * @return lista
     */
    @Override
    public List<Examen> cargarExamen(int idImparticion) {
        Query query = em.createNamedQuery("Examen.findAll");
        List<Examen> aux = query.getResultList();
        List<Examen> lista = new ArrayList<>();
        for (Examen e : aux) {
            //De exámen vamos al objeto impartición y de ahí a al int IdImparción
            //Comparamos este dato con el pasado como parámetro
            if (e.getIdImparticion().getIdImparticion() == idImparticion) {
                lista.add(e);
            }
        }
        return lista;
    }

}
