package br.com.ecommerceorquideas.strategy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import br.com.ecommerceorquideas.dao.GeneroDAO;
import br.com.ecommerceorquideas.dao.IDAO;
import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.Genero;

public class GenVerificaNome implements IStrategy{

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Genero genero = (Genero) entidade;
		
		IDAO dao = new GeneroDAO();
		HashMap<String, String> map = new HashMap<>();
		map.put("genero", genero.getGenero());
		
		List<Object> generos = (List<Object>) dao.consultar(map);
		
		if(!generos.isEmpty()) {
			return Arrays.asList("Genero já cadastrado");		
		}
		
		return null;
	}

}
