
package com.krpp.modelo.modelosMapeados;

import java.io.Serializable;
import java.util.Date;


public class EmpleadosModel implements Serializable {

    private Integer idEmpleado;

    private Integer nroCedula;

    private String nombreCompleto;

    private Date fechaNacimiento;

    private String estadoCivil;

    private String sexo;


    public EmpleadosModel() {
    }

    public EmpleadosModel(Integer idEmpleado, Integer nroCedula, String nombreCompleto, Date fechaNacimiento, String estadoCivil, String sexo) {
        this.idEmpleado = idEmpleado;
        this.nroCedula = nroCedula;
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
        this.estadoCivil = estadoCivil;
        this.sexo = sexo;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public Integer getNroCedula() {
        return nroCedula;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public String getSexo() {
        return sexo;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public void setNroCedula(Integer nroCedula) {
        this.nroCedula = nroCedula;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "EmpleadosModel{" +
                "idEmpleado=" + idEmpleado +
                ", nroCedula=" + nroCedula +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", estadoCivil='" + estadoCivil + '\'' +
                ", sexo='" + sexo + '\'' +
                '}';
    }
}
