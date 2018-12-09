
package com.krpp.modelo.entidades;

import com.krpp.modelo.modelosMapeados.EmpleadosModel;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "EMPLEADO")
@NamedQueries({
        @NamedQuery(name = "Empleado.getAll", query = "SELECT c FROM Empleado c "),
})
public class Empleado implements Serializable {
    @Id
    @Column(name = "ID")
    @SequenceGenerator(name="empleado_seq", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="empleado_seq")
    private Integer idEmpleado;

    @Column(name="NRO_CEDULA")
    private Integer nroCedula;
    
    @Column(name="NOMBRE_COMPLETO")
    private String nombreCompleto;
      
    @Column(name="FECHA_NACIMIENTO")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNacimiento;
    
    @Column(name="ESTADO_CIVIL")
    private String estadoCivil;
    
    @Column(name="SEXO")
    private String sexo;
    

    public Empleado() {
    }

    public Empleado(Integer idEmpleado, Integer nroCedula, String nombreCompleto, Date fechaNacimiento, String estadoCivil, String sexo) {
        this.idEmpleado = idEmpleado;
        this.nroCedula = nroCedula;
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
        this.estadoCivil = estadoCivil;
        this.sexo = sexo;
    }

    public Empleado(Integer nroCedula, String nombreCompleto, Date fechaNacimiento, String estadoCivil, String sexo) {
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
        return "Empleado{" +
                "idEmpleado=" + idEmpleado +
                ", nroCedula=" + nroCedula +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", estadoCivil='" + estadoCivil + '\'' +
                ", sexo='" + sexo + '\'' +
                '}';
    }

    public EmpleadosModel toModel(){
        return new EmpleadosModel(this.getIdEmpleado(), this.getNroCedula(), this.getNombreCompleto(), this.getFechaNacimiento(),
                this.getEstadoCivil(), this.getSexo());
    }
}
