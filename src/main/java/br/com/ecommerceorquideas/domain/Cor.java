package br.com.ecommerceorquideas.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Cor extends EntidadeDominio{
	
	private String cor;
	private Integer ativo;
	
	public Cor(Integer id, String cor, Integer ativo) {
		super(id);
		this.cor = cor;
		this.ativo = ativo;
	}
}
