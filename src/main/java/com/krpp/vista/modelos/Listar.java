   /*
 * HomePage.java
 *
 * Created on 21 de septiembre de 2018, 21:43
 */

package com.krpp.vista.modelos;

import com.krpp.wicket.TemplatePage;
import com.krpp.modelo.entidades.Marca;
import com.krpp.modelo.entidades.Modelo;
import com.krpp.controlador.repository.ModeloRepository;
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
    final List<IModel<Marca>> listado = new ArrayList<IModel<Marca>>();

    public Listar() {
        super();

        //agregamos el enlace a la pagina de creacion
        add(new BookmarkablePageLink<Crear>("Modelo.Crear" ,Crear.class));

        //obtenemos las marcas de la bd
        List<Modelo> modelos = ModeloRepository.getInstance().getAll();

        add(new ListView<Modelo>("modelos", modelos) {
            @Override
            protected void populateItem(ListItem<Modelo> item) {
                Modelo m = item.getModelObject();
                //columnas del listado
                item.add(new Label("nombreModelo", new PropertyModel(item.getModel(), "nombreModelo")));
                item.add(new Label("idModelo", new PropertyModel(item.getModel(), "idModelo")));
                item.add(new Label("marca", new PropertyModel(item.getModel(), "marca.nombreMarca")));

                //link para editar
                PageParameters pageParameters = new PageParameters();
                pageParameters.add("modelo", m.getIdModelo());
                item.add(new BookmarkablePageLink<Void>("editLink",
                        Editar.class, pageParameters));

                //link para eliminar
                Link<Integer> deleteLink = new Link<Integer>("deleteLink",
                        new Model<Integer>(m.getIdModelo())) {
                    @Override
                    public void onClick() {

                        ModeloRepository.getInstance().deleteById(getModelObject());
                        setResponsePage(Listar.class);

                    }
                };

                deleteLink.add(new JavascriptEventConfirmation("onclick", "Esta seguro de eliminar el modelo "+m.getNombreModelo()+"?"));
                item.add(deleteLink);
            }

        });


    }
}
