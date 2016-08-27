package br.edu.ifpb.restdelivery.entities;

import java.math.BigDecimal;
import java.util.Date;

public class DateValor {
	
	private Date date;
	private BigDecimal value;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	

}
