package br.edu.ifpb.restdelivery.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import br.edu.ifpb.restdelivery.entities.Product;
import br.edu.ifpb.restdelivery.enumerations.CategoryType;
import br.edu.ifpb.restdelivery.enumerations.PathFile;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryException;
import br.edu.ifpb.restdelivery.services.impl.ProductService;
import br.edu.ifpb.restdelivery.util.tools.ToolsProduct;
/**
 * Classe controlle para Product
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
@Named
@ViewScoped
public class ProductBean extends AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ProductService service;

	private Product product;

	private UploadedFile file;

	@PostConstruct
	public void init() {
		if (product == null) {
			product = new Product();
		}
	}

	/**
	 * Método que salva ou atualiza um novo product no bancxo
	 * @return
	 * @throws RestDeliveryException
	 */
	public String save() throws RestDeliveryException {

		try {

			if (product.getId() == null) {
				service.save(product);
				reportSuccessMensage("Item de menu salvo!");
				product = new Product();

			} else {
				service.update(product);
				reportSuccessMensage("Item de menu alterado!");
				product = new Product();
				return "product?faces-redirect=true";

			}

		} catch (Exception e) {
			reportErroMensage("Já existe um item com esse nome");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Método implementado para upload de foto.
	 * @param event
	 */
	public void handleFileUpload(FileUploadEvent event) {

		ToolsProduct.saveImageToDiretory(event.getFile().getContents(), PathFile.RESTDELIVERY.getName(), event.getFile().getFileName());
		product.setPathFile(event.getFile().getFileName());

		reportSuccessMensage(event.getFile().getFileName() + " foi upado.");

	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * Recupera lista de categorias de produtos.
	 * @return
	 */
	public CategoryType[] getCategories() {
		return CategoryType.values();
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
	public Double avegareProduct(String name){
		return service.averageProductRating(name);
	}
	
	/**
	 * Recupera todos os itens do menu cadastrados.s
	 * @return
	 */
	public String getNumberItens(){
		String value = "0";
		if(service.countAll() <10){
			value = "0"+service.countAll().toString();
		}else{
			value = service.countAll().toString();
		}
		return value;
	}

}
