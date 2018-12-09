
package com.krpp.wicket;

import com.krpp.controlador.repository.UsuarioRepository;
import org.apache.log4j.Logger;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;


public class LoginPage extends WebPage {


   private static final long serialVersionUID = 5946349607750478191L;

    final static Logger logger = Logger.getLogger(com.krpp.wicket.LoginPage.class);
  
    public LoginPage() {

        final USerModel userModel = new USerModel();

        Form<Object> form = new Form<Object>("form");

        form.add(new TextField<String>("username", new PropertyModel<String>(userModel, "name")));

        form.add(new PasswordTextField("pass", new PropertyModel<String>(userModel, "pass")));

        form.add(new Button("submit") {
            private static final long serialVersionUID = -8676092495300239679L;

            @Override
            public void onSubmit() {
                super.onSubmit();
        

                if(UsuarioRepository.getInstance().existeUsuario(userModel.getName(), userModel.getPass())){
                    logger.info("Usuario logueado correctamente");
                    UserSession.getInstance().setuSerModel(userModel);
                    setResponsePage(HomePage.class);
                }else{
                    logger.info("Usuario no encontrado");
                    error("Error: El nombre de usuario o la contraseña son invalidas");
                }

            }

        });
        


        add(form);
        add(new FeedbackPanel("feedbackPanel"));
    }





}

/*import org.apache.wicket.MarkupContainer;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

public class LoginPage {
    private static final long serialVersionUID = 1L;
    private Label sessionType;
    private String password;
    private String username;
    
    public LoginPage(final PageParameters parameters) {
        StatelessForm form = new StatelessForm("form"){
            @Override
            protected void onSubmit() {
                if("user".equals(username) && username.equals(password))
                    info("Username and password are correct!");
                else
                    error("Wrong username or password");
            }
        };
        
        form.add(new PasswordTextField("password"));
        form.add(new TextField("username"));        
        
        //add(form.setDefaultModel(new CompoundPropertyModel(this)));
        
        //add(sessionType = new Label("sessionType", Model.of("")));
        //add(new FeedbackPanel("feedbackPanel"));
    }
*/

