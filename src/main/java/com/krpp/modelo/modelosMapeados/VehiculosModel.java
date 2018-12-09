
package com.krpp.modelo.modelosMapeados;

import com.krpp.modelo.entidades.Empleado;
import com.krpp.modelo.entidades.Modelo;

import javax.persistence.*;
import java.io.Serializable;


public class VehiculosModel implements Serializable {

    private Integer idVehiculo;

    private String chapa;

    private String color;

    private String anho;

    private ModelosModel modelo;

    private EmpleadosModel empleado;
    
    private String estado;

    public VehiculosModel() {
    }

    public VehiculosModel(Integer idVehiculo, String chapa, String color, String anho, ModelosModel modelo, EmpleadosModel empleado, String estado) {
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

    public ModelosModel getModelo() {
        return modelo;
    }

    public void setModelo(ModelosModel modelo) {
        this.modelo = modelo;
    }

    public EmpleadosModel getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadosModel empleado) {
        this.empleado = empleado;
    }

    @Override
    public String toString() {
        return "VehiculosModel{" +
                "idVehiculo=" + idVehiculo +
                ", chapa='" + chapa + '\'' +
                ", color='" + color + '\'' +
                ", anho='" + anho + '\'' +
                ", modelo=" + modelo +
                ", empleado=" + empleado +
                '}';
    }
}
