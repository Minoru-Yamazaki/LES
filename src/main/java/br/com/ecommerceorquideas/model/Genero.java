package br.com.ecommerceorquideas.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Genero extends EntidadeDominio{
	
	private String genero;
	private Integer ativo;

	public Genero(Integer id, String genero, Integer ativo){
		super(id);
		this.genero = genero;
		this.ativo = ativo;
	}
}
