package br.com.ecommerceorquideas.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Endereco extends EntidadeDominio{

	private String apelido;
	private String cidade;
	private String estado;
	private String cep;
	private String pais;
	private String bairro;
	private String tipoLogradouro;
	private String logradouro;
	private String numero;
	private String complemento;
	private String tipoResidencia;
	private Integer idCliente;
	
	public Endereco(Integer id, String apelido, String cidade, String estado, String pais, String bairro, String tipoLogradouro,
			String logradouro, String numero, String complemento, String tipoRedidencia, Integer idCliente, String cep){
		super(id);
		this.apelido = apelido;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
		this.bairro = bairro;
		this.tipoLogradouro = tipoLogradouro;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.tipoResidencia = tipoRedidencia;
		this.idCliente = idCliente;
		this.cep = cep;
	}
}
