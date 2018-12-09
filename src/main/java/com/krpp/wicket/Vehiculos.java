/*
 * HomePage.java
 *
 * Created on 21 de septiembre de 2018, 21:43
 */

package com.krpp.wicket;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.settings.IJavaScriptLibrarySettings;

public class Vehiculos extends TemplatePage {
    private static final long serialVersionUID = -7465108755276912649L;

    public Vehiculos() {
        super();
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        String initScript = "$('#dataTables-example').DataTable({\n" +
                "            responsive: true\n" +
                "        });";
        response.render(OnLoadHeaderItem.forScript(initScript));
    }
}
