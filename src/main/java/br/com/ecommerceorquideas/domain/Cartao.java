package br.com.ecommerceorquideas.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Cartao extends EntidadeDominio{
	
	private String apelido;
	private String numero;
	private String nomeImpresso;
	private String codigoSeguranca;
	private String bandeira;
	private Integer idCliente;
	private Integer preferencial;
	
	public Cartao(Integer id, String apelido, String numero, String nomeImpresso, String codigoSeguranca,
			Integer idCliente, String bandeira, Integer preferencial) {
		super(id);
		this.apelido = apelido;
		this.numero = numero;
		this.nomeImpresso = nomeImpresso;
		this.codigoSeguranca = codigoSeguranca;
		this.bandeira = bandeira;
		this.idCliente = idCliente;		
		this.preferencial = preferencial;
	}
}
