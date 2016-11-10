/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mañana
 */
@Entity
@Table(name = "imparticion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Imparticion.findAll", query = "SELECT i FROM Imparticion i"),
    @NamedQuery(name = "Imparticion.findByIdImparticion", query = "SELECT i FROM Imparticion i WHERE i.idImparticion = :idImparticion"),
    @NamedQuery(name = "Imparticion.findByNombre", query = "SELECT i FROM Imparticion i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "Imparticion.findByFechaInicio", query = "SELECT i FROM Imparticion i WHERE i.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Imparticion.findByFechaFin", query = "SELECT i FROM Imparticion i WHERE i.fechaFin = :fechaFin")})
public class Imparticion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idImparticion")
    private Integer idImparticion;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "fechaInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @Column(name = "fechaFin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @JoinColumn(name = "idCurso", referencedColumnName = "idCurso")
    @ManyToOne(optional = false)
    private Curso idCurso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idImparticion")
    private Collection<Matricula> matriculaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idImparticion")
    private Collection<Examen> examenCollection;

    public Imparticion() {
    }

    public Imparticion(Integer idImparticion) {
        this.idImparticion = idImparticion;
    }

    public Imparticion(Integer idImparticion, String nombre, Date fechaInicio, Date fechaFin) {
        this.idImparticion = idImparticion;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Integer getIdImparticion() {
        return idImparticion;
    }

    public void setIdImparticion(Integer idImparticion) {
        this.idImparticion = idImparticion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Curso getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Curso idCurso) {
        this.idCurso = idCurso;
    }

    @XmlTransient
    public Collection<Matricula> getMatriculaCollection() {
        return matriculaCollection;
    }

    public void setMatriculaCollection(Collection<Matricula> matriculaCollection) {
        this.matriculaCollection = matriculaCollection;
    }

    @XmlTransient
    public Collection<Examen> getExamenCollection() {
        return examenCollection;
    }

    public void setExamenCollection(Collection<Examen> examenCollection) {
        this.examenCollection = examenCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idImparticion != null ? idImparticion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Imparticion)) {
            return false;
        }
        Imparticion other = (Imparticion) object;
        if ((this.idImparticion == null && other.idImparticion != null) || (this.idImparticion != null && !this.idImparticion.equals(other.idImparticion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Imparticion[ idImparticion=" + idImparticion + " ]";
    }
    
}
