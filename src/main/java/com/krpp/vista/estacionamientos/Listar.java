      /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krpp.vista.estacionamientos;

import com.krpp.wicket.TemplatePage;
import com.krpp.modelo.entidades.Estacionamiento;
import com.krpp.controlador.repository.EstacionamientoRepository;
import com.krpp.controlador.repository.LugarRepository;
import com.krpp.controlador.repository.VehiculoRepository;
import com.krpp.controlador.util.JavascriptEventConfirmation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;


import java.util.ArrayList;
import java.util.List;

public class Listar extends TemplatePage {
    private static final long serialVersionUID = -7465108755276912649L;
    final List<IModel<Estacionamiento>> listado = new ArrayList<IModel<Estacionamiento>>();

    public Listar() {
        super();

        //agregamos el enlace a la pagina de creacion
        add(new BookmarkablePageLink<com.krpp.vista.estacionamientos.Crear>("Estacionamiento.Crear" ,com.krpp.vista.estacionamientos.Crear.class));

        //obtenemos las marcas de la bd
        List<Estacionamiento> estacionamientos = EstacionamientoRepository.getInstance().getAll();

        add(new ListView<Estacionamiento>("estacionamientos", estacionamientos) {
            @Override
            protected void populateItem(ListItem<Estacionamiento> item) {
                final Estacionamiento e = item.getModelObject();
                //columnas del listado
                item.add(new Label("idEstacionamiento", new PropertyModel(item.getModel(), "idEstacionamiento")));
                item.add(new Label("idVehiculo", new PropertyModel(item.getModel(), "idVehiculo.chapa")));
                item.add(new Label("idLugar", new PropertyModel(item.getModel(), "idLugar.descripcion")));
                item.add(new Label("estado", new PropertyModel(item.getModel(), "estado")));   
                              
                
                Link<Integer> editLink = new Link<Integer>("editLink",
                        new Model<Integer>(e.getIdEstacionamiento())) {
                    @Override
                    public void onClick() {

                        EstacionamientoRepository.getInstance().update(e);
                        
                       LugarRepository.getInstance().update(e.getIdLugar());
                        
                        VehiculoRepository.getInstance().updateEstdo(e.getIdVehiculo());
                        
                        setResponsePage(Listar.class);

                    }
                };

                editLink.add(new JavascriptEventConfirmation("onclick", "Esta seguro de marcar la salida del vehiculo?"));
                item.add(editLink);

                //link para eliminar
               /* Link<Integer> deleteLink = new Link<Integer>("deleteLink",
                        new Model<Integer>(e.getIdEstacionamiento())) {
                    @Override
                    public void onClick() {

                       EstacionamientoRepository.getInstance().deleteById(getModelObject());
                        setResponsePage(Listar.class);

                    }
                };

                deleteLink.add(new JavascriptEventConfirmation("onclick", "Esta seguro de eliminar el estacionamiento?"));
                item.add(deleteLink);*/
            }

        });


    }
}
