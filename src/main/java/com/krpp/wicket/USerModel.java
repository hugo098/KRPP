/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krpp.wicket;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
class USerModel implements Serializable{
    private static final long serialVersionUID = -5719996440273150534L;
    private String name, pass;
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public USerModel(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public USerModel() {
    }
    
}
