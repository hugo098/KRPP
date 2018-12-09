/*
 * HomePage.java
 *
 * Created on 21 de septiembre de 2018, 21:43
 */

package com.krpp.vista.marcas;

import com.krpp.wicket.TemplatePage;
import com.krpp.controlador.repository.MarcaRepository;
import com.krpp.modelo.entidades.Marca;
import com.krpp.modelo.modelosMapeados.MarcasModel;
import org.apache.log4j.Logger;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

public class Crear extends TemplatePage {
    private static final long serialVersionUID = -7465108755276912649L;
    final static Logger logger = Logger.getLogger(com.krpp.wicket.LoginPage.class);
    public Crear(){

        final MarcasModel marcasModel = new MarcasModel();

        Form<Object> form = new Form<Object>("form");

        form.add(new TextField<String>("nombreMarca", new PropertyModel<String>(marcasModel, "nombreMarca")));


        form.add(new Button("submit") {
            private static final long serialVersionUID = -8676092495300239679L;

            @Override
            public void onSubmit() {
                //guardamos la marca
                Marca marca = new Marca(marcasModel.getNombreMarca());

                MarcaRepository.getInstance().save(marca);
                setResponsePage(Listar.class);
            }
        });

        add(form);
    }

}
