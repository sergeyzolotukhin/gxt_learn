package ua.in.sz.gxt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.core.client.GXT;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.StringLabelProvider;
import com.sencha.gxt.state.client.CookieProvider;
import com.sencha.gxt.state.client.GridStateHandler;
import com.sencha.gxt.state.client.StateManager;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import com.sencha.gxt.widget.core.client.grid.Grid;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectEntryPoint implements EntryPoint {

	private static final StockProperties props = GWT.create(StockProperties.class);

	@Override
	public void onModuleLoad() {
		StateManager.get().setProvider(new CookieProvider("/", null, null, GXT.isSecure()));

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
        con.add(createGrid(), new VerticalLayoutContainer.VerticalLayoutData(1, 1));
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

	private Grid<Stock> createGrid() {
		Grid<Stock> grid = new Grid<Stock>(createStore(), createColumnModel());
		grid.setAllowTextSelection(false);
//		grid.getView().setAutoExpandColumn(nameCol);
		grid.getView().setStripeRows(true);
		grid.getView().setColumnLines(true);
		grid.setBorders(false);
		grid.setColumnReordering(true);

		grid.setStateful(true);
		grid.setStateId("gridExample");

		GridStateHandler<Stock> state = new GridStateHandler<Stock>(grid);
		state.loadState();

		return grid;
	}

	private ListStore<Stock> createStore() {
		ListStore<Stock> store = new ListStore<Stock>(props.key());
		store.addAll(TestData.getStocks());
		return store;
	}

	private ColumnModel<Stock> createColumnModel() {
		ColumnConfig<Stock, String> nameCol = new ColumnConfig<Stock, String>(props.name(), 50, "Company");
		ColumnConfig<Stock, String> symbolCol = new ColumnConfig<Stock, String>(props.symbol(), 75, "Symbol");
		ColumnConfig<Stock, Double> lastCol = new ColumnConfig<Stock, Double>(props.last(), 75, "Last");
		ColumnConfig<Stock, Double> changeCol = new ColumnConfig<Stock, Double>(props.change(), 75, "Change");
		ColumnConfig<Stock, Date> lastTransCol = new ColumnConfig<Stock, Date>(props.lastTrans(), 100, "Last Updated");


		List<ColumnConfig<Stock, ?>> columns = new ArrayList<ColumnConfig<Stock, ?>>();
		columns.add(nameCol);
		columns.add(symbolCol);
		columns.add(lastCol);
		columns.add(changeCol);
		columns.add(lastTransCol);

		return new ColumnModel<Stock>(columns);
	}
}
