package br.edu.ifpb.restdelivery.util.tools;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import br.edu.ifpb.restdelivery.entities.ItemProduct;
import br.edu.ifpb.restdelivery.enumerations.StateNames;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryException;

/**
 * Classe com métodos utilitários.
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public class Tools {

	/**
	 * Método que criptografa uma senha no formato String no formato SHA-256.
	 * @param password
	 * @return String criptografada.
	 * @throws RestDeliveryException
	 */
	public static String criptografarSenha(String password) throws RestDeliveryException {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes("UTF-8"));
			byte[] digest = md.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			String output = bigInt.toString(16);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new RestDeliveryException("Não foi possível criptografar a senha!");
		} catch (UnsupportedEncodingException e) {
			throw new RestDeliveryException("Não foi possível criptografar a senha!");
		}
	}
	
	
	/**
	 * Metodo retorna a difrença de dias entre duas datas do tipo Date
	 * @param dataDoEvento
	 * @return
	 */
	public int diferencaEntreDatasEmDias (Date dataDoEvento){
		LocalDate data = LocalDate.now();
		
		LocalDate evento = dataDoEvento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		long diasRestantes = ChronoUnit.DAYS.between(data, evento);
		
		
		return  (int) diasRestantes ;
	}
	


	/**
	 * Método que calcula o preço total dos itens passando a quantidade desse item e seu preço.
 	 * @param quant - quantidade.
	 * @param price -preço
	 * @return
	 */
	public static Float priceItem(Integer quant, Float price) {
		float value = quant * price;
		return value;
	}

	
	/**
	 * Método que calcula o preço total da lista de itens do pedido.
	 * @param itens 
	 * @return preço total com todos os itens.
	 */
	public static Float priceAllItens(List<ItemProduct> itens) {
		float value = 0;
		for (int i = 0; i < itens.size(); i++) {
			value += itens.get(i).getPrice();
		}
		return value;
	}

	/**
	 * Método que recupera o id do estado passando como parâmetro seu nome.
	 * @param name
	 * @return
	 */
	public static int getIdState(String name) {
		StateNames[] values = StateNames.values();
		for (int i = 0; i < values.length; i++) {
			String n = values[i].getNome();
			if (name.equalsIgnoreCase(n)) {
				return values[i].getId();
			}
		}

		return 0;
	}

	/**
	 * Método que recupera nome de login do usuário logado.
	 * @return - Nome de login do usuário
	 */
	public static String recoversUserName() {
		Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		if (principal != null) {
			return principal.getName();
		}
		return null;
	}

	

}
