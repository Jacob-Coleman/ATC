package edu.utc.atc;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import edu.sc.seis.TauP.PhaseDialog;
import edu.utc.atc.components.TabComponent;
import edu.utc.atc.views.TabView;

@SuppressWarnings("serial")
@Theme("test3")
public class ATCServlet extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = ATCServlet.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		TabView tv1 = new TabView();
		setContent(tv1);
		

	}

}