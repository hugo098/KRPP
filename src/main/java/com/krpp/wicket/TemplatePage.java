/*
 * WicketExamplePage.java
 *
 * Created on 21 de septiembre de 2018, 21:43
 */
 
package com.krpp.wicket;           

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class TemplatePage extends WebPage {
    private static final long serialVersionUID = 5218374612129395403L;
    private Component navigationPanel;
    public TemplatePage(){
        add(navigationPanel = new NavigationPanel("navigationPanel"));
    }

    public Component getNavigationPanel() {
        return navigationPanel;
    }
}