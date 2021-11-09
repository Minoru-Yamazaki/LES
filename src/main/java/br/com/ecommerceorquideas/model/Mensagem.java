package br.com.ecommerceorquideas.model;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Mensagem extends EntidadeDominio{

	private String mensagem;
	private Date data;
	private Integer cliId;
	
	public Mensagem(Integer id, String mensagem, Date data, Integer cliId) {
		super(id);
		this.mensagem = mensagem;
		this.data = data;
		this.cliId = cliId;
	}
}
