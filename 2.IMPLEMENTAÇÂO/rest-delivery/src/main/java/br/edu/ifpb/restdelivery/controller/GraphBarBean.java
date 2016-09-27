package br.edu.ifpb.restdelivery.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.extensions.component.gchart.model.GChartModel;
import org.primefaces.extensions.component.gchart.model.GChartModelBuilder;
import org.primefaces.extensions.component.gchart.model.GChartType;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.edu.ifpb.restdelivery.entities.ResultMap;
import br.edu.ifpb.restdelivery.services.impl.CustomerService;
import br.edu.ifpb.restdelivery.services.impl.OrderService;

@Named
@RequestScoped
public class GraphBarBean {

	private BarChartModel barModel;
	private BarChartModel barModel2;
	
	private GChartModel chartModel ;


	private static DateFormat DATE_FORMART = new SimpleDateFormat("dd/MM");

	@Inject
	private OrderService orderService;

	@Inject
	CustomerService customerService;

	@PostConstruct
	public void init() {
		createBarModels();
		createPieExt();

	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public BarChartModel getBarModel2() {
		return barModel2;
	}

	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();

		/*
		 * Map<String, Long> valuesToDate =
		 * orderService.findOrderToAmountBuy(0l);
		 * 
		 * ChartSeries product = null;
		 * 
		 * for (String name : valuesToDate.keySet()) {
		 * 
		 * product = new ChartSeries();
		 * 
		 * product.setLabel(name);
		 * 
		 * for (String n : valuesToDate.keySet()) { if (name.equals(n)) {
		 * product.set(n, valuesToDate.get(n)*100); } }
		 * 
		 * model.addSeries(product); }
		 * 
		 * return model;
		 */

		Map<Date, ResultMap> valuesToDate = orderService.findSoBuy(15);

		ChartSeries product = null;

		for (Date date : valuesToDate.keySet()) {

			if (valuesToDate.get(date) != null) {
				product = new ChartSeries();

				ResultMap result = valuesToDate.get(date);

				product.setLabel(result.getName());

				for (Date d : valuesToDate.keySet()) {

					if (valuesToDate.get(d) != null && valuesToDate.get(d).getName().equals(result.getName())) {
						product.set(DATE_FORMART.format(d), (valuesToDate.get(d).getValue() * 10));
					}
				}
				model.addSeries(product);

			}

		}

		return model;
	}

	private BarChartModel initBarModel2() {
		BarChartModel model = new BarChartModel();

		Map<Date, Long> valuesToDate = customerService.findCustomerToDate(7);

		ChartSeries customer = new ChartSeries();
		customer.setLabel("Clientes");

		for (Date date : valuesToDate.keySet()) {
			if (valuesToDate.get(date) > 0) {
				customer.set(DATE_FORMART.format(date), valuesToDate.get(date) * 10);
			}
		}
		model.addSeries(customer);

		customer = addSerieChatToCustomerToSexo("M");
		customer.setLabel("Clientes Homens");
		model.addSeries(customer);

		customer = addSerieChatToCustomerToSexo("F");
		customer.setLabel("Clientes Mulheres");
		model.addSeries(customer);

		return model;

	}

	private ChartSeries addSerieChatToCustomerToSexo(String sexo) {

		ChartSeries customer = new ChartSeries();
		Map<Date, Long> valuesToDate = customerService.findCustomerToDateToSexo(7, sexo);
		for (Date date : valuesToDate.keySet()) {
			if (valuesToDate.get(date) > 0) {
				customer.set(DATE_FORMART.format(date), valuesToDate.get(date) * 10);
			}
		}
		return customer;
	}

	private void createPieExt() {

		GChartModelBuilder builder1 = new GChartModelBuilder().setChartType(GChartType.COLUMN);

		Map<Date, Long> values = this.customerService.findCustomerToDate(5);

		builder1.addColumns("Clientes", "Quantidade");

		for (Date c : values.keySet()) {
			builder1.addRow(DATE_FORMART.format(c), values.get(c));
		}

		chartModel = builder1.build();
	}

	private void createBarModels() {
		createBarModel();
		createBarModel2();
	}

	private void createBarModel() {
		barModel = initBarModel();

		barModel.setTitle("Quantidade comprado de cada Item");
		barModel.setLegendPosition("ne");

		Axis xAxis = barModel.getAxis(AxisType.X);
		xAxis.setLabel("Nome");

		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("Quantidade");
		yAxis.setMin(0);
		yAxis.setMax(200);
	}

	private void createBarModel2() {
		barModel = initBarModel2();

		barModel.setTitle("Situação Clientes");
		barModel.setLegendPosition("ne");

		Axis xAxis = barModel.getAxis(AxisType.X);
		xAxis.setLabel("Data");

		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("Quantidade");
		yAxis.setMin(0);
		yAxis.setMax(200);
	}
	
	public GChartModel getChart() {
		return chartModel;
	}

}
