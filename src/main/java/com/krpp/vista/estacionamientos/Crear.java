/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krpp.vista.estacionamientos;

import com.krpp.wicket.TemplatePage;
import com.krpp.modelo.entidades.Estacionamiento;
import com.krpp.modelo.entidades.Lugar;
import com.krpp.modelo.entidades.Vehiculo;
import com.krpp.modelo.modelosMapeados.EstacionamientoModel;
import com.krpp.controlador.repository.EstacionamientoRepository;
import com.krpp.controlador.repository.LugarRepository;
import com.krpp.controlador.repository.VehiculoRepository;
import org.apache.log4j.Logger;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import java.util.ArrayList;
import java.util.List;

public class Crear extends TemplatePage {

    private static final long serialVersionUID = -7465108755276912649L;
    final static Logger logger = Logger.getLogger(com.krpp.wicket.LoginPage.class);

    public Crear() {

        final EstacionamientoModel estacionamientoModel = new EstacionamientoModel();

        Form<Object> form = new Form<Object>("form");

        DropDownChoice ddc = new DropDownChoice("vehiculos",
                new PropertyModel(estacionamientoModel, "idVehiculo"),
                new Model(getChoicesV()),
                new ChoiceRenderer("chapa", "idVehiculo")
        );
        
        
        DropDownChoice ddc2 = new DropDownChoice("lugares",
                new PropertyModel(estacionamientoModel, "idLugar"),
                new Model(getChoicesL()),
                new ChoiceRenderer("descripcion", "idLugar")
        );
        
        form.add(ddc);
        form.add(ddc2);

        form.add(new Button("submit") {
            private static final long serialVersionUID = -8676092495300239679L;

            @Override
            public void onSubmit() {

                Vehiculo vehiculo = VehiculoRepository.getInstance().getById(estacionamientoModel.getIdVehiculo().getIdVehiculo());
                Lugar lugar = LugarRepository.getInstance().getById(estacionamientoModel.getIdLugar().getIdLugar());

                Estacionamiento estacionamiento = new Estacionamiento("ACTIVO", lugar, vehiculo);               
                

                EstacionamientoRepository.getInstance().save(estacionamiento);
                
                LugarRepository.getInstance().update(lugar);
                
                VehiculoRepository.getInstance().updateEstdo(vehiculo);
                
                setResponsePage(com.krpp.vista.estacionamientos.Listar.class);
            }
        });

        add(form);
    }

    private ArrayList getChoicesV() {
        List<Vehiculo> vehiculos = VehiculoRepository.getInstance().getByEstado();
        ArrayList list = new ArrayList();
        for (Vehiculo v : vehiculos) {
            list.add(v.toModel());
        }
        return list;
    }
    
    private ArrayList getChoicesL() {
        List<Lugar> lugares = LugarRepository.getInstance().getAllLibre();
        ArrayList list = new ArrayList();
        for (Lugar l : lugares) {
            list.add(l.toModel());
        }
        return list;
    }

}
