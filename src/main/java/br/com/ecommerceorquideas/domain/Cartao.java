package br.com.ecommerceorquideas.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Cartao extends EntidadeDominio{
	
	private String numero;
	private String nomeImpresso;
	private String codigoSeguranca;
	private String bandeira;
	private Integer idCliente;
	
	public Cartao(Integer id, String numero, String nomeImpresso, String codigoSeguranca,
			Integer idCliente, String bandeira) {
		super(id);
		this.numero = numero;
		this.nomeImpresso = nomeImpresso;
		this.codigoSeguranca = codigoSeguranca;
		this.bandeira = bandeira;
		this.idCliente = idCliente;		
	}
}
