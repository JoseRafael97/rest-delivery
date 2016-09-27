package br.edu.ifpb.restdelivery.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.edu.ifpb.restdelivery.entities.ResultMap;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryPersistenceException;
import br.edu.ifpb.restdelivery.services.impl.OrderService;

/**
 * Classe controller para gráficos de linha do primefacess.
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
@Named
@RequestScoped
public class GraphLineBean extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static DateFormat DATE_FORMART = new SimpleDateFormat("dd/MM");

	@Inject
	private OrderService orderService;
	private LineChartModel lineModel1;
	private LineChartModel lineModel2;
	private LineChartModel lineModel3;

	private String numberDays;

	/**
	 * Inicializa atributos.
	 */
	@PostConstruct
	public void init() {
		createLineModels();
		createLineModels2();
		createLineModels3();
		numberDays = "8";
	}

	public LineChartModel getLineModel1() {
		return lineModel1;
	}

	public LineChartModel getLineModel2() {
		return lineModel2;
	}

	public LineChartModel getLineModel3() {
		return lineModel3;
	}

	/**
	 * Cria gráfico de linhas
	 */
	private void createLineModels() {
		lineModel1 = initLinearModel();
		lineModel1.setTitle("Pedidos");
		lineModel1.setLegendPosition("nw");

		Axis yAxis = lineModel1.getAxis(AxisType.Y);
		lineModel1.setSeriesColors("337ab7, 5cb85c, 5bc0de,f0ad4e,d9534f");

		lineModel1.setAnimate(true);
		lineModel1.setShowPointLabels(true);
		lineModel1.getAxes().put(AxisType.X, new CategoryAxis("Datas"));
		yAxis = lineModel1.getAxis(AxisType.Y);
		yAxis.setLabel("Valores");
		yAxis.setMin(0);
	}

	/**
	 * Cria gráfico de linhas 2
	 */
	private void createLineModels2() {
		lineModel2 = initLinearModel2();
		lineModel2.setTitle("Compra de Itens");
		lineModel2.setLegendPosition("nw");

		Axis yAxis = lineModel2.getAxis(AxisType.Y);

		lineModel2.setAnimate(true);
		lineModel2.setSeriesColors("337ab7, 5cb85c, 5bc0de,f0ad4e,d9534f");
		lineModel2.setShowPointLabels(true);
		lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Datas"));
		yAxis = lineModel2.getAxis(AxisType.Y);
		yAxis.setLabel("Quantidade");
		yAxis.setMin(0);
	}

	/**
	 * Cria gráfico de linhas 3
	 */
	private void createLineModels3() {
		lineModel3 = initLinearModel3();
		lineModel3.setTitle("Avaliação do RestDelivery");
		lineModel3.setLegendPosition("nw");

		Axis yAxis = lineModel3.getAxis(AxisType.Y);
		lineModel3.setSeriesColors("337ab7, 5cb85c, 5bc0de,f0ad4e,d9534f");

		lineModel3.setAnimate(true);
		lineModel3.setShowPointLabels(true);
		lineModel3.getAxes().put(AxisType.X, new CategoryAxis("Datas"));
		yAxis = lineModel3.getAxis(AxisType.Y);
		yAxis.setLabel("Média");
		yAxis.setMin(0);
	}

	/**
	 * Inicializar dados do gráfico
	 * 
	 * @return
	 */
	private LineChartModel initLinearModel() {
		LineChartModel model = new LineChartModel();

		try {

			LineChartSeries series1 = new LineChartSeries();

			Map<Date, BigDecimal> valuesToDate;

			valuesToDate = this.orderService.allValuesToDate(7);

			series1.setLabel("Todos os pedidos");

			for (Date date : valuesToDate.keySet()) {

				series1.set(DATE_FORMART.format(date), valuesToDate.get(date));
			}

			model.addSeries(series1);

		} catch (RestDeliveryPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return model;
	}

	/**
	 * Inicializar dados do gráfico
	 * 
	 * @return
	 */
	private LineChartModel initLinearModel2() {

		LineChartModel model = new LineChartModel();

		Map<Date, ResultMap> valuesToDate = orderService.findSoBuy( 15);

		LineChartSeries product = null;
		
		System.out.println(valuesToDate);


		for (Date date : valuesToDate.keySet()) {

			if (valuesToDate.get(date) != null) {
				product = new LineChartSeries();

				ResultMap result = valuesToDate.get(date);

				product.setLabel(result.getName());
				

				for (Date d : valuesToDate.keySet()) {

					if (valuesToDate.get(d) != null && valuesToDate.get(d).getName().equals(result.getName())) {
						product.set(DATE_FORMART.format(d), (valuesToDate.get(d).getValue()));
					} else {
						product.set(DATE_FORMART.format(d), 0);
					}
				}
				model.addSeries(product);

			}

		}

		return model;
	}

	private LineChartModel initLinearModel3() {
		LineChartModel model = new LineChartModel();

		Map<Date, Double> valuesToDate = orderService.findAverageBuy(15);
		
		LineChartSeries product = new LineChartSeries();
		product.setLabel("Média da avaliação");

		Double valor = 0.0;

		for (Date date : valuesToDate.keySet()) {

			if (valuesToDate.get(date) == null || valuesToDate.get(date) == 0.0) {
				product.set(DATE_FORMART.format(date), valor);
			
			} else {
				valor = valuesToDate.get(date);
				product.set(DATE_FORMART.format(date), valor);
			}

		}

		model.addSeries(product);

		return model;

	}

	public String getNumberDays() {
		return numberDays;
	}

	public void setNumberDays(String numberDays) {
		this.numberDays = numberDays;
	}

}
