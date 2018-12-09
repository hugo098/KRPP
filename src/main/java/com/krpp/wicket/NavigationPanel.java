package com.krpp.wicket;


import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

public class NavigationPanel extends Panel {

    public NavigationPanel(String id) {
        super(id);
        add(new Link<String>("signOut") {

            private static final long serialVersionUID = 7691766648121983700L;

            @Override
            public void onClick() {
                UserSession.getInstance().invalidate();
                setResponsePage(LoginPage.class);
            }
        });

        add(new BookmarkablePageLink<com.krpp.vista.empleados.Listar>("Empleado.Listar", com.krpp.vista.empleados.Listar.class));
        add(new BookmarkablePageLink<com.krpp.vista.marcas.Listar>("Marca.Listar", com.krpp.vista.marcas.Listar.class));
        add(new BookmarkablePageLink<com.krpp.vista.modelos.Listar>("Modelo.Listar", com.krpp.vista.modelos.Listar.class));
        add(new BookmarkablePageLink<com.krpp.vista.vehiculos.Listar>("Vehiculo.Listar", com.krpp.vista.vehiculos.Listar.class));
        add(new BookmarkablePageLink<com.krpp.vista.lugar.Listar>("Lugar.Listar", com.krpp.vista.lugar.Listar.class));
        add(new BookmarkablePageLink<com.krpp.vista.estacionamientos.Listar>("Estacionamiento.Listar", com.krpp.vista.estacionamientos.Listar.class));
    }
}
