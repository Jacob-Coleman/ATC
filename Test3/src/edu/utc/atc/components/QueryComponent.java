package edu.utc.atc.components;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class QueryComponent extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	protected HorizontalSplitPanel horizontalSplitPanel_1;
	@AutoGenerated
	private Table table_1;
	@AutoGenerated
	private VerticalLayout verticalLayout_1;
	@AutoGenerated
	protected Button submitButton;
	@AutoGenerated
	protected TextField maxMagField;
	@AutoGenerated
	protected TextField radiusField;
	@AutoGenerated
	protected TextField longField;
	@AutoGenerated
	protected TextField latField;
	@AutoGenerated
	protected InlineDateField inlineDateField_2;
	/**
	 * 
	 */
	private static final long serialVersionUID = 8913196389988079007L;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public QueryComponent() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
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
				"top:0.0px;right:-2.0px;bottom:0.0px;left:0.0px;");
		
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
		
		// table_1
		table_1 = new Table();
		table_1.setImmediate(false);
		table_1.setWidth("100.0%");
		table_1.setHeight("100.0%");
		horizontalSplitPanel_1.addComponent(table_1);
		
		return horizontalSplitPanel_1;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_1() {
		// common part: create layout
		verticalLayout_1 = new VerticalLayout();
		verticalLayout_1.setImmediate(false);
		verticalLayout_1.setWidth("-1px");
		verticalLayout_1.setHeight("500px");
		verticalLayout_1.setMargin(false);
		verticalLayout_1.setSpacing(true);
		
		// inlineDateField_2
		inlineDateField_2 = new InlineDateField();
		inlineDateField_2.setImmediate(false);
		inlineDateField_2.setWidth("100.0%");
		inlineDateField_2.setHeight("100.0%");
		verticalLayout_1.addComponent(inlineDateField_2);
		verticalLayout_1.setExpandRatio(inlineDateField_2, 1.0f);
		
		// latField
		latField = new TextField();
		latField.setImmediate(false);
		latField.setWidth("-1px");
		latField.setHeight("-1px");
		latField.setInputPrompt("Latitude");
		verticalLayout_1.addComponent(latField);
		
		// longField
		longField = new TextField();
		longField.setImmediate(false);
		longField.setWidth("-1px");
		longField.setHeight("-1px");
		longField.setInputPrompt("Longitude");
		verticalLayout_1.addComponent(longField);
		
		// radiusField
		radiusField = new TextField();
		radiusField.setImmediate(false);
		radiusField.setWidth("-1px");
		radiusField.setHeight("-1px");
		radiusField.setInputPrompt("Radius");
		verticalLayout_1.addComponent(radiusField);
		
		// maxMagField
		maxMagField = new TextField();
		maxMagField.setImmediate(false);
		maxMagField.setWidth("-1px");
		maxMagField.setHeight("-1px");
		maxMagField.setInputPrompt("Maximum Magnitude");
		verticalLayout_1.addComponent(maxMagField);
		
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
