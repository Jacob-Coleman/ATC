package edu.utc.atc;

import edu.utc.atc.widgetset.client.googlemapswidget.GoogleMapsWidgetClientRpc;
import edu.utc.atc.widgetset.client.googlemapswidget.GoogleMapsWidgetServerRpc;
import com.vaadin.shared.MouseEventDetails;
import edu.utc.atc.widgetset.client.googlemapswidget.GoogleMapsWidgetState;

public class GoogleMapsWidget extends com.vaadin.ui.AbstractComponent {

	private GoogleMapsWidgetServerRpc rpc = new GoogleMapsWidgetServerRpc() {
		private int clickCount = 0;

		public void clicked(MouseEventDetails mouseDetails) {
			// nag every 5:th click using RPC
			if (++clickCount % 5 == 0) {
				getRpcProxy(GoogleMapsWidgetClientRpc.class).alert(
						"Ok, that's enough!");
			}
			// update shared state
			getState().text = "You have clicked " + clickCount + " times";
		}
	};  

	public GoogleMapsWidget() {
		registerRpc(rpc);
	}

	@Override
	public GoogleMapsWidgetState getState() {
		return (GoogleMapsWidgetState) super.getState();
	}
}
