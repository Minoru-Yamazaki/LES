package br.com.ecommerceorquideas.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FormataData {

	public static String ddMMyy(Date data) {
		SimpleDateFormat formatador = new SimpleDateFormat("dd-MM-yyyy");
    	String dataFormatada = formatador.format(data);
    	
    	return dataFormatada;		
	}
	
	public static String yyMMdd(Date data) {
		SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
    	String dataFormatada = formatador.format(data);
    	
    	return dataFormatada;		
	}
	
	public static String MMMyy(Date data) {
		SimpleDateFormat formatador = new SimpleDateFormat("MMM-yyyy");
    	String dataFormatada = formatador.format(data);
    	
    	return dataFormatada;		
	}
	
	//Gera data um mês depois da data informada
	public static Date mesSeguinte(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.MONTH, 1);        
		
		return calendar.getTime();
	}
}
