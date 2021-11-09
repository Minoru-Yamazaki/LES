package br.com.ecommerceorquideas.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Troca extends EntidadeDominio{

	private Integer proId;
	private Integer quantidade;
	private Integer qtdeMax;
	private Double preco;
	private Integer comId;
	private Boolean retornarEstoque;
	
	public Troca(Integer id, Integer proId, Integer quantidade, Integer qtdeMax, Double preco, Integer comId, boolean retornarEstoque) {
		super(id);
		this.proId = proId;
		this.quantidade = quantidade;
		this.qtdeMax = qtdeMax;
		this.preco = preco;
		this.comId = comId;
		this.retornarEstoque = retornarEstoque;
	}
}
