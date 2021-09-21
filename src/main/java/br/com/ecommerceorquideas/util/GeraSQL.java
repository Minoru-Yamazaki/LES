package br.com.ecommerceorquideas.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class GeraSQL {

	public static String select(HashMap<String, String> map, String tabela) {
		String find = "SELECT * FROM " + tabela + " WHERE ";// alu_id=?";

		for (Entry<String, String> pair : map.entrySet()) {

			if (isId(pair.getKey())) {
				find += pair.getKey() + "=" + pair.getValue() + " and ";
			} else {
				find += pair.getKey() + "='" + pair.getValue() + "' and ";
			}
		}
		find = find.substring(0, find.length() - 5);
		
		return find;
	}

	public static boolean isId(String palavra) {
		List<String> ids = new ArrayList<String>();
		ids.add("id");
		ids.add("cli_id");
		ids.add("id!");

		for (String id : ids) {
			if (palavra.equals(id))
				return true;
		}
		return false;

	}
}
