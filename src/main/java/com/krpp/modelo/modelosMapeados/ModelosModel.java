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
public class ModelosModel implements Serializable{
    private static final long serialVersionUID = -5719996440273150531L;

    private Integer idModelo;

    private String nombreModelo;

    private MarcasModel marca;

    public ModelosModel() {
    }

    public ModelosModel(Integer idModelo, String nombreModelo, MarcasModel marca) {
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

    public MarcasModel getMarca() {
        return marca;
    }

    public void setMarca(MarcasModel marca) {
        this.marca = marca;
    }
}
