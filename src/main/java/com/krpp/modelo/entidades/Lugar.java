/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krpp.modelo.entidades;

import com.krpp.modelo.modelosMapeados.LugarModel;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "lugar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lugar.findAll", query = "SELECT l FROM Lugar l")
    , @NamedQuery(name = "Lugar.findByIdLugar", query = "SELECT l FROM Lugar l WHERE l.idLugar = :idLugar")
    , @NamedQuery(name = "Lugar.findByDescripcion", query = "SELECT l FROM Lugar l WHERE l.descripcion = :descripcion")
    , @NamedQuery(name = "Lugar.findByEstado", query = "SELECT l FROM Lugar l WHERE l.estado = 'LIBRE'")})
public class Lugar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_lugar")
    private Integer idLugar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "estado")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLugar")
    private Collection<Estacionamiento> estacionamientoCollection;

    public Lugar() {
    }

    public Lugar(Integer idLugar) {
        this.idLugar = idLugar;
    }

    public Lugar(String descripcion, String estado) {        
        this.descripcion = descripcion;
        this.estado = estado;
    }
    
    public Lugar(int lugarId, String descripcion, String estado) {  
        this.idLugar = lugarId;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Integer getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(Integer idLugar) {
        this.idLugar = idLugar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<Estacionamiento> getEstacionamientoCollection() {
        return estacionamientoCollection;
    }

    public void setEstacionamientoCollection(Collection<Estacionamiento> estacionamientoCollection) {
        this.estacionamientoCollection = estacionamientoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLugar != null ? idLugar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lugar)) {
            return false;
        }
        Lugar other = (Lugar) object;
        if ((this.idLugar == null && other.idLugar != null) || (this.idLugar != null && !this.idLugar.equals(other.idLugar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.parqueo.krpp.entities.Lugar[ idLugar=" + idLugar + " ]";
    }
    public LugarModel toModel(){
        return new LugarModel(this.getIdLugar(), this.getDescripcion(), this.getEstado());
    }
}
