package br.com.silvano;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javax.faces.component.UIComponent;
import javax.faces.component.UIPanel;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagHandler;
import javax.faces.view.facelets.TagConfig;
import javax.el.ValueExpression;
import javax.el.ExpressionFactory;
import java.util.Collection;
import javax.el.ELContext;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.panelgrid.PanelGrid;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import br.com.silvano.annotations.Button;
import br.com.silvano.annotations.Component;
import br.com.silvano.annotations.Model;
import java.util.ArrayList;

public class FormBuilder extends TagHandler {

	private String managedBeanName;
	private UIComponent parent;
	private String panelName;

	public FormBuilder(TagConfig config) {
		super(config);
		TagAttribute tag = getAttribute("managedBean");
		if (tag != null) {
			managedBeanName = tag.getValue();
			panelName = managedBeanName + "FormBuilder" + tagId;
		}
	}

	public void apply(FaceletContext context, UIComponent parent) throws IOException {
		this.parent = parent;

		UIPanel panel = (UIPanel) createComponent(UIPanel.COMPONENT_TYPE);
		panel.setId(panelName);

		UIComponent oldPanel = parent.findComponent(panelName);
		if (oldPanel != null) {
			parent.getChildren().remove(oldPanel);
		}

		Object managedBean = getManagedBean();
		if (managedBean != null) {

			PanelGrid panelGrid = createPanelGrid();
			parserFields(panelGrid, managedBean.getClass(), managedBeanName);

			Collection<UIComponent> methodsCollection = new ArrayList<UIComponent>();
			parserMethods(methodsCollection, managedBean);

			panel.getChildren().add(panelGrid);
			panel.getChildren().addAll(methodsCollection);
			parent.getChildren().add(panel);
		}
	}

	private void parserFields(PanelGrid panelGrid, Class<?> clazz, String managedBeanName) {
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Annotation[] annotations = field.getAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof Component) {
					parserComponent(panelGrid, (Component) annotation, field.getName());
				} else if (annotation instanceof Model) {
					for (Component component : ((Model) annotation).value()) {
						parserComponent(panelGrid, component, field.getName() + "." + component.attribute());
					}
				}
			}
		}
	}

	private void parserComponent(PanelGrid panelGrid, Component component, String fieldName) {
		panelGrid.getChildren().add(createOutputLabel(component.label(), fieldName));
		switch (component.type()) {
		case INPUT:
			panelGrid.getChildren().add(createInput(component.label(), fieldName, managedBeanName));
			break;
		case CHECKBOX:
			panelGrid.getChildren().add(createCheckBox(fieldName, managedBeanName));
			break;
		}
	}

	private void parserMethods(Collection<UIComponent> methodsCollection, Object object) {
		Method[] methods = object.getClass().getMethods();
		for (Method method : methods) {
			Annotation[] annotations = method.getAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof Button) {
					Button button = (Button) annotation;
					methodsCollection.add(createCommandButton(button.caption(), method.getName(), managedBeanName));
				}
			}
		}
	}

	private SelectBooleanCheckbox createCheckBox(String fieldName, String managedBeanName) {
		SelectBooleanCheckbox checkBox = (SelectBooleanCheckbox) createComponent(SelectBooleanCheckbox.COMPONENT_TYPE);
		ValueExpression ve = FacesContext.getCurrentInstance().getApplication().getExpressionFactory().createValueExpression(FacesContext.getCurrentInstance().getELContext(), "#{" + managedBeanName + "." + fieldName + "}", Object.class);
		checkBox.setValueExpression("value", ve);
		checkBox.setId(getComponentId(fieldName));
		return checkBox;
	}

	private PanelGrid createPanelGrid() {
		PanelGrid panelGrid = (PanelGrid) createComponent(PanelGrid.COMPONENT_TYPE);
		panelGrid.setColumns(2);
		return panelGrid;
	}

	private OutputLabel createOutputLabel(String componentValue, String forComponent) {
		OutputLabel outputLabel = (OutputLabel) createComponent(OutputLabel.COMPONENT_TYPE);
		outputLabel.setValue(componentValue);
		outputLabel.setFor(getComponentId(forComponent));
		return outputLabel;
	}

	private InputText createInput(String label, String fieldName, String managedBeanName) {
		InputText inputText = (InputText) createComponent(InputText.COMPONENT_TYPE);
		ValueExpression ve = FacesContext.getCurrentInstance().getApplication().getExpressionFactory().createValueExpression(FacesContext.getCurrentInstance().getELContext(), "#{" + managedBeanName + "." + fieldName + "}", Object.class);
		inputText.setValueExpression("value", ve);
		inputText.setId(getComponentId(fieldName));
		return inputText;
	}

	private CommandButton createCommandButton(String value, String action, String managedBeanName) {
		CommandButton commandButton = (CommandButton) createComponent(CommandButton.COMPONENT_TYPE);
		commandButton.setAjax(false);
		commandButton.setValue(value);
		FacesContext faces = FacesContext.getCurrentInstance();
		ELContext ec = faces.getELContext();
		ExpressionFactory ef = faces.getApplication().getExpressionFactory();
		commandButton.setActionExpression(ef.createMethodExpression(ec, "#{" + managedBeanName + "." + action + "}", Void.TYPE, new Class<?>[0]));
		commandButton.setId(managedBeanName + action);
		commandButton.setUpdate(parent.getId());
		return commandButton;
	}

	private String getComponentId(String componentName) {
		return componentName.replace(".", "");
	}

	private Object getManagedBean() {
		return FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(FacesContext.getCurrentInstance().getELContext(), null, managedBeanName);
	}

	private UIComponent createComponent(String componentType) {
		return FacesContext.getCurrentInstance().getApplication().createComponent(componentType);
	}
}