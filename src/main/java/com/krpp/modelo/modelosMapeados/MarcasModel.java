/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krpp.modelo.modelosMapeados;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class MarcasModel implements Serializable {
    private static final long serialVersionUID = -5719996440273150534L;

    private Integer idMarca;
    private String nombreMarca;

    public MarcasModel(Integer idMarca, String nombreMarca) {
        this.idMarca = idMarca;
        this.nombreMarca = nombreMarca;
    }

    public MarcasModel() {
    }

    public Integer getIdMarca() {

        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }


}
