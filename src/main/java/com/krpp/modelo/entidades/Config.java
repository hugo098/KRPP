/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krpp.modelo.entidades;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CONFIG")
@NamedQueries({
        @NamedQuery(name = "Config.getConfigByClave", query = "SELECT c FROM Config c WHERE c.clave = :clave"),
        @NamedQuery(name = "Config.getAllConfig", query = "SELECT c FROM Config c")
})
public class Config {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CLAVE")
    private String clave;

    @Size(max = 3000)
    @Column(name = "VALOR")
    private String valor;

    @Size(max = 200)
    @Column(name = "COMENTARIO")
    private String comentario;



    public Config() {
    }

    public Config(String clave) {
        this.clave = clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClave() { return clave; }

    public String getValor() { return valor; }

    public void setValor(String valor) { this.valor = valor; }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clave != null ? clave.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Config)) {
            return false;
        }
        Config other = (Config) object;
        if ((this.clave == null && other.clave != null) || (this.clave != null && !this.clave.equals(other.clave))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Config{" +
                "clave='" + clave + '\'' +
                "valor='" + getValor() + '\'' +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
