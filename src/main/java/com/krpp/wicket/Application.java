/*
 * Application.java
 *
 * Created on 21 de septiembre de 2018, 21:43
 */
 
package com.krpp.wicket;           

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
/** 
 *
 * @author Usuario
 * @version 
 */

public class Application extends WebApplication
{
    @Override
    public Class<? extends WebPage> getHomePage()
    {
            return LoginPage.class;
    }
    
    
    @Override
    public Session newSession(Request request, Response response){
        return new UserSession(request);
    }
    
    
    
    @Override
    public void init()
    {
            super.init();
            // add your configuration here           

            
    }
}
