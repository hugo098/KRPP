/*
 * HomePage.java
 *
 * Created on 21 de septiembre de 2018, 21:43
 */

package com.krpp.vista.vehiculos;

import com.krpp.wicket.TemplatePage;
import com.krpp.modelo.entidades.Empleado;
import com.krpp.modelo.entidades.Modelo;
import com.krpp.modelo.entidades.Vehiculo;
import com.krpp.modelo.modelosMapeados.VehiculosModel;
import com.krpp.controlador.repository.EmpleadoRepository;
import com.krpp.controlador.repository.ModeloRepository;
import com.krpp.controlador.repository.VehiculoRepository;
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

        final Integer vehiculoId = params.get("vehiculo").toInteger();
        Vehiculo vehiculo = VehiculoRepository.getInstance().getById(vehiculoId);

        final VehiculosModel vehiculosModel = vehiculo.toModel();

        Form<Object> form = new Form<Object>("form");

        form.add(new TextField<String>("chapa", new PropertyModel<String>(vehiculosModel, "chapa")));
        form.add(new TextField<String>("color", new PropertyModel<String>(vehiculosModel, "color")));
        form.add(new TextField<String>("anho", new PropertyModel<String>(vehiculosModel, "anho")));

        DropDownChoice ddm = new DropDownChoice("modelos",
                new PropertyModel(vehiculosModel, "modelo"),
                new Model(getChoicesModelos()),
                new ChoiceRenderer("nombreModelo", "idModelo")
        );
        form.add(ddm);

        DropDownChoice dde = new DropDownChoice("empleados",
                new PropertyModel(vehiculosModel, "empleado"),
                new Model(getChoicesEmpleados()),
                new ChoiceRenderer("nombreCompleto", "idEmpleado")
        );
        form.add(dde);

        form.add(new Button("submit") {
            private static final long serialVersionUID = -8676092495300239679L;

            @Override
            public void onSubmit() {
                //guardamos el vehiculo
                Modelo modelo = ModeloRepository.getInstance().getById(vehiculosModel.getModelo().getIdModelo());
                Empleado empleado = EmpleadoRepository.getInstance().getById(vehiculosModel.getEmpleado().getIdEmpleado());

                Vehiculo vehiculo = new Vehiculo(vehiculosModel.getIdVehiculo(), vehiculosModel.getChapa(), vehiculosModel.getColor(),
                        vehiculosModel.getAnho(), modelo, empleado, "NO ESTACIONADO");

                VehiculoRepository.getInstance().update(vehiculo);
                setResponsePage(Listar.class);
            }
        });

        add(form);
    }

    private ArrayList getChoicesModelos() {
        List<Modelo> modelos = ModeloRepository.getInstance().getAll();
        ArrayList list = new ArrayList();
        for (Modelo m: modelos) {
            list.add(m.toModel());
        }
        return list;
    }

    private ArrayList getChoicesEmpleados() {
        List<Empleado> empleados = EmpleadoRepository.getInstance().getAll();
        ArrayList list = new ArrayList();
        for (Empleado m: empleados) {
            list.add(m.toModel());
        }
        return list;
    }

}
