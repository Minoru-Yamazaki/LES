package br.com.ecommerceorquideas.util;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.ecommerceorquideas.dao.CupomDAO;
import br.com.ecommerceorquideas.dao.IDAO;
import br.com.ecommerceorquideas.model.Compra;
import br.com.ecommerceorquideas.model.Cupom;

public class CupomTroca {

	public static void geraCupom(Compra compra, Double valor, String descricao, Date validade) {
		SimpleDateFormat formatoPadrao = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		SimpleDateFormat formatoNome = new SimpleDateFormat("ddMMyyyyHHmmss");
		String dataHora = formatoPadrao.format(compra.getData()).toString();
		
		//"Cupom de troca de produto da compra do dia : ";
		descricao += dataHora;
		
		String nome = "Cupom de troca " + formatoNome.format(compra.getData()).toString();
		System.out.println(nome);
		Date dataValidade = geraDataValidade(validade);
		
		Cupom cupom = new Cupom(
				nome, descricao.toString(), "troca",
				valor, dataValidade, compra.getCliId());
		
		IDAO dao = new CupomDAO();
		
		try {
			dao.salvar(cupom);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Date geraDataValidade(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.MONTH, 3);        
		
		return calendar.getTime();
	}
}
