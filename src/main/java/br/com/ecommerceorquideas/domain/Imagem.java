package br.com.ecommerceorquideas.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Imagem extends EntidadeDominio{

	private String foto;
	private Integer idOrquidea;
	
	public Imagem(Integer id, String foto, Integer idOrquidea) {
		super(id);
		this.foto = foto;
		this.idOrquidea = idOrquidea;
	}
}
