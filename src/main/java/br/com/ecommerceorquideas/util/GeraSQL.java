package br.com.ecommerceorquideas.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import br.com.ecommerceorquideas.model.Analise;

public class GeraSQL {

	public static String select(HashMap<String, String> map, String tabela) {
		String find = "SELECT * FROM " + tabela + " WHERE ";// alu_id=?";
				
		for (Entry<String, String> pair : map.entrySet()) {

			if (isId(pair.getKey())) {
				find += pair.getKey() + "=" + pair.getValue() + " and ";
			}else if(pair.getKey() == "nome") {
				find += "nome LIKE '%" + pair.getValue() + "%' and ";
			}else if(pair.getKey() == "valor_venda") {
				find += pair.getKey() + "<=" + pair.getValue() + " and ";
			}else {
				find += pair.getKey() + "='" + pair.getValue() + "' and ";
			}
		}
		find = find.substring(0, find.length() - 5);
		
		return find;
	}

	public static boolean isId(String palavra) {
		List<String> numeros = new ArrayList<String>();
		numeros.add("adm_id");
		numeros.add("ativo");
		numeros.add("cli_id");
		numeros.add("id");
		numeros.add("id!");
		numeros.add("ped_id");
		numeros.add("pro_id");
		numeros.add("preco");
		numeros.add("prc_id");
		numeros.add("quantidade");
		numeros.add("orq_id");
		numeros.add("ranking");
		numeros.add("total_gasto");
		numeros.add("valor_custo");
		numeros.add("valor");
		
		for (String numero : numeros) {
			if (palavra.equals(numero))
				return true;
		}
		return false;

	}
	
	public static String analiseSelect(Analise analise) {
		StringBuilder select = new StringBuilder();
		
		select.append("SELECT * FROM compras WHERE data>='");
		select.append(FormataData.yyMMdd(analise.getDataInicial()));
		select.append("' && data<='");
		select.append(FormataData.yyMMdd(analise.getDataFinal()));
		select.append("' && status NOT LIKE '%Cancelamento%'");
		
		
		return select.toString();
	}
}
