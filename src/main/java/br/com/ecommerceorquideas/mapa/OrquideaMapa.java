package br.com.ecommerceorquideas.mapa;

import java.util.HashMap;

import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.Orquidea;

public class OrquideaMapa implements IMapa{

	@Override
	public HashMap<String, String> mapeia(EntidadeDominio entidade) {
		Orquidea orquidea = (Orquidea) entidade;
		HashMap<String, String> map = new HashMap<>();
		
		if(orquidea.getId() != null)
			map.put("id", orquidea.getId().toString());
		if(orquidea.getAtivo() != null)
			map.put("ativo", orquidea.getAtivo().toString());
		if(orquidea.getCategoriaAtivacao() != null)
			map.put("categoria_ativacao", orquidea.getCategoriaAtivacao());
		if(orquidea.getCategoriaInativacao() != null)
			map.put("categoria_inativacao", orquidea.getCategoriaInativacao());
		if(orquidea.getQuantidade() != null)
			map.put("quantidade", orquidea.getQuantidade().toString());
		if(orquidea.getCor() != null)
			map.put("cor", orquidea.getCor());
		if(orquidea.getValorCusto() != null)
			map.put("valor_custo", orquidea.getValorCusto().toString());
		if(orquidea.getValorVenda() != null)
			map.put("valor_venda", orquidea.getValorVenda().toString());
		if(orquidea.getCodigoBarras() != null)
			map.put("codigo_barras", orquidea.getCodigoBarras());
		if(orquidea.getNome() != null)
			map.put("nome", orquidea.getNome());
		if(orquidea.getGenero() != null)
			map.put("genero", orquidea.getGenero());
		if(orquidea.getTipo() != null)
			map.put("tipo", orquidea.getTipo());
		if(orquidea.getTamanho() != null)
			map.put("tamanho", orquidea.getTamanho());
		if(orquidea.getClima() != null)
			map.put("clima", orquidea.getClima());
		if(orquidea.getSombreamento() != null)
			map.put("sombreamento", orquidea.getSombreamento());
		if(orquidea.getUmidadeAmbiente() != null)
			map.put("umidade_ambiente", orquidea.getUmidadeAmbiente());
		if(orquidea.getFornecedor() != null)
			map.put("fornecedor", orquidea.getFornecedor());
		if(orquidea.getDataHora() != null)
			map.put("data_hora", orquidea.getDataHora().toString());
		if(orquidea.getUsuario() != null)
			map.put("usuario", orquidea.getUsuario());
		if(orquidea.getIdPrecificacao() != null)
			map.put("prc_id", orquidea.getIdPrecificacao().toString());
		
		return map;
	}

}
