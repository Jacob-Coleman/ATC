package edu.utc.atc.components;
/**
 * Used to validate data input into the text fields.
 * Also returns relevant messages to the user so that corrections to data can be made. 
 */
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;

public class InputValidatorComponent {
	
	Boolean isValid = true;
	
	public InputValidatorComponent(String inputText, double minValue, double maxValue,String belowMinValueError, String aboveMaxValueError, String noValueError, String notValidNumberErrorMessage){
		
		
		
		try{
			if(inputText.isEmpty())
			{
				Notification error = new Notification(noValueError,Notification.Type.WARNING_MESSAGE);
				error.show(Page.getCurrent());
				isValid = false;
			}
			
			else if(Double.parseDouble(inputText) < minValue)
			{ 
				Notification error = new Notification(belowMinValueError,Notification.Type.WARNING_MESSAGE);
				error.show(Page.getCurrent());
				isValid = false;
				
			}
			else if (Double.parseDouble(inputText) > maxValue )
			{ 
				Notification error = new Notification(aboveMaxValueError,Notification.Type.WARNING_MESSAGE);
				error.show(Page.getCurrent());
				isValid = false;
			}
			
		} catch(NumberFormatException e){
			Notification error = new Notification(notValidNumberErrorMessage,Notification.Type.WARNING_MESSAGE);
			error.show(Page.getCurrent());
			isValid = false;
		}
	}
	
	public boolean getIsValid(){
		return isValid;
	}
}
