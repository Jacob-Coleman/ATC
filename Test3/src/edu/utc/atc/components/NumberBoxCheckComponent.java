package edu.utc.atc.components;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;

public class NumberBoxCheckComponent {
	
	Boolean isValid = true;
	
	public NumberBoxCheckComponent(String inputText, double minValue, double maxValue,String notWithinParamaters, String noValueError, String notValidNumberErrorMessage){
		try{
			if(Double.parseDouble(inputText) < minValue && Double.parseDouble(inputText) < maxValue )
			{ 
				Notification error = new Notification(notWithinParamaters,Notification.TYPE_WARNING_MESSAGE);
				error.show(Page.getCurrent());
				isValid = false;
				
			}
			else if( isValid == true && inputText.isEmpty())
			{
				Notification error = new Notification(noValueError,Notification.TYPE_WARNING_MESSAGE);
				error.show(Page.getCurrent());
				isValid = false;
			}
			
		} catch(NumberFormatException e){
			Notification error = new Notification(notValidNumberErrorMessage,Notification.TYPE_WARNING_MESSAGE);
			error.show(Page.getCurrent());
			isValid = false;
		}
	}
	
	public boolean getIsValid(){
		return isValid;
	}
}
