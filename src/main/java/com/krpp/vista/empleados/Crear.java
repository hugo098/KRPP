/*
 * HomePage.java
 *
 * Created on 21 de septiembre de 2018, 21:43
 */

package com.krpp.vista.empleados;

import com.krpp.wicket.TemplatePage;
import com.krpp.modelo.entidades.Empleado;
import com.krpp.modelo.modelosMapeados.EmpleadosModel;
import com.krpp.controlador.repository.EmpleadoRepository;
import org.apache.log4j.Logger;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

public class Crear extends TemplatePage {
    private static final long serialVersionUID = -7465108755276912649L;
    final static Logger logger = Logger.getLogger(com.krpp.wicket.LoginPage.class);
    public Crear(){

        final EmpleadosModel empleadosModel = new EmpleadosModel();

        Form<Object> form = new Form<Object>("form");

        form.add(new TextField<String>("nroCedula", new PropertyModel<String>(empleadosModel, "nroCedula")));
        form.add(new TextField<String>("nombreCompleto", new PropertyModel<String>(empleadosModel, "nombreCompleto")));
        form.add(new TextField<String>("estadoCivil", new PropertyModel<String>(empleadosModel, "estadoCivil")));
        form.add(new TextField<String>("sexo", new PropertyModel<String>(empleadosModel, "sexo")));

        form.add(new Button("submit") {
            private static final long serialVersionUID = -8676092495300239679L;

            @Override
            public void onSubmit() {
                //guardamos el empleado
                Empleado empleado = new Empleado(empleadosModel.getNroCedula(), empleadosModel.getNombreCompleto(), empleadosModel.getFechaNacimiento(),
                        empleadosModel.getEstadoCivil(), empleadosModel.getSexo());

                EmpleadoRepository.getInstance().save(empleado);
                setResponsePage(Listar.class);
            }
        });

        add(form);
    }

}
