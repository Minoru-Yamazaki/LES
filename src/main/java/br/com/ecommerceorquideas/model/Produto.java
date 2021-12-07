package br.com.ecommerceorquideas.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Produto extends EntidadeDominio{
	
	private Integer idProduto;
	private Double preco;
	private String nome;
	private String descricao;
	private Integer quantidade;
	private Double subTotal;
	private Integer idCompra;	
	//Não tem no construtor
	private Date data;
	
	public Produto() {}
	
	public Produto(Integer id, Integer idProduto, Double preco, String nome, String descricao,Integer quantidade, Double subTotal, Integer idCompra) {
		super(id);
		this.idProduto = idProduto;
		this.preco = preco;
		this.nome = nome;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.subTotal = subTotal;
		this.idCompra = idCompra;
	}
}
