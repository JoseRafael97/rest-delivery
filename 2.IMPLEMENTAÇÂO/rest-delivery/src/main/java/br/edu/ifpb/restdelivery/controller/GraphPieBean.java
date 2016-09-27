package br.edu.ifpb.restdelivery.controller;

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;

import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.extensions.component.gchart.model.GChartModel;
import org.primefaces.extensions.component.gchart.model.GChartModelBuilder;
import org.primefaces.extensions.component.gchart.model.GChartType;
import org.primefaces.model.chart.PieChartModel;

import br.edu.ifpb.restdelivery.exceptions.RestDeliveryPersistenceException;
import br.edu.ifpb.restdelivery.services.impl.ProductService;

/**
 * Controller para gráficos de pizza primefaces e jsf
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
@Named
@RequestScoped
public class GraphPieBean extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PieChartModel pieModel1;
	private PieChartModel pieModel2;
	private GChartModel chartModel ;
	private GChartModel chartModel2;
	
	@Inject
	private ProductService productService;

	@PostConstruct
	public void init() {
		createPieModel1();
		createPieModel2();
		createPieExt();
		createPieExt2();

	}

	/**
	 * Método que cria gráfico pizza 1
	 */
	private void createPieModel1() {
		pieModel1 = new PieChartModel();

		try {

			Map<String, BigDecimal> values = this.productService.ProductsSoBuy();

			for (String c : values.keySet()) {
				pieModel1.set(c, values.get(c));
			}

		} catch (RestDeliveryPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		pieModel1.setTitle("Avalição dos itens por categoria");
		pieModel1.setLegendPosition("w");
		pieModel1.setShowDataLabels(true);
		pieModel1.setSeriesColors("1E90FF, FF8C00, 9ACD32,63B8FF,FF6347,FFC0CB,00FF00,FFFF00");
	}

	private void createPieExt() {

		GChartModelBuilder builder1 = new GChartModelBuilder().setChartType(GChartType.PIE);

		try {

			Map<String, BigDecimal> values = this.productService.ProductsSoBuy();

			builder1.addColumns("Categorias", "Valores");
			
			for (String c : values.keySet()) {
				builder1.addRow(c, values.get(c));
			}

		} catch (RestDeliveryPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		chartModel = builder1.build();
	}

	/**
	 * Método que cria gráfico pizza 2
	 */
	private void createPieModel2() {
		pieModel2 = new PieChartModel();

		try {

			Map<String, BigDecimal> values = this.productService.findBestsellingCategory();
			for (String c : values.keySet()) {
				pieModel2.set(c, values.get(c));
			}

		} catch (RestDeliveryPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		pieModel2.setTitle("Vendas dos itens por categoria");
		pieModel2.setLegendPosition("w");
		pieModel2.setShowDataLabels(true);
		pieModel2.setSeriesColors("1E90FF, FF8C00, 9ACD32,63B8FF,FF6347,FFC0CB,00FF00,FFFF00");

	}
	
	private void createPieExt2() {
		GChartModelBuilder builder1 = new GChartModelBuilder().setChartType(GChartType.PIE);
		
		try {

			Map<String, BigDecimal> values = this.productService.findBestsellingCategory();

			builder1.addColumns("Categorias", "Valores");
			
			for (String c : values.keySet()) {
				builder1.addRow(c, values.get(c));
			}

		} catch (RestDeliveryPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		chartModel2 = builder1.build();

	}

	public PieChartModel getPieModel1() {
		return pieModel1;
	}

	public PieChartModel getPieModel2() {
		return pieModel2;
	}

	public GChartModel getChart() {
		return chartModel;
	}
	
	public GChartModel getChart2() {
		return chartModel2;
	}


}
