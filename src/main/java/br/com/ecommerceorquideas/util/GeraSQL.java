package br.com.ecommerceorquideas.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class GeraSQL {

	public static String insert(HashMap<String, String> map, String tabela) {
		String insert =  "INSERT INTO " + tabela;
		String chave = "(";
		String valor = "VALUES(";
		
		for (Entry<String, String> pair : map.entrySet()) {
		    chave += pair.getKey() + ", ";
		    valor += pair.getValue() + ", ";
		}
		chave = chave.substring(0, chave.length() - 2);
		chave += ") ";
		
		valor = valor.substring(0, valor.length() - 2);
		valor += ")";
		
		insert += chave + valor;
		return insert;
	}
	
	public static String select(HashMap<String, String> map, String tabela) {
		String find = "SELECT * FROM " + tabela + " WHERE " ;//alu_id=?";
		
		
		for (Entry<String, String> pair : map.entrySet()) {
		    if(isEqual(pair.getKey())) {
		    	find += pair.getKey() + "=" + pair.getValue() + " and ";
		    }else {
		    	find += pair.getKey() + "='" + pair.getValue() + "' and ";
		    }			
		}
		find = find.substring(0, find.length() - 5);
		
		return find;
	}
	
	public static boolean isEqual(String palavra) {
		List<String> ids = new ArrayList<String>();
		ids.add("id");
		ids.add("cli_id");
		
		for(String id : ids) {
			if(palavra.equals(id))
				return true;			
		}
		return false;
		
	}
}


