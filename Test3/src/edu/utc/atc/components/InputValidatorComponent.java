package edu.utc.atc.components;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;

public class InputValidatorComponent {
	
	Boolean isValid = true;
	
	public InputValidatorComponent(String inputText, double minValue, double maxValue,String belowMinValueError, String aboveMaxValueError, String noValueError, String notValidNumberErrorMessage){
		
		
		
		try{
			if(inputText.isEmpty())
			{
				Notification error = new Notification(noValueError,Notification.TYPE_WARNING_MESSAGE);
				error.show(Page.getCurrent());
				isValid = false;
			}
			
			else if(Double.parseDouble(inputText) < minValue)
			{ 
				Notification error = new Notification(belowMinValueError,Notification.TYPE_WARNING_MESSAGE);
				error.show(Page.getCurrent());
				isValid = false;
				
			}
			else if (Double.parseDouble(inputText) > maxValue )
			{ 
				Notification error = new Notification(aboveMaxValueError,Notification.TYPE_WARNING_MESSAGE);
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
