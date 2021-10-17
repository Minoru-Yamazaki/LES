package br.com.ecommerceorquideas.model;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Compra extends EntidadeDominio{
	
	private String status;
	private Date data;
	private Double valor;
	private Double frete;
	private String cidade;
	private String estado;
	private String pais;
	private String bairro;
	private String tipoLogradouro;
	private String logradouro;
	private String numero;
	private String complemento;
	private String tipoResidencia;
	private String cep;	
	private Integer cliId;
	private List<Produto> produtos;
	private List<CupomCompra> cupons;
	private List<CartoesCompra> cartoes;
	
	public Compra() {}
	
	public Compra(Integer id, String status, Date data, Double valor, Double frete, String cidade, 
			String estado, String pais, String bairro, String tipoLogradouro, String logradouro,
			String numero, String complemento, String tipoResidencia, String cep, Integer cliId) {
		super(id);
		this.status = status;
		this.data = data;
		this.valor = valor;
		this.frete = frete;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
		this.bairro = bairro;
		this.tipoLogradouro = tipoLogradouro ;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.tipoResidencia = tipoResidencia;
		this.cep = cep;
		this.cliId = cliId;
	}
}
