package br.edu.ifpb.restdelivery.converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.restdelivery.entities.Employee;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryException;
import br.edu.ifpb.restdelivery.services.impl.EmployeeService;

@Named
@RequestScoped
public class EmployeeConverter implements Converter{

	@Inject
	private EmployeeService employeeService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		if (value == null || value.trim().isEmpty()) {
			return null;
		}

		Long id = Long.parseLong(value);

		try {
			return employeeService.find(id);
	
		} catch (RestDeliveryException e) {
			String msgErroStr = String.format(
					"Erro de conversão! Não foi possível realizar a conversão da string '%s' para o tipo esperado.",
					value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			throw new ConverterException(msgErro);

		}catch (NumberFormatException e) {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}
		Long id = ((Employee) value).getId();

		return (id != null) ? id.toString() : null;
	}

}
