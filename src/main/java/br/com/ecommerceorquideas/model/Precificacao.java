package br.com.ecommerceorquideas.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Precificacao extends EntidadeDominio{

	Integer porcentagem;
	
	public Precificacao(Integer id, Integer porcentagem) {
		super(id);
		this.porcentagem = porcentagem;
	}
}
