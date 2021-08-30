package br.com.ecommerceorquideas.mapa;

import java.util.HashMap;

import br.com.ecommerceorquideas.domain.EntidadeDominio;

public interface IMapa {

	public HashMap<String, String> mapeia(EntidadeDominio entidade);
}
