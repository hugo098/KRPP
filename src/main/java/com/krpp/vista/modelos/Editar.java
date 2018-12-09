/*
 * HomePage.java
 *
 * Created on 21 de septiembre de 2018, 21:43
 */

package com.krpp.vista.modelos;

import com.krpp.wicket.TemplatePage;
import com.krpp.modelo.entidades.Marca;
import com.krpp.modelo.entidades.Modelo;
import com.krpp.modelo.modelosMapeados.ModelosModel;
import com.krpp.controlador.repository.MarcaRepository;
import com.krpp.controlador.repository.ModeloRepository;
import org.apache.log4j.Logger;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.ArrayList;
import java.util.List;

public class Editar extends TemplatePage {
    private static final long serialVersionUID = -7465108755276912649L;
    final static Logger logger = Logger.getLogger(com.krpp.wicket.LoginPage.class);
    public Editar(final PageParameters params){
        final Integer modeloId = params.get("modelo").toInteger();
        Modelo modelo = ModeloRepository.getInstance().getById(modeloId);

        final ModelosModel modelosModel = modelo.toModel();

        Form<Object> form = new Form<Object>("form");

        form.add(new TextField<String>("nombreModelo", new PropertyModel<String>(modelosModel, "nombreModelo")));

        DropDownChoice ddc = new DropDownChoice("marcas",
                new PropertyModel(modelosModel, "marca"),
                new Model(getChoices()),
                new ChoiceRenderer("nombreMarca", "idMarca")
        );
        form.add(ddc);

        form.add(new Button("submit") {
            private static final long serialVersionUID = -8676092495300239679L;

            @Override
            public void onSubmit() {
                //guardamos el modelo
                Marca marca = MarcaRepository.getInstance().getById(modelosModel.getMarca().getIdMarca());
                Modelo modelo = new Modelo(modelosModel.getIdModelo(), modelosModel.getNombreModelo(), marca);

                ModeloRepository.getInstance().update(modelo);
                setResponsePage(Listar.class);
            }
        });

        add(form);
    }

    private ArrayList getChoices() {
        List<Marca> marcas = MarcaRepository.getInstance().getAll();
        ArrayList list = new ArrayList();
        for (Marca m: marcas) {
            list.add(m.toModel());
        }
        return list;
    }

}
