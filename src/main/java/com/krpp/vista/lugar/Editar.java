 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krpp.vista.lugar;

import com.krpp.wicket.TemplatePage;
import com.krpp.modelo.entidades.Lugar;
import com.krpp.modelo.modelosMapeados.LugarModel;
import com.krpp.controlador.repository.LugarRepository;
import org.apache.log4j.Logger;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class Editar extends TemplatePage {
    private static final long serialVersionUID = -7465108755276912649L;
    final static Logger logger = Logger.getLogger(com.krpp.wicket.LoginPage.class);
    public Editar(final PageParameters params){
        final Integer lugarId = params.get("lugar").toInteger();
        Lugar lugar = LugarRepository.getInstance().getById(lugarId);

        final LugarModel lugarModel = new LugarModel(lugar.getIdLugar(), lugar.getDescripcion(), lugar.getEstado());

        Form<Object> form = new Form<Object>("form");

        form.add(new TextField<String>("descripcion", new PropertyModel<String>(lugarModel, "descripcion")));

        form.add(new TextField<String>("estado", new PropertyModel<String>(lugarModel, "estado")));
        
        form.add(new Button("submit") {
            private static final long serialVersionUID = -8676092495300239679L;

            @Override
            public void onSubmit() {
                //guardamos la marca
                Lugar lugar = new Lugar(lugarModel.getIdLugar(), lugarModel.getDescripcion(), lugarModel.getEstado());

                LugarRepository.getInstance().update(lugar);
                setResponsePage(Listar.class);
            }
        });

        add(form);
    }

}

