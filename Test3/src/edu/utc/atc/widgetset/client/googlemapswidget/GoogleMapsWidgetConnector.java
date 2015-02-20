package edu.utc.atc.widgetset.client.googlemapswidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

import edu.utc.atc.GoogleMapsWidget;
import edu.utc.atc.widgetset.client.googlemapswidget.GoogleMapsWidgetWidget;
import edu.utc.atc.widgetset.client.googlemapswidget.GoogleMapsWidgetServerRpc;
import com.vaadin.client.communication.RpcProxy;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.client.MouseEventDetailsBuilder;
import edu.utc.atc.widgetset.client.googlemapswidget.GoogleMapsWidgetClientRpc;
import edu.utc.atc.widgetset.client.googlemapswidget.GoogleMapsWidgetState;
import com.vaadin.client.communication.StateChangeEvent;

@Connect(GoogleMapsWidget.class)
public class GoogleMapsWidgetConnector extends AbstractComponentConnector {

	GoogleMapsWidgetServerRpc rpc = RpcProxy
			.create(GoogleMapsWidgetServerRpc.class, this);
	
	public GoogleMapsWidgetConnector() {
		registerRpc(GoogleMapsWidgetClientRpc.class, new GoogleMapsWidgetClientRpc() {
			public void alert(String message) {
				// TODO Do something useful
				Window.alert(message);
			}
		});

		// TODO ServerRpc usage example, do something useful instead
		getWidget().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				final MouseEventDetails mouseDetails = MouseEventDetailsBuilder
					.buildMouseEventDetails(event.getNativeEvent(),
								getWidget().getElement());
				rpc.clicked(mouseDetails);
			}
		});

	}

	@Override
	protected Widget createWidget() {
		return GWT.create(GoogleMapsWidgetWidget.class);
	}

	@Override
	public GoogleMapsWidgetWidget getWidget() {
		return (GoogleMapsWidgetWidget) super.getWidget();
	}

	@Override
	public GoogleMapsWidgetState getState() {
		return (GoogleMapsWidgetState) super.getState();
	}

	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);

		// TODO do something useful
		final String text = getState().text;
		getWidget().setText(text);
	}

}

