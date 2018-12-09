
package com.krpp.modelo.entidades;

import com.krpp.modelo.modelosMapeados.ModelosModel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MODELO")
@NamedQueries({
        @NamedQuery(name = "Modelo.getAll", query = "SELECT c FROM Modelo c "),
})
public class Modelo implements Serializable {
    @Id
    @Column(name = "ID")
    @SequenceGenerator(name="modelo_seq", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="modelo_seq")
    private Integer idModelo;

    @Column(name="NOMBRE_MODELO")
    private String nombreModelo;

    @JoinColumn(name = "MARCA_ID", referencedColumnName = "ID")
    @ManyToOne
    private Marca marca;

    public Modelo() {
    }

    public Modelo(Integer idModelo, String nombreModelo) {
        this.idModelo = idModelo;
        this.nombreModelo = nombreModelo;
    }

    public Modelo(Integer idModelo, String nombreModelo, Marca marca) {
        this.idModelo = idModelo;
        this.nombreModelo = nombreModelo;
        this.marca = marca;
    }

    public Integer getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Integer idModelo) {
        this.idModelo = idModelo;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "Modelo{" +
                "idModelo=" + idModelo +
                ", nombreModelo='" + nombreModelo + '\'' +
                ", marca=" + marca +
                '}';
    }

    public ModelosModel toModel(){
        return new ModelosModel(this.getIdModelo(), this.getNombreModelo(), this.getMarca().toModel());
    }
}
