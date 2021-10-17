package br.com.ecommerceorquideas.mapa;

import java.util.HashMap;

import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.Produto;

public class ProdutoMapa implements IMapa{

	@Override
	public HashMap<String, String> mapeia(EntidadeDominio entidade) {
		Produto produto = (Produto) entidade;
		HashMap<String, String> map = new HashMap<>();
		
		if(produto.getId() != null)
			map.put("id", produto.getId().toString());
		if(produto.getIdProduto() != null)
			map.put("pro_id", produto.getIdProduto().toString());
		if(produto.getPreco() != null)
			map.put("preco", produto.getPreco().toString());
		if(produto.getNome() != null)
			map.put("nome", produto.getNome());
		if(produto.getQuantidade() != null)
			map.put("quantidade", produto.getQuantidade().toString());
		if(produto.getIdCompra() != null)
			map.put("ped_id", produto.getIdCompra().toString());
		
		return map;
	}

}
