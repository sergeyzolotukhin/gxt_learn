package ua.in.sz.gxt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.data.shared.StringLabelProvider;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class ProjectEntryPoint implements EntryPoint {

	@Override
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();

		rootPanel.add(this::createContentPanel);
	}

	private ContentPanel createContentPanel() {
		ContentPanel panel = new ContentPanel();
		panel.setHeading("Basic Grid");
		panel.add(this::createLayout);
		return panel;
	}

	private VerticalLayoutContainer createLayout() {
		VerticalLayoutContainer con = new VerticalLayoutContainer();
		con.add(this::createToolBar, new VerticalLayoutContainer.VerticalLayoutData(1, -1));
//        con.add(grid, new VerticalLayoutContainer.VerticalLayoutData(1, 1));
		return con;
	}

	private ToolBar createToolBar() {
		ToolBar toolBar = new ToolBar();
		toolBar.setEnableOverflow(false);
		toolBar.add(new LabelToolItem("Selection Mode: "));
		toolBar.add(this::createComboBox);
		return toolBar;
	}

	private ComboBox<String> createComboBox() {
		SimpleComboBox<String> typeComboBox = new SimpleComboBox<String>(new StringLabelProvider<String>());
		typeComboBox.setTriggerAction(ComboBoxCell.TriggerAction.ALL);
		typeComboBox.setEditable(false);
		typeComboBox.setWidth(100);
		typeComboBox.add("Row");
		typeComboBox.add("Cell");
		typeComboBox.setValue("Row");
		return typeComboBox;
	}
}
