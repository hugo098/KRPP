package com.krpp.modelo.entidades;


import com.krpp.modelo.modelosMapeados.VehiculosModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "VEHICULO")
@NamedQueries({
    @NamedQuery(name = "Vehiculo.getAll", query = "SELECT c FROM Vehiculo c ")
    ,
        @NamedQuery(name = "Vehiculo.getByEstado", query = "SELECT c FROM Vehiculo c WHERE estado = 'NO ESTACIONADO'")

})
public class Vehiculo implements Serializable {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "vehiculo_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehiculo_seq")
    private Integer idVehiculo;

    @Column(name = "CHAPA")
    private String chapa;

    @Column(name = "COLOR")
    private String color;

    @Column(name = "ANHO")
    private String anho;

    @Column(name = "ESTADO")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVehiculo")
    private Collection<Estacionamiento> estacionamientoCollection;
    
    @JoinColumn(name = "MODELO_ID", referencedColumnName = "ID")
    @ManyToOne
    private Modelo modelo;

    @JoinColumn(name = "EMPLEADO_ID", referencedColumnName = "ID")
    @ManyToOne
    private Empleado empleado;

    public Vehiculo() {
    }

    public Vehiculo(Integer idVehiculo, String chapa, String color, String anho, Modelo modelo, Empleado empleado, String estado) {
        this.idVehiculo = idVehiculo;
        this.chapa = chapa;
        this.color = color;
        this.anho = anho;
        this.modelo = modelo;
        this.empleado = empleado;
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getChapa() {
        return chapa;
    }

    public void setChapa(String chapa) {
        this.chapa = chapa;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAnho() {
        return anho;
    }

    public void setAnho(String anho) {
        this.anho = anho;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public String toString() {
        return "Vehiculo{"
                + "idVehiculo=" + idVehiculo
                + ", chapa='" + chapa + '\''
                + ", color='" + color + '\''
                + ", anho='" + anho + '\''
                + ", modelo=" + modelo
                + ", empleado=" + empleado
                + '}';
    }

    public VehiculosModel toModel() {
        return new VehiculosModel(this.getIdVehiculo(), this.getChapa(), this.getColor(), this.getAnho(),
                this.getModelo().toModel(), this.getEmpleado().toModel(), this.getEstado());
    }
}
