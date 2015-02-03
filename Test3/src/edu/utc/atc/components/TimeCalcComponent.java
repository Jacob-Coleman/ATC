package edu.utc.atc.components;

/**
 * @version 1.0 
 * 
 * @author Jacob D. Coleman
 * 
 */

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class TimeCalcComponent extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	protected HorizontalSplitPanel horizontalSplitPanel_1;
	@AutoGenerated
	protected Table resultsTable;
	@AutoGenerated
	private VerticalLayout verticalLayout_1;
	@AutoGenerated
	protected Button submitButton;
	@AutoGenerated
	protected ComboBox modelBox;
	@AutoGenerated
	public TextField depthField;
	@AutoGenerated
	public TextField distanceField;
	@AutoGenerated
	private TextField textField_1;
	@AutoGenerated
	private TextField textField_2;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public TimeCalcComponent() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
	}

	public void addComponent(Button button) {
		// TODO Auto-generated method stub
		
	}

	public void addComponent(Label label) {
		// TODO Auto-generated method stub
		
	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// horizontalSplitPanel_1
		horizontalSplitPanel_1 = buildHorizontalSplitPanel_1();
		mainLayout.addComponent(horizontalSplitPanel_1,
				"top:0.0px;right:40.0px;bottom:0.0px;left:0.0px;");
		
		return mainLayout;
	}

	@AutoGenerated
	private HorizontalSplitPanel buildHorizontalSplitPanel_1() {
		// common part: create layout
		horizontalSplitPanel_1 = new HorizontalSplitPanel();
		horizontalSplitPanel_1.setImmediate(false);
		horizontalSplitPanel_1.setWidth("100.0%");
		horizontalSplitPanel_1.setHeight("100.0%");
		
		// verticalLayout_1
		verticalLayout_1 = buildVerticalLayout_1();
		horizontalSplitPanel_1.addComponent(verticalLayout_1);
		
		// resultsTable
		resultsTable = new Table();
		resultsTable.setImmediate(false);
		resultsTable.setWidth("100.0%");
		resultsTable.setHeight("100.0%");
		horizontalSplitPanel_1.addComponent(resultsTable);
		
		return horizontalSplitPanel_1;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_1() {
		// common part: create layout
		verticalLayout_1 = new VerticalLayout();
		verticalLayout_1.setImmediate(false);
		verticalLayout_1.setWidth("100.0%");
		verticalLayout_1.setHeight("308px");
		verticalLayout_1.setMargin(false);
		
		// textField_2
		textField_2 = new TextField();
		textField_2.setImmediate(false);
		textField_2.setWidth("100.0%");
		textField_2.setHeight("-1px");
		verticalLayout_1.addComponent(textField_2);
		
		// textField_1
		textField_1 = new TextField();
		textField_1.setImmediate(false);
		textField_1.setWidth("100.0%");
		textField_1.setHeight("-1px");
		verticalLayout_1.addComponent(textField_1);
		
		// distanceField
		distanceField = new TextField();
		distanceField.setImmediate(false);
		distanceField.setWidth("100.0%");
		distanceField.setHeight("-1px");
		distanceField.setInputPrompt("Distance deg");
		verticalLayout_1.addComponent(distanceField);
		
		// depthField
		depthField = new TextField();
		depthField.setImmediate(false);
		depthField.setWidth("100.0%");
		depthField.setHeight("-1px");
		depthField.setInputPrompt("Depth");
		verticalLayout_1.addComponent(depthField);
		
		// modelBox
		modelBox = new ComboBox();
		modelBox.setImmediate(false);
		modelBox.setWidth("100.0%");
		modelBox.setHeight("24px");
		modelBox.setTextInputAllowed(false);
		modelBox.setNullSelectionAllowed(false);
		modelBox.setInputPrompt("Select Model");
		verticalLayout_1.addComponent(modelBox);
		
		// submitButton
		submitButton = new Button();
		submitButton.setCaption("Submit");
		submitButton.setImmediate(true);
		submitButton.setWidth("-1px");
		submitButton.setHeight("-1px");
		verticalLayout_1.addComponent(submitButton);
		
		return verticalLayout_1;
	}
	
}
