/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krpp.wicket;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

/**
 *
 * @author Usuario
 */
public class UserSession extends WebSession{
    
   private static final long serialVersionUID = -8821251817445220705L;
    
   private USerModel uSerModel;

    public USerModel getuSerModel() {
        return uSerModel;
    }

    public void setuSerModel(USerModel uSerModel) {
        this.uSerModel = uSerModel;
    }
    public UserSession(Request request){
        super(request);
    }
    
    public static UserSession getInstance(){
        return (UserSession) Session.get();
    }
}
