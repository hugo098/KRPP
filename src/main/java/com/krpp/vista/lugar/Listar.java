   package com.krpp.vista.lugar;

import com.krpp.wicket.TemplatePage;
import com.krpp.modelo.entidades.Lugar;


import com.krpp.controlador.repository.LugarRepository;
import com.krpp.controlador.util.JavascriptEventConfirmation;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.ArrayList;
import java.util.List;

public class Listar extends TemplatePage {

    private static final long serialVersionUID = -7465108755276912649L;
    final List<IModel<Lugar>> listado = new ArrayList<IModel<Lugar>>();

    public Listar() {
        super();

        //agregamos el enlace a la pagina de creacion
        add(new BookmarkablePageLink<com.krpp.vista.lugar.Crear>("Lugar.Crear", com.krpp.vista.lugar.Crear.class));

        //obtenemos las marcas de la bd
        List<Lugar> lugares = LugarRepository.getInstance().getAll();

        add(new ListView<Lugar>("lugares", lugares) {
            @Override
            protected void populateItem(ListItem<Lugar> item) {
                Lugar l = item.getModelObject();
                //columnas del listado
                item.add(new Label("descripcion", new PropertyModel(item.getModel(), "descripcion")));
                item.add(new Label("idLugar", new PropertyModel(item.getModel(), "idLugar")));
                item.add(new Label("estado", new PropertyModel(item.getModel(), "estado")));
                //link para editar
                PageParameters pageParameters = new PageParameters();
                pageParameters.add("lugar", l.getIdLugar());
                item.add(new BookmarkablePageLink<Void>("editLink",
                        com.krpp.vista.lugar.Editar.class, pageParameters));

                //link para eliminar
                Link<Integer> deleteLink = new Link<Integer>("deleteLink",
                        new Model<Integer>(l.getIdLugar())) {
                    @Override
                    public void onClick() {
                        LugarRepository.getInstance().deleteById(getModelObject());
                        setResponsePage(Listar.class);
                    }
                };
                deleteLink.add(new JavascriptEventConfirmation("onclick", "Esta seguro de eliminar la marca " + l.getDescripcion() + "?"));

                item.add(deleteLink);
            }

        });

    }
}

