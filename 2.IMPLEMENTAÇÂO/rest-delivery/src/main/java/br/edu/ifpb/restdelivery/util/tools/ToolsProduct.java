package br.edu.ifpb.restdelivery.util.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;

import javax.faces.context.FacesContext;

import br.edu.ifpb.restdelivery.entities.ItemMenu;
import br.edu.ifpb.restdelivery.entities.Product;

/**
 * Classe com métodos utilizatários para Product.
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public class ToolsProduct {

	/**
	 * Método que salva as images no diretório home da máquina aonde o sistema
	 * está rodando.
	 * 
	 * @param img
	 * @param destionation
	 * @param fileName
	 */
	public static void saveImageToDiretory(byte[] img, String destionation, String fileName) {
		try {
			String home = System.getProperty("user.home");

			File diretorio = new File(home, destionation);

			if (!diretorio.exists()) {
				diretorio.mkdirs();
			}

			byte[] conteudo = img;
			FileOutputStream fos = new FileOutputStream(home + "/" + destionation + "/" + fileName);
			fos.write(conteudo);
			fos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Método que copia as imagens dos produtos do home da máquina para a pasta
	 * resource da aplicação.
	 * 
	 * @param filePath
	 * @param fileName
	 * @throws IOException
	 */
	public static void copy(String filePath, String fileName) throws IOException {
		FileInputStream origem;
		FileOutputStream destino;
		FileChannel fcOrigem;
		FileChannel fcDestino;
		String home = System.getProperty("user.home");
		String destiny = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/images/");

		origem = new FileInputStream(home + "/restDelivery/" + filePath);
		destino = new FileOutputStream(destiny + "/" + fileName);

		fcOrigem = origem.getChannel();
		fcDestino = destino.getChannel();
		fcOrigem.transferTo(0, fcOrigem.size(), fcDestino);

		origem.close();
		destino.close();
	}

	/**
	 * Método que copia as imagens dos produtos do home da máquina para a pasta
	 * resource da aplicação.
	 * 
	 * @param products
	 */
	public static void loadImgProduct(List<Product> products) {
		for (Product p : products) {
			try {
				ToolsProduct.copy(p.getPathFile(), p.getPathFile());

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * * Método que copia as imagens dos produtos do home da máquina para a
	 * pasta resource da aplicação.
	 * 
	 * @param itemMenu
	 */
	public static void loadImgItemMenu(ItemMenu itemMenu) {
		try {
			if (itemMenu.getProduct().getPathFile() != null || !itemMenu.getProduct().getPathFile().isEmpty()) {

				ToolsProduct.copy(itemMenu.getProduct().getPathFile(), itemMenu.getProduct().getPathFile());

			}

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 *  Método que copia as imagens dos produtos do home da máquina para a
	 * pasta resource da aplicação.
	 * 
	 * @param products
	 */
	public static void loadImgItens(List<ItemMenu> products) {
		for (ItemMenu p : products) {
			try {
				if (p.getProduct().getPathFile() != null || !p.getProduct().getPathFile().isEmpty()) {
					ToolsProduct.copy(p.getProduct().getPathFile(), p.getProduct().getPathFile());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
