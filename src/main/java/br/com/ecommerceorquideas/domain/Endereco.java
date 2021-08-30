package br.com.ecommerceorquideas.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Endereco extends EntidadeDominio{

	private String cidade;
	private String estado;
	private String pais;
	private String bairro;
	private String tipoLogradouro;
	private String logradouro;
	private String numero;
	private String complemento;
	private String tipoEndereco;
	private Integer idCliente;
	
	public Endereco(Integer id, String cidade, String estado, String pais, String bairro, String tipoLogradouro,
			String logradouro, String numero, String complemento, String tipoEndereco, Integer idCliente){
		super(id);
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
		this.bairro = bairro;
		this.tipoLogradouro = tipoLogradouro;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.tipoEndereco = tipoEndereco;
		this.idCliente = idCliente;
	}
}
